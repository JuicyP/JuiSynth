/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package juisynth.logic.filter;

import juisynth.logic.oscillator.Waveform;
import juisynth.logic.oscillator.WaveformCalculator;
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
    public double generateFilter(SignalStatus signal) {
        double amplitude = WaveformCalculator.calculateWaveformY(signal.getOperatorPhase(), Waveform.SIN);
        return (2 * depth * amplitude + 2 * (1 - depth) * signal.getAmplitude()) / 2;
    }
    
}
