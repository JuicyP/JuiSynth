/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.impliedfeline.juisynth.logic;

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

    private boolean bypass = false;

    private boolean fm = false;
    private double fmDepth = 0.0;

    private boolean am = false;
    private double amDepth = 0.0;

    private boolean sync = false;
    private boolean inverse = false;
    private boolean inverseOnSync = false;

    public Oscillator() {
    }

    public void setSignalSource(SignalSource signalSource) {
        this.signalSource = signalSource;
    }

    public void setWaveform(Waveform waveform) {
        this.waveform = waveform;
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

    public void setInverse(boolean inverse) {
        this.inverse = inverse;
    }

    public void setInverseOnSync(boolean inverseOnSync) {
        this.inverseOnSync = inverseOnSync;
    }

    @Override
    public void setSample(SignalStatus signal) {

        if (signalSource != null) {
            signalSource.setSample(signal);
        }

        if (bypass) {
            return;
        }

    }

}
