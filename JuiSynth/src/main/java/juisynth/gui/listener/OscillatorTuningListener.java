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
 * Defines a listener for the tuning parameter as defined by the implementation of Oscillator.
 * @author juicyp
 */
public class OscillatorTuningListener implements ChangeListener {

    private OscillatorSettings oscs;
    private JSlider slider;
    
    /**
     * Constructor for the listener, supplying the slider and the
     * OscillatorSettings component to be connected to.
     * @param oscs The OscillatorSettings object to be set as field.
     * @param slider The slider to be set as field.
     */
    public OscillatorTuningListener(OscillatorSettings oscs, JSlider slider) {
        this.oscs = oscs;
        this.slider = slider;
    }
    
    @Override
    public void stateChanged(ChangeEvent ce) {
        oscs.setTuning(slider.getValue());
    }
    
}
