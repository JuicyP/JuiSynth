/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package juisynth.logic;

import juisynth.logic.player.Settings;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author juicyp
 */
public class NoteTest {

    private Note n;

    public NoteTest() {
    }

    @Before
    public void setUp() {
        n = new Note(Note.NoteName.A, 4);
    }

    @Test
    public void noteToFrequencyReturnsConcertPitchWithA4() {
        assertEquals(Settings.CONCERT_PITCH, n.noteToFrequency(), 0.05);
    }
    
    @Test
    public void noteToFrequencyReturnsFrequencyTwoSemitonesHigherWithB4() {
        n = new Note(Note.NoteName.B, 4);
        assertEquals(Settings.CONCERT_PITCH * Math.pow(2, 2 / (double) 12), n.noteToFrequency(), 0.05);
    }

    @Test
    public void noteToFrequencyReturnsFrequencyNineSemitonesLowerWithC4() {
        n = new Note(Note.NoteName.C, 4);
        assertEquals(Settings.CONCERT_PITCH * Math.pow(2, -9 / (double) 12), n.noteToFrequency(), 0.05);
    }
    
    @Test
    public void noteToFrequencyReturnsFrequencySevenSemitonesLowerWithD4() {
        n = new Note(Note.NoteName.D, 4);
        assertEquals(Settings.CONCERT_PITCH * Math.pow(2, -7 / (double) 12), n.noteToFrequency(), 0.05);
    }
    
    @Test
    public void noteToFrequencyReturnsFrequencyFiveSemitonesLowerWithE4() {
        n = new Note(Note.NoteName.E, 4);
        assertEquals(Settings.CONCERT_PITCH * Math.pow(2, -5 / (double) 12), n.noteToFrequency(), 0.05);
    }
    
    @Test
    public void noteToFrequencyReturnsFrequencyFourSemitonesLowerWithF4() {
        n = new Note(Note.NoteName.F, 4);
        assertEquals(Settings.CONCERT_PITCH * Math.pow(2, -4 / (double) 12), n.noteToFrequency(), 0.05);
    }
    
    @Test
    public void noteToFrequencyReturnsFrequencyTwoSemitonesLowerWithG4() {
        n = new Note(Note.NoteName.G, 4);
        assertEquals(Settings.CONCERT_PITCH * Math.pow(2, -2 / (double) 12), n.noteToFrequency(), 0.05);
    }
    
    @Test
    public void noteToFrequencyReturnsFrequencyOneSemitoneHigherWithSharp() {
        n = new Note(Note.NoteName.A, 4, Note.Accidental.SHARP);
        assertEquals(Settings.CONCERT_PITCH * Math.pow(2, 1 / (double) 12), n.noteToFrequency(), 0.05);
    }
    
    @Test
    public void noteToFrequencyReturnsFrequencyOneSemitoneLowerWithFlat() {
        n = new Note(Note.NoteName.A, 4, Note.Accidental.FLAT);
        assertEquals(Settings.CONCERT_PITCH * Math.pow(2, -1 / (double) 12), n.noteToFrequency(), 0.05);
    }
    
    @Test
    public void noteToFrequencyReturnsFrequencyOneOctaveHigherWithA5() {
        n = new Note(Note.NoteName.A, 5);
        assertEquals(Settings.CONCERT_PITCH * 2, n.noteToFrequency(), 0.05);
    }
    
    @Test
    public void noteToFrequencyReturnsFrequencyOneOctaveLowerWithA3() {
        n = new Note(Note.NoteName.A, 3);
        assertEquals(Settings.CONCERT_PITCH / 2, n.noteToFrequency(), 0.05);
    }
}
