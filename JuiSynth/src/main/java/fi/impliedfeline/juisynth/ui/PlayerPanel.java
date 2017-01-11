/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.impliedfeline.juisynth.ui;

import fi.impliedfeline.juisynth.logic.player.Player;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

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
        JButton start = new JButton("Start");
        JButton stop = new JButton("Stop");
            
        start.addActionListener(new PlayerStartListener(player));
        add(start);
        stop.addActionListener(new PlayerStopListener(player));
        add(stop);
    }
}
