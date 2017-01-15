/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package juisynth.gui.listener;

import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import juisynth.gui.ADSRParameter;
import juisynth.logic.envelope.ADSRSettings;

/**
 * Defines a listener for the parameters as defined by the implementation of ADSR.
 * @author juicyp
 */
public class ADSRParameterListener implements ChangeListener {
    
    private ADSRSettings adsr;
    private JSlider slider;
    private ADSRParameter adsrParameter;
    
    /**
     * Constructor for the listener, supplying the slider, parameter enumerator and the
     * ADSRSettings component to be connected to.
     * @param adsr The ADSRSettings object.
     * @param slider The slider relating to a parameter.
     * @param adsrParameter The parameter.
     */
    public ADSRParameterListener(ADSRSettings adsr, JSlider slider, ADSRParameter adsrParameter) {
        this.adsr = adsr;
        this.slider = slider;
        this.adsrParameter = adsrParameter;
    }

    @Override
    public void stateChanged(ChangeEvent ce) {
        switch (adsrParameter) {
            case ATTACK:
                adsr.setAttack(slider.getValue());
                break;
            case DECAY:
                adsr.setDecay(slider.getValue());
                break;
            case SUSTAIN:
                adsr.setSustain(slider.getValue() / (double) 100);
                break;
            case RELEASE:
                adsr.setRelease(slider.getValue());
                break;
            default:
                break;
        }
    }
    
}
