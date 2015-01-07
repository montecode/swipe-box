package me.montecode.games.swipeball.gameworld;

import me.montecode.games.swipeball.gameobjects.Box;
import me.montecode.games.swipeball.helpers.AssetLoader;
import me.montecode.games.swipeball.levels.GenerateLevel;
import me.montecode.games.swipeball.levels.LevelReader;
import me.montecode.games.swipeball.screens.MenuScreen;
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
import com.badlogic.gdx.utils.Array;


public class GameWorld{
	
	float PPM = GameConstants.PPM;
    static int i;
    GenerateLevel generateLevel;
	int lastJumpNumber;
	boolean isFirstTime;
	boolean isTimeForGenerate;
    static public boolean playSound, playMusic;
    boolean isTimeForCameraTranslate;
    boolean toDestroyBlock;
	static int nextBlock;
	LevelReader lvlReader;
	World world;
    Body a, b;
	Enums.states currentState;
    int destroyBlock;
    //Array <Body> bodies = new Array<Body>();

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
                    if(MenuScreen.isSoundOn) {
                        playSound = true;
                    }

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
        lastJumpNumber = 0;
        isFirstTime = true;
        isTimeForGenerate = false;
        isTimeForCameraTranslate = false;
        toDestroyBlock = false;
        playSound = false;
        if(MenuScreen.isSoundOn) {
            playMusic = true;
        }
        nextBlock = 1;
        destroyBlock = 0;
        i = 1;
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

        if(Box.getYPosition() < 0 && i == 1){
            playSound = true;
            i++;
        }

        if(playSound){
            if(Box.getYPosition() < 0){
                AssetLoader.gameOverSound.play();
            }
            else {
                AssetLoader.jumpSound.play();
            }
            playSound = false;
        }

        if(playMusic && !AssetLoader.gameMusic.isPlaying()){
            AssetLoader.gameMusic.play();
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
            i = 1;
			
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
        public static void stopMusic(){
            AssetLoader.gameMusic.stop();
        }
}



