package translink;

import java.time.LocalTime;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class Arrival {
	
	
	
	private final SimpleStringProperty time;
	private final SimpleIntegerProperty stopNumber; 
	private final SimpleIntegerProperty routeNumber; 
	
	
	Arrival(LocalTime time, int stopNumber, int routeNumber) { 
		this.time = new SimpleStringProperty(time.toString()); 
		this.stopNumber = new SimpleIntegerProperty(stopNumber); 
		this.routeNumber = new SimpleIntegerProperty(routeNumber); 
	}
	
	public String getTime() { 
		return time.get(); 
	}
	
	public int getStopNumber() { 
		return stopNumber.get(); 
	}
	
	public int getRouteNumber() { 
		return routeNumber.get(); 
	}
}
