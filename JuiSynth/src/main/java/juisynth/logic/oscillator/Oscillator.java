/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package juisynth.logic.oscillator;

import juisynth.logic.WaveformCalculator;
import juisynth.logic.player.Settings;
import juisynth.logic.signal.SignalStatus;
import juisynth.logic.signal.SignalSource;

/**
 * Receives a SignalStatus-object along a signal path consisting of
 * SignalSources and modifies it based on it's state. Oscillator implements
 * SignalSource.
 *
 * @see SignalSource SignalStatus
 * @author juicyp
 */
public class Oscillator {
    
    private OscillatorSettings oscs;
    private double phase = 0;
    
    public Oscillator(OscillatorSettings oscs) {
        this.oscs = oscs;
    }
    
    public double getPhase() {
        return phase;
    }
    
    public void setPhase(double phase) {
        this.phase = phase;
    }
    
    public double generateWaveAmplitude(SignalStatus signal) {

        double frequency = signal.getFrequency();

        if (oscs.isFixed()) {
            frequency = Settings.CONCERT_PITCH;
        }
        // Equal temperant tuning = 12 semitones in an octave,
        // one semitone = 100 cents.
        frequency *= Math.pow(2.0, (oscs.getTuning() / (double) 1200));

        phase += frequency / Settings.SAMPLE_RATE;
        
        if (oscs.isSync() && signal.getCompletePeriod()) {
            signal.setCompletePeriod(false);
            phase = 0;
        }
        
        if (phase == 0 || (phase % 1) < phase) {
            signal.setCompletePeriod(true);
        }
        
        phase %= 1;
        
        double y = WaveformCalculator.calculateWaveformY(phase, oscs.getWaveform());

        return y;
    }
}
