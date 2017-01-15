/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package juisynth.logic.filter;

/**
 * Contains settings relevant to an implementation of a filter.
 * @author juicyp
 */
public class FilterSettings {
    
    private double depth = 0;
    
    public double getDepth() {
        return depth;
    }

    /**
     * Sets the depth of the applied filter to the signal.
     * Value must be between 0 and 1 inclusive.
     * @param depth The depth of the filter to be applied.
     */
    public void setDepth(double depth) {
        if (depth < 0 || depth > 1) {
            return;
        }
        this.depth = depth;
    }
}
