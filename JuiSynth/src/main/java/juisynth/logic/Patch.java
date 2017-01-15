/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package juisynth.logic;

import juisynth.logic.envelope.ADSRSettings;

/**
 *
 * @author juicyp
 */
public class Patch {

    private int tuning = 0;
    private double amp = 1;
    private boolean fixed = false;

    private boolean bypass = false;
    private boolean add = false;

    private boolean fm = false;
    private double fmDepth = 0.0;

    private boolean am = false;
    private double amDepth = 0.0;

    private boolean sync = false;
    private boolean invert = false;
    private boolean invertOnSync = false;
    
    private double filterDepth = 0;
    private ADSRSettings egs = new ADSRSettings();

    public int getTuning() {
        return tuning;
    }

    public void setTuning(int tuning) {
        this.tuning = tuning;
    }

    public double getAmp() {
        return amp;
    }

    public void setAmp(double amp) {
        if (amp < 0 || amp > 1) {
            return;
        }
        this.amp = amp;
    }

    public boolean isFixed() {
        return fixed;
    }

    public void setFixed(boolean fixed) {
        this.fixed = fixed;
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

    public void setAmDepth(double amDepth) {
        if (amDepth < -1 || amDepth > 1) {
            return;
        }
        this.amDepth = amDepth;
    }

    public boolean isSync() {
        return sync;
    }

    public void setSync(boolean sync) {
        this.sync = sync;
    }

    public boolean isInvert() {
        return invert;
    }

    public void setInvert(boolean invert) {
        this.invert = invert;
    }

    public boolean isInvertOnSync() {
        return invertOnSync;
    }

    public void setInvertOnSync(boolean invertOnSync) {
        this.invertOnSync = invertOnSync;
    }

    public double getFilterDepth() {
        return filterDepth;
    }

    public void setFilterDepth(double filterDepth) {
        if (filterDepth < 0 || filterDepth > 1) {
            return;
        }
        this.filterDepth = filterDepth;
    }
    
    public ADSRSettings getEgs() {
        return egs;
    }

    public void setEgs(ADSRSettings egs) {
        this.egs = egs;
    }
}
