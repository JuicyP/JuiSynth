/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.impliedfeline.juisynth.ui.listener;

import fi.impliedfeline.juisynth.logic.player.Player;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 *
 * @author juicyp
 */
public class PlayerAmpListener implements ChangeListener {

    private Player player;
    private JSlider slider;

    public PlayerAmpListener(Player player, JSlider slider) {
        this.player = player;
        this.slider = slider;
    }

    @Override
    public void stateChanged(ChangeEvent ce) {
        player.setAmp(slider.getValue());
    }
}
