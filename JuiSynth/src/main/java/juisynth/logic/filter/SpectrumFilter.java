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
    
    private double depth;

    public double getDepth() {
        return depth;
    }

    public void setDepth(double depth) {
        if (depth < 0 || depth > 1) {
            return;
        }
        this.depth = depth;
    }
    
    public SpectrumFilter() {
    }

    @Override
    public double generateFilter(double phase, double amplitude) {
        return (1 - depth) * amplitude + depth * WaveformCalculator.calculateWaveformY(phase, Waveform.SIN);
    }
}
