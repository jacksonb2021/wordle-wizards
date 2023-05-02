package view_controller;

import java.io.File;
import java.net.URI;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import javafx.animation.Interpolator;
import javafx.animation.RotateTransition;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;
import javafx.util.Duration;
import model.Wordle;
import model.WordleAccount;

/**
 * This class creates an interactive wordle game in a GUI, using javafx. The
 * Wordle game is simulated using a Wordle game object, using WordleAccount to
 * limit user games per day and track scoring. This file also creates a
 * Leaderboard object that records and stores user WordleAccounts to track user
 * performance across games.
 *
 * @author Jackson Burns, Jose Juan Velasquez, Duke Speed, Amon Guinan
 */
public class WordleGUI extends Application {

	private UsernameLogin loginPane;
	private Button button;
	private int counter = 0; // TODO stop game after 6 guesses
	private TextField field;
	private Wordle wordle;
	private ButtonHandler buttonHandler;
	private WordleAccount account;
	private MediaPlayer mediaPlayer;
	private BorderPane everything;
	private Label mode;
	private Label loginStatus;
	private Label accountLabel;
	private Label passwordLabel;
	Button[][] boardGameRs = new Button[6][5];
	protected Keyboard keyboard;
	private boolean dailyOrRandom;
	private LeaderboardGUI leaderboardWindow;
	private boolean isDarkMode;
	private int curBoxX = 0;
	private int curBoxY = 0;

	/**
	 * Calls launch() from javafx.application.Application.launch().
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		launch(args);
	}

	/**
	 * Initializes required objects and opens GUI window.
	 */
	@Override
	public void start(Stage stage) throws Exception {
		stage.setTitle("Wordle");
		wordle = new Wordle(true);
		dailyOrRandom = true;
		field = new TextField();
		mode = new Label("Daily word");
		isDarkMode = false;

		field.setOnAction(event -> {
			buttonHandler.handle(event);

		});

		leaderboardWindow = new LeaderboardGUI();
		for (WordleAccount u : wordle.getAccounts()) {
			leaderboardWindow.getLeaderboard().addUser(u);
		}
		layoutGUI();
		layoutKeyboard();

		setBoard();
		loginStatus = loginPane.getLoginStatus();
		accountLabel = loginPane.getAccountLabel();
		passwordLabel = loginPane.getPwdLabel();

		Scene scene = new Scene(everything, 600, 750);
		stage.setScene(scene);

		stage.addEventFilter(KeyEvent.KEY_PRESSED, this::handleKeyboardInput);
		stage.show();
	}

	/**
	 * Implements direct keyboard input for game window.
	 * 
	 * @param event
	 */
	private void handleKeyboardInput(KeyEvent event) {
		String key = event.getText();

		if (loginPane.isLoggedIn()) {
			if (event.getCode() == KeyCode.ENTER) {
				buttonHandler.handle(new ActionEvent());
				return;
			}
			if (loginPane.isFocusWithin()) {
				return;
			}

			if (event.getCode() == KeyCode.BACK_SPACE) {
				if (curBoxX != 0)
					curBoxX--;
				if (curBoxX > 4)
					curBoxX = 4;
				if (curBoxX <= 0) {
					curBoxX = 0;
				}

				boardGameRs[curBoxY][curBoxX].setText(key);
				setDarkModeText();
			} else if (curBoxX < 5) {
				boardGameRs[curBoxY][curBoxX].setText(key);
				setDarkModeText();
				curBoxX++;
			}

		}
	}

	private void setDarkModeText() {
		if (isDarkMode) {
			boardGameRs[curBoxY][curBoxX].setTextFill(Color.WHITE);
		} else {
			boardGameRs[curBoxY][curBoxX].setTextFill(Color.BLACK);
		}
	}

	/**
	 * Initializes graphical objects for the game board, and sets javafx window
	 * values.
	 */
	private void setBoard() {
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

	}

