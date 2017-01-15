/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package juisynth.logic.envelope;

/**
 * Contains settings specific to an ADSR-envelope generator.
 * @author juicyp
 */
public class ADSRSettings extends EnvelopeSettings {
    
    private int attack = 0;
    private int decay = 0;
    private double sustain = 1;
    private int release = 0;
    
    public int getAttack() {
        return attack;
    }

    /**
     * Sets non negative value for the attack, specified in milliseconds.
     * @param attack The time value of the attack phase.
     */
    public void setAttack(int attack) {
        if (attack < 0) {
            return;
        }
        this.attack = attack;
    }
    
    public int getDecay() {
        return decay;
    }

    /**
     * Sets non negative value for the decay, specified in milliseconds.
     * @param decay The time value of the decay phase.
     */
    public void setDecay(int decay) {
        if (decay < 0) {
            return;
        }
        this.decay = decay;
    }
    
    public double getSustain() {
        return sustain;
    }

    /**
     * Sets the amplitude for the generated envelope during the sustain phase.
     * Value must be between 0 and 1 inclusive.
     * @param sustain The amplitude of the sustain phase.
     */
    public void setSustain(double sustain) {
        if (sustain < 0 || sustain > 1) {
            return;
        }
        this.sustain = sustain;
    }
    
    public int getRelease() {
        return release;
    }

    /**
     * Sets non negative value for the release, specified in milliseconds.
     * @param release The time value of the release phase.
     */
    public void setRelease(int release) {
        if (release < 0) {
            return;
        }
        this.release = release;
    }
}
