package me.montecode.games.swipeball.helpers;


import me.montecode.games.swipeball.gameobjects.Ball;
import me.montecode.games.swipeball.utils.GameVars;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.math.Vector2;

public class InputHandler implements InputProcessor{
	
	
	private Vector2 lastTouch = new Vector2();
	private Vector2 delta, newTouch, firstTouch;
	private float scale = 3;
	
	@Override
	public boolean keyDown(int keycode) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		if(keycode == Keys.SPACE){
			GameVars.currentLvl++;
		}
		return false;
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
		firstTouch.set(firstTouch.x / scale, firstTouch.y / scale);
		delta = new Vector2(lastTouch.x / scale, lastTouch.y / scale).cpy().sub(firstTouch);
		delta.limit(20);
		
		if(delta.y > 0){
			delta.y = 0;
			delta.x = 0;
		}
		if(!Ball.isFlying()){
			Ball.setVelocity(delta);
		}
			
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
