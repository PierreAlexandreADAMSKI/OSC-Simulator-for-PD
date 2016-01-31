package app.view;

import app.OSCApp;
import app.service.OSCService;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.text.Text;

import java.io.IOException;

/**
 * app.app.view Created by Pierre-Alexandre Adamski on 30/01/2016.
 */
public class Controller {
	@FXML
	Button sendButton;
	@FXML
	Button resetButton;
	@FXML
	Text portText;
	@FXML
	TextField tagField;
	@FXML
	TextField arg0Field;

	@FXML
	private void initialize(){
		portText.setText(String.valueOf(OSCService.UDP_PORT));
	}

	@FXML
	private void handleSendButton(){
		try {
			OSCApp.oscService.sendMe(tagField.getText(), Integer.valueOf(arg0Field.getText()));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
