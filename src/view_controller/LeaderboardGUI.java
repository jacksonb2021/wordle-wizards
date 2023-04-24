/*
 * LeaderboardGUI.java
 * Author: Amon Guinan
 */

package view_controller;

import java.util.ArrayList;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.Leaderboard;
import model.WordleAccount;

public class LeaderboardGUI {
	private Leaderboard lb;
	private Text lb_out;
	
	public LeaderboardGUI(){
		lb = new Leaderboard();
		lb_out = new Text("");
	}
	
	public LeaderboardGUI(ArrayList<WordleAccount> users){
		lb = new Leaderboard();
		for(WordleAccount u : users) {
			lb.addUser(u);
		}
		lb_out.setText(lb.toString());
	}
	
	public void show() {
		
		BorderPane window = new BorderPane();
		VBox contents = new VBox();
		Font f = new Font("Courier", 30);
		Font f2 = new Font("Courier", 18);
		Text header = new Text("Leaderboard");
		header.setFont(f);
		lb_out.setText(lb.toString());
		lb_out.setFont(f2);
		
		contents.getChildren().setAll(header, lb_out);
		window.getChildren().setAll(contents);
		Stage s = new Stage();
		Scene scene = new Scene(window);
		s.setScene(scene);
		s.show();
	}
	
	public Leaderboard getLeaderboard() {
		return lb;
	}
}
