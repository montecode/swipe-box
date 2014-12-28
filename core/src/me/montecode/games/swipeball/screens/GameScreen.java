package me.montecode.games.swipeball.screens;

import static me.montecode.games.swipeball.utils.GameConstants.PPM;

import me.montecode.games.swipeball.SwipeBallGame;
import me.montecode.games.swipeball.gameobjects.Box;
import me.montecode.games.swipeball.gameworld.GameRenderer;
import me.montecode.games.swipeball.gameworld.GameWorld;
import me.montecode.games.swipeball.helpers.InputHandler;
import me.montecode.games.swipeball.utils.GameConstants;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class GameScreen implements Screen{

	World world;
	GameRenderer renderer;
	InputHandler inputHandler;
	GameWorld gameWorld;
	OrthographicCamera b2dcam;
	Viewport viewport;

	public GameScreen(SwipeBallGame game){
		world = new World(GameConstants.WORLD_GRAVITY, true);

		gameWorld = new GameWorld(world);
		inputHandler = new InputHandler(game);
		Gdx.input.setInputProcessor(inputHandler);
		b2dcam = new OrthographicCamera(GameConstants.GAME_WIDTH / PPM, GameConstants.GAME_HEIGHT / PPM);
        b2dcam.position.set(GameConstants.GAME_WIDTH / PPM / 2, GameConstants.GAME_HEIGHT / PPM / 2, 0);
        b2dcam.update();
        renderer = new GameRenderer(world, b2dcam);
        viewport = new ExtendViewport(800 / PPM, 460 / PPM, b2dcam);
	}
	
	
	
	@Override
	public void render(float delta) {
		gameWorld.update(delta, b2dcam);
		renderer.render();
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
		// TODO Auto-generated method stub
		
	}

}
