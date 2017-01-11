/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.impliedfeline.juisynth.logic.oscillator;

import fi.impliedfeline.juisynth.logic.oscillator.Oscillator;
import fi.impliedfeline.juisynth.logic.oscillator.Waveform;
import fi.impliedfeline.juisynth.logic.player.Settings;
import fi.impliedfeline.juisynth.logic.signal.SignalStatus;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author juicyp
 */
public class OscillatorTest {
    
    private Oscillator o;
    private int sampleRate = Settings.SAMPLE_RATE;
    private double frequency = 2;
    private int samplesInPeriod = sampleRate / (int) frequency;
    
    public OscillatorTest() {
    }
    
    @Before
    public void setUp() {
        o = new Oscillator();
        o.setWaveform(Waveform.SIN);
        
        o.setBypass(false);
        o.setAdd(true);
        
        o.setFm(false);
        o.setAm(false);
        
        o.setSync(false);
        o.setInvert(false);
        o.setInvertOnSync(false);
    }
    
    @Test
    public void generateWaveAmplitudeSineReturnsZeroWithZeroIndexBuffer() {
        SignalStatus s = new SignalStatus(0, frequency);
        o.generateSample(s);
        assertEquals(0, s.getAmplitude(), 0.1);
    }
    
    @Test
    public void generateWaveAmplitudeSineReturnsZeroWithHalfPhaseIndexBuffer() {
        SignalStatus s = new SignalStatus(samplesInPeriod / 2, frequency);
        o.generateSample(s);
        assertEquals(0, s.getAmplitude(), 0.1);
    }
    
    @Test
    public void generateWaveAmplitudeSineReturnsZeroWithFullPhaseIndexBuffer() {
        SignalStatus s = new SignalStatus(samplesInPeriod, frequency);
        o.generateSample(s);
        assertEquals(0, s.getAmplitude(), 0.1);
    }
    
    @Test
    public void generateWaveAmplitudeSineReturnsOneWithQuarterPhaseIndexBuffer() {
        SignalStatus s = new SignalStatus(samplesInPeriod / 4, frequency);
        o.generateSample(s);
        assertEquals(1, s.getAmplitude(), 0.1);
    }
    
    @Test
    public void generateWaveAmplitudeSineReturnsNegativeOneWithThreeQuartersPhaseIndexBuffer() {
        SignalStatus s = new SignalStatus(3 * (samplesInPeriod / 4), frequency);
        o.generateSample(s);
        assertEquals(-1, s.getAmplitude(), 0.1);
    }
    
    @Test
    public void generateWaveAmplitudeTriangleReturnsOneWithZeroIndexBuffer() {
        o.setWaveform(Waveform.TRI);
        SignalStatus s = new SignalStatus(0, frequency);
        o.generateSample(s);
        assertEquals(-1, s.getAmplitude(), 0.1);
    }
    
    @Test
    public void generateWaveAmplitudeTriangleReturnsOneWithHalfPhaseIndexBuffer() {
        o.setWaveform(Waveform.TRI);
        SignalStatus s = new SignalStatus(samplesInPeriod / 2, frequency);
        o.generateSample(s);
        assertEquals(1, s.getAmplitude(), 0.1);
    }
    
    @Test
    public void generateWaveAmplitudeTriangleReturnsNegativeOneWithFullPhaseIndexBuffer() {
        o.setWaveform(Waveform.TRI);
        SignalStatus s = new SignalStatus(samplesInPeriod, frequency);
        o.generateSample(s);
        assertEquals(-1, s.getAmplitude(), 0.1);
    }
    
    @Test
    public void generateWaveAmplitudeTriangleReturnsZeroWithQuarterPhaseIndexBuffer() {
        o.setWaveform(Waveform.TRI);
        SignalStatus s = new SignalStatus(samplesInPeriod / 4, frequency);
        o.generateSample(s);
        assertEquals(0, s.getAmplitude(), 0.1);
    }
    
    @Test
    public void generateWaveAmplitudeTriangleReturnsZeroWithThreeQuartersPhaseIndexBuffer() {
        o.setWaveform(Waveform.TRI);
        SignalStatus s = new SignalStatus(3 * (samplesInPeriod / 4), frequency);
        o.generateSample(s);
        assertEquals(0, s.getAmplitude(), 0.1);
    }
    
    @Test
    public void generateWaveAmplitudeSquareReturnsOneWithZeroIndexBuffer() {
        o.setWaveform(Waveform.SQU);
        SignalStatus s = new SignalStatus(0, frequency);
        o.generateSample(s);
        assertEquals(1, s.getAmplitude(), 0.1);
    }
    