	/**
	 * Reinitializes the Wordle object that simulates the game, and sets the game to
	 * 'practice mode' which doesn't record user scorig for leaderboard purposes.
	 */
	private void resetGame() {
		setBoard();
		wordle = new Wordle(false);
		dailyOrRandom = false;
		layoutKeyboard();

		field.setEditable(true);
		button.setDisable(false);

		counter = 0;
		mode.setText("Practice mode (It will not count towards the leaderboards)");
		button.setText("submit guess");
		keyboard.setDarkMode(isDarkMode);

	}

	/**
	 * Initializes the wordle game object for the first time. User score is
	 * recorded.
	 */
	private void freshNewGame() {
		setBoard();
		wordle = new Wordle(true);
		dailyOrRandom = true;
		layoutKeyboard();
		field.setEditable(true);
		button.setDisable(false);
		counter = 0;
		mode.setText("Daily word");
		button.setText("submit guess");
	}

	/**
	 * Initializes button objects.
	 * 
	 * @return array of initialized button objects.
	 */
	private Button[] ButtonMaker() {
		Button[] temp = new Button[5];
		for (int i = 0; i < 5; i++) {
			temp[i] = new Button("");
			temp[i].setMinWidth(40);
			temp[i].setStyle("-fx-padding: 5 10 10 10;");
			temp[i].setFont(new Font("Courier New", 25));
			temp[i].setBackground(null);
			temp[i].setBorder(Border.stroke(Paint.valueOf("black")));
		}
		return temp;
	}

