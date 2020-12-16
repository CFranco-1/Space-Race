package edu.lewisu.cs.cfranco;

public class Collision {
	float x, y;
	int width, height;
	
	public Collision (float x, float y, int width, int height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}
	
	public void move (float x, float y) {
		this.x = x;
		this.y = y;
	}
	
	public boolean objectCollision (Collision col) {
		return x < col.x + col.width && y < col.y + col.height && x + width > col.x && y + height > col.y;
	}
}
