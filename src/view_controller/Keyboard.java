package view_controller;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

public class Keyboard extends VBox {
	private List<HBox> rows;
	private VBox board;
	private HashMap<String, Key> keyMap;

	/**
	 *
	 * @param keys
	 */
	public Keyboard(char[] keys) {
		super();
		this.rows = generateKeys(keys, 3, 10);
		this.board = new VBox();
		this.board.getChildren().addAll(this.rows);
		this.getChildren().addAll(this.rows);
	}

	/**
	 * This function sets all keys in the keyboard to a format that looks better
	 * with a black background.
	 * 
	 * @param dark if true, enables dark mode. if false, enables light mode
	 */
	public void setDarkMode(boolean dark) {

	}

	/**
	 * This function generates a list of rows/HBoxs of buttons that correspond to
	 * the characters given in keys
	 * 
	 * @param keys     the list of characters to put on the keyboard
	 * @param rowCount the max number of rows
	 * @paraam maxRowLength the max length of each row
	 * @return
	 */
	private List<HBox> generateKeys(char[] keys, int rowCount, int maxRowLength) {
		this.keyMap = new HashMap<>();
		List<HBox> keyBoardGui = new ArrayList<>();

		int curEndPoint = maxRowLength;
		int startPoint = 0;

		for (int i = 0; i < rowCount; i++) {
			HBox newRow = new HBox();
			newRow.setSpacing(5);
			newRow.setAlignment(Pos.CENTER);

			// create the buttons that we need to place on this row
			for (int j = 0; j < curEndPoint && j + startPoint < keys.length; j++) {
				String val = "" + keys[j + startPoint];
				Key keyButton = new Key(val);
				keyMap.put(val, keyButton);
				newRow.getChildren().add(keyButton);
			}

			// add the row we just made, and continue on
			keyBoardGui.add(newRow);
			startPoint += curEndPoint;
			curEndPoint--;
		}

		return keyBoardGui;
	}

	public Collection<Key> getKeys() {
		return this.keyMap.values();
	}

	/**
	 * Returns the Key object that the letter corresponds to.
	 * 
	 * @param letter the letter linked to the Key we want to find
	 * @return a Key that corresponds to letter
	 */
	public Key getKey(String letter) {
		return keyMap.get(letter);
	}

	/**
	 *
	 */
	class Key extends Button {

		private String keyChar;

		/**
		 * Constructor. Links the letter to the object for later retrieval.
		 * 
		 * @param letter
		 */
		public Key(String letter) {
			super(letter);
			this.keyChar = letter;

			// default style options, can be changed if wanted.
			this.setStyle("-fx-padding: 5 10 10 10;");
			this.setFont(new Font("Courier New", 25));
			this.setBackground(null);
			this.setOnAction(event -> {
				Button buttonClicked = (Button) event.getSource();
				System.out.println(buttonClicked.getText());
			});
		}

		/**
		 * @return the letter with which this Key object corresponds to
		 */
		public String getKeyVal() {
			return keyChar;
		}
	}
}