package me.montecode.games.swipeball.gameobjects;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;


import static me.montecode.games.swipeball.utils.GameConstants.PPM;

public class Block{

    Body block;

    float width, height;
    static int blockNumber;

    public Block(World world, Vector2 position, float width, float height, int blockNumber){
        this.width = width / PPM;
        this.height = height / PPM;
        this.blockNumber = blockNumber;
        BodyDef bd = new BodyDef();
        FixtureDef fd = new FixtureDef();

        bd.type = BodyDef.BodyType.StaticBody;

        bd.position.set(new Vector2(position.x / PPM, position.y / PPM));

        PolygonShape shape = new PolygonShape();
        shape.setAsBox(width / PPM, height / PPM);
        fd.shape = shape;
        fd.filter.maskBits = 6;
        fd.filter.categoryBits = 4;

        block = world.createBody(bd);
        block.createFixture(fd);
        block.setUserData("block" + blockNumber);
        shape.dispose();


    }

    public  Vector2 getPosition(){
        return block.getPosition();
    }

    public float getWidth(){
        return width;
    }
}
