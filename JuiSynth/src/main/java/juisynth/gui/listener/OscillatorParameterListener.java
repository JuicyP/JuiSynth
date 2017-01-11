/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package juisynth.gui.listener;

import juisynth.gui.OscillatorParameter;
import juisynth.logic.oscillator.Oscillator;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 *
 * @author juicyp
 */
public class OscillatorParameterListener implements ChangeListener {
    
    private Oscillator oscillator;
    private JSlider slider;
    private OscillatorParameter oscillatorParameter;

    public OscillatorParameterListener(Oscillator oscillator, JSlider slider, OscillatorParameter oscillatorParameter) {
        this.oscillator = oscillator;
        this.slider = slider;
        this.oscillatorParameter = oscillatorParameter;
    }

    @Override
    public void stateChanged(ChangeEvent ce) {
        switch (oscillatorParameter) {
            case AMP:
                oscillator.setAmp(slider.getValue() / (double) 100);
                break;
            case TUNING:
                oscillator.setTuning(slider.getValue());
                break;
            case FM:
                oscillator.setFmDepth(slider.getValue() / (double) 100);
                break;
            case AM:
                oscillator.setAmDepth(slider.getValue() / (double) 100);
                break;
            default:
                break;
        }
        
    }
    
}
