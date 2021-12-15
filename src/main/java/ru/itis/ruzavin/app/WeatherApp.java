package ru.itis.ruzavin.app;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import ru.itis.ruzavin.web.http.HttpClient;

import java.io.FileNotFoundException;
import java.io.IOException;

public class WeatherApp extends Application {

	private Pane pane;
	private VBox vBox;
	private HBox hBox;
	private Button findButton;
	private TextField textField;
	private HttpClient httpClient;

	@Override
	public void start(Stage primaryStage) throws Exception {
		Scene scene = new Scene(createContent());
		primaryStage.setScene(scene);

		httpClient = new HttpClient();

		EventHandler<ActionEvent> findEvent = new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				if (findButton == event.getSource()) {
					clearVBox();
					String parameters = httpClient.sendRequestAndGetOutputString(textField.getText());
					setParameters(parameters);
				}
			}
		};

		findButton.setOnAction(findEvent);

		primaryStage.show();
	}

	private void setParameters(String parameters) {
		if (parameters == null) {
			Label label = new Label("city is not found");
			vBox.getChildren().add(label);
		} else {

			HBox hBox1 = new HBox(10);

			Label label = new Label(parameters);
			hBox1.getChildren().add(label);

			vBox.getChildren().add(hBox1);
		}
	}

	private void clearVBox() {
		ObservableList<Node> children = vBox.getChildren();
		vBox.getChildren().removeAll(children);
	}

	private Parent createContent() {
		pane = new AnchorPane();
		pane.setPrefSize(800, 800);

		vBox = new VBox(15);
		hBox = new HBox(10);

		Font font = Font.font("Calibri Light", FontWeight.MEDIUM, 18);

		findButton = new Button("Get weather");
		findButton.setMaxWidth(500);
		findButton.setMaxHeight(500);
		findButton.setFont(font);

		textField = new TextField();
		textField.setMaxWidth(500);
		textField.setMaxHeight(500);
		textField.setFont(font);

		hBox.getChildren().add(textField);
		hBox.getChildren().add(findButton);

		AnchorPane.setTopAnchor(hBox, 5.0);
		AnchorPane.setLeftAnchor(hBox, 10.0);
		AnchorPane.setRightAnchor(hBox, 10.0);

		AnchorPane.setTopAnchor(vBox, 40.0);
		AnchorPane.setLeftAnchor(vBox, 10.0);
		AnchorPane.setRightAnchor(vBox, 10.0);

		pane.getChildren().add(vBox);
		pane.getChildren().add(hBox);

		return pane;
	}
}
