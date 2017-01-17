package clipboard;

/**
 * Exception that is thrown when an attempt to deserialize a shape of an unknown
 * type occurs.
 * 
 * @author geomaster
 *
 */
public class UnknownShapeTypeException extends SerializationDeserializationException {
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
	public UnknownShapeTypeException(String message) {
		super(message);
	}
}
