package me.montecode.games.swipeball.gameobjects;

import static me.montecode.games.swipeball.utils.GameConstants.PPM;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class Box extends Actor{
	
	static Body box;
	static int jumpNumber = 0;
	static Preferences prefs;

	public Box(World world){
		BodyDef bd = new BodyDef();
		FixtureDef fd = new FixtureDef();
		
		bd.type = BodyType.DynamicBody;
		
		bd.position.set(new Vector2(60 / PPM, 100 / PPM));
		
		prefs = Gdx.app.getPreferences("SwipeBall");

        if (!prefs.contains("highScore")) {
            prefs.putInteger("highScore", 0);
        }

		PolygonShape shape = new PolygonShape();
	    shape.setAsBox(10 / PPM, 10 / PPM);
		fd.shape = shape;
		fd.restitution = 0.5f;
        fd.density = 30;
        bd.fixedRotation = true;
		fd.filter.maskBits = 4;
		fd.filter.categoryBits = 6;



		box = world.createBody(bd);
		box.createFixture(fd);
		box.setUserData("box");
		shape.dispose();
	}
	
	
	public static void setVelocity(Vector2 velocity){
        box.applyLinearImpulse(velocity, box.getWorldCenter(), true);
	}

    public static void stop(){
        box.setLinearVelocity(Vector2.Zero);
    }

	public static boolean isFlying(){
		if(box.getLinearVelocity().x == 0 && box.getLinearVelocity().y == 0){
			return false;
		}
		else{ 
			return true;
		}
	}
	
	public static float getXPosition(){
		return box.getPosition().x;
	}
	
	public static float getYPosition(){
		return box.getPosition().y;
	}
	
	public static void setPosition(Vector2 position){
		box.setTransform(position, 0);
	}
	
	public static int getScore(){
		return jumpNumber;
	}
	
	public static void updateScore(){
		jumpNumber++;
	}
	
	public static void resetScore(){
		jumpNumber = 0;
	}

    public static void checkHighScore(){
        if(jumpNumber > prefs.getInteger("highScore")){
            prefs.putInteger("highScore", jumpNumber);
            prefs.flush();
        }
    }

    public static int getHighScore(){
        return prefs.getInteger("highScore");
    }

}
