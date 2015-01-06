package me.montecode.games.swipeball;

import me.montecode.games.swipeball.helpers.AssetLoader;
import me.montecode.games.swipeball.playservices.IGoogleServices;
import me.montecode.games.swipeball.screens.LoadScreen;
import me.montecode.games.swipeball.screens.MenuScreen;

import com.badlogic.gdx.Game;

public class SwipeBallGame extends Game {

    public static IGoogleServices googleServices;

    public SwipeBallGame(IGoogleServices googleServices)
    {
        super();
        this.googleServices = googleServices;
    }

	@Override
	public void create() {
		AssetLoader.load();
        setScreen(new LoadScreen(this));
	}
	
	
}
