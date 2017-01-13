/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package juisynth.gui;

import juisynth.logic.player.Player;
import juisynth.logic.oscillator.Oscillator;
import java.awt.Container;
import java.awt.Dimension;
import javax.swing.*;

/**
 * Main UI implementation of JuiSynth.
 * @author juicyp
 */
public class GUI implements Runnable {
    
    private JFrame frame;
    
    private Oscillator oscillator1;
    private Oscillator oscillator2;
    private Oscillator oscillator3;
    private Oscillator oscillator4;
           
    private Player player;

    @Override
    public void run() {
        oscillator1 = new Oscillator();
        oscillator1.setAdd(true);
        
        oscillator2 = new Oscillator();
        oscillator2.setBypass(true);
        oscillator2.setSignalSource(oscillator1);
        
        oscillator3 = new Oscillator();
        oscillator3.setBypass(true);
        oscillator3.setSignalSource(oscillator2);
        
        oscillator4 = new Oscillator();
        oscillator4.setBypass(true);
        oscillator4.setSignalSource(oscillator3);
        
        player = new Player();
        player.setSignalSource(oscillator4);
        
        frame = new JFrame("JuiSynth");
        frame.setPreferredSize(new Dimension(1200, 750));
        
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        
        instantiateComponents(frame.getContentPane());
        
        frame.pack();
        frame.setVisible(true);
    }
    
    private void instantiateComponents(Container container) {
        BoxLayout layout = new BoxLayout(container, BoxLayout.X_AXIS);
        container.setLayout(layout);
        
        container.add(new OperatorPanel(oscillator1));
        container.add(new OperatorPanel(oscillator2));       
        container.add(new OperatorPanel(oscillator3));
        container.add(new OperatorPanel(oscillator4));
        container.add(new PlayerPanel(player));
        
        
    }
    
    public JFrame getFrame() {
        return frame;
    }
}
