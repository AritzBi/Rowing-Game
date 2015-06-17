package com.rowing.pojo;

import com.badlogic.gdx.graphics.g2d.TextureRegion;


public class Patron extends Athlete{

	private String name;
	
	private String surname;
	
	private String birthdate;
	
	private String image;
	
	private TextureRegion icon;
	
	private int mala_mar;
	
	private int buena_mar;
	
	private int experiencia;
	
	private int lesionado;
	
	private int liderazgo;
	
	private boolean used;
	
	public String toString() {
		return  getName()+ " "+getSurname()+"\n"+
			   getBirthdate()+"\n"+
			   "Experience: "+getExperiencia()+"\n"+
			   "Good sea: "+getBuena_mar()+"\n"+
			   "Bad sea: "+getMala_mar()+"\n"+
			   "Leadership: "+getLiderazgo()+"\n"+
			   "Injured: " +isLesionado();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(String birthdate) {
		this.birthdate = birthdate;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public TextureRegion getIcon() {
		return icon;
	}

	public void setIcon(TextureRegion icon) {
		this.icon = icon;
	}

	public int getMala_mar() {
		return mala_mar;
	}

	public void setMala_mar(int mala_mar) {
		this.mala_mar = mala_mar;
	}

	public int getBuena_mar() {
		return buena_mar;
	}

	public void setBuena_mar(int buena_mar) {
		this.buena_mar = buena_mar;
	}

	public int getExperiencia() {
		return experiencia;
	}

	public void setExperiencia(int experiencia) {
		this.experiencia = experiencia;
	}

	public int getLesionado() {
		return lesionado;
	}

	public void setLesionado(int lesionado) {
		this.lesionado = lesionado;
	}
	
	public boolean isLesionado() {
		return (lesionado == 1);
	}

	public int getLiderazgo() {
		return liderazgo;
	}

	public void setLiderazgo(int liderazgo) {
		this.liderazgo = liderazgo;
	}

	public boolean isUsed() {
		return used;
	}

	public void setUsed(boolean used) {
		this.used = used;
	}
	
	
	
}
