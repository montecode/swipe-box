package me.montecode.games.swipeball.levels;

import java.util.Random;

import me.montecode.games.swipeball.gameobjects.Block;
import me.montecode.games.swipeball.gameobjects.Box;
import me.montecode.games.swipeball.utils.GameConstants;
import static me.montecode.games.swipeball.utils.GameConstants.PPM;



import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;


public class GenerateLevel {

	static World world;
	public static Array <Block> blocks;
	static float lastPosition = 0;
	static int blockNumber = 1;
    static Vector2 position;
	static float width, height;
	
	public GenerateLevel(World world){
		this.world = world;
        blocks = new Array();
        position = new Vector2();
	}
	
	public static void setUp(){
        blocks = new Array();
		Box.stop();
		Box.setPosition(new Vector2(GameConstants.BALL_X / PPM, GameConstants.BALL_Y / PPM));
        Block block = new Block(world, new Vector2(0, 0), 100, 60, 0);
        width = 100;
		blocks.add(block);
	}
	
	public static void generate(){
		Random rand = new Random();
		position.x = rand.nextFloat() * 350 + width + 50 + lastPosition;
		position.y = 0;
		height = 60;
        width = rand.nextFloat() * 50 + 20;
		blocks.add(new Block(world, position, width, height, blockNumber));
		lastPosition = position.x;
		blockNumber++;
	}
	
	public static void reset(){
		lastPosition = 0;
		blockNumber = 1;
	}
}
