/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.impliedfeline.juisynth.logic;

import java.util.Random;

/**
 *
 * @author juicyp
 */
public class Oscillator implements SignalSource {

    public enum Waveform {
        SIN, SQU, SAW, TRI, NOI
    }

    private SignalSource signalSource = null;

    private Waveform waveform = Waveform.SIN;
    private int tuning = 0;

    private boolean bypass = false;

    private boolean fm = false;
    private double fmDepth = 0.0;

    private boolean am = false;
    private double amDepth = 0.0;

    private boolean sync = false;
    private boolean invert = false;
    private boolean invertOnSync = false;
    
    private Random noiseGenerator = new Random();

    public Oscillator() {
    }

    public void setSignalSource(SignalSource signalSource) {
        this.signalSource = signalSource;
    }

    public void setWaveform(Waveform waveform) {
        this.waveform = waveform;
    }

    public void setTuning(int tuning) {
        this.tuning = tuning;
    }

    public void setBypass(boolean bypass) {
        this.bypass = bypass;
    }

    public void setFm(boolean fm) {
        this.fm = fm;
    }

    public void setFmDepth(double fmDepth) {
        if (fmDepth < -1 || fmDepth > 1) {
            return;
        }
        this.fmDepth = fmDepth;
    }

    public void setAm(boolean am) {
        this.am = am;
    }

    public void setAmDepth(double amDepth) {
        if (amDepth < -1 || amDepth > 1) {
            return;
        }
        this.amDepth = amDepth;
    }

    public void setSync(boolean sync) {
        this.sync = sync;
    }

    public void setInverse(boolean invert) {
        this.invert = invert;
    }

    public void setInverseOnSync(boolean inverseOnSync) {
        this.invertOnSync = inverseOnSync;
    }

    @Override
    public void generateSample(SignalStatus signal) {

        if (bypass) {
            if (signalSource != null) {
                signalSource.generateSample(signal);
            }
            return;
        }

        double amplitude = generateWaveY(signal);

        if (fm) {
            applyFM(amplitude, signal);
        }

        if (signalSource != null) {
            signalSource.generateSample(signal);
        }

        if (am) {
            applyAM(amplitude, signal);
        }
        
        signal.setAmplitude(amplitude);

    }

    private double generateWaveY(SignalStatus signal) {

        int samplesInPeriod = (int) (signal.getSampleRate() / signal.getFrequency());
        double x = (signal.getBufferIndex() % samplesInPeriod) / (double) samplesInPeriod;
        boolean inverse = invert;

        if (sync && signal.getCompletePeriod()) {
            x = 0;
            if (invertOnSync) {
                inverse = !inverse;
            }
        }

        double y;

        // TODO: Refactor into separate method
        switch (waveform) {

            default:
            case SIN:
                y = Math.sin(2.0 * Math.PI * x);
                break;

            case SQU:
                if (x < 0.5) {
                    y = 1.0;
                } else {
                    y = -1.0;
                }
                break;

            case SAW:
                y = 2.0 * (x - Math.floor(x + 0.5));
                break;

            case TRI:
                y = Math.abs(x % samplesInPeriod - 2) - 1;
                break;

            // TODO: Noise generator field, don't instantiate new object on each
            // sample fetch.
            case NOI:
                y = 2 * noiseGenerator.nextDouble() - 1;
        }

        if (inverse) {
            y = -y;
        }

        if (signal.getBufferIndex() % samplesInPeriod == 0) {
            signal.setCompletePeriod();
        }

        return y;
    }
    
    private void applyFM(double amplitude, SignalStatus signal) {
        signal.setFrequency(signal.getFrequency() * Math.pow(2, amplitude * fmDepth));
    }
    
    private void applyAM(double amplitude, SignalStatus signal) {
        signal.setAmplitude(signal.getAmplitude() * Math.pow(2, amplitude * amDepth));
    }

}
