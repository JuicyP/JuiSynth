/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package juisynth.logic.oscillator;

import juisynth.logic.Waveform;

/**
 * Contains settings relevant to an oscillator.
 * @author juicyp
 */
public class OscillatorSettings {
    
    private int tuning = 0;
    private boolean fixed = false;
    private boolean sync = false;
    private Waveform waveform = Waveform.SIN;

    public int getTuning() {
        return tuning;
    }

    public void setTuning(int tuning) {
        this.tuning = tuning;
    }

    public boolean isFixed() {
        return fixed;
    }

    public void setFixed(boolean fixed) {
        this.fixed = fixed;
    }

    public boolean isSync() {
        return sync;
    }

    public void setSync(boolean sync) {
        this.sync = sync;
    }
    
    public Waveform getWaveform() {
        return waveform;
    }

    public void setWaveform(Waveform waveform) {
        this.waveform = waveform;
    }
}
