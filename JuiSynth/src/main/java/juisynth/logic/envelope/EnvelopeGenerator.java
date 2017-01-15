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
public abstract class EnvelopeGenerator {
    
    protected EnvelopeSettings egs;

    public EnvelopeSettings getEnvelopeGeneratorSettings() {
        return egs;
    }

    public void loadEnvelopeGeneratorSettings(EnvelopeSettings egs) {
        this.egs = egs;
    }
    
    public abstract double generateEnvelope(boolean activeNote);
}
