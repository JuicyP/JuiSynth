/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package juisynth.logic.oscillator;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author juicyp
 */
public class WaveformCalculatorTest {

    public WaveformCalculatorTest() {
    }

    @Test
    public void calculateWaveformYReturnsOneWithZeroAndSquare() {
        assertEquals(1, WaveformCalculator.calculateWaveformY(0, Waveform.SQU), 0.1);
    }

    @Test
    public void calculateWaveformYReturnsNegativeOneWithHalfAndSquare() {
        assertEquals(-1, WaveformCalculator.calculateWaveformY(0.5, Waveform.SQU), 0.1);
    }

    @Test
    public void calculateWaveformYReturnNegativeOneWithOneAndSquare() {
        assertEquals(-1, WaveformCalculator.calculateWaveformY(1, Waveform.SQU), 0.1);
    }
    
    @Test
    public void calculateWaveformYReturnsZeroWithZeroAndSaw() {
        assertEquals(0, WaveformCalculator.calculateWaveformY(0, Waveform.SAW), 0.1);
    }
    
    @Test
    public void calculateWaveformYReturnsHalfWithQuarterAndSaw() {
        assertEquals(0.5, WaveformCalculator.calculateWaveformY(0.25, Waveform.SAW), 0.1);
    }

    @Test
    public void calculateWaveformYReturnsNegativeOneWithHalfAndSaw() {
        assertEquals(-1, WaveformCalculator.calculateWaveformY(0.5, Waveform.SAW), 0.1);
    }
    
    @Test
    public void calculateWaveformYReturnsNegativeHalfWithThreeQuartersAndSaw() {
        assertEquals(-0.5, WaveformCalculator.calculateWaveformY(0.75, Waveform.SAW), 0.1);
    }

    @Test
    public void calculateWaveformYReturnsZeroWithOneAndSaw() {
        assertEquals(0, WaveformCalculator.calculateWaveformY(1, Waveform.SAW), 0.1);
    }
    
    @Test
    public void calculateWaveformYReturnsZeroWithZeroAndTriangle() {
        assertEquals(0, WaveformCalculator.calculateWaveformY(0, Waveform.TRI), 0.1);
    }
    
    @Test
    public void calculateWaveformYReturnsOneWithQuarterAndTriangle() {
        assertEquals(1, WaveformCalculator.calculateWaveformY(0.25, Waveform.TRI), 0.1);
    }

    @Test
    public void calculateWaveformYReturnsZeroWithHalfAndTriangle() {
        assertEquals(0, WaveformCalculator.calculateWaveformY(0.5, Waveform.TRI), 0.1);
    }
    
    @Test
    public void calculateWaveformYReturnsNegativeOneWithThreeQuartersAndTriangle() {
        assertEquals(-1, WaveformCalculator.calculateWaveformY(0.75, Waveform.TRI), 0.1);
    }

    @Test
    public void calculateWaveformYReturnsZeroWithOneAndTriangle() {
        assertEquals(0, WaveformCalculator.calculateWaveformY(1, Waveform.TRI), 0.1);
    }
    

}
