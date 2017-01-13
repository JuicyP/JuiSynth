/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package juisynth.gui.listener;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import juisynth.logic.player.Player;

/**
 *
 * @author juicyp
 */
public class KeyboardListener implements KeyListener {
    
    private Player player;
    
    public KeyboardListener(Player player) {
        this.player = player;
    }

    @Override
    public void keyTyped(KeyEvent ke) {
        if (ke.getKeyChar() == 'i') {
            
        } else if (ke.getKeyChar() == '8')
        {
            
        }
    }

    @Override
    public void keyPressed(KeyEvent ke) {
        
    }

    @Override
    public void keyReleased(KeyEvent ke) {
    }
    
    
}
