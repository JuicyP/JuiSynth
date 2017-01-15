/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package juisynth.logic.signal;

/**
 * Defines a method signature for generating a sample, receiving a SignalStatus
 * object.
 * @see SignalStatus
 * @author juicyp
 */
public interface SignalSource {

    /**
     * Interface for a method that takes in a SignalStatus object.
     * @param signal A SignalStatus-object containing information relevant to the signal's state.
     */
    public void generateSample(SignalStatus signal);

}
