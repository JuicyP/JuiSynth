/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package juisynth.gui.listener;

import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import juisynth.logic.filter.SpectrumFilter;

/**
 *
 * @author juicyp
 */
public class SpectrumFilterListener implements ChangeListener {
    
    private SpectrumFilter spectrumFilter;
    private JSlider slider;
    
    public SpectrumFilterListener(SpectrumFilter spectrumFilter, JSlider slider) {
        this.spectrumFilter = spectrumFilter;
        this.slider = slider;
    }

    @Override
    public void stateChanged(ChangeEvent ce) {
        spectrumFilter.setDepth(slider.getValue() / (double) 100);
    }
    
}
