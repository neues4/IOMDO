package bachelorthesis.IOMDOProject.controller;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;

/**
 * 
 * @author romap1
 *
 */
public class IOMDocumentationController {


	@FXML
	private Button addRowBtn;

	@FXML
	private RowConstraints row2;

	@FXML
	private GridPane infoGrid;

	@FXML
	private SplitPane splitpane;

	@FXML
	private Button save;

	@FXML
	private TextField timeStartTF;
	
	@FXML
	private ComboBox<String> categoryIOMStart;
	
	@FXML
	private ComboBox<String> entryIOMStart;
	
	@FXML
	private TextField commentIOMStart;

	private int row = 2;

	private Map<String, Node> nodeList = new HashMap<String, Node>();

	public void initialize(URL location, ResourceBundle resources) {

	}

	/**
	 * 
	 * @param event
	 * @throws IOException
	 */
	public  void addRow(ActionEvent event) throws IOException {
		//create nessecary nodes
		TextField timeTF = new TextField();	
		ComboBox<String> categoryCB = new ComboBox<String>();
		ComboBox<String> entryCB = new ComboBox<String>();
		TextField commentTF = new TextField();
		Button deleteBtn = new Button();
		//E:\Bachelorthesis2021Workspace\maven.1618306824553\IOMDOProject\src\main\resources\bachelorthesis\IOMDOProject
		//Image img = new Image("/src/main/resources/bachelorthesis/IOMDOProject/173-bin.png");
	     //ImageView view = new ImageView(img);
	    // deleteBtn.setGraphic(view);
		//add nodes to HashMap, Key is ROW + Columnnumber. eg. Key = 21 for Node in ROW 2, Columne 1. 
		nodeList.put(row + "1", timeTF );
		nodeList.put(row + "2", categoryCB );
		nodeList.put(row + "3", entryCB );
		nodeList.put(row + "4", commentTF );


		//add delete event on delete Button
		deleteBtn.addEventHandler(ActionEvent.ACTION,
				new EventHandler<ActionEvent>() {
			@Override public void handle(ActionEvent event) {
				infoGrid.getChildren().remove(deleteBtn); 
				infoGrid.getChildren().remove(timeTF);
				infoGrid.getChildren().remove(categoryCB);
				infoGrid.getChildren().remove(entryCB);
				infoGrid.getChildren().remove(commentTF);
				row--;
			}
		});

		//Add nodes to Grid
		infoGrid.add(timeTF, 1, row);
		infoGrid.add(categoryCB , 2, row); 
		infoGrid.add(entryCB, 3, row);
		infoGrid.add(commentTF, 4, row);
		infoGrid.add(deleteBtn, 5, row);
		//removes Add button from row to place it a row further below
		infoGrid.getChildren().remove(addRowBtn);
		row++;
		infoGrid.add(addRowBtn, 1, row);

	}

	/**
	 * 
	 * @param event
	 * @throws IOException
	 */
	public  void save(ActionEvent event) throws IOException {
		
		System.out.println(timeStartTF.getText());
		//categoryIOMStart.getId();
		//entryIOMStart.getId();
		System.out.println(commentIOMStart.getText());

		int rowsToRead = nodeList.size()/4;
		for(int i= 1; i <= rowsToRead; i++) {
			System.out.println(getTextField(nodeList.get( i + 1 +"" + 1)).getText());
			getComboBox(nodeList.get( i + 1 +"" + 2));
			getComboBox(nodeList.get( i + 1 +"" + 3));
			System.out.println(getTextField(nodeList.get( i + 1 +"" + 4)).getText());
		
		}
		//Node node = nodeList.get("21");
		//TextField test =  (TextField) node.getClass().cast(node);
		//System.out.println(test.getText());

		//System.out.println(nodeList.size());
		//System.out.println(nodeList.get("31"));
		//	System.out.println(nodeList.get("22"));
	}

	/**
	public String getTextFromNode(Node node) {
		if (node.getClass().cast(node).getClass().equals(tf.getClass())) {
			TextField test = (TextField) node.getClass().cast(node);
			return test.getText();
		}
		
		return "";
	}
	*/
	
	
	@SuppressWarnings("exports")
	public TextField getTextField(Node node) {
		return (TextField) node.getClass().cast(node);
	}


	@SuppressWarnings("unchecked")
	public ComboBox<String> getComboBox(Node node) {
		return (ComboBox<String>) node.getClass().cast(node);
	}

}
