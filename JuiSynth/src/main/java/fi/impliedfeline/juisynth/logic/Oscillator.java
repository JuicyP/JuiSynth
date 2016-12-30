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
    // To be implemented. Adjusts frequency used on calculations by cents.
    private int tuning = 0;

    private boolean bypass = false;
    private boolean add = false;

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
    
    public void setAdd(boolean add) {
        this.add = add;
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

        double amplitude = generateWaveAmplitude(signal);

        if (fm) {
            applyFM(amplitude, signal);
        }

        if (signalSource != null) {
            signalSource.generateSample(signal);
        }

        if (am) {
            applyAM(amplitude, signal);
        }

        if (add) {
            if (signalSource != null) {
                signal.setAmplitude(signal.getAmplitude() * amplitude);
            } else {
                signal.setAmplitude(amplitude);
            }
        }

    }

    private double generateWaveAmplitude(SignalStatus signal) {

        int samplesInPeriod = (int) (signal.getSampleRate() / signal.getFrequency());
        double x = (signal.getBufferIndex() % samplesInPeriod) / (double) samplesInPeriod;
        boolean inverse = invert;

        if (sync && signal.getAndUpdateCompletePeriod()) {
            x = 0;
            if (invertOnSync) {
                inverse = !inverse;
            }
        }

        double y = calculateFunctionY(x);

        if (inverse) {
            y = -y;
        }

        if (signal.getBufferIndex() % samplesInPeriod == 0) {
            signal.setCompletePeriodTrue();
        }

        return y;
    }

    // Encapsulate into own class?
    private double calculateFunctionY(double x) {

        double y;

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
                y = 2.0 * Math.abs(2.0 * (x - Math.floor(x + 0.5))) - 1;
                break;

            case NOI:
                y = 2 * noiseGenerator.nextDouble() - 1;
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
