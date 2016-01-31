package app.service;

import com.illposed.osc.OSCPort;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

public class StageService {

	private StageService() {
	}

	private static class StageServiceHolder {
		private static final StageService INSTANCE = new StageService();
	}

	public static StageService getInstance() {
		return StageServiceHolder.INSTANCE;
	}

	private Stage primaryStage;

	public Stage getPrimaryStage() {
		return primaryStage;
	}

	public void setPrimaryStage(Stage primaryStage) {
		this.primaryStage = primaryStage;
	}


	public static void warning(Warning warning) {
		Alert alert = new Alert(Alert.AlertType.WARNING);
		alert.initOwner(StageServiceHolder.INSTANCE.primaryStage);
		if (warning == Warning.PORT_UNDETEDCTED) {
			alert.setTitle("UPD Port Warning");
			alert.setContentText("UDP Port for OSC has not been recognized, \n" +
					"You may change Port :" + OSCService.UDP_PORT + " to default Port :" + OSCPort.DEFAULT_SC_OSC_PORT);
		}
		alert.showAndWait();
	}

}
