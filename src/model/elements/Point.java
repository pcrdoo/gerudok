package model.elements;

import java.io.Serializable;

public class Point implements Serializable{
	private int x, y;
	
	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}
	
	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}

	public Point clone() {
		return new Point(x, y);
	}
	
	public boolean equalPoint(Point other) {
		return x == other.x &&
			   y == other.y;
	}
}
