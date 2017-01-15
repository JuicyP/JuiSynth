/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package juisynth.gui;

import juisynth.gui.listener.PlayerModeListener;
import juisynth.gui.listener.PlayerAmpListener;
import juisynth.logic.player.Player;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;

/**
 * PlayerPanel is a class extending JPanel containing the GUI elements relevant to an
 * Player.
 * @author juicyp
 */
public class PlayerPanel extends JPanel {
    
    private Player player;
    
    /**
     * Constructor for PlayerPanel, instantiates all the GUI components in the panel.
     * @param player The Player object to connect to the GUI elements.
     */
    public PlayerPanel(Player player) {
        super();
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.player = player;
        instantiateComponents();
    }

    private void instantiateComponents() {
        JLabel ampLabel = new JLabel("Master");
        JSlider amp = new JSlider(JSlider.HORIZONTAL, 0, 100, (int)(player.getAmp() * 100));
        amp.setMajorTickSpacing(50);
        amp.setPaintTicks(true);
        amp.setPaintLabels(true);
        
        JButton start = new JButton("Start");
        JButton stop = new JButton("Stop");
        
        add(ampLabel);
        amp.addChangeListener(new PlayerAmpListener(player, amp));
        add(amp);
        
        start.addActionListener(new PlayerModeListener(player, PlayerMode.START));
        add(start);
        stop.addActionListener(new PlayerModeListener(player, PlayerMode.STOP));
        add(stop);
    }
}
