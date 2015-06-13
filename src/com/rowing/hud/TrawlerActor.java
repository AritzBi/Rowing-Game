package com.rowing.hud;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Input.Buttons;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.rowing.core.Constants;
import com.rowing.pojo.Equipo;

public class TrawlerActor extends Actor implements InputProcessor{
	private Texture trawler;
	private Texture slot;
	private Equipo equipo;
	private Stage stage;
	private int focusedSlot;
	public static int OFFSET_X=100;
	public static int OFFSET_Y=100;
	public TrawlerActor(Equipo equipo, Stage stage) {
		this.trawler = new Texture(Gdx.files.internal("resources/trainera.png"));
		this.equipo=equipo;
		this.stage=stage;
		slot = new Texture(Gdx.files.internal("resources/slot.png"));
		focusedSlot = 0;
	}
	
	public void draw(SpriteBatch batch, float partenAlpha) {
		/**System.out.println("Mi y es: "+getY());
		System.out.println("La altura de la imagen es: " + trawler.getHeight());
		System.out.println("My X es: "+getX());
		System.out.println("La anchura de la imagen es: "+trawler.getWidth());
		System.out.println("La anchura de la imagen entre 7 es: "+ trawler.getWidth()/7);**/
		batch.draw(trawler, getX(), getY());
		float posX=getX();
		for (int i = 0; i<7; i++){
			if (i == focusedSlot){
				batch.draw(slot, posX, getY()+trawler.getHeight(), 70, 70);
				batch.draw(slot, posX, getY()+trawler.getHeight(), 70, 70);	
			}
			else
				batch.draw(slot, posX, getY()+trawler.getHeight(), 64, 64);
			posX+=trawler.getWidth()/7;
		}
		posX=getX();
		for (int i = 0;i < equipo.getTrainera().getRemeros().size() && i<7; i++){
			if (i == focusedSlot)
				batch.draw(equipo.getTrainera().getRemeros().get(i).getIcon(),posX,getY()+trawler.getHeight()+15, 61,51);
			else
				batch.draw(equipo.getTrainera().getRemeros().get(i).getIcon(),posX,getY()+trawler.getHeight()+15, 55,45);
			posX+=trawler.getWidth()/7;
		}
		posX=getX();
		for(int i= 7 ; i < Constants.NUM_ROWERS ; i++){
			if (i == focusedSlot){
				batch.draw(slot, posX, trawler.getHeight()-getY(), 70, 70);
				batch.draw(slot, posX, trawler.getHeight()-getY(), 70, 70);
			}
			else
				batch.draw(slot, posX,trawler.getHeight()-getY(), 64, 64);
			posX+=trawler.getWidth()/7;
		}
		posX=getX();
		for(int i= 7 ; i < equipo.getTrainera().getRemeros().size() && i < Constants.NUM_ROWERS ; i++){
			if (focusedSlot == i)
				batch.draw(equipo.getTrainera().getRemeros().get(i).getIcon(),posX+5,trawler.getHeight()-getY()+15, 61,51);
			else
				batch.draw(equipo.getTrainera().getRemeros().get(i).getIcon(),posX+5,trawler.getHeight()-getY()+15, 55,45);
			posX+=trawler.getWidth()/7;
		}
		if (focusedSlot == Constants.NUM_ROWERS){
			batch.draw(slot, getX()-Constants.SIZE_X, getY() + Constants.SIZE_Y/2, 70, 70);
			batch.draw(slot, getX()-Constants.SIZE_X, getY() + Constants.SIZE_Y/2, 70, 70);
			if(equipo.getTrainera().getPatron()!= null){
				batch.draw(equipo.getTrainera().getPatron().getIcon(),getX()-Constants.SIZE_X+5,getY() + Constants.SIZE_Y/2+15, 61,51);
			}
		}else{
			batch.draw(slot, getX()-Constants.SIZE_X, getY() + Constants.SIZE_Y/2, 64, 64);
			if(equipo.getTrainera().getPatron()!= null){
				batch.draw(equipo.getTrainera().getPatron().getIcon(),getX()-Constants.SIZE_X+5,getY() + Constants.SIZE_Y/2+15, 55,45);
			}
		}

	}

	@Override
	public boolean keyDown(int arg0) {

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
	public boolean mouseMoved(int screenX, int screenY) {
		Vector2 pos = stage.screenToStageCoordinates(new Vector2(screenX, screenY));
		if (pos.x > getX() && pos.x < trawler.getWidth()+getX() && pos.y < (getY()+trawler.getHeight()+Constants.SIZE_Y) && pos.y>(getY()-Constants.SIZE_Y)) {
			float posX=getX();
			for (int i = 0; i<7; i++){
				if(pos.x>posX && pos.x<posX+Constants.SIZE_X && pos.y >getY()+trawler.getHeight() && pos.y < getY()+trawler.getHeight()+Constants.SIZE_Y){
					focusedSlot=i;
					return false;
				}
				posX+=trawler.getWidth()/7;
			}
			posX=getX();
			for(int i= 7 ; i < Constants.NUM_ROWERS ; i++){
				if(pos.x>posX && pos.x<posX+Constants.SIZE_X && pos.y >getY()-Constants.SIZE_Y && pos.y<getY()){
					focusedSlot=i;
					return false;
				}
				posX+=trawler.getWidth()/7;
			}
			//getX()-Constants.SIZE_X, getY() + Constants.SIZE_Y/2
		}else if(pos.x < getX()-Constants.SIZE_X+Constants.SIZE_X && pos.x >getX()-Constants.SIZE_X && pos.y <  getY() + Constants.SIZE_Y/2 +Constants.SIZE_Y&& pos.y > getY() + Constants.SIZE_Y/2 ){
			focusedSlot=Constants.NUM_ROWERS;
		}else{
			focusedSlot=-1;
		}
		return false;
	}

	@Override
	public boolean scrolled(int arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		Vector2 pos = stage.screenToStageCoordinates(new Vector2(screenX, screenY));

		if (Gdx.input.isButtonPressed(Buttons.LEFT)) {
			if (pos.x > getX() && pos.x < trawler.getWidth()+getX() && pos.y < (getY()+trawler.getHeight()+Constants.SIZE_Y) && pos.y>(getY()-Constants.SIZE_Y)) {
				float posX=getX();
				for (int i = 0; i<7; i++){
					if(pos.x>posX && pos.x<posX+Constants.SIZE_X && pos.y >getY()+trawler.getHeight() && pos.y < getY()+trawler.getHeight()+Constants.SIZE_Y){
						//focusedSlot=i;
						removeRowerFromTrawler(i);
						return false;
					}
					posX+=trawler.getWidth()/7;
				}
				posX=getX();
				for(int i= 7 ; i < Constants.NUM_ROWERS ; i++){
					if(pos.x>posX && pos.x<posX+Constants.SIZE_X && pos.y >getY()-Constants.SIZE_Y && pos.y<getY()){
						//focusedSlot=i;
						removeRowerFromTrawler(i);
						return false;
					}
					posX+=trawler.getWidth()/7;
				}
			} 
		}
		return false;
	}
	public void removeRowerFromTrawler(int position){
		this.equipo.getTrainera().getRemeros().remove(position);
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
