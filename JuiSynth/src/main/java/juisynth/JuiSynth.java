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

    /**
     * Main method does not take arguments.
     * @param args Not to be supplied.
     */
    public static void main(String[] args) {
        
        GUI ui = new GUI();
        SwingUtilities.invokeLater(ui);
    }
}
