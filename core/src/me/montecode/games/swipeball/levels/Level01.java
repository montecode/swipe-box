package me.montecode.games.swipeball.levels;

import me.montecode.games.swipeball.gameobjects.Box;
import me.montecode.games.swipeball.utils.GameConstants;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

import static me.montecode.games.swipeball.utils.GameConstants.PPM;

public class Level01 {

	private  float w = GameConstants.GAME_WIDTH / PPM;
	private  float h = GameConstants.GAME_HEIGHT / PPM;
	
	public void createLvl(World world){
		BodyDef bd = new BodyDef();
		FixtureDef fd = new FixtureDef();
		bd.position.set(new Vector2(w - 20 / PPM, 0));
		bd.type = BodyType.StaticBody;
		
		PolygonShape shape = new PolygonShape();
		shape.setAsBox(20 / PPM, h *  (3f/4f));
		fd.shape = shape;
		
		Body block = world.createBody(bd);
		block.createFixture(fd);
		
		bd.position.set(new Vector2(0, 0));
		bd.type = BodyType.StaticBody;
		
		shape = new PolygonShape();
		shape.setAsBox(20 / PPM, h);
		fd.shape = shape;
		
		Body block2 = world.createBody(bd);
		block2.createFixture(fd);
		
		
		bd.position.set(new Vector2(w - 40 / PPM, (100 + 40) / PPM));
		bd.type = BodyType.StaticBody;
		
		shape = new PolygonShape();
		shape.setAsBox(100 / PPM, 100 / PPM);
		fd.shape = shape;
		
		Body block3 = world.createBody(bd);
		block3.createFixture(fd);
		
		
		shape.dispose();
		
	}
	
}
