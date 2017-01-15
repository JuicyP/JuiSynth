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
    private ADSRSettings es;

    public ADSRTest() {
    }

    @Before
    public void setUp() {
        es = new ADSRSettings();
        e = new ADSR(es);
    }

    @Test
    public void setAttackSetsWithPositive() {
        es.setAttack(1);
        assertEquals(1, es.getAttack());
    }

    @Test
    public void setAttackSetsWithZero() {
        es.setAttack(1);
        es.setAttack(0);
        assertEquals(0, es.getAttack());
    }

    @Test
    public void setAttackFailsWithNegative() {
        int defaultAttack = es.getAttack();
        es.setAttack(-1);
        assertEquals(defaultAttack, es.getAttack());
    }

    @Test
    public void setDecaySetsWithPositive() {
        es.setDecay(1);
        assertEquals(1, es.getDecay());
    }

    @Test
    public void setDecaySetsWithZero() {
        es.setDecay(1);
        es.setDecay(0);
        assertEquals(0, es.getDecay());
    }

    @Test
    public void setDecayFailsWithNegative() {
        int defaultDecay = es.getDecay();
        es.setDecay(-1);
        assertEquals(defaultDecay, es.getDecay());
    }

    @Test
    public void setSustainSetsWithOne() {
        es.setSustain(1);
        assertEquals(1, es.getSustain(), 0.05);
    }

    @Test
    public void setSustainSetsWithZero() {
        es.setSustain(1);
        es.setSustain(0);
        assertEquals(0, es.getSustain(), 0.05);
    }

    @Test
    public void setSustainFailsWithGreaterThanOne() {
        double defaultSustain = es.getSustain();
        es.setSustain(1.1);
        assertEquals(defaultSustain, es.getSustain(), 0.05);
    }

    @Test
    public void setSustainFailsWithNegative() {
        double defaultSustain = es.getSustain();
        es.setSustain(-0.1);
        assertEquals(defaultSustain, es.getSustain(), 0.05);
    }

    @Test
    public void setReleaseSetsWithPositive() {
        es.setRelease(1);
        assertEquals(1, es.getRelease());
    }

    @Test
    public void setReleaseSetsWithZero() {
        es.setRelease(1);
        es.setRelease(0);
        assertEquals(0, es.getRelease());
    }

    @Test
    public void setReleaseFailsWithNegative() {
        int defaultRelease = es.getRelease();
        es.setRelease(-1);
        assertEquals(defaultRelease, es.getRelease());
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
        es.setSustain(0.5);
        assertEquals(0.5, e.generateEnvelope(true), 0.05);
    }

    @Test
    public void generateEnvelopeReturnsZeroWithActiveNoteTrueAttackThousandOneInvocation() {
        es.setAttack(1000);
        double amplitude = 0;
        for (int i = 0; i < 1; i++) {
            amplitude = e.generateEnvelope(true);
        }
        assertEquals(0, amplitude, 0.05);
    }

    @Test
    public void generateEnvelopeReturnsHalfWithActiveNoteTrueAttackThousandHalfSampleRateInvocations() {
        es.setAttack(1000);
        double amplitude = 0;
        for (int i = 0; i < Settings.SAMPLE_RATE / 2; i++) {
            amplitude = e.generateEnvelope(true);
        }
        assertEquals(0.5, amplitude, 0.05);
    }

    @Test
    public void generateEnvelopeReturnsOneWithActiveNoteTrueAttackThousandSampleRateInvocations() {
        es.setAttack(1000);
        double amplitude = 0;
        for (int i = 0; i < Settings.SAMPLE_RATE; i++) {
            amplitude = e.generateEnvelope(true);
        }
        assertEquals(1, amplitude, 0.05);
    }

    @Test
    public void generateEnvelopeReturnsOneWithActiveNoteTrueDecayThousandOneInvocation() {
        es.setDecay(1000);
        double amplitude = 0;
        for (int i = 0; i < 1; i++) {
            amplitude = e.generateEnvelope(true);
        }
        assertEquals(1, amplitude, 0.05);
    }

    @Test
    public void generateEnvelopeReturnsOnePlusSustainDividedByTwoWithActiveNoteTrueDecayThousandHalfSampleRateInvocations() {
        es.setDecay(1000);
        es.setSustain(1); // Any valid sustain value
        double amplitude = 0;
        for (int i = 0; i < Settings.SAMPLE_RATE / 2; i++) {
            amplitude = e.generateEnvelope(true);
        }
        assertEquals((1 + es.getSustain()) / 2, amplitude, 0.05);
    }

    @Test
    public void generateEnvelopeReturnsSustainWithActiveNoteTrueDecayThousandSampleRateInvocations() {
        es.setDecay(1000);
        es.setSustain(0); // Any valid sustain value
        double amplitude = 0;
        for (int i = 0; i < Settings.SAMPLE_RATE; i++) {
            amplitude = e.generateEnvelope(true);
        }
        assertEquals(es.getSustain(), amplitude, 0.05);
    }

    @Test
    public void generateEnvelopeReturnsOneWithActiveNoteTrueAttackAndDecayThousandSampleRateInvocations() {
        es.setAttack(1000);
        es.setDecay(1000);
        es.setSustain(0);
        double amplitude = 0;
        for (int i = 0; i < Settings.SAMPLE_RATE; i++) {
            amplitude = e.generateEnvelope(true);
        }
        assertEquals(1, amplitude, 0.05);
    }

    @Test
    public void generateEnvelopeReturnsSustainWithActiveNoteTrueAttackAndDecayThousandTwiceSampleRateInvocations() {
        es.setAttack(1000);
        es.setDecay(1000);
        es.setSustain(0);
        double amplitude = 0;
        for (int i = 0; i < 2 * Settings.SAMPLE_RATE; i++) {
            amplitude = e.generateEnvelope(true);
        }
        assertEquals(es.getSustain(), amplitude, 0.05);
    }

    @Test
    public void generateEnvelopeReturnsOneWithActiveNoteFalseReleaseThousandOneInvocation() {
        es.setRelease(1000);
        double amplitude = 0;
        e.generateEnvelope(true);
        for (int i = 0; i < 1; i++) {
            amplitude = e.generateEnvelope(false);
        }
        assertEquals(1, amplitude, 0.05);
    }

    @Test
    public void generateEnvelopeReturnsSustainDividedByTwoWithActiveNoteFalseReleaseThousandHalfSampleRateInvocations() {
        es.setRelease(1000);
        es.setSustain(0.5); // Any valid sustain value
        double amplitude = 0;
        e.generateEnvelope(true);
        for (int i = 0; i < Settings.SAMPLE_RATE / 2; i++) {
            amplitude = e.generateEnvelope(false);
        }
        assertEquals(es.getSustain() / 2, amplitude, 0.05);
    }

    @Test
    public void generateEnvelopeReturnsZeroWithActiveNoteFalseReleaseThousandSampleRateInvocations() {
        es.setRelease(1000);
        es.setSustain(1); // Any valid sustain value
        double amplitude = 0;
        e.generateEnvelope(true);
        for (int i = 0; i < Settings.SAMPLE_RATE; i++) {
            amplitude = e.generateEnvelope(false);
        }
        assertEquals(0, amplitude, 0.05);
    }

    @Test
    public void generateEnvelopeGeneratesAnticipatedEnvelope() {
        es.setAttack(1000);
        es.setDecay(1000);
        es.setSustain(0.5);
        es.setRelease(1000);

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
        assertEquals((1 + es.getSustain()) / 2, amplitude, 0.05);

        for (int i = 0; i < Settings.SAMPLE_RATE / 2; i++) {
            amplitude = e.generateEnvelope(true);
        }
        assertEquals(es.getSustain(), amplitude, 0.05);

        for (int i = 0; i < Settings.SAMPLE_RATE / 2; i++) {
            amplitude = e.generateEnvelope(false);
        }
        assertEquals(es.getSustain() / 2, amplitude, 0.05);

        for (int i = 0; i < Settings.SAMPLE_RATE / 2; i++) {
            amplitude = e.generateEnvelope(false);
        }
        assertEquals(0, amplitude, 0.05);
    }
}
