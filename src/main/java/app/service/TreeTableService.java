package app.service;

import app.OSCApp;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * app.service Created by Pierre-Alexandre Adamski on 02/02/2016.
 */
public class TreeTableService {
	private final Path root = Paths.get(OSCApp.class.getResource("sound").getPath());

	public Map<String, List<String>> getWavFolders() {
		Map<String, List<String>> wavFolders = new HashMap<>();
		try {
			DirectoryStream<Path> stream = Files.newDirectoryStream(root);
			for (Path entry : stream) {
				if (entry.getNameCount() > 0){
					final DirectoryStream<Path> subStream = Files.newDirectoryStream(entry);
					final List<String> files = new ArrayList<>();
					for (Path subEntry : subStream){
						if (getExtension(subEntry).equals(".wav")){
							files.add(subEntry.getFileName().toString());
						}
					}
					if (!files.isEmpty()){
						wavFolders.put(entry.getFileName().toString(), files);
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return wavFolders;
	}


	protected String getExtension(Path entry) {
		String fileName = entry.getFileName().toString();
		return fileName.substring(fileName.lastIndexOf("."));
	}
}
