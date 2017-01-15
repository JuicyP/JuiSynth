/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package juisynth.logic.filter;

/**
 *
 * @author juicyp
 */
public class FilterSettings {
    
    private double depth = 0;
    
    public double getDepth() {
        return depth;
    }

    public void setDepth(double depth) {
        if (depth < 0 || depth > 1) {
            return;
        }
        this.depth = depth;
    }
}
