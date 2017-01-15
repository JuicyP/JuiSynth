/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package juisynth.logic;

import juisynth.logic.player.Settings;
import juisynth.logic.signal.SignalStatus;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author juicyp
 */
public class OperatorTest {

    private Operator o;
    private Patch p;
    private int sampleRate = Settings.SAMPLE_RATE;
    private double frequency = Settings.SAMPLE_RATE / 4;

    public OperatorTest() {
    }

    @Before
    public void setUp() {
        p = new Patch();
        o = new Operator(p);
        p.setAdd(true);
    }

    @Test
    public void setAmpZeroSets() {
        p.setAmp(0);
        assertEquals(0, p.getAmp(), 0.1);
    }

    @Test
    public void setAmpOneSets() {
        p.setAmp(0);
        p.setAmp(1);
        assertEquals(1, p.getAmp(), 0.1);
    }

    @Test
    public void setAmpNegativeFails() {
        p.setAmp(-0.1);
        assertEquals(1, p.getAmp(), 0.1);
    }

    @Test
    public void setAmpLargerThanOneFails() {
        p.setAmp(1.1);
        assertEquals(1, p.getAmp(), 0.05);
    }

    @Test
    public void setFmDepthOneSets() {
        p.setFmDepth(1);
        assertEquals(1, p.getFmDepth(), 0.1);
    }

    @Test
    public void setFmDepthNegativeOneSets() {
        p.setFmDepth(-1);
        assertEquals(-1, p.getFmDepth(), 0.1);
    }

    @Test
    public void setFmDepthGreaterThanOneFails() {
        p.setFmDepth(1.1);
        assertEquals(0, p.getFmDepth(), 0.05);
    }

    @Test
    public void setFmDepthLessThanNegativeOneFails() {
        p.setFmDepth(-1.1);
        assertEquals(0, p.getFmDepth(), 0.05);
    }

    @Test
    public void setAmDepthOneSets() {
        p.setAmDepth(1);
        assertEquals(1, p.getAmDepth(), 0.1);
    }

    @Test
    public void setAmDepthNegativeOneSets() {
        p.setAmDepth(-1);
        assertEquals(-1, p.getAmDepth(), 0.1);
    }

    @Test
    public void setAmDepthGreaterThanOneFails() {
        p.setAmDepth(1.1);
        assertEquals(0, p.getAmDepth(), 0.05);
    }

    @Test
    public void setAmDepthLessThanNegativeOneFails() {
        p.setAmDepth(-1.1);
        assertEquals(0, p.getAmDepth(), 0.05);
    }

    @Test
    public void generateSampleDoesNotModifySampleWithBypass() {
        p.getOscillatorSettings().setWaveform(Waveform.SQU);
        p.setBypass(true);
        SignalStatus s = new SignalStatus(frequency);
        o.generateSample(s);
        assertEquals(0, s.getAmplitude(), 0.1);
    }

    @Test
    public void generateSampleModifiesFrequencyWithFM() {
        p.getOscillatorSettings().setWaveform(Waveform.SQU);
        p.setFm(true);
        p.setFmDepth(1);
        SignalStatus s = new SignalStatus(frequency);
        o.generateSample(s);
        assertEquals(frequency * 2, s.getFrequency(), 0.1);
    }

    @Test
    public void generateSampleDoesNotModifyFrequencyWithFMDepthZero() {
        p.getOscillatorSettings().setWaveform(Waveform.SQU);
        p.setFm(true);
        p.setFmDepth(0);
        SignalStatus s = new SignalStatus(frequency);
        o.generateSample(s);
        assertEquals(frequency, s.getFrequency(), 0.1);
    }

    @Test
    public void generateSampleSquareReturnsZeroWithZeroAmp() {
        p.setAmp(0);
        p.getOscillatorSettings().setWaveform(Waveform.SQU);
        SignalStatus s = new SignalStatus(frequency);
        o.generateSample(s);
        assertEquals(0, s.getAmplitude(), 0.1);
    }

    @Test
    public void generateSampleSquareReturnsHalfWithHalfAmp() {
        p.setAmp(0.5);
        p.getOscillatorSettings().setWaveform(Waveform.SQU);
        SignalStatus s = new SignalStatus(frequency);
        o.generateSample(s);
        assertEquals(0.5, s.getAmplitude(), 0.1);
    }

    @Test
    public void generateSampleDoesNotModifySampleWithAddDisabled() {
        p.setAdd(false);
        p.getOscillatorSettings().setWaveform(Waveform.SQU);
        SignalStatus s = new SignalStatus(frequency);
        o.generateSample(s);
        assertEquals(0, s.getAmplitude(), 0.1);

    }

    @Test
    public void generateSampleSquareReturnsNegativeOneWithQuarterPhaseInvert() {
        p.setInvert(true);
        p.getOscillatorSettings().setWaveform(Waveform.SQU);
        SignalStatus s = new SignalStatus(frequency);
        o.generateSample(s);
        assertEquals(-1, s.getAmplitude(), 0.1);
    }

    @Test
    public void generateSampleSquareReturnsOneWithHalfPhaseInvert() {
        p.setInvert(true);
        p.getOscillatorSettings().setWaveform(Waveform.SQU);
        SignalStatus s = new SignalStatus(frequency);
        o.generateSample(s);
        s.resetSignal();
        o.generateSample(s);
        assertEquals(1, s.getAmplitude(), 0.1);
    }

    
}
