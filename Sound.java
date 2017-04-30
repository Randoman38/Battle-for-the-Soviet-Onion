package net.rSoft.LD38;

import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Sound {
	public static Sound breakObj = loadSound("res/Sound/punch.wav");
	public static Sound jump = loadSound("res/Sound/Jumperoni.wav");
	public static Sound theme = loadSound("res/Sound/Intro.wav");
	public static Sound powerup = loadSound("res/Sound/Powerup.wav");
	public static Sound powerdown = loadSound("res/Sound/Powerdown.wav");
	public static Sound climb = loadSound("res/Sound/Climb.wav");
	public static Sound step = loadSound("res/Sound/Step.wav");
	public static Sound fire = loadSound("res/Sound/Fireball.wav");
	public static Sound enemHit = loadSound("res/Sound/EnemHit.wav");
	
	public static Sound loadSound(String fileName) {
		
		Sound sound = new Sound();
		try {
			AudioInputStream ais = AudioSystem.getAudioInputStream(new File(fileName));
			Clip clip = AudioSystem.getClip();
			clip.open(ais);
			sound.clip = clip;
		} catch (Exception e) {
			System.out.println(e);
		}
		return sound;
	}

	private Clip clip;

	public void play() {
		try {
			if (clip != null) {
				new Thread() {
					public void run() {
						synchronized (clip) {
							clip.stop();
							clip.setFramePosition(0);
							clip.start();
						}
					}
				}.start();
			}
		} catch (Exception e) {
			System.out.println(e);
		}
	}
}
