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
		AnimatedRenderer firework = new AnimatedRenderer(true);
		firework.ox=0;
		firework.oy=0;
		TextureRegion[][] tmp = TextureRegion.split(load("fireworks_sprite_2.png"), 200, 200);
		TextureRegion [] frames = new TextureRegion[tmp.length * tmp[0].length];
        int index = 0;
        for (int i = 0; i < tmp.length; i++) {
                for (int j = 0; j < tmp[0].length; j++) {
                        frames[index++] = tmp[i][j];
                }
        }
        firework.animation = new Animation(5f/24, frames);
        return firework;
	}
	
	public static AnimatedRenderer loadFireworks2(){
		AnimatedRenderer firework = new AnimatedRenderer(true);
		firework.ox=0;
		firework.oy=0;
		TextureRegion[][] tmp = TextureRegion.split(load("firework_sprite_3.png"), 200, 200);
		TextureRegion [] frames = new TextureRegion[tmp.length * tmp[0].length];
        int index = 0;
        for (int i = 0; i < tmp.length; i++) {
                for (int j = 0; j < tmp[0].length; j++) {
                        frames[index++] = tmp[i][j];
                }
        }
        firework.animation = new Animation(5f/16, frames);
        return firework;
	}
	
	public static AnimatedRenderer loadTentacles(){
		AnimatedRenderer tentacle = new AnimatedRenderer(false);
		tentacle.ox = 0;
		tentacle.oy = 0;
		TextureRegion[][] tmp = TextureRegion.split(load("tentacles.png"), 512, 512);
		TextureRegion [] frames = new TextureRegion[tmp.length * tmp[0].length];
        int index = 0;
        for (int i = 0; i < tmp.length; i++) {
                for (int j = 0; j < tmp[0].length; j++) {
                        frames[index++] = tmp[i][j];
                }
        }
        
        tentacle.animation = new Animation(1.5f/frames.length, frames);
        return tentacle;
	}

	
	
	public static AnimatedRenderer loadDead(){
		AnimatedRenderer death = new AnimatedRenderer(false);
		TextureRegion[][] tmp = TextureRegion.split(load("ballista-death.png"), 128, 64);
        TextureRegion [] deathFrames = new TextureRegion[tmp.length * tmp[0].length];
        int index = 0;
        for (int i = 0; i < tmp.length; i++) {
                for (int j = 0; j < tmp[0].length; j++) {
                        deathFrames[index++] = tmp[i][j];
                }
        }
	    death.animation = new Animation(1f/deathFrames.length, deathFrames);
	    death.ox = -20;
	    death.oy = 0;
	    return death;
	}



}
