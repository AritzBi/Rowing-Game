package com.rowing.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.rowing.core.Rowing;
import com.rowing.hud.ListRowers;
import com.rowing.hud.TooltipBox;
import com.rowing.pojo.Equipo;
import com.rowing.utils.MusicPlayer;

public class TeamSelectionScreen extends AbstractScreen  implements InputProcessor{
	private Texture background;
	private TextButton []buttons;
	private TextButtonStyle normalStyle;
	private TextButtonStyle focusedStyle;
	private int focusedBotton;
	private Equipo equipo;
	private TooltipBox tooltip;
	public TeamSelectionScreen(Rowing game, Equipo equipo) {
		super(game);
		background=new Texture(Gdx.files.internal("resources/background.jpg"));
		normalStyle=new TextButtonStyle();
		normalStyle.font=getSkin().getFont("buttonFont");
		normalStyle.up=getSkin().getDrawable("normal-button");
		normalStyle.down=getSkin().getDrawable("pushed-button");
		focusedStyle=new TextButtonStyle();
		focusedStyle.font=getSkin().getFont("buttonFont");
		focusedStyle.up=getSkin().getDrawable("focused-button");
		focusedStyle.down=getSkin().getDrawable("pushed-button");
		buttons=new TextButton[4];
		focusedBotton=1;
		this.tooltip = new TooltipBox(this);
		
		Rowing.game.inputMultiplexer.addProcessor(this);
		if(game.getScreen()!=null){
			game.getScreen().dispose();
		}
		ListRowers listRowers = new ListRowers(equipo,this.stage, tooltip);
		Rowing.game.inputMultiplexer.addProcessor(listRowers);
		this.stage.addActor(listRowers);
		System.out.println( Gdx.graphics.getWidth());
		tooltip.setBounds( Gdx.graphics.getWidth()-ListRowers.ROWERS_PER_ROW*ListRowers.SIZE_X-200,0, 200, 200);
		MusicPlayer.play("menu-theme.ogg");
	}

	public void show(){
		
	}
	public void render(float delta) {
		this.delta=delta;
		Gdx.gl.glClearColor( 0f, 0f, 0f, 1f );
		Gdx.gl.glClear( GL20.GL_COLOR_BUFFER_BIT );
		batch.begin();
		batch.draw(background, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		//buttons[focusedBotton-1].setStyle(focusedStyle);
		//Update delta and draw the actors inside the stage
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
		//System.out.println("x"+arg0);
		//System.out.println("y"+arg1);
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
