package com.rowing.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.rowing.core.Rowing;
import com.rowing.utils.MusicPlayer;
import com.rowing.utils.Utils;


public class MenuScreen extends AbstractScreen implements InputProcessor{
	private Texture background;
	private int focusedBotton;
	private TextButton startGameButton;
	private TextButton optionsButton;
	private TextButton exitButton;
	private TextButton []buttons;
	private TextButtonStyle normalStyle;
	private TextButtonStyle focusedStyle;
	public MenuScreen(Rowing game) {
		super(game);
		background=new Texture(Gdx.files.internal("resources/concha1.jpg"));
		normalStyle=new TextButtonStyle();
		normalStyle.font=getSkin().getFont("buttonFont");
		normalStyle.up=getSkin().getDrawable("normal-button");
		normalStyle.down=getSkin().getDrawable("pushed-button");
		focusedStyle=new TextButtonStyle();
		focusedStyle.font=getSkin().getFont("buttonFont");
		focusedStyle.up=getSkin().getDrawable("focused-button");
		focusedStyle.down=getSkin().getDrawable("pushed-button");
		startGameButton = new TextButton( "START GAME", normalStyle);
		optionsButton = new TextButton( "OPTIONS", normalStyle);
		exitButton = new TextButton( "EXIT", normalStyle );
		buttons=new TextButton[4];
		buttons[0]=startGameButton;
		buttons[1]=optionsButton;
		buttons[2]=exitButton;
		focusedBotton=1;
		Rowing.game.inputMultiplexer.addProcessor(this);
		if(game.getScreen()!=null){
			game.getScreen().dispose();
		}
		MusicPlayer.play("playa.mp3");
	}
    @Override
    public void show()
    {
        super.show();
        // retrieve the default table actor
        Table table = super.getTable();
        // register the button "start game"
        startGameButton.addListener( new InputListener() {
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
	                Rowing.game.setScreen(new TeamSelectionScreen(game,Utils.loadEquipoOrio()));
            	}

            }

        } );
        
        startGameButton.addListener(new ClickListener(){
        	public boolean mouseMoved(InputEvent event,
                    float x,
                    float y){
        		if(focusedBotton!=1){
        			buttons[focusedBotton-1].setStyle(normalStyle);
        		}
        		focusedBotton=1;
        		return true;
        		
        	}

        });
        table.add( startGameButton ).size( 300, 60 ).uniform().spaceBottom( 10 );
        table.row();

        

        // register the button "high scores"
        optionsButton.addListener( new InputListener() {
            @Override
            public void touchUp(
                InputEvent event,
                float x,
                float y,
                int pointer,
                int button )
            {
            	if (button==0){
	                Rowing.game.clearProcessors();
	                Rowing.game.setScreen(new OptionsScreen(game,true));
	                //Rowing.game.setScreen(new CreditsScreen());
            	}
            }
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) 
            {
                return true;
            }
        } );
        
        optionsButton.addListener(new ClickListener(){
        	public boolean mouseMoved(InputEvent event,
                    float x,
                    float y){
        		if(focusedBotton!=2){
        			buttons[focusedBotton-1].setStyle(normalStyle);
        		}
        		focusedBotton=2;
        		return true;
        		
        	}

        });
        table.add( optionsButton ).uniform().fill().spaceBottom(10);
        table.row();
        // register the button "high scores"
        exitButton.addListener( new InputListener() {
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
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) 
            {
                return true;
            }
        } );
        
        exitButton.addListener(new ClickListener(){
        	public boolean mouseMoved(InputEvent event,
                    float x,
                    float y){
        		if(focusedBotton!=3){
        			buttons[focusedBotton-1].setStyle(normalStyle);
        		}
        		focusedBotton=3;
        		return true;
        		
        	}

        });
        table.add( exitButton ).uniform().fill();
    }
    
	public void render(float delta) {
	       Gdx.gl.glClearColor( 0f, 0f, 0f, 1f );
	        Gdx.gl.glClear( GL20.GL_COLOR_BUFFER_BIT );
	        batch.begin();
	        batch.draw(background, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
	        buttons[focusedBotton-1].setStyle(focusedStyle);
	        //Update delta and draw the actors inside the stage
	        batch.end();
	        stage.act( delta );
	        stage.draw();
		
	}
	@Override
	public boolean keyDown(int keycode) {
		if( keycode == Keys.W || keycode == Keys.UP){
			buttons[focusedBotton-1].setStyle(normalStyle);
			if(focusedBotton==1)
				focusedBotton=3;
			else
				focusedBotton--;
			return true;
		}else{
			if(keycode == Keys.S || keycode == Keys.DOWN){
				buttons[focusedBotton-1].setStyle(normalStyle);
				if(focusedBotton==3)
					focusedBotton=1;
				else
					focusedBotton++;
				return true;
			}else{
				if(keycode == Keys.ENTER){
					if(focusedBotton==1){
		                Rowing.game.clearProcessors();
		                Rowing.game.setScreen(new TeamSelectionScreen(game,Utils.loadEquipoOrio()));
		                return true;
					}else{
						if(focusedBotton==2){
			                Rowing.game.clearProcessors();
			                Rowing.game.setScreen(new OptionsScreen(game,true));
			                return true;
						}else{
							if(focusedBotton==3){
								System.exit(0);
								
							}
						}
					}
					return true;
				}
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
		return false;
	}
}
