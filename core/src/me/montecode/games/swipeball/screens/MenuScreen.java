package me.montecode.games.swipeball.screens;

import me.montecode.games.swipeball.SwipeBallGame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.viewport.Viewport;

public class MenuScreen implements Screen, InputProcessor{
	
	static SpriteBatch batch;
	static OrthographicCamera cam;
	static ShapeRenderer shapeRenderer;
	static Rectangle playBounds;
	static Rectangle aboutBounds;
	static Vector3 touchPoint;
	static SwipeBallGame game;
    static BitmapFont font;

    FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/Dimbo Regular.ttf"));
    FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();

	public MenuScreen(SwipeBallGame game){
		this.game = game;
		batch = new SpriteBatch();
		setUpCamera();
        parameter.size = 40;
		font = generator.generateFont(parameter);
        generator.dispose();
		playBounds = new Rectangle(Gdx.graphics.getWidth() / 2 - 100, Gdx.graphics.getHeight() / 2 - 37.5f, 200, 75);
        aboutBounds = new Rectangle(Gdx.graphics.getWidth() / 2 - 100, Gdx.graphics.getHeight() / 3.5f - 37.5f, 200, 75);
        touchPoint = new Vector3();
        shapeRenderer = new ShapeRenderer();
        shapeRenderer.setProjectionMatrix(cam.combined);
        Gdx.input.setCatchBackKey(true);
        Gdx.input.setInputProcessor(this);

	}
	
	public static void update(){
		if (Gdx.input.justTouched()) {
            cam.unproject(touchPoint.set(Gdx.input.getX(), Gdx.input.getY(), 0));

            if (playBounds.contains(touchPoint.x, touchPoint.y)) {
                game.setScreen(new GameScreen(game));
            }

            if (aboutBounds.contains(touchPoint.x, touchPoint.y)) {
                game.setScreen(new AboutScreen(game));
            }
        }
	}
	
	public static void draw(){

        Gdx.gl20.glClearColor(0, 0, 0, 1);
        Gdx.gl20.glClear(GL20.GL_COLOR_BUFFER_BIT);

        //Draw red background
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(238/256f, 28/256f, 26/256f, 1);
        shapeRenderer.rect(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        shapeRenderer.end();

		shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(244/256f, 119/256f, 124/256f, 1);
		shapeRenderer.rect(playBounds.getX(), playBounds.getY(), playBounds.getWidth(), playBounds.getHeight());
		shapeRenderer.rect(aboutBounds.getX(), aboutBounds.getY(), aboutBounds.getWidth(), aboutBounds.getHeight());
		shapeRenderer.end();

        batch.begin();
        font.draw(batch, "SwipeBox v1.0", 10, Gdx.graphics.getHeight() - 10);
        font.draw(batch, "Play", playBounds.getX() + playBounds.getWidth() / 2 - font.getBounds("Play").width / 2, playBounds.getY() + playBounds.getHeight() / 2 + font.getBounds("Play").height / 2);
        font.draw(batch, "About", aboutBounds.getX() + aboutBounds.getWidth() / 2 - font.getBounds("About").width / 2, aboutBounds.getY() + aboutBounds.getHeight() / 2 + font.getBounds("About").height / 2);
        batch.end();
		
	}
	
	public static void setUpCamera(){
		cam = new OrthographicCamera();
		cam.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		cam.update();
	}
	
	@Override
	public void render(float delta) {
		update();
		draw();
	}

	@Override
	public void resize(int width, int height) {
		
	}

	@Override
	public void show() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		
		
	}

	@Override
	public boolean keyDown(int keycode) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		
		if( keycode == Input.Keys.BACK || keycode == Input.Keys.ESCAPE ){
			Gdx.app.exit();
		}
		
		return true;
	}

	@Override
	public boolean keyTyped(char character) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		// TODO Auto-generated method stub
		return false;
	}

}
