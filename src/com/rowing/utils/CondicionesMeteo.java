package com.rowing.utils;

import net.aksingh.owmjapis.CurrentWeather.Main;
import net.aksingh.owmjapis.CurrentWeather.Rain;
import net.aksingh.owmjapis.CurrentWeather.Wind;

public class CondicionesMeteo {

	private Rain rain;
	private Wind wind;
	private Main mainConditions;

	private boolean buenaMar = false;
	private boolean malaMar = false;

	public CondicionesMeteo() {
		calcularParametroBuenaYMalaMar();
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

	public void calcularParametroBuenaYMalaMar() {
		if (mainConditions.getHumidity() < 75.0 && wind.getWindSpeed() < 22.0) {
			buenaMar = true;
		}
		if (mainConditions.getHumidity() > 75.0 && wind.getWindSpeed() > 22.0) {
			malaMar = true;
		}
		if (mainConditions.getHumidity() > 75.0 && wind.getWindSpeed() < 22.0) {
			// 80% de buena mar, 20% mala mar
		}
		if (mainConditions.getHumidity() < 75.0 && wind.getWindSpeed() > 22.0) {
			// 60% de mala mar, 40% buena mar
		}
	}

	public boolean isBuenaMar() {
		return buenaMar;
	}

	public void setBuenaMar(boolean buenaMar) {
		this.buenaMar = buenaMar;
	}

	public boolean isMalaMar() {
		return malaMar;
	}

	public void setMalaMar(boolean malaMar) {
		this.malaMar = malaMar;
	}
}
