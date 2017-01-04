/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.impliedfeline.juisynth.logic;

/**
 * Contains information relevant to a sample in a signal path.
 * @author juicyp
 */
public class SignalStatus {

    // Encapsulate sampleRate into singleton?
    private int sampleRate;
    private int bufferIndex;

    private double frequency;
    private double amplitude = 0;

    private boolean completePeriod = false;

    /**
     * 
     * @param sampleRate The sample rate of the sample based signal path
     * @param bufferIndex The sample index
     * @param frequency The frequency of the periodic waveform to be generated
     */
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

    public boolean getAndUpdateCompletePeriod() {
        if (completePeriod) {
            completePeriod = false;
            return true;
        }

        return false;
    }

    public void setCompletePeriodTrue() {
        this.completePeriod = true;
    }

}
