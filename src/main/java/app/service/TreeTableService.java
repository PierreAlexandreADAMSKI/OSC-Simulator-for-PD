package app.service;

import app.OSCApp;
import javafx.event.Event;
import javafx.event.EventType;
import javafx.scene.control.TreeItem;
import javafx.scene.input.MouseEvent;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;

/**
 * app.service Created by Pierre-Alexandre Adamski on 02/02/2016.
 */


public class TreeTableService extends TreeItem<String> {

	public final static Path rootPath = Paths.get(OSCApp.class.getResource("sound").getPath());

	public static String getExtension(Path entry) {
		String fileName = entry.getFileName().toString();
		if (fileName.lastIndexOf(".")!=-1) {
			return fileName.substring(fileName.lastIndexOf("."));
		}else {
			return null;
		}
	}

	//this stores the full path to the file or directory

	private String fullPath;

	public String getFullPath() {
		return (this.fullPath);
	}


	private boolean isDirectory;

	public boolean isDirectory() {
		return (this.isDirectory);
	}


	public TreeTableService(Path file) {

		super(file.toString());

		this.fullPath = file.toString();

		//test if this is a directory and set the icon

		this.isDirectory = Files.isDirectory(file);

		//set the value

		if (!fullPath.endsWith(File.separator)) {
			//set the value (which is what is displayed in the tree)
			String value = file.toString();
			int indexOf = value.lastIndexOf(File.separator);
			if (indexOf > 0) {
				this.setValue(value.substring(indexOf + 1));
			} else {
				this.setValue(value);
			}
		}

		this.addEventHandler(TreeItem.branchExpandedEvent(), event1 -> {

			TreeTableService source = (TreeTableService) ((Event) event1).getSource();

			try {
				if (source.getChildren().isEmpty()) {
					Path path = Paths.get(source.getFullPath());
					BasicFileAttributes attribs = Files.readAttributes(path, BasicFileAttributes.class);
					if (attribs.isDirectory()) {
						DirectoryStream<Path> dir = Files.newDirectoryStream(path);
						for (Path file1 : dir) {
							if (this.getExtension(file1.getFileName()).equals("wav")) {
								TreeTableService treeNode = new TreeTableService(file1);
								source.getChildren().add(treeNode);
							}
						}
					}
				}
			} catch (IOException x) {
				x.printStackTrace();
			}
		});
	}
}

