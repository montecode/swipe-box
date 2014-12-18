package me.montecode.games.swipeball.gameobjects;

import static me.montecode.games.swipeball.utils.GameConstants.PPM;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class Ball extends Actor{
	
	static Body ball;
	static int jumpNumber = 0;
	
	public Ball(World world){
		BodyDef bd = new BodyDef();
		FixtureDef fd = new FixtureDef();
		
		bd.type = BodyType.DynamicBody;
		
		bd.position.set(new Vector2(60 / PPM, 300 / PPM));
		
		//CircleShape shape = new CircleShape();
		PolygonShape shape = new PolygonShape();
		//shape.setRadius(10 / PPM);
	    shape.setAsBox(10 / PPM, 10 / PPM);
		//fd.density = 10;
		fd.shape = shape;
		fd.restitution = 0.3f;
		fd.density = 10;
		fd.filter.maskBits = 4;
		fd.filter.categoryBits = 6 | 8 | 16;
		bd.linearDamping = 1;
		
		ball = world.createBody(bd);
		ball.createFixture(fd);
		ball.setUserData("ball");
		shape.dispose();
	}
	
	
	public static void setVelocity(Vector2 velocity){
		ball.setLinearVelocity(velocity);
	}
	
	public static boolean isFlying(){
		if(ball.getLinearVelocity().x == 0 && ball.getLinearVelocity().y == 0){
			return false;
		}
		else{ 
			return true;
		}
	}
	
	public static float getXPosition(){
		return ball.getPosition().x;
	}
	
	public static float getYPosition(){
		return ball.getPosition().y;
	}
	
	public static void setPosition(Vector2 position){
		ball.setTransform(position, 0);
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
	
}
