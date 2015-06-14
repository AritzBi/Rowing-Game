package com.rowing.pojo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import com.rowing.core.Constants;
import com.rowing.core.GameSession;
import com.rowing.logic.BasicLogic;
import com.rowing.utils.Utils;

public class Regata {

	// Guarda el equipo de Orio del juego
	private Equipo equipo;
	// Guarda las traineras competidoras
	private List<Trainera> trainerasCompetidoras;
	// Calles de la regata
	public static Map<Integer, String> calles;

	public Regata() {
		equipo = new Equipo();
		trainerasCompetidoras = new ArrayList<Trainera>();
	}

	public Equipo getEquipo() {
		return equipo;
	}

	public void setEquipo(Equipo equipo) {
		this.equipo = equipo;
	}

	public List<Trainera> getTrainerasCompetidoras() {
		return trainerasCompetidoras;
	}

	public void setTrainerasCompetidoras(List<Trainera> trainerasCompetidoras) {
		this.trainerasCompetidoras = trainerasCompetidoras;
		System.out.println(this.trainerasCompetidoras);
	}

	public Map<Integer, String> getCalles() {
		return calles;
	}

	public void setCalles(Map<Integer, String> calles) {
		this.calles = calles;
	}

	public static Integer getCalleBuena() {
		for (Integer numeroCalle : calles.keySet()) {
			if (calles.get(numeroCalle).equals(Constants.CALLE_BUENA)) {
				return numeroCalle;
			}
		}
		return null;
	}

	public void obtenerTrainerasCompetidoras() {
		this.trainerasCompetidoras = BasicLogic.obtenerTraineras(equipo
				.getTrainera());
	}
	
	public void crearCallesVuelta() {
		this.calles = BasicLogic.obtenerCalles();
		//Modificamos el estado de las calles que tienen asociadas las traineras
		Map<Integer,String> nuevaCalle = new HashMap<Integer, String>();
		int numeroCalleOrio = getEquipo().getTrainera().getNumeroCalle();
		nuevaCalle.put(numeroCalleOrio, calles.get(numeroCalleOrio) );
		//Actualizamos el valor de orio
		getEquipo().getTrainera().setCalle(nuevaCalle);
		//Actualizamos los valores de las traineras competidoras
		for ( Trainera traineraAux: getTrainerasCompetidoras() ) {
			nuevaCalle = new HashMap<Integer, String>();
			nuevaCalle.put( traineraAux.getNumeroCalle(), calles.get(traineraAux.getNumeroCalle()) );
			traineraAux.setCalle(nuevaCalle);
		}
	}

	/**
	 * Método que crea las calles de la regata y asigna a las traineras calles
	 * ¡Se debe llamar cuando Equipo y List<Trainera> estén rellenos!
	 */
	public void crearCallesYAsignarATraineras() {
		this.calles = BasicLogic.obtenerCalles();
		BasicLogic.asignarCallesATraineras(equipo.getTrainera(),
				trainerasCompetidoras, calles);
	}

	/**
	 * Método que crea los scores asociados a las traineras competidoras y la
	 * trainera de Orio ¡Después del método crearCallesYAsignarATraineras!
	 * 
	 * @param estrategia
	 */
	public void crearScoreDeIdaSegunEstrategia(String estrategia) {
		// Calculamos el score de la trainera de Orio
		equipo.getTrainera().calcularScoreTrainera_Ida(estrategia);

		// Calculamos el score de las traineras competidores con estrategias
		// aleatorias
		for (Trainera trainera : getTrainerasCompetidoras()) {

			int estrategiaRandom = 0;
			if (trainera.isBuenaCalle()) {
				estrategiaRandom = Utils.generaNumeroAleatorio(0, 2);
			} else if (trainera.isSemiBuenaCalle()) {
				estrategiaRandom = Utils.generaNumeroAleatorio(0, 1);
			} else if (trainera.isMalaCalle()) {
				estrategiaRandom = Utils.generaNumeroAleatorio(1, 2);
			}
			trainera.calcularScoreTrainera_Ida(Constants.ESTRATEGIAS_SALIDA
					.get(estrategiaRandom));
		}
	}

