/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package juisynth.logic.envelope;

import java.util.Random;
import juisynth.logic.player.Settings;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author juicyp
 */
public class ADSRTest {

    private ADSR e;

    public ADSRTest() {
    }

    @Before
    public void setUp() {
        e = new ADSR();
    }
    
    @Test
    public void setAttackSetsWithPositive() {
        e.setAttack(1);
        assertEquals(1, e.getAttack());
    }

    @Test
    public void setAttackSetsWithZero() {
        e.setAttack(1);   
        e.setAttack(0);
        assertEquals(0, e.getAttack());
    }
    
    @Test
    public void setAttackFailsWithNegative() {
        int defaultAttack = e.getAttack();
        e.setAttack(-1);
        assertEquals(defaultAttack, e.getAttack());
    }
    
    @Test
    public void setDecaySetsWithPositive() {
        e.setDecay(1);
        assertEquals(1, e.getDecay());
    }

    @Test
    public void setDecaySetsWithZero() {
        e.setDecay(1);   
        e.setDecay(0);
        assertEquals(0, e.getDecay());
    }
    
    @Test
    public void setDecayFailsWithNegative() {
        int defaultDecay = e.getDecay();
        e.setDecay(-1);
        assertEquals(defaultDecay, e.getDecay());
    }
    
    @Test
    public void setSustainSetsWithOne() {
        e.setSustain(1);
        assertEquals(1, e.getSustain(), 0.05);
    }
    
    @Test
    public void setSustainSetsWithZero() {
        e.setSustain(1);
        e.setSustain(0);
        assertEquals(0, e.getSustain(), 0.05);
    }
    
    @Test
    public void setSustainFailsWithGreaterThanOne() {
        double defaultSustain = e.getSustain();
        e.setSustain(1.1);
        assertEquals(defaultSustain, e.getSustain(), 0.05);
    }
    
    @Test
    public void setSustainFailsWithNegative() {
        double defaultSustain = e.getSustain();
        e.setSustain(-0.1);
        assertEquals(defaultSustain, e.getSustain(), 0.05);
    }
    
    @Test
    public void setReleaseSetsWithPositive() {
        e.setRelease(1);
        assertEquals(1, e.getRelease());
    }

    @Test
    public void setReleaseSetsWithZero() {
        e.setRelease(1);   
        e.setRelease(0);
        assertEquals(0, e.getRelease());
    }
    
    @Test
    public void setReleaseFailsWithNegative() {
        int defaultRelease = e.getRelease();
        e.setRelease(-1);
        assertEquals(defaultRelease, e.getRelease());
    }

    @Test
    public void generateEnvelopeReturnsOneWithActiveNoteTrueByDefault() {
        assertEquals(1, e.generateEnvelope(true), 0.05);
    }

    @Test
    public void generateEnvelopeReturnsZeroWithActiveNoteFalseByDefault() {
        assertEquals(0, e.generateEnvelope(false), 0.05);
    }

    @Test
    public void generateEnvelopeReturnsSustainWithActiveNoteTrueByDefault() {
        e.setSustain(0.5);
        assertEquals(0.5, e.generateEnvelope(true), 0.05);
    }
    
    @Test
    public void generateEnvelopeReturnsZeroWithActiveNoteTrueAttackThousandOneInvocation() {
        e.setAttack(1000);
        double amplitude = 0;
        for (int i = 0; i < 1; i++) {
            amplitude = e.generateEnvelope(true);
        }
        assertEquals(0, amplitude, 0.05);
    }
    
    @Test
    public void generateEnvelopeReturnsHalfWithActiveNoteTrueAttackThousandHalfSampleRateInvocations() {
        e.setAttack(1000);
        double amplitude = 0;
        for (int i = 0; i < Settings.SAMPLE_RATE / 2; i++) {
            amplitude = e.generateEnvelope(true);
        }
        assertEquals(0.5, amplitude, 0.05);
    }
    
    @Test
    public void generateEnvelopeReturnsOneWithActiveNoteTrueAttackThousandSampleRateInvocations() {
        e.setAttack(1000);
        double amplitude = 0;
        for (int i = 0; i < Settings.SAMPLE_RATE; i++) {
            amplitude = e.generateEnvelope(true);
        }
        assertEquals(1, amplitude, 0.05);
    }
    
    @Test
    public void generateEnvelopeReturnsOneWithActiveNoteTrueDecayThousandOneInvocation() {
        e.setDecay(1000);
        double amplitude = 0;
        for (int i = 0; i < 1; i++) {
            amplitude = e.generateEnvelope(true);
        }
        assertEquals(1, amplitude, 0.05);
    }
    
