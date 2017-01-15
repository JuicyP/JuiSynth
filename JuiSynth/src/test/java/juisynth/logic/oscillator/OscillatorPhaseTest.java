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

    private OscillatorSettings os;
    private Oscillator o;
    private int sampleRate = Settings.SAMPLE_RATE;
    private double frequency = Settings.SAMPLE_RATE / 4;

    public OscillatorPhaseTest() {
    }

    @Before
    public void setUp() {
        this.os = new OscillatorSettings();
        this.o = new Oscillator(os);
    }

    @Test
    public void generateWaveAmplitudeSineReturnsOneWithQuarterPhase() {
        SignalStatus s = new SignalStatus(frequency);
        for (int i = 0; i < 1; i++) {
            s.resetSignal();
            s.setAmplitude(o.generateWaveAmplitude(s));
        }
        assertEquals(1, s.getAmplitude(), 0.05);
        assertEquals(0.25, o.getPhase(), 0.05);
    }

    @Test
    public void generateWaveAmplitudeSineReturnsZeroWithHalfPhase() {
        SignalStatus s = new SignalStatus(frequency);
        for (int i = 0; i < 2; i++) {
            s.resetSignal();
            s.setAmplitude(o.generateWaveAmplitude(s));
        }
        assertEquals(0, s.getAmplitude(), 0.05);
        assertEquals(0.5, o.getPhase(), 0.05);
    }

    @Test
    public void generateWaveAmplitudeSineReturnsNegativeOneWithThreeQuartersPhase() {
        SignalStatus s = new SignalStatus(frequency);
        for (int i = 0; i < 3; i++) {
            s.resetSignal();
            s.setAmplitude(o.generateWaveAmplitude(s));
        }
        assertEquals(-1, s.getAmplitude(), 0.05);
        assertEquals(0.75, o.getPhase(), 0.05);
    }

    @Test
    public void generateWaveAmplitudeSineReturnsZeroWithFullPhase() {
        SignalStatus s = new SignalStatus(frequency);
        for (int i = 0; i < 4; i++) {
            s.resetSignal();
            s.setAmplitude(o.generateWaveAmplitude(s));
        }
        assertEquals(0, s.getAmplitude(), 0.05);
        assertEquals(0, o.getPhase(), 0.05);
    }

    @Test
    public void generateWaveAmplitudeSquareReturnsOneWithQuarterPhase() {
        os.setWaveform(Waveform.SQU);
        SignalStatus s = new SignalStatus(frequency);
        for (int i = 0; i < 1; i++) {
            s.resetSignal();
            s.setAmplitude(o.generateWaveAmplitude(s));
        }
        assertEquals(1, s.getAmplitude(), 0.05);
        assertEquals(0.25, o.getPhase(), 0.05);
    }

    @Test
    public void generateWaveAmplitudeSquareReturnsNegativeOneWithHalfPhase() {
        os.setWaveform(Waveform.SQU);
        SignalStatus s = new SignalStatus(frequency);
        for (int i = 0; i < 2; i++) {
            s.resetSignal();
            s.setAmplitude(o.generateWaveAmplitude(s));
        }
        assertEquals(-1, s.getAmplitude(), 0.05);
        assertEquals(0.5, o.getPhase(), 0.05);
    }

    @Test
    public void generateWaveAmplitudeSquareReturnsNegativeOneWithThreeQuartersPhase() {
        os.setWaveform(Waveform.SQU);
        SignalStatus s = new SignalStatus(frequency);
        for (int i = 0; i < 3; i++) {
            s.resetSignal();
            s.setAmplitude(o.generateWaveAmplitude(s));
        }
        assertEquals(-1, s.getAmplitude(), 0.05);
        assertEquals(0.75, o.getPhase(), 0.05);
    }

    @Test
    public void generateWaveAmplitudeSquareReturnsOneWithFullPhase() {
        os.setWaveform(Waveform.SQU);
        SignalStatus s = new SignalStatus(frequency);
        for (int i = 0; i < 4; i++) {
            s.resetSignal();
            s.setAmplitude(o.generateWaveAmplitude(s));
        }
        assertEquals(1, s.getAmplitude(), 0.05);
        assertEquals(0, o.getPhase(), 0.05);
    }

    @Test
    public void generateWaveAmplitudeSawReturnsHalfWithQuarterPhase() {
        os.setWaveform(Waveform.SAW);
        SignalStatus s = new SignalStatus(frequency);
        for (int i = 0; i < 1; i++) {
            s.resetSignal();
            s.setAmplitude(o.generateWaveAmplitude(s));
        }
        assertEquals(0.5, s.getAmplitude(), 0.05);
        assertEquals(0.25, o.getPhase(), 0.05);
    }

    @Test
    public void generateWaveAmplitudeSawReturnsNegativeOneWithHalfPhase() {
        os.setWaveform(Waveform.SAW);
        SignalStatus s = new SignalStatus(frequency);
        for (int i = 0; i < 2; i++) {
            s.resetSignal();
            s.setAmplitude(o.generateWaveAmplitude(s));
        }
        assertEquals(-1, s.getAmplitude(), 0.05);
        assertEquals(0.5, o.getPhase(), 0.05);
    }

    @Test
    public void generateWaveAmplitudeSawReturnsNegativeHalfWithThreeQuartersPhase() {
        os.setWaveform(Waveform.SAW);
        SignalStatus s = new SignalStatus(frequency);
        for (int i = 0; i < 3; i++) {
            s.resetSignal();
            s.setAmplitude(o.generateWaveAmplitude(s));
        }
        assertEquals(-0.5, s.getAmplitude(), 0.05);
        assertEquals(0.75, o.getPhase(), 0.05);
    }

    @Test
    public void generateWaveAmplitudeSawReturnsZeroWithFullPhase() {
        os.setWaveform(Waveform.SAW);
        SignalStatus s = new SignalStatus(frequency);
        for (int i = 0; i < 4; i++) {
            s.resetSignal();
            s.setAmplitude(o.generateWaveAmplitude(s));
        }
        assertEquals(0, s.getAmplitude(), 0.05);
        assertEquals(0, o.getPhase(), 0.05);
    }

    @Test
    public void generateWaveAmplitudeTriangleReturnsOneWithQuarterPhase() {
        os.setWaveform(Waveform.TRI);
        SignalStatus s = new SignalStatus(frequency);
        for (int i = 0; i < 1; i++) {
            s.resetSignal();
            s.setAmplitude(o.generateWaveAmplitude(s));
        }
        assertEquals(1, s.getAmplitude(), 0.05);
        assertEquals(0.25, o.getPhase(), 0.05);
    }

    @Test
    public void generateWaveAmplitudeTriangleReturnsZeroWithHalfPhase() {
        os.setWaveform(Waveform.TRI);
        SignalStatus s = new SignalStatus(frequency);
        for (int i = 0; i < 2; i++) {
            s.resetSignal();
            s.setAmplitude(o.generateWaveAmplitude(s));
        }
        assertEquals(0, s.getAmplitude(), 0.05);
        assertEquals(0.5, o.getPhase(), 0.05);
    }

    @Test
    public void generateWaveAmplitudeTriangleReturnsNegativeOneWithThreeQuartersPhase() {
        os.setWaveform(Waveform.TRI);
        SignalStatus s = new SignalStatus(frequency);
        for (int i = 0; i < 3; i++) {
            s.resetSignal();
            s.setAmplitude(o.generateWaveAmplitude(s));
        }
        assertEquals(-1, s.getAmplitude(), 0.05);
        assertEquals(0.75, o.getPhase(), 0.05);
    }

    @Test
    public void generateWaveAmplitudeTriangleReturnsZeroWithFullPhase() {
        os.setWaveform(Waveform.TRI);
        SignalStatus s = new SignalStatus(frequency);
        for (int i = 0; i < 4; i++) {
            s.resetSignal();
            s.setAmplitude(o.generateWaveAmplitude(s));
        }
        assertEquals(0, s.getAmplitude(), 0.05);
        assertEquals(0, o.getPhase(), 0.05);
    }
}
