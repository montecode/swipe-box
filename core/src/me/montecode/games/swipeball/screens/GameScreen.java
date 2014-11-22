package me.montecode.games.swipeball.screens;

import me.montecode.games.swipeball.gameobjects.Ball;
import me.montecode.games.swipeball.gameworld.GameRenderer;
import me.montecode.games.swipeball.gameworld.GameWorld;
import me.montecode.games.swipeball.helpers.InputHandler;
import me.montecode.games.swipeball.utils.GameConstants;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;

public class GameScreen implements Screen{
	
	//GameWorld world;
	World world;
	GameRenderer renderer;
	Ball ball;
	private float runTime;
	InputHandler inputHandler;
	
	public GameScreen(){
		//world = new GameWorld();
		world = new World(GameConstants.WORLD_GRAVITY, true);
		inputHandler = new InputHandler();
		Gdx.input.setInputProcessor(inputHandler);
		renderer = new GameRenderer(world);
		
	}
	
	
	@Override
	public void render(float delta) {
		world.step(delta, 6, 2);
		renderer.render();
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
