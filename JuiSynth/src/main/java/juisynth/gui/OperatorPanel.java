/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package juisynth.gui;

import juisynth.gui.listener.OscillatorWaveformListener;
import juisynth.gui.listener.OscillatorParameterListener;
import juisynth.logic.oscillator.Oscillator;
import juisynth.logic.oscillator.Waveform;
import javax.swing.BoxLayout;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;

/**
 *
 * @author juicyp
 */
public class OperatorPanel extends JPanel {
    
    private Oscillator oscillator;
    
    public OperatorPanel(Oscillator oscillator) {
        super();
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.oscillator = oscillator;
        instantiateComponents();
    }

    private void instantiateComponents() {
        JLabel ampLabel = new JLabel("Volume");
        JSlider amp = new JSlider(JSlider.HORIZONTAL, 0, 100, (int) (oscillator.getAmp() * 100));
        amp.setMajorTickSpacing(50);
        amp.setPaintTicks(true);
        amp.setPaintLabels(true);
        
        JLabel tuningLabel = new JLabel("Tuning");
        JSlider tuning = new JSlider(JSlider.HORIZONTAL, -7200, 7200, oscillator.getTuning());
        tuning.setMajorTickSpacing(3600);
        tuning.setPaintTicks(true);
        tuning.setPaintLabels(true);
        tuning.setPaintTrack(false);
        
        JLabel waveformLabel = new JLabel("Waveform");
        JComboBox waveform = new JComboBox(Waveform.values());
        
        JLabel fmLabel = new JLabel("FM Depth");
        JSlider fm = new JSlider(JSlider.HORIZONTAL, -100, 100, (int) (oscillator.getFmDepth() * 100));
        fm.setMajorTickSpacing(100);
        fm.setPaintTicks(true);
        fm.setPaintLabels(true);
        fm.setPaintTrack(false);
        
        JLabel amLabel = new JLabel("AM Depth");
        JSlider am = new JSlider(JSlider.HORIZONTAL, -100, 100, (int) (oscillator.getAmDepth() * 100));
        am.setMajorTickSpacing(100);
        am.setPaintTicks(true);
        am.setPaintLabels(true);
        am.setPaintTrack(false);
        
        amp.addChangeListener(new OscillatorParameterListener(oscillator, amp, OscillatorParameter.AMP));
        add(ampLabel);
        add(amp);
        
        tuning.addChangeListener(new OscillatorParameterListener(oscillator, tuning, OscillatorParameter.TUNING));
        add(tuningLabel);
        add(tuning);
        
        waveform.addActionListener(new OscillatorWaveformListener(oscillator, waveform));
        add(waveformLabel);
        add(waveform);
        
        fm.addChangeListener(new OscillatorParameterListener(oscillator, fm, OscillatorParameter.FM));
        add(fm);
        
        am.addChangeListener(new OscillatorParameterListener(oscillator, am, OscillatorParameter.AM));
        add(am);
        
        add(new OscillatorOptionsPanel(oscillator));
    }
}
