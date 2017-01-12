/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package juisynth.logic.envelope;

import juisynth.logic.player.Settings;

/**
 *
 * @author juicyp
 */
public class ADSR extends EnvelopeGenerator {

    // Instantiating fields at declaration here as well for uniformity
    private int attack = 0;
    private int decay = 0;
    private double sustain = 1;
    private int release = 0;

    private double noteOffLevel;
    private int sampleTimer = -1;
    private int noteOffSample = -1;

    public ADSR() {
    }
    
    public int getAttack() {
        return attack;
    }

    public void setAttack(int attack) {
        if (attack < 0) {
            return;
        }
        this.attack = attack;
    }
    
    public int getDecay() {
        return decay;
    }

    public void setDecay(int decay) {
        if (decay < 0) {
            return;
        }
        this.decay = decay;
    }
    
    public double getSustain() {
        return sustain;
    }

    public void setSustain(double sustain) {
        if (sustain < 0 || sustain > 1) {
            return;
        }
        this.sustain = sustain;
    }
    
    public int getRelease() {
        return release;
    }

    public void setRelease(int release) {
        if (release < 0) {
            return;
        }
        this.release = release;
    }

    @Override
    public double generateEnvelope(boolean activeNote) {
        sampleTimer++;
        int elapsedTime = (int) (sampleTimer / (double) Settings.SAMPLE_RATE * 1000);
        double amplitude = 0;
        
        if (activeNote) {
            noteOffSample = -1;
            if (attack > elapsedTime) {
                amplitude = elapsedTime / (double) attack;
            } else if (attack + decay > elapsedTime) {
                amplitude = 1 - (1 - sustain) * ((elapsedTime - attack) / (double) decay);
            } else {
                amplitude = sustain;
            }
            noteOffLevel = amplitude;
            return amplitude;
        }

        if (noteOffSample == -1) {
            noteOffSample = sampleTimer;
        }

        int noteOffTime = (int) (noteOffSample / (double) Settings.SAMPLE_RATE * 1000);
        // Time elapsed after noteOff isn't longer than release
        if (release > elapsedTime - noteOffTime) {
            return amplitude = noteOffLevel - noteOffLevel * ((elapsedTime - noteOffTime) / (double) release);
        }

        sampleTimer = -1;
        return 0;
    }

}
