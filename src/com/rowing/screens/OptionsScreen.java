package com.rowing.screens;

import java.util.ArrayList;
import java.util.Collections;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.CheckBox;
import com.badlogic.gdx.scenes.scene2d.ui.CheckBox.CheckBoxStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Slider;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.rowing.core.Rowing;
import com.rowing.utils.MusicPlayer;


public class OptionsScreen extends AbstractScreen implements InputProcessor{
	private Texture background;
	private	Slider music;
	private Slider resolution;
	private Label labelMusic;
	private Label labelResolution;
	private Label resolutionsLabel;
	private CheckBox fullScreen;
	private TextButton returnButton;
	private TextButtonStyle normalStyle;
	private TextButtonStyle focusedStyle;
	//private String[] resolutions;
	private ArrayList<Resolution>resolutions;
	private int currentValue;
	boolean fromMenu;
	boolean isFullScreen;
	public OptionsScreen(Rowing game, boolean fromMenu) {
		super(game);
		resolutions=new ArrayList<Resolution>();
		for(int i=0;i<Gdx.graphics.getDisplayModes().length;i++){
			if((Gdx.graphics.getDisplayModes())[i].height>600){
				resolutions.add(new Resolution(Gdx.graphics.getDisplayModes()[i].width,Gdx.graphics.getDisplayModes()[i].height));
			}
		}
		Collections.sort(resolutions);
		this.fromMenu=fromMenu;
		boolean found=false;
		if(Gdx.graphics.getDesktopDisplayMode().width==Gdx.graphics.getWidth() && Gdx.graphics.getDesktopDisplayMode().height==Gdx.graphics.getHeight())
			isFullScreen=true;
		else
			isFullScreen=false;
		//resolutions=new String []{"1024x640","1280x800","1440x900","1280x1080"};
		currentValue=0;
		String actualRes=Gdx.graphics.getWidth()+"x"+Gdx.graphics.getHeight();
		for(int i=0;i<resolutions.size()&& !found;i++){
			if(actualRes.equals(resolutions.get(i).toString())){
				found=true;
				currentValue=i;
			}
		}
		this.background=new Texture(Gdx.files.internal("resources/background2.jpg"));
		this.music=new Slider(0,10,1,false,getSkin());
		this.music.setValue(MusicPlayer.instance.volume*10);
		this.labelMusic=new Label("music",skin);
		this.labelResolution=new Label("resolution",skin);
		LabelStyle lStyle=new LabelStyle();
		lStyle.font=skin.getFont("numberFont");
		lStyle.fontColor=skin.getColor("white");
		this.resolutionsLabel=new Label(resolutions.get(currentValue).toString(),lStyle);
		this.resolutionsLabel.setScale(1f, 1f);
		this.resolution=new Slider(0,resolutions.size()-1,1,false,getSkin());
		this.resolution.setValue(currentValue);
		normalStyle=new TextButtonStyle();
		normalStyle.font=getSkin().getFont("buttonFont");
		normalStyle.up=getSkin().getDrawable("normal-button");
		normalStyle.down=getSkin().getDrawable("pushed-button");
		focusedStyle=new TextButtonStyle();
		focusedStyle.font=getSkin().getFont("buttonFont");
		focusedStyle.up=getSkin().getDrawable("focused-button");
		focusedStyle.down=getSkin().getDrawable("pushed-button");
		returnButton = new TextButton( "Return", focusedStyle);
		CheckBoxStyle cbStyle=new CheckBoxStyle();
		cbStyle.checkboxOff=getSkin().getDrawable("check-off");
		cbStyle.checkboxOn=getSkin().getDrawable("check-on");
		cbStyle.font=getSkin().getFont("numberFont");
		cbStyle.font.setScale(0.5f, 0.5f);
		cbStyle.fontColor=getSkin().getColor("white");
		fullScreen=new CheckBox("FullScreen", cbStyle);
		fullScreen.setChecked(isFullScreen);
		stage.addActor(fullScreen);
		stage.addActor(music);
		stage.addActor(labelMusic);
		stage.addActor(labelResolution);
		stage.addActor(returnButton);
		stage.addActor(resolution);
		stage.addActor(resolutionsLabel);
		Rowing.game.inputMultiplexer.addProcessor(this);
	}
	
