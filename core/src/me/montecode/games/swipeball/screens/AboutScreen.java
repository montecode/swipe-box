package me.montecode.games.swipeball.screens;

import me.montecode.games.swipeball.SwipeBallGame;
import me.montecode.games.swipeball.utils.GameConstants;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.*;

public class AboutScreen implements Screen, InputProcessor{
	
	SwipeBallGame game;
	private static final int VIEWPORT_WIDTH = GameConstants.GAME_WIDTH;
    private static final int VIEWPORT_HEIGHT = GameConstants.GAME_HEIGHT;
    private OrthographicCamera camera;
    private SpriteBatch batcher;
    private BitmapFont font;
    private Texture about;
    private Rectangle linkRect;
    private ShapeRenderer shapeRenderer;
    private int firstTouch = 0;
	
	
	public AboutScreen(SwipeBallGame game){
		this.game = game;
		setupCamera();
        batcher = new SpriteBatch();
        shapeRenderer = new ShapeRenderer();
        Gdx.input.setCatchBackKey(true);
        Gdx.input.setInputProcessor(this);
        about = new Texture(Gdx.files.internal("montecode.png"));
        font = new BitmapFont();
        linkRect = new Rectangle(0,0, GameConstants.GAME_WIDTH, GameConstants.GAME_HEIGHT);
	}
	
	public void update(){
		
	}
	
	public void draw() {
        Gdx.gl.glClearColor(243, 236, 205, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        camera.update();


        batcher.setProjectionMatrix(camera.combined);

        batcher.begin();


        batcher.draw(about, 0, 0, VIEWPORT_WIDTH, VIEWPORT_HEIGHT);
        font.draw(batcher, "www.montecode.me", VIEWPORT_WIDTH / 2, VIEWPORT_HEIGHT / 4);


        batcher.end();
	}
	
	private void setupCamera() {
        camera = new OrthographicCamera(VIEWPORT_WIDTH, VIEWPORT_HEIGHT);
        camera.position.set(camera.viewportWidth / 2, camera.viewportHeight / 2, 0f);
        camera.update();
    }
	
	@Override
	public boolean keyDown(int keycode) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		if (keycode == Input.Keys.BACK) {
            game.setScreen(new MenuScreen(game));
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
        if (linkRect.contains(screenX, screenY) && firstTouch > 1) {
            Gdx.net.openURI("http://montecode.me");
        }
        firstTouch++;
        return true;
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

	@Override
	public void render(float delta) {
		update();
		draw();
		
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
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
		// TODO Auto-generated method stub
		
	}

}
