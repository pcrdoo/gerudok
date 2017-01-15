package model.elements;

import java.io.Serializable;

/**
 * Ellipse shape component of a graphic element.
 * 
 * @author geomaster
 *
 */
public class GraphicEllipseShape extends GraphicShape implements Serializable {
	/**
	 * Bounds (extent) of the ellipse.
	 */
	private Rectangle bounds;

	/**
	 * Constructor.
	 * 
	 * @param bounds
	 *            Bounds (extent) of the ellipse
	 */
	public GraphicEllipseShape(Rectangle bounds) {
		super(GraphicShapeType.ELLIPSE);
		this.bounds = bounds;
	}

	/**
	 * Tests if a point is inside the ellipse.
	 * 
	 * @param p
	 *            Point to test
	 * @return true if the point is inside the ellipse, false if not
	 */
	@Override
	public boolean pointInside(Point p) {
		Point o = bounds.getOrigin();
		int w2 = bounds.getWidth() / 2;
		int h2 = bounds.getHeight() / 2;
		int cx = o.getX() + w2;
		int cy = o.getY() + h2;
		int x = (p.getX() - cx);
		int y = (p.getY() - cy);

		return h2 * h2 * x * x + w2 * w2 * y * y <= w2 * w2 * h2 * h2;
	}

	/**
	 * Gets the bounding rectangle of the ellipse. Same as getBounds().
	 * 
	 * @return Bounding rectangle
	 */
	@Override
	public Rectangle getBoundingRectangle() {
		return bounds;
	}

	/**
	 * Gets the bounds (extent) of the ellipse.
	 * 
	 * @return Bounds
	 */
	public Rectangle getBounds() {
		return bounds;
	}

	/**
	 * Sets the new bounds (extent) of the ellipse.
	 * 
	 * @param bounds
	 *            New bounds
	 */
	public void setBounds(Rectangle bounds) {
		this.bounds = bounds;
	}

	/**
	 * Translates the ellipse by a given amount.
	 * 
	 * @param x
	 *            Amount of pixels to translate the ellipse along the X axis
	 * @param y
	 *            Amount of pixels to translate the ellipse along the Y axis
	 * @return New position of the top-left corner of the ellipse's extent
	 */
	@Override
	public Point translate(int x, int y) {
		bounds.translate(x, y);
		return bounds.getOrigin();
	}

	/**
	 * Creates an identical copy of this ellipse.
	 * 
	 * @return Clone of the ellipse
	 */
	@Override
	public GraphicShape clone() {
		return new GraphicEllipseShape(bounds.clone());
	}

	/**
	 * Sets the position of the top-left corner of the ellipse's extent.
	 * 
	 * @param newPoint
	 *            new position
	 */
	@Override
	public void setPosition(Point newPoint) {
		bounds.setOrigin(newPoint.clone());
	}

	/**
	 * Gets the position of the top-left corner of the ellipse's extent. Same as
	 * getBounds().getOrigin().
	 * 
	 * @return Position of the ellipse
	 */
	@Override
	public Point getPosition() {
		return bounds.getOrigin();
	}

	/**
	 * Checks if this ellipse is the same as another shape.
	 * 
	 * @return true if the shapes are the same, false otherwise
	 */
	@Override
	public boolean equalShape(GraphicShape other) {
		return other instanceof GraphicEllipseShape && bounds.equalRectangle(((GraphicEllipseShape) other).getBounds());
	}
}
