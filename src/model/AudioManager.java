package model;

/**
 * This code will play any song assuming that file is in folder songfiles. 
 * 
 * Programmer Jose Juan Velasquez
 */
import java.io.File;
import java.net.URI;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class AudioManager {

//	public static void main(String[] args) {
//		launch(args);
//	}
	private String pathStart;
	private MediaPlayer mediaPlayer;
	public AudioManager() {
		pathStart = "audioFiles/";
		//playASong(pathStart);
	}

//	@Override
//	public void start(Stage stage) throws Exception {
//		BorderPane pane = new BorderPane();
//		// I, Jose Velasquez, changed the song to be played before pushing to GitHub
//		String pathStart = "audiofiles/tileFlip.mp3";
//		playASong(pathStart);
//		// And swapped two lines
//		pane.setCenter(new Label(pathStart));
//		// Put the pane in a sized Scene and show the GUI
//		Scene scene = new Scene(pane, 255, 85); // 255 pixels wide, 85 pixels tall
//		stage.setScene(scene);
//		// Don't forget to show the running app:
//		stage.show();
//	}

	public void playASong(String name) {

		// Need a File and URI object so the path works on all OSs
		File file = new File(pathStart + name);
		URI uri = file.toURI();
		System.out.println(uri);
		// Play one mp3 and and have code run when the song ends
		Media media = new Media(uri.toString());
		mediaPlayer = new MediaPlayer(media);
		mediaPlayer.play();

		mediaPlayer.setOnEndOfMedia(new Waiter());
		System.out.println("You may need to shut this App down");

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