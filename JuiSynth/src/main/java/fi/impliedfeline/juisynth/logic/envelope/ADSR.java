/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.impliedfeline.juisynth.logic.envelope;

import fi.impliedfeline.juisynth.logic.player.Settings;
import fi.impliedfeline.juisynth.logic.signal.SignalStatus;

/**
 *
 * @author juicyp
 */
public class ADSR extends EnvelopeGenerator {

    // Instantiating fields at declaration here as well for uniformity
    private int attack = 0;
    private int decay = 0;
    private int sustain = 100;
    private int release = 0;

    private double noteOffLevel;
    // Watch out for unexpected behaviour due to overflowing buffer indices
    private int noteOffBufferIndex = -1;

    public ADSR() {
    }

    public void setAttack(int attack) {
        if (attack < 0) {
            return;
        }
        this.attack = attack;
    }

    public void setDecay(int decay) {
        if (decay < 0) {
            return;
        }
        this.decay = decay;
    }

    public void setSustain(int sustain) {
        if (sustain < 0 || sustain > 100) {
            return;
        }
        this.sustain = sustain;
    }

    public void setRelease(int release) {
        if (release < 0) {
            return;
        }
        this.release = release;
    }

    // Useless casts?
    @Override
    public double generateEnvelope(SignalStatus signal) {
        int elapsedTime = (int) (signal.getBufferIndex() / (double) Settings.SAMPLE_RATE * 1000);
        double amplitude = 0;

        if (signal.getActiveNote()) {
            noteOffBufferIndex = -1;
            if (attack >= elapsedTime) {
                amplitude = elapsedTime / (double) attack;
            } else if (attack + decay >= elapsedTime) {
                amplitude = 1 - (1 - sustain) * ((elapsedTime - attack) / (double) decay);
            } else {
                amplitude = sustain / (double) 100;
            }
            noteOffLevel = amplitude;
            return amplitude;
        }

        if (noteOffBufferIndex == -1) {
            noteOffBufferIndex = signal.getBufferIndex();
        }

        int noteOffTime = (int) (noteOffBufferIndex / (double) Settings.SAMPLE_RATE * 1000);
        // Time elapsed after noteOff isn't longer than release
        if (release >= elapsedTime - noteOffTime) {
            amplitude = noteOffLevel - noteOffLevel * ((elapsedTime - noteOffTime) / (double) release);
        }

        return amplitude;
    }

}
