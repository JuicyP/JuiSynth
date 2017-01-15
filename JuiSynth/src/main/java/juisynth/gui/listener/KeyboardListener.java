/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package juisynth.gui.listener;

import java.awt.KeyEventDispatcher;
import java.awt.event.KeyEvent;
import juisynth.logic.Note;
import juisynth.logic.player.Player;

/**
 * Implements a KeyEventDispatcher listening to the key strokes.
 * Sends note information to the Player.
 * @author juicyp
 */
public class KeyboardListener implements KeyEventDispatcher {

    private Player player;

    /**
     * The constructor for the KeyboardListener, sets the Player field.
     * @param player The player to be sent note information to.
     */
    public KeyboardListener(Player player) {
        this.player = player;
    }

    // Here there be monsters
    private void keyTyped(KeyEvent ke) {
        Note note;
        char key = ke.getKeyChar();
        switch (key) {
            case 'p':
                note = new Note(Note.NoteName.C, 6);
                break;
            case '0':
                note = new Note(Note.NoteName.C, 6, Note.Accidental.FLAT);
                break;
            case 'o':
                note = new Note(Note.NoteName.B, 5);
                break;
            case '9':
                note = new Note(Note.NoteName.B, 5, Note.Accidental.FLAT);
                break;
            case 'i':
                note = new Note(Note.NoteName.A, 5);
                break;
            case '8':
                note = new Note(Note.NoteName.A, 5, Note.Accidental.FLAT);
                break;
            case 'u':
                note = new Note(Note.NoteName.G, 5);
                break;
            case '7':
                note = new Note(Note.NoteName.G, 5, Note.Accidental.FLAT);
                break;
            case 'y':
                note = new Note(Note.NoteName.F, 5);
                break;
            case '6':
                note = new Note(Note.NoteName.F, 5, Note.Accidental.FLAT);
                break;
            case 't':
                note = new Note(Note.NoteName.E, 5);
                break;
            case '5':
                note = new Note(Note.NoteName.E, 5, Note.Accidental.FLAT);
                break;
            case 'r':
                note = new Note(Note.NoteName.D, 5);
                break;
            case '4':
                note = new Note(Note.NoteName.D, 5, Note.Accidental.FLAT);
                break;
            case 'e':
                note = new Note(Note.NoteName.C, 5);
                break;
            case '3':
                note = new Note(Note.NoteName.C, 5, Note.Accidental.FLAT);
                break;
            case 'w':
                note = new Note(Note.NoteName.B, 4);
                break;
            case '2':
                note = new Note(Note.NoteName.B, 4, Note.Accidental.FLAT);
                break;
            case 'q':
                 note = new Note(Note.NoteName.A, 4);
                break;
            case '1':
                note = new Note(Note.NoteName.A, 4, Note.Accidental.FLAT);
                break;
            default:
                note = new Note(Note.NoteName.A, 4);
                break;
        }
         player.setFrequency(note.noteToFrequency());
         player.setActiveNote(true);
    }

    private void keyPressed(KeyEvent ke) {
        player.setActiveNote(true);
    }

    private void keyReleased(KeyEvent ke) {
        player.setActiveNote(false);
    }

    @Override
    public boolean dispatchKeyEvent(KeyEvent ke) {
        if (ke.getID() == KeyEvent.KEY_PRESSED) {
            keyPressed(ke);
        } else if (ke.getID() == KeyEvent.KEY_RELEASED) {
            keyReleased(ke);
        } else if (ke.getID() == KeyEvent.KEY_TYPED) {
            keyTyped(ke);
        }
        return true;
    }

}
