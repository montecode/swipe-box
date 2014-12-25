package me.montecode.games.swipeball.gameworld;

import me.montecode.games.swipeball.gameobjects.Block;
import me.montecode.games.swipeball.gameobjects.Box;
import me.montecode.games.swipeball.levels.GenerateLevel;
import me.montecode.games.swipeball.utils.GameConstants;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;

import static me.montecode.games.swipeball.utils.GameConstants.PPM;

public class GameRenderer{
	
	SpriteBatch batch;
	ShapeRenderer shapeRenderer;
	Box2DDebugRenderer debugRenderer;
	static OrthographicCamera b2dcam;
	Array<Body> bodies = new Array<Body>();
	float h, w;
	World world;
	Box box;
    BitmapFont font;

	public GameRenderer(World world, OrthographicCamera cam){
		this.world = world;
		shapeRenderer = new ShapeRenderer();
		batch  = new SpriteBatch();
		h = Gdx.graphics.getHeight();
		w = Gdx.graphics.getWidth();
        new BitmapFont();
        b2dcam = cam;
        font = new BitmapFont();
        box = new Box(world);
		debugRenderer = new Box2DDebugRenderer();
	
	}
	
	public void render(){

        world.getBodies(bodies);

        Gdx.gl.glClearColor(238/256f, 28/256f, 26/256f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
        //batch.setProjectionMatrix(b2dcam.combined);
		debugRenderer.render(world, b2dcam.combined);
        shapeRenderer.setProjectionMatrix(b2dcam.combined);
		batch.begin();
		font.draw(batch, Box.getScore() + "", w / 2, h - 100);
        font.draw(batch, "high score: " + Box.getHighScore(), 10, h - 10);
		batch.end();
        //Draw box
        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        shapeRenderer.setColor(1, 1, 1, 1);
        shapeRenderer.box(Box.getXPosition() - 0.1f, Box.getYPosition() - 0.1f, 0, 10/100f * 2, 10/100f * 2, 0);
        shapeRenderer.end();
        //Draw blocks
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(244/256f, 119/256f, 124/256f, 1);
        for(Block block : GenerateLevel.blocks){
            //Gdx.app.log("pos: ", block.getPosition() + "");
            //Gdx.app.log("size", GenerateLevel.blocks.size + "");
            if(block.getPosition().x == 0){
                float w = 100 / 100f;
                float h = 60 / 100f;
                shapeRenderer.box(block.getPosition().x, block.getPosition().y, 0, w, h, 0);

            }else {
                float w = block.width;
                float h = 60 / 100f;
                shapeRenderer.box(block.getPosition().x - w, block.getPosition().y, 0, w * 2, h, 0);
            }

        }
        shapeRenderer.end();
		
	}
	
	public static void resetCameraPosition(){
		b2dcam.position.set(GameConstants.GAME_WIDTH / PPM / 2, GameConstants.GAME_HEIGHT / PPM / 2, 0);
		b2dcam.update();
	}
	
}
