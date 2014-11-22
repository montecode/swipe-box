package me.montecode.games.swipeball.screens;

import me.montecode.games.swipeball.gameworld.GameRenderer;
import me.montecode.games.swipeball.gameworld.GameWorld;

import com.badlogic.gdx.Screen;

public class GameScreen implements Screen{
	
	GameWorld world;
	GameRenderer renderer;
	private float runTime;
	
	public GameScreen(){
		world = new GameWorld();
		renderer = new GameRenderer();
		
	}
	
	
	@Override
	public void render(float delta) {
		
		
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
