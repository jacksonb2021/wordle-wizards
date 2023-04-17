package view_controller;

import java.util.ArrayList;
import java.util.List;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
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
	Button[][] boardGameRs = new Button[6][5];
	ArrayList<ArrayList<Button>> keyboardRows = new ArrayList<>();

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
		stage.addEventFilter(KeyEvent.KEY_PRESSED, event->{
			handleKeyboardInput(event);
		});
		stage.show();
	}

	private void handleKeyboardInput(KeyEvent event) {
		String key = event.getText();
	}

	private void layoutGame() {
		// TODO Auto-generated method stub

	}

	private void setBoard() {
		// Label word = new Label(wordle.getWord(true));
		List<HBox> rows = new ArrayList<>();

		for (int i = 0; i < 6; i++) {
			rows.add(new HBox());
		}

		VBox boardGame = new VBox();

		for (int i = 0; i < 6; i++) {
			HBox curRow = rows.get(i);
			Button[] boardGame1 = ButtonMaker();
			curRow.getChildren().addAll(boardGame1);
			curRow.setAlignment(Pos.CENTER);
			curRow.setSpacing(5);
			boardGameRs[i] = boardGame1;
		}

		boardGame.getChildren().addAll(rows);
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
		int curEndPoint = 10;
		int startPoint = 0;
		for (int i = 0; i < 3; i++) {
			// each row
			ArrayList<Button> keyboardRow = new ArrayList<>();
			// each letter on the keyboard
			for (int j = 0; j < curEndPoint && j+startPoint < letters.length(); j++) {
				Button keyButton = new Button("" + qwerty[j + startPoint]);

				keyButton.setStyle("-fx-padding: 5 10 10 10;");
				keyButton.setFont(new Font("Courier New", 32));
				keyButton.setOnAction(event -> {
					Button buttonClicked = (Button) event.getSource();
					System.out.println(buttonClicked.getText());
				});
				keyboardRow.add(keyButton);

			}
			keyboardRows.add(keyboardRow);
			startPoint += curEndPoint;
			curEndPoint--;
		}

		Row1.getChildren().addAll(keyboardRows.get(0));
		Row2.getChildren().addAll(keyboardRows.get(1));
		Row3.getChildren().addAll(keyboardRows.get(2));
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

			Button[] curBoard = boardGameRs[counter];
			boardGameRs[counter] = colorBoard(guess, guessStr, curBoard);
			colorKeyboard(guess, guessStr);

			counter++;
			if (winCondition(curBoard)) {
				everything.setDisable(true);
				account.updateScore(counter+1);
			} else if (counter == boardGameRs.length){
				everything.setDisable(true);
				System.out.println("Game over.\n the word was " + wordle.getWord(true) + "\n");
				account.updateScore(counter+1);
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

		private void colorKeyboard(String guess, int[] guessStr) {
			String guessUpper = guess.toUpperCase();
			String[] splitGuess = guessUpper.split("");
			for (ArrayList<Button> keyboard : keyboardRows) {
				for (int i = 0; i < splitGuess.length; i++) {
					for (int j = 0; j <keyboard.size(); j++) {
						if (splitGuess[i].equals(keyboard.get(j).getText())) {
							if (guessStr[i] == 1) {
								keyboard.get(j).setStyle("-fx-background-color: #00FF00; ");
							} else if (guessStr[i] == 2
									&& !keyboard.get(j).getStyle().contains("-fx-background-color: #00FF00; ")) {
								keyboard.get(j).setStyle("-fx-background-color: #FFFF00; ");
							} else if (guessStr[i] == 0
									&& !keyboard.get(j).getStyle().contains("-fx-background-color: #00FF00; ")) {
								keyboard.get(j).setStyle("-fx-background-color: #808080; ");
							}
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