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

/**
 * A keyboard, represented by rows of Button objects.
 *
 * @author Duke Speed
 */
public class Keyboard extends VBox {
	private HashMap<String, Key> keyMap;

	public Keyboard(char[] keys) {
		super();
		List<HBox> rows = generateKeys(keys, 3, 10);
		VBox board = new VBox();
		board.getChildren().addAll(rows);
		this.getChildren().addAll(rows);
	}

	/**
	 * This function sets all keys in the keyboard to a format that looks better
	 * with a black background.
	 * 
	 * @param dark if true, enables dark mode. if false, enables light mode
	 */
	public void setDarkMode(boolean dark) {
		for (Key key : this.getKeys()) {
			key.setDarkMode(dark);
		}
	}

	/**
	 * This function generates a list of rows/HBoxs of buttons that correspond to &
	 * the characters given in keys
	 * 
	 * @param keys         the list of characters to put on the keyboard
	 * @param rowCount     the max number of rows
	 * @param maxRowLength the max length of each row
	 * @return list of HBox objects that contain Buttons
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
			newRow.setStyle("-fx-padding: 5 10 10 10;");

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

	/**
	 * Returns a Collection object that consists of all the keys in the keyboard.
	 * 
	 * @return all the key objects in the keyboard
	 */
	public Collection<Key> getKeys() {
		return this.keyMap.values();
	}

	/**
	 * Returns the Key object that the letter corresponds to.
	 * 
	 * @param letter the letter linked to the Key we want to find
	 * @return a Key that corresponds to a letter
	 */
	public Key getKey(String letter) {
		return keyMap.get(letter);
	}

	/**
	 * Represents a single key on a keyboard, can represent any character or string
	 * of characters.
	 */
	static class Key extends Button {
		private final String keyChar;

		public Key(String letter) {
			super(letter);
			this.keyChar = letter;

			// default style options, can be changed if wanted.
			this.setStyle("-fx-padding: 5 10 10 10; " + "background: white; "
					+ "-fx-text-fill: ladder(background, white 49%, black 50%);");
			this.setFont(new Font("Arial", 25));
			this.setBackground(null);

		}

		/**
		 * Returns the value that the Key is set to.
		 * 
		 * @return the letter with which this Key object corresponds to
		 */
		public String getKeyVal() {
			return keyChar;
		}

		/**
		 * Manages the color the keyboard should be based on the current mode
		 * 
		 * @param a boolean indicating whether dark mode is on or off
		 */
		public void setDarkMode(boolean darkMode) {
			if (darkMode) {
				this.setStyle("-fx-padding: 5 10 10 10; " + "background: black; "
						+ "-fx-text-fill: ladder(background, white 49%, black 50%);");
			} else {
				this.setStyle("-fx-padding: 5 10 10 10; " + "background: white; "
						+ "-fx-text-fill: ladder(background, white 49%, black 50%);");
			}
		}
	}
}