package me.montecode.games.swipeball.screens;

import me.montecode.games.swipeball.SwipeBallGame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class LoadScreen implements Screen{
	
	ShapeRenderer shapeRenderer;
	SwipeBallGame game;
	float time = 0;
	
	public LoadScreen(SwipeBallGame game){
		this.game = game;
		shapeRenderer = new ShapeRenderer();
	}
	
	public void draw(){
		Gdx.gl.glClearColor(0, 0, 0, 0);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
	}
	public void update(){
		if(time > 1){
			game.setScreen(new GameScreen(game));
		}
	}
	
	public void render(float delta) {
		time += delta;
		Gdx.app.log("time", time + "");
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
		// TODO Auto-generated method stub
		
	}

	
	
}
