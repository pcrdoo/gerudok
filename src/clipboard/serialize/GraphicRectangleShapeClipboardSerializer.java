package clipboard.serialize;

import model.elements.GraphicShape;
import model.elements.GraphicRectangleShape;

/**
 * Serializer for rectangle shapes.
 * 
 * @author geomaster
 *
 */
public class GraphicRectangleShapeClipboardSerializer implements GraphicShapeClipboardSerializer {
	/**
	 * Serializes a rectangle to a string.
	 * 
	 * @param shape
	 *            Rectangle to serialize
	 * @param sb
	 *            StringBuilder to which to append the result.
	 */
	public void serialize(GraphicShape shape, StringBuilder sb) {
		GraphicRectangleShape s = (GraphicRectangleShape) shape;
		new RectangleSerializer().serialize(s.getRectangle(), sb);
	}
}
