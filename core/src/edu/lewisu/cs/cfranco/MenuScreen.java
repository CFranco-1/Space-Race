package edu.lewisu.cs.cfranco;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;

public class MenuScreen implements Screen{
	
	private static final int EXIT_BUTTON_WIDTH = 300;
	private static final int EXIT_BUTTON_HEIGHT = 150;
	private static final int PLAY_BUTTON_WIDTH = 300;
	private static final int PLAY_BUTTON_HEIGHT = 150;
	private static final int EXIT_BUTTON_Y = 100;
	private static final int PLAY_BUTTON_Y = 250;
	
	VgFinalCure game;
	
	Texture playButton;
	Texture exitButton;
	
	BitmapFont font;
	
	public MenuScreen (VgFinalCure game) {
		this.game = game;
		playButton = new Texture("play_button.png");
		exitButton = new Texture("exit_button1.png");
		font = new BitmapFont(Gdx.files.internal("fonts/score.fnt"));
	}

	@Override
	public void show() {
		
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
				
		game.batch.begin();
		
		GlyphLayout layout = new GlyphLayout(font, "SPACE RACE");
		font.draw(game.batch, layout, Gdx.graphics.getWidth() / 2 - layout.width / 2, Gdx.graphics.getHeight() - layout.height - 10);
		
		int x = 480 / 2 - EXIT_BUTTON_WIDTH / 2;
		
		// Checks if Play Button is Clicked
		if (Gdx.input.getX() < x + PLAY_BUTTON_WIDTH && Gdx.input.getX() > x && 720 - Gdx.input.getY() < PLAY_BUTTON_Y + PLAY_BUTTON_HEIGHT && 720 - Gdx.input.getY() > PLAY_BUTTON_Y) {
			game.batch.draw(playButton, x, PLAY_BUTTON_Y, PLAY_BUTTON_WIDTH, PLAY_BUTTON_HEIGHT);
			if(Gdx.input.isTouched()) {
				this.dispose();
				// Move to Main Game Screen
				game.setScreen(new MainScreen(game));
			} else {
				game.batch.draw(playButton, x, PLAY_BUTTON_Y, PLAY_BUTTON_WIDTH, PLAY_BUTTON_HEIGHT);
			}
		} else {
			game.batch.draw(playButton, x, PLAY_BUTTON_Y, PLAY_BUTTON_WIDTH, PLAY_BUTTON_HEIGHT);
		}
		
		
		// Checks if Exit Button is clicked
		if (Gdx.input.getX() < x + EXIT_BUTTON_WIDTH && Gdx.input.getX() > x && 720 - Gdx.input.getY() < EXIT_BUTTON_Y + EXIT_BUTTON_HEIGHT && 720 - Gdx.input.getY() > EXIT_BUTTON_Y) {
			game.batch.draw(exitButton, x, EXIT_BUTTON_Y, EXIT_BUTTON_WIDTH, EXIT_BUTTON_HEIGHT);
			if(Gdx.input.isTouched()) {
				// Exits Application
				Gdx.app.exit();
			}
		} else {
			game.batch.draw(exitButton, x, EXIT_BUTTON_Y, EXIT_BUTTON_WIDTH, EXIT_BUTTON_HEIGHT);
		}
		
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
