package clipboard.serialize;

import java.util.regex.Pattern;

import clipboard.InvalidDataFormatException;

import java.util.regex.Matcher;

import model.elements.Rectangle;
import model.elements.Point;

/**
 * Deserializer for simple rectangles.
 * 
 * @author geomaster
 *
 */
public class RectangleDeserializer {
	/**
	 * Deserializes a rectangle from a string
	 * 
	 * @param serializedString
	 *            Serialized string created using RectangleSerializer
	 * @return Rectangle represented by the string
	 * @throws InvalidDataFormatException
	 *             If the format of the string is non-conforming
	 */
	public Rectangle deserialize(String serializedString) throws InvalidDataFormatException {
		Pattern p = Pattern.compile("^rect\\[x=([-0-9]+),y=([-0-9]+),w=([-0-9]+),h=([-0-9]+)\\]$");
		Matcher m = p.matcher(serializedString);
		if (m.find()) {
			try {
				int x = Integer.parseInt(m.group(1));
				int y = Integer.parseInt(m.group(2));
				int w = Integer.parseInt(m.group(3));
				int h = Integer.parseInt(m.group(4));

				return new Rectangle(new Point(x, y), w, h);
			} catch (NumberFormatException e) {
				throw new InvalidDataFormatException("Invalid number in the Rectangle specification");
			}
		} else {
			throw new InvalidDataFormatException("Invalid format for Rectangle");
		}
	}
}
