package com.rowing.hud;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.rowing.core.Constants;
import com.rowing.core.Rowing;
import com.rowing.screens.RegattaScreen;
import com.rowing.screens.StrategySelectionScreen;

public class GoToStrategySelection extends Table implements InputProcessor {
	private Table table;
	private TextButton goToTheRaceButton;
	private RegattaScreen screen;
	private int focusedButton;
	private TextButtonStyle normalStyle;
	private TextButtonStyle focusedStyle;
	
	
	public GoToStrategySelection(RegattaScreen screen){
		this.screen=screen;
		normalStyle=new TextButtonStyle();
		normalStyle.font=screen.getSkin().getFont("buttonFont");
		normalStyle.up=screen.getSkin().getDrawable("normal-button");
		normalStyle.down=screen.getSkin().getDrawable("pushed-button");
		focusedStyle=new TextButtonStyle();
		focusedStyle.font=screen.getSkin().getFont("buttonFont");
		focusedStyle.up=screen.getSkin().getDrawable("focused-button");
		focusedStyle.down=screen.getSkin().getDrawable("pushed-button");
		goToTheRaceButton = new TextButton( "Press Enter to Continue", focusedStyle);
	}
	@Override
	public boolean keyDown(int keycode) {
		if(keycode == Input.Keys.ENTER){
			Rowing.game.clearProcessors();
            //Rowing.game.setScreen(new StrategySelectionScreen(screen.getEquipo(),Constants.ESTRATEGIAS_SALIDA));;
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
