package view_controller;

import java.util.ArrayList;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.TilePane;
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
	private int counter = 0; // TODO stop game after 6 guesses
	private TextField field;
	private Wordle wordle;
	private WordleAccount account;
	private BorderPane everything;
	private TilePane tilePane;
	Button[] boardGameR1 = new Button[5];
	Button[] boardGameR2 = new Button[5];
	Button[] boardGameR3 = new Button[5];
	Button[] boardGameR4 = new Button[5];
	Button[] boardGameR5 = new Button[5];
	Button[] boardGameR6 = new Button[5];
	ArrayList<Button> keyboardR1 = new ArrayList<>();
	ArrayList<Button> keyboardR2 = new ArrayList<>();
	ArrayList<Button> keyboardR3 = new ArrayList<>();

	// Button[] keyboardR1 = new Button[10];
//	Button[] keyboardR2 = new Button[9];
//	Button[] keyboardR3 = new Button[7];

	@Override
	public void start(Stage stage) throws Exception {
		stage.setTitle("Wordle");
		wordle = new Wordle();
		field = new TextField();

		layoutGUI();
		layoutKeyboard();
		// setupText();
//		verifyLogin();
		setBoard();
		layoutGame();

		Scene scene = new Scene(everything, 800, 700);

		stage.setScene(scene);
		stage.show();
	}

	private void layoutGame() {
		// TODO Auto-generated method stub

	}

	private void setBoard() {
		// Label word = new Label(wordle.getWord(true));
		HBox Row1 = new HBox();
		HBox Row2 = new HBox();
		HBox Row3 = new HBox();
		HBox Row4 = new HBox();
		HBox Row5 = new HBox();
		HBox Row6 = new HBox();
		VBox boardGame = new VBox();

		for (int i = 0; i < 6; i++) {
			if (i == 0) {
				boardGameR1 = ButtonMaker();
				Row1.getChildren().addAll(boardGameR1);
			} else if (i == 1) {
				boardGameR2 = ButtonMaker();
				Row2.getChildren().addAll(boardGameR2);
			} else if (i == 2) {
				boardGameR3 = ButtonMaker();
				Row3.getChildren().addAll(boardGameR3);
			} else if (i == 3) {
				boardGameR4 = ButtonMaker();
				Row4.getChildren().addAll(boardGameR4);
			} else if (i == 4) {
				boardGameR5 = ButtonMaker();
				Row5.getChildren().addAll(boardGameR5);
			} else if (i == 5) {
				boardGameR6 = ButtonMaker();
				Row6.getChildren().addAll(boardGameR6);
			}

		}
		Row1.setAlignment(Pos.CENTER);
		Row2.setAlignment(Pos.CENTER);
		Row3.setAlignment(Pos.CENTER);
		Row4.setAlignment(Pos.CENTER);
		Row5.setAlignment(Pos.CENTER);
		Row6.setAlignment(Pos.CENTER);
		Row1.setSpacing(5);
		Row2.setSpacing(5);
		Row3.setSpacing(5);
		Row4.setSpacing(5);
		Row5.setSpacing(5);
		Row6.setSpacing(5);
		boardGame.getChildren().addAll(Row1, Row2, Row3, Row4, Row5, Row6);
		boardGame.setSpacing(5);
		boardGame.setStyle("-fx-padding: 20 0 0 0;");
		everything.setCenter(boardGame);
		// TODO Auto-generated method stub

	}

	private Button[] ButtonMaker() {
		Button[] temp = new Button[5];
		for (int i = 0; i < 5; i++) {
			temp[i] = new Button("_");
			temp[i].setStyle("-fx-padding: 5 10 10 10;");
			temp[i].setFont(new Font("Courier New", 32));
			temp[i].setOnAction(event -> {
				Button buttonClicked = (Button) event.getSource();
				System.out.println(buttonClicked.getText());
			});
		}
		return temp;
	}
//
//	public void verifyLogin() {
//		if(!loginPane.isLoggedIn()) {
//			board.setDisable(true);	
//		} else {
//			board.setDisable(false);
//		}
//		// TODO Auto-generated method stub
//		
//	}

