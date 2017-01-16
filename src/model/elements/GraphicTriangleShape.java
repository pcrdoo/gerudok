package model.elements;

import java.io.Serializable;

/**
 * Triangle shape component of a graphic element. The triangle is isosceles,
 * with one (bottom) side parallel to the X-axis, and is defined by the
 * minimal-area axis-aligned rectangle which contains it.
 *
 * @author geomaster
 *
 */
public class GraphicTriangleShape extends GraphicShape implements Serializable {
	/**
	 * Version UID for serialization.
	 */
	final static long serialVersionUID = 1;
	
	/**
	 * Bounds (extent) of the triangle.
	 */
	Rectangle bounds;

	/**
	 * Constructor.
	 * 
	 * @param bounds
	 *            Bounds (extent) of the triangle
	 */
	public GraphicTriangleShape(Rectangle bounds) {
		super(GraphicShapeType.TRIANGLE);
		this.bounds = bounds;
	}

	/**
	 * Tests if a point is inside the triangle.
	 * 
	 * @param p
	 *            Point to test
	 * @return true if the point is inside the triangle, false if not
	 */
	@Override
	public boolean pointInside(Point p) {
		// TODO: Write a legitimate algorithm for this
		return bounds.pointInside(p);
	}

	/**
	 * Gets the bounding rectangle of the triangle. Same as getBounds().
	 * 
	 * @return Bounding rectangle
	 */
	@Override
	public Rectangle getBoundingRectangle() {
		return bounds;
	}

	/**
	 * Gets the bounds (extent) of the triangle.
	 * 
	 * @return Bounds
	 */
	public Rectangle getBounds() {
		return bounds;
	}

	/**
	 * Sets the new bounds (extent) of the triangle.
	 * 
	 * @param bounds
	 *            New bounds
	 */
	public void setBounds(Rectangle bounds) {
		this.bounds = bounds;
	}

	/**
	 * Translates the triangle by a given amount.
	 * 
	 * @param x
	 *            Amount of pixels to translate the triangle along the X axis
	 * @param y
	 *            Amount of pixels to translate the triangle along the Y axis
	 * @return New position of the top-left corner of the triangle's extent
	 */
	@Override
	public Point translate(int x, int y) {
		bounds.translate(x, y);
		return bounds.getOrigin();
	}

	/**
	 * Sets the position of the top-left corner of the triangle's extent.
	 * 
	 * @param newPoint
	 *            new position
	 */
	@Override
	public void setPosition(Point newPoint) {
		bounds.setOrigin(newPoint.clone());
	}

	/**
	 * Creates an identical copy of this triangle.
	 * 
	 * @return Clone of the triangle
	 */
	@Override
	public GraphicShape clone() {
		return new GraphicTriangleShape(bounds.clone());
	}

	/**
	 * Gets the position of the top-left corner of the triangle's extent. Same
	 * as getBounds().getOrigin().
	 * 
	 * @return Position of the triangle
	 */
	@Override
	public Point getPosition() {
		return bounds.getOrigin();
	}

	/**
	 * Checks if this triangle is the same as another shape.
	 * 
	 * @return true if the shapes are the same, false otherwise
	 */
	@Override
	public boolean equalShape(GraphicShape other) {
		return other instanceof GraphicTriangleShape
				&& bounds.equalRectangle(((GraphicTriangleShape) other).getBounds());
	}
}
