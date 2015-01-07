package me.montecode.games.swipeball.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import me.montecode.games.swipeball.gameworld.GameRenderer;

public class GameVars {
	public static int currentLvl = 1;
    public static float h = Gdx.graphics.getHeight();
    public static float w = Gdx.graphics.getWidth();
    public static float sw = GameRenderer.font.getBounds("Submit score").width + 10;
    public static Rectangle restartBounds = new Rectangle(w/2 - sw/2 ,h * 2/3 - 20, sw, 40);
    public static Rectangle submitScoreBounds = new Rectangle(w/2 - sw/2, h * 4/5 - 20 , sw, 40);
}
