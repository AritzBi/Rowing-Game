package com.rowing.core;

import java.util.Stack;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.badlogic.gdx.utils.Array;
import com.rowing.screens.MenuScreen;
import com.rowing.utils.MusicPlayer;

public class Rowing extends Game {
	public static final int FRAME_WIDTH = 1440;
	public static final int FRAME_HEIGHT = 900;
	public static Rowing game;
	
	//public HudSystem hudSystem;

	public InputMultiplexer inputMultiplexer;
	
	public boolean pause;
	
	public Stack<Screen> screens;
	public Stack<InputProcessor> processors;
		

	@Override
	public void create() {
		game=this;
		inputMultiplexer=new InputMultiplexer();
		Gdx.input.setInputProcessor(inputMultiplexer);
		//Gdx.graphics.setDisplayMode(Gdx.graphics.getDesktopDisplayMode().width, Gdx.graphics.getDesktopDisplayMode().height, true);
		Gdx.graphics.setDisplayMode(1280, 720, false);
		screens=new Stack<Screen>();
		processors = new Stack<InputProcessor>();
		MusicPlayer.initialize();
			
		//Save, New, Load game handler.
		setScreen(new MenuScreen(this));
		
	}
	public static void main(String[] args) {
		LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
		cfg.width = FRAME_WIDTH;
		cfg.height = FRAME_HEIGHT;
		cfg.useGL20 = true;
		cfg.resizable = false;
		cfg.fullscreen = false;
		cfg.title = "Aupa Orio!";
		new LwjglApplication(new Rowing(), cfg);
	}

	public void restoreInputProcessors() {
		inputMultiplexer.clear();
		while(!processors.isEmpty()){
			inputMultiplexer.addProcessor(processors.pop());
		}
	}
	
	public void archiveProcessors(){
		Array<InputProcessor> procs = inputMultiplexer.getProcessors();
		for(int i = 0; i < procs.size; i++){
			processors.push(procs.get(i));
		}
		inputMultiplexer.clear();
	}
	
	public void clearProcessors(){
		processors.empty();
		inputMultiplexer.clear();
	}
	
	public void clearScreens(){
		screens.empty();
	}
}
