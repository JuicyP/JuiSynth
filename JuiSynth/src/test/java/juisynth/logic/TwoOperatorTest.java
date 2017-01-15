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
public class TwoOperatorTest {

    private Operator o1;
    private Operator o2;
    private Patch p1;
    private Patch p2;
    private int sampleRate = Settings.SAMPLE_RATE;
    private double frequency = 4;
    private int samplesInPeriod = (int) (sampleRate / frequency);

    public TwoOperatorTest() {
    }

    @Before
    public void setUp() {
        p1 = new Patch();
        p1.setAdd(true);
        o1 = new Operator(p1);
        p2 = new Patch();
        o2 = new Operator(p2);
        o2.setSignalSource(o1);
    }

    @Test
    public void generateSampleBypassOnSecondOperatorFetchesFirstOperatorSample() {
        p1.getOscillatorSettings().setWaveform(Waveform.SQU);
        p2.setAdd(true);
        p2.setFm(true);
        p2.setAm(true);
        p2.setFmDepth(1);
        p2.setAmDepth(1);
        p2.setBypass(true);
        SignalStatus s = new SignalStatus(frequency);
        o1.generateSample(s);
        double firstOperatorAmplitude = s.getAmplitude();
        s = new SignalStatus(frequency);
        o2.generateSample(s);
        double secondOperatorAmplitude = s.getAmplitude();

        assertEquals(firstOperatorAmplitude, secondOperatorAmplitude, 0.05);
    }

    @Test
    public void generateSampleBypassOnFirstOperatorFetchesSecondOperatorSample() {
        p2.getOscillatorSettings().setWaveform(Waveform.SQU);
        p2.setAdd(true);
        p1.setFm(true);
        p1.setAm(true);
        p1.setFmDepth(1);
        p1.setAmDepth(1);
        p1.setBypass(true);
        SignalStatus s = new SignalStatus(frequency);
        o2.generateSample(s);

        assertEquals(1, s.getAmplitude(), 0.05);
    }

    @Test
    public void generateSampleTwoOperatorAddSetsSignalStatusActiveOperatorCountToTwo() {
        p2.setAdd(true);
        SignalStatus s = new SignalStatus(frequency);
        o2.generateSample(s);

        assertEquals(2, s.getActiveOperatorCount(), 0.05);
    }

    @Test
    public void generateSampleReturnsHalfWithTwoActiveOneSilentOperators() {
        p1.getOscillatorSettings().setWaveform(Waveform.SQU);
        p2.setAdd(true);
        p2.setAmp(0);
        SignalStatus s = new SignalStatus(frequency);
        o2.generateSample(s);

        assertEquals(0.5, s.getAmplitude(), 0.05);
    }

    @Test
    public void generateSampleReturnsZeroWithTwoSameOperatorsAddedOtherInverted() {
        p2.setAdd(true);
        p2.setInvert(true);
        p1.getOscillatorSettings().setWaveform(Waveform.SQU);
        p2.getOscillatorSettings().setWaveform(Waveform.SQU);

        SignalStatus s = new SignalStatus(frequency);
        o2.generateSample(s);

        assertEquals(0, s.getAmplitude(), 0.05);
    }
    
    @Test
    public void generateSampleReturnsHalvedAmplitudeWithAMQuarterPhase() {
        p2.setAm(true);
        p2.setAmDepth(1);
        p1.getOscillatorSettings().setWaveform(Waveform.SQU);
        
        SignalStatus s = new SignalStatus(frequency);
        o2.generateSample(s);
        assertEquals(0.5, s.getAmplitude(), 0.05);
    }

}