    @Test
    public void generateWaveAmplitudeSquareReturnsNegativeOneWithHalfPhaseIndexBuffer() {
        o.setWaveform(Waveform.SQU);
        SignalStatus s = new SignalStatus(samplesInPeriod / 2, frequency);
        o.generateSample(s);
        assertEquals(-1, s.getAmplitude(), 0.1);
    }
    
    @Test
    public void generateWaveAmplitudeSquareReturnsOneWithFullPhaseIndexBuffer() {
        o.setWaveform(Waveform.SQU);
        SignalStatus s = new SignalStatus(samplesInPeriod, frequency);
        o.generateSample(s);
        assertEquals(1, s.getAmplitude(), 0.1);
    }
    
    @Test
    public void generateWaveAmplitudeSquareReturnsOneWithQuarterPhaseIndexBuffer() {
        o.setWaveform(Waveform.SQU);
        SignalStatus s = new SignalStatus(samplesInPeriod / 4, frequency);
        o.generateSample(s);
        assertEquals(1, s.getAmplitude(), 0.1);
    }
    
    @Test
    public void generateWaveAmplitudeSquareReturnsNegativeOneWithThreeQuartersPhaseIndexBuffer() {
        o.setWaveform(Waveform.SQU);
        SignalStatus s = new SignalStatus(3 * (samplesInPeriod / 4), frequency);
        o.generateSample(s);
        assertEquals(-1, s.getAmplitude(), 0.1);
    }
    
    @Test
    public void generateWaveAmplitudeSawReturnsZeroWithZeroIndexBuffer() {
        o.setWaveform(Waveform.SAW);
        SignalStatus s = new SignalStatus(0, frequency);
        o.generateSample(s);
        assertEquals(0, s.getAmplitude(), 0.1);
    }
    
    @Test
    public void generateWaveAmplitudeSawReturnsNegativeOneWithHalfPhaseIndexBuffer() {
        o.setWaveform(Waveform.SAW);
        SignalStatus s = new SignalStatus(samplesInPeriod / 2, frequency);
        o.generateSample(s);
        assertEquals(-1, s.getAmplitude(), 0.1);
    }
    
    @Test
    public void generateWaveAmplitudeSawReturnsZeroWithFullPhaseIndexBuffer() {
        o.setWaveform(Waveform.SAW);
        SignalStatus s = new SignalStatus(samplesInPeriod, frequency);
        o.generateSample(s);
        assertEquals(0, s.getAmplitude(), 0.1);
    }
    
    @Test
    public void generateWaveAmplitudeSawReturnsHalfWithQuarterPhaseIndexBuffer() {
        o.setWaveform(Waveform.SAW);
        SignalStatus s = new SignalStatus(samplesInPeriod / 4, frequency);
        o.generateSample(s);
        assertEquals(0.5, s.getAmplitude(), 0.1);
    }
    
    @Test
    public void generateWaveAmplitudeSawReturnsNegativeHalfWithThreeQuartersPhaseIndexBuffer() {
        o.setWaveform(Waveform.SAW);
        SignalStatus s = new SignalStatus(3 * (samplesInPeriod / 4), frequency);
        o.generateSample(s);
        assertEquals(-0.5, s.getAmplitude(), 0.1);
    }
    
    @Test
    public void generateWaveAmplitudeDoesNotModifySampleWithBypass() {
        o.setWaveform(Waveform.SQU);
        o.setBypass(true);
        SignalStatus s = new SignalStatus(0, frequency);
        o.generateSample(s);
        assertEquals(0, s.getAmplitude(), 0.1);
    }
    
    @Test
    public void generateWaveAmplitudeModifiesFrequencyWithFM() {
        o.setWaveform(Waveform.SQU);
        o.setFm(true);
        o.setFmDepth(1);
        SignalStatus s = new SignalStatus(0, frequency);
        o.generateSample(s);
        assertEquals(4, s.getFrequency(), 0.1);
    }
    
    @Test
    public void generateWaveAmplitudeDoesNotModifyFrequencyWithFMDepthZero() {
        o.setWaveform(Waveform.SQU);
        o.setFm(true);
        o.setFmDepth(0);
        SignalStatus s = new SignalStatus(0, frequency);
        o.generateSample(s);
        assertEquals(2, s.getFrequency(), 0.1);
    }
    // Signal paths of more than one oscillator into their own class
}
