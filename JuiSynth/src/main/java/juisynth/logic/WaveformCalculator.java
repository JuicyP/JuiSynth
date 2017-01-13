/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package juisynth.logic;

import java.util.Random;

/**
 * Contains calculations relevant to waves.
 * @author juicyp
 */
public class WaveformCalculator {
    
    private static final Random NOISE_GENERATOR = new Random();

    /**
     * Calculates the y position of a wave based on the waveform and the position x.
     * @param x The x position of a wave.
     * @param waveform Specified waveform.
     * @return returns y position of a wave.
     */
    public static double calculateWaveformY(double x, Waveform waveform) {

        double y;
        
        if (x > 1 || x < 0) {
            return 0;
        }

        switch (waveform) {
            default:
            case SIN:
                y = Math.sin(2.0 * Math.PI * x);
                break;

            case SQU:
                if (x < 0.5) {
                    y = 1.0;
                } else {
                    y = -1.0;
                }
                break;

            case SAW:
                y = 2.0 * (x - Math.floor(x + 0.5));
                break;

            case TRI:
                y = Math.abs((4 * x + 3) % 4 - 2) - 1;
                break;

            case NOI:
                y = 2 * NOISE_GENERATOR.nextDouble() - 1;
        }
        return y;
    }
    
}
