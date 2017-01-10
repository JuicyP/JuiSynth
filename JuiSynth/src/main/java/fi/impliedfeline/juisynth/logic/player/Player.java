/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.impliedfeline.juisynth.logic.player;

import fi.impliedfeline.juisynth.logic.signal.SignalStatus;
import fi.impliedfeline.juisynth.logic.signal.SignalSource;
import java.util.Arrays;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;
import javax.swing.SwingWorker;

/**
 * Starts a thread and writes sample data fetched from SignalSource into
 * SourceDataLine.
 *
 * @author juicyp
 */
public class Player {

    private AudioFormat format;
    private DataLine.Info info;
    private SourceDataLine audioline;

    private byte[] sampleBuffer = new byte[Settings.BUFFER_SIZE];
    private SignalSource signalSource;
    private int bufferIndex;
    private double frequency = 440;

    private SwingWorker worker;

    /**
     * Constructor for Player sets format according to values specified in
     * fields.
     */
    public Player() {
        // The constuctor of AudioFormat takes the sample rate, sample resolution in bits,
        // amount of channels, signedness (true for signed),
        // endianness (true for big endian) in that order as format information.
        format = new AudioFormat(Settings.SAMPLE_RATE, Settings.SAMPLE_SIZE,
                                Settings.CHANNELS, Settings.SIGNED, Settings.BIG_ENDIAN);
        info = new DataLine.Info(SourceDataLine.class, format);
    }

    /**
     * Starts the player on a new thread.
     *
     * @see stopPlayer
     */
    public void startPlayer() {
        if (signalSource != null && worker == null) {
            worker = new SwingWorker<Void, Void>() {

                private boolean done;

                @Override
                protected Void doInBackground() {

                    done = false;

                    try {
                        audioline = (SourceDataLine) AudioSystem.getLine(info);
                        audioline.open(format);
                        audioline.start();
                        bufferIndex = 0;

                        while (!done) {
                            writeBuffer();
                        }

                    } catch (LineUnavailableException e) {

                    } finally {
                        audioline.drain();
                        audioline.close();
                    }
                    return null;
                }

                @Override
                protected void done() {
                    done = true;
                }
            };
            worker.execute();
        }
    }

    /**
     * Sets a flag to kill the thread.
     *
     * @see startPlayer
     */
    public void stopPlayer() {
        if (worker != null) {
            worker.cancel(true);
            worker = null;
        }
    }

    public void setFrequency(double frequency) {
        this.frequency = frequency;
    }

    public void setSignalSource(SignalSource signalSource) {
        this.signalSource = signalSource;
    }

    private void writeBuffer() {

        Arrays.fill(sampleBuffer, (byte) 0);

        int index = 0;
        
        for (int i = 0; i < Settings.SAMPLES_PER_BUFFER; i++) {

            // Maybe just use same SignalStatus instance for successive sample fetches?
            // Less memory garbage
            
            SignalStatus signal = new SignalStatus(bufferIndex++, frequency);
            signalSource.generateSample(signal);

            double ds = signal.getAmplitude() * Short.MAX_VALUE;
            short ss = (short) Math.round(ds);
            // Big endian, shift first eight bits and add as first part of sample
            sampleBuffer[index++] = (byte) (ss >> 8);
            sampleBuffer[index++] = (byte) (ss & 0xFF);

        }

        audioline.write(sampleBuffer, 0, Settings.BUFFER_SIZE);
    }
}
