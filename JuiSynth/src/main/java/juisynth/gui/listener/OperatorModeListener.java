/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package juisynth.gui.listener;

import juisynth.gui.OperatorMode;
import juisynth.logic.oscillator.Oscillator;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import juisynth.logic.Patch;

/**
 *
 * @author juicyp
 */
public class OperatorModeListener implements ItemListener {
    
    private Patch patch;
    private OperatorMode oscillatorMode;
    
    public OperatorModeListener(Patch patch, OperatorMode oscillatorMode) {
        this.patch = patch;
        this.oscillatorMode = oscillatorMode;
    }

    @Override
    public void itemStateChanged(ItemEvent ie) {
        boolean value = (ie.getStateChange() == ItemEvent.SELECTED);
        
        switch (oscillatorMode) {
            case FIXED:
                patch.getOscillatorSettings().setFixed(value);
                break;
            case BYPASS:
                patch.setBypass(value);
                break;
            case ADD:
                patch.setAdd(value);
                break;
            case FM:
                patch.setFm(value);
                break;
            case AM:
                patch.setAm(value);
                break;
            case SYNC:
                patch.getOscillatorSettings().setSync(value);
                break;
            case INVERT:
                patch.setInvert(value);
                break;
            default:
                break;
        }
    }
}
