package com.rowing.hud;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.rowing.core.Constants;
import com.rowing.pojo.Equipo;


public class TrawlerStats extends Actor {
	
	public Texture container;
	public Texture red_bar;
	public Texture blue_bar;
	public Texture green_bar;
	public Texture orange_bar;
	public Texture yellow_bar;
	public BitmapFont font;
	public Equipo equipo;
	
	public TrawlerStats(Equipo equipo){
		container = new Texture(Gdx.files.internal("resources/container.png"));
		red_bar = new Texture(Gdx.files.internal("resources/red_bar.png"));
		blue_bar = new Texture(Gdx.files.internal("resources/blue_bar.png"));
		green_bar = new Texture(Gdx.files.internal("resources/green_bar.png"));
		orange_bar = new Texture(Gdx.files.internal("resources/orange_bar.png"));
		yellow_bar = new Texture(Gdx.files.internal("resources/brown_bar.png"));
		font = new BitmapFont();
		this.equipo = equipo;
	}
	@Override
	public void draw (SpriteBatch batch, float parentAlpha) {
        equipo.getTrainera().calcularParametrosDeLaTrainera();
		int offset=0;
		float proportion=0;
		//Potencia
		font.setScale(1.3f);
        font.draw(batch,"Potency", getX()-95,getY()-offset +26);
        font.setScale(1f);
		proportion = (float)equipo.getTrainera().getPotenciaTotal()/ (float)Constants.MAX_POTENCY;
        batch.draw(container, getX(),  getY()-offset);
        batch.draw(red_bar, getX() , getY()-offset +8, 0, 0, (int)(blue_bar.getWidth()*proportion), 20);
        font.draw(batch,equipo.getTrainera().getPotenciaTotal() + "/" + Constants.MAX_POTENCY, getX() + blue_bar.getWidth()*0.40f,getY()-offset +25);
        offset+=Constants.STATS_BAR_OFFSET;
        //Experiencia
		font.setScale(1.3f);
        font.draw(batch,"Experience", getX()-95,getY()-offset +26);
        font.setScale(1f);
        proportion = (float)equipo.getTrainera().getExperienciaTotal() / (float)Constants.MAX_EXPERIENCE;
        batch.draw(container, getX(),  getY()-offset);
        batch.draw(orange_bar, getX() , getY()-offset +8, 0, 0, (int)(orange_bar.getWidth()*proportion), 20);
        font.draw(batch,equipo.getTrainera().getExperienciaTotal() + "/" + Constants.MAX_EXPERIENCE, getX() + blue_bar.getWidth()*0.40f,getY()-offset +25);
        offset+=Constants.STATS_BAR_OFFSET;
        //Energia
		font.setScale(1.3f);
        font.draw(batch,"Energy", getX()-95,getY()-offset +26);
        font.setScale(1f);
        proportion = (float)equipo.getTrainera().getEnergiaTotal() / (float)Constants.MAX_ENERGY;
        batch.draw(container, getX(),  getY()-offset);
        batch.draw(green_bar, getX() , getY()-offset +8, 0, 0, (int)(blue_bar.getWidth()*proportion), 20);
        font.draw(batch,equipo.getTrainera().getEnergiaTotal() + "/" + Constants.MAX_ENERGY, getX() + green_bar.getWidth()*0.40f,getY()-offset +25);
        offset+=Constants.STATS_BAR_OFFSET;
        //Buena mar
		font.setScale(1.3f);
        font.draw(batch,"Good Sea", getX()-95,getY()-offset +26);
        font.setScale(1f);
        proportion = (float)equipo.getTrainera().getHabilidadBuenaMarTotal() / (float)Constants.GOOD_SEA;
        batch.draw(container, getX(),  getY()-offset);
        batch.draw(blue_bar, getX() , getY()-offset +8, 0, 0, (int)(blue_bar.getWidth()*proportion), 20);
        font.draw(batch,equipo.getTrainera().getHabilidadBuenaMarTotal() + "/" + Constants.GOOD_SEA, getX() + blue_bar.getWidth()*0.40f,getY()-offset +25);
        offset+=Constants.STATS_BAR_OFFSET;
        //Mala mar
		font.setScale(1.3f);
        font.draw(batch,"Bad Sea", getX()-95,getY()-offset +26);
        font.setScale(1f);
        proportion = (float)equipo.getTrainera().getHabilidadMalaMarTotal() / (float)Constants.BAD_SEA;
        batch.draw(container, getX(),  getY()-offset);
        batch.draw(yellow_bar, getX() , getY()-offset +8, 0, 0, (int)(yellow_bar.getWidth()*proportion), 20);
        font.draw(batch,equipo.getTrainera().getHabilidadMalaMarTotal() + "/" + Constants.BAD_SEA, getX() + blue_bar.getWidth()*0.40f,getY()-offset +25);
	}

}
