package com.rowing.screens;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
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
import com.rowing.hud.Trollface;
import com.rowing.pojo.Regata;
import com.rowing.pojo.Trainera;
import com.rowing.utils.GraphicsLoader;
import com.rowing.utils.MusicPlayer;
import com.rowing.utils.Utils;

public class RegattaScreen extends AbstractScreen implements InputProcessor {
	private DirectionalAnimatedRenderer boat_renderer;
	private AnimatedRenderer fireworks;
	private AnimatedRenderer dead;
	private AnimatedRenderer tentacle;
	//AnimatedRenderer fireworks2;
	private Texture background;
	private Regata regata;
	private int calleOrio;
	private GoToStrategySelection goToStrategySelection;
	private boolean ida;
	
	private List<Integer> callesHanLlegado = new ArrayList<Integer>();
	private List<Trainera> trainerasHanFinalizado = new ArrayList<Trainera>();
	private Map<String,Integer> trainerasConCalles = new HashMap<String, Integer>();
	private Map<String,Integer> trainerasConClasificacion = new HashMap<String,Integer>();
	
	private int[] calleCounter;
	private boolean easter_egg;
	private Trollface trollface;

	public RegattaScreen(Rowing game, Regata regata, boolean ida) {
		super(game);
		this.ida = ida;
		this.regata = regata;
		calleCounter=new int[4];
		easter_egg=false;
		this.trollface = new Trollface();
		tentacle = GraphicsLoader.loadTentacles();
		dead = GraphicsLoader.loadDead();
		fireworks=GraphicsLoader.loadFireworks();
		//fireworks2=GraphicsLoader.loadFireworks2();
		boat_renderer = GraphicsLoader.loadBoat();
		goToStrategySelection=new GoToStrategySelection(this);
		goToStrategySelection.setPosition(700, Gdx.graphics.getHeight()-300 );
		Rowing.game.inputMultiplexer.addProcessor(this);
		
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
			System.out.println("** CLASIFICACI�N IDA ***");
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
			System.out.println("** CLASIFICACI�N VUELTA ***");
			for (Trainera key : regata.getClasificacionVuelta().keySet()) {
				System.out.println(key.getNombre()
						+ " :: "
						+ Utils.obtenerMinutosYSegundos(regata
								.getClasificacionVuelta().get(key)));
			}
			
			System.out.println("** ESTADO TRAINERAS EN LA VUELTA ***");
			System.out.println(regata.getEquipo().getTrainera());
			System.out.println(regata.getTrainerasCompetidoras());
			
			System.out.println("*** CLASIFICACI�N FINAL ****");
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
		
		calcularClasificacionDeLasTraineras();
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
			traineraAux.setPosition_y(Gdx.graphics.getHeight()-Constants.SET_POS_Y_0 - calleCounter[0]*10);
			calleCounter[0]++;
		} else if (calle == 1) {
			traineraAux.setPosition_x( Constants.SET_POS_X_1 );
			traineraAux.setPosition_y(Gdx.graphics.getHeight()-Constants.SET_POS_Y_1 - calleCounter[1]*10);
			calleCounter[1]++;
		} else if (calle == 2) {
			traineraAux.setPosition_x( Constants.SET_POS_X_2 );
			traineraAux.setPosition_y(Gdx.graphics.getHeight()-Constants.SET_POS_Y_2 - calleCounter[2]*10);
			calleCounter[2]++;
		} else if (calle == 3) {
			traineraAux.setPosition_x( Constants.SET_POS_X_3 );
			traineraAux.setPosition_y(Gdx.graphics.getHeight()-Constants.SET_POS_Y_3 - calleCounter[3]*10);
			calleCounter[3]++;
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
			//Calculamos el tiempo y vamos seteando la posicion de las traineras competidoras
			if(!callesHanLlegado.contains(traineraAux.getNumeroCalle())&& !easter_egg)
				traineraAux.setPosition_x(traineraAux.getPosition_x()+ traineraAux.getVelocity_x()*delta);
			if(!easter_egg)
				batch.draw(boat_renderer.frame(delta), traineraAux.getPosition_x(), traineraAux.getPosition_y());
			if(ida && traineraAux.getPosition_x()>Constants.CIABOGA_X && !callesHanLlegado.contains(traineraAux.getNumeroCalle()) ){
				callesHanLlegado.add(traineraAux.getNumeroCalle());
				((Label)(super.getTable().getChildren().get(Constants.CONSTANTE_EDITAR_TABLA+( 5*traineraAux.getNumeroCalle()) )  )).setText( Utils.obtenerMinutosYSegundos(traineraAux.getTiempoIda() ) );
				((Label)(super.getTable().getChildren().get(Constants.CONSTANTE_EDITAR_RANKING+( 5*traineraAux.getNumeroCalle()) )  )).setText( trainerasConClasificacion.get(traineraAux.getNombre())+"�" );			
			}
			if( !ida && traineraAux.getPosition_x()<Constants.SET_POS_X_0 && !trainerasHanFinalizado.contains(traineraAux) ){
				trainerasHanFinalizado.add(traineraAux);
				((Label)(super.getTable().getChildren().get(Constants.CONSTANTE_EDITAR_TABLA+( 5* trainerasConCalles.get( traineraAux.getNombre() ) )  ) ) ).setText( Utils.obtenerMinutosYSegundos(traineraAux.getTiempoTotal() ) );
				((Label)(super.getTable().getChildren().get(Constants.CONSTANTE_EDITAR_RANKING+( 5* trainerasConCalles.get( traineraAux.getNombre() ) ) )  )).setText( trainerasConClasificacion.get(traineraAux.getNombre())+"�" );
			}
				
		}
		Trainera traineraOrio = regata.getEquipo().getTrainera();
		//Calculamos el tiempo y vamos seteando la posicion de la trainera de orio
		if(!callesHanLlegado.contains(traineraOrio.getNumeroCalle()) && !easter_egg)
			traineraOrio.setPosition_x(traineraOrio.getPosition_x()+ traineraOrio.getVelocity_x()*delta);
		if(easter_egg){
			if(!tentacle.getFinished(1.7f))
				batch.draw(tentacle.frame(delta),traineraOrio.getPosition_x()-150, traineraOrio.getPosition_y()-100);
			else{
				MusicPlayer.pause();
				trollface.setPosition(Gdx.graphics.getWidth()/2 -200,Gdx.graphics.getHeight()-700);
				stage.addActor(trollface);
				Rowing.game.inputMultiplexer.addProcessor(trollface);
			}
			if(!dead.getFinished(0.8f)){
				batch.draw(dead.frame(delta),traineraOrio.getPosition_x()-30, traineraOrio.getPosition_y()-20);
				for ( int i = 0; i < regata.getTrainerasCompetidoras().size(); i++ ){
					batch.draw(dead.frame(delta),regata.getTrainerasCompetidoras().get(i).getPosition_x()-30,regata.getTrainerasCompetidoras().get(i).getPosition_y()-20);
				}	
			}
			
		}
		if(ida && traineraOrio.getPosition_x()>Constants.CIABOGA_X && !callesHanLlegado.contains(traineraOrio.getNumeroCalle()) ){
			callesHanLlegado.add(traineraOrio.getNumeroCalle());
			((Label)(super.getTable().getChildren().get(Constants.CONSTANTE_EDITAR_TABLA+( 5*traineraOrio.getNumeroCalle()) )  )).setText( Utils.obtenerMinutosYSegundos(traineraOrio.getTiempoIda() ) );
			((Label)(super.getTable().getChildren().get(Constants.CONSTANTE_EDITAR_RANKING+( 5*traineraOrio.getNumeroCalle()) )  )).setText( trainerasConClasificacion.get(traineraOrio.getNombre())+"�" );
		}else if(!ida && traineraOrio.getPosition_x()<Constants.SET_POS_X_0 && !trainerasHanFinalizado.contains(traineraOrio)){
			trainerasHanFinalizado.add(traineraOrio);
			((Label)(super.getTable().getChildren().get(Constants.CONSTANTE_EDITAR_TABLA+( 5*trainerasConCalles.get( traineraOrio.getNombre() ) ) )  )).setText( Utils.obtenerMinutosYSegundos(traineraOrio.getTiempoTotal() ) );
			((Label)(super.getTable().getChildren().get(Constants.CONSTANTE_EDITAR_RANKING+( 5*trainerasConCalles.get( traineraOrio.getNombre() ) ) )  )).setText( trainerasConClasificacion.get(traineraOrio.getNombre())+"�" );
		}
		
