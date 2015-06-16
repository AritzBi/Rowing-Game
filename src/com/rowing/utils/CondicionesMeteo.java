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
	
	private float windDefecto;
	private float humidityPorDefecto;

	public CondicionesMeteo() {
	}
	
	public String toString() {
		String objet = "";
		if ( wind != null )
			objet += "Wind(km/h): " + convertirMilesToKilometers( getWindSpeed() ) + "\n";
		if ( mainConditions != null )
		{
			objet += "Humidity(%): " + getHumidity() + "\n";
		}
		objet += "Calm Seas: " + buenaMar + "\n";
		objet += "Dangerous Seas: " + malaMar;
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
	
	public float getWindSpeed() {
		if ( wind != null ) {
			return wind.getWindSpeed();
		} else {
			return windDefecto;
		}
	}

	public void setWind(Wind wind) {
		this.wind = wind;
	}

	public Main getMainConditions() {
		return mainConditions;
	}
	
	public float getHumidity() {
		if ( mainConditions != null ) {
			return mainConditions.getHumidity();
		}
		else {
			return humidityPorDefecto;
		}
	}

	public void setMainConditions(Main mainConditions) {
		this.mainConditions = mainConditions;
	}

	public void calcularParametroBuenaYMalaMar() {
		if (getHumidity() < 75.0 && convertirMilesToKilometers(getWindSpeed()) < 22.0) {
			buenaMar = true;
		}
		else if (getHumidity() > 75.0 && convertirMilesToKilometers(getWindSpeed()) > 22.0) {
			malaMar = true;
		}
		else if (getHumidity() > 75.0 && convertirMilesToKilometers(getWindSpeed()) < 22.0) {
			int probabilidad = Utils.generaNumeroAleatorio(0, 100);
			if ( probabilidad > 80 ) //Con un 20%, mala mar
				malaMar = true;
			else //con un 80%, buena mar
				buenaMar = true;
		}
		else if (getHumidity() < 75.0 && convertirMilesToKilometers(getWindSpeed()) > 22.0) {
			int probabilidad = Utils.generaNumeroAleatorio(0, 100);
			if ( probabilidad > 60 ) //Con un 40%, buena mar
				buenaMar = true;
			else //con un 60%, mala mar
				malaMar = true;
		}
	}

	public static float convertirMilesToKilometers(float miles) {
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

	public float getWindDefecto() {
		return windDefecto;
	}

	public void setWindDefecto(float windDefecto) {
		this.windDefecto = windDefecto;
	}

	public float getHumidityPorDefecto() {
		return humidityPorDefecto;
	}

	public void setHumidityPorDefecto(float humidityPorDefecto) {
		this.humidityPorDefecto = humidityPorDefecto;
	}
	
}
