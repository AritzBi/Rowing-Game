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

		// 1º paso: obtenemos el equipo de Orio y lo seteamos sobre la regata
		Equipo equipoOrio = Utils.loadEquipoOrio();
		regata.setEquipo(equipoOrio);
		System.out.println(equipoOrio.getRemeros());
		System.out.println(equipoOrio.getPatrones());
		// 2º paso: creamos la trainera del equipo de Orio (ventana de seleccion
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

		// 3º paso: se obtiene o no con el botón las condiciones meteorológicas
		// de Donosti. ¡¡Hacerlo antes de seleccionar la trainera de Orio!!
		Utils.getWeatherDonosti( true );
		
		System.out.println("\n***CONDICIONES METEOROLOGICAS DE LA REGATA***");
		System.out.println(GameSession.getInstance().condicionesMeteo);

		// 4º paso: se obtienen las traineras competidoras de la trainera Orio
		regata.obtenerTrainerasCompetidoras();

		// 5º paso: se obtienen las calles de la regata y se asignan a cada
		// trainera
		regata.crearCallesYAsignarATraineras();

		// 6º paso: se obtienen los scores de ida para las traineras según la
		// estrategia seleccionada (después de la ventana de seleccionar
		// estrategia)
		regata.crearScoreDeIdaSegunEstrategia(Constants.ESTRATEGIAS_SALIDA
				.get(0));

		// 7º paso: tenemos los tiempos de ida de todas las traineras
		System.out.println("*** CLASIFICACIÓN IDA ****");
		for(Trainera key: regata.getClasificacionIda().keySet() ){
            System.out.println(key.getNombre()  +" :: "+ regata.getClasificacionIda().get(key) );
        }
		
		//[OPERACIONES PARA LA VUELTA: necesitamos el seteo de la estrategia de Orio]
		//8º paso: calcular de nuevo las calles y asignarselas a las traineras
		regata.crearCallesYAsignarATraineras();
		
		//9º paso: calcular las estrategias de vuelta de las traineras [se le pasa como parametro la estrategia seleccionada por Orio]
		//Si el usuario no especifica ninguna, se pondrá la del patrón.
		//Se calculará también el score asociado a cada trainera
		regata.crearScoreDeVueltaSegunEstrategia(Constants.ESTRATEGIAS_VUELTA.get(4));
		
		//10º paso: obtenemos el score de la vuelta
		System.out.println("*** CLASIFICACIÓN VUELTA ****");
		for(Trainera key: regata.getClasificacionVuelta().keySet() ){
            System.out.println(key.getNombre()  +" :: "+ regata.getClasificacionVuelta().get(key) );
        }
		
		//11º paso: obtenemos la clasificacion final de la regata
		System.out.println("*** CLASIFICACIÓN FINAL ****");
		for(Trainera key: regata.getClasificacionFinal().keySet() ){
            System.out.println(key.getNombre()  +" :: "+ regata.getClasificacionFinal().get(key) );
        }
	}

	public void pruebaSinObjetoRegata() {
		// Al entrar en confeccionar equipo, se requerirá consultar el tiempo!
		Utils.getWeatherDonosti( true );

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
			int estrategiaRandom = Utils.generaNumeroAleatorio(0,2);
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
