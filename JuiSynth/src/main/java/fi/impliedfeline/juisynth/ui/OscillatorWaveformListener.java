/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.impliedfeline.juisynth.ui;

import fi.impliedfeline.juisynth.logic.Oscillator;
import fi.impliedfeline.juisynth.logic.Waveform;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JComboBox;

/**
 *
 * @author juicyp
 */
public class OscillatorWaveformListener implements ActionListener {
    
    private Oscillator oscillator;
    private JComboBox comboBox;
    
    public OscillatorWaveformListener(Oscillator oscillator, JComboBox comboBox) {
        this.oscillator = oscillator;
        this.comboBox = comboBox;
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        oscillator.setWaveform((Waveform)comboBox.getSelectedItem());
    }
            
}
