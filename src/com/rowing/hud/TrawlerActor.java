package com.rowing.hud;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.rowing.core.Constants;
import com.rowing.pojo.Equipo;

public class TrawlerActor extends Actor implements InputProcessor{
	private Texture trawler;
	private Texture slot;
	private Equipo equipo;
	private Stage stage;
	public static int OFFSET_X=100;
	public static int OFFSET_Y=100;
	public TrawlerActor(Equipo equipo, Stage stage) {
		this.trawler = new Texture(Gdx.files.internal("resources/trainera.png"));
		this.equipo=equipo;
		this.stage=stage;
		slot = new Texture(Gdx.files.internal("resources/slot.png"));
	}
	
	public void draw(SpriteBatch batch, float partenAlpha) {
		System.out.println("Mi y es: "+getY());
		System.out.println("La altura de la imagen es: " + trawler.getHeight());
		System.out.println("My X es: "+getX());
		System.out.println("La anchura de la imagen es: "+trawler.getWidth());
		System.out.println("La anchura de la imagen entre 7 es: "+ trawler.getWidth()/7);
		batch.draw(trawler, getX(), getY());
		float posX=getX();
		float posY=getY();
		for (int i = 0;i < equipo.getTrainera().getRemeros().size() && i<7; i++){
			batch.draw(slot, posX, getY()+trawler.getHeight(), 64, 64);
			batch.draw(equipo.getTrainera().getRemeros().get(i).getIcon(),posX,getY()+trawler.getHeight()+15, 61,51);
			posX+=trawler.getWidth()/7;
		}
		posX=getX();
		posY=getY();
		for(int i= 7 ; i < equipo.getTrainera().getRemeros().size() && i < Constants.NUM_ROWERS ; i++){
			batch.draw(slot, posX, trawler.getHeight()-getY(), 64, 64);
			batch.draw(equipo.getTrainera().getRemeros().get(i).getIcon(),posX,trawler.getHeight()-getY()+15, 61,51);
			posX+=trawler.getWidth()/7;
		}
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
