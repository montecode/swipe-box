package me.montecode.games.swipeball.gameworld;

import me.montecode.games.swipeball.gameobjects.Ball;
import me.montecode.games.swipeball.gameobjects.Ground;
import me.montecode.games.swipeball.levels.Level01;
import me.montecode.games.swipeball.utils.GameConstants;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.Shape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;

import static me.montecode.games.swipeball.utils.GameConstants.PPM;

public class GameRenderer{
	
	SpriteBatch batch;
	ShapeRenderer shapeRenderer;
	Box2DDebugRenderer debugRenderer;
	OrthographicCamera cam, b2dcam;
	Array<Body> bodies;
	float h, w;
	World world;
	Ball ball;
	Ground ground;
	Level01 lvl1;
	
	public GameRenderer(World world){
		this.world = world;
		shapeRenderer = new ShapeRenderer();
		batch  = new SpriteBatch();
		h = Gdx.graphics.getHeight();
		w = Gdx.graphics.getWidth();
		cam = new OrthographicCamera(w, h);
        cam.position.set(cam.viewportWidth / 2f, cam.viewportHeight / 2f, 0);
        cam.update();
        
        b2dcam = new OrthographicCamera();
        b2dcam.setToOrtho(false, GameConstants.GAME_WIDTH / PPM, GameConstants.GAME_HEIGHT / PPM);
        b2dcam.update();
        
		ball = new Ball(world);
		ground = new Ground();
		ground.createGround(world);
		lvl1 = new Level01();
		lvl1.createLvl(world);
		bodies = new Array<Body>(world.getBodyCount());
		debugRenderer = new Box2DDebugRenderer();
		
		
		
		
	}
	
	public void render(){
		
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		/*
		shapeRenderer.setProjectionMatrix(cam.combined);
		
		shapeRenderer.begin(ShapeType.Filled);
		
		world.getBodies(bodies);
		
		for(Body body : bodies) {
			
			shapeRenderer.setColor(new Color(1, 0, 0, 1));
	        shapeRenderer.circle(body.getPosition().x, body.getPosition().y, GameConstants.BALL_RADIUS);
		}
		
		
		
		shapeRenderer.end();
		*/
		debugRenderer.render(world, b2dcam.combined);
		
		
	}
	
}
