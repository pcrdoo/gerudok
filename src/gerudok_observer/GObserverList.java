package gerudok_observer;

import java.util.ArrayList;

/**
 * A class that holds the list of observers to be maintained by an observable
 * class.
 * 
 * @author Nikola Jovanovic
 *
 */
public class GObserverList {
	/**
	 * The list of observers.
	 */
	private ArrayList<GObserver> observers;

	/**
	 * Constructor that initializes the list.
	 */
	public GObserverList() {
		observers = new ArrayList<>();
	}

	/**
	 * Sends a message to all registered observers.
	 * 
	 * @param notification
	 *            notification that labels outgoing messages
	 * @param obj
	 *            auxiliary object sent with the message
	 */
	public void notifyObservers(GNotification notification, Object obj) {
		for (GObserver obs : observers) {
			obs.update(notification, obj);
		}
	}

	/**
	 * Registers a new observer.
	 * 
	 * @param observer
	 *            new observer to be added
	 */
	public void addObserver(GObserver observer) {
		observers.add(observer);
	}
}
