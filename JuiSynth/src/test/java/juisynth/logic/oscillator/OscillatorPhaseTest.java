/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package juisynth.logic.oscillator;

import juisynth.logic.Waveform;
import juisynth.logic.player.Settings;
import juisynth.logic.signal.SignalStatus;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author juicyp
 */
public class OscillatorPhaseTest {

    private Oscillator o;
    private int sampleRate = Settings.SAMPLE_RATE;
    private double frequency = Settings.SAMPLE_RATE / 4;

    public OscillatorPhaseTest() {

    }

    @Before
    public void setUp() {
        o = new Oscillator();
        o.setAdd(true);
    }

    @Test
    public void generateSampleSineReturnsOneWithQuarterPhase() {
        SignalStatus s = new SignalStatus(frequency);
        for (int i = 0; i < 1; i++) {
            s.resetSignal();
            o.generateSample(s);
        }
        assertEquals(1, s.getAmplitude(), 0.1);
    }

    @Test
    public void generateSampleSineReturnsZeroWithHalfPhase() {
        SignalStatus s = new SignalStatus(frequency);
        for (int i = 0; i < 2; i++) {
            s.resetSignal();
            o.generateSample(s);
        }
        assertEquals(0, s.getAmplitude(), 0.1);
    }

    @Test
    public void generateSampleSineReturnsNegativeOneWithThreeQuartersPhase() {
        SignalStatus s = new SignalStatus(frequency);
        for (int i = 0; i < 3; i++) {
            s.resetSignal();
            o.generateSample(s);
        }
        assertEquals(-1, s.getAmplitude(), 0.1);
    }

    @Test
    public void generateSampleSineReturnsZeroWithFullPhase() {
        SignalStatus s = new SignalStatus(frequency);
        for (int i = 0; i < 4; i++) {
            s.resetSignal();
            o.generateSample(s);
        }
        assertEquals(0, s.getAmplitude(), 0.1);
    }

    @Test
    public void generateSampleSquareReturnsOneWithQuarterPhase() {
        o.setWaveform(Waveform.SQU);
        SignalStatus s = new SignalStatus(frequency);
        for (int i = 0; i < 1; i++) {
            s.resetSignal();
            o.generateSample(s);
        }
        assertEquals(1, s.getAmplitude(), 0.1);
    }

    @Test
    public void generateSampleSquareReturnsNegativeOneWithHalfPhase() {
        o.setWaveform(Waveform.SQU);
        SignalStatus s = new SignalStatus(frequency);
        for (int i = 0; i < 2; i++) {
            s.resetSignal();
            o.generateSample(s);
        }
        assertEquals(-1, s.getAmplitude(), 0.1);
    }

    @Test
    public void generateSampleSquareReturnsNegativeOneWithThreeQuartersPhase() {
        o.setWaveform(Waveform.SQU);
        SignalStatus s = new SignalStatus(frequency);
        for (int i = 0; i < 3; i++) {
            s.resetSignal();
            o.generateSample(s);
        }
        assertEquals(-1, s.getAmplitude(), 0.1);
    }

    @Test
    public void generateSampleSquareReturnsOneWithFullPhase() {
        o.setWaveform(Waveform.SQU);
        SignalStatus s = new SignalStatus(frequency);
        for (int i = 0; i < 4; i++) {
            s.resetSignal();
            o.generateSample(s);
        }
        assertEquals(1, s.getAmplitude(), 0.1);
    }

    @Test
    public void generateSampleSawReturnsHalfWithQuarterPhase() {
        o.setWaveform(Waveform.SAW);
        SignalStatus s = new SignalStatus(frequency);
        for (int i = 0; i < 1; i++) {
            s.resetSignal();
            o.generateSample(s);
        }
        assertEquals(0.5, s.getAmplitude(), 0.1);
    }

    @Test
    public void generateSampleSawReturnsNegativeOneWithHalfPhase() {
        o.setWaveform(Waveform.SAW);
        SignalStatus s = new SignalStatus(frequency);
        for (int i = 0; i < 2; i++) {
            s.resetSignal();
            o.generateSample(s);
        }
        assertEquals(-1, s.getAmplitude(), 0.1);
    }

    @Test
    public void generateSampleSawReturnsNegativeHalfWithThreeQuartersPhase() {
        o.setWaveform(Waveform.SAW);
        SignalStatus s = new SignalStatus(frequency);
        for (int i = 0; i < 3; i++) {
            s.resetSignal();
            o.generateSample(s);
        }
        assertEquals(-0.5, s.getAmplitude(), 0.1);
    }

    @Test
    public void generateSampleSawReturnsZeroWithFullPhase() {
        o.setWaveform(Waveform.SAW);
        SignalStatus s = new SignalStatus(frequency);
        for (int i = 0; i < 4; i++) {
            s.resetSignal();
            o.generateSample(s);
        }
        assertEquals(0, s.getAmplitude(), 0.1);
    }

    @Test
    public void generateSampleTriangleReturnsOneWithQuarterPhase() {
        o.setWaveform(Waveform.TRI);
        SignalStatus s = new SignalStatus(frequency);
        for (int i = 0; i < 1; i++) {
            s.resetSignal();
            o.generateSample(s);
        }
        assertEquals(1, s.getAmplitude(), 0.1);
    }

    @Test
    public void generateSampleTriangleReturnsZeroWithHalfPhase() {
        o.setWaveform(Waveform.TRI);
        SignalStatus s = new SignalStatus(frequency);
        for (int i = 0; i < 2; i++) {
            s.resetSignal();
            o.generateSample(s);
        }
        assertEquals(0, s.getAmplitude(), 0.1);
    }

    @Test
    public void generateSampleTriangleReturnsNegativeOneWithThreeQuartersPhase() {
        o.setWaveform(Waveform.TRI);
        SignalStatus s = new SignalStatus(frequency);
        for (int i = 0; i < 3; i++) {
            s.resetSignal();
            o.generateSample(s);
        }
        assertEquals(-1, s.getAmplitude(), 0.1);
    }

    @Test
    public void generateSampleTriangleReturnsZeroWithFullPhase() {
        o.setWaveform(Waveform.TRI);
        SignalStatus s = new SignalStatus(frequency);
        for (int i = 0; i < 4; i++) {
            s.resetSignal();
            o.generateSample(s);
        }
        assertEquals(0, s.getAmplitude(), 0.1);
    }
}
