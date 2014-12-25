package me.montecode.games.swipeball.screens;

import me.montecode.games.swipeball.SwipeBallGame;
import me.montecode.games.swipeball.helpers.AssetLoader;
import me.montecode.games.swipeball.utils.GameConstants;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class LoadScreen implements Screen{
	
	ShapeRenderer shapeRenderer;
	SwipeBallGame game;
	float time = 0;
    SpriteBatch batch;

	public LoadScreen(SwipeBallGame game){
		this.game = game;
        batch = new SpriteBatch();
		shapeRenderer = new ShapeRenderer();
	}
	
	public void draw(){
		Gdx.gl.glClearColor(0, 0, 0, 0);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        batch.draw(AssetLoader.splashScreen, 0, 0);
        batch.end();

	}
	public void update(){
		if(time > 1){
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
		// TODO Auto-generated method stub
		
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
