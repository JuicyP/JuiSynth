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
public class OscillatorTest {
    
    private Oscillator o;
    private int sampleRate = 16;
    private double frequency = 2;
    private int samplesInPeriod = sampleRate / (int) frequency;
    
    public OscillatorTest() {
    }
    
    @Before
    public void setUp() {
        o = new Oscillator();
        o.setWaveform(Oscillator.Waveform.SIN);
        
        o.setBypass(false);
        o.setAdd(true);
        
        o.setFm(false);
        o.setAm(false);
        
        o.setSync(false);
        o.setInverse(false);
        o.setInverseOnSync(false);
    }
    
    @Test
    public void generateWaveAmplitudeSineReturnsZeroWithZeroIndexBuffer() {
        SignalStatus s = new SignalStatus(sampleRate, 0, frequency);
        o.generateSample(s);
        assertEquals(0, s.getAmplitude(), 0.1);
    }
    
    @Test
    public void generateWaveAmplitudeSineReturnsZeroWithHalfPhaseIndexBuffer() {
        SignalStatus s = new SignalStatus(sampleRate, samplesInPeriod / 2, frequency);
        o.generateSample(s);
        assertEquals(0, s.getAmplitude(), 0.1);
    }
    
    @Test
    public void generateWaveAmplitudeSineReturnsZeroWithFullPhaseIndexBuffer() {
        SignalStatus s = new SignalStatus(sampleRate, samplesInPeriod, frequency);
        o.generateSample(s);
        assertEquals(0, s.getAmplitude(), 0.1);
    }
    
    @Test
    public void generateWaveAmplitudeSineReturnsOneWithQuarterPhaseIndexBuffer() {
        SignalStatus s = new SignalStatus(sampleRate, samplesInPeriod / 4, frequency);
        o.generateSample(s);
        assertEquals(1, s.getAmplitude(), 0.1);
    }
    
    @Test
    public void generateWaveAmplitudeSineReturnsNegativeOneWithThreeQuartersPhaseIndexBuffer() {
        SignalStatus s = new SignalStatus(sampleRate, 3 * (samplesInPeriod / 4), frequency);
        o.generateSample(s);
        assertEquals(-1, s.getAmplitude(), 0.1);
    }
    
    @Test
    public void generateWaveAmplitudeTriangleReturnsOneWithZeroIndexBuffer() {
        o.setWaveform(Oscillator.Waveform.TRI);
        SignalStatus s = new SignalStatus(sampleRate, 0, frequency);
        o.generateSample(s);
        assertEquals(-1, s.getAmplitude(), 0.1);
    }
    
    @Test
    public void generateWaveAmplitudeTriangleReturnsOneWithHalfPhaseIndexBuffer() {
        o.setWaveform(Oscillator.Waveform.TRI);
        SignalStatus s = new SignalStatus(sampleRate, samplesInPeriod / 2, frequency);
        o.generateSample(s);
        assertEquals(1, s.getAmplitude(), 0.1);
    }
    
    @Test
    public void generateWaveAmplitudeTriangleReturnsNegativeOneWithFullPhaseIndexBuffer() {
        o.setWaveform(Oscillator.Waveform.TRI);
        SignalStatus s = new SignalStatus(sampleRate, samplesInPeriod, frequency);
        o.generateSample(s);
        assertEquals(-1, s.getAmplitude(), 0.1);
    }
    
    @Test
    public void generateWaveAmplitudeTriangleReturnsZeroWithQuarterPhaseIndexBuffer() {
        o.setWaveform(Oscillator.Waveform.TRI);
        SignalStatus s = new SignalStatus(sampleRate, samplesInPeriod / 4, frequency);
        o.generateSample(s);
        assertEquals(0, s.getAmplitude(), 0.1);
    }
    
    @Test
    public void generateWaveAmplitudeTriangleReturnsZeroWithThreeQuartersPhaseIndexBuffer() {
        o.setWaveform(Oscillator.Waveform.TRI);
        SignalStatus s = new SignalStatus(sampleRate, 3 * (samplesInPeriod / 4), frequency);
        o.generateSample(s);
        assertEquals(0, s.getAmplitude(), 0.1);
    }
    
    @Test
    public void generateWaveAmplitudeSquareReturnsOneWithZeroIndexBuffer() {
        o.setWaveform(Oscillator.Waveform.SQU);
        SignalStatus s = new SignalStatus(sampleRate, 0, frequency);
        o.generateSample(s);
        assertEquals(1, s.getAmplitude(), 0.1);
    }
    
