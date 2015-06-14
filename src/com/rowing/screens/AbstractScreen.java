package com.rowing.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.rowing.core.Rowing;

public class AbstractScreen implements Screen{
	protected final Rowing game;
	protected final Stage stage;
	protected SpriteBatch batch;
	protected Skin skin;
	protected Table table;
	protected float delta;
	public AbstractScreen(Rowing game){
		this.game=game;
		this.batch=new SpriteBatch();
		this.stage=new Stage(0,0,true);
        Rowing.game.inputMultiplexer.addProcessor(stage);
	}
	protected Skin getSkin()
	{
	        if( skin == null ) {
	            skin = new Skin(  Gdx.files.internal( "resources/skin2.json" ) );
	        }
	        return skin;
	    }
    protected Table getTable()
    {
        if( table == null ) {
            table = new Table( getSkin() );
            table.setFillParent( true );
            stage.addActor( table );
        }
        return table;
    }
    public SpriteBatch getBatch()
    {
        if( batch == null ) {
            batch = new SpriteBatch();
        }
        return batch;
    }
    
    public void toogleGameMenu(){
    }
	@Override
	public void render(float delta) {
		this.delta=delta;
		Gdx.gl.glClearColor( 0f, 0f, 0f, 1f );
		Gdx.gl.glClear( GL20.GL_COLOR_BUFFER_BIT );
		batch.begin();
		batch.end();
		//Update delta and draw the actors inside the stage
		stage.act( delta );
		stage.draw();
		
	}

	@Override
	public void resize(int width, int height) {
		stage.setViewport( width, height, true );
	}

	@Override
	public void show() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		stage.dispose();
        if( batch != null ) batch.dispose();
        if( skin != null ) skin.dispose();
	}
	public Rowing getGame() {
		return game;
	}
	public Stage getStage() {
		return stage;
	}
	public void setBatch(SpriteBatch batch) {
		this.batch = batch;
	}
	public void setSkin(Skin skin) {
		this.skin = skin;
	}
	public void setTable(Table table) {
		this.table = table;
	}
	public float getDelta() {
		return delta;
	}
	public void setDelta(float delta) {
		this.delta = delta;
	}

}
