package gerudok_observer;

import java.util.ArrayList;

public class ObserverList {
	private ArrayList<GeRuDokObserver> observers;
	
	public ObserverList() {
		observers = new ArrayList<>();
	}
	
	public void notifyObservers(GeRuDokObserverNotification notification, Object obj) {
		for(GeRuDokObserver obs : observers) {
			obs.update(notification, obj);
		}
	}
	
	public void addObserver(GeRuDokObserver observer) {
		observers.add(observer);
	}
}
