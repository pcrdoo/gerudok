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
	 * 
	 * Deserializes a shape from a string.
	 * 
	 * @param serializedString
	 *            String to deserialize from
	 * @return Deserialized shape
	 * @throws SerializationDeserializationException
	 *             If a data format or other deserialization error occurs.
	 */
	public GraphicShape deserialize(String serializedString) throws SerializationDeserializationException;
}
