/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package juisynth.gui.listener;

import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import juisynth.logic.oscillator.OscillatorSettings;

/**
 *
 * @author juicyp
 */
public class OscillatorTuningListener implements ChangeListener {

    private OscillatorSettings oscs;
    private JSlider slider;
    
    public OscillatorTuningListener(OscillatorSettings oscs, JSlider slider) {
        this.oscs = oscs;
        this.slider = slider;
    }
    
    @Override
    public void stateChanged(ChangeEvent ce) {
        oscs.setTuning(slider.getValue());
    }
    
}
