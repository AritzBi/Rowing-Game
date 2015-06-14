package com.rowing.screens;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.rowing.core.Constants;
import com.rowing.core.Rowing;
import com.rowing.pojo.Equipo;
import com.rowing.utils.Utils;

public class StrategySelectionScreen extends AbstractScreen implements InputProcessor{
	private Texture background;
	private Game game;
	private Equipo equipo;
	private int focusedBotton;
	private Table table;
	private TextButton []buttons;
	private TextButtonStyle normalStyle;
	private TextButtonStyle focusedStyle;
	private List<String>strategies;
	public StrategySelectionScreen(Rowing game,Equipo equipo,List<String> estrategiasSalida) {
		super(game);
		background=new Texture(Gdx.files.internal("resources/conchatarde.jpg"));
		this.game=game;
		this.equipo=equipo;
		focusedBotton=1;
		normalStyle=new TextButtonStyle();
		normalStyle.font=getSkin().getFont("buttonFont");
		normalStyle.up=getSkin().getDrawable("normal-button");
		normalStyle.down=getSkin().getDrawable("pushed-button");
		normalStyle.font.setScale(0.5f);
		focusedStyle=new TextButtonStyle();
		focusedStyle.font=getSkin().getFont("buttonFont");
		focusedStyle.up=getSkin().getDrawable("focused-button");
		focusedStyle.down=getSkin().getDrawable("pushed-button");
		this.strategies=estrategiasSalida;
		buttons=new TextButton[estrategiasSalida.size()];
		int i=0;
		for(String strategy : estrategiasSalida){
			buttons[i]=new TextButton( strategy, normalStyle);
			buttons[i].setWidth(300);
			buttons[i].setBounds(0, 0, 300, 60);
			i++;
		}
		Rowing.game.inputMultiplexer.addProcessor(this);
		if(game.getScreen()!=null){
			game.getScreen().dispose();
		}
	}
	
    public void show()
    {
    	super.show();
        // retrieve the default table actor
        Table table = super.getTable();
        TextButton button;
        System.out.println(buttons.length);
        for(int i=0;i < buttons.length ; i++){
        	int position = i;
        	System.out.println(buttons.length);
        	button = buttons[i];
        	button.addListener( new InputListener() {
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
                		
                	}

                }

            } );
            
        	button.addListener(new ClickListener(){
            	public boolean mouseMoved(InputEvent event,
                        float x,
                        float y){
            		if(focusedBotton!=1){
            			buttons[focusedBotton-1].setStyle(normalStyle);
            		}
            		//focusedBotton=i+1;
            		return true;
            		
            	}

            });
            table.add( button ).size( 500, 60 ).uniform().spaceBottom( 10 );
            table.row();
        }
    }
	public void render(float delta) {
		this.delta=delta;
		Gdx.gl.glClearColor( 0f, 0f, 0f, 1f );
		Gdx.gl.glClear( GL20.GL_COLOR_BUFFER_BIT );
		batch.begin();
		batch.draw(background, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		batch.end();
		stage.act( delta );
		stage.draw();	
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
