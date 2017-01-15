/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package juisynth.gui.listener;

import juisynth.logic.Waveform;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JComboBox;
import juisynth.logic.oscillator.OscillatorSettings;

/**
 * Defines a listener for the waveform parameter as defined by the implementation of Oscillator.
 * @author juicyp
 */
public class OscillatorWaveformListener implements ActionListener {

    private OscillatorSettings oscs;
    private JComboBox comboBox;

    /**
     * Constructor for the listener, supplying the JComboBox and the
     * OscillatorSettings component to be connected to.
     * @param oscs The OscillatorSettings object to be set as field.
     * @param comboBox The JComboBox object to be set as field.
     */
    public OscillatorWaveformListener(OscillatorSettings oscs, JComboBox comboBox) {
        this.oscs = oscs;
        this.comboBox = comboBox;
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        oscs.setWaveform((Waveform) comboBox.getSelectedItem());
    }

}
