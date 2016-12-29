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
    
    private SignalSource signalSource;
    
    private Waveform waveform;
    
    private boolean bypass;
    
    private boolean fm;
    private double fmDepth;
    
    private boolean am;
    private double amDepth;
    
    private boolean sync;
    private boolean inverse;
    private boolean inverseOnSync;
    
    public Oscillator() {
        this.
    }

    @Override
    public void setSample(SignalStatus signal) {
        
        if (signalSource != null)
            signalSource.setSample(signal);       
        
    }
    
}
