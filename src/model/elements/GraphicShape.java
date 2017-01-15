package model.elements;

import java.io.Serializable;

/**
 * Generic shape component of a graphic element.
 * 
 * @author geomaster
 *
 */
public abstract class GraphicShape implements Serializable {
	/**
	 * Shape type.
	 */
	private GraphicShapeType type;

	/**
	 * Constructor.
	 * 
	 * @param type
	 *            Shape type
	 */
	protected GraphicShape(GraphicShapeType type) {
		this.type = type;
	}

	/**
	 * Gets the shape type.
	 * 
	 * @return Shape type
	 */
	public GraphicShapeType getType() {
		return type;
	}

	/**
	 * Tests if a point is inside this shape.
	 * 
	 * @param p
	 *            Point to test
	 * @return true if the shape geometry contains the point, false if not
	 */
	public abstract boolean pointInside(Point p);

	/**
	 * Gets the minimal area rectangle that encompasses the shape.
	 * 
	 * @return Rectangle
	 */
	public abstract Rectangle getBoundingRectangle();

	/**
	 * Gets an identical copy of this shape.
	 * 
	 * @return Clone of this shape
	 */
	public abstract GraphicShape clone();

	/**
	 * Translates this shape by a given amount of pixels in X and Y directions.
	 * 
	 * @param x
	 *            Translation amount in pixels for the X axis
	 * @param y
	 *            Translation amount in pixels for the Y axis
	 * @return New position of the top-left corner of the shape.
	 */
	public abstract Point translate(int x, int y);

	/**
	 * Sets the new position of the shape.
	 * 
	 * @param newPosition
	 *            New coordinates of the top-left corner of the shape.
	 */
	public abstract void setPosition(Point newPosition);

	/**
	 * Gets the positiion of the shape.
	 * 
	 * @return Coordinates of the top-left corner of the shape.
	 */
	public abstract Point getPosition();

	/**
	 * Checks if this shape is equal to another shape.
	 * 
	 * @param other
	 *            Another shape to test
	 * @return true if this shape is the same as the other shape, false if not
	 */
	public boolean equalShape(GraphicShape other) {
		return equals(other);
	}
}
