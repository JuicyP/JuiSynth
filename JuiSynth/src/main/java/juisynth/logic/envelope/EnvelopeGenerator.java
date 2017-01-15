/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package juisynth.logic.envelope;

/**
 * EnvelopeGenerator defines an object with a field containing settings for EGs and
 * a getter, a method to load given settings and an envelope generating method.
 * @author juicyp
 */
public abstract class EnvelopeGenerator {
    
    protected EnvelopeSettings egs;

    public EnvelopeSettings getEnvelopeGeneratorSettings() {
        return egs;
    }

    /**
     * Checks the given EnvelopeSettings object for the correct type and sets it
     * to the EnvelopeSettings field.
     * @param egs An object of type EnvelopeSettings
     */
    public void loadEnvelopeGeneratorSettings(EnvelopeSettings egs) {
        this.egs = egs;
    }
    
    /**
     * Generates an envelope dependent on the internal state of the EnvelopeGenerator
     * and the EnvelopeSettings field.
     * @param activeNote Boolean value for whether the note to be played is still active.
     * @return The value of the envelope.
     */
    public abstract double generateEnvelope(boolean activeNote);
}