	/**
	 * Opens statistics window, which reflects the outcome of the game, and user
	 * performance therein.
	 * 
	 * @param menu
	 * @param win
	 * @param loggedIn
	 */
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
			header = "The word was " + wordle.getWord(dailyOrRandom)
					+ "\n\nScore Summary\nPress OK to play or Cancel to quit";
			playASong("gameOverWin.mp3");
		} else {
			content = "Game over. You lose\n" + account.getScoreString();
			header = "The word was " + wordle.getWord(dailyOrRandom)
					+ "\n\nScore Summary\nPress OK to play or Cancel to quit";
			playASong("gameOverLoss.mp3");
		}
		scoreAlert.setContentText(content);
		scoreAlert.setHeaderText(header);

		Optional<ButtonType> result = scoreAlert.showAndWait();
		if (result.get() == ButtonType.OK) {
			resetGame();
		} else {
//			resetGame();
			Platform.exit();
			System.exit(0);
		}

		everything.setDisable(false);
		leaderboardWindow.getLeaderboard().addUser(account);
	}

	/**
	 * Initializes keyboard object with GUI buttons for mouse/touchscreen use.
	 */
	private void layoutKeyboard() {

		HBox textbutton = new HBox();

		button = new Button("submit guess");
		buttonHandler = new ButtonHandler();
		button.setOnAction(buttonHandler);
		textbutton.setStyle("-fx-padding: 5 10 10 10;");
		textbutton.setSpacing(5);
		textbutton.getChildren().addAll(button);
		field.setEditable(false);
		button.setDisable(true);

		String letters = "QWERTYUIOPASDFGHJKLZXCVBNM";
		char[] qwerty = letters.toCharArray();

		this.keyboard = new Keyboard(qwerty);

		keyboard.getChildren().add(textbutton);

		everything.setBottom(keyboard);

	}

	/**
	 * Initializes various javafx objects, and calls/organizes individual GUI
	 * elements in window.
	 */
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

		personalScore.setOnAction(actionEvent -> {
			account = loginPane.getCurrentUser();
			if (account == null) {
				showScore(true, false, false);
			} else {
				showScore(true, false, true);
			}
		});

		leaderboard.setOnAction(actionEvent -> {
			leaderboardWindow.show();
		});

		newGame.setOnAction(actionEvent -> {
			mode.setText("Practice mode (It will not count towards the leaderboards)");
			resetGame();
		});

		practiceMode.setOnAction(event -> {
			mode.setText("Practice mode (It will not count towards the leaderboards)");
			buttonHandler.mode = false;
			resetGame();
		});
		Button login;
		Button logout;
		login = loginPane.getLoginButton();
		logout = loginPane.getLogoutButton();

		login.setOnAction(event -> {
			loginPane.login();
			if (loginPane.isLoggedIn()) {
				loginPane.setVisible(false);
				holder.getChildren().add(logout);
				holder.setAlignment(Pos.CENTER);
				logout.setFocusTraversable(false);
				field.setEditable(true);
				button.setDisable(false);
				LocalDate localDate;

				localDate = loginPane.getCurrentUser().getLastPlayed();

				boolean isDifferentDay = !(localDate.equals(LocalDate.now()));
				if (isDifferentDay) {
					freshNewGame();
				} else {
					Alert alreadyWordled = new Alert(AlertType.INFORMATION,
							"You've already played a game of Wordle today. Going to practice mode.");
					alreadyWordled.showAndWait();
					resetGame();
				}
			}

		});

		logout.setOnAction(event -> {
			holder.getChildren().remove(logout);
			loginPane.getPwdLane().getChildren().add(logout);
			loginPane.setVisible(true);
			// holder.setAlignment(Pos.TOP_CENTER);
			loginPane.logout();
			freshNewGame();
			field.setEditable(false);
			button.setDisable(true);

		});

	}

	/**
	 * Implements javafx button EventHandler logic for responding to user input.
	 */
	private class ButtonHandler implements EventHandler<ActionEvent> {

		@SuppressWarnings("unused")
		public boolean mode = true;

		@Override
		public void handle(ActionEvent actionEvent) {

			Button[] gues = boardGameRs[curBoxY];
			String guess = "";
			for (Button but : gues) {
				guess += but.getText();
			}
			field.setEditable(true);
			button.setDisable(false);
			account = loginPane.getCurrentUser();
			if (account == null) {
				button.setText("you are not logged in");
				field.setText("");
				return;
			}
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
			curBoxX = 0;
			curBoxY = counter;
			if (winCondition(curBoard)) {
				everything.setDisable(true);
//				account.updateScore(counter + 1);
//				wordle.updateAccount(account);
//				wordle.save();
				field.setEditable(false);
				button.setDisable(true);
				showScore(false, true, true);
				// loginPane.logout();
				curBoxX = 0;
				curBoxY = 0;
			} else if (counter == boardGameRs.length) {
				everything.setDisable(true);
				account.updateScore(counter + 1);
				showScore(false, false, true);
				field.setEditable(false);
				button.setDisable(true);
				curBoxX = 0;
				curBoxY = counter;
			}
			account.setLastPlayed(LocalDate.now());
//			everything.setDisable(false);

		}

		/**
		 * Determines if the game was won by the user.
		 * 
		 * @param boardGame
		 * @return true if game was won, false if otherwise.
		 */
		private boolean winCondition(Button[] boardGame) {
			for (int i = 0; i < boardGame.length; i++) {
				if (!boardGame[i].getStyle().contains("-fx-background-color: #53C448; ")) {
					return false;
				}
			}
			account = loginPane.getCurrentUser();
			account.updateScore(counter);
			account.setLastPlayed(LocalDate.now());
			wordle.updateAccount(account);
			wordle.save();

			return true;
		}

		/**
		 * Handles changes in color for keyboard GUI elements.
		 * 
		 * @param guess
		 * @param guessStr
		 */
		private void colorKeyboard(String guess, int[] guessStr) {
			String guessUpper = guess.toUpperCase();
			String[] splitGuess = guessUpper.split("");

			Collection<Keyboard.Key> keys = keyboard.getKeys();

			keys.forEach((key -> {
				final int WRONG = 0;
				final int CORRECT = 1;
				final int CONTAINS = 2;
				// iterate over every key

				for (int i = 0; i < guessStr.length; i++) {
					if (splitGuess[i].equals(key.getText())) {

						switch (guessStr[i]) {
						case WRONG -> {
							if (!key.getStyle().contains("-fx-background-color")) {
								key.setStyle("-fx-background-color: #808080; ");
							}
						}
						case CORRECT -> key.setStyle("-fx-background-color: #53C448; ");

						case CONTAINS -> {

							if (!key.getStyle().contains("-fx-background-color: #E9E546;")) {
								key.setStyle("-fx-background-color: #FFFF00; ");
							}
						}

						}
					}
				}
			}));
		}

		/**
		 * handles color logic for the game board, allowing char tiles to be animated
		 * and change color, depending on whether the user guessed correctly or not.
		 * 
		 * @param guess
		 * @param guessStr
		 * @param boardGameR
		 * @return
		 */
		private Button[] colorBoard(String guess, int[] guessStr, Button[] boardGameR) {

			for (Button b : boardGameR) {
				RotateTransition dab = createRotator(b);
				dab.play();
			}

			String[] temp = guess.split("");
			for (int i = 0; i < 5; i++) {
				// sleep();
				boardGameR[i].setText(temp[i].toUpperCase());
				playASong("tileFlip.mp3");
				if (guessStr[i] == 1) {
					boardGameR[i].setTextFill(Color.WHITE);
					boardGameR[i].setStyle("-fx-background-color: #53C448; ");
				} else if (guessStr[i] == 2) {
					boardGameR[i].setTextFill(Color.WHITE);
					boardGameR[i].setStyle("-fx-background-color: #E9E546; ");
				} else if (guessStr[i] == 0) {
					boardGameR[i].setTextFill(Color.WHITE);
					boardGameR[i].setStyle("-fx-background-color: #808080; ");
				}

			}
			return boardGameR;
		}

	}

	/**
	 * This class is used to change the display to a dark mode.
	 *
	 * @author jackson burns
	 */
	private class DarkMode implements EventHandler<ActionEvent> {

		/**
		 * This method is used to change the display to a dark mode.
		 * 
		 * @param actionEvent - the event that triggers the method
		 */
		@Override
		public void handle(ActionEvent actionEvent) {

			if (!isDarkMode) {
				everything.setStyle("-fx-background-color: #212121; ");
				mode.setTextFill(Color.WHITE);
				loginStatus.setTextFill(Color.WHITE);
				accountLabel.setTextFill(Color.WHITE);
				passwordLabel.setTextFill(Color.WHITE);
				isDarkMode = true;

			} else {
				everything.setStyle("-fx-background-color: #FFFFFF; ");
				mode.setTextFill(Color.BLACK);
				loginStatus.setTextFill(Color.BLACK);
				accountLabel.setTextFill(Color.BLACK);
				passwordLabel.setTextFill(Color.BLACK);
				isDarkMode = false;

			}
			keyboard.setDarkMode(isDarkMode);

		}
	}

	private RotateTransition createRotator(Node node) {
		RotateTransition rotator = new RotateTransition(Duration.millis(1000), node);
		rotator.setAxis(Rotate.Y_AXIS);
		rotator.setFromAngle(90);
		rotator.setToAngle(0);
		rotator.setInterpolator(Interpolator.LINEAR);
		rotator.setCycleCount(1);
		return rotator;
	}

	/**
	 * This method manages the audio that should be according to the current
	 * situation
	 *
	 * @param name - the name of the audio file that should be played
	 */
	public void playASong(String name) {
		// Need a File and URI object so the path works on all OSs
		File file = new File("audioFiles/" + name);
		URI uri = file.toURI();
		// Play one mp3 and and have code run when the song ends
		Media media = new Media(uri.toString());
		mediaPlayer = new MediaPlayer(media);
		mediaPlayer.play();
		mediaPlayer.setOnEndOfMedia(new Waiter());

	}

	private class Waiter implements Runnable {
		@Override
		public void run() {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				throw new RuntimeException(e);
			}
			mediaPlayer.dispose();
		}

	}
}
