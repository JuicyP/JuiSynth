/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package juisynth.logic.envelope;

import juisynth.logic.signal.SignalStatus;

/**
 *
 * @author juicyp
 */
public abstract class EnvelopeGenerator {
    
    public abstract double generateEnvelope(boolean activeNote);
}
