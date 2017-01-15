/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package juisynth.logic;

import juisynth.logic.envelope.EnvelopeGenerator;
import juisynth.logic.filter.Filter;
import juisynth.logic.oscillator.Oscillator;
import juisynth.logic.signal.SignalSource;
import juisynth.logic.signal.SignalStatus;

/**
 *
 * @author juicyp
 */
public class Operator implements SignalSource {
    
    private Oscillator oscillator;
    private EnvelopeGenerator eg;
    private Filter filter;
    private SignalSource signalSource;
    private Patch patch;
    
    public Operator(Oscillator oscillator, EnvelopeGenerator eg, Filter filter, SignalSource signalSource, Patch patch) {
        this.oscillator = oscillator;
        this.eg = eg;
        this.filter = filter;
        this.signalSource = signalSource;
        this.patch = patch;
    }

    @Override
    public void generateSample(SignalStatus signal) {
        
    }

    public Oscillator getOscillator() {
        return oscillator;
    }

    public void setOscillator(Oscillator oscillator) {
        this.oscillator = oscillator;
    }

    public EnvelopeGenerator getEg() {
        return eg;
    }

    public void setEg(EnvelopeGenerator eg) {
        this.eg = eg;
    }

    public Filter getFilter() {
        return filter;
    }

    public void setFilter(Filter filter) {
        this.filter = filter;
    }

    public SignalSource getSignalSource() {
        return signalSource;
    }

    public void setSignalSource(SignalSource signalSource) {
        this.signalSource = signalSource;
    }

    public Patch getPatch() {
        return patch;
    }

    public void setPatch(Patch patch) {
        this.patch = patch;
    }
}
