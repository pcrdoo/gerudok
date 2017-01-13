package model;

public abstract class GraphicShape {
	private GraphicShapeType type;
	
	protected GraphicShape(GraphicShapeType type) {
		this.type = type;
	}
	
	public GraphicShapeType getType() {
		return type;
	}
	
	public abstract boolean pointInside(Point p);
	public abstract Rectangle getBoundingRectangle();
	public abstract GraphicShape clone();
	public abstract void translate(int x, int y);
}