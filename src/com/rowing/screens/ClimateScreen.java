package com.rowing.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.ui.TextField.TextFieldStyle;
import com.rowing.core.GameSession;
import com.rowing.core.Rowing;
import com.rowing.pojo.Equipo;
import com.rowing.utils.CondicionesMeteo;

public class ClimateScreen extends AbstractScreen implements InputProcessor {
	private Texture background;
	private Game game;
	private Equipo equipo;
	private CondicionesMeteo condicionesMeteo;
	private TextFieldStyle textFieldStyle;
	
	public ClimateScreen(Rowing game,Equipo equipo) {
		super(game);
		background=new Texture(Gdx.files.internal("resources/farotiempo.jpg"));
		this.game=game;
		this.equipo=equipo;
		//conseguimos las condiciones meteorologicas de la sesión del juego
		this.condicionesMeteo = GameSession.getInstance().condicionesMeteo;
		//definimos el textfieldstyle
		textFieldStyle = new TextFieldStyle();
	
	}
	
	public void render(float delta) {
		this.delta=delta;
		Gdx.gl.glClearColor( 0f, 0f, 0f, 1f );
		Gdx.gl.glClear( GL20.GL_COLOR_BUFFER_BIT );
		batch.begin();
		batch.draw(background, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		//Update delta and draw the actors inside the stage
		batch.end();
		stage.act( delta );
		stage.draw();	
	}
	@Override
	public void show() {
		super.show();
		
		Table table = super.getTable();
		especificarTiempoEnTabla ( table );
		
	};
	
	public void especificarTiempoEnTabla ( Table table ) 
	{
		Label windSpeed = new Label("Wind Speed", skin );
		TextField windSpeedText = new TextField("Prueba", skin );
		
		table.add(windSpeed);
		table.add(windSpeedText).width(100);
	}
	
	 
	@Override
	public boolean keyDown(int arg0) {
		return false;
	}

	@Override
	public boolean keyTyped(char arg0) {
		return false;
	}

	@Override
	public boolean keyUp(int arg0) {
		return false;
	}

	@Override
	public boolean mouseMoved(int arg0, int arg1) {
		return false;
	}

	@Override
	public boolean scrolled(int arg0) {
		return false;
	}

	@Override
	public boolean touchDown(int arg0, int arg1, int arg2, int arg3) {
		return false;
	}

	@Override
	public boolean touchDragged(int arg0, int arg1, int arg2) {
		return false;
	}

	@Override
	public boolean touchUp(int arg0, int arg1, int arg2, int arg3) {
		return false;
	}

}
