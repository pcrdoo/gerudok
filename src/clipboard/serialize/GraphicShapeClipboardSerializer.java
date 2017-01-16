package clipboard.serialize;

import model.elements.GraphicShape;

/**
 * Serializer for a generic shape.
 * 
 * @author geomaster
 *
 */
public interface GraphicShapeClipboardSerializer {
	/**
	 * Serializes a shape to a string.
	 * 
	 * @param shape
	 *            Shape to serialize
	 * @param destination
	 *            StringBuilder to which to append the result.
	 */
	public void serialize(GraphicShape shape, StringBuilder destination);
}
