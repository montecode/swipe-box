package me.montecode.games.swipeball.gameworld;

import static me.montecode.games.swipeball.utils.GameConstants.PPM;
import me.montecode.games.swipeball.gameobjects.Ball;
import me.montecode.games.swipeball.levels.GenerateLevel;
import me.montecode.games.swipeball.levels.LevelReader;
import me.montecode.games.swipeball.utils.GameConstants;
import me.montecode.games.swipeball.utils.GameVars;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;



public class GameWorld{
	
	float PPM = GameConstants.PPM;
	float gw = GameConstants.GAME_WIDTH;
	float gh = GameConstants.GAME_HEIGHT;
	int lastJumpNumber = 0;
	boolean isFirstTime = true;
	boolean isRestarted = false;
	boolean isTimeForGenerate = false;
	int nextBlock = 1;
	LevelReader lvlReader;
	World world;
	ContactListener listener = new ContactListener(){
		@Override
		public void beginContact(Contact contact) {
			Body a = contact.getFixtureA().getBody();
	        Body b = contact.getFixtureB().getBody();
	        
	        if((a.getUserData().equals("finish") && b.getUserData().equals("ball")) ||
	        	(a.getUserData().equals("ball") && b.getUserData().equals("finish"))){	        	
	        	GameVars.currentLvl = GameVars.lastLvl + 1;
	        }
	        else if((a.getUserData().equals("restart") && b.getUserData().equals("ball")) ||
		        	(a.getUserData().equals("ball") && b.getUserData().equals("restart"))){
	        	GameVars.ballPosition = new Vector2(GameConstants.BALL_X / PPM, GameConstants.BALL_Y / PPM);
	        	isRestarted = true;
	        }
	        else if((a.getUserData().equals("block" + nextBlock) && b.getUserData().equals("ball")) ||
		        	(a.getUserData().equals("ball") && b.getUserData().equals("block" + nextBlock))){
	        		Ball.setVelocity(Vector2.Zero);
	        		isTimeForGenerate = true;
	        		nextBlock++;
	        }
			
		}


		@Override
		public void endContact(Contact contact) {
			// TODO Auto-generated method stub
			
		}


		@Override
		public void preSolve(Contact contact, Manifold oldManifold) {
			// TODO Auto-generated method stub
			
		}


		@Override
		public void postSolve(Contact contact, ContactImpulse impulse) {
			// TODO Auto-generated method stub
			
		}
	};
	
	
	public GameWorld(World world){
		this.world = world;
		lvlReader = new LevelReader(world);
		GenerateLevel generateLevel = new GenerateLevel(world);
		world.setContactListener(listener);
	}
	
	
	public void update(float delta, OrthographicCamera cam){
		world.step(delta, 6, 2);
		if(isRestarted){
			Ball.setPosition(GameVars.ballPosition);
			isRestarted = false;
			Ball.setVelocity(Vector2.Zero);
		}
		if(isTimeForGenerate){
			GenerateLevel.generate();
			isTimeForGenerate = false;
			isFirstTime = false;
			lastJumpNumber = Ball.getJumpNumber();
			cam.position.x = Ball.getXPosition() + cam.viewportWidth / 2 - 20 / PPM;
			cam.update();
		}
		switch(GameVars.currentLvl){
			case 1:
				lvlReader.readLevel(Gdx.files.internal("lvl01.txt").reader());
				GameVars.currentLvl = 0;
				GameVars.lastLvl = 1;
				break;
			case 2:
				reset("02", 2);
				break;
			case 3:
				lvlReader.clearLevel();
				GenerateLevel.setUp();
				GenerateLevel.generate();
				GameVars.currentLvl = 0;
				break;
			case 4:
				reset("04", 4);
				break;
			case 5:
				reset("05", 5);
				break;
			case 6:
				reset("06", 6);
				break;
			case 7:
				reset("07", 7);
				break;
			case 8:
				reset("08", 8);
				break;
			case 9:
				reset("09", 9);
				break;
			case 10:
				reset("10", 10);
				break;
			case 11:
				reset("11", 11);
				break;
			default:
				
		}
		
		
			
		}
	
		public void reset(String lvlnum, int lvlnumber){
			
			lvlReader.clearLevel();
			Ball.setVelocity(new Vector2(0, 0));
			Ball.setPosition(new Vector2(GameConstants.BALL_X / PPM, GameConstants.BALL_Y / PPM));
			lvlReader.readLevel(Gdx.files.internal("lvl" + lvlnum +".txt").reader());
			GameVars.currentLvl = 0;
			GameVars.lastLvl = lvlnumber;
			
		}
}



