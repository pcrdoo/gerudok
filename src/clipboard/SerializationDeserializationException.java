package clipboard;

/**
 * Generic exception related to serialization or deserialization.
 * 
 * @author geomaster
 *
 */
public class SerializationDeserializationException extends Exception {
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
	public SerializationDeserializationException(String message) {
		super(message);
	}
}
