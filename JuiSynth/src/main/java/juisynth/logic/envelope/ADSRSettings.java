/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package juisynth.logic.envelope;

/**
 *
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
}
