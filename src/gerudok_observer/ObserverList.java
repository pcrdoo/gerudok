package gerudok_observer;

import java.util.ArrayList;

public class ObserverList {
	private ArrayList<GObserver> observers;
	
	public ObserverList() {
		observers = new ArrayList<>();
	}
	
	public void notifyObservers(GObserverNotification notification, Object obj) {
		for(GObserver obs : observers) {
			obs.update(notification, obj);
		}
	}
	
	public void addObserver(GObserver observer) {
		observers.add(observer);
	}
}