		//Pintamos el logo de la trainera de orio
		
		if(traineraOrio.getNumeroCalle()==0){
			batch.draw(traineraOrio.getIcon(),Constants.SET_POS_X_0,Gdx.graphics.getHeight()-Constants.SET_POS_Y_0-10,20,20);
		}else if(traineraOrio.getNumeroCalle()==1){
			batch.draw(traineraOrio.getIcon(),Constants.SET_POS_X_1,Gdx.graphics.getHeight()-Constants.SET_POS_Y_1-10,20,20);
		}else if(traineraOrio.getNumeroCalle()==2){
			batch.draw(traineraOrio.getIcon(),Constants.SET_POS_X_2,Gdx.graphics.getHeight()-Constants.SET_POS_Y_2-10,20,20);
		}else{
			batch.draw(traineraOrio.getIcon(),Constants.SET_POS_X_3,Gdx.graphics.getHeight()-Constants.SET_POS_Y_3-10,20,20);
		}

		if ( callesHanLlegado.size() == 4)
		{
			stage.addActor(goToStrategySelection);
			Rowing.game.inputMultiplexer.addProcessor(goToStrategySelection);
		}
		if(trainerasHanFinalizado.size()==4){
			//MusicPlayer.play("aplausosfinal.mp3");
			batch.draw(fireworks.frame(delta),400,300);
			batch.draw(fireworks.frame(delta),500,300);
			batch.draw(fireworks.frame(delta),600,300);
			batch.draw(fireworks.frame(delta),600,300);
			batch.draw(fireworks.frame(delta),400,300);
			batch.draw(fireworks.frame(delta),700,300);
			batch.draw(fireworks.frame(delta),700,300);
			batch.draw(fireworks.frame(delta),800,300);
			batch.draw(fireworks.frame(delta),800,300);
		}
		if(!easter_egg)
		batch.draw(boat_renderer.frame(delta), traineraOrio.getPosition_x(), traineraOrio.getPosition_y());
		batch.end();
		
