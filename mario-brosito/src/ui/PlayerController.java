package ui;


import java.io.IOException;

import customExceptions.IllegalInputException;
import customExceptions.IntegerValuesException;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.StageStyle;
import model.Game;
import model.Player;
import model.Score;


public class PlayerController {
	
	/**
	 * The player controller's game.
	 */
	private Game g;
	
	private GameController game;
    @FXML
    private ComboBox<String> optionsSearch;
    @FXML
    private TextField tfName;

    @FXML
    private TextField tfNick;

    @FXML
    private TextField search;

    @FXML
    private Label timeCheck111;

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
    private Label dId;
    @FXML
    private Canvas canvas;
    
    private TableView<Player> table;
    private ObservableList<Player> data;
    
    public void initialize() throws IOException, IllegalInputException, IntegerValuesException {
    	g=new Game();
			//g= game.getGame();
			table =createTable();
	    	vBoxList.getChildren().add(table);
	    	optionsSearch.getItems().addAll("Id","Name","Nick","Score");

    	
    }
    private TableView<Player> createTable(){
    	table = new TableView<Player>();
    	data = createData();
    	table.setEditable(true);
    	TableColumn<Player, String> id= new TableColumn<Player, String>("ID");
    	id.setCellValueFactory(new PropertyValueFactory<Player, String>("id"));
    	
    	TableColumn<Player, String> name= new TableColumn<Player, String>("NAME");
    	name.setCellValueFactory(new PropertyValueFactory<Player, String>("name"));
    	
    	TableColumn<Player, String> nickName= new TableColumn<Player, String>("NICK");
    	nickName.setCellValueFactory(new PropertyValueFactory<Player, String>("nickName"));
    	
    	TableColumn<Player, Integer> score= new TableColumn<Player, Integer>("SCORE");
    	score.setCellValueFactory(new PropertyValueFactory<Player, Integer>("score"));
    	
    
    	table.setItems(data);
    	table.getColumns().addAll(id,name, nickName, score);
    	table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
    	
    	return table;
    }
    @FXML
    void search(ActionEvent event) {
    	String o= (String) optionsSearch.getValue();
    	try {
    		
	    	if(o.equals("Id")) {
	    		search.setPromptText("Enter your id");
	        	String id = search.getText();
	        	long start = System.currentTimeMillis();
	        	Player espNick = g.searchByCode(id);
	        	if(id != "" && id != null) {
	        		if(espNick != null) {
	        			
	        			foundScoreImg.setImage(new Image(espNick.getPhoto()));
	        			dName.setText("Name: "+espNick.getName());
	        			dNick.setText("NickName: "+espNick.getNickName());
	        			dScore.setText("Score: "+espNick.getScore());
	        			dId.setText("Id: "+espNick.getId());
	        			long endTime = (System.currentTimeMillis() - start)/1000;
	                   	timeCheck111.setText("Time: "+endTime);
	        		}else {
	        			Alert a = new Alert(AlertType.INFORMATION);
	            		a.setContentText("Player with Id: " + id + " not found");
	            		a.show();
	        		}
	        	} else {
	        		Alert a = new Alert(AlertType.INFORMATION);
	        		a.setContentText("Please type an id");
	        		a.show();
	        	}
	        		
	    	}else if(o.equals("Name")) {
	    		search.setPromptText("Enter your name");
	        	String id = search.getText();
	        	long start = System.currentTimeMillis();
	        	Player espNick = g.searchPlayerByName(g.getPlayersToArray(),id);
	        	if(id != "" && id != null) {
	        		if(espNick != null) {
	        			
	        			foundScoreImg.setImage(new Image(espNick.getPhoto()));
	        			dName.setText("Name: "+espNick.getName());
	        			dNick.setText("NickName: "+espNick.getNickName());
	        			dScore.setText("Score: "+espNick.getScore());
	        			dId.setText("Id: "+espNick.getId());
	        			long endTime = (System.currentTimeMillis() - start)/1000;
	                   	timeCheck111.setText("Time: "+endTime);
	        		}else {
	        			Alert a = new Alert(AlertType.INFORMATION);
	            		a.setContentText("Player with name: " + id + " not found");
	            		a.show();
	        		}
	        	} else {
	        		Alert a = new Alert(AlertType.INFORMATION);
	        		a.setContentText("Please type an name");
	        		a.show();
	        	}
	    	}else if(o.equals("Nick")) {
	    		search.setPromptText("Enter your nick");
	        	String id = search.getText();
	        	long start = System.currentTimeMillis();
	        	Player espNick = g.searchByNick(id);
	        	if(id != "" && id != null) {
	        		if(espNick != null) {
	        			
	        			foundScoreImg.setImage(new Image(espNick.getPhoto()));
	        			dName.setText("Name: "+espNick.getName());
	        			dNick.setText("NickName: "+espNick.getNickName());
	        			dScore.setText("Score: "+espNick.getScore());
	        			dId.setText("Id: "+espNick.getId());
	        			long endTime = (System.currentTimeMillis() - start)/1000;
	                   	timeCheck111.setText("Time: "+endTime);
	        		}else {
	        			Alert a = new Alert(AlertType.INFORMATION);
	            		a.setContentText("Player with Nick: " + id + " not found");
	            		a.show();
	        		}
	        	} else {
	        		Alert a = new Alert(AlertType.INFORMATION);
	        		a.setContentText("Please type an nick");
	        		a.show();
	        	}
	    	}else if(o.equals("Score")) {
	    		search.setPromptText("Enter your id");
	        	String id = search.getText();
	        	long start = System.currentTimeMillis();
	        	Player espNick = g.searchByScore(id);
	        	if(id != "" && id != null) {
	        		if(espNick != null) {
	        			
	        			foundScoreImg.setImage(new Image(espNick.getPhoto()));
	        			dName.setText("Name: "+espNick.getName());
	        			dNick.setText("NickName: "+espNick.getNickName());
	        			dScore.setText("Score: "+espNick.getScore());
	        			dId.setText("Id: "+espNick.getId());
	        			long endTime = (System.currentTimeMillis() - start)/1000;
	                   	timeCheck111.setText("Time: "+endTime);
	        		}else {
	        			Alert a = new Alert(AlertType.INFORMATION);
	            		a.setContentText("Player with Score: " + id + " not found");
	            		a.show();
	        		}
	        	} else {
	        		Alert a = new Alert(AlertType.INFORMATION);
	        		a.setContentText("Please type an score");
	        		a.show();
	        	}
	    	}
    	}catch(NumberFormatException e) {
    		Alert info = new Alert(AlertType.ERROR);
        	info.setTitle("Simulation");
        	info.setHeaderText(null);
        	info.initStyle(StageStyle.UTILITY);
        	info.setContentText("Please introduce a value valid");
        	info.show();
    	} 
    }
    @FXML
    void sortId(ActionEvent event) {
    	data.clear();
    	g.sortByCode();
    	data.addAll(g.getPlayersToArray());
    }

