package model.elements;

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
	public Point translate(int x, int y) {
		rectangle.translate(x, y);
		return rectangle.getOrigin();
	}
	
	@Override
	public GraphicShape clone()
	{
		return new GraphicRectangleShape(rectangle.clone());
	}

	@Override
	public void setPosition(Point newPoint) {
		rectangle.setOrigin(newPoint.clone());
	}
	
	@Override
	public Point getPosition()
	{
		return rectangle.getOrigin();
	}
	
	@Override
	public boolean equalShape(GraphicShape other)
	{
		return other instanceof GraphicRectangleShape && 
			   rectangle.equalRectangle(((GraphicRectangleShape) other).getRectangle());
	}
}
