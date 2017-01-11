/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.impliedfeline.juisynth.ui;

import fi.impliedfeline.juisynth.logic.player.Player;
import fi.impliedfeline.juisynth.logic.oscillator.Waveform;
import fi.impliedfeline.juisynth.logic.oscillator.Oscillator;
import java.awt.Container;
import java.awt.Dimension;
import javax.swing.*;
import javax.swing.JComboBox;

/**
 * Main UI implementation of JuiSynth.
 * @author juicyp
 */
public class UI implements Runnable {
    
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
        frame.setPreferredSize(new Dimension(1200, 300));
        
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        
        instantiateComponents(frame.getContentPane());
        
        frame.pack();
        frame.setVisible(true);
    }
    
    //TODO: Refactor into separate classes extending JPanel
    private void instantiateComponents(Container container) {
        BoxLayout layout = new BoxLayout(container, BoxLayout.X_AXIS);
        container.setLayout(layout);
        
        container.add(new Operator(oscillator1));
        container.add(new Operator(oscillator2));       
        container.add(new Operator(oscillator3));
        container.add(new Operator(oscillator4));
        container.add(new PlayerPanel(player));
    }
    
    public JFrame getFrame() {
        return frame;
    }
}
