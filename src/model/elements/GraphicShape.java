package model.elements;

import java.io.Serializable;

public abstract class GraphicShape implements Serializable{
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
	public abstract Point translate(int x, int y);
	public abstract void setPosition(Point newPosition);
	public abstract Point getPosition();
	
	public boolean equalShape(GraphicShape other)
	{
		return equals(other);
	}
}