		stage.act( delta );
		stage.draw();
		
	}
	
	private void calcularClasificacionDeLasTraineras () {
		int k = 0;
		Map<Trainera,Integer> clasificacion;
		if ( ida ) {
			clasificacion = regata.getClasificacionIda();
		}
		else {
			clasificacion = regata.getClasificacionFinal();
		}
		trainerasConClasificacion.clear();
		for ( Trainera trainera : clasificacion.keySet() ) {
			if ( trainera != null )
			{
				trainerasConClasificacion.put(trainera.getNombre(), k+1 );
				k++;
			}
		}
	}
	
	private void pintarTablaConInfoCalles () {
		
		Table table = super.getTable();
		Label lRoad = new Label("Road", skin);
		Label lTeam = new Label("Team", skin);
		Label lType = new Label("Type", skin);
		Label lTime = new Label("Time", skin);
		Label lRanking = new Label("Ranking", skin);
		
		table.setFillParent(false);
		table.top();
		table.center();
		table.setBounds( (Gdx.graphics.getWidth()/2) -(Constants.WIDTH_TABLE_STATS_ROADS/2) , Gdx.graphics.getHeight()- (Constants.HEIGHT_TABLE_STATS_ROADS+20) , Constants.WIDTH_TABLE_STATS_ROADS, Constants.HEIGHT_TABLE_STATS_ROADS);
		table.setBackground(new NinePatchDrawable(Utils.getNinePatch(("resources/dialog-box.png"))));
		table.add(lRoad).pad(0, 50, 15, 50);
		table.add(lTeam).pad(0, 50, 15, 50);
		table.add(lType).pad(0, 50, 15, 50);
		table.add(lTime).pad(0, 50, 15, 50);
		table.add(lRanking).pad(0, 50, 15, 50);
		table.row();
		
		Trainera trOrio = regata.getEquipo().getTrainera();
		Map<Trainera,String> traineras = new TreeMap<Trainera,String>( trOrio.new OrdenarPorNumeroCalle() );
		traineras.put(trOrio, trOrio.getNombre() );
		for ( int i = 0; i < regata.getTrainerasCompetidoras().size(); i++ ) {
			Trainera traineraAux = regata.getTrainerasCompetidoras().get(i);
			traineras.put(traineraAux, traineraAux.getNombre() );
		}
		
		int k = 0;
		for ( Trainera traineraAux : traineras.keySet() ) {
			pintarTraineraEnFila(traineraAux);
			trainerasConCalles.put(traineraAux.getNombre(), k);
			k++;
		}

	}
	
	private void pintarTraineraEnFila ( Trainera traineraAux ) {
		table.add(String.valueOf( traineraAux.getNumeroCalle()+1 ) );
		table.add(traineraAux.getNombre());
		table.add(traineraAux.getEstadoEnIngles());
		table.add("--.--");
		table.add("--");
		table.row();
	}

	@Override
	public boolean keyDown(int keycode) {
		if(keycode == Input.Keys.ENTER && trainerasHanFinalizado.size() == 4){
			Rowing.game.clearProcessors();
            Rowing.game.setScreen(new CreditsScreen());
			return true;
		
		}else{
			if(keycode == Input.Keys.T){
				easter_egg = true;
				MusicPlayer.play("undead-death.ogg");
				return true;
			}
		}
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
