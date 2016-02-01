package app.service;

import app.OSCApp;
import javafx.concurrent.Task;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

/**
 * app.service Created by Pierre-Alexandre Adamski on 31/01/2016.
 */
public class SoundService extends Task<Void>{

	private String name;
	public static long sampleLenght;

	public SoundService(String name) {
		this.name = name;
	}

	@Override
	protected Void call() throws Exception {
		AudioInputStream audioIn = AudioSystem.getAudioInputStream(OSCApp.class.getResource("service/" + this.name + ".wav"));
		Clip clip = AudioSystem.getClip();
		clip.open(audioIn);
		sampleLenght = clip.getMicrosecondLength();
		clip.start();
		return null;
	}
}
