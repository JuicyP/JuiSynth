/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package juisynth.logic;

import juisynth.logic.player.Settings;

/**
 * Note contains fields for enumerations of note name and accidental
 * and an octave with an integral value. Note also provides a method for
 * transforming note into frequency.
 * @author juicyp
 */
public class Note {

    public enum NoteName {
        A, B, C, D, E, F, G
    }

    public enum Accidental {
        FLAT, NEUTRAL, SHARP
    }

    private NoteName noteName;
    private Accidental accidental;
    private int octave;

    /**
     * Constructor for Note, user must provide the note name, octave and accidental.
     * @param noteName The name of the note in the English note naming convention.
     * @param octave The octave of the note.
     * @param accidental The accidental of the note.
     */
    public Note(NoteName noteName, int octave, Accidental accidental) {
        this.noteName = noteName;
        this.accidental = accidental;
        this.octave = octave;
    }

    /**
     * Constructor for Note with neutral accidental.
     * @param noteName The name of the note in the English note naming convention.
     * @param octave The octave of the note.
     */
    public Note(NoteName noteName, int octave) {
        this(noteName, octave, Accidental.NEUTRAL);
    }

    /**
     * Returns the frequency of the Note-object.
     * @return Frequency as calculated from the fields of the Note-object.
     */
    public double noteToFrequency() {
        double frequency = Settings.CONCERT_PITCH * Math.pow(2, octave - 4);
        int semitonesFromA;
        switch (noteName) {
            default:
            case A:
                semitonesFromA = 0;
                break;
            case B:
                semitonesFromA = 2;
                break;
            case C:
                semitonesFromA = -9;
                break;
            case D:
                semitonesFromA = -7;
                break;
            case E:
                semitonesFromA = -5;
                break;
            case F:
                semitonesFromA = -4;
                break;
            case G:
                semitonesFromA = -2;
                break;
        }

        switch (accidental) {
            default:
            case NEUTRAL:
                break;
            case SHARP:
                semitonesFromA++;
                break;
            case FLAT:
                semitonesFromA--;
                break;
        }

        return frequency * Math.pow(2, semitonesFromA / (double) 12);
    }

}
