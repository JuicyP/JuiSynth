/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.impliedfeline.juisynth.ui;


import fi.impliedfeline.juisynth.logic.oscillator.Oscillator;
import fi.impliedfeline.juisynth.logic.oscillator.Waveform;
import javax.swing.BoxLayout;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;

/**
 *
 * @author juicyp
 */
public class Operator extends JPanel {
    
    private Oscillator oscillator;
    
    public Operator(Oscillator oscillator) {
        super();
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.oscillator = oscillator;
        instantiateComponents();
    }

    private void instantiateComponents() {
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
        
        JLabel waveformLabel = new JLabel("Waveform");
        JComboBox waveform = new JComboBox(Waveform.values());
        
        amp.addChangeListener(new OscillatorAmpListener(oscillator, amp));
        add(ampLabel);
        add(amp);
        
        tuning.addChangeListener(new OscillatorTuningListener(oscillator, tuning));
        add(tuningLabel);
        add(tuning);
        
        waveform.addActionListener(new OscillatorWaveformListener(oscillator, waveform));
        add(waveformLabel);
        add(waveform);
    }
}
