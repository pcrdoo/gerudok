package clipboard.serialize;

import model.elements.GraphicShape;
import model.elements.GraphicTriangleShape;

/**
 * Serializer for triangle shapes.
 * 
 * @author geomaster
 *
 */
public class GraphicTriangleShapeClipboardSerializer implements GraphicShapeClipboardSerializer {
	/**
	 * Serializes a triangle to a string.
	 * 
	 * @param shape
	 *            Triangle to serialize
	 * @param sb
	 *            StringBuilder to which to append the result.
	 */
	@Override
	public void serialize(GraphicShape shape, StringBuilder sb) {
		GraphicTriangleShape s = (GraphicTriangleShape) shape;
		new RectangleSerializer().serialize(s.getBounds(), sb);
	}
}
