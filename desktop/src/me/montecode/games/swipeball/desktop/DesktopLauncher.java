package me.montecode.games.swipeball.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import me.montecode.games.swipeball.SwipeBallGame;
import me.montecode.games.swipeball.utils.GameConstants;;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		
		config.width = GameConstants.GAME_WIDTH;
		config.height = GameConstants.GAME_HEIGHT;
		
		new LwjglApplication(new SwipeBallGame(), config);
		
		
	}
}
