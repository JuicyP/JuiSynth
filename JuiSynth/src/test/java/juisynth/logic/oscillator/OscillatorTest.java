/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package juisynth.logic.oscillator;

import juisynth.logic.player.Settings;
import juisynth.logic.signal.SignalStatus;
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
    private double frequency = 4;
    private int samplesInPeriod = (int) (sampleRate / frequency);
    
    public OscillatorTest() {
    }
    
    @Before
    public void setUp() {
        o = new Oscillator();
        o.setAdd(true);
    }
    
    @Test
    public void setAmpZeroSets() {
        o.setAmp(0);
        assertEquals(0, o.getAmp(), 0.1);
    }
    
    @Test
    public void setAmpOneSets() {
        o.setAmp(0);
        o.setAmp(1);
        assertEquals(1, o.getAmp(), 0.1);
    }
    
    @Test
    public void setAmpNegativeFails() {
        o.setAmp(-0.1);
        assertEquals(1, o.getAmp(), 0.1);
    }
    
    @Test
    public void setAmpLargerThanOneFails() {
        o.setAmp(1.1);
        assertEquals(1, o.getAmp(), 0.05);
    }
    
    @Test
    public void setFmDepthOneSets() {
        o.setFmDepth(1);
        assertEquals(1, o.getFmDepth(), 0.1);
    }
    
    @Test
    public void setFmDepthNegativeOneSets() {
        o.setFmDepth(-1);
        assertEquals(-1, o.getFmDepth(), 0.1);
    }
    
    @Test
    public void setFmDepthGreaterThanOneFails() {
        o.setFmDepth(1.1);
        assertEquals(0, o.getFmDepth(), 0.05);
    }
    
    @Test
    public void setFmDepthLessThanNegativeOneFails() {
        o.setFmDepth(-1.1);
        assertEquals(0, o.getFmDepth(), 0.05);
    }
    
    @Test
    public void setAmDepthOneSets() {
        o.setFmDepth(1);
        assertEquals(1, o.getFmDepth(), 0.1);
    }
    
    @Test
    public void setAmDepthNegativeOneSets() {
        o.setFmDepth(-1);
        assertEquals(-1, o.getFmDepth(), 0.1);
    }
    
    @Test
    public void setAmDepthGreaterThanOneFails() {
        o.setFmDepth(1.1);
        assertEquals(0, o.getFmDepth(), 0.05);
    }
    
    @Test
    public void setAmDepthLessThanNegativeOneFails() {
        o.setFmDepth(-1.1);
        assertEquals(0, o.getFmDepth(), 0.05);
    }
    
    @Test
    public void generateSampleSineReturnsZeroWithZeroIndexBuffer() {
        SignalStatus s = new SignalStatus(0, frequency);
        o.generateSample(s);
        assertEquals(0, s.getAmplitude(), 0.1);
    }
    
    @Test
    public void generateSampleSineReturnsZeroWithHalfPhaseIndexBuffer() {
        SignalStatus s = new SignalStatus(samplesInPeriod / 2, frequency);
        o.generateSample(s);
        assertEquals(0, s.getAmplitude(), 0.1);
    }
    
    @Test
    public void generateSampleSineReturnsZeroWithFullPhaseIndexBuffer() {
        SignalStatus s = new SignalStatus(samplesInPeriod, frequency);
        o.generateSample(s);
        assertEquals(0, s.getAmplitude(), 0.1);
    }
    
    @Test
    public void generateSampleSineReturnsOneWithQuarterPhaseIndexBuffer() {
        SignalStatus s = new SignalStatus(samplesInPeriod / 4, frequency);
        o.generateSample(s);
        assertEquals(1, s.getAmplitude(), 0.1);
    }
    
    @Test
    public void generateSampleSineReturnsNegativeOneWithThreeQuartersPhaseIndexBuffer() {
        SignalStatus s = new SignalStatus(3 * (samplesInPeriod / 4), frequency);
        o.generateSample(s);
        assertEquals(-1, s.getAmplitude(), 0.1);
    }
    
    @Test
    public void generateSampleTriangleReturnsNegativeOneWithZeroIndexBuffer() {
        o.setWaveform(Waveform.TRI);
        SignalStatus s = new SignalStatus(0, frequency);
        o.generateSample(s);
        assertEquals(-1, s.getAmplitude(), 0.1);
    }
    
    @Test
    public void generateSampleTriangleReturnsOneWithHalfPhaseIndexBuffer() {
        o.setWaveform(Waveform.TRI);
        SignalStatus s = new SignalStatus(samplesInPeriod / 2, frequency);
        o.generateSample(s);
        assertEquals(1, s.getAmplitude(), 0.1);
    }
    
    @Test
    public void generateSampleTriangleReturnsNegativeOneWithFullPhaseIndexBuffer() {
        o.setWaveform(Waveform.TRI);
        SignalStatus s = new SignalStatus(samplesInPeriod, frequency);
        o.generateSample(s);
        assertEquals(-1, s.getAmplitude(), 0.1);
    }
    
    @Test
    public void generateSampleTriangleReturnsZeroWithQuarterPhaseIndexBuffer() {
        o.setWaveform(Waveform.TRI);
        SignalStatus s = new SignalStatus(samplesInPeriod / 4, frequency);
        o.generateSample(s);
        assertEquals(0, s.getAmplitude(), 0.1);
    }
    
    @Test
    public void generateSampleTriangleReturnsZeroWithThreeQuartersPhaseIndexBuffer() {
        o.setWaveform(Waveform.TRI);
        SignalStatus s = new SignalStatus(3 * (samplesInPeriod / 4), frequency);
        o.generateSample(s);
        assertEquals(0, s.getAmplitude(), 0.1);
    }
    
    @Test
    public void generateSampleSquareReturnsOneWithZeroIndexBuffer() {
        o.setWaveform(Waveform.SQU);
        SignalStatus s = new SignalStatus(0, frequency);
        o.generateSample(s);
        assertEquals(1, s.getAmplitude(), 0.1);
    }
    
    @Test
    public void generateSampleSquareReturnsNegativeOneWithHalfPhaseIndexBuffer() {
        o.setWaveform(Waveform.SQU);
        SignalStatus s = new SignalStatus(samplesInPeriod / 2, frequency);
        o.generateSample(s);
        assertEquals(-1, s.getAmplitude(), 0.1);
    }
    
    @Test
    public void generateSampleSquareReturnsOneWithFullPhaseIndexBuffer() {
        o.setWaveform(Waveform.SQU);
        SignalStatus s = new SignalStatus(samplesInPeriod, frequency);
        o.generateSample(s);
        assertEquals(1, s.getAmplitude(), 0.1);
    }
    
    @Test
    public void generateSampleSquareReturnsOneWithQuarterPhaseIndexBuffer() {
        o.setWaveform(Waveform.SQU);
        SignalStatus s = new SignalStatus(samplesInPeriod / 4, frequency);
        o.generateSample(s);
        assertEquals(1, s.getAmplitude(), 0.1);
    }
    
    @Test
    public void generateSampleSquareReturnsNegativeOneWithThreeQuartersPhaseIndexBuffer() {
        o.setWaveform(Waveform.SQU);
        SignalStatus s = new SignalStatus(3 * (samplesInPeriod / 4), frequency);
        o.generateSample(s);
        assertEquals(-1, s.getAmplitude(), 0.1);
    }
    
    @Test
    public void generateSampleSawReturnsZeroWithZeroIndexBuffer() {
        o.setWaveform(Waveform.SAW);
        SignalStatus s = new SignalStatus(0, frequency);
        o.generateSample(s);
        assertEquals(0, s.getAmplitude(), 0.1);
    }
    
    @Test
    public void generateSampleSawReturnsNegativeOneWithHalfPhaseIndexBuffer() {
        o.setWaveform(Waveform.SAW);
        SignalStatus s = new SignalStatus(samplesInPeriod / 2, frequency);
        o.generateSample(s);
        assertEquals(-1, s.getAmplitude(), 0.1);
    }
    
    @Test
    public void generateSampleSawReturnsZeroWithFullPhaseIndexBuffer() {
        o.setWaveform(Waveform.SAW);
        SignalStatus s = new SignalStatus(samplesInPeriod, frequency);
        o.generateSample(s);
        assertEquals(0, s.getAmplitude(), 0.1);
    }
    
    @Test
    public void generateSampleSawReturnsHalfWithQuarterPhaseIndexBuffer() {
        o.setWaveform(Waveform.SAW);
        SignalStatus s = new SignalStatus(samplesInPeriod / 4, frequency);
        o.generateSample(s);
        assertEquals(0.5, s.getAmplitude(), 0.1);
    }
    
    @Test
    public void generateSampleSawReturnsNegativeHalfWithThreeQuartersPhaseIndexBuffer() {
        o.setWaveform(Waveform.SAW);
        SignalStatus s = new SignalStatus(3 * (samplesInPeriod / 4), frequency);
        o.generateSample(s);
        assertEquals(-0.5, s.getAmplitude(), 0.1);
    }
    
    @Test
    public void generateSampleDoesNotModifySampleWithBypass() {
        o.setWaveform(Waveform.SQU);
        o.setBypass(true);
        SignalStatus s = new SignalStatus(0, frequency);
        o.generateSample(s);
        assertEquals(0, s.getAmplitude(), 0.1);
    }
    
    @Test
    public void generateSampleModifiesFrequencyWithFM() {
        o.setWaveform(Waveform.SQU);
        o.setFm(true);
        o.setFmDepth(1);
        SignalStatus s = new SignalStatus(0, frequency);
        o.generateSample(s);
        assertEquals(frequency * 2, s.getFrequency(), 0.1);
    }
    
    @Test
    public void generateSampleDoesNotModifyFrequencyWithFMDepthZero() {
        o.setWaveform(Waveform.SQU);
        o.setFm(true);
        o.setFmDepth(0);
        SignalStatus s = new SignalStatus(0, frequency);
        o.generateSample(s);
        assertEquals(frequency, s.getFrequency(), 0.1);
    }
    
    @Test
    public void generateSampleSquareReturnsOneWithHalfPhaseIndexBufferAndTuningOneOctaveUp() {
        o.setTuning(1200);
        o.setWaveform(Waveform.SQU);
        SignalStatus s = new SignalStatus(samplesInPeriod / 2, frequency);
        o.generateSample(s);
        assertEquals(1, s.getAmplitude(), 0.1);
    }
    
    @Test
    public void generateSampleSquareReturnsOneWithFullPhaseIndexBufferAndTuningOneOctaveDown() {
        o.setTuning(-1200);
        o.setWaveform(Waveform.SQU);
        SignalStatus s = new SignalStatus(samplesInPeriod, frequency);
        o.generateSample(s);
        assertEquals(-1, s.getAmplitude(), 0.1);
    }
    
    @Test
    public void generateSampleSquareReturnsZeroWithZeroAmp() {
        o.setAmp(0);
        o.setWaveform(Waveform.SQU);
        SignalStatus s = new SignalStatus(0, frequency);
        o.generateSample(s);
        assertEquals(0, s.getAmplitude(), 0.1);
    }
    
    @Test
    public void generateSampleSquareReturnsHalfWithHalfAmp() {
        o.setAmp(0.5);
        o.setWaveform(Waveform.SQU);
        SignalStatus s = new SignalStatus(0, frequency);
        o.generateSample(s);
        assertEquals(0.5, s.getAmplitude(), 0.1);
    }
    
    @Test
    public void generateSampleFixedOperatesWithConcertPitchFrequency() {
        o.setFixed(true);
        SignalStatus s = new SignalStatus(0, frequency);
        o.generateSample(s);
        double fixedAmplitude = s.getAmplitude();
        o.setFixed(false);
        s.setFrequency(440);
        o.generateSample(s);
        double concertPitchAmplitude = s.getAmplitude();
        assertEquals(concertPitchAmplitude, fixedAmplitude, 0.1);
    }
    
    @Test
    public void generateSampleDoesNotModifySampleWithAddDisabled() {
        o.setAdd(false);
        o.setWaveform(Waveform.SQU);
        SignalStatus s = new SignalStatus(samplesInPeriod, frequency);
        o.generateSample(s);
        assertEquals(0, s.getAmplitude(), 0.1);
        
    }
    
    @Test
    public void generateSampleSquareReturnsNegativeOneWithZeroIndexBufferInvert() {
        o.setInvert(true);
        o.setWaveform(Waveform.SQU);
        SignalStatus s = new SignalStatus(0, frequency);
        o.generateSample(s);
        assertEquals(-1, s.getAmplitude(), 0.1);
    }
    
    @Test
    public void generateSampleSquareReturnsOneWithHalfPhaseIndexBufferInvert() {
        o.setInvert(true);
        o.setWaveform(Waveform.SQU);
        SignalStatus s = new SignalStatus(samplesInPeriod / 2, frequency);
        o.generateSample(s);
        assertEquals(1, s.getAmplitude(), 0.1);
    }
    // Signal paths of more than one oscillator into their own class
}
