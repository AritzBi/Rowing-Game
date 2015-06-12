package com.rowing.hud;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.rowing.pojo.Equipo;

public class TrawlerActor extends Actor implements InputProcessor{
	private Texture trawler;
	private Equipo equipo;
	private Stage stage;
	private static int OFFSET_X=100;
	private static int OFFSET_Y=100;
	public TrawlerActor(Equipo equipo, Stage stage) {
		this.trawler = new Texture(Gdx.files.internal("resources/trainera.png"));
		this.equipo=equipo;
		this.stage=stage;
	}
	
	public void draw(SpriteBatch batch, float partenAlpha) {
		batch.draw(trawler, getX()+OFFSET_X, getY()+OFFSET_Y);
	}

	@Override
	public boolean keyDown(int arg0) {
		// TODO Auto-generated method stub
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
