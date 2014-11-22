package me.montecode.games.swipeball.helpers;


import me.montecode.games.swipeball.gameobjects.Ball;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.math.Vector2;

public class InputHandler implements InputProcessor{
	
	
	private Vector2 lastTouch = new Vector2();
	private Vector2 delta, newTouch, firstTouch;
	private float scale = 3;
	private float maxXr = 20, maxY = -20, maxXl = -20;
	private Vector2 maxVelocity = new Vector2(20, -20);
	
	@Override
	public boolean keyDown(int keycode) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		firstTouch = new Vector2(screenX, screenY);
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		firstTouch.set(firstTouch.x / scale, firstTouch.y / scale);
		delta = new Vector2(lastTouch.x / scale, lastTouch.y / scale).cpy().sub(firstTouch);
		
		
		if(delta.x > maxXr){
			delta.x = maxXr;
		}
		else if(delta.x < maxXl){
			delta.x = maxXl;
		}
		if(delta.y < maxY){
			delta.y = maxY;
		}
		if(delta.y > 0){
			delta.y = 0;
			delta.x = 0;
		}
		if(!Ball.isFlying()){
			Ball.setVelocity(delta);
		}
			
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		Vector2 newTouch = new Vector2(screenX, screenY);		
		
		lastTouch = newTouch;
		return true;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		// TODO Auto-generated method stub
		return false;
	}

	

}
