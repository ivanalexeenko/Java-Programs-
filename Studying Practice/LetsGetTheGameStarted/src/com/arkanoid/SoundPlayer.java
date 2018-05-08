package com.arkanoid;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;

/**
 * Created by LENOVO on 25.04.2018.
 */
public class SoundPlayer {

    public static final String BRICK_HIT_SOUND = "src/com/arkanoid/sounds/brick_hit_sound.wav";
    public static final String MINUS_LIFE_SOUND = "src/com/arkanoid/sounds/minus_life_sound.wav";
    public static final String NAVIGATION_SOUND = "src/com/arkanoid/sounds/navigation_sound.wav";
    public static final String PLATFORM_HIT_SOUND = "src/com/arkanoid/sounds/platform_hit_sound.wav";
    public static final String WALL_HIT_SOUND = "src/com/arkanoid/sounds/wall_hit_sound.wav";
    public static final String WIN_SOUND = "src/com/arkanoid/sounds/win_sound.wav";

    public static final String LOSE_SOUND = "src/com/arkanoid/sounds/lose_sound.wav";


    public void playSound(String soundName)
    {
        try
        {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(soundName).getAbsoluteFile());
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
        }
        catch(Exception ex)
        {
            System.out.println("Error with playing sound.");
            ex.printStackTrace( );
        }
    }
}
