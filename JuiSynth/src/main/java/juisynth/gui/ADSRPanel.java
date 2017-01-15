/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package juisynth.gui;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.SwingConstants;
import juisynth.gui.listener.ADSRParameterListener;
import juisynth.logic.envelope.ADSR;
import juisynth.logic.envelope.ADSRSettings;


public class ADSRPanel extends JPanel {

    private ADSRSettings adsr;

    public ADSRPanel(ADSRSettings adsr) {
        super();
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.adsr = adsr;
        instantiateComponents();
    }

    private void instantiateComponents() {
        JLabel envelopeLabel = new JLabel("Envelope", SwingConstants.CENTER);
        
        JLabel attackLabel = new JLabel("Attack", SwingConstants.CENTER);
        JLabel decayLabel = new JLabel("Decay", SwingConstants.CENTER);
        JLabel sustainLabel = new JLabel("Sustain", SwingConstants.CENTER);
        JLabel releaseLabel = new JLabel("Release", SwingConstants.CENTER);
        
        JSlider attack = new JSlider(JSlider.HORIZONTAL, 0, 3000, adsr.getAttack());
        JSlider decay = new JSlider(JSlider.HORIZONTAL, 0, 3000, adsr.getDecay());
        JSlider sustain = new JSlider(JSlider.HORIZONTAL, 0, 100, (int) (adsr.getSustain() * 100));
        JSlider release = new JSlider(JSlider.HORIZONTAL, 0, 3000, adsr.getRelease());

        attack.setMajorTickSpacing(1000);
        attack.setPaintTicks(true);
        attack.setPaintLabels(true);
        
        decay.setMajorTickSpacing(1000);
        decay.setPaintTicks(true);
        decay.setPaintLabels(true);
        
        sustain.setMajorTickSpacing(100);
        sustain.setPaintTicks(true);
        sustain.setPaintLabels(true);
        
        release.setMajorTickSpacing(1000);
        release.setPaintTicks(true);
        release.setPaintLabels(true);
        
        attack.addChangeListener(new ADSRParameterListener(adsr, attack, ADSRParameter.ATTACK));
        decay.addChangeListener(new ADSRParameterListener(adsr, decay, ADSRParameter.DECAY));
        sustain.addChangeListener(new ADSRParameterListener(adsr, sustain, ADSRParameter.SUSTAIN));
        release.addChangeListener(new ADSRParameterListener(adsr, release, ADSRParameter.RELEASE));

        add(envelopeLabel);

        add(attackLabel);
        add(attack);
        
        add(decayLabel);
        add(decay);
        
        add(sustainLabel);
        add(sustain);
        
        add(releaseLabel);
        add(release);
    }

}
