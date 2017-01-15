/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package juisynth.logic.filter;


/**
 * Specifies a filter for an oscillated signal.
 * @author juicyp
 */
public abstract class Filter {
    
    protected FilterSettings settings;
    
    /**
     * Generates a filtered amplitude based on the phase of the oscillating signal
     * and it's amplitude.
     * @param phase The phase of the oscillating signal to be filtered.
     * @param amplitude The amplitude of the oscillating signal to be filtered.
     * @return The amplitude of the filtered signal.
     */
    public abstract double generateFilter(double phase, double amplitude);
}
