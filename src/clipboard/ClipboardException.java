package clipboard;

/**
 * Exception that is thrown as a result of a failed clipboard read operation.
 * 
 * @author geomaster
 *
 */
public class ClipboardException extends Exception {
	/**
	 * Version UID for serialization.
	 */
	final static long serialVersionUID = 1;
	
	/**
	 * Constructor.
	 * 
	 * @param message
	 *            Exception message
	 */
	public ClipboardException(String message) {
		super(message);
	}
}
