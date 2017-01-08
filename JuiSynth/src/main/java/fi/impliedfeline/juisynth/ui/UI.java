/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.impliedfeline.juisynth.ui;

import java.awt.Container;
import java.awt.Dimension;
import javax.swing.*;

/**
 *
 * @author juicyp
 */
public class UI implements Runnable {
    
    private JFrame frame;

    @Override
    public void run() {
        frame = new JFrame("JuiSynth");
        frame.setPreferredSize(new Dimension(1000, 600));
        
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        
        instantiateComponents(frame.getContentPane());
        
        frame.pack();
        frame.setVisible(true);
    }
    
    private void instantiateComponents(Container container) {
        BoxLayout layout = new BoxLayout(container, BoxLayout.Y_AXIS);
        container.setLayout(layout);
        
        JLabel operatorName = new JLabel("Carrier");
        JLabel ampLabel = new JLabel("Volume");
        JSlider amp = new JSlider(JSlider.HORIZONTAL, 0, 100, 0);
        amp.setMajorTickSpacing(10);
        amp.setPaintTicks(true);
        amp.setPaintLabels(true);
        
        JLabel tuningLabel = new JLabel("Tuning");
        JSlider tuning = new JSlider(JSlider.HORIZONTAL, -3600, 3600, 0);
        tuning.setMajorTickSpacing(1200);
        tuning.setPaintTicks(true);
        tuning.setPaintLabels(true);
        
        JButton start = new JButton("Start");
        JButton stop = new JButton("Stop");
        
        JLabel waveformsLabel = new JLabel("Waveforms");
        JRadioButton sine = new JRadioButton("Sine");
        JRadioButton square = new JRadioButton("Square");
        JRadioButton saw = new JRadioButton("Saw");
        JRadioButton triangle = new JRadioButton("Triangle");
        JRadioButton noise = new JRadioButton("Noise");
        
        ButtonGroup waveforms = new ButtonGroup();
        waveforms.add(sine);
        waveforms.add(square);
        waveforms.add(saw);
        waveforms.add(triangle);
        waveforms.add(noise);
        
        //container.add(operatorName);
        
        container.add(ampLabel);
        container.add(amp);
        
        container.add(tuningLabel);
        container.add(tuning);
        
        container.add(waveformsLabel);
        container.add(sine);
        container.add(square);
        container.add(saw);
        container.add(triangle);
        container.add(noise);
        
        container.add(start);
        container.add(stop);
        
    }
    
    public JFrame getFrame() {
        return frame;
    }
}