	public Map<Trainera, Integer> getClasificacionIda() {
		Map<Trainera,Integer> clasificacionIda =
                new TreeMap<Trainera,Integer>( trainerasCompetidoras.get(0).new OrdenarPorTiempoIda() );
		
		for (Trainera traineraAux : getTrainerasCompetidoras()) {
			clasificacionIda.put(traineraAux, new Integer ( traineraAux.getTiempoIda() ) );
		}
		clasificacionIda.put(equipo.getTrainera(), new Integer ( equipo.getTrainera()
				.getTiempoIda() ) );

		return clasificacionIda;
	}

	public Map<Trainera, Integer> getClasificacionVuelta() {
		Map<Trainera,Integer> clasificacioNVuelta =
                new TreeMap<Trainera,Integer>( trainerasCompetidoras.get(0).new OrdenarPorTiempoVuelta() );
		
		for (Trainera traineraAux : getTrainerasCompetidoras()) {
			clasificacioNVuelta.put(traineraAux, traineraAux.getTiempoVuelta());
		}
		clasificacioNVuelta.put(equipo.getTrainera(), equipo.getTrainera()
				.getTiempoVuelta());

		return clasificacioNVuelta;
	}

	public Map<Trainera, Integer> getClasificacionFinal() {
		Map<Trainera,Integer> clasificacioNFinal =
                new TreeMap<Trainera,Integer>( trainerasCompetidoras.get(0).new OrdenarPorTiempoTotal() );
		for (Trainera traineraAux : getTrainerasCompetidoras()) {
			clasificacioNFinal.put(traineraAux, traineraAux.getTiempoVuelta()
					+ traineraAux.getTiempoIda());
		}
		clasificacioNFinal.put(equipo.getTrainera(), equipo.getTrainera()
				.getTiempoVuelta() + equipo.getTrainera().getTiempoVuelta());

		return clasificacioNFinal;
	}

