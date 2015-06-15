package com.rowing.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.rowing.core.Rowing;
import com.rowing.graphics.DirectionalAnimatedRenderer;
import com.rowing.utils.GraphicsLoader;
import com.rowing.utils.MusicPlayer;

public class RegattaScreen extends AbstractScreen implements InputProcessor{
	DirectionalAnimatedRenderer boat_renderer;
	float pos_x_1;
	float pos_y_1;
	float pos_x_2;
	float pos_y_2;
	float pos_x_3;
	float pos_y_3;
	float pos_x_4;
	float pos_y_4;
	private Texture background;
	public RegattaScreen(Rowing game) {
		super(game);
		boat_renderer=GraphicsLoader.loadBoat();
		pos_x_1=100f;
		pos_y_1=200f;
		background = new Texture(Gdx.files.internal("resources/conchaid.png"));
		MusicPlayer.play("traineracorto.mp3");
	}
	public void render(float delta) {
		//pos_x_1+=(50)*delta;
		pos_x_1+=(50)*delta;
		pos_y_1+=(-1)*delta;
		pos_x_2+=(55)*delta;
		pos_x_3+=(65)*delta;
		pos_x_4+=(70)*delta;
		
		System.out.println(pos_x_1);
		System.out.println(pos_y_1);
		this.delta=delta;
		Gdx.gl.glClearColor( 0f, 0f, 0f, 1f );
		Gdx.gl.glClear( GL20.GL_COLOR_BUFFER_BIT );
		batch.begin();
		batch.draw(background, 0, 0, Gdx.graphics.getWidth(),
				Gdx.graphics.getHeight());
		batch.draw(boat_renderer.frame(delta), pos_x_1, pos_y_1);
		batch.draw(boat_renderer.frame(delta), pos_x_2, 210);
		batch.draw(boat_renderer.frame(delta), pos_x_3, 220);
		batch.draw(boat_renderer.frame(delta), pos_x_4, 230);

		//buttons[focusedBotton-1].setStyle(focusedStyle);
		//Update delta and draw the actors inside the stage
		batch.end();
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
