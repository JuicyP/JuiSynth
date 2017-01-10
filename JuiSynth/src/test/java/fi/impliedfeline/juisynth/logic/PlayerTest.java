/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.impliedfeline.juisynth.logic;

import fi.impliedfeline.juisynth.logic.player.Player;
import fi.impliedfeline.juisynth.logic.oscillator.Oscillator;
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
    
    public PlayerTest() {
    }
    
    @Before
    public void setUp() {
        p = new Player();
        o = new Oscillator();
    }
    
    // How do I test this?

}
