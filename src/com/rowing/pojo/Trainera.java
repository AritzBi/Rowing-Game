package com.rowing.pojo;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.rowing.core.Constants;
import com.rowing.core.GameSession;

public class Trainera {
	
	private List<Remero> remeros;

	private Patron patron;

	private int potenciaTotal;

	private int energiaTotal;

	private int experienciaTotal;

	private int habilidadBuenaMarTotal;

	private int habilidadMalaMarTotal;
	
	private int tiempoIda;
	
	private int tiempoVuelta;
	
	public String nombre;

	private static int HABILIDAD_BUENA_MAR_PATRON = 2;
	private static int HABILIDAD_MALA_MAR_PATRON = 5;

	// Se almacenara la calle en la que sale la trainera dentro de la
	// competición
	private Map<Integer, String> calle;

	private int score = 0;
	
	private String estrategiaActual;

	public Trainera() {
		remeros = new ArrayList<Remero>();
	}

	public List<Remero> getRemeros() {
		return remeros;
	}

	public void setRemeros(List<Remero> remeros) {
		this.remeros = remeros;
	}

	public Patron getPatron() {
		return patron;
	}

	public void setPatron(Patron patron) {
		this.patron = patron;
	}
	
	public void calcularParametrosDeLaTrainera() {
		calcularPotencialTotal();
		calcularEnergiaTotal();
		calcularExperienciaTotal();
		calcularHabilidadBuenMarTotal();
		calcularHabilidadMalaMarTotal();
	}

	public void calcularPotencialTotal() {
		potenciaTotal = 0;

		for (Remero remero : getRemeros()) {
			potenciaTotal += remero.getPotencia();
		}
		if (potenciaTotal != 0)
			potenciaTotal = (potenciaTotal / remeros.size());
	}

	public void calcularEnergiaTotal() {
		energiaTotal = 0;

		for (Remero remero : getRemeros()) {
			energiaTotal += remero.getEnergia();
		}

		if (energiaTotal != 0)
			energiaTotal = (energiaTotal / remeros.size());
	}

	public void calcularExperienciaTotal() {
		experienciaTotal = 0;

		for (Remero remero : getRemeros()) {
			experienciaTotal += remero.getExperiencia();
		}
		if (patron != null)
			experienciaTotal += patron.getExperiencia();

		int existePatron = patron != null ? 1 : 0;
		if (experienciaTotal != 0)
			experienciaTotal = (experienciaTotal / (remeros.size() + existePatron));
	}

	public void calcularHabilidadBuenMarTotal() {
		habilidadBuenaMarTotal = 0;

		for (Remero remero : getRemeros()) {
			habilidadBuenaMarTotal += remero.getBuena_mar();
		}
		if (patron != null)
			habilidadBuenaMarTotal += (patron.getBuena_mar() * HABILIDAD_BUENA_MAR_PATRON);

		int existePatron = patron != null ? 1 : 0;
		if (habilidadBuenaMarTotal != 0)
			habilidadBuenaMarTotal = (habilidadBuenaMarTotal / (remeros.size() + existePatron));
	}

	public void calcularHabilidadMalaMarTotal() {
		habilidadMalaMarTotal = 0;

		for (Remero remero : getRemeros()) {
			habilidadMalaMarTotal += remero.getMala_mar();
		}
		if (patron != null)
			habilidadMalaMarTotal += (patron.getMala_mar() * HABILIDAD_MALA_MAR_PATRON);

		int existePatron = patron != null ? 1 : 0;
		if (habilidadMalaMarTotal != 0)
			habilidadMalaMarTotal = (habilidadMalaMarTotal / (remeros.size() + existePatron));
	}

	public int getPotenciaTotal() {
		return potenciaTotal;
	}

	public void setPotenciaTotal(int potenciaTotal) {
		this.potenciaTotal = potenciaTotal;
	}

	public int getEnergiaTotal() {
		return energiaTotal;
	}

	public void setEnergiaTotal(int energiaTotal) {
		this.energiaTotal = energiaTotal;
	}

