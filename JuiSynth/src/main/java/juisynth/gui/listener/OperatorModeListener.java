/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package juisynth.gui.listener;

import juisynth.gui.OperatorMode;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import juisynth.logic.Patch;

/**
 * Defines a listener for the boolean parameters as defined by the implementation of Operator.
 * @author juicyp
 */
public class OperatorModeListener implements ItemListener {
    
    private Patch patch;
    private OperatorMode oscillatorMode;
    
    /**
     * Constructor for the listener, supplying parameter enumerator and the
     * Patch component to be connected to.
     * @param patch The Patch object to be set as field.
     * @param oscillatorMode The OscillatorMode enumerator to be set as field.
     */
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
