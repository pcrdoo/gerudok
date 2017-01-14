package model;

import java.io.Serializable;

public class Rectangle implements Serializable{
	private Point origin;
	private int width, height;

	public Rectangle(Point origin, int width, int height)
	{
		this.origin = origin;
		this.width = width;
		this.height = height;
	}
	
	public boolean pointInside(Point p)
	{
		Point o = origin;
		int x = p.getX();
		int y = p.getY();
		int w = width;
		int h = height;
		return x >= o.getX() &&
			   y >= o.getY() &&
			   x <= o.getX() + w &&
			   y <= o.getY() + h;
	}
	
	public Point getOrigin() {
		return origin;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public void setOrigin(Point origin) {
		this.origin = origin;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public void setHeight(int height) {
		this.height = height;
	}
	
	public Rectangle clone() {
		return new Rectangle(origin.clone(), width, height);
	}
	
	public void translate(int x, int y) {
		origin.setX(origin.getX() + x);
		origin.setY(origin.getY() + y);
	}
	
	public void normalize() {
		if (this.width < 0) {
			this.origin.setX(this.origin.getX() + this.width);
			this.width *= -1;
		}
		
		if (this.height < 0) {
			this.origin.setY(this.origin.getY() + this.height);
			this.height *= -1;
		}
	}
}
