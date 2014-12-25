package me.montecode.games.swipeball.gameworld;

import me.montecode.games.swipeball.gameobjects.Box;
import me.montecode.games.swipeball.levels.GenerateLevel;
import me.montecode.games.swipeball.levels.LevelReader;
import me.montecode.games.swipeball.utils.Enums;
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


public class GameWorld{
	
	float PPM = GameConstants.PPM;
    GenerateLevel generateLevel;
	int lastJumpNumber = 0;
	boolean isFirstTime = true;
	boolean isTimeForGenerate = false;
    boolean toDestroyBlock = false;
	static int nextBlock = 1;
	LevelReader lvlReader;
	World world;
	Enums.states currentState;
    Body c = null;
    int destroyBlock = 0;

	ContactListener listener = new ContactListener(){
		@Override
		public void beginContact(Contact contact) {
			Body a = contact.getFixtureA().getBody();
	        Body b = contact.getFixtureB().getBody();
	        if((a.getUserData().equals("block" + nextBlock) && b.getUserData().equals("box")) ||
		        	(a.getUserData().equals("box") && b.getUserData().equals("block" + nextBlock))){
	        		Box.setVelocity(Vector2.Zero);
	        		isTimeForGenerate = true;
	        		nextBlock++;
	        		Box.updateScore();
                    if(c != null) {
                        toDestroyBlock = true;
                    }
	        }
            else{
                if(!b.getUserData().equals("box") && b.getUserData().equals("block" + destroyBlock)){
                    c = b;
                }else if(!a.getUserData().equals("box") && a.getUserData().equals("block" + destroyBlock)){
                    c = a;
                }
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
		currentState = Enums.states.PLAY;
		lvlReader = new LevelReader(world);
		world.setContactListener(listener);
        generateLevel = new GenerateLevel(world);
	}
	
	
	public void update(float delta, OrthographicCamera cam){
		world.step(delta, 6, 2);
		if(isTimeForGenerate){
			GenerateLevel.generate();
			isTimeForGenerate = false;
			isFirstTime = false;
			lastJumpNumber = Box.getScore();
			cam.position.x = Box.getXPosition() + cam.viewportWidth / 2 - 20 / PPM;
			cam.update();
		}

        if(toDestroyBlock){
           world.destroyBody(c);
           GenerateLevel.blocks.removeIndex(0);
           destroyBlock++;
           toDestroyBlock = false;
           c = null;
        }

		switch(currentState){
			case PLAY:
				lvlReader.clearLevel();
				GenerateLevel.setUp();
				GenerateLevel.generate();
				GameVars.currentLvl = 0;
				currentState = Enums.states.RUNNING;
				break;
			default:
				
		}
		
		
			
		}
	
		public static void reset(){
			
			nextBlock = 1;
			
		}
}



