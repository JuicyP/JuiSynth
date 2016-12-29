/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.impliedfeline.juisynth.logic;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author juicyp
 */
public class SignalStatusTest {
    
    private SignalStatus signal;
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
        signal = new SignalStatus(sampleRate, bufferIndex, frequency);
    }
    
    @Test
    public void constructorValuesSet() {
        assertEquals(0, signal.getAmplitude(), 0.1);
        assertEquals(false, signal.getCompletePeriod());
        assertEquals(sampleRate, signal.getSampleRate());
        assertEquals(bufferIndex, signal.getBufferIndex());
        assertEquals(frequency, signal.getFrequency(), 0.1);
    }
    
    @Test
    public void setFrequencyPositiveSets() {
        frequency = 880;
        signal.setFrequency(frequency);
        assertEquals(frequency, signal.getFrequency(), 0.1);
    }
    
    @Test
    public void setFrequencyNegativeFails() {
        double frequency = -1;
        signal.setFrequency(frequency);
        assertEquals(this.frequency, signal.getFrequency(), 0.1);
    }
    
    @Test
    public void setFrequencyZeroSets() {
        double frequency = 0;
        signal.setFrequency(frequency);
        assertEquals(frequency, signal.getFrequency(), 0.1);
    }
    
    @Test
    public void setAmplitudeOneSets() {
        double amplitude = 1;
        signal.setAmplitude(amplitude);
        assertEquals(amplitude, signal.getAmplitude(), 0.1);
    }
    
    @Test
    public void setAmplitudeNegativeOneSets() {
        double amplitude = -1;
        signal.setAmplitude(amplitude);
        assertEquals(amplitude, signal.getAmplitude(), 0.1);
    }
    
    @Test
    public void setAmplitudeZeroSets() {
        double amplitude = 0;
        signal.setAmplitude(1);
        signal.setAmplitude(amplitude);
        assertEquals(amplitude, signal.getAmplitude(), 0.1);
    }
    
    @Test
    public void setAmplitudeLargerThanOneFails() {
        double amplitude = 1.1;
        signal.setAmplitude(amplitude);
        assertEquals(0, signal.getAmplitude(), 0.1);
    }
    
    @Test
    public void setAmplitudeLessThanNegativeOneFails() {
        double amplitude = -1.1;
        signal.setAmplitude(amplitude);
        assertEquals(0, signal.getAmplitude(), 0.1);
    }
    
    @Test
    public void setCompletePeriodSets() {
        signal.setCompletePeriod();
        assertTrue(signal.getCompletePeriod());
    }
    
    @Test
    public void successiveGetCompletePeriodReturnsFalse() {
        signal.setCompletePeriod();
        signal.getCompletePeriod();
        assertFalse(signal.getCompletePeriod());
    }
    
    
    
    
}
