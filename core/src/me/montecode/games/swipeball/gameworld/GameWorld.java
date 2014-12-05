package me.montecode.games.swipeball.gameworld;

import static me.montecode.games.swipeball.utils.GameConstants.PPM;
import me.montecode.games.swipeball.gameobjects.Ball;
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
	boolean isFirstTime = true;
	LevelReader lvlReader;
	World world;
	Array <Body> bodies = new Array();
	ContactListener listener = new ContactListener(){
		@Override
		public void beginContact(Contact contact) {
			Body a = contact.getFixtureA().getBody();
	        Body b = contact.getFixtureB().getBody();
	        Gdx.app.log("a", a.getUserData() + "");
	        Gdx.app.log("b", b.getUserData() + "");
	        
	        if((a.getUserData().equals("finish") && b.getUserData().equals("ball")) ||
	        	(a.getUserData().equals("ball") && b.getUserData().equals("finish"))){
	        	
	        	GameVars.currentLvl = GameVars.lastLvl + 1;
	        	
	        	
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
		world.setContactListener(listener);
	}
	
	
	public void update(float delta, OrthographicCamera cam){
		world.step(delta, 6, 2);
		cam.position.x = Ball.getXPosition();
		cam.update();
		switch(GameVars.currentLvl){
			case 1:
				lvlReader.readLevel(Gdx.files.internal("lvl01.txt").reader());
				GameVars.currentLvl = 0;
				GameVars.lastLvl = 1;
				break;
			case 2:
				world.getBodies(bodies);
				for(Body body : bodies){
					if(!body.getUserData().equals("ball")){
						world.destroyBody(body);
					}
				}
				Ball.setVelocity(new Vector2(0, 0));
				Ball.setPosition(new Vector2(GameConstants.BALL_X / PPM, GameConstants.BALL_Y / PPM));
				lvlReader.readLevel(Gdx.files.internal("lvl02.txt").reader());
				GameVars.currentLvl = 0;
				GameVars.lastLvl = 2;
				break;
			default:
				
		}
		
	}


	
}