    @Test
    public void generateWaveAmplitudeSquareReturnsNegativeOneWithHalfPhaseIndexBuffer() {
        o.setWaveform(Oscillator.Waveform.SQU);
        SignalStatus s = new SignalStatus(sampleRate, samplesInPeriod / 2, frequency);
        o.generateSample(s);
        assertEquals(-1, s.getAmplitude(), 0.1);
    }
    
    @Test
    public void generateWaveAmplitudeSquareReturnsOneWithFullPhaseIndexBuffer() {
        o.setWaveform(Oscillator.Waveform.SQU);
        SignalStatus s = new SignalStatus(sampleRate, samplesInPeriod, frequency);
        o.generateSample(s);
        assertEquals(1, s.getAmplitude(), 0.1);
    }
    
    @Test
    public void generateWaveAmplitudeSquareReturnsOneWithQuarterPhaseIndexBuffer() {
        o.setWaveform(Oscillator.Waveform.SQU);
        SignalStatus s = new SignalStatus(sampleRate, samplesInPeriod / 4, frequency);
        o.generateSample(s);
        assertEquals(1, s.getAmplitude(), 0.1);
    }
    
    @Test
    public void generateWaveAmplitudeSquareReturnsNegativeOneWithThreeQuartersPhaseIndexBuffer() {
        o.setWaveform(Oscillator.Waveform.SQU);
        SignalStatus s = new SignalStatus(sampleRate, 3 * (samplesInPeriod / 4), frequency);
        o.generateSample(s);
        assertEquals(-1, s.getAmplitude(), 0.1);
    }
    
    @Test
    public void generateWaveAmplitudeSawReturnsZeroWithZeroIndexBuffer() {
        o.setWaveform(Oscillator.Waveform.SAW);
        SignalStatus s = new SignalStatus(sampleRate, 0, frequency);
        o.generateSample(s);
        assertEquals(0, s.getAmplitude(), 0.1);
    }
    
    @Test
    public void generateWaveAmplitudeSawReturnsNegativeOneWithHalfPhaseIndexBuffer() {
        o.setWaveform(Oscillator.Waveform.SAW);
        SignalStatus s = new SignalStatus(sampleRate, samplesInPeriod / 2, frequency);
        o.generateSample(s);
        assertEquals(-1, s.getAmplitude(), 0.1);
    }
    
    @Test
    public void generateWaveAmplitudeSawReturnsZeroWithFullPhaseIndexBuffer() {
        o.setWaveform(Oscillator.Waveform.SAW);
        SignalStatus s = new SignalStatus(sampleRate, samplesInPeriod, frequency);
        o.generateSample(s);
        assertEquals(0, s.getAmplitude(), 0.1);
    }
    
    @Test
    public void generateWaveAmplitudeSawReturnsHalfWithQuarterPhaseIndexBuffer() {
        o.setWaveform(Oscillator.Waveform.SAW);
        SignalStatus s = new SignalStatus(sampleRate, samplesInPeriod / 4, frequency);
        o.generateSample(s);
        assertEquals(0.5, s.getAmplitude(), 0.1);
    }
    
    @Test
    public void generateWaveAmplitudeSawReturnsNegativeHalfWithThreeQuartersPhaseIndexBuffer() {
        o.setWaveform(Oscillator.Waveform.SAW);
        SignalStatus s = new SignalStatus(sampleRate, 3 * (samplesInPeriod / 4), frequency);
        o.generateSample(s);
        assertEquals(-0.5, s.getAmplitude(), 0.1);
    }
    
    @Test
    public void generateWaveAmplitudeDoesNotModifySampleWithBypass() {
        o.setWaveform(Oscillator.Waveform.SQU);
        o.setBypass(true);
        SignalStatus s = new SignalStatus(sampleRate, 0, frequency);
        o.generateSample(s);
        assertEquals(0, s.getAmplitude(), 0.1);
    }
    
    @Test
    public void generateWaveAmplitudeModifiesFrequencyWithFM() {
        o.setWaveform(Oscillator.Waveform.SQU);
        o.setFm(true);
        o.setFmDepth(1);
        SignalStatus s = new SignalStatus(sampleRate, 0, frequency);
        o.generateSample(s);
        assertEquals(4, s.getFrequency(), 0.1);
    }
    
    @Test
    public void generateWaveAmplitudeDoesNotModifyFrequencyWithFMDepthZero() {
        o.setWaveform(Oscillator.Waveform.SQU);
        o.setFm(true);
        o.setFmDepth(0);
        SignalStatus s = new SignalStatus(sampleRate, 0, frequency);
        o.generateSample(s);
        assertEquals(2, s.getFrequency(), 0.1);
    }
    // Signal paths of more than one oscillator into their own class
}
