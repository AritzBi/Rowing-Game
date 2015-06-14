package com.rowing.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.rowing.core.Constants;
import com.rowing.core.Rowing;
import com.rowing.hud.GameMenu;
import com.rowing.hud.ListRowers;
import com.rowing.hud.TooltipBox;
import com.rowing.hud.TrawlerActor;
import com.rowing.hud.TrawlerSelectionButton;
import com.rowing.hud.TrawlerStats;
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
	private TrawlerActor trawlerActor;
	private GameMenu gameMenu;
	private TrawlerSelectionButton trawlerSelectionButton;
	public TeamSelectionScreen(Rowing game, Equipo equipo) {
		super(game);
		background=new Texture(Gdx.files.internal("resources/concha3.jpg"));
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
		this.gameMenu=new GameMenu(this.skin,game);
		gameMenu.setPosition(Gdx.graphics.getWidth()/2,Gdx.graphics.getHeight()/2+50 );
		gameMenu.setViewport(Gdx.graphics.getHeight());;
		Rowing.game.inputMultiplexer.addProcessor(this);
		if(game.getScreen()!=null){
			game.getScreen().dispose();
		}
		TrawlerStats trawlerStats = new TrawlerStats(equipo);
		this.stage.addActor(trawlerStats);
		trawlerStats.setPosition(200, Gdx.graphics.getHeight()-50);
		ListRowers listRowers = new ListRowers(equipo,this.stage, tooltip);
		this.trawlerActor =new TrawlerActor(equipo,stage);
		this.trawlerActor.setPosition(TrawlerActor.OFFSET_X, TrawlerActor.OFFSET_Y);
		this.stage.addActor(trawlerActor);
		Rowing.game.inputMultiplexer.addProcessor(trawlerActor);
		Rowing.game.inputMultiplexer.addProcessor(listRowers);
		this.stage.addActor(listRowers);
		tooltip.setBounds( Gdx.graphics.getWidth()-(ListRowers.ROWERS_PER_ROW*Constants.SIZE_X*3),Gdx.graphics.getHeight()-200, 200, 200);
		trawlerSelectionButton=new TrawlerSelectionButton(game,skin,equipo);
		Rowing.game.inputMultiplexer.addProcessor(trawlerSelectionButton);
		trawlerSelectionButton.setPosition(700, Gdx.graphics.getHeight()-100);
		this.stage.addActor(trawlerSelectionButton);
		MusicPlayer.play("olasdemar.mp3");
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
	
	public void toogleGameMenu(){
		if(!gameMenu.hasParent()){
			stage.addActor(gameMenu);
			game.inputMultiplexer.addProcessor(gameMenu);
		}else{
			gameMenu.remove();
			game.inputMultiplexer.removeProcessor(gameMenu);
		}
	}
	@Override
	public boolean keyDown(int keycode) {
		if(keycode==Input.Keys.ESCAPE){
			this.toogleGameMenu();
			return true;
		}
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
