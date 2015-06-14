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
import com.rowing.core.Constants;
import com.rowing.core.Rowing;
import com.rowing.pojo.Equipo;
import com.rowing.screens.ClimateScreen;
import com.rowing.screens.StrategySelectionScreen;

public class TrawlerSelectionButton extends Table implements InputProcessor{
	private Table table;
	private TextButton goToTheRaceButton;
	private TextButton showClimate;
	private TextButton [] buttons;
	private int focusedButton;
	private TextButtonStyle normalStyle;
	private TextButtonStyle focusedStyle;
	private Rowing game;
	private Skin skin;
	private Equipo equipo;
	public TrawlerSelectionButton(final Rowing game,Skin skin,final Equipo equipo) {
		this.game=game;
		table=this;
		this.equipo=equipo;
		this.focusedButton=1;
		this.skin=skin;
		normalStyle=new TextButtonStyle();
		normalStyle.font=skin.getFont("buttonFont");
		normalStyle.up=skin.getDrawable("normal-button");
		normalStyle.down=skin.getDrawable("pushed-button");
		focusedStyle=new TextButtonStyle();
		focusedStyle.font=skin.getFont("buttonFont");
		focusedStyle.up=skin.getDrawable("focused-button");
		focusedStyle.down=skin.getDrawable("pushed-button");
		goToTheRaceButton = new TextButton( "Go to the Race!", focusedStyle);
		showClimate = new TextButton( "Show climate", normalStyle);
		buttons=new TextButton[2];
		buttons[0]=goToTheRaceButton;
		buttons[1]=showClimate;
		focusedButton=1;
		goToTheRaceButton.addListener( new InputListener() {
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
            		Rowing.game.clearProcessors();
	                Rowing.game.setScreen(new StrategySelectionScreen(game,equipo,Constants.ESTRATEGIAS_SALIDA));;
            	}

            }

        } );
		goToTheRaceButton.addListener(new ClickListener(){
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
		table.add( goToTheRaceButton ).size( 300, 60 ).uniform().spaceBottom( 10 );
	    table.row();
	    showClimate.addListener( new InputListener() {
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
            		Rowing.game.clearProcessors();
	                Rowing.game.setScreen(new ClimateScreen(game,equipo));
            	}

            }

        } );
		showClimate.addListener(new ClickListener(){
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
	    table.add( showClimate ).uniform().fill().spaceBottom( 10 );
	}

	@Override
	public boolean keyDown(int keycode) {
		/*if(keycode == Input.Keys.ENTER){
			if(focusedButton==1){
        		Rowing.game.clearProcessors();
                Rowing.game.setScreen(new StrategySelectionScreen(game,equipo,Constants.ESTRATEGIAS_SALIDA));;
			}else if(focusedButton==2){
        		Rowing.game.clearProcessors();
                Rowing.game.setScreen(new ClimateScreen(game,equipo));
			}
			return true;
		}
		if(keycode==Input.Keys.UP){
			buttons[focusedButton-1].setStyle(normalStyle);
			if(focusedButton==1)
				focusedButton=2;
			else
				focusedButton--;
			buttons[focusedButton-1].setStyle(focusedStyle);
			return true;
		}else{
			if(keycode==Input.Keys.DOWN){
				buttons[focusedButton-1].setStyle(normalStyle);
				if(focusedButton>=2)
					focusedButton=1;
				else
					focusedButton++;
				buttons[focusedButton-1].setStyle(focusedStyle);
				return true;
			}
		}	*/
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
