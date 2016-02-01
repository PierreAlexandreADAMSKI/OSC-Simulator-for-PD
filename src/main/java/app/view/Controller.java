package app.view;

import app.OSCApp;
import app.service.OSCService;
import app.service.SoundService;
import app.service.Way;
import com.illposed.osc.OSCMessage;
import com.illposed.osc.OSCPort;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import java.io.IOException;

/**
 * app.app.view Created by Pierre-Alexandre Adamski on 30/01/2016.
 */
public class Controller {
	@FXML
	private Button sendButton;
	@FXML
	private Button resetButton;
	@FXML
	private Text portText;
	@FXML
	private TextField tagField;
	@FXML
	private TextField arg0Field;
	@FXML
	private MenuItem integerItem;
	@FXML
	private MenuItem floatItem;
	@FXML
	private MenuItem stringItem;


	@FXML
	private void initialize(){
		portText.setText(String.valueOf(OSCService.UDP_PORT));
		integerItem.setOnAction(event -> currentInstance = 0);
		floatItem.setOnAction(event -> currentInstance = 0.f);
		stringItem.setOnAction(event -> currentInstance = "");
	}

	private Object currentInstance;

	@FXML
	private void handleSendButton(){
		try {
			if (currentInstance instanceof Integer){
				currentInstance = Integer.valueOf(arg0Field.getText());
				OSCApp.serviceOut.sendMe(tagField.getText(), currentInstance);
			}
			if (currentInstance instanceof Float){
				currentInstance = Float.valueOf(arg0Field.getText());
				OSCApp.serviceOut.sendMe(tagField.getText(), currentInstance);
			}
			if (currentInstance instanceof String){
				final OSCService oscServiceOut = new OSCService(57120, Way.OUT);
				oscServiceOut.sendMe(tagField.getText(), arg0Field.getText());
				OSCApp.serviceIn.receiveMe(tagField.getText());
			}
		} catch (Throwable throwable) {
			throwable.printStackTrace();
		}
	}
}
