package me.montecode.games.swipeball.screens;

import me.montecode.games.swipeball.SwipeBallGame;
import me.montecode.games.swipeball.helpers.AssetLoader;
import me.montecode.games.swipeball.utils.GameConstants;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class LoadScreen implements Screen{
	
	ShapeRenderer shapeRenderer;
	SwipeBallGame game;
	float time = 0;
    SpriteBatch batch;
    OrthographicCamera cam;
    Viewport viewport;

	public LoadScreen(SwipeBallGame game){
		this.game = game;
        batch = new SpriteBatch();
		shapeRenderer = new ShapeRenderer();
        cam = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        cam.setToOrtho(false, cam.viewportWidth, cam.viewportHeight);
        viewport = new ExtendViewport(800, 460);
	}
	
	public void draw(){
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.setProjectionMatrix(cam.combined);
        batch.begin();
        batch.draw(AssetLoader.splashScreen, Gdx.graphics.getWidth() / 2 - AssetLoader.splashScreen.getWidth() / 2,
                Gdx.graphics.getHeight() / 2 - AssetLoader.splashScreen.getHeight() / 2);
        batch.end();

	}
	public void update(){
		if(time > 5){
			game.setScreen(new MenuScreen(game));
		}
	}
	
	public void render(float delta) {
		time += delta;
		update();
		draw();
		
	}

	@Override
	public void resize(int width, int height) {
		viewport.update(width, height);
		
	}

	@Override
	public void show() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		AssetLoader.dispose();
		
	}

	
	
}
