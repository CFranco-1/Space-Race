package edu.lewisu.cs.cfranco;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Asteroid {

	public static final int SPEED = 250;
	public static final int WIDTH = 16;
	public static final int HEIGHT = 16;
	private static Texture texture;
	
	float imgX, imgY;
	Collision col;
	public boolean remove = false;
	
	public Asteroid (float imgX) {
		this.imgX = imgX;
		this.imgY = Gdx.graphics.getHeight();
		this.col = new Collision(imgX, imgY, WIDTH, HEIGHT);


		if (texture == null) {
			texture = new Texture("asteroid.png");
		}
	}
	public void update (float deltaTime) {
		imgY -= SPEED * deltaTime;
		if (imgY < -HEIGHT) {
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
