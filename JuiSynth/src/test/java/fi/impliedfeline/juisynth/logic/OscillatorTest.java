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
    private int sampleRate = 8;
    private double frequency = 1;

    public OscillatorTest() {
    }

    @Before
    public void setUp() {
        o = new Oscillator();
    }

    @Test
    public void generateWaveAmplitudeSineReturnsZeroWithZeroIndexBuffer() {
        SignalStatus s = new SignalStatus(sampleRate, 0, frequency);
        o.generateSample(s);
        assertEquals(s.getAmplitude(), 0, 0.1);
    }

    @Test
    public void generateWaveAmplitudeSineReturnsZeroWithHalfSampleRateIndexBuffer() {
        SignalStatus s = new SignalStatus(sampleRate, sampleRate / 2, frequency);
        o.generateSample(s);
        assertEquals(s.getAmplitude(), 0, 0.1);
    }

    @Test
    public void generateWaveAmplitudeSineReturnsZeroWithSampleRateIndexBuffer() {
        SignalStatus s = new SignalStatus(sampleRate, sampleRate, frequency);
        o.generateSample(s);
        assertEquals(s.getAmplitude(), 0, 0.1);
    }

    @Test
    public void generateWaveAmplitudeSineReturnsOneWithQuarterSampleRateIndexBuffer() {
        SignalStatus s = new SignalStatus(sampleRate, sampleRate / 4, frequency);
        o.generateSample(s);
        assertEquals(s.getAmplitude(), 1, 0.1);
    }

    @Test
    public void generateWaveAmplitudeSineReturnsNegativeOneWithThreeQuartersSampleRateIndexBuffer() {
        SignalStatus s = new SignalStatus(sampleRate, 3 * (sampleRate / 4), frequency);
        o.generateSample(s);
        assertEquals(s.getAmplitude(), -1, 0.1);
    }

    @Test
    public void generateWaveAmplitudeTriangleReturnsOneWithZeroIndexBuffer() {
        o.setWaveform(Oscillator.Waveform.TRI);
        SignalStatus s = new SignalStatus(sampleRate, 0, frequency);
        o.generateSample(s);
        assertEquals(s.getAmplitude(), -1, 0.1);
    }

    @Test
    public void generateWaveAmplitudeTriangleReturnsOneWithHalfSampleRateIndexBuffer() {
        o.setWaveform(Oscillator.Waveform.TRI);
        SignalStatus s = new SignalStatus(sampleRate, sampleRate / 2, frequency);
        o.generateSample(s);
        assertEquals(s.getAmplitude(), 1, 0.1);
    }

    @Test
    public void generateWaveAmplitudeTriangleReturnsNegativeOneWithSampleRateIndexBuffer() {
        o.setWaveform(Oscillator.Waveform.TRI);
        SignalStatus s = new SignalStatus(sampleRate, sampleRate, frequency);
        o.generateSample(s);
        assertEquals(s.getAmplitude(), -1, 0.1);
    }

    @Test
    public void generateWaveAmplitudeTriangleReturnsZeroWithQuarterSampleRateIndexBuffer() {
        o.setWaveform(Oscillator.Waveform.TRI);
        SignalStatus s = new SignalStatus(sampleRate, sampleRate / 4, frequency);
        o.generateSample(s);
        assertEquals(s.getAmplitude(), 0, 0.1);
    }

    @Test
    public void generateWaveAmplitudeTriangleReturnsZeroWithThreeQuartersSampleRateIndexBuffer() {
        o.setWaveform(Oscillator.Waveform.TRI);
        SignalStatus s = new SignalStatus(sampleRate, 3 * (sampleRate / 4), frequency);
        o.generateSample(s);
        assertEquals(s.getAmplitude(), 0, 0.1);
    }

    @Test
    public void generateWaveAmplitudeSquareReturnsOneWithZeroIndexBuffer() {
        o.setWaveform(Oscillator.Waveform.SQU);
        SignalStatus s = new SignalStatus(sampleRate, 0, frequency);
        o.generateSample(s);
        assertEquals(s.getAmplitude(), 1, 0.1);
    }

    @Test
    public void generateWaveAmplitudeSquareReturnsNegativeOneWithHalfSampleRateIndexBuffer() {
        o.setWaveform(Oscillator.Waveform.SQU);
        SignalStatus s = new SignalStatus(sampleRate, sampleRate / 2, frequency);
        o.generateSample(s);
        assertEquals(s.getAmplitude(), -1, 0.1);
    }

    @Test
    public void generateWaveAmplitudeSquareReturnsOneWithSampleRateIndexBuffer() {
        o.setWaveform(Oscillator.Waveform.SQU);
        SignalStatus s = new SignalStatus(sampleRate, sampleRate, frequency);
        o.generateSample(s);
        assertEquals(s.getAmplitude(), 1, 0.1);
    }

    @Test
    public void generateWaveAmplitudeSquareReturnsOneWithQuarterSampleRateIndexBuffer() {
        o.setWaveform(Oscillator.Waveform.SQU);
        SignalStatus s = new SignalStatus(sampleRate, sampleRate / 4, frequency);
        o.generateSample(s);
        assertEquals(s.getAmplitude(), 1, 0.1);
    }

    @Test
    public void generateWaveAmplitudeSquareReturnsNegativeOneWithThreeQuartersSampleRateIndexBuffer() {
        o.setWaveform(Oscillator.Waveform.SQU);
        SignalStatus s = new SignalStatus(sampleRate, 3 * (sampleRate / 4), frequency);
        o.generateSample(s);
        assertEquals(s.getAmplitude(), -1, 0.1);
    }

    @Test
    public void generateWaveAmplitudeSawReturnsZeroWithZeroIndexBuffer() {
        o.setWaveform(Oscillator.Waveform.SAW);
        SignalStatus s = new SignalStatus(sampleRate, 0, frequency);
        o.generateSample(s);
        assertEquals(s.getAmplitude(), 0, 0.1);
    }

    @Test
    public void generateWaveAmplitudeSawReturnsNegativeOneWithHalfSampleRateIndexBuffer() {
        o.setWaveform(Oscillator.Waveform.SAW);
        SignalStatus s = new SignalStatus(sampleRate, sampleRate / 2, frequency);
        o.generateSample(s);
        assertEquals(s.getAmplitude(), -1, 0.1);
    }

    @Test
    public void generateWaveAmplitudeSawReturnsZeroWithSampleRateIndexBuffer() {
        o.setWaveform(Oscillator.Waveform.SAW);
        SignalStatus s = new SignalStatus(sampleRate, sampleRate, frequency);
        o.generateSample(s);
        assertEquals(s.getAmplitude(), 0, 0.1);
    }

    @Test
    public void generateWaveAmplitudeSawReturnsHalfWithQuarterSampleRateIndexBuffer() {
        o.setWaveform(Oscillator.Waveform.SAW);
        SignalStatus s = new SignalStatus(sampleRate, sampleRate / 4, frequency);
        o.generateSample(s);
        assertEquals(s.getAmplitude(), 0.5, 0.1);
    }

    @Test
    public void generateWaveAmplitudeSawReturnsNegativeHalfWithThreeQuartersSampleRateIndexBuffer() {
        o.setWaveform(Oscillator.Waveform.SAW);
        SignalStatus s = new SignalStatus(sampleRate, 3 * (sampleRate / 4), frequency);
        o.generateSample(s);
        assertEquals(s.getAmplitude(), -0.5, 0.1);
    }
}
