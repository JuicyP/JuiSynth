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
import java.awt.KeyboardFocusManager;
import javax.swing.*;
import juisynth.gui.listener.KeyboardListener;
import juisynth.logic.Operator;
import juisynth.logic.Patch;


public class GUI implements Runnable {
    
    private JFrame frame;
    
    private Operator op1;
    private Operator op2;
    private Operator op3;
    private Operator op4;
           
    private Player player;
    
    private KeyboardListener keyboardListener;

    @Override
    public void run() {
        op1 = new Operator(new Patch());
        op1.getPatch().setAdd(true);
        
        op2 = new Operator(new Patch());
        op2.getPatch().setBypass(true);
        op2.setSignalSource(op1);
        
        op3 = new Operator(new Patch());
        op3.getPatch().setBypass(true);
        op3.setSignalSource(op2);
        
        op4 = new Operator(new Patch());
        op4.getPatch().setBypass(true);
        op4.setSignalSource(op3);
        
        player = new Player();
        player.setSignalSource(op4);
        
        keyboardListener = new KeyboardListener(player);
        
        frame = new JFrame("JuiSynth");
        frame.setPreferredSize(new Dimension(1200, 750));
        
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        
        KeyboardFocusManager.getCurrentKeyboardFocusManager().addKeyEventDispatcher(keyboardListener);
        
        instantiateComponents(frame.getContentPane());
        
        frame.pack();
        frame.setVisible(true);
    }
    
    private void instantiateComponents(Container container) {
        BoxLayout layout = new BoxLayout(container, BoxLayout.X_AXIS);
        container.setLayout(layout);
        
        container.add(new OperatorPanel(op1));
        container.add(new OperatorPanel(op2));       
        container.add(new OperatorPanel(op3));
        container.add(new OperatorPanel(op4));
        container.add(new PlayerPanel(player));
    }
    
    public JFrame getFrame() {
        return frame;
    }
}
