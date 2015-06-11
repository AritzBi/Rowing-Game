package com.rowing.pojo;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.rowing.utils.GraphicsLoader;

public class Remero {

	private String name;

	private String surname;

	private String birthdate;

	private String image;
	
	public TextureRegion icon;
	

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
	
	public String toString(){
		return name+" "+surname+" "+" "+birthdate+" "+" "+image;
	}
	
	

}
