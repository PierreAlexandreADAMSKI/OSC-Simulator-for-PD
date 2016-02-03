package app.service;

import app.OSCApp;
import app.exceptions.AudioException;
import javafx.concurrent.Task;

import javax.sound.sampled.*;
import java.io.IOException;

/**
 * app.service Created by Pierre-Alexandre Adamski on 31/01/2016.
 */
public class SoundService extends Task<Void> {

	private String name;
	public static long sampleLenght;

	public SoundService(String name) {
		this.name = name;
	}

	@Override
	protected Void call() throws AudioException {
		AudioInputStream audioIn = null;
		try {
			audioIn = AudioSystem.getAudioInputStream(OSCApp.class.getResource("sound/" + this.name + ".wav"));
			Clip clip = AudioSystem.getClip();
			clip.open(audioIn);
			sampleLenght = clip.getMicrosecondLength();
			clip.start();
		} catch (UnsupportedAudioFileException e) {
			throw new AudioException("Audio file was not found :", e);
		} catch (IOException e) {
			throw  new AudioException("AudioException :", e);
		} catch (LineUnavailableException e) {
			throw new AudioException("AudioSystem.getClip() failed :", e);
		}
		return null;
	}
}
