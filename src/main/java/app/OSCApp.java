package app;

import app.service.OSCService;
import app.service.StageService;
import app.service.ViewService;
import com.illposed.osc.OSCPort;
import javafx.application.Application;
import javafx.stage.Stage;

import java.net.InetAddress;

/**
 * app Created by Pierre-Alexandre Adamski on 30/01/2016.
 */
public class OSCApp extends Application {

	public static OSCService oscService;


	public static void main(String[] args) throws Exception {
		//localHost on default OSC port 
		oscService = new OSCService(OSCPort.DEFAULT_SC_OSC_PORT);
		launch(args); // for javaFx
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		StageService.getInstance().setPrimaryStage(primaryStage);
		StageService.getInstance().getPrimaryStage().setTitle("OSC Test Simulator");
		ViewService.showPrimary(OSCApp.class.getResource("view/HomeView.fxml"));
	}
}
