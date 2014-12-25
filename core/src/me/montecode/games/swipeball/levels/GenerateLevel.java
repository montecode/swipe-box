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
	static float lastPosition = 100;
	static int blockNumber = 1;
	
	
	public GenerateLevel(World world){
		this.world = world;
        blocks = new Array();
	}
	
	public static void setUp(){
        blocks = new Array();
		Box.setVelocity(new Vector2(0, 0));
		Box.setPosition(new Vector2(GameConstants.BALL_X / PPM, GameConstants.BALL_Y / PPM));
        Block block = new Block(world, new Vector2(0, 0), 100, 60, 0);
		blocks.add(block);
	}
	
	public static void generate(){
		Vector2 position = new Vector2();
		float width, height;
		Random rand = new Random();
		width = rand.nextFloat() * 50 + 20;
		position.x = rand.nextFloat() * 400 + width + 100 + lastPosition;
		position.y = 0;
		height = 60;
		blocks.add(new Block(world, position, width, height, blockNumber));
		lastPosition = position.x;
		blockNumber++;
	}
	
	public static void reset(){
		lastPosition = 0;
		blockNumber = 1;
	}
}
