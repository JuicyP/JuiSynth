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
    private double frequency = Settings.SAMPLE_RATE / 4;

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
    public void generateSampleDoesNotModifySampleWithBypass() {
        o.setWaveform(Waveform.SQU);
        o.setBypass(true);
        SignalStatus s = new SignalStatus(frequency);
        o.generateSample(s);
        assertEquals(0, s.getAmplitude(), 0.1);
    }

    @Test
    public void generateSampleModifiesFrequencyWithFM() {
        o.setWaveform(Waveform.SQU);
        o.setFm(true);
        o.setFmDepth(1);
        SignalStatus s = new SignalStatus(frequency);
        o.generateSample(s);
        assertEquals(frequency * 2, s.getFrequency(), 0.1);
    }

    @Test
    public void generateSampleDoesNotModifyFrequencyWithFMDepthZero() {
        o.setWaveform(Waveform.SQU);
        o.setFm(true);
        o.setFmDepth(0);
        SignalStatus s = new SignalStatus(frequency);
        o.generateSample(s);
        assertEquals(frequency, s.getFrequency(), 0.1);
    }

    @Test
    public void generateSampleTuningOneOctaveUpReturnsGenerateSampleTwiceTuningZero() {
        o.setTuning(1200);
        SignalStatus s = new SignalStatus(frequency);
        o.generateSample(s);
        double oAmplitude = s.getAmplitude();
        Oscillator o2 = new Oscillator();
        o2.setAdd(true);
        s.resetSignal();
        o2.generateSample(s);
        o2.generateSample(s);
        assertEquals(s.getAmplitude(), oAmplitude, 0.1);
    }

    @Test
    public void generateSampleTwiceTuningOneOctaveDownReturnsGenerateSampleTuningZero() {
        o.setTuning(-1200);
        SignalStatus s = new SignalStatus(frequency);
        o.generateSample(s);
        s.resetSignal();
        o.generateSample(s);
        double oAmplitude = s.getAmplitude();
        Oscillator o2 = new Oscillator();
        o2.setAdd(true);
        s.resetSignal();
        o2.generateSample(s);
        assertEquals(s.getAmplitude(), oAmplitude, 0.1);
    }

    @Test
    public void generateSampleSquareReturnsZeroWithZeroAmp() {
        o.setAmp(0);
        o.setWaveform(Waveform.SQU);
        SignalStatus s = new SignalStatus(frequency);
        o.generateSample(s);
        assertEquals(0, s.getAmplitude(), 0.1);
    }

    @Test
    public void generateSampleSquareReturnsHalfWithHalfAmp() {
        o.setAmp(0.5);
        o.setWaveform(Waveform.SQU);
        SignalStatus s = new SignalStatus(frequency);
        o.generateSample(s);
        assertEquals(0.5, s.getAmplitude(), 0.1);
    }

    @Test
    public void generateSampleFixedOperatesWithConcertPitchFrequency() {
        o.setFixed(true);
        SignalStatus s = new SignalStatus(frequency);
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
        SignalStatus s = new SignalStatus(frequency);
        o.generateSample(s);
        assertEquals(0, s.getAmplitude(), 0.1);

    }

    @Test
    public void generateSampleSquareReturnsNegativeOneWithQuarterPhaseInvert() {
        o.setInvert(true);
        o.setWaveform(Waveform.SQU);
        SignalStatus s = new SignalStatus(frequency);
        o.generateSample(s);
        assertEquals(-1, s.getAmplitude(), 0.1);
    }

    @Test
    public void generateSampleSquareReturnsOneWithHalfPhaseInvert() {
        o.setInvert(true);
        o.setWaveform(Waveform.SQU);
        SignalStatus s = new SignalStatus(frequency);
        o.generateSample(s);
        s.resetSignal();
        o.generateSample(s);
        assertEquals(1, s.getAmplitude(), 0.1);
    }
}
