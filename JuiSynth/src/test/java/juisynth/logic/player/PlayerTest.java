/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package juisynth.logic.player;

import juisynth.logic.oscillator.Oscillator;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import juisynth.logic.Operator;
import juisynth.logic.Patch;
import juisynth.logic.signal.SignalStatus;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;


/**
 *
 * @author juicyp
 */
public class PlayerTest {
    
    private Player p;
    private Operator o;
    private double ampDefaultValue;
    private SourceDataLineStub a;
    
    public PlayerTest() {
    }
    
    @Before
    public void setUp() {
        a = new SourceDataLineStub();
        p = new Player();
        o = new Operator(new Patch());
        o.getPatch().setAdd(true);
        ampDefaultValue = p.getAmp();
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
        assertEquals(ampDefaultValue, p.getAmp(), 0.1);
    }
    
    @Test
    public void setAmpLargerThanOneFails() {
        p.setAmp(1.1);
        assertEquals(ampDefaultValue, p.getAmp(), 0.05);
    }
    
    @Test
    public void runningPlayerWritesBuffer() throws NoSuchMethodException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchFieldException {
        p.setSignalSource(o);
        Field field = p.getClass().getDeclaredField("audioline");
        field.setAccessible(true);
        field.set(p, a);
        Method method = p.getClass().getDeclaredMethod("writeBuffer");
        method.setAccessible(true);
        method.invoke(p, new Object[0]);
        
        int index = 0;
        o = new Operator(new Patch());
        o.getPatch().setAdd(true);
        
        byte[] sampleBuffer = new byte[Settings.BUFFER_SIZE];
        for (int i = 0; i < Settings.SAMPLES_PER_BUFFER; i++) {
            SignalStatus s = new SignalStatus(p.getFrequency());
            o.generateSample(s);
            s.setAmplitude(s.getAmplitude() * p.getAmp());
            
            double ds = s.getAmplitude() * Short.MAX_VALUE;
            short ss = (short) Math.round(ds);
            // Big endian, shift first eight bits and add as first part of sample
            sampleBuffer[index++] = (byte) (ss >> 8);
            sampleBuffer[index++] = (byte) (ss & 0xFF);
        }
        
        assertArrayEquals(a.getSampleBuffer(), sampleBuffer);
    }
    
    @Test
    public void startingThreadDoesntKillProgram() {
        p.setSignalSource(o);
        p.startPlayer();
        p.stopPlayer();
    }
}
