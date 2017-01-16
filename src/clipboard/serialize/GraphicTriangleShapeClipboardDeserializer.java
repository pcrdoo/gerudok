package clipboard.serialize;

import clipboard.SerializationDeserializationException;
import model.elements.GraphicShape;
import model.elements.GraphicTriangleShape;

/**
 * Deserializer for triangle shapes.
 * 
 * @author geomaster
 *
 */
public class GraphicTriangleShapeClipboardDeserializer implements GraphicShapeClipboardDeserializer {
	/**
	 * Deserializes a triangle shape from a string.
	 * 
	 * @param serializedString
	 *            String to deserialize from
	 */
	@Override
	public GraphicShape deserialize(String serializedString) throws SerializationDeserializationException {
		return new GraphicTriangleShape(new RectangleDeserializer().deserialize(serializedString));
	}
}
