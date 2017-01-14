package model;

import java.io.Serializable;

public class GraphicEllipseShape extends GraphicShape implements Serializable{
	Rectangle bounds;
	
	public GraphicEllipseShape(Rectangle bounds)
	{
		super(GraphicShapeType.ELLIPSE);
		this.bounds = bounds;
	}
	
	@Override
	public boolean pointInside(Point p)
	{
		Point o = bounds.getOrigin();
		int w2 = bounds.getWidth() / 2;
		int h2 = bounds.getHeight() / 2;
		int cx = o.getX() + w2;
		int cy = o.getY() + h2;
		int x = (p.getX() - cx);
		int y = (p.getY() - cy);
	
		return h2 * h2 * x * x + w2 * w2 * y * y <= w2 * w2 * h2 * h2;
	}
	
	@Override
	public Rectangle getBoundingRectangle()
	{
		return bounds;
	}
	
	public Rectangle getBounds()
	{
		return bounds;
	}

	public void setBounds(Rectangle bounds) {
		this.bounds = bounds;
	}
	
	@Override
	public void translate(int x, int y) {
		bounds.translate(x, y);
	}
	
	@Override
	public GraphicShape clone()
	{
		return new GraphicEllipseShape(bounds.clone());
	}
}
