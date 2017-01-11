/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.impliedfeline.juisynth.gui;

import fi.impliedfeline.juisynth.gui.listener.OscillatorModeListener;
import fi.impliedfeline.juisynth.logic.oscillator.Oscillator;
import java.awt.GridLayout;
import javax.swing.JCheckBox;
import javax.swing.JPanel;

/**
 *
 * @author juicyp
 */
public class OscillatorOptionsPanel extends JPanel {
    
    private Oscillator oscillator;
    
    public OscillatorOptionsPanel(Oscillator oscillator) {
        super();
        setLayout(new GridLayout(0, 2));
        this.oscillator = oscillator;
        instantiateComponents();
    }

    private void instantiateComponents() {
        JCheckBox fixed = new JCheckBox("Fixed", oscillator.getFixed());
        JCheckBox bypass = new JCheckBox("Bypass", oscillator.getBypass());
        JCheckBox add = new JCheckBox("Add", oscillator.getAdd());
        JCheckBox fm = new JCheckBox("FM", oscillator.getFm());
        JCheckBox am = new JCheckBox("AM", oscillator.getAm());
        JCheckBox sync = new JCheckBox("Sync", oscillator.getSync());
        JCheckBox invert = new JCheckBox("Invert", oscillator.getInvert());
        JCheckBox invertOnSync = new JCheckBox("Invert On Sync", oscillator.getInvertOnSync());
        
        fixed.addItemListener(new OscillatorModeListener(oscillator, OscillatorMode.FIXED));
        bypass.addItemListener(new OscillatorModeListener(oscillator, OscillatorMode.BYPASS));
        add.addItemListener(new OscillatorModeListener(oscillator, OscillatorMode.ADD));
        fm.addItemListener(new OscillatorModeListener(oscillator, OscillatorMode.FM));
        am.addItemListener(new OscillatorModeListener(oscillator, OscillatorMode.AM));
        sync.addItemListener(new OscillatorModeListener(oscillator, OscillatorMode.SYNC));
        invert.addItemListener(new OscillatorModeListener(oscillator, OscillatorMode.INVERT));
        invertOnSync.addItemListener(new OscillatorModeListener(oscillator, OscillatorMode.INVERT_ON_SYNC));
        
        add(fixed);
        add(bypass);
        add(add);
        add(sync);
        add(fm);
        add(am);
        add(invert);
        add(invertOnSync);
    }
}
