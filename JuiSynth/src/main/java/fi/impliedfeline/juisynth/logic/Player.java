/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.impliedfeline.juisynth.logic;

import java.util.Arrays;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;

/**
 *
 * @author juicyp
 */
public class Player extends Thread {

    private static final int SAMPLE_RATE = 88200;
    private static final int SAMPLE_SIZE = 16;
    private static final int CHANNELS = 1;
    private static final boolean SIGNED = true;
    private static final boolean BIG_ENDIAN = true;

    private static final int BUFFER_SIZE = 200;
    // 16-bit samples so two indices of a byte array represent a single sample.
    // Thus, amount of samples per buffer is half the size of the array.
    private static final int SAMPLES_PER_BUFFER = BUFFER_SIZE / 2;

    private AudioFormat format;
    private DataLine.Info info;
    private SourceDataLine audioline;

    private boolean done;
    private byte[] sampleBuffer = new byte[BUFFER_SIZE];
    private SignalSource signalSource;

    public Player() {
        // The constuctor of AudioFormat takes the sample rate, sample resolution in bits,
        // amount of channels, signedness (true for signed),
        // endianness (true for big endian) in that order as format information.
        format = new AudioFormat(SAMPLE_RATE, SAMPLE_SIZE, CHANNELS, SIGNED, BIG_ENDIAN);
        info = new DataLine.Info(SourceDataLine.class, format);
    }

    @Override
    public void run() {

        done = false;

        try {

            audioline = (SourceDataLine) AudioSystem.getLine(info);
            audioline.open(format);
            audioline.start();
            int bufferIndex = 0;

            while (!done) {

                
                Arrays.fill(sampleBuffer, (byte) 0);

                int index = 0;

                for (int i = 0; i < SAMPLES_PER_BUFFER; i++) {

                    // Maybe just use same SignalStatus instance for successive sample fetches?
                    // Less memory garbage
                    SignalStatus signal = new SignalStatus(SAMPLE_RATE, bufferIndex, 440);
                    bufferIndex++;
                    signalSource.generateSample(signal);

                    double ds = signal.getAmplitude() * Short.MAX_VALUE;
                    short ss = (short) Math.round(ds);
                    // Big endian, shift first eight bits and add as first part of sample
                    sampleBuffer[index++] = (byte) (ss >> 8);
                    sampleBuffer[index++] = (byte) (ss & 0xFF);
                }
                
                audioline.write(sampleBuffer, 0, BUFFER_SIZE);

            }

        } catch (LineUnavailableException e) {

        } finally {

            audioline.drain();
            audioline.close();

        }

    }

    public void startPlayer() {
        if (signalSource != null) {
            start();
        }
    }

    public void stopPlayer() {
        done = true;
    }

    public void setSignalSource(SignalSource signalSource) {
        this.signalSource = signalSource;
    }
}
