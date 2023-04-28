package view_controller;

import java.io.File;
import java.net.URI;
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
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Paint;
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
	private MediaPlayer mediaPlayer;
	private BorderPane everything;
	private Label mode;
	Button[][] boardGameRs = new Button[6][5];
	ArrayList<ArrayList<Button>> keyboardRows = new ArrayList<>();
	protected Keyboard keyboard;
	private LocalDate localDate;
	private Button login;
	private Button logout;
	private boolean dailyOrRandom;
	private LeaderboardGUI leaderboardWindow;
	private int curBoxX = 0;
	private int curBoxY = 0;

	@Override
	public void start(Stage stage) throws Exception {
		stage.setTitle("Wordle");
		wordle = new Wordle(true);
		dailyOrRandom = true;
		field = new TextField();
		mode = new Label("Daily word");
		//audio = new AudioManager();

		field.setOnAction(event -> {
			String text = field.getText();
			System.out.println(text);
			buttonHandler.handle(event);

		});

		leaderboardWindow = new LeaderboardGUI();
		for (WordleAccount u : wordle.getAccounts()) {
			System.out.println(u.getUsername());
			leaderboardWindow.getLeaderboard().addUser(u);
		}
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
		if (event.getCode() == KeyCode.ENTER) {
			buttonHandler.handle(new ActionEvent());
			return;
		}
		if (loginPane.isFocusWithin())
			return;

		if (event.getCode() == KeyCode.BACK_SPACE) {
			if (curBoxX != 0)
				curBoxX--;
			if (curBoxX > 4)
				curBoxX = 4;
			if (curBoxX <= 0) {
				curBoxX = 0;

			}
			boardGameRs[curBoxY][curBoxX].setText(key);

		} else if (curBoxX < 5) {
			boardGameRs[curBoxY][curBoxX].setText(key);
			curBoxX++;
		}

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

	private void resetGame(Boolean logOut) {
		setBoard();
		wordle = new Wordle(false);
		dailyOrRandom = false;
		// this.keyboard = new Keyboard("QWERTYUIOPASDFGHJKLZXCVBNM".toCharArray());
		layoutKeyboard();

		field.setEditable(true);
		button.setDisable(false);
		// loginPane.logout();

		counter = 0;
		mode.setText("Practice mode (It will not count towards the leaderboards)");
		button.setText("submit guess");

	}

	// ONLY BE USED WHEN A USER HASNT PLAYED THAT DAY
	private void freshNewGame() {
		setBoard();
		wordle = new Wordle(true);
		dailyOrRandom = true;
		// this.keyboard = new Keyboard("QWERTYUIOPASDFGHJKLZXCVBNM".toCharArray());
		layoutKeyboard();
		field.setEditable(true);
		button.setDisable(false);
		// loginPane.logout();
		counter = 0;
		mode.setText("Daily word");
		button.setText("submit guess");
	}

	private Button[] ButtonMaker() {
		Button[] temp = new Button[5];
		for (int i = 0; i < 5; i++) {
			temp[i] = new Button("");
			temp[i].setMinWidth(40);
			temp[i].setStyle("-fx-padding: 5 10 10 10;");
			temp[i].setFont(new Font("Courier New", 25));
			temp[i].setBackground(null);
			temp[i].setBorder(Border.stroke(Paint.valueOf("black")));
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
			header = "The word was " + wordle.getWord(dailyOrRandom) + "\n\nScore Summary";
			playASong("gameOverWin.mp3");
		} else {
			content = "Game over. You lose\n" + account.getScoreString();
			header = "The word was " + wordle.getWord(dailyOrRandom) + "\n\nScore Summary";
			playASong("gameOverLoss.mp3");
		}
		scoreAlert.setContentText(content);
		scoreAlert.setHeaderText(header);

		Optional<ButtonType> result = scoreAlert.showAndWait();

		if (result.get() == ButtonType.OK) {
			// loginPane.logout();
			everything.setDisable(false);
			if (dailyOrRandom) {
				freshNewGame();
			} else {
				resetGame(true);
			}
		} else {
			// If you can figure out how to just make this alert one
			// button feel free to do so
			// loginPane.logout();
			everything.setDisable(false);
			if (dailyOrRandom) {
				freshNewGame();
			} else {
				resetGame(true);
			}
		}
		leaderboardWindow.getLeaderboard().addUser(account);
//		scoreAlert.show();
	}

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
			// wordle.setRandomWord(5);
			resetGame(true);
		});

		practiceMode.setOnAction(event -> {
			mode.setText("Practice mode (It will not count towards the leaderboards)");
			buttonHandler.mode = false;
			resetGame(true);
		});

		login = loginPane.getLoginButton();
		logout = loginPane.getLogoutButton();

		login.setOnAction(event -> {
			loginPane.login();
			if (loginPane.isLoggedIn()) {
				field.setEditable(true);
				button.setDisable(false);
				localDate = loginPane.getCurrentUser().getLastPlayed();

				System.out.println(localDate);
				System.out.println(LocalDate.now());
				boolean isDifferentDay = !(localDate.equals(LocalDate.now()));
				if (isDifferentDay) {
					freshNewGame();
				} else {
					Alert alreadyWordled = new Alert(AlertType.INFORMATION,
							"You've already played a game of Wordle today. Going to practice mode.");
					alreadyWordled.showAndWait();
					resetGame(false);
				}
			}

		});

		logout.setOnAction(event -> {
			loginPane.logout();
			freshNewGame();
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
			// get the text from boxes and make sure its a length or something. fire
			// evenmt??
			Button[] gues = boardGameRs[curBoxY];
			String guess = "";
			for (Button but : gues) {
				guess += but.getText();
			}
			System.out.println(guess);
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
			curBoxX = 0;
			curBoxY = counter;
			if (winCondition(curBoard)) {
				account.setLastPlayed(LocalDate.now());
				everything.setDisable(true);

//				account.updateScore(counter + 1);
//				wordle.updateAccount(account);
//				wordle.save();
				field.setEditable(false);
				button.setDisable(true);
				showScore(false, true, true);
				loginPane.logout();
				curBoxX = 0;
				curBoxY = 0;
			} else if (counter == boardGameRs.length) {
				account.setLastPlayed(LocalDate.now());
				everything.setDisable(true);
				System.out.println("Game over.\n the word was " + wordle.getWord(mode) + "\n");
//				account.updateScore(counter + 1);
//				wordle.updateAccount(account);
//				wordle.save();
				showScore(false, false, true);
				field.setEditable(false);
				button.setDisable(true);
				curBoxX = 0;
				curBoxY = counter;
			}

//			everything.setDisable(false);

		}

		private boolean winCondition(Button[] boardGame) {
			for (int i = 0; i < boardGame.length; i++) {
				if (!boardGame[i].getStyle().contains("-fx-background-color: #00FF00; ")) {
					return false;
				}
			}
			account = loginPane.getCurrentUser();
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
						case WRONG -> {
							if (!key.getStyle().contains("-fx-background-color")) {
								key.setStyle("-fx-background-color: #808080; ");
							}
						}
						case CORRECT -> key.setStyle("-fx-background-color: #00FF00; ");

						case CONTAINS -> {

							if (!key.getStyle().contains("-fx-background-color: #00FF00;")) {
								key.setStyle("-fx-background-color: #FFFF00; ");
							}
						}

						}
					}
				}
			}));
		}

		private Button[] colorBoard(String guess, int[] guessStr, Button[] boardGameR) {
			String[] temp = guess.split("");
			for (int i = 0; i < 5; i++) {
				//sleep();
				boardGameR[i].setText(temp[i].toUpperCase());
				playASong("tileFlip.mp3");
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
			keyboard.setDarkMode(isDarkMode);

		}
	}
	
	public void playASong(String name) {
		// Need a File and URI object so the path works on all OSs
		File file = new File("audioFiles/" + name);
		URI uri = file.toURI();
		System.out.println(uri);
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
