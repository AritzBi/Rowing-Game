package com.rowing.utils;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

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
			for (int i = 0; i<equipo.getPatrones().size();i++){
				equipo.getPatrones().get(i).setIcon(new TextureRegion(GraphicsLoader.load("images/"+equipo.getPatrones().get(i).getImage())));
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
		int remeroLesionado = Utils.generaNumeroAleatorio(0, equipo.getRemeros().size()-1);
		remerosLesionados.add(remeroLesionado);
		boolean enc = false;
		int remeroLesionadoDos = 0;
		while (!enc) {
			remeroLesionadoDos = Utils.generaNumeroAleatorio(0, equipo.getRemeros().size()-1);
			if (!remerosLesionados.contains(remeroLesionadoDos))
				enc = true;
		}
		equipo.getRemeros().get(remeroLesionado).setLesionado(1);
		equipo.getRemeros().get(remeroLesionadoDos).setLesionado(1);

		// PATRONES
		int patronLesionado = Utils.generaNumeroAleatorio(0, 100);
		if (patronLesionado > 0 && patronLesionado < 21) {
			int seleccionarPatron = Utils.generaNumeroAleatorio(0, 1);
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
	
	public static int generaNumeroAleatorio(int minimo, int maximo){
        
        int num=(int)Math.floor(Math.random()*(minimo-(maximo+1))+(maximo+1));
        return num;
    }
	
	public static String obtenerMinutosYSegundos ( int segundos ) {
		return String.format("%d min, %d sec", TimeUnit.SECONDS.toMinutes(segundos), segundos - TimeUnit.MINUTES.toSeconds(TimeUnit.SECONDS.toMinutes(segundos)) );
	}

}
