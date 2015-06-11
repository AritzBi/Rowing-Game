package com.rowing.pojo;

import java.util.ArrayList;
import java.util.List;


public class Equipo {

	public List<Remero> remeros;
	
	public String team_name;
	
	public String team_logo;
	
	public List<Patron> patrones;
	
	public Trainera trainera;
	
	public Equipo () {
		remeros = new ArrayList<Remero>();
		patrones = new ArrayList<Patron>();
		trainera = new Trainera();
	}

	public List<Remero> getRemeros() {
		return remeros;
	}

	public void setRemeros(List<Remero> remeros) {
		this.remeros = remeros;
	}

	public String getTeam_name() {
		return team_name;
	}

	public void setTeam_name(String team_name) {
		this.team_name = team_name;
	}

	public String getTeam_logo() {
		return team_logo;
	}

	public void setTeam_logo(String team_logo) {
		this.team_logo = team_logo;
	}

	public List<Patron> getPatrones() {
		return patrones;
	}

	public void setPatrones(List<Patron> patrones) {
		this.patrones = patrones;
	}
	
}
