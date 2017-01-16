package model.elements;

import java.io.Serializable;

/**
 * Rectangle shape component of a graphic element.
 * 
 * @author geomaster
 *
 */
public class GraphicRectangleShape extends GraphicShape implements Serializable {
	/**
	 * Version UID for serialization.
	 */
	final static long serialVersionUID = 1;
	
	/**
	 * Extent of the rectangle.
	 */
	Rectangle rectangle;

	/**
	 * Constructor.
	 * 
	 * @param rectangle
	 *            extent of the rectangle
	 */
	public GraphicRectangleShape(Rectangle rectangle) {
		super(GraphicShapeType.RECTANGLE);
		this.rectangle = rectangle;
	}

	/**
	 * Tests if a point is inside the rectangle.
	 * 
	 * @param p
	 *            Point to test
	 * @return true if the point is inside the rectangle, false if not
	 */
	@Override
	public boolean pointInside(Point p) {
		return rectangle.pointInside(p);
	}

	/**
	 * Gets the bounding rectangle of the rectangle. Same as getRectangle().
	 * 
	 * @return Bounding rectangle
	 */
	@Override
	public Rectangle getBoundingRectangle() {
		return rectangle;
	}

	/**
	 * Gets the extent of the rectangle.
	 * 
	 * @return rectangle
	 */
	public Rectangle getRectangle() {
		return rectangle;
	}

	/**
	 * Sets the new extent of the rectangle.
	 * 
	 * @param rectangle
	 *            New rectangle
	 */
	public void setRectangle(Rectangle rectangle) {
		this.rectangle = rectangle;
	}

	/**
	 * Translates the rectangle by a given amount.
	 * 
	 * @param x
	 *            Amount of pixels to translate the rectangle along the X axis
	 * @param y
	 *            Amount of pixels to translate the rectangle along the Y axis
	 * @return New position of the top-left corner of the rectangle's extent
	 */
	@Override
	public Point translate(int x, int y) {
		rectangle.translate(x, y);
		return rectangle.getOrigin();
	}

	/**
	 * Creates an identical copy of this rectangle.
	 * 
	 * @return Clone of the rectangle
	 */
	@Override
	public GraphicShape clone() {
		return new GraphicRectangleShape(rectangle.clone());
	}

	/**
	 * Sets the position of the top-left corner of the rectangle's extent.
	 * 
	 * @param newPoint
	 *            new position
	 */
	@Override
	public void setPosition(Point newPoint) {
		rectangle.setOrigin(newPoint.clone());
	}

	/**
	 * Gets the position of the top-left corner of the rectangle's extent. Same
	 * as getRectangle().getOrigin().
	 * 
	 * @return Position of the rectangle
	 */
	@Override
	public Point getPosition() {
		return rectangle.getOrigin();
	}

	/**
	 * Checks if this rectangle is the same as another shape.
	 * 
	 * @return true if the shapes are the same, false otherwise
	 */
	@Override
	public boolean equalShape(GraphicShape other) {
		return other instanceof GraphicRectangleShape
				&& rectangle.equalRectangle(((GraphicRectangleShape) other).getRectangle());
	}
}