	public int getExperienciaTotal() {
		return experienciaTotal;
	}

	public void setExperienciaTotal(int experienciaTotal) {
		this.experienciaTotal = experienciaTotal;
	}

	public int getHabilidadBuenaMarTotal() {
		return habilidadBuenaMarTotal;
	}

	public void setHabilidadBuenaMarTotal(int habilidadBuenaMarTotal) {
		this.habilidadBuenaMarTotal = habilidadBuenaMarTotal;
	}

	public int getHabilidadMalaMarTotal() {
		return habilidadMalaMarTotal;
	}

	public void setHabilidadMalaMarTotal(int habilidadMalaMarTotal) {
		this.habilidadMalaMarTotal = habilidadMalaMarTotal;
	}

	public Map<Integer, String> getCalle() {
		return calle;
	}

	public void setCalle(Map<Integer, String> calle) {
		this.calle = calle;
	}
	
	public String getEstadoCalle() {
		Set<Integer> calleDeLaTrainera = calle.keySet();
		String estadoCalle = "";
		for (Integer calleAux : calleDeLaTrainera) {
			estadoCalle = calle.get(calleAux);
		}
		return estadoCalle;
	}
	
	public boolean isBuenaCalle () {
		return getEstadoCalle().equals(Constants.CALLE_BUENA);
	}
	
	public boolean isSemiBuenaCalle() {
		return getEstadoCalle().equals(Constants.CALLE_SEMI_BUENA);
	}
	
	public boolean isMalaCalle() {
		return getEstadoCalle().equals(Constants.CALLE_MALA);
	}

	public void calcularScoreTrainera_Ida(String estrategia) {
		//Especificamos la estrategia de la trainera
		estrategiaActual = estrategia;
		int modificadorPotencia = 0;

		if (estrategia.equals(Constants.ESTRATEGIAS_SALIDA.get(0))) {
			modificadorPotencia = 5;
			energiaTotal -= 20;
		} else if (estrategia.equals(Constants.ESTRATEGIAS_SALIDA.get(1))) {
			modificadorPotencia = 2;
			energiaTotal -= 10;
		} else if (estrategia.equals(Constants.ESTRATEGIAS_SALIDA.get(2))) {
			modificadorPotencia = -4;
			energiaTotal += 10;
		}

		Set<Integer> calleDeLaTrainera = calle.keySet();
		String estadoCalle = "";
		for (Integer calleAux : calleDeLaTrainera) {
			estadoCalle = calle.get(calleAux);
		}

		if (estadoCalle.equals(Constants.CALLE_BUENA)) {
			energiaTotal -= 40;
		} else if (estadoCalle.equals(Constants.CALLE_SEMI_BUENA)) {
			energiaTotal -= 45;
		} else {
			energiaTotal -= 50;
		}

		if (GameSession.getInstance().condicionesMeteo.isBuenaMar()) {
			score = potenciaTotal + energiaTotal + experienciaTotal
					+ habilidadBuenaMarTotal + modificadorPotencia;
		} else if (GameSession.getInstance().condicionesMeteo.isMalaMar()) {
			score = potenciaTotal + energiaTotal + experienciaTotal
					+ habilidadMalaMarTotal + modificadorPotencia;
		}
	}

	public int getTiempoIda() {
		//Obtenemos el tiempo que se le corresponde en base a sus puntos
		int tiempoCorrespondiente = ( 570 * score ) / 325;
		//El diferencial se suma al total de 570 segundos
		tiempoIda = (570 - tiempoCorrespondiente ) + 570;
		return tiempoIda;
	}
	
