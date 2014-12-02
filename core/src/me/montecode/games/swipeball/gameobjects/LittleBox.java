package me.montecode.games.swipeball.gameobjects;

import static me.montecode.games.swipeball.utils.GameConstants.PPM;
import me.montecode.games.swipeball.utils.GameConstants;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;

public class LittleBox{

	World world;
	Vector2 position;
	Body block;
	
	public LittleBox(World world, Vector2 position, float width, float height) {
		this.world = world;
		this.position = position;
		
		float w = GameConstants.GAME_WIDTH / PPM;
		float h = GameConstants.GAME_HEIGHT / PPM;
		
		BodyDef bd = new BodyDef();
		FixtureDef fd = new FixtureDef();
		
		bd.type = BodyType.DynamicBody;
		
		bd.position.set(new Vector2(position.x / PPM, position.y / PPM));
		
		
		PolygonShape shape = new PolygonShape();
		shape.setAsBox(width / PPM, height / PPM);
		fd.shape = shape;
		
		block = world.createBody(bd);
		block.createFixture(fd);
		shape.dispose();
	}

}
