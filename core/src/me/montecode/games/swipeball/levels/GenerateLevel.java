package me.montecode.games.swipeball.levels;

import java.util.Random;

import me.montecode.games.swipeball.gameobjects.Ball;
import me.montecode.games.swipeball.gameobjects.Box;
import me.montecode.games.swipeball.utils.GameConstants;
import static me.montecode.games.swipeball.utils.GameConstants.PPM;



import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;


public class GenerateLevel {

	static World world;
	static Array <Body> bodies = new Array();
	static float lastPosition = 0;
	static int blockNumber = 1;
	
	
	public GenerateLevel(World world){
		this.world = world;
	}
	
	public static void setUp(){
		Ball.setVelocity(new Vector2(0, 0));
		Ball.setPosition(new Vector2(GameConstants.BALL_X / PPM, GameConstants.BALL_Y / PPM));
		Box box = new Box(world, new Vector2(0, 0), 100, 60, 0);
	}
	
	public static void generate(){
		Vector2 position = new Vector2();
		float width, height;
		Random rand = new Random();
		width = rand.nextFloat() * 50 + 20;
		position.x = rand.nextFloat() * 200 + width + 100 + lastPosition;
		position.y = 0;
		height = 60;
		Box box = new Box(world, position, width, height, blockNumber);
		lastPosition += position.x + 30;
		blockNumber++;
	}
	
}
