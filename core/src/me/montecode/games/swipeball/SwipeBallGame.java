package me.montecode.games.swipeball;

import me.montecode.games.swipeball.screens.GameScreen;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class SwipeBallGame extends Game {

	@Override
	public void create() {
		setScreen(new GameScreen());
	}
	
	
}
