/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package juisynth.gui.listener;

import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import juisynth.logic.filter.FilterSettings;

public class SpectrumFilterListener implements ChangeListener {
    
    private FilterSettings fs;
    private JSlider slider;
    
    public SpectrumFilterListener(FilterSettings fs, JSlider slider) {
        this.fs = fs;
        this.slider = slider;
    }

    @Override
    public void stateChanged(ChangeEvent ce) {
        fs.setDepth(slider.getValue() / (double) 100);
    }
    
}
