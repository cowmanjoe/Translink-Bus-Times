package translink;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class ArrivalTable extends TableView {
	
	private ObservableList<Arrival> arrivals; 
	
	// map with stop number as key and list of associated route numbers 
	// if list is empty then search all 
	private Map<Integer, List<Integer>> searches; 
	
	public ArrivalTable() { 
		setEditable(true);
		arrivals = FXCollections.observableArrayList(); 
		searches = new HashMap<Integer, List<Integer>>(); 
		
		TableColumn stopNumberCol = new TableColumn("Stop Number"); 
		stopNumberCol.setMinWidth(150); 
		stopNumberCol.setCellValueFactory(new PropertyValueFactory<Arrival, Integer>("stopNumber"));
		
		TableColumn routeNumberCol = new TableColumn("Route Number"); 
		routeNumberCol.setMinWidth(150);; 
		routeNumberCol.setCellValueFactory(new PropertyValueFactory<Arrival, Integer>("routeNumber"));
		
		TableColumn timeCol = new TableColumn("Expected Time"); 
		timeCol.setMinWidth(150);
		timeCol.setCellValueFactory(new PropertyValueFactory<Arrival, String>("time"));
		
		setItems(arrivals);
		getColumns().addAll(stopNumberCol, routeNumberCol, timeCol); 
	}
	
	
	public void addArrivals(int stop) { 
		List<Arrival> as = DataFetcher.getArrivals(stop); 
		arrivals.addAll(as); 
		searches.put(stop, new ArrayList<Integer>()); 
	}
	
	public void addArrivals(int stop, int route) { 
		List<Arrival> as = DataFetcher.getArrivals(stop, route); 
		arrivals.addAll(as); 
		List<Integer> routes = searches.get(stop); 
		if (routes == null) { 
			routes = new ArrayList<Integer>(); 
			routes.add(route); 
			searches.put(stop, routes); 
		}
		else {
			routes.add(route); 
		}
	}
	
	public void clearArrivals() { 
		arrivals.clear(); 
		searches.clear(); 
	}
	
	public void refreshArrivals() { 
		arrivals.clear(); 
		for (Integer stop : searches.keySet()) System.out.println("Stop: " + stop);
		
		
		for (Integer stop : searches.keySet()) { 
			List<Integer> routes = searches.get(stop); 
			if (routes.size() == 0) { 
				arrivals.addAll(DataFetcher.getArrivals(stop));
			}
			else {
				for (Integer route : routes) 
					arrivals.addAll(DataFetcher.getArrivals(stop, route)); 
			}
		}
	}
}
