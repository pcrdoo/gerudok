package model.elements;

import java.io.Serializable;

/**
 * Axis-aligned rectangle in a plane, defined by its top-left point and two
 * dimensions (width and height) in pixels.
 * 
 * @author geomaster
 *
 */
public class Rectangle implements Serializable {
	/**
	 * Origin (top-left) point of the rectangle.
	 */
	private Point origin;

	/**
	 * Width of the rectangle, in pixels.
	 */
	private int width;

	/**
	 * Height of the rectangle, in pixels.
	 */
	private int height;

	/**
	 * Constructor.
	 * 
	 * @param origin
	 *            Origin (top-left) point of the rectangle
	 * @param width
	 *            Width in pixels
	 * @param height
	 *            Height in pixels
	 */
	public Rectangle(Point origin, int width, int height) {
		this.origin = origin;
		this.width = width;
		this.height = height;
	}

	/**
	 * Tests if a point is inside the rectangle.
	 * 
	 * @param p
	 *            Point to test
	 * @return true if the point is inside the rectangle, false otherwise
	 */
	public boolean pointInside(Point p) {
		Point o = origin;
		int x = p.getX();
		int y = p.getY();
		int w = width;
		int h = height;
		return x >= o.getX() && y >= o.getY() && x <= o.getX() + w && y <= o.getY() + h;
	}

	/**
	 * Gets the origin (top-left) point of the rectangle.
	 * 
	 * @return Origin point
	 */
	public Point getOrigin() {
		return origin;
	}

	/**
	 * Gets the width of the rectangle.
	 * 
	 * @return Width in pixels
	 */
	public int getWidth() {
		return width;
	}

	/**
	 * Gets the height of the rectangle.
	 * 
	 * @return Height in pixels.
	 */
	public int getHeight() {
		return height;
	}

	/**
	 * Sets the origin (top-left) point of the rectangle.
	 * 
	 * @param origin
	 *            New origin point
	 */
	public void setOrigin(Point origin) {
		this.origin = origin;
	}

	/**
	 * Sets the width of the rectangle.
	 * 
	 * @param width
	 *            New width in pixels
	 */
	public void setWidth(int width) {
		this.width = width;
	}

	/**
	 * Sets the height of the rectangle.
	 * 
	 * @param height
	 *            New height in pixels
	 */
	public void setHeight(int height) {
		this.height = height;
	}

	/**
	 * Creates an exact copy of this rectangle.
	 * 
	 * @return Clone of the rectangle
	 */
	public Rectangle clone() {
		return new Rectangle(origin.clone(), width, height);
	}

	/**
	 * Translates the rectangle for the specified amount along the X and Y axes.
	 * 
	 * @param x
	 *            Amount of pixels to translate along the X axis
	 * @param y
	 *            Amount of pixels to translate along the Y axis
	 */
	public void translate(int x, int y) {
		origin.setX(origin.getX() + x);
		origin.setY(origin.getY() + y);
	}

	/**
	 * Tests if the rectangle is the same as another rectangle.
	 * 
	 * @param other
	 *            Rectangle to test
	 * @return true if the rectangles are the same, false if not
	 */
	public boolean equalRectangle(Rectangle other) {
		Rectangle a = getNormalized();
		Rectangle b = other.getNormalized();
		return a.getOrigin().equalPoint(b.getOrigin()) && a.width == b.width && a.height == b.height;
	}

	/**
	 * Normalizes the rectangle, i.e. transforms it so that it geometrically
	 * describes the same rectangle while ensuring that the width and height are
	 * positive.
	 */
	public void normalize() {
		if (this.width < 0) {
			this.origin.setX(this.origin.getX() + this.width);
			this.width *= -1;
		}

		if (this.height < 0) {
			this.origin.setY(this.origin.getY() + this.height);
			this.height *= -1;
		}
	}

	/**
	 * Tests if a point is inside the rectangle.
	 * 
	 * @param other
	 *            Point to test
	 * @return true if the point is inside, false if not
	 */
	public boolean isInside(Rectangle other) {
		return origin.getX() >= other.origin.getX() && origin.getY() >= other.origin.getY()
				&& origin.getX() + width <= other.origin.getX() + other.width
				&& origin.getY() + height <= other.origin.getY() + other.height;
	}

	/**
	 * Returns the normalized version of the rectangle, without mutating this
	 * instance.
	 * 
	 * @see model.elements.Rectangle#normalize
	 * @return Normalized version of the rectangle
	 */
	public Rectangle getNormalized() {
		Rectangle normalized = clone();
		normalized.normalize();
		return normalized;
	}
}
