/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package juisynth.gui;

import juisynth.gui.listener.OscillatorWaveformListener;
import juisynth.gui.listener.OscillatorParameterListener;
import juisynth.logic.oscillator.Oscillator;
import juisynth.logic.Waveform;
import javax.swing.BoxLayout;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.SwingConstants;
import juisynth.gui.listener.SpectrumFilterListener;
import juisynth.logic.envelope.ADSR;
import juisynth.logic.filter.SpectrumFilter;

/**
 *
 * @author juicyp
 */
public class OperatorPanel extends JPanel {
    
    private Oscillator oscillator;
    private SpectrumFilter spectrumFilter;
    private ADSR adsr;
    
    public OperatorPanel(Oscillator oscillator) {
        super();
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        // TODO: Instantiate new oscillator instead of passing after refactoring
        // Operator/Oscillator
        this.oscillator = oscillator;
        this.spectrumFilter = new SpectrumFilter();
        this.adsr = new ADSR();
        this.oscillator.setFilter(this.spectrumFilter);
        this.oscillator.setEnvelopeGenerator(adsr);
        instantiateComponents();
    }

    private void instantiateComponents() {
        JLabel oscLabel = new JLabel("Oscillator", SwingConstants.CENTER);
        
        JLabel ampLabel = new JLabel("Volume", SwingConstants.CENTER);
        JSlider amp = new JSlider(JSlider.HORIZONTAL, 0, 100, (int) (oscillator.getAmp() * 100));
        amp.setMajorTickSpacing(50);
        amp.setPaintTicks(true);
        amp.setPaintLabels(true);
        
        JLabel tuningLabel = new JLabel("Tuning", SwingConstants.CENTER);
        JSlider tuning = new JSlider(JSlider.HORIZONTAL, -7200, 7200, oscillator.getTuning());
        tuning.setMajorTickSpacing(3600);
        tuning.setPaintTicks(true);
        tuning.setPaintLabels(true);
        tuning.setPaintTrack(false);
        
        JLabel filterLabel = new JLabel("Filter", SwingConstants.CENTER);
        JLabel filterParameterLabel = new JLabel("Cutoff", SwingConstants.CENTER);
        JSlider filter = new JSlider(JSlider.HORIZONTAL, 0, 100, (int) (spectrumFilter.getDepth() * 100));
        filter.setMajorTickSpacing(100);
        filter.setPaintTicks(true);
        filter.setPaintLabels(true);
        
        JLabel waveformLabel = new JLabel("Waveform", SwingConstants.CENTER);
        JComboBox waveform = new JComboBox(Waveform.values());
        
        JLabel fmLabel = new JLabel("FM Depth", SwingConstants.CENTER);
        JSlider fm = new JSlider(JSlider.HORIZONTAL, -100, 100, (int) (oscillator.getFmDepth() * 100));
        fm.setMajorTickSpacing(100);
        fm.setPaintTicks(true);
        fm.setPaintLabels(true);
        fm.setPaintTrack(false);
        
        JLabel amLabel = new JLabel("AM Depth", SwingConstants.CENTER);
        JSlider am = new JSlider(JSlider.HORIZONTAL, -100, 100, (int) (oscillator.getAmDepth() * 100));
        am.setMajorTickSpacing(100);
        am.setPaintTicks(true);
        am.setPaintLabels(true);
        am.setPaintTrack(false);
        
        add(oscLabel);
        
        amp.addChangeListener(new OscillatorParameterListener(oscillator, amp, OscillatorParameter.AMP));
        add(ampLabel);
        add(amp);
        
        tuning.addChangeListener(new OscillatorParameterListener(oscillator, tuning, OscillatorParameter.TUNING));
        add(tuningLabel);
        add(tuning);
        
        waveform.addActionListener(new OscillatorWaveformListener(oscillator, waveform));
        add(waveformLabel);
        add(waveform);
        
        add(fmLabel);
        fm.addChangeListener(new OscillatorParameterListener(oscillator, fm, OscillatorParameter.FM));
        add(fm);
        
        add(amLabel);
        am.addChangeListener(new OscillatorParameterListener(oscillator, am, OscillatorParameter.AM));
        add(am);
        
        add(new OscillatorOptionsPanel(oscillator));
        
        add(filterLabel);
        filter.addChangeListener(new SpectrumFilterListener(spectrumFilter, filter));
        add(filterParameterLabel);
        add(filter);
        
        add(new ADSRPanel(adsr));
    }
}
