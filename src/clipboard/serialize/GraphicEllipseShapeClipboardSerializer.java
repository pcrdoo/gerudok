package clipboard.serialize;

import model.elements.GraphicShape;
import model.elements.GraphicEllipseShape;

/**
 * Serializer for ellipse shapes.
 * 
 * @author geomaster
 *
 */
public class GraphicEllipseShapeClipboardSerializer implements GraphicShapeClipboardSerializer {
	/**
	 * Serializes an ellipse to a string.
	 * 
	 * @param shape
	 *            Ellipse to serialize
	 * @param sb
	 *            StringBuilder to which to append the result.
	 */
	@Override
	public void serialize(GraphicShape shape, StringBuilder sb) {
		GraphicEllipseShape s = (GraphicEllipseShape) shape;
		new RectangleSerializer().serialize(s.getBounds(), sb);
	}
}
