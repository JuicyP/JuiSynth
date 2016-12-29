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
public class SignalStatus {
    
    private int sampleRate;
    private int bufferIndex;
    
    private double frequency;
    private double amplitude = 0;
    
    private boolean completePeriod = false;
    
    public SignalStatus(int sampleRate, int bufferIndex, double frequency) {
        this.sampleRate = sampleRate;
        this.bufferIndex = bufferIndex;
        this.frequency = frequency;
    }
    
    public int getSampleRate() {
        return sampleRate;
    }
    
    public int getBufferIndex() {
        return bufferIndex;
    }
    
    public double getFrequency() {
        return frequency;
    }
    
    public void setFrequency(double frequency) {
        if (frequency < 0) {
            return;
        }
        this.frequency = frequency;
    }
    
    public double getAmplitude() {
        return amplitude;
    }
    
    public void setAmplitude(double amplitude) {
        if (amplitude < -1 || amplitude > 1) {
            return;
        }
        this.amplitude = amplitude;
    }
    
    public boolean getCompletePeriod() {
        if (completePeriod) {
            completePeriod = false;
            return true;
        }
            
        return false;
    }
    
    public void setCompletePeriod() {
        this.completePeriod = true;
    }
    
    
}
