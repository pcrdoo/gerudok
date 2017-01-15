package model.elements;

import java.io.Serializable;

public class GraphicTriangleShape extends GraphicShape implements Serializable{
	Rectangle bounds;
	
	public GraphicTriangleShape(Rectangle bounds)
	{
		super(GraphicShapeType.TRIANGLE);
		this.bounds = bounds;
	}
	
	@Override
	public boolean pointInside(Point p)
	{
		// TODO: Write a legitimate algorithm for this
		return bounds.pointInside(p);
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
	public Point translate(int x, int y) {
		bounds.translate(x, y);
		return bounds.getOrigin();
	}
	
	@Override
	public void setPosition(Point newPoint) {
		bounds.setOrigin(newPoint.clone());
	}
	
	@Override
	public GraphicShape clone()
	{
		return new GraphicTriangleShape(bounds.clone());
	}
	
	@Override
	public Point getPosition()
	{
		return bounds.getOrigin();
	}

	@Override
	public boolean equalShape(GraphicShape other)
	{
		return other instanceof GraphicTriangleShape && 
			   bounds.equalRectangle(((GraphicTriangleShape) other).getBounds());
	}
}
