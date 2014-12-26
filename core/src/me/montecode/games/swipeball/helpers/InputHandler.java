package me.montecode.games.swipeball.helpers;


import me.montecode.games.swipeball.SwipeBallGame;
import me.montecode.games.swipeball.gameobjects.Box;
import me.montecode.games.swipeball.gameworld.GameRenderer;
import me.montecode.games.swipeball.gameworld.GameWorld;
import me.montecode.games.swipeball.levels.GenerateLevel;
import me.montecode.games.swipeball.levels.LevelReader;
import me.montecode.games.swipeball.screens.MenuScreen;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.math.Vector2;

public class InputHandler implements InputProcessor{
	
	private boolean fromMenu = true;
	private Vector2 lastTouch = new Vector2();
	private Vector2 delta, newTouch, firstTouch;
	private float scale = 5;
    private SwipeBallGame game;

    public InputHandler(SwipeBallGame game){
        this.game = game;
    }

	@Override
	public boolean keyDown(int keycode) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
        if (keycode == Input.Keys.BACK) {
            game.setScreen(new MenuScreen(game));
        }

        return true;
	}

	@Override
	public boolean keyTyped(char character) {
		
		
		
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		firstTouch = new Vector2(screenX, screenY);
		
		return true;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        if(!fromMenu) {
            firstTouch.set(firstTouch.x / scale, firstTouch.y / scale);
            delta = new Vector2(lastTouch.x / scale, lastTouch.y / scale).cpy().sub(firstTouch);

            delta.limit(15);

            if (delta.angle() < 270) {
                delta.y = 0;
                delta.x = 0;
            }
            if (!Box.isFlying()) {
                Box.setVelocity(delta);
            }
            if (Box.getYPosition() < 0) {
                LevelReader.clearLevel();
                GenerateLevel.reset();
                GameWorld.reset();
                GenerateLevel.setUp();
                GenerateLevel.generate();
                GameRenderer.resetCameraPosition();
                Box.checkHighScore();
                Box.resetScore();

            }
        }
        fromMenu = false;
		return true;

	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		newTouch = new Vector2(screenX, screenY);		
		lastTouch = newTouch;
		return true;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		return false;
	}

	

}
