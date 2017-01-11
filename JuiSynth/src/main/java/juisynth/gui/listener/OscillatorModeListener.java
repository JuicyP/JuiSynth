/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package juisynth.gui.listener;

import juisynth.gui.OscillatorMode;
import juisynth.logic.oscillator.Oscillator;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

/**
 *
 * @author juicyp
 */
public class OscillatorModeListener implements ItemListener {
    
    private Oscillator oscillator;
    private OscillatorMode oscillatorMode;
    
    public OscillatorModeListener(Oscillator oscillator, OscillatorMode oscillatorMode) {
        this.oscillator = oscillator;
        this.oscillatorMode = oscillatorMode;
    }

    @Override
    public void itemStateChanged(ItemEvent ie) {
        boolean value = (ie.getStateChange() == ItemEvent.SELECTED);
        
        switch (oscillatorMode) {
            case FIXED:
                oscillator.setFixed(value);
                break;
            case BYPASS:
                oscillator.setBypass(value);
                break;
            case ADD:
                oscillator.setAdd(value);
                break;
            case FM:
                oscillator.setFm(value);
                break;
            case AM:
                oscillator.setAm(value);
                break;
            case SYNC:
                oscillator.setSync(value);
                break;
            case INVERT:
                oscillator.setInvert(value);
                break;
            case INVERT_ON_SYNC:
                oscillator.setInvertOnSync(value);
                break;
            default:
                break;
        }
    }
}
