/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package juisynth.logic.filter;

import juisynth.logic.Waveform;
import juisynth.logic.WaveformCalculator;
import juisynth.logic.signal.SignalStatus;

/**
 *
 * @author juicyp
 */
public class SpectrumFilter extends Filter {
    
    private double depth;
    
    public SpectrumFilter() {
    }
    
    public double getDepth() {
        return depth;
    }
    
    public void setDepth(double depth) {
        this.depth = depth;
    }

    @Override
    public double generateFilter(double phase, double amplitude) {
        double filteredAmplitude = WaveformCalculator.calculateWaveformY(phase, Waveform.SIN);
        return depth * filteredAmplitude + (1 - depth) * amplitude;
    }
    
}
