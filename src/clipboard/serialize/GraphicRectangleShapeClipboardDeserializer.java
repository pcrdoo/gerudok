package clipboard.serialize;

import model.elements.GraphicShape;
import clipboard.SerializationDeserializationException;
import model.elements.GraphicRectangleShape;

/**
 * Deserializer for rectangle shapes.
 * 
 * @author geomaster
 *
 */
public class GraphicRectangleShapeClipboardDeserializer implements GraphicShapeClipboardDeserializer {
	/**
	 * Deserializes a rectangle from a string.
	 * 
	 * @param serializedString
	 *            String to deserialize from
	 */
	@Override
	public GraphicShape deserialize(String serializedString) throws SerializationDeserializationException {
		return new GraphicRectangleShape(new RectangleDeserializer().deserialize(serializedString));
	}
}
