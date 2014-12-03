package me.montecode.games.swipeball.gameworld;

import me.montecode.games.swipeball.gameobjects.Ball;
import me.montecode.games.swipeball.levels.LevelReader;
import me.montecode.games.swipeball.utils.GameConstants;
import me.montecode.games.swipeball.utils.GameVars;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;



public class GameWorld{
	
	float PPM = GameConstants.PPM;
	float gw = GameConstants.GAME_WIDTH;
	float gh = GameConstants.GAME_HEIGHT;
	boolean isFirstTime = true;
	LevelReader lvlReader;
	World world;
	Array <Body> bodies = new Array();
	
	public GameWorld(World world){
		this.world = world;
		lvlReader = new LevelReader(world);
	}
	
	
	public void update(float delta, OrthographicCamera cam){
		world.step(delta, 6, 2);
		cam.position.x = Ball.getXPosition() + cam.viewportWidth / 2 - 50 / PPM;
		cam.update();
		switch(GameVars.currentLvl){
			case 1:
				/*
				world.getBodies(bodies);
				for(Body body : bodies){
					world.destroyBody(body);
				}
				*/
				lvlReader.readLevel(Gdx.files.internal("lvl01.txt").reader());
				GameVars.currentLvl = 0;
				break;
			default:
				
		}
		
	}
}
