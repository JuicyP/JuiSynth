/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.impliedfeline.juisynth.ui;

import fi.impliedfeline.juisynth.logic.*;
import java.awt.Container;
import java.awt.Dimension;
import javax.swing.*;
import javax.swing.JComboBox;

/**
 *
 * @author juicyp
 */
public class UI implements Runnable {
    
    private JFrame frame;
    private Oscillator oscillator;
    private Player player;

    @Override
    public void run() {
        oscillator = new Oscillator();
        oscillator.setAdd(true);
        
        player = new Player();      
        player.setSignalSource(oscillator);
        
        frame = new JFrame("JuiSynth");
        frame.setPreferredSize(new Dimension(300, 500));
        
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        
        instantiateComponents(frame.getContentPane());
        
        frame.pack();
        frame.setVisible(true);
    }
    
    //TODO: Refactor into separate classes extending JPanel
    private void instantiateComponents(Container container) {
        BoxLayout layout = new BoxLayout(container, BoxLayout.Y_AXIS);
        container.setLayout(layout);
        
        /*
        JLabel ampLabel = new JLabel("Volume");
        JSlider amp = new JSlider(JSlider.HORIZONTAL, 0, 100, 0);
        amp.setMajorTickSpacing(10);
        amp.setPaintTicks(true);
        amp.setPaintLabels(true);
        */
        
        JLabel tuningLabel = new JLabel("Tuning");
        JSlider tuning = new JSlider(JSlider.HORIZONTAL, -3600, 3600, 0);
        tuning.setMajorTickSpacing(1200);
        tuning.setPaintTicks(true);
        tuning.setPaintLabels(true);
        
        JButton start = new JButton("Start");
        JButton stop = new JButton("Stop");
        
        JLabel waveformLabel = new JLabel("Waveform");
        JComboBox waveform = new JComboBox(Waveform.values());
        /*
        container.add(ampLabel);
        container.add(amp);
        */
        
        tuning.addChangeListener(new OscillatorTuningListener(oscillator, tuning));
        container.add(tuningLabel);
        container.add(tuning);
        
        waveform.addActionListener(new OscillatorWaveformListener(oscillator, waveform));
        container.add(waveformLabel);
        container.add(waveform);
        
        start.addActionListener(new PlayerStartListener(player));
        container.add(start);
        stop.addActionListener(new PlayerStopListener(player));
        container.add(stop);
    }
    
    public JFrame getFrame() {
        return frame;
    }
}
