package ui;


import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.ImagePattern;
import javafx.stage.Stage;

public class Main extends Application{

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("brosito.fxml"));
		Parent root = loader.load();
		//MenuController mc = loader.getController();
		brositoController bC = loader.getController();
		Scene scene = new Scene(root);
		bC.setMainScene(scene);
		bC.configureScene();
		stage.setScene(scene);
		stage.setTitle("Mariosito");
		stage.show();
	}

}
