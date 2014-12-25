package me.montecode.games.swipeball.helpers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class AssetLoader {

    public static Texture menuBg, about, playBtn, aboutBtn, splashScreen;

    public static void load(){
        splashScreen = new Texture(Gdx.files.internal("splash.png"));
    }

    public static void dispose(){
        splashScreen.dispose();
    }

}
