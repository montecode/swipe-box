package me.montecode.games.swipeball.gameworld;

import me.montecode.games.swipeball.gameobjects.Box;
import me.montecode.games.swipeball.levels.GenerateLevel;
import me.montecode.games.swipeball.levels.LevelReader;
import me.montecode.games.swipeball.utils.Enums;
import me.montecode.games.swipeball.utils.GameConstants;
import me.montecode.games.swipeball.utils.GameVars;
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
    GenerateLevel generateLevel;
	int lastJumpNumber = 0;
	boolean isFirstTime = true;
	boolean isTimeForGenerate = false;
    boolean isTimeForCameraTranslate = false;
    boolean toDestroyBlock = false;
	static int nextBlock = 1;
	LevelReader lvlReader;
	World world;
    Body a, b;
	Enums.states currentState;
    int destroyBlock = 0;
    Array <Body> bodies = new Array<Body>();

	ContactListener listener = new ContactListener(){
		@Override
		public void beginContact(Contact contact) {
			a = contact.getFixtureA().getBody();
	        b = contact.getFixtureB().getBody();
	        if((a.getUserData().equals("block" + nextBlock) && b.getUserData().equals("box")) ||
		        	(a.getUserData().equals("box") && b.getUserData().equals("block" + nextBlock))){
                    Box.stop();
	        		isTimeForGenerate = true;
                    isTimeForCameraTranslate = true;
	        		nextBlock++;
	        		Box.updateScore();
	        }
            else{
                Box.stop();
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
        if(isTimeForCameraTranslate) {
            translateCamera(cam, Box.getXPosition() + cam.viewportWidth / 2 - 50 / PPM);
        }
		if(isTimeForGenerate){
			GenerateLevel.generate();
			isTimeForGenerate = false;
			isFirstTime = false;
			lastJumpNumber = Box.getScore();
		}

        if(toDestroyBlock){
            /*world.getBodies(bodies);
            for(Body body : bodies){
                if(body.getUserData().equals("block" + destroyBlock)){
                    world.destroyBody(body);
                }
            }*/
           GenerateLevel.blocks.removeIndex(0);
           destroyBlock++;
           toDestroyBlock = false;
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

        public void translateCamera(OrthographicCamera camera, float location){
            if(camera.position.x <  location && Box.getYPosition() > 0) {
                camera.translate(10 / PPM, 0);
                camera.update();

            }else{
                isTimeForCameraTranslate = false;
                if(Box.getYPosition() > 0) {
                    toDestroyBlock = true;
                }
            }
        }
}



