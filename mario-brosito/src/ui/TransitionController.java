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
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;

public class TransitionController {

	    /**
	     * The pane of the transition.
	     */
	    @FXML
	    private Pane transitionPane;

	    /**
	     * The start image of Mario.
	     */
	    @FXML
	    private ImageView startImg;

	  //  private GameController bC;

	    /**
	     * <b>Description:</b>
	     * This function starts the main Mario game.
	     * @param event The click event on the image.
	     * @throws IOException Throws if the file is not found.
	     */
	    @FXML
	    public void start(MouseEvent event) throws IOException {

	     	FXMLLoader loader = new FXMLLoader(getClass().getResource("brosito.fxml"));
	    	Parent root =  loader.load();
	    	Scene scene = startImg.getScene();

	    	transitionPane.getChildren().add(root);


			GameController bC = loader.getController();

	    	bC.setMainScene(scene);
	    	bC.configureScene();
	    	if(!(bC.getPause())) {
	    		bC.getClip().start();
	    	}

	    	root.translateXProperty().set(scene.getHeight());


	    	Timeline timeline = new Timeline();
	    	KeyValue kv = new KeyValue(root.translateXProperty(),0, Interpolator.EASE_IN);
	    	KeyFrame kf = new KeyFrame(Duration.seconds(1),kv);
	    	timeline.getKeyFrames().add(kf);
	    	timeline.setOnFinished(event1-> {
	    		transitionPane.getChildren().clear();
	    		javafx.scene.paint.Color c = javafx.scene.paint.Color.rgb(93, 148, 251);
	    		//javafx.scene.paint.Color c = javafx.scene.paint.Color.rgb(0, 0, 0);
	    		scene.setFill(c);
	    		transitionPane.getChildren().add(root);

	    		transitionPane.setBackground(new Background(new BackgroundFill(c, null, null)));
	    	});                               
	    	timeline.play();
	    }
	}