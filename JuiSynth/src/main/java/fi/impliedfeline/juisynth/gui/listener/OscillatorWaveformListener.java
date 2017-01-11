/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.impliedfeline.juisynth.gui.listener;

import fi.impliedfeline.juisynth.logic.oscillator.Oscillator;
import fi.impliedfeline.juisynth.logic.oscillator.Waveform;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JComboBox;

/**
 * Listens to changes made to dropdown menu and changes oscillator waveform.
 *
 * @author juicyp
 */
public class OscillatorWaveformListener implements ActionListener {

    private Oscillator oscillator;
    private JComboBox comboBox;

    /**
     * Constructor sets values of fields.
     * @param oscillator Oscillator to be modified.
     * @param comboBox Dropdown menu to be listened.
     */
    public OscillatorWaveformListener(Oscillator oscillator, JComboBox comboBox) {
        this.oscillator = oscillator;
        this.comboBox = comboBox;
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        oscillator.setWaveform((Waveform) comboBox.getSelectedItem());
    }

}
