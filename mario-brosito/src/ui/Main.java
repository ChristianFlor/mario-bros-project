package ui;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class Main extends Application{
	
	/**
	* The main function responsible of initiating the program.
	* @param args Used to launch the program.
	*/
	public static void main(String[] args) {
		launch(args);
	}

	/**
	* The start function which initializes the stage and displays it. Also deactivates the threads when the window is closed by using an anonymous nested class.
	* @param stage The main stage of the graphical interface.
	*/
	@Override
	public void start(Stage stage) throws Exception {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("menu.fxml"));
		Parent root = loader.load();
	
		Scene scene = new Scene(root);
		
		MenuController menu = loader.getController();
		stage.setOnCloseRequest(new EventHandler<WindowEvent>() {

			@Override
			public void handle(WindowEvent event) {
				menu.closeWindow();
			
			}
			
		});
		
		/*loader = new FXMLLoader(getClass().getResource("brosito.fxml"));
		loader.load();
		GameController gc = loader.getController();
		
		stage.setOnCloseRequest(new EventHandler<WindowEvent>() {

			@Override
			public void handle(WindowEvent event) {
				gc.closeWindow();
			
			}
			
		});*/
		
		
		
		stage.setScene(scene);
		stage.setTitle("MARIO BROSS");
		stage.show();
	}

}
