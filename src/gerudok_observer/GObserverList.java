package gerudok_observer;

import java.util.ArrayList;

public class GObserverList {
	private ArrayList<GObserver> observers;
	
	public GObserverList() {
		observers = new ArrayList<>();
	}
	
	public void notifyObservers(GNotification notification, Object obj) {
		for(GObserver obs : observers) {
			obs.update(notification, obj);
		}
	}
	
	public void addObserver(GObserver observer) {
		observers.add(observer);
	}
}