    @FXML
    void sortName(ActionEvent event) {
    	data.clear();
    	g.sortByName();
    	data.addAll(g.getPlayersToArray());
    }

    @FXML
    void sortNick(ActionEvent event) {
    	data.clear();
    	g.sortByNick();
    	data.addAll(g.getPlayersToArray());
    }

    @FXML
    void sortScore(ActionEvent event) {
    	data.clear();
    	g.sortByScore();
    	data.addAll(g.getPlayersToArray());
    }

    private ObservableList<Player> createData(){
    	data = FXCollections.observableArrayList();
    	
    	data.addAll(g.getPlayersToArray());
    	return data;
    }
    @FXML

    void register(ActionEvent event){
    	data.clear();
       	//int n = Integer.parseInt(game.getScoreOfMario().getText());
    	try {
			g.addPlayer(tfName.getText(),tfNick.getText(),10000);
			g.addScore(g.searchPlayer(tfName.getText()));
			Alert a = new Alert(AlertType.CONFIRMATION);
    		a.setContentText("The player is register");
    		a.show();
		} catch (IllegalInputException e) {
			Alert a = new Alert(AlertType.ERROR);
    		a.setContentText(e.getMessage());
    		a.show();
		} catch (IntegerValuesException e) {
			Alert a = new Alert(AlertType.ERROR);
    		a.setContentText(e.getMessage());
    		a.show();
		}
    	data.addAll(g.getPlayersToArray());
    }
   
    @FXML
    void generateTree(ActionEvent event) {
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
    		
    		canvas.getGraphicsContext2D().setFill(Color.YELLOW);
    		canvas.getGraphicsContext2D().fillOval(node.getX(), node.getY(), 70, 70);
    		canvas.getGraphicsContext2D().setFill(Color.BLACK);
    		canvas.getGraphicsContext2D().strokeOval(node.getX(), node.getY(), 70, 70);
    		canvas.getGraphicsContext2D().fillText(node.getScore()+"\n"+node.getName(), node.getX()+6, node.getY()+30);
    		canvas.getGraphicsContext2D().drawImage(new Image(node.getPhoto()), node.getX()+50, node.getY(),60,60);
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
    @FXML
    void generateList(ActionEvent event) {
    	if(g!= null) {
    		canvas.getGraphicsContext2D().clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        	this.g.setWidth(canvas.getWidth());
        	this.g.setHeight(canvas.getHeight());
        	this.g.assignePositionsList();
        	this.canvas.getGraphicsContext2D().setLineWidth(3);
        	double ny = g.getTreeHeight()*90+50;
        	if(ny > canvas.getHeight()) {
        		canvas.setHeight(ny);
        	}
        	g.increaseBounds();
        	this.canvas.setWidth(g.getWidth());
        	drawLinesForList(g.getFirst());
        	drawImagesForList(g.getFirst());
    	} else {
    		Alert a = new Alert(AlertType.INFORMATION);
    		a.setContentText("Please load a file with the assistants info first");
    		a.show();
    	}
    }

    public void drawImagesForList(Player node) {
    	if(node != null) {
    		
    		canvas.getGraphicsContext2D().drawImage(new Image(node.getPhoto()), node.getX(), node.getY()+10,60,60);
    		canvas.getGraphicsContext2D().fillText(node.getName()+"", node.getX(), node.getY());
    		drawImagesForList(node.getNext());
    	}
    }
    
    public void drawLinesForList(Player node) {
    	if(node != null) {
    		if(node.getNext() != null) {
    			canvas.getGraphicsContext2D().strokeLine(node.getX()+25, node.getY()+25, node.getNext().getX()+25, node.getNext().getY()+25);
    		}
    		if(node.getPrev() != null) {
    			canvas.getGraphicsContext2D().strokeLine(node.getX()+25, node.getY()+50, node.getPrev().getX()+25, node.getPrev().getY()+50);
    		}
    		drawLinesForList(node.getNext());
    	}
    }
}
