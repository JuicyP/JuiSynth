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
public class TwoOscillatorTest {
    
    private Oscillator o1;
    private Oscillator o2;
    private int sampleRate = Settings.SAMPLE_RATE;
    private double frequency = 4;
    private int samplesInPeriod = (int) (sampleRate / frequency);
    
    public TwoOscillatorTest() {
    }
    
    @Before
    public void setUp() {
        o1 = new Oscillator();
        o1.setAdd(true);
        o2 = new Oscillator();
        o2.setSignalSource(o1);
    }
    
    @Test
    public void generateSampleBypassOnSecondOperatorFetchesFirstOperatorSample() {
        o1.setWaveform(Waveform.SQU);
        o2.setAdd(true);
        o2.setFm(true);
        o2.setAm(true);
        o2.setFmDepth(1);
        o2.setAmDepth(1);
        o2.setBypass(true);
        SignalStatus s = new SignalStatus( frequency);
        o1.generateSample(s);
        double firstOperatorAmplitude = s.getAmplitude();
        s = new SignalStatus( frequency);
        o2.generateSample(s);
        double secondOperatorAmplitude = s.getAmplitude();
        
        assertEquals(firstOperatorAmplitude, secondOperatorAmplitude, 0.1);
    }
    
    @Test
    public void generateSampleBypassOnFirstOperatorFetchesSecondOperatorSample() {
        o2.setWaveform(Waveform.SQU);
        o2.setAdd(true);
        o1.setFm(true);
        o1.setAm(true);
        o1.setFmDepth(1);
        o1.setAmDepth(1);
        o1.setBypass(true);
        SignalStatus s = new SignalStatus( frequency);
        o2.generateSample(s);
        
        assertEquals(1, s.getAmplitude(), 0.1);
    }
    
    @Test
    public void generateSampleTwoOperatorAddSetsSignalStatusActiveOperatorCountToTwo() {
        o2.setAdd(true);
        SignalStatus s = new SignalStatus( frequency);
        o2.generateSample(s);
       
        assertEquals(2, s.getActiveOperatorCount(), 0.1);
    }
    
    @Test
    public void generateSampleReturnsHalfWithTwoActiveOneSilentOperators() {
        o1.setWaveform(Waveform.SQU);
        o2.setAdd(true);
        o2.setAmp(0);
        SignalStatus s = new SignalStatus( frequency);
        o2.generateSample(s);
       
        assertEquals(0.5, s.getAmplitude(), 0.1);
    }
    
    @Test
    public void generateSampleReturnsZeroWithTwoSameOperatorsAddedOtherInverted() {
        o2.setAdd(true);
        o2.setInvert(true);
        o1.setWaveform(Waveform.SQU);
        o2.setWaveform(Waveform.SQU);
        
        SignalStatus s = new SignalStatus( frequency);
        o2.generateSample(s);
       
        assertEquals(0, s.getAmplitude(), 0.1);
    }
    
    
}
