/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package juisynth.gui.listener;

import juisynth.logic.player.Player;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 * Defines a listener for the amp parameter as defined by the implementation of Player.
 * @author juicyp
 */
public class PlayerAmpListener implements ChangeListener {

    private Player player;
    private JSlider slider;

     /**
     * Constructor for the listener, supplying the slider and the
     * Player component to be connected to.
     * @param player The Player object to be set as field.
     * @param slider The slider to be set as field.
     */
    public PlayerAmpListener(Player player, JSlider slider) {
        this.player = player;
        this.slider = slider;
    }

    @Override
    public void stateChanged(ChangeEvent ce) {
        player.setAmp(slider.getValue() / (double) 100);
    }
}
