package app;

import app.service.OSCService;
import app.service.StageService;
import app.service.ViewService;
import app.service.Way;
import com.illposed.osc.OSCPort;
import javafx.application.Application;
import javafx.stage.Stage;
import oscP5.OscIn;
import oscP5.OscP5;

/**
 * app Created by Pierre-Alexandre Adamski on 30/01/2016.
 */
public class OSCApp extends Application {

	public static OSCService oscServiceOut;
	public static OSCService oscServiceIn;


	public static void main(String[] args) throws Exception {
		//localHost on default OSC port 
		oscServiceOut = new OSCService(OSCPort.DEFAULT_SC_OSC_PORT, Way.OUT);
		oscServiceIn = new OSCService(OSCPort.DEFAULT_SC_OSC_PORT, Way.IN);

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
