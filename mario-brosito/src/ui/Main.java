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
		javafx.scene.paint.Color c = javafx.scene.paint.Color.rgb(93, 148, 251);
		scene.setFill(c);
	//	scene.setFill(new ImagePattern(new Image("uiImg/Mountain.png",1538,448,false,false))); name = new (); 
		scene.setOnKeyPressed(new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent e) {
				if(e.getCode().equals(KeyCode.D)) {
					bC.moveImage(1);
				}
				else if(e.getCode().equals(KeyCode.A)) {
					bC.moveImage(-1);
				}
			}
	    	
	    });
		stage.setScene(scene);
		stage.setTitle("Mariosito");
		stage.show();
	}

}
