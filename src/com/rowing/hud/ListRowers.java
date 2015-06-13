package com.rowing.hud;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Buttons;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.rowing.core.Constants;
import com.rowing.pojo.Athlete;
import com.rowing.pojo.Equipo;
import com.rowing.pojo.Patron;
import com.rowing.pojo.Remero;





public class ListRowers extends Actor implements InputProcessor  {
	private Texture slot;
	private int focusedSlot;
	private Equipo equipo;
	private Stage stage;
	public static int ROWERS_PER_ROW=3;
	private int rower_num_rows;
	private TooltipBox tooltip;
	private ArrayList<Athlete>athletes;
	public BitmapFont font;
	public ListRowers(Equipo equipo, Stage stage, TooltipBox tooltip) {
		slot = new Texture(Gdx.files.internal("resources/slot.png"));
		focusedSlot = -1;
		this.equipo=equipo;
		this.stage=stage;
		this.tooltip=tooltip;
		font = new BitmapFont();
		athletes = new ArrayList<Athlete>();
		athletes.addAll(equipo.getRemeros());
		athletes.addAll(equipo.getPatrones());
		rower_num_rows =athletes.size()/ROWERS_PER_ROW;
		if (athletes.size()%ROWERS_PER_ROW > 0)
			rower_num_rows++;
		
	}
	public void draw(SpriteBatch batch, float partenAlpha) {
		/*System.out.println("Screen X: "+stage.getWidth());
		System.out.println("Screen Y: "+stage.getHeight());*/
		float posX = stage.getWidth() - ROWERS_PER_ROW*Constants.SIZE_X;
		float posY = stage.getHeight() - Constants.SIZE_Y;
		float posFocusX = 0;
		float posFocusY = 0;
		boolean existsFocus = false;
		Athlete remeroFocused = null;
		for (int i = 0; i < athletes.size() ; i++){
			existsFocus = false;
			if (i == focusedSlot) {
				posFocusX = posX;
				posFocusY = posY;
				existsFocus = true;
			} else {
				batch.draw(slot, posX, posY, 64, 64);
			}
			Athlete remero =athletes.get(i);
			if (i == focusedSlot) {
				remeroFocused = remero;
			} else{
				batch.draw(remero.getIcon(), posX + 5, posY + 15, 55, 45);
				if (remero instanceof Remero)
					font.draw(batch, "Trawler", posX + slot.getWidth()*0.10f, posY+ slot.getHeight()*0.2f);
				else
					font.draw(batch, "Captain", posX + slot.getWidth()*0.10f, posY+ slot.getHeight()*0.2f);
			}
			posX += Constants.SIZE_X;
			if ((i - 1) % 3 == 0) {
				posY -= Constants.SIZE_Y;
				posX = stage.getWidth() - ROWERS_PER_ROW*Constants.SIZE_X;
			}
			if (existsFocus) {
				batch.draw(slot, posFocusX, posFocusY, 70, 70);
				batch.draw(slot, posFocusX, posFocusY, 70, 70);
				batch.draw(remeroFocused.getIcon(), posFocusX + 5, posFocusY + 15, 61,51);
				if (remero instanceof Remero)
					font.draw(batch, "Trawler", posFocusX + slot.getWidth()*0.10f +5, posFocusY+ slot.getHeight()*0.2f);
				else
					font.draw(batch, "Captain", posFocusX + slot.getWidth()*0.10f +5, posFocusY+ slot.getHeight()*0.2f);
				remero = athletes.get(focusedSlot) ;
				if (remero != null) {
					tooltip.setText(remero.toString(), 0f);
				} else {
					tooltip.setText(null, 0);
				}
			}
		}
	}


	@Override
	public boolean keyDown(int arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyTyped(char arg0) {

		if (Gdx.input.isKeyPressed(Keys.TAB)) {
			if (focusedSlot == athletes.size()-1) {
				focusedSlot = 0;
			} else {
				focusedSlot++;
			}
		} else if (Gdx.input.isKeyPressed(Keys.ENTER)) {
			this.addRowerToTrawler(focusedSlot);
		} 
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
		if (pos.x > stage.getWidth() - ROWERS_PER_ROW*Constants.SIZE_X && pos.x < stage.getWidth() && pos.y > stage.getHeight()-rower_num_rows*Constants.SIZE_Y
				&& pos.y < stage.getHeight()) {
			float posX = stage.getWidth() - ROWERS_PER_ROW*Constants.SIZE_X;
			float posY = stage.getHeight() - Constants.SIZE_Y;
			for (int i = 0;i < athletes.size();i++) {
				if (pos.x > posX && pos.x < posX + Constants.SIZE_X && pos.y > posY
						&& pos.y < posY + Constants.SIZE_Y) {
					focusedSlot = i;
					return false;
				}
				posX += Constants.SIZE_X;
				if ((i - 1) % 3 == 0) {
					posY -= Constants.SIZE_Y;
					posX = stage.getWidth() - ROWERS_PER_ROW*Constants.SIZE_X;
				}
			}
		} else{
			focusedSlot=-1;
			tooltip.setText(null, 0);
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
			if (pos.x > stage.getWidth() - ROWERS_PER_ROW*Constants.SIZE_X && pos.x < stage.getWidth() && pos.y > stage.getHeight()-rower_num_rows*Constants.SIZE_Y
					&& pos.y < stage.getHeight()) {
				float posX = stage.getWidth() - ROWERS_PER_ROW*Constants.SIZE_X;
				float posY = stage.getHeight() - Constants.SIZE_Y;
				for (int i = 0;i < athletes.size();i++) {
					if (pos.x > posX && pos.x < posX + Constants.SIZE_X && pos.y > posY
							&& pos.y < posY + Constants.SIZE_Y) {
						this.addRowerToTrawler(i);
						return false;
					}
					posX += Constants.SIZE_X;
					if ((i - 1) % 3 == 0) {
						posY -= Constants.SIZE_Y;
						posX = stage.getWidth() - ROWERS_PER_ROW*Constants.SIZE_X;
					}
				}
			} 	
		}
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
	
	public void addRowerToTrawler(int i){
		Athlete athlete = this.athletes.get(i);
		if (athlete instanceof Remero){
			if(!this.equipo.getTrainera().getRemeros().contains(athlete) && this.equipo.getTrainera().getRemeros().size()<Constants.NUM_ROWERS)
				this.equipo.getTrainera().getRemeros().add((Remero) athlete);
			else
				System.out.println("The trawler is full or the rower is already in the trawler");
		}else{
			i = i-this.equipo.getRemeros().size();
			Patron patron=this.equipo.getPatrones().get(i);
			if (equipo.trainera.getPatron()==null){
				equipo.trainera.setPatron(patron);
			}else{
				if(equipo.trainera.getPatron()==patron)
					System.out.println("The captain is already in the trawler");
				else
					equipo.trainera.setPatron(patron);
			}
		}

	}

}
