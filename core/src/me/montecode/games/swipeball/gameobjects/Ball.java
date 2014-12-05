package me.montecode.games.swipeball.gameobjects;

import static me.montecode.games.swipeball.utils.GameConstants.PPM;

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
	
	
	public Ball(World world){
		BodyDef bd = new BodyDef();
		FixtureDef fd = new FixtureDef();
		
		bd.type = BodyType.DynamicBody;
		
		bd.position.set(new Vector2(60 / PPM, 300 / PPM));
		
		CircleShape shape = new CircleShape();
		shape.setRadius(10 / PPM);
		
		fd.shape = shape;
		fd.restitution = 0.3f;
		
		
		
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
	
}
