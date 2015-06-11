package com.rowing.utils;

import net.aksingh.owmjapis.CurrentWeather.Main;
import net.aksingh.owmjapis.CurrentWeather.Rain;
import net.aksingh.owmjapis.CurrentWeather.Wind;

public class CondicionesMeteo {

	private Rain rain;
	private Wind wind;
	private Main mainConditions;

	public CondicionesMeteo() {
	}

	public Rain getRain() {
		return rain;
	}

	public void setRain(Rain rain) {
		this.rain = rain;
	}

	public Wind getWind() {
		return wind;
	}

	public void setWind(Wind wind) {
		this.wind = wind;
	}

	public Main getMainConditions() {
		return mainConditions;
	}

	public void setMainConditions(Main mainConditions) {
		this.mainConditions = mainConditions;
	}
	
}
