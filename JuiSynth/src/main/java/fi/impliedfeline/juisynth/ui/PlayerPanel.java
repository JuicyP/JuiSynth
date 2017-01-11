/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.impliedfeline.juisynth.ui;

import fi.impliedfeline.juisynth.ui.listener.PlayerStopListener;
import fi.impliedfeline.juisynth.ui.listener.PlayerStartListener;
import fi.impliedfeline.juisynth.logic.player.Player;
import fi.impliedfeline.juisynth.ui.listener.PlayerAmpListener;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;

/**
 *
 * @author juicyp
 */
public class PlayerPanel extends JPanel {
    
    private Player player;
    
    public PlayerPanel(Player player) {
        super();
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.player = player;
        instantiateComponents();
    }

    private void instantiateComponents() {
        JLabel ampLabel = new JLabel("Master");
        JSlider amp = new JSlider(JSlider.HORIZONTAL, 0, 100, 100);
        amp.setMajorTickSpacing(10);
        amp.setPaintTicks(true);
        amp.setPaintLabels(true);
        
        JButton start = new JButton("Start");
        JButton stop = new JButton("Stop");
        
        add(ampLabel);
        amp.addChangeListener(new PlayerAmpListener(player, amp));
        add(amp);
        
        start.addActionListener(new PlayerStartListener(player));
        add(start);
        stop.addActionListener(new PlayerStopListener(player));
        add(stop);
    }
}
