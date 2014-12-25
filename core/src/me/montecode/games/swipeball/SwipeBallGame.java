package me.montecode.games.swipeball;

import me.montecode.games.swipeball.helpers.AssetLoader;
import me.montecode.games.swipeball.screens.LoadScreen;
import me.montecode.games.swipeball.screens.MenuScreen;

import com.badlogic.gdx.Game;

public class SwipeBallGame extends Game {

	@Override
	public void create() {
		AssetLoader.load();
        setScreen(new LoadScreen(this));
	}
	
	
}
