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
public abstract class Filter {
    
    protected FilterSettings settings;
    
    public abstract double generateFilter(double phase, double amplitude);
}
