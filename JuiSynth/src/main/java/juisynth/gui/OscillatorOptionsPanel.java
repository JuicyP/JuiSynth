/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package juisynth.gui;

import juisynth.gui.listener.OperatorModeListener;
import java.awt.GridLayout;
import javax.swing.JCheckBox;
import javax.swing.JPanel;
import juisynth.logic.Patch;

/**
 * OscillatorOptionsPanel is a class extending JPanel containing the GUI elements relevant to an
 * Oscillator.
 * @author juicyp
 */
public class OscillatorOptionsPanel extends JPanel {
    
    private Patch patch;
    
    public OscillatorOptionsPanel(Patch patch) {
        super();
        setLayout(new GridLayout(0, 2));
        this.patch = patch;
        instantiateComponents();
    }

    private void instantiateComponents() {
        JCheckBox fixed = new JCheckBox("Fixed", patch.getOscillatorSettings().isFixed());
        JCheckBox bypass = new JCheckBox("Bypass", patch.isBypass());
        JCheckBox add = new JCheckBox("Add", patch.isAdd());
        JCheckBox fm = new JCheckBox("FM", patch.isFm());
        JCheckBox am = new JCheckBox("AM", patch.isAm());
        JCheckBox sync = new JCheckBox("Sync", patch.getOscillatorSettings().isSync());
        JCheckBox invert = new JCheckBox("Invert", patch.isInvert());
        
        fixed.addItemListener(new OperatorModeListener(patch, OperatorMode.FIXED));
        bypass.addItemListener(new OperatorModeListener(patch, OperatorMode.BYPASS));
        add.addItemListener(new OperatorModeListener(patch, OperatorMode.ADD));
        fm.addItemListener(new OperatorModeListener(patch, OperatorMode.FM));
        am.addItemListener(new OperatorModeListener(patch, OperatorMode.AM));
        sync.addItemListener(new OperatorModeListener(patch, OperatorMode.SYNC));
        invert.addItemListener(new OperatorModeListener(patch, OperatorMode.INVERT));
        
        add(fixed);
        add(bypass);
        add(add);
        add(sync);
        add(fm);
        add(am);
        add(invert);
    }
}
