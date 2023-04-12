package view_controller;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import model.WordleSerializer;

public class WordleGUI extends Application {
	private UsernameLogin loginPane;
	private BorderPane everything;
	private WordleSerializer login;

	@Override
	public void start(Stage stage) throws Exception {
		stage.setTitle("Wordle");
		login = new WordleSerializer();
		LayoutGUI();
		LayoutKeyboard();
		Scene scene = new Scene(everything, 800, 700);

		stage.setScene(scene);
		stage.show();
	}

	private void LayoutKeyboard() {
		HBox Row1 = new HBox();
		HBox Row2 = new HBox();
		HBox Row3 = new HBox();
		VBox board = new VBox();
		Button[] keyboardR1 = new Button[10];
		Button[] keyboardR2 = new Button[9];
		Button[] keyboardR3 = new Button[7];
		String letters = "QWERTYUIOPASDFGHJKLZXCVBNM";
		char[] qwerty = letters.toCharArray();

		for (int i = 0; i < 10; i++) {
			keyboardR1[i] = new Button("" + qwerty[i]);
			keyboardR1[i].setStyle("-fx-padding: 5 10 10 10;");
			keyboardR1[i].setFont(new Font("Courier New", 32));

			keyboardR1[i].setOnAction(event -> {
				Button buttonClicked = (Button) event.getSource();
				System.out.println(buttonClicked.getText());
			});
		}
		for (int i = 0; i < 9; i++) {
			keyboardR2[i] = new Button("" + qwerty[i+10]);
			keyboardR2[i].setStyle("-fx-padding: 5 10 10 10;");
			keyboardR2[i].setFont(new Font("Courier New", 32));

			keyboardR2[i].setOnAction(event -> {
				Button buttonClicked = (Button) event.getSource();
				System.out.println(buttonClicked.getText());
			});
		}
		for (int i = 0; i < 7 ; i++) {
			keyboardR3[i] = new Button("" + qwerty[i+19]);
			keyboardR3[i].setStyle("-fx-padding: 5 10 10 10;");
			keyboardR3[i].setFont(new Font("Courier New", 32));
			keyboardR3[i].setOnAction(event -> {
				Button buttonClicked = (Button) event.getSource();
				System.out.println(buttonClicked.getText());
			});
		}
		Row1.getChildren().addAll(keyboardR1);
		Row2.getChildren().addAll(keyboardR2);
		Row3.getChildren().addAll(keyboardR3);
		Row1.setSpacing(5);
		Row2.setSpacing(5);
		Row3.setSpacing(5);
		Row1.setStyle("-fx-padding: 0 0 0 0;");
		Row1.setAlignment(Pos.CENTER);
		Row2.setAlignment(Pos.CENTER);
		Row3.setAlignment(Pos.CENTER);
		board.getChildren().addAll(Row1, Row2, Row3);
		everything.setBottom(board);
		// TODO Auto-generated method stub
	}

	private void LayoutGUI() {
		everything = new BorderPane();
		loginPane = new UsernameLogin(login);
		everything.setCenter(loginPane);
	}

	public static void main(String[] args) {
		launch(args);
	}

}
