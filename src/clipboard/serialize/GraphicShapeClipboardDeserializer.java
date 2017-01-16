package clipboard.serialize;

import clipboard.SerializationDeserializationException;
import model.elements.GraphicShape;

/**
 * Deserializer for a generic shape.
 * 
 * @author geomaster
 *
 */
public interface GraphicShapeClipboardDeserializer {
	/**
	 * TODO (David Davidovic): Fix javadoc.
	 * Deserializes a shape from a string.
	 * 
	 * @param serializedString
	 *            String to deserialize from
	 */
	public GraphicShape deserialize(String serializedString) throws SerializationDeserializationException;
}
