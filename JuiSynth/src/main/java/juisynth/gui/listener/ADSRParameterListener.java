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
import juisynth.logic.envelope.ADSR;
import juisynth.logic.envelope.ADSRSettings;

/**
 *
 * @author juicyp
 */
public class ADSRParameterListener implements ChangeListener {
    
    private ADSRSettings adsr;
    private JSlider slider;
    private ADSRParameter adsrParameter;
    
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
