/*
 * LeaderboardGUI.java
 * Author: Amon Guinan
 */

package view_controller;

import java.util.ArrayList;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.Leaderboard;
import model.WordleAccount;

/**
 * 
 * @author Amon Guinan This class contains a JavaFX GUI for the Leaderboard
 *         object, available in the model folder.
 *
 */
public class LeaderboardGUI {
	private Leaderboard lb;
	private Text lb_out;

	/**
	 * Constructor. Initializes private vars.
	 */
	public LeaderboardGUI() {
		lb = new Leaderboard();
		lb_out = new Text("");
	}

	/**
	 * Constructor.
	 * 
	 * @param users Adds contents of users to initialized Leaderboard.
	 */
	public LeaderboardGUI(ArrayList<WordleAccount> users) {
		lb = new Leaderboard();
		for (WordleAccount u : users) {
			lb.addUser(u);
		}
		lb_out.setText(lb.toString());
	}

	/**
	 * Generates window.
	 */
	public void show() {

		BorderPane window = new BorderPane();

		VBox contents = new VBox();
		contents.setPadding(new Insets(15, 15, 15, 15));
		Font f = new Font("Courier", 30);
		Font f2 = new Font("Courier", 18);
		Font f3 = new Font("Courier", 22);
		Text header = new Text("Leaderboard");
		Text title = new Text("Total Wordles completed");
		header.setFont(f);
		title.setFont(f3);
		lb_out.setText(lb.toString());
		lb_out.setFont(f2);
		contents.getChildren().setAll(header, title, lb_out);
		contents.setSpacing(15);
		window.getChildren().setAll(contents);
		Stage s = new Stage();
		s.setWidth(400);
		s.setHeight(600);
		Scene scene = new Scene(window);
		s.setScene(scene);
		s.show();
	}

	/**
	 * @return leaderboard object
	 */
	public Leaderboard getLeaderboard() {
		return lb;
	}
}
