/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.impliedfeline.juisynth.gui.listener;

import fi.impliedfeline.juisynth.gui.PlayerMode;
import fi.impliedfeline.juisynth.logic.player.Player;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Listens to button and starts or stops player, depending on the
 * given playerMode parameter.
 *
 * @author juicyp
 */
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
