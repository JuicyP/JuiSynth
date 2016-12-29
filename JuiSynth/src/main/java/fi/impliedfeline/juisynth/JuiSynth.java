/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.impliedfeline.juisynth;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.SourceDataLine;
import javax.sound.sampled.LineUnavailableException;
import java.util.Arrays;

/**
 *
 * @author juicyp
 */
public class JuiSynth {

    public static void main(String[] args) {

        // Prototype modeled after Dr. Dobb's synth articles 
        // http://www.drdobbs.com/jvm/creating-music-components-in-java/229700113?pgno=1
        // TODO: Refactor everything
        int sampleRate = 44100; // 44.1kHz is a sample rate with fairly high fidelity

        // The constuctor of AudioFormat takes the sample rate, sample resolution in bits,
        // amount of channels, signedness (true for signed),
        // endianness (true for big endian) in that order as format information.
        AudioFormat format = new AudioFormat(sampleRate, 16, 1, true, true);
        DataLine.Info info = new DataLine.Info(SourceDataLine.class, format);
        SourceDataLine audioline = null;

        int bufferSize = 1000;
        // 16-bit samples so two indices of a byte array represent a single sample.
        // Thus, amount of samples per buffer is half the size of the array.
        int samplesPerBuffer = bufferSize / 2;
        byte[] sampleBuffer = new byte[bufferSize];
        Arrays.fill(sampleBuffer, (byte) 0);

        try {

            audioline = (SourceDataLine) AudioSystem.getLine(info);
            audioline.open(format);
            audioline.start();

            // Game loop :)
            int timer = 1000;
            long lastTime = System.currentTimeMillis();

            while (timer > 0) {
                generateSineIntoBuffer(sampleBuffer, sampleRate, 440, samplesPerBuffer);
                audioline.write(sampleBuffer, 0, bufferSize);
                timer -= (System.currentTimeMillis() - lastTime);
            }

        } catch (LineUnavailableException e) {

        } finally {

            audioline.drain();
            audioline.close();

        }
    }

    // Calculate the y value of a sine wave at frequency for each discrete point x,
    // and put into buffer.
    public static void generateSineIntoBuffer(byte[] sampleBuffer, int sampleRate, double frequency, int samplesPerBuffer) {

        long sampleIndex = 0;
        long samplesInPeriod = (long) (sampleRate / frequency);

        int index = 0;

        for (int i = 0; i < samplesPerBuffer; i++) {

            double x = sampleIndex / (double) samplesInPeriod;
            double value = Math.sin(2.0 * Math.PI * x);
            sampleIndex = (sampleIndex + 1) % samplesInPeriod;

            double ds = value * Short.MAX_VALUE;
            short ss = (short) Math.round(ds);
            // Big endian, shift first eight bits and add as first part of sample
            sampleBuffer[index++] = (byte) (ss >> 8);
            sampleBuffer[index++] = (byte) (ss & 0xFF);
        }
    }
}
