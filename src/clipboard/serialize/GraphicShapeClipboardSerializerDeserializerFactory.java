package clipboard.serialize;

import model.elements.GraphicShapeType;

/**
 * Factory for specific shape serializers and deserializers.
 * 
 * @author geomaster
 *
 */
public class GraphicShapeClipboardSerializerDeserializerFactory {
	/**
	 * Creates a serializer for a shape type.
	 * 
	 * @param type
	 *            Shape type to create a serializer for
	 * @return Created serializer, or null if the type is not supported
	 */
	public GraphicShapeClipboardSerializer createSerializer(GraphicShapeType type) {
		switch (type) {
		case RECTANGLE:
			return new GraphicRectangleShapeClipboardSerializer();
		case ELLIPSE:
			return new GraphicEllipseShapeClipboardSerializer();
		case TRIANGLE:
			return new GraphicTriangleShapeClipboardSerializer();
		default:
			return null;
		}
	}

	/**
	 * Creates a deserializer for a shape type.
	 * 
	 * @param type
	 *            Shape type to create a deserializer for
	 * @return Created deserializer, or null if the type is not supported
	 */
	public GraphicShapeClipboardDeserializer createDeserializer(GraphicShapeType type) {
		switch (type) {
		case RECTANGLE:
			return new GraphicRectangleShapeClipboardDeserializer();
		case ELLIPSE:
			return new GraphicEllipseShapeClipboardDeserializer();
		case TRIANGLE:
			return new GraphicTriangleShapeClipboardDeserializer();
		default:
			return null;
		}
	}
}
