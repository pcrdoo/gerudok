package model.elements;

import java.io.Serializable;

/**
 * Point in a plane, defined by its Cartesian coordinates in pixels.
 * 
 * @author geomaster
 *
 */
public class Point implements Serializable {
	/**
	 * Version UID for serialization.
	 */
	final static long serialVersionUID = 1;
	
	/**
	 * X coordinate of the point, in pixels.
	 */
	private int x;

	/**
	 * Y coordinate of the point, in pixels.
	 */
	private int y;

	/**
	 * Constructor.
	 * 
	 * @param x
	 *            X coordinate of the point in pixels
	 * @param y
	 *            Y coordinate of the point in pixels
	 */
	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}

	/**
	 * Gets the X coordinate of the point.
	 * 
	 * @return X coordinate in pixels
	 */
	public int getX() {
		return x;
	}

	/**
	 * Gets the Y coordinate of the point.
	 * 
	 * @return Y coordinate in pixels
	 */
	public int getY() {
		return y;
	}

	/**
	 * Sets the X coordinate of the point.
	 * 
	 * @param x
	 *            New X coordinate in pixels
	 */
	public void setX(int x) {
		this.x = x;
	}

	/**
	 * Sets the Y coordinate of the point.
	 * 
	 * @param y
	 *            New Y coordinate in pixels.
	 */
	public void setY(int y) {
		this.y = y;
	}

	/**
	 * Creates an exact copy of this point.
	 * 
	 * @return Clone of the point
	 */
	@Override
	public Point clone() {
		return new Point(x, y);
	}

	/**
	 * Checks if this point is equal to another point.
	 * 
	 * @param other
	 *            Point to check equality with
	 * @return true if the points are the same, false otherwise
	 */
	public boolean equalPoint(Point other) {
		return x == other.x && y == other.y;
	}
}
