/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.impliedfeline.juisynth.logic;

/**
 * Contains information relevant to a sample in a signal path.
 *
 * @author juicyp
 */
public class SignalStatus {

    // Encapsulate sampleRate into singleton?
    private int sampleRate;
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
    public boolean getAndUpdateCompletePeriod() {
        if (completePeriod) {
            completePeriod = false;
            return true;
        }

        return false;
    }

    /**
     * Sets completePeriod-field to true.
     */
    public void setCompletePeriodTrue() {
        this.completePeriod = true;
    }

    public int getActiveOperatorCount() {
        return activeOperatorCount;
    }

    public boolean getActiveNote() {
        return activeNote;
    }

    public void setActiveNote(boolean activeNote) {
        this.activeNote = activeNote;
    }

    // Should I really implement logic in accessors? Seems really off
    public void setActiveOperatorCountToOneHigher() {
        activeOperatorCount++;
    }

}
