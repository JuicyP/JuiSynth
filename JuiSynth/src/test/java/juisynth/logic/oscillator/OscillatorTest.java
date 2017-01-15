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
public class OscillatorTest {
    
    private OscillatorSettings os;
    private Oscillator o;
    private int sampleRate = Settings.SAMPLE_RATE;
    private double frequency = Settings.SAMPLE_RATE / 4;
    
    public OscillatorTest() {
    }
    
    @Before
    public void setUp() {
        os = new OscillatorSettings();
        o = new Oscillator(os);
    }
    
    
    @Test
    public void generateWaveAmplitudeTuningOneOctaveUpReturnsGenerateSampleTwiceTuningZero() {
        os.setTuning(1200);
        SignalStatus s = new SignalStatus(frequency);
        o.generateWaveAmplitude(s);
        double oAmplitude = s.getAmplitude();
        OscillatorSettings os2 = new OscillatorSettings();
        Oscillator o2 = new Oscillator(os2);
        s.resetSignal();
        o2.generateWaveAmplitude(s);
        o2.generateWaveAmplitude(s);
        assertEquals(s.getAmplitude(), oAmplitude, 0.1);
    }

    @Test
    public void generateWaveAmplitudeTwiceTuningOneOctaveDownReturnsGenerateSampleTuningZero() {
        os.setTuning(-1200);
        SignalStatus s = new SignalStatus(frequency);
        o.generateWaveAmplitude(s);
        s.resetSignal();
        o.generateWaveAmplitude(s);
        double oAmplitude = s.getAmplitude();
        OscillatorSettings os2 = new OscillatorSettings();
        Oscillator o2 = new Oscillator(os2);
        s.resetSignal();
        o2.generateWaveAmplitude(s);
        assertEquals(s.getAmplitude(), oAmplitude, 0.1);
    }
    
    @Test
    public void generateWaveAmplitudeFixedOperatesWithConcertPitchFrequency() {
        os.setFixed(true);
        SignalStatus s = new SignalStatus(frequency);
        o.generateWaveAmplitude(s);
        double fixedAmplitude = s.getAmplitude();
        o = new Oscillator(os);
        os.setFixed(false);
        s.setFrequency(440);
        o.generateWaveAmplitude(s);
        double concertPitchAmplitude = s.getAmplitude();
        assertEquals(concertPitchAmplitude, fixedAmplitude, 0.1);
    }
    
    @Test
    public void generateWaveAmplitudeHalfPhaseSquareReturnsOneWithSyncAndCompletePeriodTrue() {
        os.setSync(true);
        os.setWaveform(Waveform.SQU);
        SignalStatus s = new SignalStatus(frequency);
        s.setCompletePeriod(true);
        o.generateWaveAmplitude(s);
        s = new SignalStatus(frequency);
        s.setCompletePeriod(true);
        double amplitude = o.generateWaveAmplitude(s);
        assertEquals(1, amplitude, 0.1);
    }

    @Test
    public void generateWaveAmplitudeHalfPhaseSquareReturnsNegativeOneWithSyncAndCompletePeriodFalse() {
        os.setSync(true);
        os.setWaveform(Waveform.SQU);
        SignalStatus s = new SignalStatus(frequency);
        s.setCompletePeriod(false);
        o.generateWaveAmplitude(s);
        s = new SignalStatus(frequency);
        s.setCompletePeriod(false);
        double amplitude = o.generateWaveAmplitude(s);
        assertEquals(-1, amplitude, 0.1);
    }
    
    @Test
    public void generateWaveAmplitudeSyncTrueFullPhaseSetsCompletePeriodTrue() {
        os.setSync(true);
        SignalStatus s = new SignalStatus(frequency);
        for (int i = 0; i < 4; i++) {
            s = new SignalStatus(frequency);
            s.setCompletePeriod(false);
            o.generateWaveAmplitude(s);
        }
        assertEquals(true, s.getCompletePeriod());
    }
}
