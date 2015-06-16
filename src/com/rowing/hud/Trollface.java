package com.rowing.hud;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.rowing.core.Rowing;
import com.rowing.screens.CreditsScreen;

public class Trollface extends Actor implements InputProcessor {
	private Texture trollface;
	
	public Trollface(){
		trollface=new Texture(Gdx.files.internal("resources/trollface.png"));
	}
	public void draw (SpriteBatch batch, float parentAlpha) {
		batch.draw(trollface,getX(),getY(),500,500);
	}
	@Override
	public boolean keyDown(int keycode) {
		if(keycode == Input.Keys.ENTER){
			Rowing.game.clearProcessors();
			Rowing.game.setScreen(new CreditsScreen());
			return true;
		}
		return false;
	}

	@Override
	public boolean keyTyped(char arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyUp(int arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean mouseMoved(int arg0, int arg1) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean scrolled(int arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDown(int arg0, int arg1, int arg2, int arg3) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDragged(int arg0, int arg1, int arg2) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchUp(int arg0, int arg1, int arg2, int arg3) {
		// TODO Auto-generated method stub
		return false;
	}

}
