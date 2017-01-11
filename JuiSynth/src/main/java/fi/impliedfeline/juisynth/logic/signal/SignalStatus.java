/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.impliedfeline.juisynth.logic.signal;

/**
 * Contains information relevant to a sample in a signal path.
 *
 * @author juicyp
 */
public class SignalStatus {

    private int bufferIndex;

    private double frequency;
    private double amplitude = 0;

    private boolean activeNote = true;
    private boolean completePeriod = false;
    private int activeOperatorCount = 0;

    /**
     * Constructor for SignalStatus object sets sample rate, index of buffer and
     * frequency of signal.
     *
     * @param bufferIndex The sample index
     * @param frequency The frequency of the periodic waveform to be generated
     */
    public SignalStatus(int bufferIndex, double frequency) {
        this.bufferIndex = bufferIndex;
        this.frequency = frequency;
    }

    public int getBufferIndex() {
        return bufferIndex;
    }

    public void setBufferIndex(int bufferIndex) {
        this.bufferIndex = bufferIndex;
    }

    public double getFrequency() {
        return frequency;
    }

    /**
     * Sets non-negative frequency.
     *
     * @param frequency
     */
    public void setFrequency(double frequency) {
        if (frequency < 0) {
            return;
        }
        this.frequency = frequency;
    }

    public double getAmplitude() {
        return amplitude;
    }

    /**
     * Sets amplitude between -1 and 1 inclusive.
     *
     * @param amplitude
     */
    public void setAmplitude(double amplitude) {
        if (amplitude < -1 || amplitude > 1) {
            return;
        }
        this.amplitude = amplitude;
    }

    /**
     * Returns value of completePeriod field and sets to false.
     *
     * @return returns value of completePeriod field.
     */
    public boolean getCompletePeriod() {
        return completePeriod;
    }
    
    public void setCompletePeriod(boolean completePeriod) {
        this.completePeriod = completePeriod;
    }

    public int getActiveOperatorCount() {
        return activeOperatorCount;
    }
    
    public void setActiveOperatorCount(int activeOperatorCount) {
        this.activeOperatorCount = activeOperatorCount;
    }

    public boolean getActiveNote() {
        return activeNote;
    }

    public void setActiveNote(boolean activeNote) {
        this.activeNote = activeNote;
    }
}
