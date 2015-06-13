package com.rowing.hud;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.rowing.pojo.Equipo;


public class TrawlerStats extends Actor {
	
	public Texture container;
	public Texture red_bar;
	public BitmapFont font;
	public Equipo equipo;
	
	public TrawlerStats(Equipo equipo){
		container = new Texture(Gdx.files.internal("resources/container.png"));
		//red_bar = new Texture(Gdx.files.internal("resources/red_bar.png"));
		font = new BitmapFont();
		this.equipo = equipo;
	}
	@Override
	public void draw (SpriteBatch batch, float parentAlpha) {
        equipo.getTrainera().calcularParametrosDeLaTrainera();
		
        batch.draw(container, getX(),  getY());
		
		/**float proportion = (float)stats.health / (float)stats.maxHealth;
		batch.draw(bars, getX() +3, getY() + 40, 0, 0, (int)(bars.getWidth()*proportion), 20);
		font.draw(batch, stats.health + "/" + stats.maxHealth, getX() + bars.getWidth()*0.40f, getY() + 56);
		proportion = (float)stats.mana / (float)stats.maxMana;
        batch.draw(bars, getX() +3, getY() + 14, 0, 20, (int)(bars.getWidth()*proportion), 20);
        font.draw(batch, stats.mana + "/" + stats.maxMana, getX() + bars.getWidth()*0.40f, getY() + 30);
        proportion=(float)stats.experience/(float)stats.maxExperience;
        batch.draw(bars, getX() +3, getY()-10, 0, 40, (int)(bars.getWidth()*proportion), (int)(13));
        font.setScale(0.9f);
        font.draw(batch, stats.experience + "/" + stats.maxExperience, getX() + bars.getWidth()*0.42f, getY()+5);
        font.setScale(1.0f);**/
	}

}
