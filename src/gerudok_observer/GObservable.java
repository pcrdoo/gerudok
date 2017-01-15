package gerudok_observer;

/**
 * Own implementation of Observable interface.
 * 
 * Made to solve the problem with Java version of Observable being an abstract
 * class and limiting the inheritance possiblities of Observable classes due to
 * lack of multiple inheritance in the language.
 * 
 * @author Nikola Jovanovic
 *
 */
public interface GObservable {
	/**
	 * Registers a new observer.
	 * 
	 * @param obs
	 *            new observer to be added
	 */
	public void addObserver(GObserver obs);
}
