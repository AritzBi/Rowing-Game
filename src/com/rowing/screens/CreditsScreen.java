package com.rowing.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.rowing.core.Rowing;
import com.rowing.utils.GraphicsLoader;
import com.rowing.utils.MusicPlayer;


public class CreditsScreen extends AbstractScreen{

	public String title;
	public String devs;
	public String devsBody;
	public String graphics;
	public String graphicsBody;
	public String audio;
	public String audioBody;
	public String specialThanks;
	public String testers;
	public String testersBody;
	public String by;
	public String bybody;
	public String devfor;
	public String devforBody;
	public OrthographicCamera cam;
	public BitmapFont font;
	public SpriteBatch batch;
	public float timer, width, height, duration;
	public Texture[] screenshots;
	
	
	public CreditsScreen() {
		super(Rowing.game);
		this.title = "Aupa Orio!";
		this.devs = "Design and Programming";
		this.devsBody = "Iñigo Aramendi Inchauspe @aramendi\n"
				+ "Aritz Bilbao Jayo @aritzbi \n"
				+ "Aitor Simon De Las Heras @aitor_s";
		this.graphics = "Art and Animation";
		this.graphicsBody = "Johannes Sjolund\n"
				+ "Johann Charlot\n"
				+ "Daniel Eddeland\n"
				+ "Artur Reterski\n"
				+ "Dethe Elza\n"
				+ "Skyler Robert Colladay\n"
				+ "Casper Nilsson\n"
				+ "Joe White\n"
				+ "";
		this.audio = "Music and Sound effects";
		this.audioBody = "Oblibion \n"
				+ "Trainera Orio entrenando - Youtube \n";
		this.specialThanks = "Special Thanks";
		this.testers = "To...";
		this.testersBody = "Aimar Rodriguez @Aimarrod\n";
		this.by = "A game by";
		this.bybody = "Iñigo Aramendi, Aritz Bilbao\n"
				+ "and\n"
				+ "Aitor Simon";
		this.devforBody = "developed for Multimedia interactiva y videjuegos at University of Deusto, 2015";
		this.batch = super.getBatch();
		this.font = new Skin(Gdx.files.internal("resources/skin2.json" )).getFont("menuFont");
		
		this.timer = 0f;
		this.duration = 5f;
		
		screenshots = new Texture[]{
			GraphicsLoader.load("credits-screen-1.png"),
			GraphicsLoader.load("credits-screen-2.png"),
			GraphicsLoader.load("credits-screen-3.png"),
			GraphicsLoader.load("credits-screen-4.png"),
			GraphicsLoader.load("credits-screen-5.png"),
			GraphicsLoader.load("credits-screen-6.png"),
			GraphicsLoader.load("credits-screen-7.png"),
			GraphicsLoader.load("credits-screen-8.png"),
			GraphicsLoader.load("credits-screen-9.png"),
			GraphicsLoader.load("credits-screen-10.png")
		};
		
		cam = new OrthographicCamera();
	}
	
	@Override
	public void show(){
		MusicPlayer.play("credits.mp3");
	    cam.position.set(0,0,0);
	    cam.update();
	}
	
	@Override
	public void resize(int width, int height) {
		this.width = width;
		this.height = height;
		float y = cam.position.y;
		cam.setToOrtho(false, width, height);
		cam.position.y = y;
	}
	
