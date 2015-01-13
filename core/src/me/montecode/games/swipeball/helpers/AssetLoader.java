package me.montecode.games.swipeball.helpers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;

public class AssetLoader {

    public static Texture splashScreen, hint, hint2, background, soundon, soundoff, menuBg;
    public static Music gameMusic;
    public static Sound jumpSound, gameOverSound;

    public static void load(){
        splashScreen = new Texture(Gdx.files.internal("splash.png"));
        hint = new Texture(Gdx.files.internal("hint.png"));
        hint2 = new Texture(Gdx.files.internal("hint2.png"));
        background = new Texture(Gdx.files.internal("background-01.png"));
        gameMusic = Gdx.audio.newMusic(Gdx.files.internal("music/space_battle.mp3"));
        jumpSound = Gdx.audio.newSound(Gdx.files.internal("sounds/jump.mp3"));
        gameOverSound = Gdx.audio.newSound(Gdx.files.internal("sounds/game_over.mp3"));
        soundon = new Texture(Gdx.files.internal("soundon.png"));
        soundoff = new Texture(Gdx.files.internal("soundoff.png"));
        menuBg = new Texture(Gdx.files.internal("moving_menu_background.png"));
    }

    public static void dispose(){
        splashScreen.dispose();
        hint.dispose();
        background.dispose();
        gameMusic.dispose();
        jumpSound.dispose();
        gameOverSound.dispose();
        soundon.dispose();
        soundoff.dispose();
        menuBg.dispose();
        hint2.dispose();
    }

}
