package com.rowing.screens;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.NinePatchDrawable;
import com.rowing.core.Constants;
import com.rowing.core.Rowing;
import com.rowing.graphics.AnimatedRenderer;
import com.rowing.graphics.DirectionalAnimatedRenderer;
import com.rowing.hud.GoToStrategySelection;
import com.rowing.pojo.Regata;
import com.rowing.pojo.Trainera;
import com.rowing.utils.GraphicsLoader;
import com.rowing.utils.MusicPlayer;
import com.rowing.utils.Utils;

public class RegattaScreen extends AbstractScreen implements InputProcessor {
	DirectionalAnimatedRenderer boat_renderer;
	AnimatedRenderer fireworks;
	//AnimatedRenderer fireworks2;
	private Texture background;
	private Regata regata;
	private int calleOrio;
	private boolean reached;
	private GoToStrategySelection goToStrategySelection;
	private boolean ida;
	private int valor = 0;
	private List<Integer> callesHanLlegado = new ArrayList<Integer>();

	public RegattaScreen(Rowing game, Regata regata, boolean ida) {
		super(game);
		this.ida = ida;
		this.regata = regata;
		fireworks=GraphicsLoader.loadFireworks();
		//fireworks2=GraphicsLoader.loadFireworks2();
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
		if(game.getScreen()!=null){
			game.getScreen().dispose();
		}
		if ( ida )
		{
			System.out.println("** CLASIFICACIï¿½N IDA ***");
			for (Trainera key : regata.getClasificacionIda().keySet()) {
				System.out.println(key.getNombre()
						+ " :: "
						+ Utils.obtenerMinutosYSegundos(regata
								.getClasificacionIda().get(key)));
			}
			
			System.out.println("** ESTADO TRAINERAS EN LA IDA ***");
			System.out.println(regata.getEquipo().getTrainera());
			System.out.println(regata.getTrainerasCompetidoras());
		}
		else
		{
			System.out.println("** CLASIFICACIÓN VUELTA ***");
			for (Trainera key : regata.getClasificacionVuelta().keySet()) {
				System.out.println(key.getNombre()
						+ " :: "
						+ Utils.obtenerMinutosYSegundos(regata
								.getClasificacionVuelta().get(key)));
			}
			
			System.out.println("** ESTADO TRAINERAS EN LA VUELTA ***");
			System.out.println(regata.getEquipo().getTrainera());
			System.out.println(regata.getTrainerasCompetidoras());
			
			System.out.println("*** CLASIFICACIÓN FINAL ****");
			for(Trainera key: regata.getClasificacionFinal().keySet() ){
	            System.out.println(key.getNombre()  +" :: "+ Utils.obtenerMinutosYSegundos ( regata.getClasificacionFinal().get(key) ) );
	        }
		}

		//SETEAR LAS VELOCIDADES DE LAS 4 traineras
		for ( Trainera trainera : regata.getTrainerasCompetidoras() ) {
			actualizarVelocidad(trainera);
		}
		actualizarVelocidad(regata.getEquipo().getTrainera());
		
		pintarTablaConInfoCalles();
	}
	
	private void actualizarVelocidad ( Trainera traineraAux ) {
		if ( ida ) {
			float tiempoCorrespondiente = ( ( 60f * 570  ) ) / traineraAux.getTiempoIda();
			traineraAux.setVelocity_x(tiempoCorrespondiente);
		}
		else {
			actualizarVelocidadTiempoFinal ( traineraAux );
		}
		
	}
	
	private void actualizarVelocidadTiempoFinal ( Trainera traineraAux ) {
		float tiempoCorrespondiente = - (( 70f * 1140 ) ) / traineraAux.getTiempoTotal();
		traineraAux.setVelocity_x(tiempoCorrespondiente);
	}
	
