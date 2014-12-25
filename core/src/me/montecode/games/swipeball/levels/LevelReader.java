package me.montecode.games.swipeball.levels;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;

import me.montecode.games.swipeball.gameobjects.Block;

public class LevelReader {
	static World world;
	static Array <Body> bodies = new Array();
	
	public LevelReader(World world){
		this.world = world;
	}
	
	public static void clearLevel(){
		world.getBodies(bodies);
		for(Body body : bodies){
			if(!body.getUserData().equals("box")){
				world.destroyBody(body);
			}
		}

	}
	
}