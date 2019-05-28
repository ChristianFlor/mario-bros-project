package ui;

import javax.sound.sampled.Clip;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import model.SoundsLoader;

public class Main extends Application{

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("menu.fxml"));
		Parent root = loader.load();
	
		Scene scene = new Scene(root);
		loader = new FXMLLoader(getClass().getResource("brosito.fxml"));
		loader.load();
		GameController gc = loader.getController();
		stage.setOnCloseRequest(new EventHandler<WindowEvent>() {

			@Override
			public void handle(WindowEvent event) {
				gc.closeWindow();
			}
			
		});
		Clip bang = SoundsLoader.cargarSonido("/uilmusic/Ground_Theme.wav");
    	bang.start();
		
		stage.setScene(scene);
		stage.setTitle("Mariosito");
		stage.show();
	}

}
