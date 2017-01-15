/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package juisynth.logic;

import juisynth.logic.envelope.ADSRSettings;
import juisynth.logic.filter.FilterSettings;
import juisynth.logic.oscillator.OscillatorSettings;

/**
 * Patch contains the settings for the implementation of a synthesizer component,
 * specified as containing an oscillator, a spectrum filter and an ADSR envelope.
 * @author juicyp
 */
public class Patch {
   
    private double amp = 1;
    
    private boolean bypass = false;
    private boolean add = false;

    private boolean fm = false;
    private double fmDepth = 0.0;

    private boolean am = false;
    private double amDepth = 0.0;

    private boolean invert = false;
    
    private FilterSettings fs;
    private ADSRSettings egs;
    private OscillatorSettings oscs;

    /**
     * Constructor for patch. Instantiates new settings.
     */
    public Patch() {
        this.fs = new FilterSettings();
        this.egs = new ADSRSettings();
        this.oscs = new OscillatorSettings();
    }
    
    public double getAmp() {
        return amp;
    }

    /**
     * Sets amplifier value for the patch. The value must be between 0 and 1 inclusive.
     * @param amp Value for amplifier.
     */
    public void setAmp(double amp) {
        if (amp < 0 || amp > 1) {
            return;
        }
        this.amp = amp;
    }

    public boolean isBypass() {
        return bypass;
    }

    public void setBypass(boolean bypass) {
        this.bypass = bypass;
    }

    public boolean isAdd() {
        return add;
    }

    public void setAdd(boolean add) {
        this.add = add;
    }

    public boolean isFm() {
        return fm;
    }

    public void setFm(boolean fm) {
        this.fm = fm;
    }

    public double getFmDepth() {
        return fmDepth;
    }

    /**
     * Sets the depth of FM to be applied. Value must be between -1 and 1 inclusive.
     * @param fmDepth Depth of applied FM.
     */
    public void setFmDepth(double fmDepth) {
        if (fmDepth < -1 || fmDepth > 1) {
            return;
        }
        this.fmDepth = fmDepth;
    }

    public boolean isAm() {
        return am;
    }

    public void setAm(boolean am) {
        this.am = am;
    }

    public double getAmDepth() {
        return amDepth;
    }

    /**
     * Sets the depth of AM to be applied. Value must be between -1 and 1 inclusive.
     * @param amDepth Depth of applied AM.
     */
    public void setAmDepth(double amDepth) {
        if (amDepth < -1 || amDepth > 1) {
            return;
        }
        this.amDepth = amDepth;
    }

    public boolean isInvert() {
        return invert;
    }

    public void setInvert(boolean invert) {
        this.invert = invert;
    }
    
    public OscillatorSettings getOscillatorSettings() {
        return oscs;
    }
    
    public void setOscillatorSettings(OscillatorSettings oscs) {
        this.oscs = oscs;
    }
    
    public ADSRSettings getEnvelopeGeneratorSettings() {
        return egs;
    }

    public void setEnvelopeGeneratorSettings(ADSRSettings egs) {
        this.egs = egs;
    }
    
    public FilterSettings getFilterSettings() {
        return fs;
    }
    
    public void setFilterSettings(FilterSettings fs) {
        this.fs = fs;
    }
}
