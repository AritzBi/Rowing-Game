package com.rowing.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.NinePatchDrawable;
import com.rowing.core.GameSession;
import com.rowing.core.Rowing;
import com.rowing.pojo.Equipo;
import com.rowing.utils.CondicionesMeteo;
import com.rowing.utils.MusicPlayer;

public class ClimateScreen extends AbstractScreen implements InputProcessor {
	private Texture background;
	private Rowing game;
	private Equipo equipo;
	private CondicionesMeteo condicionesMeteo;
	private TextButton backToMenu;
	private TextButtonStyle normalStyle;
	private TextButtonStyle focusedStyle;

	public ClimateScreen(Rowing game, Equipo equipo) {
		super(game);
		background = new Texture(Gdx.files.internal("resources/farotiempo.jpg"));
		MusicPlayer.play("wind.mp3");
		this.game = game;
		this.equipo = equipo;
		// conseguimos las condiciones meteorologicas de la sesi�n del juego
		this.condicionesMeteo = GameSession.getInstance().condicionesMeteo;
	}

	public void render(float delta) {
		this.delta = delta;
		Gdx.gl.glClearColor(0f, 0f, 0f, 1f);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		batch.draw(background, 0, 0, Gdx.graphics.getWidth(),
				Gdx.graphics.getHeight());
		// Update delta and draw the actors inside the stage
		batch.end();
		stage.act(delta);
		stage.draw();
	}

	@Override
	public void show() {
		super.show();
		Table table = super.getTable();
		table.setFillParent(false);
		table.setBounds(300, 200, 700, 400);
		table.setBackground(new NinePatchDrawable(getNinePatch(("resources/dialog-box.png"))));
		
		normalStyle=new TextButtonStyle();
		normalStyle.font=skin.getFont("buttonFont");
		normalStyle.up=skin.getDrawable("normal-button");
		normalStyle.down=skin.getDrawable("pushed-button");
		
		focusedStyle=new TextButtonStyle();
		focusedStyle.font=skin.getFont("buttonFont");
		focusedStyle.up=skin.getDrawable("focused-button");
		focusedStyle.down=skin.getDrawable("pushed-button");
		
		backToMenu = new TextButton("Go Back", normalStyle);
		
		backToMenu.addListener(new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
				return true;
			}

			@Override
			public void touchUp(InputEvent event, float x, float y,
					int pointer, int button) {
				if (button == 0) {
					Rowing.game.clearProcessors();
					Rowing.game
							.setScreen(new TeamSelectionScreen(game, equipo));
				}
			}

		});
		
		backToMenu.addListener(new ClickListener(){
        	public boolean mouseMoved(InputEvent event,
                    float x,
                    float y){;
        		backToMenu.setStyle(focusedStyle);
        		
        		return true;
        		
        	}

        });
		
		especificarTiempoEnTabla(table);
		table.row();
		table.add( backToMenu ).size( 300, 60 ).uniform().spaceBottom( 10 ).colspan(2).spaceTop(20);
		

	};

	public void especificarTiempoEnTabla(Table table) {
		Label windSpeed = new Label("Wind Speed (km/h) ", skin);
		TextField windSpeedText = new TextField(String.format("%.1f",CondicionesMeteo
				.convertirMilesToKilometers(condicionesMeteo.getWind()
						.getWindSpeed())), skin);
		Label humidity = new Label("Humidity (%) ", skin);
		TextField humidityText = new TextField(String.format("%.1f",condicionesMeteo
				.getMainConditions().getHumidity()), skin);
		Label goodSea = new Label("Good Sea ", skin);
		TextField goodSeaText = new TextField(
				condicionesMeteo.isBuenaMar() ? "Yes" : "No", skin);
		Label badSea = new Label("Bad Sea ", skin);
		TextField badSeaText = new TextField(
				condicionesMeteo.isMalaMar() ? "Yes" : "No", skin);

		table.add(windSpeed);
		table.add(windSpeedText).pad(10);
		table.row();
		table.add(humidity);
		table.add(humidityText).pad(10);
		table.row();
		table.add(goodSea);
		table.add(goodSeaText).pad(10);
		table.row();
		table.add(badSea);
		table.add(badSeaText).pad(10);

	}
	
	private NinePatch getNinePatch(String fname) {
	    
		final Texture t = new Texture(Gdx.files.internal(fname));
	    return new NinePatch( new TextureRegion(t, 1, 1 , t.getWidth() - 2, t.getHeight() - 2), 10, 10, 10, 10);
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
