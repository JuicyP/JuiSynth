/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.impliedfeline.juisynth.ui;

import fi.impliedfeline.juisynth.logic.Player;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author juicyp
 */
public class PlayerStartListener implements ActionListener {

    private Player player;
    
    public PlayerStartListener(Player player) {
        this.player = player;
    }
    
    @Override
    public void actionPerformed(ActionEvent ae) {
        player.startPlayer();
    }
    
}
