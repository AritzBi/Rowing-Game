package com.rowing.screens;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.rowing.core.Rowing;


public class GameMenu extends Table implements InputProcessor {
	private Table table;
	private TextButton resumeGameButton;
	private TextButton mainMenuButton;
	private TextButton exitButton;
	private TextButton [] buttons;
	private Skin skin;
	private int focusedButton;
	private TextButtonStyle normalStyle;
	private TextButtonStyle focusedStyle;
	private int height;
	private Rowing game;
	public GameMenu(Skin skin,Rowing game){
		this.game=game;
		table=this;
		this.skin = skin;
		this.height=900;
		this.focusedButton=1;
		normalStyle=new TextButtonStyle();
		normalStyle.font=skin.getFont("buttonFont");
		normalStyle.up=skin.getDrawable("normal-button");
		normalStyle.down=skin.getDrawable("pushed-button");
		focusedStyle=new TextButtonStyle();
		focusedStyle.font=skin.getFont("buttonFont");
		focusedStyle.up=skin.getDrawable("focused-button");
		focusedStyle.down=skin.getDrawable("pushed-button");
		resumeGameButton = new TextButton( "Resume Game", focusedStyle);
		mainMenuButton = new TextButton( "Main Menu", normalStyle);
		exitButton = new TextButton( "Exit", normalStyle );
		buttons=new TextButton[5];
		buttons[0]=resumeGameButton;
		buttons[1]=mainMenuButton;
		buttons[2]=exitButton;
		focusedButton=1;
	    table.add( resumeGameButton ).size( 300, 60 ).uniform().spaceBottom( 10 );
	    table.row();
	    table.add( mainMenuButton ).uniform().fill().spaceBottom( 10 );
	    table.row();
	    table.add( exitButton ).uniform().fill().spaceBottom(10);
	}
	
	public void setViewport(int height){
		this.height=height;
	}
	
	@Override
	public boolean keyDown(int keycode) {
		if(keycode==Input.Keys.ESCAPE){
			//SoC.game.world.getSystem(HudSystem.class).toogleGameMenu();
			return true;
		}
		if(keycode == Input.Keys.ENTER){
			if(focusedButton==1){
				//SoC.game.world.getSystem(HudSystem.class).toogleGameMenu();
			}else if(focusedButton==2){
				game.clearProcessors();
				game.setScreen(new MenuScreen(game));
			}else if(focusedButton==3){
				System.exit(0);
			}
			return true;
		}
		if(keycode==Input.Keys.UP){
			buttons[focusedButton-1].setStyle(normalStyle);
			if(focusedButton==1)
				focusedButton=3;
			else
				focusedButton--;
			buttons[focusedButton-1].setStyle(focusedStyle);
			return true;
		}else{
			if(keycode==Input.Keys.DOWN){
				buttons[focusedButton-1].setStyle(normalStyle);
				if(focusedButton==3)
					focusedButton=1;
				else
					focusedButton++;
				buttons[focusedButton-1].setStyle(focusedStyle);
				return true;
			}
		}	
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		if(getX()-150<screenX && getX()+150>screenX && (getY()+75)<height-screenY && (getY()+135)>height-screenY){
			//SoC.game.world.getSystem(HudSystem.class).toogleGameMenu();
			return true;
		}else if(getX()-150<screenX && getX()+150>screenX && (getY()+5)<height-screenY && (getY()+65)>height-screenY){
			game.clearProcessors();
			game.setScreen(new MenuScreen(game));
			return true;
		}else if(getX()-150<screenX && getX()+150>screenX && (getY()-5)>height-screenY && (getY()-65)<height-screenY){
			System.exit(0	);
			return true;
		}
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		if(getX()-150<screenX && getX()+150>screenX && (getY()+75)<height-screenY && (getY()+135)>height-screenY){
			buttons[focusedButton-1].setStyle(normalStyle);
			focusedButton=1;
			buttons[focusedButton-1].setStyle(focusedStyle);
			return true;
		}else if(getX()-150<screenX && getX()+150>screenX && (getY()+5)<height-screenY && (getY()+65)>height-screenY){
			buttons[focusedButton-1].setStyle(normalStyle);
			focusedButton=2;
			buttons[focusedButton-1].setStyle(focusedStyle);
			return true;
		}else if(getX()-150<screenX && getX()+150>screenX && (getY()-5)>height-screenY && (getY()-65)<height-screenY){
			buttons[focusedButton-1].setStyle(normalStyle);
			focusedButton=3;
			buttons[focusedButton-1].setStyle(focusedStyle);
			return true;
		}
		
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		// TODO Auto-generated method stub
		return false;
	}

}
