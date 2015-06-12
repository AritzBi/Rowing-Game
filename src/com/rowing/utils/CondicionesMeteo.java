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
	}
	
	public String toString() {
		String objet = "";
		if ( wind != null )
			objet += "Wind(km/h): " + convertirMilesToKilometers( wind.getWindSpeed() ) + "\n";
		if ( mainConditions != null )
		{
			objet += "Humidity(%): " + mainConditions.getHumidity() + "\n";
			objet += "Temperature(Fahrenheit): " + mainConditions.getTemperature();
		}	
		return objet;
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
		if (mainConditions.getHumidity() < 75.0 && convertirMilesToKilometers(wind.getWindSpeed()) < 22.0) {
			buenaMar = true;
		}
		else if (mainConditions.getHumidity() > 75.0 && convertirMilesToKilometers(wind.getWindSpeed()) > 22.0) {
			malaMar = true;
		}
		else if (mainConditions.getHumidity() > 75.0 && convertirMilesToKilometers(wind.getWindSpeed()) < 22.0) {
			int probabilidad = (int) (Math.random() * 101 + 1);
			if ( probabilidad > 80 ) //Con un 20%, mala mar
				malaMar = true;
			else //con un 80%, buena mar
				buenaMar = true;
		}
		else if (mainConditions.getHumidity() < 75.0 && convertirMilesToKilometers(wind.getWindSpeed()) > 22.0) {
			int probabilidad = (int) (Math.random() * 101 + 1);
			if ( probabilidad > 60 ) //Con un 40%, buena mar
				buenaMar = true;
			else //con un 60%, mala mar
				malaMar = true;
		}
	}

	public float convertirMilesToKilometers(float miles) {
		return (float) (miles * 1.609344);
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
