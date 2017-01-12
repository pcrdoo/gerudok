package model;

public class GraphicTriangleShape extends GraphicShape {
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
	public void translate(int x, int y) {
		bounds.translate(x, y);
	}
	
	@Override
	public GraphicShape clone()
	{
		return new GraphicTriangleShape(bounds.clone());
	}
}
