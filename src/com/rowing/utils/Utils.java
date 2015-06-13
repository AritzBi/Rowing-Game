package com.rowing.utils;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

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

		especificarLesionados(equipo);
		return equipo;
	}

	private static void especificarLesionados(Equipo equipo) {
		//REMEROS
		List<Integer> remerosLesionados = new ArrayList<Integer>();
		int remeroLesionado = (int) (Math.random() * equipo.getRemeros().size() + 0);
		remerosLesionados.add(remeroLesionado);
		boolean enc = false;
		int remeroLesionadoDos = 0;
		while (!enc) {
			remeroLesionadoDos = (int) (Math.random()
					* equipo.getRemeros().size() + 0);
			if (!remerosLesionados.contains(remeroLesionadoDos))
				enc = true;
		}
		equipo.getRemeros().get(remeroLesionado).setLesionado(1);
		equipo.getRemeros().get(remeroLesionadoDos).setLesionado(1);

		// PATRONES
		int patronLesionado = (int) (Math.random() * 101 + 0);
		if (patronLesionado > 0 && patronLesionado < 21) {
			int seleccionarPatron = (int) (Math.random() * 2 + 0);
			equipo.getPatrones().get(seleccionarPatron).setLesionado(1);
		}
	}

	public static CondicionesMeteo getWeatherDonosti(
			boolean guardarCondicionesEnSession) {
		CondicionesMeteo condiciones = new CondicionesMeteo();

		OpenWeatherMap owm = new OpenWeatherMap("");
		CurrentWeather cwd = null;
		try {
			owm.setUnits(Units.IMPERIAL);
			owm.setApiKey("ecf63f5f77f19982212e116e2e1a5baa");

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
