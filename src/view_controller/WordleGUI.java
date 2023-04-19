package view_controller;

import java.util.ArrayList;
import java.util.List;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import model.*;

import javafx.event.ActionEvent;

public class WordleGUI extends Application {
	private static final int WRONG = 0;
	private static final int CORRECT = 1;
	private static final int CONTAINS = 2;
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
		field.setOnAction(event -> {
			String text = field.getText();
			field.setText("");
			System.out.println(text);
		});

		layoutGUI();
		layoutKeyboard();
		// setupText();
//		verifyLogin();
		setBoard();


		Scene scene = new Scene(everything, 600, 750);

		stage.setScene(scene);
		stage.addEventFilter(KeyEvent.KEY_PRESSED, this::handleKeyboardInput);
		stage.show();
	}

	private void handleKeyboardInput(KeyEvent event) {
		String key = event.getText();
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
			temp[i].setFont(new Font("Courier New", 25));
			temp[i].setOnAction(event -> {
				Button buttonClicked = (Button) event.getSource();
				System.out.println(buttonClicked.getText());
			});
		}
		return temp;
	}

	private void showScore(boolean menu, boolean win, boolean loggedIn){


		Alert scoreAlert = new Alert(AlertType.CONFIRMATION);
		String header ="";
		String content="";
		if(!loggedIn){
			header = "You must be logged in to see your score";
			content = "Please log in to see your score";

		}
		else if(menu){
			content = account.getScoreString();
			header = "Score Statistics";
		}
		else if(win){
			content = "You win!\n"+account.getScoreString();
			header = "The word was "+wordle.getWord(true)+"\n\nScore Summary";
		}
		else{
			content = "Game over. You lose\n"+account.getScoreString();
			header = "The word was "+wordle.getWord(true)+"\n\nScore Summary";
		}
		scoreAlert.setContentText(content);
		scoreAlert.setHeaderText(header);
		scoreAlert.show();
	}


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
				keyButton.setFont(new Font("Courier New", 25));
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

	}

	private void layoutGUI(){
		everything = new BorderPane();
		loginPane = new UsernameLogin(wordle);

		MenuBar menuBar = new MenuBar();

		Menu settings = new Menu("settings");
		MenuItem darkMode = new MenuItem("dark mode toggle");
		MenuItem practiceMode = new MenuItem("practice mode");

		Menu score = new Menu("score");
		MenuItem leaderboard = new MenuItem("leaderboard");
		MenuItem personalScore = new MenuItem("statistics");

		settings.getItems().addAll(darkMode, practiceMode);
		score.getItems().addAll(leaderboard, personalScore);
		menuBar.getMenus().addAll(settings,score);

		VBox holder = new VBox();
		holder.getChildren().addAll(menuBar, loginPane);
		everything.setTop(holder);

		darkMode.setOnAction(new DarkMode());
		score.setOnAction(actionEvent -> {
			account = loginPane.getCurrentUser();
			if (account==null){
				showScore(true, false, false);
			}
			else{
				showScore(true, false, true);
			}
		});


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
			account = loginPane.getCurrentUser();
			if (guess.length() != 5) {
				button.setText("invalid length, try again");
				field.setText("");
				return;
			}
			field.setText("");
			button.setText("submit guess");
			int[] guessStr = wordle.guess(guess, true);

			Button[] curBoard = boardGameRs[counter];
			boardGameRs[counter] = colorBoard(guess, guessStr, curBoard);
			colorKeyboard(guess, guessStr);

			counter++;
			if (winCondition(curBoard)) {
				everything.setDisable(true);
				account.updateScore(counter+1);
				wordle.updateAccount(account);
				wordle.save();
				showScore(false,true,true);
			} else if (counter == boardGameRs.length){
				everything.setDisable(true);
				System.out.println("Game over.\n the word was " + wordle.getWord(true) + "\n");
				account.updateScore(counter+1);
				wordle.updateAccount(account);
				wordle.save();
				showScore(false,false,true);


			}



		}

		private boolean winCondition(Button[] boardGame) {
			for (int i = 0; i < boardGame.length; i++) {
				if (!boardGame[i].getStyle().contains("-fx-background-color: #00FF00; ")) {
					return false;
				}
			}
//			System.out.println("Game over. You win!");
//			System.out.println("You guessed the word in " + counter + " guesses.");
//			System.out.println("The word was " + wordle.getWord(true));
			account.updateScore(counter);
//			System.out.println(account.getScoreString());
			wordle.updateAccount(account);
			wordle.save();
			

			return true;
		}

		private void colorKeyboard(String guess, int[] guessStr) {
			String guessUpper = guess.toUpperCase();
			String[] splitGuess = guessUpper.split("");

			keyboardRows.forEach((row) -> row.forEach((key) -> {
				// iterate over every key
				for (int i = 0; i < guessStr.length; i++) {
					if (splitGuess[i].equals(key.getText())) {
						switch (guessStr[i]) {
							case (WRONG) -> key.setStyle("-fx-background-color: #808080; ");
							case (CORRECT) -> key.setStyle("-fx-background-color: #00FF00; ");
							case (CONTAINS) -> key.setStyle("-fx-background-color: #FFFF00; ");
						}
					}}}));
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

	private class DarkMode implements EventHandler<ActionEvent>{
		boolean isDarkMode = false;
		@Override
		public void handle(ActionEvent actionEvent) {
			if (!isDarkMode) {
				everything.setStyle("-fx-background-color: #000000; ");
				isDarkMode = true;
			} else {
				everything.setStyle("-fx-background-color: #FFFFFF; ");
				isDarkMode = false;
			}

		}
	}
}