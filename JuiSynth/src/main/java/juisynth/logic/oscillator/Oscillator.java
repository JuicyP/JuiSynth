/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package juisynth.logic.oscillator;

import juisynth.logic.Waveform;
import juisynth.logic.WaveformCalculator;
import juisynth.logic.envelope.EnvelopeGenerator;
import juisynth.logic.filter.Filter;
import juisynth.logic.player.Settings;
import juisynth.logic.signal.SignalStatus;
import juisynth.logic.signal.SignalSource;

/**
 * Receives a SignalStatus-object along a signal path consisting of
 * SignalSources and modifies it based on it's state. Oscillator implements
 * SignalSource.
 *
 * @see SignalSource SignalStatus
 * @author juicyp
 */
public class Oscillator implements SignalSource {

    private SignalSource signalSource = null;

    private Waveform waveform = Waveform.SIN;
    
    private double phase = 0;

    private int tuning = 0;
    private double amp = 1;
    private boolean fixed = false;

    private boolean bypass = false;
    private boolean add = false;

    private boolean fm = false;
    private double fmDepth = 0.0;

    private boolean am = false;
    private double amDepth = 0.0;

    private boolean sync = false;
    private boolean invert = false;
    private boolean invertOnSync = false;
    
    private Filter filter;
    private EnvelopeGenerator envelopeGenerator;
    
    public Filter getFilter() {
        return filter;
    }
    
    public void setFilter(Filter filter) {
        this.filter = filter;
    }
    
    public EnvelopeGenerator getEnvelopeGenerator() {
        return envelopeGenerator;
    }
    
    public void setEnvelopeGenerator(EnvelopeGenerator envelopeGenerator) {
        this.envelopeGenerator = envelopeGenerator;
    }
    
    public double getPhase() {
        return phase;
    }
    
    public void setPhase(double phase) {
        this.phase = phase;
    }

    public void setSignalSource(SignalSource signalSource) {
        this.signalSource = signalSource;
    }

    public void setWaveform(Waveform waveform) {
        this.waveform = waveform;
    }

    public double getAmp() {
        return amp;
    }

    public void setAmp(double amp) {
        if (amp < 0 || amp > 1) {
            return;
        }
        this.amp = amp;
    }

    public int getTuning() {
        return tuning;
    }

    public void setTuning(int tuning) {
        this.tuning = tuning;
    }

    public boolean getFixed() {
        return fixed;
    }

    public void setFixed(boolean fixed) {
        this.fixed = fixed;
    }
    
    public boolean getBypass() {
        return bypass;
    }

    public void setBypass(boolean bypass) {
        this.bypass = bypass;
    }
    
    public boolean getAdd() {
        return add;
    }

    public void setAdd(boolean add) {
        this.add = add;
    }
    
    public boolean getFm() {
        return fm;
    }

    public void setFm(boolean fm) {
        this.fm = fm;
    }
    
    public double getFmDepth() {
        return fmDepth;
    }

    /**
     * Setter for depth of applied FM.
     *
     * @param fmDepth Double value between -1 and 1 inclusive.
     */
    public void setFmDepth(double fmDepth) {
        if (fmDepth < -1 || fmDepth > 1) {
            return;
        }
        this.fmDepth = fmDepth;
    }
    
    public boolean getAm() {
        return am;
    }

    public void setAm(boolean am) {
        this.am = am;
    }
    
    public double getAmDepth() {
        return amDepth;
    }

    /**
     * Setter for depth of applied AM.
     *
     * @param amDepth Double value between -1 and 1 inclusive.
     */
    public void setAmDepth(double amDepth) {
        if (amDepth < -1 || amDepth > 1) {
            return;
        }
        this.amDepth = amDepth;
    }
    
    public boolean getSync() {
        return sync;
    }

    public void setSync(boolean sync) {
        this.sync = sync;
    }
    
    public boolean getInvert() {
        return invert;
    }

    public void setInvert(boolean invert) {
        this.invert = invert;
    }
    
    public boolean getInvertOnSync() {
        return invertOnSync;
    }

    public void setInvertOnSync(boolean invertOnSync) {
        this.invertOnSync = invertOnSync;
    }

    /**
     * Branches based on state and modifies signal amplitude and/or frequency.
     *
     * @param signal SignalStatus passed along the signal path.
     */
    @Override
    public void generateSample(SignalStatus signal) {

        if (bypass) {
            if (signalSource != null) {
                signalSource.generateSample(signal);
            }
            return;
        }

        double amplitude = generateWaveAmplitude(signal);
        amplitude *= amp;

        if (fm) {
            applyFM(amplitude, signal);
        }

        // Prevent self-AM modulation
        if (add) {
            signal.setActiveOperatorCount(signal.getActiveOperatorCount() + 1);
        }

        if (signalSource != null) {
            signalSource.generateSample(signal);
        }

        if (am) {
            applyAM(amplitude, signal);
        }

        if (add) {
            if (signalSource != null) {
                signal.setAmplitude(signal.getAmplitude() + amplitude / signal.getActiveOperatorCount());
            } else {
                // Active operator count will always be non zero
                signal.setAmplitude(amplitude / signal.getActiveOperatorCount());
            }
        }
    }

    private double generateWaveAmplitude(SignalStatus signal) {

        double frequency = signal.getFrequency();

        if (fixed) {
            frequency = 440.0;
        }
        // Equal temperant tuning = 12 semitones in an octave,
        // one semitone = 100 cents.
        frequency *= Math.pow(2.0, (tuning / (double) 1200));

        phase += frequency / Settings.SAMPLE_RATE;
        
        boolean inverse = invert;
        
        if (sync && signal.getCompletePeriod()) {
            signal.setCompletePeriod(false);
            phase = 0;
            if (invertOnSync) {
                inverse = !inverse;
            }
        }
        
        if (phase == 0 || (phase % 1) < phase) {
            signal.setCompletePeriod(true);
        }
        
        phase %= 1;
        
        double y = WaveformCalculator.calculateWaveformY(phase, waveform);
        
        if (filter != null) {
            y = filter.generateFilter(phase, y);
        }
        
        if (inverse) {
            y = -y;
        }

        return y;
    }

    private void applyFM(double amplitude, SignalStatus signal) {
        signal.setFrequency(signal.getFrequency() * Math.pow(2, amplitude * fmDepth));
    }

    private void applyAM(double amplitude, SignalStatus signal) {
        signal.setAmplitude(signal.getAmplitude() * Math.pow(2, (amplitude - 1) * amDepth));
    }
}
