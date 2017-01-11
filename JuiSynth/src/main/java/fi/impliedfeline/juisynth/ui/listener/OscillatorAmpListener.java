/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.impliedfeline.juisynth.ui.listener;

import fi.impliedfeline.juisynth.logic.oscillator.Oscillator;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 *
 * @author juicyp
 */
public class OscillatorAmpListener implements ChangeListener {
    
    private Oscillator oscillator;
    private JSlider slider;

    public OscillatorAmpListener(Oscillator oscillator, JSlider slider) {
        this.oscillator = oscillator;
        this.slider = slider;
    }

    @Override
    public void stateChanged(ChangeEvent ce) {
        oscillator.setAmp(slider.getValue());
    }
    
}
