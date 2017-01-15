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
	 * Constructor.
	 * 
	 * @param message
	 *            Exception message
	 */
	public UnknownShapeTypeException(String message) {
		super(message);
	}
}
