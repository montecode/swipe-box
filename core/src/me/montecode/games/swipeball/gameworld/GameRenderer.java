package me.montecode.games.swipeball.gameworld;

import me.montecode.games.swipeball.gameobjects.Block;
import me.montecode.games.swipeball.gameobjects.Box;
import me.montecode.games.swipeball.helpers.AssetLoader;
import me.montecode.games.swipeball.levels.GenerateLevel;
import me.montecode.games.swipeball.utils.GameConstants;
import me.montecode.games.swipeball.utils.GameVars;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;

import static me.montecode.games.swipeball.utils.GameConstants.PPM;

public class GameRenderer{
	
	SpriteBatch batch;
	ShapeRenderer shapeRenderer, shapeRenderer2;
	Box2DDebugRenderer debugRenderer;
	static OrthographicCamera b2dcam;
	Array<Body> bodies = new Array<Body>();
    public static boolean firstGame = true;
	float h, w, posy1, posy2;
	World world;
	Box box;
    public static BitmapFont font;
    FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/Dimbo Regular.ttf"));
    FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();

	public GameRenderer(World world, OrthographicCamera cam){
		this.world = world;
		shapeRenderer = new ShapeRenderer();
        shapeRenderer2 = new ShapeRenderer();
		batch  = new SpriteBatch();
		h = Gdx.graphics.getHeight();
		w = Gdx.graphics.getWidth();
        posy1 = 0;
        posy2 = Gdx.graphics.getHeight();
        b2dcam = cam;
        //parameter.size = 30;
        parameter.size = (int)(30 * Gdx.graphics.getDensity()/1.3);
        font = generator.generateFont(parameter);
        generator.dispose();
        box = new Box(world);
		debugRenderer = new Box2DDebugRenderer();
	
	}
	
	public void render(){

        world.getBodies(bodies);


        Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        shapeRenderer.setProjectionMatrix(b2dcam.combined);

        //Draw red background
        /*
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(238/256f, 28/256f, 26/256f, 1);
        shapeRenderer.rect(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        shapeRenderer.end();*/

        batch.begin();
        batch.draw(AssetLoader.background, 0, posy1, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        batch.draw(AssetLoader.background, 0, posy2, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        posy1--;
        posy2--;
        if(posy1 < -Gdx.graphics.getHeight()){
            posy1 = 0;
        }
        if(posy2 < 0){
            posy2 = Gdx.graphics.getHeight();
        }
        batch.end();

        //Draw score
		batch.begin();
		font.draw(batch, Box.getScore() + "", w / 2, h - 100);
        font.draw(batch, "high score: " + Box.getHighScore(), 10, h - 10);
		batch.end();

        //Draw box
        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        Gdx.gl20.glLineWidth(0.4f);
        shapeRenderer.setColor(1, 1, 1, 1);
        shapeRenderer.rect(Box.getXPosition() - 0.1f, Box.getYPosition() - 0.1f, 10/100f * 2, 10/100f * 2);
        shapeRenderer.end();
        //Draw blocks
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(1, 1, 1, 1);
        for(Block block : GenerateLevel.blocks){

            if(block.getPosition().x == 0){
                float w = 100 / 100f;
                float h = 60 / 100f;
                shapeRenderer.box(block.getPosition().x, block.getPosition().y, 0, w, h, 0);

            }else {
                float w = block.getWidth();
                float h = block.getHeight();
                shapeRenderer.box(block.getPosition().x - w, block.getPosition().y, 0, w * 2, h, 0);
            }

        }
        shapeRenderer.end();

        if(Box.getYPosition() < 0){
            shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
            shapeRenderer.setColor(244/256f, 119/256f, 124/256f, 0.3f);
            shapeRenderer.rect(b2dcam.position.x - 150/PPM, (GameConstants.GAME_HEIGHT/2 - 200) / PPM, 300 / PPM, 400 / PPM);
            shapeRenderer.end();

            shapeRenderer2.begin(ShapeRenderer.ShapeType.Filled);
            shapeRenderer2.setColor(238/256f, 28/256f, 26/256f, 1);
            shapeRenderer2.rect(GameVars.submitScoreBounds.getX(), GameVars.h - GameVars.submitScoreBounds.getY() - GameVars.submitScoreBounds.getHeight(),
                    GameVars.submitScoreBounds.getWidth(), GameVars.submitScoreBounds.getHeight());
            shapeRenderer2.rect(GameVars.restartBounds.getX(), GameVars.h - GameVars.restartBounds.getY() - GameVars.restartBounds.getHeight(), GameVars.restartBounds.getWidth(),
                    GameVars.restartBounds.getHeight());
            shapeRenderer2.end();

            batch.begin();
            String s1 = "Your Score: " + Box.getScore();
            String s2 = "High Score: " + Box.getHighScore();
            String s3 = "Game Over";
            String s4 = "Restart";
            String s5 = "Submit Score";
            font.draw(batch, s1, w / 2 - font.getBounds(s1).width/2, h / 2 + font.getBounds(s1).height/2);
            font.draw(batch, s2, w / 2 - font.getBounds(s2).width/2, h * 2/3 + font.getBounds(s2).height/2);
            font.draw(batch, s3, w / 2 - font.getBounds(s3).width/2, h - 100 + font.getBounds(s3).height/2);
            font.draw(batch, s4, w / 2 - font.getBounds(s4).width/2, h / 3 + font.getBounds(s4).height/2);
            font.draw(batch, s5, w / 2 - font.getBounds(s5).width/2, h / 5 + font.getBounds(s5).height/2);
            batch.end();
        }

        if(firstGame){
            batch.begin();
            batch.draw(AssetLoader.hint, Gdx.graphics.getWidth() / 2 - AssetLoader.hint.getWidth() / 2,
                    Gdx.graphics.getHeight()  / 2 - AssetLoader.hint.getHeight()  / 2);
            batch.end();
        }

	}
	
	public static void resetCameraPosition(){
		b2dcam.position.set(GameConstants.GAME_WIDTH/ PPM / 2, GameConstants.GAME_HEIGHT / PPM / 2, 0);
		b2dcam.update();
	}
	
}
