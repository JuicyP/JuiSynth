/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.impliedfeline.juisynth;

import fi.impliedfeline.juisynth.logic.*;
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

        // BEWARE! Software is LOUD
        
        // Prototype modeled after Dr. Dobb's synth articles 
        // http://www.drdobbs.com/jvm/creating-music-components-in-java/229700113?pgno=1
        // Testing
        int sampleRate = 44100; // 44.1kHz is a sample rate with fairly high fidelity

        // The constuctor of AudioFormat takes the sample rate, sample resolution in bits,
        // amount of channels, signedness (true for signed),
        // endianness (true for big endian) in that order as format information.
        AudioFormat format = new AudioFormat(sampleRate, 16, 1, true, true);
        DataLine.Info info = new DataLine.Info(SourceDataLine.class, format);
        SourceDataLine audioline = null;


        // Larger buffer less suspectible to system running slow
        // Working on a laptop from the last decade
        int bufferSize = 10000;
        // 16-bit samples so two indices of a byte array represent a single sample.
        // Thus, amount of samples per buffer is half the size of the array.
        int samplesPerBuffer = bufferSize / 2;
        byte[] sampleBuffer = new byte[bufferSize];
        Arrays.fill(sampleBuffer, (byte) 0);

        try {

            audioline = (SourceDataLine) AudioSystem.getLine(info);
            audioline.open(format);
            audioline.start();

            // Fetching system time on each iteration too slow,
            // distorted signal. Reducing a number on each iteration instead,
            // writing the amount of buffers on variable
            int bufferCount = 10;

            while (bufferCount > 0) {
                generateWaveIntoBuffer(sampleBuffer, sampleRate, 440, samplesPerBuffer);
                audioline.write(sampleBuffer, 0, bufferSize);
                bufferCount -= 1;
            }

        } catch (LineUnavailableException e) {

        } finally {

            audioline.drain();
            audioline.close();

        }
    }

    // Calculate the y value of a sine wave at frequency for each discrete point x,
    // and put into buffer.
    public static void generateWaveIntoBuffer(byte[] sampleBuffer, int sampleRate, double frequency, int samplesPerBuffer) {

        Oscillator oscillator = new Oscillator();
        oscillator.setWaveform(Oscillator.Waveform.TRI);
        int index = 0;

        for (int i = 0; i < samplesPerBuffer; i++) {

            // Maybe just use same SignalStatus instance for successive sample fetches?
            // Less memory garbage
            SignalStatus signal = new SignalStatus(sampleRate, i, frequency);
            oscillator.generateSample(signal);

            double ds = signal.getAmplitude() * Short.MAX_VALUE;
            short ss = (short) Math.round(ds);
            // Big endian, shift first eight bits and add as first part of sample
            sampleBuffer[index++] = (byte) (ss >> 8);
            sampleBuffer[index++] = (byte) (ss & 0xFF);
        }
    }
}
