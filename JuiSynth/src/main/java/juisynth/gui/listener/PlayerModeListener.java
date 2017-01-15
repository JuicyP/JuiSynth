/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package juisynth.gui.listener;

import juisynth.gui.PlayerMode;
import juisynth.logic.player.Player;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Defines a listener for the methods as defined by the implementation of Player.
 * @author juicyp
 */
public class PlayerModeListener implements ActionListener {

    private Player player;
    private PlayerMode playerMode;

    /**
     * Constructor for the listener, supplying parameter enumerator and the
     * Player component to be connected to.
     * @param player The Player object to be set as field.
     * @param playerMode The PlayerMode enumerator to be set as field.
     */
    public PlayerModeListener(Player player, PlayerMode playerMode) {
        this.player = player;
        this.playerMode = playerMode;
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (playerMode == playerMode.START) {
            player.startPlayer();
        } else if (playerMode == playerMode.STOP) {
            player.stopPlayer();
        }
    }
}
