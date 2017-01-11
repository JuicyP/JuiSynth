/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package juisynth.logic.player;

import juisynth.logic.player.Player;
import juisynth.logic.oscillator.Oscillator;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;


/**
 *
 * @author juicyp
 */
public class PlayerTest {
    
    private Player p;
    private Oscillator o;
    private double ampDefaultValue;
    private SourceDataLineStub s;
    
    public PlayerTest() {
    }
    
    @Before
    public void setUp() {
        s = new SourceDataLineStub();
        p = new Player();
        o = new Oscillator();
        o.setAdd(true);
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
        field.set(p, s);
        Method method = p.getClass().getDeclaredMethod("writeBuffer");
        method.setAccessible(true);
        method.invoke(p, new Object[0]);
        assertNotNull(s.getSampleBuffer());
    }
}
