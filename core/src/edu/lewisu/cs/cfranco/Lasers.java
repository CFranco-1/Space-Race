package edu.lewisu.cs.cfranco;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Lasers {

	public static final int SPEED = 500;
	public static final int DEFAULT_Y = 100;
	public static final int WIDTH = 40;
	public static final int HEIGHT = 60;
	private static Texture texture;
	
	float imgX, imgY;
	Collision col;
	public boolean remove = false;
	
	public Lasers (float imgX) {
		this.imgX = imgX;
		this.imgY = DEFAULT_Y;
		this.col = new Collision(imgX, imgY, WIDTH, HEIGHT);

		if (texture == null) {
			texture = new Texture("laser1.png");
		}
	}
	public void update (float deltaTime) {
		imgY += SPEED * deltaTime;
		if (imgY > Gdx.graphics.getHeight()) {
			remove = true;
			
		col.move(imgX, imgY);
		}
	}
	public void render (SpriteBatch batch) {
		batch.draw(texture, imgX, imgY);
	}
	
	public Collision getCollision() {
		return col;
	}
}
