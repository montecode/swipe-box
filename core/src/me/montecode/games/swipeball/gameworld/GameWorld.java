package me.montecode.games.swipeball.gameworld;

import me.montecode.games.swipeball.gameobjects.Ball;
import me.montecode.games.swipeball.levels.Level02;
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
	String file;
	World world;
	Array <Body> bodies = new Array();
	
	public GameWorld(World world){
		this.world = world;
		file = "C:\\Users\\pc\\Documents\\SwipeBall\\android\\assets\\lvl03.txt";
		lvlReader = new LevelReader(world);
	}
	
	
	public void update(float delta, OrthographicCamera cam){
		world.step(delta, 6, 2);
		
		if(Ball.getXPosition() > gw / PPM && isFirstTime){
			GameVars.currentLvl = 3;
			isFirstTime = false;
		}
		switch(GameVars.currentLvl){
			case 1:
				
				break;
			case 2:
				cam.position.set((gw / 2 + gw) / PPM , gh / 2 / PPM, 0);
				cam.update();
				Level02.createLvl(world);
				GameVars.currentLvl = 0;
				break;
				
			case 3:
				world.getBodies(bodies);
				for(Body body : bodies){
					world.destroyBody(body);
				}
				
				lvlReader.readLevel(file);
				break;
			default:
				
		}
		
	}
}