	public void render(float delta) {
	       Gdx.gl.glClearColor( 0f, 0f, 0f, 1f );
	       Gdx.gl.glClear( GL20.GL_COLOR_BUFFER_BIT );
	       
	       
	       if(Gdx.input.isKeyPressed(Input.Keys.DOWN)){
		       timer += delta*10;
	       } else {
	    	   timer += delta;
	       }
	       
	       
	       batch.begin();
	       batch.setProjectionMatrix(cam.combined);
	       batch.setColor(1,1,1,1);
	       font.setColor(1,1,1,1);
	       
	       if(timer <= 5.5f){
	    	   setTransparency(5.5f, 0, 1.5f);
		       font.setScale(4f);
		       batch.draw(screenshots[5], width/2 -screenshots[5].getWidth()*0.5f, height/2);
		       font.drawWrapped(batch, title, 100, height/2, width - 100, BitmapFont.HAlignment.CENTER);
	       } else if(timer > 5 && timer <= 12){
	    	   setTransparency(12, 5, 1);
		       batch.draw(screenshots[0], 100, height/2-height/4, width/2.5f, height/2);
		       font.setScale(2f);
		       font.drawWrapped(batch, devs, width/2, height/2 + height/4, width/2, BitmapFont.HAlignment.CENTER);
		       font.setScale(1f);
		       font.drawWrapped(batch, devsBody, width/2, height/2 + 100, width/2, BitmapFont.HAlignment.CENTER);
	       } else if(timer > 13 && timer <= 18){
	    	   setTransparency(18, 13, 1);
		       batch.draw(screenshots[1], width/2, height/2-height/4, width/2.5f, height/2);
		       font.setScale(2f);
		       font.drawWrapped(batch, graphics, 100, height/2 + height/4, width-width/2, BitmapFont.HAlignment.CENTER);
		       font.setScale(1f);
		       font.drawWrapped(batch, graphicsBody, 100, height/2 + 100, width-width/2, BitmapFont.HAlignment.CENTER);
	       }  else if(timer > 19 && timer <= 25){
	    	   setTransparency(25, 19, 1);
		       batch.draw(screenshots[2], 100, height/2-height/4, width/2.5f, height/2);
		       font.setScale(2f);
		       font.drawWrapped(batch, audio, width/2, height/2 + height/4, width/2, BitmapFont.HAlignment.CENTER);
		       font.setScale(1f);
		       font.drawWrapped(batch, audioBody, width/2, height/2 + 100, width/2, BitmapFont.HAlignment.CENTER);
	       } else if(timer > 26 && timer <= 31.5f){
	    	   setTransparency(31.5f, 26, 2);
		       font.setScale(2.5f);
		       batch.draw(screenshots[3], 50, height/2- 100);
		       batch.draw(screenshots[4], width-50-250, height/2 - 100);
		       font.drawWrapped(batch, specialThanks, width/4, height/2, width/2, BitmapFont.HAlignment.CENTER);
	       }  else if(timer > 33 && timer <= 39){
	    	   setTransparency(39, 33, 1.5f);
		       batch.draw(screenshots[9], width/2 - 100, height/2-height/3);
		       batch.draw(screenshots[8], width/2 + 100, height/2);
		       font.setScale(2f);
		       font.drawWrapped(batch, testers, 100, height/2 + height/4, width-width/2, BitmapFont.HAlignment.CENTER);
		       font.setScale(1f);
		       font.drawWrapped(batch, testersBody, 100, height/2 + 100, width-width/2, BitmapFont.HAlignment.CENTER);
	       }   else if(timer > 40 && timer <= 50){
	    	   setTransparency(50, 40, 3.5f);
		       batch.draw(screenshots[7], 50, height/2- 100);
		       batch.draw(screenshots[6], width-50-250, height/2 - 100);
		       font.setScale(1.5f);
		       font.drawWrapped(batch, by, width/4, height/2+height/4, width/2, BitmapFont.HAlignment.CENTER);
		       font.setScale(1f);
		       font.drawWrapped(batch, bybody, width/4, height/2, width/2, BitmapFont.HAlignment.CENTER);
		       font.setScale(0.8f);
		       font.drawWrapped(batch, devforBody, width/4, height/4, width/2, BitmapFont.HAlignment.CENTER);
	       } else if(timer > 52.3f && timer < 53f) {
	    	   MusicPlayer.pause();
	       } else if(timer > 53f){
	    	   game.setScreen(new MenuScreen(game));
	       }
	       
	       
	       batch.end();	       
	       
	       font.setScale(1);

	}
	
	private void setTransparency(float max, float min, float fadeDuration){
		if(timer < min+fadeDuration){
			font.setColor(1,1,1, (timer-min)/fadeDuration);
			batch.setColor(1,1,1, (timer-min)/fadeDuration);
		} else if(timer > max-fadeDuration){
			font.setColor(1,1,1, (max-timer)/fadeDuration);
			batch.setColor(1,1,1, (max-timer)/fadeDuration);
		} else {
			font.setColor(1,1,1,1);
			batch.setColor(1,1,1,1);
		}
	}

}
