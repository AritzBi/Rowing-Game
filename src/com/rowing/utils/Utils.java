package com.rowing.utils;

import java.io.FileReader;

import net.aksingh.owmjapis.CurrentWeather;
import net.aksingh.owmjapis.CurrentWeather.Main;
import net.aksingh.owmjapis.CurrentWeather.Rain;
import net.aksingh.owmjapis.CurrentWeather.Wind;
import net.aksingh.owmjapis.OpenWeatherMap;
import net.aksingh.owmjapis.OpenWeatherMap.Units;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.google.gson.Gson;
import com.rowing.core.GameSession;
import com.rowing.pojo.Equipo;
import com.rowing.pojo.Trainera;

public class Utils {

	public static Equipo loadEquipoOrio() {
		Gson gson = new Gson();
		Equipo equipo = null;
		try {
			equipo = gson.fromJson(new FileReader("resources/data/orio.json"),
					Equipo.class);
			equipo.setTrainera(new Trainera());
			for (int i = 0; i < equipo.getRemeros().size(); i++) {
				equipo.getRemeros()
						.get(i)
						.setIcon(
								new TextureRegion(GraphicsLoader
										.load("images/"
												+ equipo.getRemeros().get(i)
														.getImage())));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return equipo;
	}

	public static CondicionesMeteo getWeatherDonosti(
			boolean guardarCondicionesEnSession) {
		CondicionesMeteo condiciones = new CondicionesMeteo();

		OpenWeatherMap owm = new OpenWeatherMap("");
		CurrentWeather cwd = null;
		try {
			owm.setUnits(Units.IMPERIAL);
			cwd = owm.currentWeatherByCityName("Donosti");
		} catch (Exception e) {
			e.printStackTrace();
		}

		Rain rain = cwd.getRainInstance();
		condiciones.setRain(rain);
		Wind wind = cwd.getWindInstance();
		condiciones.setWind(wind);
		Main main = cwd.getMainInstance();
		condiciones.setMainConditions(main);
		condiciones.calcularParametroBuenaYMalaMar();
		if (guardarCondicionesEnSession) {
			// Si tiramos de este metodo, lo que hacemos es guardar en la sesion
			// las condiciones meteorologicas
			GameSession.setInstance(condiciones);
		}
		return condiciones;
	}

}
