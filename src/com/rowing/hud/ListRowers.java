package com.rowing.hud;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Input.Buttons;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.rowing.core.Constants;
import com.rowing.pojo.Equipo;
import com.rowing.pojo.Remero;





public class ListRowers extends Actor implements InputProcessor  {
	private Texture slot;
	private int focusedSlot;
	private Equipo equipo;
	private Stage stage;
	public static int ROWERS_PER_ROW=3;
	private int rower_num_rows;
	private TooltipBox tooltip;
	public ListRowers(Equipo equipo, Stage stage, TooltipBox tooltip) {
		slot = new Texture(Gdx.files.internal("resources/slot.png"));
		focusedSlot = 1;
		this.equipo=equipo;
		this.stage=stage;
		this.tooltip=tooltip;
		rower_num_rows = equipo.getRemeros().size()/ROWERS_PER_ROW;
		if (equipo.getRemeros().size()%ROWERS_PER_ROW > 0)
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
		Remero remeroFocused = null;
		//int j=0;
		for (int i = 0; i < equipo.getRemeros().size() ; i++){
			if (i == focusedSlot) {
				posFocusX = posX;
				posFocusY = posY;
				existsFocus = true;
			} else {
				batch.draw(slot, posX, posY, 64, 64);
			}
			Remero remero = equipo.getRemeros().get(i);
			if (i == focusedSlot) {
				remeroFocused = remero;
			} else
				batch.draw(remero.getIcon(), posX + 5, posY + 15, 55, 45);
			posX += Constants.SIZE_X;
			if ((i - 1) % 3 == 0) {
				posY -= Constants.SIZE_Y;
				posX = stage.getWidth() - ROWERS_PER_ROW*Constants.SIZE_X;
			}
			if (existsFocus) {
				batch.draw(slot, posFocusX, posFocusY, 70, 70);
				batch.draw(remeroFocused.getIcon(), posFocusX + 5, posFocusY + 15, 61,51);
			}
			remero = equipo.getRemeros().get(focusedSlot) ;
			if (remero != null) {
				tooltip.setText(remero.toString(), 0f);
			} else {
				tooltip.setText(null, 0);
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
			if (focusedSlot == equipo.getRemeros().size()-1) {
				focusedSlot = 0;
			} else {
				focusedSlot++;
			}
		} else if (Gdx.input.isKeyPressed(Keys.ENTER)) {
			System.out.println("Focused Slot: "+focusedSlot);
			System.out.println("Datos del remero: "+equipo.getRemeros().get(focusedSlot));
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
		/*System.out.println("Pos mouse X "+pos.x);
		System.out.println("Pos mouse Y "+pos.y);
		System.out.println(getX());
		System.out.println(getY());
		System.out.println("Vector X: "+pos.x);
		System.out.println("Izquierda de x"+ (stage.getWidth() - ROWERS_PER_ROW*SIZE_X ));
		System.out.println("Derecha de X: "+ stage.getWidth());
		System.out.println("Vector Y:"+pos.y);
		System.out.println("Arriba de Y:" +stage.getHeight() );
		System.out.println("Abajo de Y:"+(stage.getHeight()-rower_num_rows*SIZE_Y));*/
		if (pos.x > stage.getWidth() - ROWERS_PER_ROW*Constants.SIZE_X && pos.x < stage.getWidth() && pos.y > stage.getHeight()-rower_num_rows*Constants.SIZE_Y
				&& pos.y < stage.getHeight()) {
			float posX = stage.getWidth() - ROWERS_PER_ROW*Constants.SIZE_X;
			float posY = stage.getHeight() - Constants.SIZE_Y;
			for (int i = 0;i < equipo.getRemeros().size();i++) {
				if (pos.x > posX && pos.x < posX + Constants.SIZE_X && pos.y > posY
						&& pos.y < posY + Constants.SIZE_Y) {
					focusedSlot = i;
					/**Remero remero = equipo.getRemeros().get(i) ;
					if (remero != null) {
						tooltip.setText(remero.toString(), 0f);
					} else {
						tooltip.setText(null, 0);
					}**/
					return false;
				}
				posX += Constants.SIZE_X;
				if ((i - 1) % 3 == 0) {
					posY -= Constants.SIZE_Y;
					posX = stage.getWidth() - ROWERS_PER_ROW*Constants.SIZE_X;
				}
			}
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
				for (int i = 0;i < equipo.getRemeros().size();i++) {
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
		Remero remero = this.equipo.getRemeros().get(i);
		if(!this.equipo.getTrainera().getRemeros().contains(remero) && this.equipo.getTrainera().getRemeros().size()<Constants.NUM_ROWERS)
			this.equipo.getTrainera().getRemeros().add(remero);
		else
			System.out.println("The trawler is full or the rower is already in the trawler");
	}

}
