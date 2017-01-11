/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package juisynth;

import juisynth.gui.GUI;
import javax.swing.SwingUtilities;

/** Main class for JuiSynth project.
 *
 * @author juicyp
 */
public class JuiSynth {

    // BEWARE! Software is LOUD
    // Prototype modeled after Dr. Dobb's synth articles 
    // http://www.drdobbs.com/jvm/creating-music-components-in-java/229700113?pgno=1
    // Testing
    /**
     * main method, does not take arguments.
     * @param args 
     */
    public static void main(String[] args) {
        
        GUI ui = new GUI();
        SwingUtilities.invokeLater(ui);
    }
}