	public void crearScoreDeVueltaSegunEstrategia(String estrategia) {

		equipo.getTrainera().calcularScoreTrainera_Vuelta(estrategia);
		
		for (Trainera trainera : getTrainerasCompetidoras()) {
			int estrategiaElegida = 0;
			String estrategiaSeleccionada = "";
			if (trainera.isMalaCalle() && trainera.getEnergiaTotal() < 60) {
				// coge siempre la estrategia 4 de vuelta!!
				estrategiaSeleccionada = Constants.ESTRATEGIAS_VUELTA.get(3);
			}
			else if ( trainera.isMalaCalle() && trainera.getEnergiaTotal() >= 60 ) {
				estrategiaSeleccionada = Constants.ESTRATEGIAS_VUELTA.get(0);
			} else if (trainera.isBuenaCalle()
					&& GameSession.getInstance().condicionesMeteo.isBuenaMar()) {
				if ( trainera.getEnergiaTotal() >= 50 )
				{
					// coge o la estrategia 1 o 5
					estrategiaElegida = Utils.generaNumeroAleatorio(0, 1);
					if (estrategiaElegida == 0)
						estrategiaSeleccionada = Constants.ESTRATEGIAS_VUELTA.get(0);
					else
						estrategiaSeleccionada = Constants.ESTRATEGIAS_VUELTA.get(4);
				}
				else
				{
					//coge o estrategia 2,3 o 5
					estrategiaElegida = Utils.generaNumeroAleatorio(0, 2);
					if (estrategiaElegida == 0)
						estrategiaSeleccionada = Constants.ESTRATEGIAS_VUELTA.get(1);
					else if ( estrategiaElegida == 1)
						estrategiaSeleccionada = Constants.ESTRATEGIAS_VUELTA.get(2);
					else
						estrategiaSeleccionada = Constants.ESTRATEGIAS_VUELTA.get(4);
				}
			} else if (trainera.isBuenaCalle()
					&& GameSession.getInstance().condicionesMeteo.isMalaMar()) {
				if ( trainera.getEnergiaTotal() >= 50 )
				{
					// coge o la estrategia 1, 5 o 6
					estrategiaElegida = Utils.generaNumeroAleatorio(0, 2);
					if (estrategiaElegida == 0)
						estrategiaSeleccionada = Constants.ESTRATEGIAS_VUELTA.get(0);
					else if (estrategiaElegida == 1)
						estrategiaSeleccionada = Constants.ESTRATEGIAS_VUELTA.get(4);
					else
						estrategiaSeleccionada = Constants.ESTRATEGIAS_VUELTA.get(5);
				}
				else
				{
					//coge o la estrategia 2,5 y 6
					estrategiaElegida = Utils.generaNumeroAleatorio(0, 2);
					if (estrategiaElegida == 0)
						estrategiaSeleccionada = Constants.ESTRATEGIAS_VUELTA.get(1);
					else if ( estrategiaElegida == 1)
						estrategiaSeleccionada = Constants.ESTRATEGIAS_VUELTA.get(4);
					else
						estrategiaSeleccionada = Constants.ESTRATEGIAS_VUELTA.get(5);
				}
			} else if (trainera.isSemiBuenaCalle()
					&& trainera.getEnergiaTotal() > 45
					&& GameSession.getInstance().condicionesMeteo.isBuenaMar()) {
				// coge o la estrategia 1,2,4 y 5
				if ( trainera.getEstrategiaActual().equals(Constants.ESTRATEGIAS_SALIDA.get(0)) 
						&& trainera.getEnergiaTotal() < 40 ) {
					estrategiaElegida = Utils.generaNumeroAleatorio(1, 3);
				}
				else
				{
					estrategiaElegida = Utils.generaNumeroAleatorio(0, 3);
				}
				if (estrategiaElegida == 0)
					estrategiaSeleccionada = Constants.ESTRATEGIAS_VUELTA.get(0);
				else if (estrategiaElegida == 1)
					estrategiaSeleccionada = Constants.ESTRATEGIAS_VUELTA.get(1);
				else if (estrategiaElegida == 2)
					estrategiaSeleccionada = Constants.ESTRATEGIAS_VUELTA.get(3);
				else
					estrategiaSeleccionada = Constants.ESTRATEGIAS_VUELTA.get(4);
			} else if (trainera.isSemiBuenaCalle()
					&& trainera.getEnergiaTotal() <= 45
					&& GameSession.getInstance().condicionesMeteo.isBuenaMar()) {
				// coge o estrategia 2,3,4 o 5
				estrategiaElegida = Utils.generaNumeroAleatorio(2, 5);
				estrategiaSeleccionada = Constants.ESTRATEGIAS_VUELTA.get(estrategiaElegida - 1);
			} else if (trainera.isSemiBuenaCalle()
					&& trainera.getEnergiaTotal() > 45
					&& GameSession.getInstance().condicionesMeteo.isMalaMar()) {
				// coge o la estrategia 1,2,4,5 o 6
				estrategiaElegida = Utils.generaNumeroAleatorio(0, 4);
				if (estrategiaElegida == 0)
					estrategiaSeleccionada = Constants.ESTRATEGIAS_VUELTA
							.get(0);
				else if (estrategiaElegida == 1)
					estrategiaSeleccionada = Constants.ESTRATEGIAS_VUELTA
							.get(1);
				else if (estrategiaElegida == 2)
					estrategiaSeleccionada = Constants.ESTRATEGIAS_VUELTA
							.get(3);
				else if (estrategiaElegida == 3)
					estrategiaSeleccionada = Constants.ESTRATEGIAS_VUELTA
							.get(4);
				else
					estrategiaSeleccionada = Constants.ESTRATEGIAS_VUELTA
							.get(5);
			} else if (trainera.isSemiBuenaCalle()
					&& trainera.getEnergiaTotal() <= 45
					&& GameSession.getInstance().condicionesMeteo.isMalaMar()) {
				// coge o estrategia 2,3,4,5,6
				estrategiaElegida = Utils.generaNumeroAleatorio(2, 6);
				estrategiaSeleccionada = Constants.ESTRATEGIAS_VUELTA
						.get(estrategiaElegida - 1);
			}
			trainera.calcularScoreTrainera_Vuelta(estrategiaSeleccionada);
		}
	}

	public String toString() {
		return "Regata con TRAINERA ORIO --> "
				+ equipo.getTrainera().toString()
				+ "\ncon TRAINERAS COMPETIDORAS --> "
				+ trainerasCompetidoras.toString();
	}

}
