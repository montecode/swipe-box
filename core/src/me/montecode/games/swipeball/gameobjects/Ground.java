package me.montecode.games.swipeball.gameobjects;

import static me.montecode.games.swipeball.utils.GameConstants.PPM;
import me.montecode.games.swipeball.utils.GameConstants;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

public class Ground {

	public Body createGround(World world){
		
		BodyDef bd = new BodyDef();
		FixtureDef fd = new FixtureDef();
		
		bd.type = BodyType.StaticBody;
		bd.position.set(new Vector2(0 / PPM, 0 / PPM));
		
		PolygonShape shape = new PolygonShape();
		shape.setAsBox(GameConstants.GAME_WIDTH / PPM, 100 / PPM);
		
		fd.shape = shape;
		
		Body ground = world.createBody(bd);
		ground.createFixture(fd);
		
		shape.dispose();
		return ground;
		
	}
	
}