//	private void setupText(){
//		Label word = new Label(wordle.getWord(true));
//		VBox layout = new VBox();
//		text = new TextArea();
//		text.setEditable(false);
//		text.setFont(new Font("comic sans MS",20));
//		layout.getChildren().addAll(word,text);
//		everything.setCenter(layout);
//	}

	private void layoutKeyboard() {
		HBox Row1 = new HBox();
		HBox Row2 = new HBox();
		HBox Row3 = new HBox();
		VBox board = new VBox();
		HBox textbutton = new HBox();

		button = new Button("submit guess");
		button.setOnAction(new ButtonHandler());
		textbutton.getChildren().addAll(field, button);

		String letters = "QWERTYUIOPASDFGHJKLZXCVBNM";
		char[] qwerty = letters.toCharArray();

		for (int i = 0; i < 10; i++) {
			keyboardR1.add(new Button("" + qwerty[i]));
			keyboardR1.get(i).setStyle("-fx-padding: 5 10 10 10;");
			keyboardR1.get(i).setFont(new Font("Courier New", 32));
			keyboardR1.get(i).setOnAction(event -> {
				Button buttonClicked = (Button) event.getSource();
				System.out.println(buttonClicked.getText());
			});
		}
		for (int i = 0; i < 9; i++) {
			keyboardR2.add(new Button("" + qwerty[i + 10]));
			keyboardR2.get(i).setStyle("-fx-padding: 5 10 10 10;");
			keyboardR2.get(i).setFont(new Font("Courier New", 32));
			keyboardR2.get(i).setOnAction(event -> {
				Button buttonClicked = (Button) event.getSource();
				System.out.println(buttonClicked.getText());
			});
		}
		for (int i = 0; i < 7; i++) {
			keyboardR3.add(new Button("" + qwerty[i + 19]));
			keyboardR3.get(i).setStyle("-fx-padding: 5 10 10 10;");
			keyboardR3.get(i).setFont(new Font("Courier New", 32));
			keyboardR3.get(i).setOnAction(event -> {
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
			if (!loginPane.isLoggedIn()) {
				button.setText("you are not logged in");
				field.setText("");
				return;
			}
			if (guess.length() != 5) {
				button.setText("invalid length, try again");
				field.setText("");
				return;
			}
			if(loginPane.isLoggedIn()) {
				account = loginPane.getCurrentUser();
			}
			field.setText("");
			int[] guessStr = wordle.guess(guess, true);
			if (counter == 0) {
				boardGameR1 = colorBoard(guess, guessStr, boardGameR1);
				colorKeyboard1(guess, guessStr);
				colorKeyboard2(guess, guessStr);
				colorKeyboard3(guess, guessStr);
				counter++;
				if (winCondition(boardGameR1)) {
					everything.setDisable(true);
					account.updateScore(counter+1);

				}
			} else if (counter == 1) {
				boardGameR2 = colorBoard(guess, guessStr, boardGameR2);
				colorKeyboard1(guess, guessStr);
				colorKeyboard2(guess, guessStr);
				colorKeyboard3(guess, guessStr);
				counter++;
				if (winCondition(boardGameR2)) {
					everything.setDisable(true);
					account.updateScore(counter+1);

				}
			} else if (counter == 2) {
				boardGameR3 = colorBoard(guess, guessStr, boardGameR3);
				colorKeyboard1(guess, guessStr);
				colorKeyboard2(guess, guessStr);
				colorKeyboard3(guess, guessStr);
				counter++;
				if (winCondition(boardGameR3)) {
					everything.setDisable(true);
					account.updateScore(counter+1);

				}
			} else if (counter == 3) {
				boardGameR4 = colorBoard(guess, guessStr, boardGameR4);
				colorKeyboard1(guess, guessStr);
				colorKeyboard2(guess, guessStr);
				colorKeyboard3(guess, guessStr);
				counter++;
				if (winCondition(boardGameR4)) {
					everything.setDisable(true);
					account.updateScore(counter+1);

				}
			} else if (counter == 4) {
				boardGameR5 = colorBoard(guess, guessStr, boardGameR5);
				colorKeyboard1(guess, guessStr);
				colorKeyboard2(guess, guessStr);
				colorKeyboard3(guess, guessStr);
				counter++;
				if (winCondition(boardGameR5)) {
					everything.setDisable(true);
					account.updateScore(counter+1);

				}
			} else if (counter == 5) {
				boardGameR6 = colorBoard(guess, guessStr, boardGameR6);
				colorKeyboard1(guess, guessStr);
				colorKeyboard2(guess, guessStr);
				colorKeyboard3(guess, guessStr);
				counter++;
				if (winCondition(boardGameR6)) {
					everything.setDisable(true);
				} else {
					everything.setDisable(true);
					System.out.println("Game over.\n the word was " + wordle.getWord(true) + "\n");
					account.updateScore(counter+1);
				}
			}

		}

		private boolean winCondition(Button[] boardGame) {
			for (int i = 0; i < boardGame.length; i++) {
				if (!boardGame[i].getStyle().contains("-fx-background-color: #00FF00; ")) {
					return false;
				}
			}
			System.out.println("Game over. You win!");
			System.out.println("You guessed the word in " + counter + " guesses.");
			System.out.println("The word was " + wordle.getWord(true));
			account.updateScore(counter);
			System.out.println(account.getScoreString());
			wordle.updateAccount(account);
			wordle.save();
			return true;
		}

		private void colorKeyboard3(String guess, int[] guessStr) {

			String guessUpper = guess.toUpperCase();
			String[] temp = guessUpper.split("");
			for (int i = 0; i < temp.length; i++) {
				for (int j = 0; j < keyboardR3.size(); j++) {
					if (temp[i].equals(keyboardR3.get(j).getText())) {
						if (guessStr[i] == 1) {
							keyboardR3.get(j).setStyle("-fx-background-color: #00FF00; ");
						} else if (guessStr[i] == 2
								&& !keyboardR3.get(j).getStyle().contains("-fx-background-color: #00FF00; ")) {
							keyboardR3.get(j).setStyle("-fx-background-color: #FFFF00; ");
						} else if (guessStr[i] == 0
								&& !keyboardR3.get(j).getStyle().contains("-fx-background-color: #00FF00; ")) {
							keyboardR3.get(j).setStyle("-fx-background-color: #808080; ");
						}
					}
				}
			}
		}

		private void colorKeyboard2(String guess, int[] guessStr) {
			String guessUpper = guess.toUpperCase();
			String[] temp = guessUpper.split("");
			for (int i = 0; i < temp.length; i++) {
				for (int j = 0; j < keyboardR2.size(); j++) {
					if (temp[i].equals(keyboardR2.get(j).getText())) {
						if (guessStr[i] == 1) {
							keyboardR2.get(j).setStyle("-fx-background-color: #00FF00; ");
						} else if (guessStr[i] == 2
								&& !keyboardR2.get(j).getStyle().contains("-fx-background-color: #00FF00; ")) {
							keyboardR2.get(j).setStyle("-fx-background-color: #FFFF00; ");
						} else if (guessStr[i] == 0
								&& !keyboardR2.get(j).getStyle().contains("-fx-background-color: #00FF00; ")) {
							keyboardR2.get(j).setStyle("-fx-background-color: #808080; ");
						}
					}
				}
			}
		}

		private void colorKeyboard1(String guess, int[] guessStr) {
			String guessUpper = guess.toUpperCase();
			String[] temp = guessUpper.split("");
			for (int i = 0; i < temp.length; i++) {
				for (int j = 0; j < keyboardR1.size(); j++) {
					if (temp[i].equals(keyboardR1.get(j).getText())) {
						if (guessStr[i] == 1) {
							keyboardR1.get(j).setStyle("-fx-background-color: #00FF00; ");
						} else if (guessStr[i] == 2
								&& !keyboardR1.get(j).getStyle().contains("-fx-background-color: #00FF00; ")) {
							keyboardR1.get(j).setStyle("-fx-background-color: #FFFF00; ");
						} else if (guessStr[i] == 0
								&& !keyboardR1.get(j).getStyle().contains("-fx-background-color: #00FF00; ")) {
							keyboardR1.get(j).setStyle("-fx-background-color: #808080; ");
						}
					}
				}
			}
		}

		private Button[] colorBoard(String guess, int[] guessStr, Button[] boardGameR) {
			String[] temp = guess.split("");
			for (int i = 0; i < 5; i++) {
				boardGameR[i].setText(temp[i].toUpperCase());
				if (guessStr[i] == 1) {
					boardGameR[i].setStyle("-fx-background-color: #00FF00; ");
				} else if (guessStr[i] == 2) {
					boardGameR[i].setStyle("-fx-background-color: #FFFF00; ");
				} else if (guessStr[i] == 0) {
					boardGameR[i].setStyle("-fx-background-color: #808080; ");
				}
			}
			return boardGameR;
		}

	}
}