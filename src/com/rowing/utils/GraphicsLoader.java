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
		AnimatedRenderer flame = new AnimatedRenderer(true);
		flame.ox=0;
		flame.oy=0;
		TextureRegion[][] tmp = TextureRegion.split(load("fireworks_sprite_2.png"), 200, 200);
		TextureRegion [] frames = new TextureRegion[tmp.length * tmp[0].length];
        int index = 0;
        for (int i = 0; i < tmp.length; i++) {
                for (int j = 0; j < tmp[0].length; j++) {
                        frames[index++] = tmp[i][j];
                }
        }
        flame.animation = new Animation(5f/24, frames);
        return flame;
	}
	
	public static AnimatedRenderer loadFireworks2(){
		AnimatedRenderer flame = new AnimatedRenderer(true);
		flame.ox=0;
		flame.oy=0;
		TextureRegion[][] tmp = TextureRegion.split(load("firework_sprite_3.png"), 200, 200);
		TextureRegion [] frames = new TextureRegion[tmp.length * tmp[0].length];
        int index = 0;
        for (int i = 0; i < tmp.length; i++) {
                for (int j = 0; j < tmp[0].length; j++) {
                        frames[index++] = tmp[i][j];
                }
        }
        flame.animation = new Animation(5f/16, frames);
        return flame;
	}

}
