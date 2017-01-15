package clipboard;

public class InvalidDataFormatException extends SerializationDeserializationException {
	public InvalidDataFormatException(String message) {
		super(message);
	}
}
