package app.view;

import app.OSCApp;
import app.exceptions.OSCReceiveException;
import app.exceptions.OSCSendException;
import app.service.*;
import app.service.TreeTableService;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
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
	private VBox treeBox;

	private TreeItem<String> rootNode = new TreeItem<>("samples");


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
		setupTree();
	}

	private void setupTree() {
		TreeView<String> treeView;

		treeBox.setPadding(new Insets(5, 5, 5, 5));
		treeBox.setSpacing(5);

		try {
			setupRootNode(Files.newDirectoryStream(TreeTableService.rootPath), this.rootNode);
		} catch (IOException e) {
			e.printStackTrace();
		}

		this.rootNode.setExpanded(true);

		treeView = new TreeView<>(this.rootNode);
		treeBox.getChildren().addAll(new Label("Samples Selection :"), treeView);
		VBox.setVgrow(treeView, Priority.ALWAYS);
	}

	private void setupRootNode(DirectoryStream<Path> stream, TreeItem<String> root){
		for (Path name : stream) {
			TreeTableService treeNode = new TreeTableService(name);
			if (treeNode.isDirectory()){
				TreeItem<String> subRoot = new TreeItem<>(name.getFileName().toString());
				try {
					setupRootNode(Files.newDirectoryStream(name), subRoot);
				} catch (IOException e) {
					e.printStackTrace();
				}
				root.getChildren().add(subRoot);
			} else {
				root.getChildren().add(treeNode);
			}
		}
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
