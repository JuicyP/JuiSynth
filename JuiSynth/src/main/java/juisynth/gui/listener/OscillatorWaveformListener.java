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


public class OscillatorWaveformListener implements ActionListener {

    private OscillatorSettings oscs;
    private JComboBox comboBox;

    public OscillatorWaveformListener(OscillatorSettings oscs, JComboBox comboBox) {
        this.oscs = oscs;
        this.comboBox = comboBox;
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        oscs.setWaveform((Waveform) comboBox.getSelectedItem());
    }

}
