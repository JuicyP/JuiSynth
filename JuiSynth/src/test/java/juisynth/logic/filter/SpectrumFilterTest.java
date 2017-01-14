/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package juisynth.logic.filter;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author juicyp
 */
public class SpectrumFilterTest {
    
    private SpectrumFilter sf;
    
    public SpectrumFilterTest() {
    }
    
    @Before
    public void setUp() {
        sf = new SpectrumFilter();
    }
    
    @Test
    public void generateFilterReturnsZeroWithZeroPhase() {
        assertEquals(0, sf.generateFilter(0), 0.05);
    }
    
    @Test
    public void generateFilterReturnsOneWithQuarterPhase() {
        assertEquals(1, sf.generateFilter(0.25), 0.05);
    }
    
    @Test
    public void generateFilterReturnsZeroWithHalfPhase() {
        assertEquals(0, sf.generateFilter(0.5), 0.05);
    }
    
    @Test
    public void generateFilterReturnsNegativeOneWithThreeQuartersPhase() {
        assertEquals(-1, sf.generateFilter(0.75), 0.05);
    }
    
    @Test
    public void generateFilterReturnsZeroWithFullPhase() {
        assertEquals(0, sf.generateFilter(1), 0.05);
    }
}
