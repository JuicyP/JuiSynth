/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package juisynth.logic.filter;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author juicyp
 */
public class SpectrumFilterTest {
    
    private SpectrumFilter sf;
    private FilterSettings sfs;
    
    public SpectrumFilterTest() {
    }
    
    @Before
    public void setUp() {
        sfs = new FilterSettings();
        sf = new SpectrumFilter(sfs);
        sfs.setDepth(1);
    }
    
    @Test
    public void generateFilterReturnsZeroWithZeroPhase() {
        assertEquals(0, sf.generateFilter(0, 1), 0.05);
    }
    
    @Test
    public void generateFilterReturnsOneWithQuarterPhase() {
        assertEquals(1, sf.generateFilter(0.25, 1), 0.05);
    }
    
    @Test
    public void generateFilterReturnsZeroWithHalfPhase() {
        assertEquals(0, sf.generateFilter(0.5, 1), 0.05);
    }
    
    @Test
    public void generateFilterReturnsNegativeOneWithThreeQuartersPhase() {
        assertEquals(-1, sf.generateFilter(0.75, 1), 0.05);
    }
    
    @Test
    public void generateFilterReturnsZeroWithFullPhase() {
        assertEquals(0, sf.generateFilter(1, 1), 0.05);
    }
}
