package ui;


import java.io.IOException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import model.Game;
import model.Score;


public class PlayerController {
	private Game g;
    @FXML
    private TextField tfName;

    @FXML
    private TextField tfNick;

    @FXML
    private TextField idName;

    @FXML
    private Label timeCheck111;

    @FXML
    private TextField idNick;

    @FXML
    private Label timeCheck1111;

    @FXML
    private ImageView foundScoreImg;

    @FXML
    private Label dName;

    @FXML
    private Label dScore;

    @FXML
    private Label dNick;

    @FXML
    private Label dCountry;

    @FXML
    private VBox vBoxList;

    @FXML
    private Canvas canvas;
    
    private TableView<Score> table;
    private ObservableList<Score> data;
    
    public void initialize() {
    	try {
			g= new Game();
			g.initPlayers();
	    	table =createTable();
	    	vBoxList.getChildren().add(table);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	
    }
    private TableView<Score> createTable(){
    	table = new TableView<Score>();
    	data = createData();
    	table.setEditable(true);

    	TableColumn<Score, String> name= new TableColumn<Score, String>("NAME");
    	name.setCellValueFactory(new PropertyValueFactory<Score, String>("name"));
    	
    	TableColumn<Score, String> nickName= new TableColumn<Score, String>("NICK");
    	nickName.setCellValueFactory(new PropertyValueFactory<Score, String>("nickName"));
    	
    	TableColumn<Score, Integer> score= new TableColumn<Score, Integer>("SCORE");
    	score.setCellValueFactory(new PropertyValueFactory<Score, Integer>("score"));
    	
    
    	table.setItems(data);
    	table.getColumns().addAll(name, nickName, score);
    	table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
    	
    	return table;
    }
    private ObservableList<Score> createData(){
    	data = FXCollections.observableArrayList();
    	
    	data.addAll(g.inorderListOfScore());
    	return data;
    }
    @FXML
    void inOrden(ActionEvent event) {
    	data.clear();
    	data.addAll(g.inorderListOfScore());
    }

    @FXML
    void postOrden(ActionEvent event) {
    	data.clear();
    	data.addAll(g.postorderListOfScore());
    }

    @FXML
    void preOrden(ActionEvent event) {
    	data.clear();
    	data.addAll(g.preorderListOfScore());
    }
    @FXML
    void register(ActionEvent event) {
    	
    }
    
    @FXML
    void searchName(ActionEvent event) {

    	if(g != null) {
    		String id = idName.getText();
        	long start = System.currentTimeMillis();
    		Score esp = g.searchName(id);
    		long endTime = (System.currentTimeMillis() - start);
           	timeCheck111.setText("Time: "+endTime);
        	if(id != "" && id != null) {
        		if(esp != null) {
        			foundScoreImg.setImage(new Image(esp.getPhoto()));
        			
        			dName.setText("Name: "+esp.getName());
        			dNick.setText("Nick Name: "+esp.getNickName());
        			dScore.setText("Email: "+esp.getScore());
        			
        		}else {
        			Alert a = new Alert(AlertType.INFORMATION);
            		a.setContentText("Player with Name: " + id + " not found");
            		a.show();
        		}
        		
        	} else {
        		Alert a = new Alert(AlertType.INFORMATION);
        		a.setContentText("Please type an name");
        		a.show();
        	}
    	} else {
    		Alert a = new Alert(AlertType.INFORMATION);
    		a.setContentText("Please load a file with the player info first");
    		a.show();
    	}
    	
    }

    @FXML
    void searchNick(ActionEvent event) {
    	if(g != null) {
    		String id = idNick.getText();
        	long start = System.currentTimeMillis();
    		Score espNick = g.searchNick(id);
    		long endTime = (System.currentTimeMillis() - start);
           	timeCheck1111.setText("Time: "+endTime);
        	if(id != "" && id != null) {
        		if(espNick != null) {
        			
        			foundScoreImg.setImage(new Image(espNick.getPhoto()));
        			dName.setText("Name: "+espNick.getName());
        			dNick.setText("Last Name: "+espNick.getNickName());
        			dScore.setText("Email: "+espNick.getScore());
        			
        		}else {
        			Alert a = new Alert(AlertType.INFORMATION);
            		a.setContentText("Player with Name: " + id + " not found");
            		a.show();
        		}
        		
        	} else {
        		Alert a = new Alert(AlertType.INFORMATION);
        		a.setContentText("Please type an name");
        		a.show();
        	}
    	} else {
    		Alert a = new Alert(AlertType.INFORMATION);
    		a.setContentText("Please load a file with the player info first");
    		a.show();
    	}
    }
    @FXML
    void generate(ActionEvent event) {
    	if(g!= null) {
    		canvas.getGraphicsContext2D().clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        	this.g.setWidth(canvas.getWidth());
        	this.g.setHeight(canvas.getHeight());
        	this.g.assignePositions();
        	this.canvas.getGraphicsContext2D().setLineWidth(3);
        	double ny = g.getTreeHeight()*90+50;
        	if(ny > canvas.getHeight()) {
        		canvas.setHeight(ny);
        	}
        	g.increaseBounds();
        	
        	this.canvas.setWidth(g.getWidth());
        	drawLinesForTree(g.getRoot(), g.getRoot());
        	drawImagesForTree(g.getRoot(), g.getRoot());
        	canvas.autosize();
    	} else {
    		Alert a = new Alert(AlertType.INFORMATION);
    		a.setContentText("Please load a file with the assistants info first");
    		a.show();
    	}
    }
    public void drawImagesForTree(Score node, Score parent) {
    	if(node != null) {
    		canvas.getGraphicsContext2D().drawImage(new Image(node.getPhoto()), node.getX(), node.getY());
    		canvas.getGraphicsContext2D().fillText(node.getScore()+"\n"+node.getName(), node.getX(), node.getY()+30);
    		drawImagesForTree(node.getLeft(), node);
    		drawImagesForTree(node.getRight(), node);
    	}
    }
    
    public void drawLinesForTree(Score node, Score parent) {
    	if(node != null) {
    		canvas.getGraphicsContext2D().strokeLine(parent.getX()+25, parent.getY()+25, node.getX()+25, node.getY()+25);
    		drawLinesForTree(node.getLeft(), node);
    		drawLinesForTree(node.getRight(), node);
    	}
    }

}
