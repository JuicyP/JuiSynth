/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package juisynth.gui.listener;

import juisynth.gui.OperatorParameter;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import juisynth.logic.Patch;

/**
 *
 * @author juicyp
 */
public class OperatorParameterListener implements ChangeListener {
    
    private Patch patch;
    private JSlider slider;
    private OperatorParameter operatorParameter;

    public OperatorParameterListener(Patch patch, JSlider slider, OperatorParameter operatorParameter) {
        this.patch = patch;
        this.slider = slider;
        this.operatorParameter = operatorParameter;
    }

    @Override
    public void stateChanged(ChangeEvent ce) {
        switch (operatorParameter) {
            case AMP:
                patch.setAmp(slider.getValue() / (double) 100);
                break;
            case FM:
                patch.setFmDepth(slider.getValue() / (double) 100);
                break;
            case AM:
                patch.setAmDepth(slider.getValue() / (double) 100);
                break;
            default:
                break;
        }
    }
}
