package me.montecode.games.swipeball.helpers;


import me.montecode.games.swipeball.SwipeBallGame;
import me.montecode.games.swipeball.gameobjects.Box;
import me.montecode.games.swipeball.gameworld.GameRenderer;
import me.montecode.games.swipeball.gameworld.GameWorld;
import me.montecode.games.swipeball.levels.GenerateLevel;
import me.montecode.games.swipeball.levels.LevelReader;
import me.montecode.games.swipeball.screens.MenuScreen;
import me.montecode.games.swipeball.utils.GameVars;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class InputHandler implements InputProcessor{
	
	private boolean fromMenu = true;
	private Vector2 lastTouch = new Vector2();
	private Vector2 delta, newTouch, firstTouch;
    private SwipeBallGame game;
    OrthographicCamera cam;

    public InputHandler(SwipeBallGame game, OrthographicCamera cam){
        this.game = game;
        this.cam = cam;
    }

	@Override
	public boolean keyDown(int keycode) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
        if (keycode == Input.Keys.BACK || keycode == Input.Keys.ESCAPE) {
            game.setScreen(new MenuScreen(game));
            GameWorld.playMusic = false;
            GameWorld.stopMusic();
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
        if(GameRenderer.firstGame && !fromMenu){
            GameRenderer.firstGame = false;
        }

        if(!fromMenu) {
            firstTouch.set(firstTouch.x, firstTouch.y);
            delta = new Vector2(lastTouch.x, lastTouch.y).cpy().sub(firstTouch);
            delta.limit(200);
            delta.scl(1/10f);

            if (delta.angle() < 280) {
                delta.y = 0;
                delta.x = 0;
            }

            if (!Box.isFlying()) {
                Box.setVelocity(delta);
            }
            if (Box.getYPosition() < 0) {
                if(GameVars.restartBounds.contains(screenX, screenY)) {
                    if(Box.getScore() > 1 && Box.prefs.getBoolean("showRate")) {
                        SwipeBallGame.googleServices.rateGame();
                    }

                    Box.checkHighScore();
                    Box.resetScore();

                    LevelReader.clearLevel();
                    GenerateLevel.reset();
                    GameWorld.reset();
                    GenerateLevel.setUp();
                    GenerateLevel.generate();
                    GameRenderer.resetCameraPosition();


                }
                else if(GameVars.submitScoreBounds.contains(screenX, screenY)){
                    SwipeBallGame.googleServices.signIn();
                    SwipeBallGame.googleServices.submitScore(Box.getScore());
                }

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
