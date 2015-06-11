package com.rowing.utils;

import java.io.FileReader;

import net.aksingh.owmjapis.CurrentWeather;
import net.aksingh.owmjapis.CurrentWeather.Main;
import net.aksingh.owmjapis.CurrentWeather.Rain;
import net.aksingh.owmjapis.CurrentWeather.Wind;
import net.aksingh.owmjapis.OpenWeatherMap;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.google.gson.Gson;
import com.rowing.pojo.Equipo;

public class Utils {

	public static Equipo loadEquipoOrio() {
		Gson gson = new Gson();
		Equipo equipo = null;
		try {
			equipo = gson.fromJson(new FileReader("resources/data/orio.json"),
					Equipo.class);
			for(int i=0;i<equipo.getRemeros().size();i++){
				equipo.getRemeros().get(i).setIcon(new TextureRegion(GraphicsLoader.load("images/"+equipo.getRemeros().get(i).getImage())));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return equipo;
	}
	
	//http://api.openweathermap.org/data/2.5/weather?q=Donostia
	public static CondicionesMeteo getWeatherDonosti() {
		CondicionesMeteo condiciones = new CondicionesMeteo();
		
		OpenWeatherMap owm = new OpenWeatherMap("");
		CurrentWeather cwd = null;
		try {
			cwd = owm.currentWeatherByCityName("Donostia");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		Rain rain = cwd.getRainInstance();
		condiciones.setRain(rain);
		Wind wind = cwd.getWindInstance();
		condiciones.setWind(wind);
		Main main = cwd.getMainInstance();
		condiciones.setMainConditions(main);
		System.out.println(condiciones);
		return condiciones;
	}

	public static void main(String[] args) {
		loadEquipoOrio();
		
		getWeatherDonosti();
	}
}
