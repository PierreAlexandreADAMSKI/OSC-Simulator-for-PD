package app.view;

import app.OSCApp;
import app.exceptions.OSCReceiveException;
import app.exceptions.OSCSendException;
import app.service.OSCService;
import app.service.SoundService;
import app.service.StageService;
import app.service.Way;
import com.illposed.osc.OSCMessage;
import com.illposed.osc.OSCPort;
import com.illposed.osc.OSCPortIn;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.text.Text;
import javafx.util.Callback;

import java.io.IOException;
import java.util.*;

/**
 * app.app.view Created by Pierre-Alexandre Adamski on 30/01/2016.
 */
public class Controller {
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
	private TreeTableView<String> table;

	@FXML
	private void initialize() {
		portText.setText(String.valueOf(""));
		integerItem.setOnAction(event -> currentInstance = 0);
		floatItem.setOnAction(event -> currentInstance = 0.f);
		stringItem.setOnAction(event -> currentInstance = "");
		EventHandler<KeyEvent> eventHandler = event -> {
			if (Objects.equals(event.getCode(), KeyCode.ENTER)) {
				try {
					handleSendButton();
				} catch (OSCSendException | OSCReceiveException e) {
					e.printStackTrace();
				}
			}
		};
		tagField.setOnKeyPressed(eventHandler);
		arg0Field.setOnKeyPressed(eventHandler);
	}

	private void setTable(Map<String, List<String>> map) {
		final List<TreeItem<String>> rootList = new ArrayList<>();
		Set<Map.Entry<String, List<String>>> set = map.entrySet();
		for (Map.Entry<String, List<String>> roots : set) {
			final TreeItem<String> rootItem = new TreeItem<>(roots.getKey());
			rootItem.setExpanded(true);
			for (String root : roots.getValue()) {
				rootItem.getChildren().add(new TreeItem<>(root));
			}
			rootList.add(rootItem);
		}
		final TreeTableColumn<String, String> arguments = new TreeTableColumn<>("arguments");
		arguments.setCellValueFactory(p -> new ReadOnlyStringWrapper(p.getValue().getValue()));
		table.setRoot(rootList.get(0));
		table.getColumns().add(arguments);
	}

	private Object currentInstance;

	@FXML
	private void handleSendButton() throws OSCSendException, OSCReceiveException {
		try {
			if (currentInstance instanceof Integer) {
				portText.setText(String.valueOf(57110));
				Integer temp = Integer.valueOf(arg0Field.getText());
				OSCApp.serviceOut.sendMe(tagField.getText(), temp);
			}
			if (currentInstance instanceof Float) {
				portText.setText(String.valueOf(57110));
				Float temp = Float.valueOf(arg0Field.getText());
				OSCApp.serviceOut.sendMe(tagField.getText(), temp);
			}
		} catch (Throwable e) {
			throw new OSCSendException("OSCSendException :", e);
		}
		try {
			if (currentInstance instanceof String) {
				portText.setText(String.valueOf(57120));
				final OSCService oscServiceOut = new OSCService(57120, Way.OUT);
				oscServiceOut.sendMe(tagField.getText(), arg0Field.getText());
				OSCApp.serviceIn.receiveMe(tagField.getText());
			}
		} catch (Throwable e) {
			throw new OSCReceiveException("OSCReceiveException", e);
		}
	}

	@FXML
	private void handleResetButton() throws OSCSendException, OSCReceiveException {
		currentInstance = new Object();
		tagField.setText("");
		arg0Field.setText("");
		portText.setText("");
	}
}
