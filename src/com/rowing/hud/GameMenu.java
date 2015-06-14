package com.rowing.hud;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.rowing.core.Rowing;
import com.rowing.screens.AbstractScreen;
import com.rowing.screens.MenuScreen;
import com.rowing.screens.TeamSelectionScreen;
import com.rowing.utils.Utils;


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
	private AbstractScreen parent;
	public GameMenu(Skin skin,final Rowing game,AbstractScreen parent){
		this.game=game;
		table=this;
		this.skin = skin;
		this.height=900;
		this.focusedButton=1;
		this.parent=parent;
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
		buttons=new TextButton[3];
		buttons[0]=resumeGameButton;
		buttons[1]=mainMenuButton;
		buttons[2]=exitButton;
		focusedButton=1;
		resumeGameButton.addListener( new InputListener() {
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) 
            {
                return true;
            }
            @Override
            public void touchUp(
                InputEvent event,
                float x,
                float y,
                int pointer,
                int button )
            {
            	if(button==0){
            		System.out.println("asda");
            	}

            }

        } );
		resumeGameButton.addListener(new ClickListener(){
        	public boolean mouseMoved(InputEvent event,
                    float x,
                    float y){
    			focusedButton=1;
        		buttons[focusedButton-1].setStyle(focusedStyle);
        		for (int i=0;i<buttons.length;i++){
        			if (i!=focusedButton-1){
        				buttons[i].setStyle(normalStyle);
        			}
        		}
        		return true;
        		
        	}

        });
	    table.add( resumeGameButton ).size( 300, 60 ).uniform().spaceBottom( 10 );
	    table.row();
	    mainMenuButton.addListener( new InputListener() {
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) 
            {
                return true;
            }
            @Override
            public void touchUp(
                InputEvent event,
                float x,
                float y,
                int pointer,
                int button )
            {
            	if(button==0){
    				game.clearProcessors();
    				game.setScreen(new MenuScreen(game));
            	}

            }

        } );
		mainMenuButton.addListener(new ClickListener(){
        	public boolean mouseMoved(InputEvent event,
                    float x,
                    float y){
        			focusedButton=2;
            		buttons[focusedButton-1].setStyle(focusedStyle);
            		for (int i=0;i<buttons.length;i++){
            			if (i!=focusedButton-1){
            				buttons[i].setStyle(normalStyle);
            			}
            		}
        		return true;
        		
        	}

        });
	    table.add( mainMenuButton ).uniform().fill().spaceBottom( 10 );
	    table.row();
	    exitButton.addListener( new InputListener() {
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) 
            {
                return true;
            }
            @Override
            public void touchUp(
                InputEvent event,
                float x,
                float y,
                int pointer,
                int button )
            {
            	if(button==0){
            		System.exit(0);
            	}

            }

        } );
	    exitButton.addListener(new ClickListener(){
        	public boolean mouseMoved(InputEvent event,
                    float x,
                    float y){
    			focusedButton=3;
        		buttons[focusedButton-1].setStyle(focusedStyle);
        		for (int i=0;i<buttons.length;i++){
        			if (i!=focusedButton-1){
        				buttons[i].setStyle(normalStyle);
        			}
        		}
        		return true;
        		
        	}

        });
	    table.add( exitButton ).uniform().fill().spaceBottom(10);
	}
	public void setViewport(int height){
		this.height=height;
	}
	
	@Override
	public boolean keyDown(int keycode) {
		if(keycode == Input.Keys.ESCAPE)
			parent.toogleGameMenu();
		if(keycode == Input.Keys.ENTER){
			if(focusedButton==1){
				parent.toogleGameMenu();
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
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {		
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		// TODO Auto-generated method stub
		return false;
	}

}
