package gerudok_observer;

/**
 * Own implementation of Observer interface.
 * 
 * Made to solve the problem with Java version of Observable being an abstract
 * class and limiting the inheritance possiblities of Observable classes due to
 * lack of multiple inheritance in the language.
 * 
 * @author Nikola Jovanovic
 *
 */
public interface GObserver {
	/**
	 * Sends an update to all registered observers.
	 * 
	 * @param notification
	 *            notification that labels outgoing messages
	 * @param obj
	 *            auxiliary object sent with the message
	 */
	public void update(GNotification notification, Object obj);
}
