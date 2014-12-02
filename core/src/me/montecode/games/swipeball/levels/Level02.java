package me.montecode.games.swipeball.levels;

import me.montecode.games.swipeball.gameobjects.Box;
import me.montecode.games.swipeball.gameobjects.LittleBox;
import me.montecode.games.swipeball.utils.GameConstants;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;

public class Level02 {
	
	private static  float w = GameConstants.GAME_WIDTH;
	private static  float h = GameConstants.GAME_HEIGHT;
	
	
	public static void createLvl(World world){
		Box box = new Box(world, new Vector2(w * 3 / 2 - 25, h / 2 - 25), 50, 50);
		Box box2 = new Box(world, new Vector2(w * 3 / 2 - 25 - 100, h / 2 - 25), 50, 50);
		Box box3 = new Box(world, new Vector2(w * 3 / 2 - 25 - 200, h / 2 - 25), 50, 50);
		Box box4 = new Box(world, new Vector2(w * 3 / 2 - 25 - 300, h / 2 - 25), 50, 50);
		LittleBox lbox1 = new LittleBox(world, new Vector2(w * 3 / 2 - 25 - 100, h / 2 - 25 + 130), 20, 20);
	}
}
