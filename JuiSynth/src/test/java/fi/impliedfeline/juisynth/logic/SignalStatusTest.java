/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.impliedfeline.juisynth.logic;

import fi.impliedfeline.juisynth.logic.signal.SignalStatus;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author juicyp
 */
public class SignalStatusTest {

    private SignalStatus s;
    private int sampleRate;
    private int bufferIndex;
    private double frequency;

    public SignalStatusTest() {
    }

    @Before
    public void setUp() {
        sampleRate = 44100;
        bufferIndex = 0;
        frequency = 440;
        s = new SignalStatus(bufferIndex, frequency);
    }

    @Test
    public void constructorValuesSet() {
        assertEquals(0, s.getAmplitude(), 0.1);
        assertEquals(false, s.getAndUpdateCompletePeriod());
        assertEquals(bufferIndex, s.getBufferIndex());
        assertEquals(frequency, s.getFrequency(), 0.1);
    }

    @Test
    public void setFrequencyPositiveSets() {
        frequency = 880;
        s.setFrequency(frequency);
        assertEquals(frequency, s.getFrequency(), 0.1);
    }

    @Test
    public void setFrequencyNegativeFails() {
        double frequency = -1;
        s.setFrequency(frequency);
        assertEquals(this.frequency, s.getFrequency(), 0.1);
    }

    @Test
    public void setFrequencyZeroSets() {
        double frequency = 0;
        s.setFrequency(frequency);
        assertEquals(frequency, s.getFrequency(), 0.1);
    }

    @Test
    public void setAmplitudeOneSets() {
        double amplitude = 1;
        s.setAmplitude(amplitude);
        assertEquals(amplitude, s.getAmplitude(), 0.1);
    }

    @Test
    public void setAmplitudeNegativeOneSets() {
        double amplitude = -1;
        s.setAmplitude(amplitude);
        assertEquals(amplitude, s.getAmplitude(), 0.1);
    }

    @Test
    public void setAmplitudeZeroSets() {
        double amplitude = 0;
        s.setAmplitude(1);
        s.setAmplitude(amplitude);
        assertEquals(amplitude, s.getAmplitude(), 0.1);
    }

    @Test
    public void setAmplitudeLargerThanOneFails() {
        double amplitude = 1.1;
        s.setAmplitude(amplitude);
        assertEquals(0, s.getAmplitude(), 0.1);
    }

    @Test
    public void setAmplitudeLessThanNegativeOneFails() {
        double amplitude = -1.1;
        s.setAmplitude(amplitude);
        assertEquals(0, s.getAmplitude(), 0.1);
    }

    @Test
    public void setCompletePeriodSets() {
        s.setCompletePeriodTrue();
        assertTrue(s.getAndUpdateCompletePeriod());
    }

    @Test
    public void successiveGetCompletePeriodReturnsFalse() {
        s.setCompletePeriodTrue();
        s.getAndUpdateCompletePeriod();
        assertFalse(s.getAndUpdateCompletePeriod());
    }

}
