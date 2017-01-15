package clipboard;

/**
 * Generic exception related to serialization or deserialization.
 * 
 * @author geomaster
 *
 */
public class SerializationDeserializationException extends Exception {
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
