package clipboard.serialize;

import model.elements.Rectangle;

/**
 * Serializer for simple rectangles.
 * 
 * @author geomaster
 *
 */
public class RectangleSerializer {
	/**
	 * Serializes a rectangle.
	 * 
	 * @param r
	 *            Rectangle to serialize
	 * @param sb
	 *            StringBuilder to which to append the result
	 */
	void serialize(Rectangle r, StringBuilder sb) {
		sb.append("rect[x=");
		sb.append(r.getOrigin().getX());
		sb.append(",y=");
		sb.append(r.getOrigin().getY());
		sb.append(",w=");
		sb.append(r.getWidth());
		sb.append(",h=");
		sb.append(r.getHeight());
		sb.append("]");
	}
}
