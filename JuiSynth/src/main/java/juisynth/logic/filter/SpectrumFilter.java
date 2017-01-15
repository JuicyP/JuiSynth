/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package juisynth.logic.filter;

import juisynth.logic.Waveform;
import juisynth.logic.WaveformCalculator;

/**
 *
 * @author juicyp
 */
public class SpectrumFilter extends Filter {
    
    public SpectrumFilter(FilterSettings settings) {
        this.settings = settings;
    }

    @Override
    public double generateFilter(double phase, double amplitude) {
        return (1 - settings.getDepth()) * amplitude + settings.getDepth() * WaveformCalculator.calculateWaveformY(phase, Waveform.SIN);
    }
}