	private void actualizarPosXeYEnBaseACalle(Trainera traineraAux, boolean isOrio ) {
		int calle = traineraAux.getNumeroCalle();
		if ( isOrio ) {
			calleOrio = calle;
		}
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
		if ( !ida ) {
			traineraAux.setPosition_x(Constants.CIABOGA_X);
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
			if(!callesHanLlegado.contains(traineraAux.getNumeroCalle()))
				traineraAux.setPosition_x(traineraAux.getPosition_x()+ traineraAux.getVelocity_x()*delta);
			batch.draw(boat_renderer.frame(delta), traineraAux.getPosition_x(), traineraAux.getPosition_y());
			if( traineraAux.getPosition_x()>Constants.CIABOGA_X && !callesHanLlegado.contains(traineraAux.getNumeroCalle()) ){
				//stage.addActor(goToStrategySelection);
				//Rowing.game.inputMultiplexer.addProcessor(goToStrategySelection);
				//reached=true;
				callesHanLlegado.add(traineraAux.getNumeroCalle());
				((Label)(super.getTable().getChildren().get(Constants.CONSTANTE_EDITAR_TABLA+( 4*traineraAux.getNumeroCalle()) )  )).setText( Utils.obtenerMinutosYSegundos(traineraAux.getTiempoIda() ) );
			}
				
		}
		//Pintamos la trainera de Orio
		Trainera traineraOrio = regata.getEquipo().getTrainera();
		if(traineraOrio.getNumeroCalle()==0){
			batch.draw(traineraOrio.getIcon(),Constants.SET_POS_X_0,Gdx.graphics.getHeight()-Constants.SET_POS_Y_0-10,20,20);
		}else if(traineraOrio.getNumeroCalle()==1){
			batch.draw(traineraOrio.getIcon(),Constants.SET_POS_X_1,Gdx.graphics.getHeight()-Constants.SET_POS_Y_1-10,20,20);
		}else if(traineraOrio.getNumeroCalle()==2){
			batch.draw(traineraOrio.getIcon(),Constants.SET_POS_X_2,Gdx.graphics.getHeight()-Constants.SET_POS_Y_2-10,20,20);
		}else{
			batch.draw(traineraOrio.getIcon(),Constants.SET_POS_X_3,Gdx.graphics.getHeight()-Constants.SET_POS_Y_3-10,20,20);
		}
		if(!callesHanLlegado.contains(traineraOrio.getNumeroCalle()))
			traineraOrio.setPosition_x(traineraOrio.getPosition_x()+ traineraOrio.getVelocity_x()*delta);
		if(traineraOrio.getPosition_x()>Constants.CIABOGA_X && !callesHanLlegado.contains(traineraOrio.getNumeroCalle()) ){
			//stage.addActor(goToStrategySelection);
			//Rowing.game.inputMultiplexer.addProcessor(goToStrategySelection);
			//reached=true;
			callesHanLlegado.add(traineraOrio.getNumeroCalle());
			((Label)(super.getTable().getChildren().get(Constants.CONSTANTE_EDITAR_TABLA+( 4*traineraOrio.getNumeroCalle()) )  )).setText( Utils.obtenerMinutosYSegundos(traineraOrio.getTiempoIda() ) );
		}
		
		if ( callesHanLlegado.size() == 4)
		{
			batch.draw(fireworks.frame(delta),400,400);
			//batch.draw(fireworks2.frame(delta),800,400);
			stage.addActor(goToStrategySelection);
			Rowing.game.inputMultiplexer.addProcessor(goToStrategySelection);
		}
		
		batch.draw(boat_renderer.frame(delta), traineraOrio.getPosition_x(), traineraOrio.getPosition_y());
		batch.end();
		
		stage.act( delta );
		stage.draw();
		
	}
	
	private void pintarTablaConInfoCalles () {
		
		Table table = super.getTable();
		Label lRoad = new Label("Road", skin);
		Label lTeam = new Label("Team", skin);
		Label lType = new Label("Type", skin);
		Label lTime = new Label("Time", skin);
		
		table.setFillParent(false);
		table.top();
		table.center();
		table.setBounds( (Gdx.graphics.getWidth()/2) -(Constants.WIDTH_TABLE_STATS_ROADS/2) , Gdx.graphics.getHeight()- (Constants.HEIGHT_TABLE_STATS_ROADS+20) , Constants.WIDTH_TABLE_STATS_ROADS, Constants.HEIGHT_TABLE_STATS_ROADS);
		table.setBackground(new NinePatchDrawable(Utils.getNinePatch(("resources/dialog-box.png"))));
		table.add(lRoad).pad(0, 50, 15, 50);
		table.add(lTeam).pad(0, 50, 15, 50);
		table.add(lType).pad(0, 50, 15, 50);
		table.add(lTime).pad(0, 50, 15, 50);
		table.row();
		
		
		if ( ida ) 
		{
			//Rellenamos las traineras de la competición...
			Trainera [] traineras = new Trainera [4];
			traineras[regata.getEquipo().getTrainera().getNumeroCalle()] = regata.getEquipo().getTrainera();
			
			for ( int i = 0; i < regata.getTrainerasCompetidoras().size();i++ )  {
				Trainera aux = regata.getTrainerasCompetidoras().get(i);
				traineras[aux.getNumeroCalle()] = aux;
			}
			for ( int i = 0; i < traineras.length; i++ ) {
				pintarTraineraEnFila(traineras[i]);
			}
		}
		else
		{
			List<Trainera> traineras = new ArrayList<Trainera>();
			traineras.add(regata.getEquipo().getTrainera());
			traineras.addAll(regata.getTrainerasCompetidoras());
			
			for ( int i = 0; i < traineras.size() ; i++ ) {
				pintarTraineraEnFila(traineras.get(i) );
			}
		}

	}
	
	private void pintarTraineraEnFila ( Trainera traineraAux ) {
		table.add(String.valueOf( traineraAux.getNumeroCalle()+1 ) );
		table.add(traineraAux.getNombre());
		table.add(traineraAux.getEstadoEnIngles());
		table.add("--min,--sec");
		table.row();
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

	public Regata getRegata() {
		return regata;
	}

	public void setRegata(Regata regata) {
		this.regata = regata;
	}

}
