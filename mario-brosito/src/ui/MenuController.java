package ui;

import java.io.IOException;

import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;

public class MenuController {
	
    @FXML
    private StackPane stackPane;
    
    @FXML
    private Pane marioPane;

    @FXML
    private Label brandLabel;

    @FXML
    private Label playerLabel;

    @FXML
    public void beginGame(MouseEvent event) throws IOException {
    	Parent root = FXMLLoader.load(getClass().getResource("transition.fxml"));
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

}
