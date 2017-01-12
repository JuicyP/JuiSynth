/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package juisynth.logic.filter;

import juisynth.logic.signal.SignalStatus;

/**
 *
 * @author juicyp
 */
public abstract class Filter {
    
    public abstract double generateFilter(SignalStatus signal);
}