	public void show(){
		 super.show();
		 music.addListener( new ChangeListener() {

			@Override
			public void changed(ChangeEvent event, Actor actor) {
				MusicPlayer.instance.setVolume(music.getValue()/10);
			}
	        } );
		 
	        returnButton.addListener( new InputListener() {
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
	        			if(!fullScreen.isChecked()){
	        	    		Gdx.graphics.setDisplayMode(resolutions.get(currentValue).width,resolutions.get(currentValue).height,false);
	        			}
	        			if(fromMenu){
	        				Rowing.game.clearProcessors();
	        				Rowing.game.setScreen(new MenuScreen(game));
	        			}else{
	        				Rowing.game.restoreInputProcessors();
	        				Rowing.game.setScreen(Rowing.game.screens.pop());
	        			}
	            	}

	            }

	        } );
	        fullScreen.addListener(new ChangeListener(){

				@Override
				public void changed(ChangeEvent event, Actor actor) {
					if(fullScreen.isChecked()){
						Gdx.graphics.setDisplayMode(Gdx.graphics.getDesktopDisplayMode().width, Gdx.graphics.getDesktopDisplayMode().height, true);
					}else{
						Gdx.graphics.setDisplayMode(1280, 720, false);
        	    		//Gdx.graphics.setDisplayMode(resolutions.get(currentValue).width,resolutions.get(currentValue).height,false);
					}
					
				}
	        	
	        });

	        
	        resolution.addListener( new ChangeListener() {

				@Override
				public void changed(ChangeEvent event, Actor actor) {
					//resolutionsLabel.setText(resolutions[(int) resolution.getValue()]);
					currentValue=(int)resolution.getValue();
					resolutionsLabel.setText(resolutions.get(currentValue).toString());
				}
		        } );
	}
	
	public void resize(int width, int height) {
		super.resize(width, height);
		this.returnButton.setX(width-800);
		this.returnButton.setY(height-550);
		this.returnButton.setWidth(200);
		this.labelResolution.setX(width-800);
		this.labelResolution.setY(height-300);
		this.labelMusic.setX(width-800);
		this.labelMusic.setY(height-100);
		this.music.setX(width-800);
		this.music.setY(height-150);
		this.music.setWidth(390);
		this.fullScreen.setX(width-800);
		this.fullScreen.setY(height-400);
		this.resolution.setX(width-800);
		this.resolution.setY(height-350);
		this.resolutionsLabel.setX(width-650);
		this.resolutionsLabel.setY(height-360);
	}
	@Override
	public boolean keyDown(int keycode) {
		if(keycode==Keys.ENTER){
			if(!fullScreen.isChecked()){
	    		Gdx.graphics.setDisplayMode(resolutions.get(currentValue).width,resolutions.get(currentValue).height,false);
			}
    		if(fromMenu){
				Rowing.game.clearProcessors();
				Rowing.game.setScreen(new MenuScreen(game));
			}else{
				Rowing.game.restoreInputProcessors();
				Rowing.game.setScreen(Rowing.game.screens.pop());
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
	
	public void render(float delta) {
	       Gdx.gl.glClearColor( 0f, 0f, 0f, 1f );
	        Gdx.gl.glClear( GL20.GL_COLOR_BUFFER_BIT );
	        batch.begin();
	        batch.draw(background, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
	        //Update delta and draw the actors inside the stage
	        batch.end();
	        stage.act( delta );
	        stage.draw();
	        batch.begin();
	        batch.end();
	}

}
