package translink;


import java.time.LocalTime;
import java.util.List;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage; 
import javafx.event.ActionEvent; 

public class Graphics extends Application {
	
	private ArrivalTable table = new ArrivalTable(); 
	
	TextField stopField = new TextField(); 
	TextField routeField = new TextField(); 
	
	public static void main(String[] args) {
		launch(args); 
	}
	
	
	@Override
	public void start(Stage stage) throws Exception {
		Scene scene = new Scene (new Group()); 
		stage.setWidth(500); 
		stage.setHeight(800); 
		
		final Label label = new Label("Bus Times"); 
		final Label stopLabel = new Label("Stop #"); 
		final Label routeLabel = new Label("Route #");
		
		
		
		Button addButton = new Button("Add");
		addButton.setOnAction(new EventHandler<ActionEvent>() { 
			@Override
			public void handle(ActionEvent event) { 
				addData(); 
			}
		});
		
		Button refreshButton = new Button("Refresh"); 
		refreshButton.setOnAction(new EventHandler<ActionEvent>() { 
			@Override
			public void handle(ActionEvent event) { 
				refreshData(); 
			}
		});
		
		Button clearButton = new Button("Clear"); 
		clearButton.setOnAction(new EventHandler<ActionEvent>() { 
			@Override
			public void handle(ActionEvent event) { 
				clearData(); 
			}
		});
		
		final VBox vbox = new VBox() ; 
		vbox.setSpacing(5);
		vbox.setPadding(new Insets(10, 0, 0, 10));
		vbox.getChildren().addAll(label, table, stopLabel, stopField, routeLabel, routeField, addButton, refreshButton, clearButton); 
		
		((Group) scene.getRoot()).getChildren().addAll(vbox); 
		
		stage.setScene(scene);
		stage.show(); 
	}
	
	private void addData() { 
		int stop = Integer.parseInt(stopField.getCharacters().toString().trim());
		if (routeField.getCharacters().toString().trim().equals("")) {
			table.addArrivals(stop);
		} else {
			int route = Integer.parseInt(routeField.getCharacters().toString().trim()); 
			table.addArrivals(stop, route); 
		}  
	}
	
	private void refreshData() { 
		table.refreshArrivals(); 
	}
	
	private void clearData() { 
		table.clearArrivals();
	}
	
}
