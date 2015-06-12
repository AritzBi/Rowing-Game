package com.rowing.core;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.rowing.logic.BasicLogic;
import com.rowing.pojo.Equipo;
import com.rowing.pojo.Patron;
import com.rowing.pojo.Regata;
import com.rowing.pojo.Remero;
import com.rowing.pojo.Trainera;
import com.rowing.utils.GraphicsLoader;
import com.rowing.utils.Utils;

public class Main {

	public static void pruebaConObjetoRegata() {
		// Inicializamos el objeto regata...
		Regata regata = new Regata();

		// 1� paso: obtenemos el equipo de Orio y lo seteamos sobre la regata
		Equipo equipoOrio = Utils.loadEquipoOrio();
		regata.setEquipo(equipoOrio);

		// 2� paso: creamos la trainera del equipo de Orio (ventana de seleccion
		// de jugadores)
		List<Remero> remeros = equipoOrio.getRemeros();
		List<Patron> patrones = equipoOrio.getPatrones();

		List<Remero> remerosParaTrainera = new ArrayList<Remero>();
		for (int i = 0; i < 13; i++) {
			remerosParaTrainera.add(remeros.get(i));
		}

		Trainera trainera = new Trainera();
		trainera.setPatron(patrones.get(0));
		trainera.setRemeros(remerosParaTrainera);
		// Necesitamos hacer esto al seleccionar todos para que se calculen los
		// parametros de la trainera!!
		trainera.calcularParametrosDeLaTrainera();
		// Setteamos la trainera sobre el equipo de Orio que esta a nivel de la
		// regata
		equipoOrio.setTrainera(trainera);

		// 3� paso: se obtiene o no con el bot�n las condiciones meteorol�gicas
		// de Donosti
		Utils.getWeatherDonosti();

		// 4� paso: se obtienen las traineras competidoras de la trainera Orio
		regata.obtenerTrainerasCompetidoras();

		// 5� paso: se obtienen las calles de la regata y se asignan a cada
		// trainera
		regata.crearCallesYAsignarATraineras();

		// 6� paso: se obtienen los scores de ida para las traineras seg�n la
		// estrategia seleccionada (despu�s de la ventana de seleccionar
		// estrategia)
		regata.crearScoreDeIdaSegunEstrategia(Constants.ESTRATEGIAS_SALIDA
				.get(0));

		// 7� paso: tenemos los tiempos de ida de todas las traineras
		System.out.println(regata.toString());
	}

	public void pruebaSinObjetoRegata() {
		// Al entrar en confeccionar equipo, se requerir� consultar el tiempo!
		Utils.getWeatherDonosti();

		GraphicsLoader.initialize();
		Equipo equipoOrio = Utils.loadEquipoOrio();

		List<Remero> remeros = equipoOrio.getRemeros();
		List<Patron> patrones = equipoOrio.getPatrones();

		List<Remero> remerosParaTrainera = new ArrayList<Remero>();
		for (int i = 0; i < 13; i++) {
			remerosParaTrainera.add(remeros.get(i));
		}

		Trainera trainera = new Trainera();
		trainera.setPatron(patrones.get(0));
		trainera.setRemeros(remerosParaTrainera);
		trainera.calcularParametrosDeLaTrainera();

		List<Trainera> trainerasCompetidoras = BasicLogic
				.obtenerTraineras(trainera);
		Map<Integer, String> calles = BasicLogic.obtenerCalles();
		BasicLogic.asignarCallesATraineras(trainera, trainerasCompetidoras,
				calles);

		// Seteamos por "defecto" una estrategia para la trainera de Orio
		String estrategia_Orio = Constants.ESTRATEGIAS_SALIDA.get(0);
		trainera.calcularScoreTrainera_Ida(estrategia_Orio);

		for (Trainera traineraAux : trainerasCompetidoras) {
			int estrategiaRandom = (int) (Math.random() * 3 + 0);
			traineraAux.calcularScoreTrainera_Ida(Constants.ESTRATEGIAS_SALIDA
					.get(estrategiaRandom));
		}

		System.out.println("*** TRAINERA ORIO ***");
		System.out.println(trainera.toString());

		System.out.println("**** TRAINERAS COMPETIDORAS ***");
		System.out.println(trainerasCompetidoras.get(0).toString());
		System.out.println(trainerasCompetidoras.get(1).toString());
		System.out.println(trainerasCompetidoras.get(2).toString());
	}

	public static void main(String[] args) {
		pruebaConObjetoRegata();
	}

}
