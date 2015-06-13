package com.rowing.pojo;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.rowing.core.Constants;
import com.rowing.logic.BasicLogic;

public class Regata {

	// Guarda el equipo de Orio del juego
	private Equipo equipo;
	// Guarda las traineras competidoras
	private List<Trainera> trainerasCompetidoras;
	// Calles de la regata
	private Map<Integer, String> calles;

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
	}

	public Map<Integer, String> getCalles() {
		return calles;
	}

	public void setCalles(Map<Integer, String> calles) {
		this.calles = calles;
	}

	public void obtenerTrainerasCompetidoras() {
		this.trainerasCompetidoras = BasicLogic.obtenerTraineras(equipo
				.getTrainera());
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
	 * Método que crea los scores asociados a las traineras competidoras y la trainera de Orio
	 * ¡Después del método crearCallesYAsignarATraineras!
	 * @param estrategia
	 */
	public void crearScoreDeIdaSegunEstrategia(String estrategia) {
		// Calculamos el score de la trainera de Orio
		equipo.getTrainera().calcularScoreTrainera_Ida(estrategia);

		// Calculamos el score de las traineras competidores con estrategias
		// aleatorias
		for (Trainera trainera : getTrainerasCompetidoras()) {
			
			int estrategiaRandom = 0;
			if ( trainera.isBuenaCalle() ) {
				estrategiaRandom = (int) (Math.random() * 3 + 0);
			}
			else if ( trainera.isSemiBuenaCalle() ) {
				estrategiaRandom = (int) (Math.random() * 2 + 0);
			}
			else if ( trainera.isMalaCalle() ) {
				estrategiaRandom = (int) (Math.random() * 3 + 1);
			}
			trainera.calcularScoreTrainera_Ida(Constants.ESTRATEGIAS_SALIDA
					.get(estrategiaRandom));
		}
	}
	
	//TODO-asimon:No se le puede asignar la estrategia "Change to the best path" a la trainera que vaya por la calle buena!!
	public void crearScoreDeVueltaSegunEstrategia(String estrategia) {
		
	}
	
	public String toString () {
		return "Regata con TRAINERA ORIO --> " + equipo.getTrainera().toString() + 
				"\ncon TRAINERAS COMPETIDORAS --> " + trainerasCompetidoras.toString();
	}

}