    @Test
    public void generateEnvelopeReturnsOnePlusSustainDividedByTwoWithActiveNoteTrueDecayThousandHalfSampleRateInvocations() {
        e.setDecay(1000);
        e.setSustain(1); // Any valid sustain value
        double amplitude = 0;
        for (int i = 0; i < Settings.SAMPLE_RATE / 2; i++) {
            amplitude = e.generateEnvelope(true);
        }
        assertEquals((1 + e.getSustain()) / 2, amplitude, 0.05);
    }
    
    @Test
    public void generateEnvelopeReturnsSustainWithActiveNoteTrueDecayThousandSampleRateInvocations() {
        e.setDecay(1000);
        e.setSustain(0); // Any valid sustain value
        double amplitude = 0;
        for (int i = 0; i < Settings.SAMPLE_RATE; i++) {
            amplitude = e.generateEnvelope(true);
        }
        assertEquals(e.getSustain(), amplitude, 0.05);
    }
    
    @Test
    public void generateEnvelopeReturnsOneWithActiveNoteTrueAttackAndDecayThousandSampleRateInvocations() {
        e.setAttack(1000);
        e.setDecay(1000);
        e.setSustain(0);
        double amplitude = 0;
        for (int i = 0; i < Settings.SAMPLE_RATE; i++) {
            amplitude = e.generateEnvelope(true);
        }
        assertEquals(1, amplitude, 0.05);
    }
    
    @Test
    public void generateEnvelopeReturnsSustainWithActiveNoteTrueAttackAndDecayThousandTwiceSampleRateInvocations() {
        e.setAttack(1000);
        e.setDecay(1000);
        e.setSustain(0);
        double amplitude = 0;
        for (int i = 0; i < 2 * Settings.SAMPLE_RATE; i++) {
            amplitude = e.generateEnvelope(true);
        }
        assertEquals(e.getSustain(), amplitude, 0.05);
    }
    
    @Test
    public void generateEnvelopeReturnsOneWithActiveNoteFalseReleaseThousandOneInvocation() {
        e.setRelease(1000);
        double amplitude = 0;
        e.generateEnvelope(true);
        for (int i = 0; i < 1; i++) {
            amplitude = e.generateEnvelope(false);
        }
        assertEquals(1, amplitude, 0.05);
    }
    
    @Test
    public void generateEnvelopeReturnsSustainDividedByTwoWithActiveNoteFalseReleaseThousandHalfSampleRateInvocations() {
        e.setRelease(1000);
        e.setSustain(0.5); // Any valid sustain value
        double amplitude = 0;
        e.generateEnvelope(true);
        for (int i = 0; i < Settings.SAMPLE_RATE / 2; i++) {
            amplitude = e.generateEnvelope(false);
        }
        assertEquals(e.getSustain() / 2, amplitude, 0.05);
    }
    
    @Test
    public void generateEnvelopeReturnsZeroWithActiveNoteFalseReleaseThousandSampleRateInvocations() {
        e.setRelease(1000);
        e.setSustain(1); // Any valid sustain value
        double amplitude = 0;
        e.generateEnvelope(true);
        for (int i = 0; i < Settings.SAMPLE_RATE; i++) {
            amplitude = e.generateEnvelope(false);
        }
        assertEquals(0, amplitude, 0.05);
    }
    
    @Test
    public void generateEnvelopeGeneratesAnticipatedEnvelope() {
        e.setAttack(1000);
        e.setDecay(1000);
        e.setSustain(0.5);
        e.setRelease(1000);
        
        double amplitude = 0;
        for (int i = 0; i < Settings.SAMPLE_RATE / 2; i++) {
            amplitude = e.generateEnvelope(true);
        }
        assertEquals(0.5, amplitude, 0.05);
        
        for (int i = 0; i < Settings.SAMPLE_RATE / 2; i++) {
            amplitude = e.generateEnvelope(true);
        }
        assertEquals(1, amplitude, 0.05);
        
        for (int i = 0; i < Settings.SAMPLE_RATE / 2; i++) {
            amplitude = e.generateEnvelope(true);
        }
        assertEquals((1 + e.getSustain()) / 2, amplitude, 0.05);
        
        for (int i = 0; i < Settings.SAMPLE_RATE / 2; i++) {
            amplitude = e.generateEnvelope(true);
        }
        assertEquals(e.getSustain(), amplitude, 0.05);
        
        for (int i = 0; i < Settings.SAMPLE_RATE / 2; i++) {
            amplitude = e.generateEnvelope(false);
        }
        assertEquals(e.getSustain() / 2, amplitude, 0.05);
        
        for (int i = 0; i < Settings.SAMPLE_RATE / 2; i++) {
            amplitude = e.generateEnvelope(false);
        }
        assertEquals(0, amplitude, 0.05);
    }
}
