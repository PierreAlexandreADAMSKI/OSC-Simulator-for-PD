package app;

import app.exceptions.OSCPortException;
import app.service.OSCService;
import app.service.StageService;
import app.service.ViewService;
import app.service.Way;
import com.illposed.osc.OSCPort;
import javafx.application.Application;
import javafx.stage.Stage;


/**
 * app Created by Pierre-Alexandre Adamski on 30/01/2016.
 */
public class OSCApp extends Application {

	public static OSCService serviceOut;
	public static OSCService serviceIn;

	public static void main(String[] args) throws OSCPortException {
		try {
			serviceOut = new OSCService(OSCPort.DEFAULT_SC_OSC_PORT, Way.OUT);
			serviceIn = new OSCService(57120, Way.IN);
		} catch (Throwable throwable) {
			throw new OSCPortException("OSCPortException", throwable);
		}
		//TODO try oscp5 it might be more stable
		launch(args); // for javaFx
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		StageService.getInstance().setPrimaryStage(primaryStage);
		StageService.getInstance().getPrimaryStage().setTitle("OSC Test Simulator");
		ViewService.showPrimary(OSCApp.class.getResource("view/HomeView.fxml"));
	}
}
