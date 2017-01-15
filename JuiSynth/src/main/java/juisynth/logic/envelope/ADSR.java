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

    private double noteOffLevel = 0;
    private int sampleTimer = -1;
    private int noteOffSample = -1;

    public ADSR(ADSRSettings egs) {
        this.egs = egs;
    }

    @Override
    public void loadEnvelopeGeneratorSettings(EnvelopeSettings egs) {
        if (!(egs instanceof ADSRSettings)) {
            return;
        }
        this.egs = egs;
    }

    @Override
    public double generateEnvelope(boolean activeNote) {
        ADSRSettings ags = (ADSRSettings) egs;
        sampleTimer++;
        int elapsedTime = (int) (sampleTimer / (double) Settings.SAMPLE_RATE * 1000);
        double amplitude = 0;

        if (activeNote) {
            noteOffSample = -1;
            if (ags.getAttack() > elapsedTime) {
                amplitude = elapsedTime / (double) ags.getAttack();
            } else if (ags.getAttack() + ags.getDecay() > elapsedTime) {
                amplitude = 1 - (1 - ags.getSustain()) * ((elapsedTime - ags.getAttack()) / (double) ags.getDecay());
            } else {
                amplitude = ags.getSustain();
            }
            noteOffLevel = amplitude;
            return amplitude;
        }

        if (noteOffSample == -1) {
            noteOffSample = sampleTimer;
        }

        int noteOffTime = (int) (noteOffSample / (double) Settings.SAMPLE_RATE * 1000);
        // Time elapsed after noteOff isn't longer than release
        if (ags.getRelease() > elapsedTime - noteOffTime) {
            return amplitude = noteOffLevel - noteOffLevel * ((elapsedTime - noteOffTime) / (double) ags.getRelease());
        }

        sampleTimer = -1;
        noteOffSample = -1;
        noteOffLevel = 0;
        return 0;
    }

}
