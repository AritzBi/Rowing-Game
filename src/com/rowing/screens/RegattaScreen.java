package com.rowing.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.rowing.core.Constants;
import com.rowing.core.Rowing;
import com.rowing.graphics.DirectionalAnimatedRenderer;
import com.rowing.hud.GoToStrategySelection;
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
	private boolean reached;
	private GoToStrategySelection goToStrategySelection;

	public RegattaScreen(Rowing game, Regata regata) {
		super(game);
		boat_renderer = GraphicsLoader.loadBoat();
		goToStrategySelection=new GoToStrategySelection(this);
		goToStrategySelection.setPosition(700, Gdx.graphics.getHeight()-300 );
		reached = false;
		// traineras competidoras
		for (int i = 0; i < regata.getTrainerasCompetidoras().size(); i++) {
			Trainera traineraAux = regata.getTrainerasCompetidoras().get(i);
			actualizarPosXeYEnBaseACalle ( traineraAux, false );
		}
		actualizarPosXeYEnBaseACalle(regata.getEquipo().getTrainera(), true );
		background = new Texture(Gdx.files.internal("resources/conchaid.png"));
		MusicPlayer.play("traineracorto.mp3");
		this.regata = regata;

		System.out.println("** CLASIFICACI�N IDA ***");
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
		this.delta = delta;
		Gdx.gl.glClearColor(0f, 0f, 0f, 1f);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		batch.draw(background, 0, 0, Gdx.graphics.getWidth(),
				Gdx.graphics.getHeight());		
		for ( int i = 0; i < regata.getTrainerasCompetidoras().size(); i++ )
		{
			Trainera traineraAux = regata.getTrainerasCompetidoras().get(i);
			if(!reached)
				traineraAux.setPosition_x(traineraAux.getPosition_x()+ traineraAux.getVelocity_x()*delta);
			batch.draw(boat_renderer.frame(delta), traineraAux.getPosition_x(), traineraAux.getPosition_y());
			if(traineraAux.getPosition_x()>Constants.CIABOGA_X && !reached){
				stage.addActor(goToStrategySelection);
				Rowing.game.inputMultiplexer.addProcessor(goToStrategySelection);
				reached=true;
			}
				
		}
		//Pintamos la trainera de Orio
		Trainera traineraOrio = regata.getEquipo().getTrainera();
		if(!reached)
			traineraOrio.setPosition_x(traineraOrio.getPosition_x()+ traineraOrio.getVelocity_x()*delta);
		if(traineraOrio.getPosition_x()>Constants.CIABOGA_X && !reached){
			stage.addActor(goToStrategySelection);
			Rowing.game.inputMultiplexer.addProcessor(goToStrategySelection);
			reached=true;
		}
		batch.draw(boat_renderer.frame(delta), traineraOrio.getPosition_x(), traineraOrio.getPosition_y());
		batch.end();
		if (reached){
			stage.act( delta );
			stage.draw();	
		}
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

	public Regata getRegata() {
		return regata;
	}

	public void setRegata(Regata regata) {
		this.regata = regata;
	}

}
