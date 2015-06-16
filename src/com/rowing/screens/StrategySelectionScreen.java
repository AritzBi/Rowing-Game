package com.rowing.screens;

import java.util.ArrayList;
import java.util.List;

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
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.Timer.Task;
import com.rowing.core.Constants;
import com.rowing.core.Rowing;
import com.rowing.pojo.Regata;

public class StrategySelectionScreen extends AbstractScreen implements InputProcessor{
	private Texture background;
	private int focusedBotton;
	private TextButton []buttons;
	private TextButtonStyle normalStyle;
	private TextButtonStyle focusedStyle;
	private List<String>strategies;
	private Regata regata;
	private boolean orio;
	
	/**
	 * 
	 * @param regata
	 * @param orio. Si es true significa que estamos en la vuelta!!
	 * @param strategies
	 */
	public StrategySelectionScreen(Regata regata,boolean orio, List<String> strategies ) {
		super(Rowing.game);
		this.orio = orio;
		this.strategies = new ArrayList<String>( strategies );
		if (orio)
			background=new Texture(Gdx.files.internal("resources/oriociaboga.jpg"));
		else
			background=new Texture(Gdx.files.internal("resources/conchatarde.jpg"));
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

		if ( orio ) {
			//1º paso: generar calles de vuelta
			regata.crearCallesVuelta();
			if ( regata.getEquipo().getTrainera().isBuenaCalle() )  {
				//borramos la estrategia para que no salga a nivel de botones
				this.strategies.remove(3);
			}
		}
		
		buttons=new TextButton[this.strategies.size()];
		int i=0;
		for(String strategy : this.strategies){
			buttons[i]=new TextButton( strategy, normalStyle);
			buttons[i].setWidth(300);
			buttons[i].setBounds(0, 0, 300, 60);
			i++;
		}
		Rowing.game.inputMultiplexer.addProcessor(this);
		if(Rowing.game.getScreen()!=null){
			Rowing.game.getScreen().dispose();
		}
		this.regata = regata;
		
	}
	
    public void show()
    {
    	super.show();
        // retrieve the default table actor
        Table table = super.getTable();
        TextButton button;
        System.out.println(buttons.length);
        for(int i=0;i < buttons.length ; i++){
        	final int position = i;
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
    	                Rowing.game.clearProcessors();
    	               
    	                if ( orio ) //si estamos en la vuelta
    	                {
    	                	Timer.instance().clear();
    	                	regata.crearScoreDeVueltaSegunEstrategia( strategies.get(position) );
    	                	Rowing.game.setScreen(new RegattaScreen(Rowing.game, regata, false ));
    	                }
    	                else
    	                {
    	                    regata.crearScoreDeIdaSegunEstrategia( Constants.ESTRATEGIAS_SALIDA.get(position) );
        	                Rowing.game.setScreen(new RegattaScreen(Rowing.game, regata, true ));	
    	                }
    	                
    	               
                	}

                }

            } );
            
        	button.addListener(new ClickListener(){
            	public boolean mouseMoved(InputEvent event,
                        float x,
                        float y){
            		focusedBotton=position+1;
            		if(focusedBotton==position+1){
            			buttons[position].setStyle(focusedStyle);
            		}
            		for(int j=0;j<buttons.length;j++){
            			if(j!=position)
            				buttons[j].setStyle(normalStyle);
            		}
            		return true;
            		
            	}

            });
            table.add( button ).size( 500, 60 ).uniform().spaceBottom( 10 );
            table.row();
        }
        
        if ( orio ) {
            Timer.schedule( new Task() {
				
				public void run() {
			     	regata.crearScoreDeVueltaSegunEstrategia( Constants.ESTRATEGIAS_VUELTA.get(4) );
			     	Timer.instance().clear();
		            Rowing.game.setScreen( new RegattaScreen( game, regata, false ));
				}
			}, Constants.TIEMPO_PARA_ELEGIR_ESTRATEGIA_VUELTA);
            
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
