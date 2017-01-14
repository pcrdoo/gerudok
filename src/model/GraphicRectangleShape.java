package model;

import java.io.Serializable;

public class GraphicRectangleShape extends GraphicShape implements Serializable{
	Rectangle rectangle;
	
	public GraphicRectangleShape(Rectangle rectangle)
	{
		super(GraphicShapeType.RECTANGLE);
		this.rectangle = rectangle;
	}
	
	@Override
	public boolean pointInside(Point p)
	{
		return rectangle.pointInside(p);
	}
	
	@Override
	public Rectangle getBoundingRectangle()
	{
		return rectangle;
	}
	
	public Rectangle getRectangle()
	{
		return rectangle;
	}
	
	public void setRectangle(Rectangle rectangle) {
		this.rectangle = rectangle;
	}

	@Override
	public void translate(int x, int y) {
		rectangle.translate(x, y);
	}
	
	@Override
	public GraphicShape clone()
	{
		return new GraphicRectangleShape(rectangle.clone());
	}
}
