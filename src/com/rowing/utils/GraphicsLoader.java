package com.rowing.utils;

import java.util.HashMap;
import java.util.Map;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.rowing.core.Constants;
import com.rowing.graphics.AnimatedRenderer;
import com.rowing.graphics.DirectionalAnimatedRenderer;



public class GraphicsLoader {
	
	public static GraphicsLoader instance;
	
	public Map<String,Texture> loaded;
	
	
	public static void initialize(){
		GraphicsLoader.instance = new GraphicsLoader();
	}
	
	private GraphicsLoader(){
		loaded = new HashMap<String, Texture>();
	}
	
	public static Texture load(String texture){
		Texture tex = instance.loaded.get(texture);
		if(tex == null){
			tex = new Texture(Gdx.files.internal(Constants.RESOURCE_DIR + texture));
			instance.loaded.put(texture, tex);
		}
		return tex;
	}
	public static DirectionalAnimatedRenderer loadBoat(){
		DirectionalAnimatedRenderer move = new DirectionalAnimatedRenderer(true);
		
		TextureRegion[][] tmp = TextureRegion.split(load("boat_sprite6.png"), 18, 10);
		for(int i = 0; i < tmp.length; i++){
	   		move.animations[i] = new Animation(0.2f/tmp[i].length, tmp[i]);
	   	}
		
		return move;
	}
	
	public static AnimatedRenderer loadFireworks(){
		AnimatedRenderer fireworks = new AnimatedRenderer(true);
		TextureRegion[][] tmp = TextureRegion.split(load("fireworks_sprite.png"), 64, 72);
		fireworks.animation = new Animation(0.2f/4, tmp[0]);
		return fireworks;
	   	
	}

	

}
