/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.impliedfeline.juisynth.ui;

import fi.impliedfeline.juisynth.logic.player.Player;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Listens to button and stops player.
 * @author juicyp
 */
public class PlayerStopListener implements ActionListener {

    private Player player;
    
    /**
     * Sets value of player field.
     * @param player 
     */
    public PlayerStopListener(Player player) {
        this.player = player;
    }
    
    @Override
    public void actionPerformed(ActionEvent ae) {
        player.stopPlayer();
    }
    
}
