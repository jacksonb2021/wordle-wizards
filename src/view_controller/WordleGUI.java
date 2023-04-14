package view_controller;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import model.*;

import javafx.event.ActionEvent;

public class WordleGUI extends Application {
	private UsernameLogin loginPane;
	private TextArea text;
	private Button button;
	private TextField field;
	private Wordle wordle;
	private BorderPane everything;

	@Override
	public void start(Stage stage) throws Exception {
		stage.setTitle("Wordle");
		wordle = new Wordle();
		field = new TextField();
		layoutGUI();
		layoutKeyboard();
		setupText();
		everything.setCenter(text);
		Scene scene = new Scene(everything, 800, 700);

		stage.setScene(scene);
		stage.show();
	}

	private void setupText(){
		Label word = new Label(wordle.getWord(true));
		VBox layout = new VBox();
		text = new TextArea();
		text.setEditable(false);
		text.setFont(new Font("comic sans MS",20));
		layout.getChildren().addAll(word,text);
	}


	private void layoutKeyboard() {
		HBox Row1 = new HBox();
		HBox Row2 = new HBox();
		HBox Row3 = new HBox();
		VBox board = new VBox();
		HBox textbutton = new HBox();
		button = new Button("submit guess");
		button.setOnAction(new ButtonHandler());
		textbutton.getChildren().addAll(field,button);
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
		board.getChildren().addAll(Row1, Row2, Row3, textbutton);
		everything.setBottom(board);
		// TODO Auto-generated method stub
	}

	private void layoutGUI() {
		everything = new BorderPane();
		loginPane = new UsernameLogin(wordle);
		everything.setTop(loginPane);
	}

	public static void main(String[] args) {
		launch(args);
	}


	private class ButtonHandler implements EventHandler<ActionEvent> {


		@Override
		public void handle(ActionEvent actionEvent) {
			String guess = field.getText().strip();
			if(guess.length()!=5){
				button.setText("invalid length, try again");
				field.setText("");
				return;
			}
			int[] guessstr = wordle.guess(guess, true);
			text.appendText(wordle.guessToString(guessstr)+"\n");
		}

	}
}
