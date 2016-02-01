package app.service;

import app.OSCApp;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;

public class ViewService {

	public static void showPrimary(URL url) { //create Exceptions!!!
		try {
			final FXMLLoader loader = new FXMLLoader(url);
			final AnchorPane anchorPane = loader.load();
			final Scene scene = new Scene(anchorPane);
			scene.getStylesheets().add(OSCApp.class.getResource("view/style.css").toExternalForm());
			StageService.getInstance().getPrimaryStage().setScene(scene);
			StageService.getInstance().getPrimaryStage().show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}