package app.service;

import app.OSCApp;
import app.exceptions.AudioException;
import javafx.concurrent.Task;

import javax.sound.sampled.*;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.regex.Pattern;

/**
 * app.service Created by Pierre-Alexandre Adamski on 31/01/2016.
 */
public class SoundService extends Task<Void> {

	private String name;
	public static long sampleLenght;

	public SoundService(String name) {
		this.name = name;
	}

	private Path foundPath(String fileName) {
		try {
			return throughDirectory(fileName, Files.newDirectoryStream(TreeTableService.rootPath));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	private Path throughDirectory(String fileName, DirectoryStream<Path> subStream) throws IOException {
		for (Path file : subStream) {
			if (Files.isDirectory(file)) {
				Path actualDirectory = throughDirectory(fileName, Files.newDirectoryStream(file));
				if (actualDirectory != null){
					return actualDirectory;
				}
			} else if (file.getFileName().toString().split(Pattern.quote("."))[0].equals(fileName)) {
				return file;
			}
		}
		return null;
	}

	@Override
	protected Void call() throws AudioException {
		AudioInputStream audioIn;
		try {
			final Path soundPath = foundPath(this.name);
			if (soundPath != null) {
				final String soundPathToString = soundPath.subpath(10, soundPath.getNameCount()).toString();
				audioIn = AudioSystem.getAudioInputStream(OSCApp.class.getResource(soundPathToString));
				Clip clip = AudioSystem.getClip();
				clip.open(audioIn);
				sampleLenght = clip.getMicrosecondLength();
				clip.start();
			}
		} catch (UnsupportedAudioFileException e) {
			throw new AudioException("Audio file was not found :", e);
		} catch (IOException e) {
			throw new AudioException("AudioException :", e);
		} catch (LineUnavailableException e) {
			throw new AudioException("AudioSystem.getClip() failed :", e);
		}
		return null;
	}
}