	public void calcularScoreTrainera_Vuelta(String estrategia) {
		estrategiaActual = estrategia;
		int modificadorPotencia = 0;
		
		if (estrategia.equals(Constants.ESTRATEGIAS_VUELTA.get(0))) {
			modificadorPotencia = 5;
			energiaTotal -= 20;
		} else if (estrategia.equals(Constants.ESTRATEGIAS_VUELTA.get(1))) {
			modificadorPotencia = 2;
			energiaTotal -= 10;
		} else if (estrategia.equals(Constants.ESTRATEGIAS_VUELTA.get(2))) {
			modificadorPotencia = -4;
			energiaTotal += 10;
		} else if (estrategia.equals(Constants.ESTRATEGIAS_VUELTA.get(3))) {
			if ( isSemiBuenaCalle() ) {
				energiaTotal -= 5;
				modificadorPotencia = 10;
				//TODO-asimon: cambiar a la calle buena!
			} else if ( isMalaCalle() ) {
				energiaTotal -= 10;
				modificadorPotencia = 2;
			}
		} else if (estrategia.equals(Constants.ESTRATEGIAS_VUELTA.get(4))) //elección del patron
		{
			int experienciaLiderazgoPatron = getPatron().getExperiencia() + getPatron().getLiderazgo();
			int sumarPotencia = 0;
			if ( experienciaLiderazgoPatron >= 180 ) {
				//modificador entre 5 y 8
				sumarPotencia = (int)(Math.random() * 9 + 5);
			}
			else if ( experienciaLiderazgoPatron >= 160 && experienciaLiderazgoPatron < 180 ) {
				//modificador entre 3 y 5
				sumarPotencia = (int)(Math.random() * 6 + 3);
			}
			else if ( experienciaLiderazgoPatron >= 140 && experienciaLiderazgoPatron < 160 ) {
				//modificador entre 1 y 3
				sumarPotencia = (int)(Math.random() *4 + 1);
			}
			else if ( experienciaLiderazgoPatron < 140 ) {
				//modificador entre 0 y 1
				sumarPotencia = (int)(Math.random() *2 + 0);
			}
			modificadorPotencia = sumarPotencia;
		}
		else if (estrategia.equals(Constants.ESTRATEGIAS_VUELTA.get(5))) //ola a favor
		{
			if ( GameSession.getInstance().condicionesMeteo.isMalaMar() ) {
				energiaTotal += 15;
				modificadorPotencia = 4;
			}
			else if ( GameSession.getInstance().condicionesMeteo.isBuenaMar() ) {
				energiaTotal += 5;
				modificadorPotencia = 1;
			}
		}
		
		if (isBuenaCalle()) {
			energiaTotal -= 30;
		} else if (isSemiBuenaCalle()) {
			energiaTotal -= 35;
		} else {
			energiaTotal -= 40;
		}
		
		if (GameSession.getInstance().condicionesMeteo.isBuenaMar()) {
			score = potenciaTotal + energiaTotal + experienciaTotal
					+ habilidadBuenaMarTotal + modificadorPotencia;
		} else if (GameSession.getInstance().condicionesMeteo.isMalaMar()) {
			score = potenciaTotal + energiaTotal + experienciaTotal
					+ habilidadMalaMarTotal + modificadorPotencia;
		}
		
	}
	
	public void setTiempoIda(int tiempoIda) {
		this.tiempoIda = tiempoIda;
	}

	public int getTiempoVuelta() {
		return tiempoVuelta;
	}

	public void setTiempoVuelta(int tiempoVuelta) {
		this.tiempoVuelta = tiempoVuelta;
	}
	
	public String getEstrategiaActual() {
		return estrategiaActual;
	}

	public void setEstrategiaActual(String estrategiaActual) {
		this.estrategiaActual = estrategiaActual;
	}

	public String toString() {
				return 	"\nTrainera " + nombre + "\ncon potencia total --> " + potenciaTotal
				+ "\ncon energia total --> " + energiaTotal + "\ncon experiencia total --> " + experienciaTotal + "\ncon habilidad buena mar --> " + habilidadBuenaMarTotal
				+ "\ncon habilidad mala mar --> " + habilidadMalaMarTotal + "\ncon score --> " + score + "\ncon calle -->" + calle.toString()
				+ "\ncon tiempo de ida --> " + getTiempoIda() + "\ncon estrategia actual --> " + getEstrategiaActual();
	}

}
