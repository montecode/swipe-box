package me.montecode.games.swipeball.levels;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;

import me.montecode.games.swipeball.gameobjects.Ball;
import me.montecode.games.swipeball.gameobjects.Box;
import me.montecode.games.swipeball.utils.GameConstants;

public class LevelReader {
	//Translate txt file to lvl
	//X - box
	static World world;
	
	
	public LevelReader(World world){
		this.world = world;
	}
	
	
	
	public static void readLevel(String file){
		BufferedReader br = null;
		try{
			br = new BufferedReader(new FileReader(file));
			String line;
			int lineNumber = 1;
			float yPosition = 0;
			float xPosition = 0;
			while ((line = br.readLine()) != null){
				char[] chars = line.toCharArray();
				yPosition = GameConstants.GAME_HEIGHT - lineNumber * 40;
				for(int i = 0; i < chars.length; i++){
					xPosition = i * 40 + 20;
					if(chars[i] == 'X'){
						//create box
						Box box = new Box(world, new Vector2(xPosition, yPosition), 20, 20);
					}
				}
				lineNumber++;
		}
		}
		catch(IOException e){
			e.printStackTrace();
		}
		finally{
			try {
				br.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}