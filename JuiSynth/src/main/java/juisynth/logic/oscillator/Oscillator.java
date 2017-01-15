/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package juisynth.logic.oscillator;

import juisynth.logic.WaveformCalculator;
import juisynth.logic.player.Settings;
import juisynth.logic.signal.SignalStatus;

/**
 * Oscillator defines an oscillating component, producing a signal based on it's phase
 * and a given SignalStatus object.
 * @author juicyp
 */
public class Oscillator {
    
    private OscillatorSettings oscs;
    private double phase = 0;
    
    /**
     * Sets the given OscillatorSettings object into the field containing object of said type.
     * @param oscs OscillatorSettings object containing information relevant to oscillators.
     */
    public Oscillator(OscillatorSettings oscs) {
        this.oscs = oscs;
    }
    
    public double getPhase() {
        return phase;
    }
    
    /**
     * Generates an amplitude based on the phase of the oscillator, it's settings and
     * a given SignalStatus-object.
     * @param signal SignalStatus-object containing the information relevant for oscillators.
     * @return amplitude of the generated signal.
     */
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
