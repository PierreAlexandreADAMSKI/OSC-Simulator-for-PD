package app.service;

import app.OSCApp;
import app.exceptions.UIException;
import com.aquafx_project.AquaFx;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;

public class ViewService {

	public static void showPrimary(URL url) throws UIException{ //create Exceptions!!!
		try {
			final FXMLLoader loader = new FXMLLoader(url);
			final AnchorPane anchorPane = loader.load();
			final Scene scene = new Scene(anchorPane);
			scene.getStylesheets().add(OSCApp.class.getResource("view/style.css").toExternalForm());
			//AquaFx.style();
			StageService.getInstance().getPrimaryStage().setScene(scene);
			StageService.getInstance().getPrimaryStage().show();
		} catch (IOException e) {
			throw new UIException("UIException :\nCheck resources paths", e);
		}
	}

}
