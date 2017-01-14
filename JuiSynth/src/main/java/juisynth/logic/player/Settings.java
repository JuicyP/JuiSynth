/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package juisynth.logic.player;

/**
 *
 * @author juicyp
 */
public final class Settings {

    private Settings() {
    }

    public static final int SAMPLE_RATE = 88200;
    public static final int SAMPLE_SIZE = 16;
    public static final int CHANNELS = 1;
    
    public static final boolean SIGNED = true;
    public static final boolean BIG_ENDIAN = true;
    
    public static final int BUFFER_SIZE = 200;
    // 16-bit samples so two indices of a byte array represent a single sample.
    // Thus, amount of samples per buffer is half the size of the array.
    public static final int SAMPLES_PER_BUFFER = BUFFER_SIZE / 2;
    
    public static final double CONCERT_PITCH = 440;
    
}
