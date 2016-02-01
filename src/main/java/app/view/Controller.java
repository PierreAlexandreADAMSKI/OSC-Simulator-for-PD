package app.view;

import app.OSCApp;
import app.service.OSCService;
import app.service.SoundService;
import com.illposed.osc.OSCMessage;
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
				OSCApp.oscServiceOut.sendMe(tagField.getText(), currentInstance);
			}
			if (currentInstance instanceof Float){
				currentInstance = Float.valueOf(arg0Field.getText());
				OSCApp.oscServiceOut.sendMe(tagField.getText(), currentInstance);
			}
			if (currentInstance instanceof String){
				OSCApp.oscServiceOut.sendMe(tagField.getText(), arg0Field.getText());
				OSCApp.oscServiceIn.receiveMe(tagField.getText());
				/*Thread play = new Thread( new SoundService((String) message.getArguments().get(0)) );
				play.setDaemon(true);
				play.start();*/
			}
		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
		}
	}
}
