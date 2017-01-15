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


public class PlayerModeListener implements ActionListener {

    private Player player;
    private PlayerMode playerMode;

    /**
     * Sets value of player field.
     *
     * @param player
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
