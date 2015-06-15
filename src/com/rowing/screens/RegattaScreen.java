package com.rowing.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.rowing.core.Constants;
import com.rowing.core.Rowing;
import com.rowing.graphics.DirectionalAnimatedRenderer;
import com.rowing.pojo.Regata;
import com.rowing.pojo.Trainera;
import com.rowing.utils.GraphicsLoader;
import com.rowing.utils.MusicPlayer;
import com.rowing.utils.Utils;

public class RegattaScreen extends AbstractScreen implements InputProcessor {
	DirectionalAnimatedRenderer boat_renderer;
	private Texture background;
	private Regata regata;
	private int calleOrio;

	public RegattaScreen(Rowing game, Regata regata) {
		super(game);
		boat_renderer = GraphicsLoader.loadBoat();
		// traineras competidoras
		for (int i = 0; i < regata.getTrainerasCompetidoras().size(); i++) {
			Trainera traineraAux = regata.getTrainerasCompetidoras().get(i);
			actualizarPosXeYEnBaseACalle ( traineraAux, false );
		}
		actualizarPosXeYEnBaseACalle(regata.getEquipo().getTrainera(), true );
		background = new Texture(Gdx.files.internal("resources/conchaid.png"));
		MusicPlayer.play("traineracorto.mp3");
		this.regata = regata;

		System.out.println("** CLASIFICACIÓN IDA ***");
		for (Trainera key : regata.getClasificacionIda().keySet()) {
			System.out.println(key.getNombre()
					+ " :: "
					+ Utils.obtenerMinutosYSegundos(regata
							.getClasificacionIda().get(key)));
		}
		//SETEAR LAS VELOCIDADES DE LAS 4 traineras
		for ( Trainera trainera : regata.getTrainerasCompetidoras() ) {
			actualizarVelocidad(trainera);
		}
		actualizarVelocidad(regata.getEquipo().getTrainera());
		
		System.out.println("** ESTADO TRAINERAS EN LA IDA ***");
		System.out.println(regata.getEquipo().getTrainera());
		System.out.println(regata.getTrainerasCompetidoras());
	}
	
	private void actualizarVelocidad ( Trainera traineraAux ) {
		float tiempoCorrespondiente = ( ( 60f * 570  ) ) / traineraAux.getTiempoIda();
		traineraAux.setVelocity_x(tiempoCorrespondiente);
		System.out.println("A la calle: " + traineraAux.getNumeroCalle() + " del equipo " + traineraAux.getNombre() + " se le asigna " + tiempoCorrespondiente);
	}
	
	private void actualizarPosXeYEnBaseACalle(Trainera traineraAux, boolean isOrio ) {
		int calle = traineraAux.getNumeroCalle();
		calleOrio = calle;
		if (calle == 0) {
			traineraAux.setPosition_x( Constants.SET_POS_X_0 );
			traineraAux.setPosition_y(Gdx.graphics.getHeight()-Constants.SET_POS_Y_0 );
		} else if (calle == 1) {
			traineraAux.setPosition_x( Constants.SET_POS_X_1 );
			traineraAux.setPosition_y(Gdx.graphics.getHeight()-Constants.SET_POS_Y_1 );
		} else if (calle == 2) {
			traineraAux.setPosition_x( Constants.SET_POS_X_2 );
			traineraAux.setPosition_y(Gdx.graphics.getHeight()-Constants.SET_POS_Y_2 );
		} else if (calle == 3) {
			traineraAux.setPosition_x( Constants.SET_POS_X_3 );
			traineraAux.setPosition_y(Gdx.graphics.getHeight()-Constants.SET_POS_Y_3 );
		}
	}

	public void render(float delta) {
		// pos_x_1+=(50)*delta;
		//pos_x_1 += (50) * delta;
		//pos_y_1 += (-1) * delta;
		//pos_x_2 += (55) * delta;
		//pos_x_3 += (65) * delta;
		//pos_x_4 += (70) * delta;

		// System.out.println(pos_x_1);
		// System.out.println(pos_y_1);
		this.delta = delta;
		Gdx.gl.glClearColor(0f, 0f, 0f, 1f);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		batch.draw(background, 0, 0, Gdx.graphics.getWidth(),
				Gdx.graphics.getHeight());
		//batch.draw(boat_renderer.frame(delta), pos_x_1, pos_y_1);
		//batch.draw(boat_renderer.frame(delta), pos_x_2, 210);
	//	batch.draw(boat_renderer.frame(delta), pos_x_3, 220);
	//	batch.draw(boat_renderer.frame(delta), pos_x_4, 230);
		
		for ( int i = 0; i < regata.getTrainerasCompetidoras().size(); i++ )
		{
			Trainera traineraAux = regata.getTrainerasCompetidoras().get(i);
			traineraAux.setPosition_x(traineraAux.getPosition_x()+ traineraAux.getVelocity_x()*delta);
			batch.draw(boat_renderer.frame(delta), traineraAux.getPosition_x(), traineraAux.getPosition_y());
		}
		//Pintamos la trainera de Orio
		Trainera traineraOrio = regata.getEquipo().getTrainera();
		traineraOrio.setPosition_x(traineraOrio.getPosition_x()+ traineraOrio.getVelocity_x()*delta);
		batch.draw(boat_renderer.frame(delta), traineraOrio.getPosition_x(), traineraOrio.getPosition_y());

		// buttons[focusedBotton-1].setStyle(focusedStyle);
		// Update delta and draw the actors inside the stage
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
