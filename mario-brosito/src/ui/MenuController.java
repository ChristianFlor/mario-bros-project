package ui;

import java.io.IOException;

import javax.sound.sampled.Clip;

import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;
import model.SoundsLoader;




public class MenuController {
	
	private TransitionController transition;
	
	private PlayerController playerController;
	
	
	
    @FXML
    private StackPane stackPane;
    
    @FXML
    private Pane marioPane;

    @FXML
    private Label brandLabel;

    @FXML
    private Label playerLabel;
    
    @FXML
    private Label optionsButton;

    @FXML
    private Label scoresLabel;
    @FXML
    public void initialize() {
    	BackgroundImage myBI= new BackgroundImage(new Image("menuimg/back.png",685,480,false,true),
    	        BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
    			new BackgroundSize(685, 480, false, false, false, false));
  
    	marioPane.setBackground(new Background(myBI));

    }
    

    @FXML
    public void beginGame(MouseEvent event) throws IOException {
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("transition.fxml"));
    	 Parent root = loader.load();
    	Scene scene = playerLabel.getScene();
    	
    	transition = loader.getController();
    	root.translateXProperty().set(scene.getHeight());
    	stackPane.getChildren().add(root);
    	
    	Timeline timeline = new Timeline();
    	KeyValue kv = new KeyValue(root.translateXProperty(),0, Interpolator.EASE_IN);
    	KeyFrame kf = new KeyFrame(Duration.seconds(1),kv);
    	timeline.getKeyFrames().add(kf);
    	timeline.setOnFinished(event1-> {
    		stackPane.getChildren().remove(marioPane);
    	});                               
    	timeline.play();
    }
    

    @FXML
    void beginOptions(MouseEvent event) {

    }

    @FXML
    public void seeScoresMenu(MouseEvent event) throws IOException {
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("gui.fxml"));
    	 Parent root = loader.load();
    	 
    	 playerController = loader.getController();
    	 Scene scene = playerLabel.getScene();
    	
    	
    	
    	root.translateXProperty().set(scene.getHeight());
    	stackPane.getChildren().add(root);
    	
    	Timeline timeline = new Timeline();
    	KeyValue kv = new KeyValue(root.translateXProperty(),0, Interpolator.EASE_IN);
    	KeyFrame kf = new KeyFrame(Duration.seconds(1),kv);
    	timeline.getKeyFrames().add(kf);
    	timeline.setOnFinished(event1-> {
    		stackPane.getChildren().remove(marioPane);
    	});                               
    	timeline.play();

    }
    
    
    public void closeWindow() {
    	try {
			if(playerController!=null)
				playerController.onCloseRequest();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
    	if(transition!=null)
    		transition.closeWindow();
    }

}
