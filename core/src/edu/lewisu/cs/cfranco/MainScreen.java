package edu.lewisu.cs.cfranco;

import java.util.ArrayList;
import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;

public class MainScreen implements Screen {
	
	Texture img;
	float imgX;
	float imgY;
	public static final float MIN_SPAWN_TIME = 0.3f;
	public static final float MAX_SPAWN_TIME = 0.6f;
	float astTimer;

	Random random;
	
	VgFinalCure game;
	
	ArrayList<Lasers> lasers;
	ArrayList<Asteroid> asteroids;
	
	// Font
	BitmapFont font;
	
	int score;
	
	public MainScreen(VgFinalCure game) {
		this.game = game;
		lasers = new ArrayList<Lasers>();
		asteroids = new ArrayList<Asteroid>();
		font = new BitmapFont(Gdx.files.internal("fonts/score.fnt"));
		
		score = 0;

		random = new Random();
		astTimer = random.nextFloat() * (MAX_SPAWN_TIME - MIN_SPAWN_TIME) + MIN_SPAWN_TIME;
	}
	
	// Speed of lasers
	public static final float SPEED = 275;
	
	// Time Delay for how many lasers you can shoot so you don't spam space bar
	public static final float SHOOT_WAIT_TIME = 0.3f;
	
	float laserTimer;

	@Override
	public void show() {
		img = new Texture("superman1.png");
	}

	// Controls for Superman
	public void handleInput() {
		if (Gdx.input.isKeyPressed(Keys.UP)) {
			imgY = imgY + SPEED * Gdx.graphics.getDeltaTime();
		}
		if (Gdx.input.isKeyPressed(Keys.DOWN)) {
			imgY = imgY - SPEED * Gdx.graphics.getDeltaTime();
		}
		if (Gdx.input.isKeyPressed(Keys.LEFT)) {
			imgX = imgX - SPEED * Gdx.graphics.getDeltaTime();
			if (imgX < 0) {
				imgX = -21;
			}
		}
		if (Gdx.input.isKeyPressed(Keys.RIGHT)) {
			imgX = imgX + SPEED * Gdx.graphics.getDeltaTime();
			if (imgX + 51 > Gdx.graphics.getWidth()) {
				imgX = Gdx.graphics.getWidth() - 71;
			}
		}
		if (Gdx.input.isKeyPressed(Keys.ESCAPE)) {
			Gdx.app.exit();
			}
		}
	
	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		// Spawn for Asteroids
		astTimer -= delta;
		if(astTimer <= 0) {
			astTimer = random.nextFloat() * (MAX_SPAWN_TIME - MIN_SPAWN_TIME) + MIN_SPAWN_TIME;
			asteroids.add(new Asteroid(random.nextInt(Gdx.graphics.getWidth() - Asteroid.WIDTH)));
		}
		
		// Update asteroids
		ArrayList<Asteroid> astRemove = new ArrayList<Asteroid>();
		for (Asteroid asteroid : asteroids) {
			asteroid.update(delta);
			if (asteroid.remove) {
				astRemove.add(asteroid);
			}
		}
		
		// Update lasers
		ArrayList<Lasers> lasersRemove = new ArrayList<Lasers>();
		for (Lasers lasers : lasers) {
			lasers.update(delta);
			if (lasers.remove) {
				lasersRemove.add(lasers);
			}
		}
		
		// Control for shooting lasers
		laserTimer += delta;
		if (Gdx.input.isKeyPressed(Keys.SPACE) && laserTimer >= SHOOT_WAIT_TIME) {
			laserTimer = 0;
			lasers.add(new Lasers(imgX + 25));
		}
		
		// Collisions
		for (Lasers laser : lasers) {
			for (Asteroid asteroid : asteroids) {
				if (laser.getCollision().objectCollision(asteroid.getCollision())) {
					lasersRemove.add(laser);
					astRemove.add(asteroid);
					score += 100;
				}
			}
		}
		asteroids.removeAll(astRemove);
		lasers.removeAll(lasersRemove);
		
		// Call Controls Function
		handleInput();
		
		game.batch.begin();
		
		GlyphLayout layout = new GlyphLayout(font, "" + score);
		font.draw(game.batch, layout, Gdx.graphics.getWidth() / 2 - layout.width / 2, Gdx.graphics.getHeight() - layout.height - 10);
		
		for (Asteroid asteroid : asteroids) {
			asteroid.render(game.batch);
		}
		
		for (Lasers lasers : lasers) {
			lasers.render(game.batch);
		}
		
		game.batch.draw(img, imgX, imgY);
		
		game.batch.end();
	}

	@Override
	public void resize(int width, int height) {
		
	}

	@Override
	public void pause() {
		
	}

	@Override
	public void resume() {
		
	}

	@Override
	public void hide() {
		
	}

	@Override
	public void dispose() {
		
	}
	
}
