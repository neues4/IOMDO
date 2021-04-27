package bachelorthesis.IOMDOProject.controller;

import java.io.IOException;

import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableCell;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.TreeTableColumn.CellDataFeatures;
import javafx.scene.control.TreeTableView;
import javafx.scene.layout.GridPane;
import javafx.util.Callback;

public class ProtocolOverviewController {

	@FXML
	private GridPane gridPaneProtocolOverview;
	
	@FXML
	private TextField TBDtestTF;
	
	@FXML
	private Button searchBtn;
	
	@FXML
	private TreeTableView<String> ProtocolTTView;
	
	@FXML
	private TreeTableColumn<String, String> surnameTTColumn;
	
	
	
	TreeItem<String> surname = new TreeItem<>("Tidus");
	
	public void test(ActionEvent event) throws IOException{
	
		TreeItem<String> childNode1 = new TreeItem<>("Child Node 1");
		TreeItem<String> root = new TreeItem<>("Root node");
		root.getChildren().setAll(childNode1);    
		
		surnameTTColumn.setCellValueFactory((CellDataFeatures<String, String> p) ->
		new ReadOnlyStringWrapper(p.getValue().getValue())); 
	
		 ProtocolTTView.setRoot(root);
		
	/**
		surnameTTColumn.setCellFactory(new Callback<TreeTableColumn<String,String>, TreeTableCell<String,String>>() {
			
			@Override
			public TreeTableCell<String, String> call(TreeTableColumn<String, String> param) {
				// TODO Auto-generated method stub
				return null;
			}
		});
		*/
		
	}
	
	
	
}
