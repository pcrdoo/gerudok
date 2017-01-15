package clipboard.serialize;

import model.elements.GraphicShape;
import clipboard.SerializationDeserializationException;
import model.elements.GraphicEllipseShape;

/**
 * Deserializer for ellipse shapes.
 * 
 * @author geomaster
 *
 */
public class GraphicEllipseShapeClipboardDeserializer implements GraphicShapeClipboardDeserializer {
	/**
	 * Deserializes an ellipse from a string.
	 * 
	 * @param serializedString
	 *            String to deserialize from
	 */
	public GraphicShape deserialize(String serializedString) throws SerializationDeserializationException {
		return new GraphicEllipseShape(new RectangleDeserializer().deserialize(serializedString));
	}
}
