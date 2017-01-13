/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package juisynth.logic.signal;

/**
 * Contains information relevant to a sample in a signal path.
 *
 * @author juicyp
 */
public class SignalStatus {

    private double frequency;
    private final double frequencyInit;
    
    //TODO: Remove amplitude
    private double amplitude;
    private final double amplitudeInit = 0;

    private boolean activeNote;
    private final boolean activeNoteInit = false;
    
    private boolean completePeriod;
    private final boolean completePeriodInit = false;
    
    private int activeOperatorCount;
    private final int activeOperatorCountInit = 0;

    /**
     * Constructor for SignalStatus object sets sample rate, index of buffer and
     * frequency of signal.
     *
     * @param frequency The frequency of the periodic waveform to be generated
     */
    public SignalStatus(double frequency) {
        this.frequencyInit = frequency;
        resetSignal();
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

    public void resetSignal() {
        resetFrequency();
        this.amplitude = amplitudeInit;
        this.activeNote = activeNoteInit;
        this.completePeriod = completePeriodInit;
        this.activeOperatorCount = activeOperatorCountInit;
    }
    
    public void resetFrequency() {
        this.frequency = frequencyInit;
    }
}
