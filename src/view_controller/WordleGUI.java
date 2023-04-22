package view_controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import model.Wordle;
import model.WordleAccount;

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
	private ButtonHandler buttonHandler;
	private WordleAccount account;
	private BorderPane everything;
	private Label mode;
	Button[][] boardGameRs = new Button[6][5];
	ArrayList<ArrayList<Button>> keyboardRows = new ArrayList<>();
	protected Keyboard keyboard;
	private LocalDate localDate;
	private Button login;
	private Button logout;

	@Override
	public void start(Stage stage) throws Exception {
		stage.setTitle("Wordle");
		wordle = new Wordle(true);
		field = new TextField();
		mode = new Label("Daily word");

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
		localDate = LocalDate.now();

//		if(!loginPane.isLoggedIn()) {
//			
//		}

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
		VBox v = new VBox();
		v.getChildren().addAll(mode, boardGame);
		v.setAlignment(Pos.BASELINE_CENTER);
		everything.setCenter(v);
		// TODO Auto-generated method stub

	}

	private void resetGame() {
		setBoard();
		wordle = new Wordle(false);

		//this.keyboard = new Keyboard("QWERTYUIOPASDFGHJKLZXCVBNM".toCharArray());
		layoutKeyboard();
		field.setEditable(true);
		button.setDisable(false);
		loginPane.logout();
		counter = 0;
	}

	private Button[] ButtonMaker() {
		Button[] temp = new Button[5];
		for (int i = 0; i < 5; i++) {
			temp[i] = new Button("_");
			temp[i].setStyle("-fx-padding: 5 10 10 10;");
			temp[i].setFont(new Font("Courier New", 25));
			temp[i].setBackground(null);
			temp[i].setOnAction(event -> {
				Button buttonClicked = (Button) event.getSource();
				System.out.println(buttonClicked.getText());
			});
		}
		return temp;
	}

	private void showScore(boolean menu, boolean win, boolean loggedIn) {

		Alert scoreAlert = new Alert(AlertType.CONFIRMATION);
		String header = "";
		String content = "";
		if (!loggedIn) {
			header = "You must be logged in to see your score";
			content = "Please log in to see your score";

		} else if (menu) {
			content = account.getScoreString();
			header = "Score Statistics";
		} else if (win) {
			content = "You win!\n" + account.getScoreString();
			header = "The word was " + wordle.getWord(true) + "\n\nScore Summary";
		} else {
			content = "Game over. You lose\n" + account.getScoreString();
			header = "The word was " + wordle.getWord(true) + "\n\nScore Summary";
		}
		scoreAlert.setContentText(content);
		scoreAlert.setHeaderText(header);

		Optional<ButtonType> result = scoreAlert.showAndWait();

		if (result.get() == ButtonType.OK) {
			loginPane.logout();
			everything.setDisable(false);
			resetGame();
		} else {
			// If you can figure out how to just make this alert one
			// button feel free to do so
			loginPane.logout();
			everything.setDisable(false);
			resetGame();
		}
//		scoreAlert.show();
	}

	private void layoutKeyboard() {

		HBox textbutton = new HBox();

		button = new Button("submit guess");
		buttonHandler = new ButtonHandler();
		button.setOnAction(buttonHandler);
		textbutton.getChildren().addAll(field, button);
		field.setEditable(false);
		button.setDisable(true);

		String letters = "QWERTYUIOPASDFGHJKLZXCVBNM";
		char[] qwerty = letters.toCharArray();

		this.keyboard = new Keyboard(qwerty);

		keyboard.getChildren().add(textbutton);
		everything.setBottom(keyboard);

	}

	private void layoutGUI() {
		everything = new BorderPane();
		loginPane = new UsernameLogin(wordle);

		MenuBar menuBar = new MenuBar();

		Menu settings = new Menu("settings");
		MenuItem darkMode = new MenuItem("dark mode toggle");
		MenuItem practiceMode = new MenuItem("practice mode");
		MenuItem newGame = new MenuItem("new game (practice mode)");

		Menu score = new Menu("score");
		MenuItem leaderboard = new MenuItem("leaderboard");
		MenuItem personalScore = new MenuItem("statistics");

		settings.getItems().addAll(darkMode, practiceMode, newGame);
		score.getItems().addAll(leaderboard, personalScore);
		menuBar.getMenus().addAll(settings, score);

		VBox holder = new VBox();
		holder.getChildren().addAll(menuBar, loginPane);
		everything.setTop(holder);

		darkMode.setOnAction(new DarkMode());
		score.setOnAction(actionEvent -> {
			account = loginPane.getCurrentUser();
			if (account == null) {
				showScore(true, false, false);
			} else {
				showScore(true, false, true);
			}
		});

		newGame.setOnAction(actionEvent -> {
			mode.setText("Practice Mode");
			// wordle.setRandomWord(5);
			resetGame();

		});

		practiceMode.setOnAction(event -> {
			mode.setText("Practice Mode");
			buttonHandler.mode = false;
			resetGame();
		});

		login = loginPane.getLoginButton();
		logout = loginPane.getLogoutButton();

		login.setOnAction(event -> {
			loginPane.login();
			if (loginPane.isLoggedIn()) {
				field.setEditable(true);
				button.setDisable(false);
			}
		});

		logout.setOnAction(event -> {
			loginPane.logout();
			resetGame();
			field.setEditable(false);
			button.setDisable(true);

		});

	}

	public static void main(String[] args) {
		launch(args);
	}

	private class ButtonHandler implements EventHandler<ActionEvent> {

		public boolean mode = true;

		@Override
		public void handle(ActionEvent actionEvent) {
			String guess = field.getText().strip();
//			if (!loginPane.isLoggedIn()) {
//				button.setText("you are not logged in");
//				field.setText("");
//				return;
//			}
			field.setEditable(true);
			button.setDisable(false);
			account = loginPane.getCurrentUser();
			if (guess.length() != 5) {
				button.setText("invalid length, try again");
				field.setText("");
				return;
			}
			field.setText("");
			button.setText("submit guess");
			int[] guessStr;
			if (!wordle.isWord(guess)) {

				button.setText("Not a word, try again");
				return;
			} else {
				guessStr = wordle.guess(guess);
			}

			Button[] curBoard = boardGameRs[counter];
			boardGameRs[counter] = colorBoard(guess, guessStr, curBoard);
			colorKeyboard(guess, guessStr);

			counter++;
			if (winCondition(curBoard)) {
				everything.setDisable(true);
//				account.updateScore(counter + 1);
//				wordle.updateAccount(account);
//				wordle.save();
				field.setEditable(false);
				button.setDisable(true);
				button.setText("Logout to start a new game");
				showScore(false, true, true);
			} else if (counter == boardGameRs.length) {
				everything.setDisable(true);
				System.out.println("Game over.\n the word was " + wordle.getWord(mode) + "\n");
//				account.updateScore(counter + 1);
//				wordle.updateAccount(account);
//				wordle.save();
				showScore(false, false, true);
				field.setEditable(false);
				button.setDisable(true);
				button.setText("Logout to start a new game or select practice mode.");
			}
//			everything.setDisable(false);

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

			Collection<Keyboard.Key> keys = keyboard.getKeys();

			keys.forEach((key -> {
				// iterate over every key
				String letter = key.getKeyVal();

				for (int i = 0; i < guessStr.length; i++) {
					if (splitGuess[i].equals(key.getText())) {
						switch (guessStr[i]) {
						case WRONG -> key.setStyle("-fx-background-color: #808080; ");
						case CORRECT -> key.setStyle("-fx-background-color: #00FF00; ");
						case CONTAINS -> key.setStyle("-fx-background-color: #FFFF00; ");
						}
					}
				}
			}));
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

	private class DarkMode implements EventHandler<ActionEvent> {
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
