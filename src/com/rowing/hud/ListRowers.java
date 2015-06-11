package com.rowing.hud;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.rowing.pojo.Equipo;
import com.rowing.pojo.Remero;




public class ListRowers extends Actor implements InputProcessor  {
	public Texture slot;
	public Texture focusSlot;
	public int width;
	public int height;
	public int focusedSlot;
	private Equipo equipo;
	private Stage stage;
	
	public ListRowers(Equipo equipo, Stage stage) {
		slot = new Texture(Gdx.files.internal("resources/slot.png"));
		focusSlot = new Texture(Gdx.files.internal("resources/slot-weapon.png"));
		this.width = 1280;
		this.height = 900;
		focusedSlot = 1;
		this.equipo=equipo;
		this.stage=stage;
		
	}
	public void draw(SpriteBatch batch, float partenAlpha) {
		System.out.println("Screen X: "+stage.getWidth());
		System.out.println("Screen Y: "+stage.getHeight());
		
		float posX = getX() + 256;
		float posY = getY();
		float posFocusX = 0;
		float posFocusY = 0;
		boolean existsFocus = false;
		Remero remeroFocused = null;
		int j=0;
		for (int i = equipo.getRemeros().size(); i > 0; i--) {
			/**j++;
			if (j == 5)
				break;**/
			if (i == focusedSlot) {
				posFocusX = posX;
				posFocusY = posY;
				existsFocus = true;
			} else {
				batch.draw(slot, posX, posY, 64, 64);
			}
			Remero remero = equipo.getRemeros().get(i-1);
			if (i == focusedSlot) {
				remeroFocused = remero;
			} else
				batch.draw(remero.getIcon(), posX + 5, posY + 15, 55, 45);
			posX -= 64;
			if ((i - 1) % 3 == 0) {
				posY += 64;
				posX = getX() + 256;
			}
		}
		if (existsFocus) {
			batch.draw(slot, posFocusX, posFocusY, 70, 70);
			batch.draw(remeroFocused.getIcon(), posFocusX + 5, posFocusY + 15, 61,51);
		}
	}
	public void updateRes(int witdh, int height) {
		this.width = witdh;
		this.height = height;
	}

	@Override
	public boolean keyDown(int arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyTyped(char arg0) {

		if (Gdx.input.isKeyPressed(Keys.TAB)) {
			if (focusedSlot == equipo.getRemeros().size()) {
				focusedSlot = 1;
			} else {
				focusedSlot++;
			}
		} else if (Gdx.input.isKeyPressed(Keys.ENTER)) {
			//ToTrainera
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
		if (pos.x > getX() && pos.x < getX() + 320 && pos.y > getY()
				&& pos.y < getY()+256) {
			float posX = getX() + 256;
			float posY = getY();
			for (int i = equipo.getRemeros().size(); i > 0; i--) {
				if (pos.x > posX && pos.x < posX + 64 && pos.y > posY
						&& pos.y < posY + 64) {
					focusedSlot = i;
					/*Item item = SoC.game.playermapper.get(SoC.game.player).inventary[i - 1];
					if (item != null) {
						parent.tooltip.setText(item.tooltip, 0f);
					} else {
						parent.tooltip.setText(null, 0);
					}*/
					return false;
				}
				posX -= 64;
				if ((i - 1) % 5 == 0) {
					posY += 64;
					posX = getX() + 256;
				}
			}
		} 
		//else {
			//focusedSlot = 1;
			//parent.tooltip.setText(null, 0);

		//}
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
