package com.rowing.pojo;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.rowing.core.Constants;
import com.rowing.core.GameSession;
import com.rowing.utils.Utils;

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
	
	private TextureRegion icon;

	private static int HABILIDAD_BUENA_MAR_PATRON = 2;
	private static int HABILIDAD_MALA_MAR_PATRON = 5;

	// Se almacenara la calle en la que sale la trainera dentro de la
	// competición
	private Map<Integer, String> calle;

	private int score = 0;
	//Hablamos de la estrategia de ida de la trainera
	private String estrategiaActual;
	private String estrategiaVuelta;
	
	private float position_x;
	private float position_y;
	
	private float velocity_x;
	private float velocity_y;

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
	
	public int getTiempoTotal() {
		return tiempoIda + tiempoVuelta;
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
	
	public Integer getNumeroCalle() {
		Set<Integer> calleDeLaTrainera = calle.keySet();
		Integer numeroCalle = null;
		for (Integer numeroCalleAux : calleDeLaTrainera) {
			numeroCalle = numeroCalleAux;
		}
		return numeroCalle;
	}
	
	public String getEstadoCalle() {
		Set<Integer> calleDeLaTrainera = calle.keySet();
		String estadoCalle = "";
		for (Integer calleAux : calleDeLaTrainera) {
			estadoCalle = calle.get(calleAux);
		}
		return estadoCalle;
	}
	
	public String getEstadoEnIngles() {
		if ( getEstadoCalle().equals(Constants.CALLE_BUENA) )
			return Constants.CALLE_BUENA_EN;
		else if ( getEstadoCalle().equals(Constants.CALLE_SEMI_BUENA))
			return Constants.CALLE_SEMI_BUENA_EN;
		else if ( getEstadoCalle().equals(Constants.CALLE_MALA))
			return Constants.CALLE_MALA_EN;
		return null;
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

	public String getEstrategiaVuelta() {
		return estrategiaVuelta;
	}

	public void setEstrategiaVuelta(String estrategiaVuelta) {
		this.estrategiaVuelta = estrategiaVuelta;
	}

	public void calcularScoreTrainera_Ida(String estrategia) {
		//Especificamos la estrategia de la trainera
		estrategiaActual = estrategia;
		int modificadorPotencia = 0;

		if (estrategia.equals(Constants.ESTRATEGIAS_SALIDA.get(0))) //Come up with Maximum Power and Keep Up
		{
			modificadorPotencia = 25;
			energiaTotal -= 20;
		} else if (estrategia.equals(Constants.ESTRATEGIAS_SALIDA.get(1))) //Come up with Maximum Power and Maintain A Stable Rhythm
		{
			modificadorPotencia = 12;
			energiaTotal -= 10;
		} else if (estrategia.equals(Constants.ESTRATEGIAS_SALIDA.get(2))) //Come up with conservative way and reserve forces
		{
			modificadorPotencia = -8;
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
		int tiempoCorrespondiente = ( 570 * score ) / 350;
		//El diferencial se suma al total de 570 segundos
		tiempoIda = (570 - tiempoCorrespondiente ) + 570;
		return tiempoIda;
	}
	
	public int getTiempoVuelta() {
		//Obtenemos el tiempo que se le corresponde en base a sus puntos
		int tiempoCorrespondiente = ( 570 * score ) / 344;
		//El diferencial se suma al total de 570 segundos
		tiempoVuelta = (570 - tiempoCorrespondiente ) + 570;
		return tiempoVuelta;
	}
	
	public void calcularScoreTrainera_Vuelta(String estrategia) {
		estrategiaVuelta = estrategia;
		int modificadorPotencia = 0;
		
		if (estrategia.equals(Constants.ESTRATEGIAS_VUELTA.get(0))) //Come up with Maximum Power and Keep Up
		{
			modificadorPotencia = 25;
			energiaTotal -= 20;
			//Si selecciona dos veces esta estrategia..
			if ( estrategiaActual.equals(estrategia) )
			{
				energiaTotal -= 15;
			}
		} else if (estrategia.equals(Constants.ESTRATEGIAS_VUELTA.get(1))) //Come up with Maximum Power and Maintain A Stable Rhythm
		{
			modificadorPotencia = 12;
			energiaTotal -= 10;
		} else if (estrategia.equals(Constants.ESTRATEGIAS_VUELTA.get(2))) //Come up with conservative way and reserve forces
		{
			modificadorPotencia = -8;
			energiaTotal += 10;
			//Si selecciona dos veces esta estrategia..
			if ( estrategiaActual.equals(estrategia) ) {
				modificadorPotencia += -10;
			}
		} else if (estrategia.equals(Constants.ESTRATEGIAS_VUELTA.get(3))) //cambiar a la mejor calle
		{
			int modificadorPotenciaRandom = 0;
			if ( isSemiBuenaCalle() ) {
				energiaTotal -= 10;
				modificadorPotenciaRandom = Utils.generaNumeroAleatorio(10, 14);
			} else if ( isMalaCalle() ) {
				energiaTotal -= 15;
				modificadorPotenciaRandom = Utils.generaNumeroAleatorio(15, 17);
			}
			modificadorPotencia = modificadorPotenciaRandom;
			//Se le asigna a la trainera la calle buena!!
			calle.clear();
			calle.put(Regata.getCalleBuena(), Constants.CALLE_BUENA);
		} else if (estrategia.equals(Constants.ESTRATEGIAS_VUELTA.get(4))) //elección del patron
		{
			int experienciaLiderazgoPatron = getPatron().getExperiencia() + getPatron().getLiderazgo();
			int sumarPotencia = 0;
			int energiaRestar = Utils.generaNumeroAleatorio(0, 1);
			if ( experienciaLiderazgoPatron >= 180 ) {
				//modificador entre 4 y 8
				sumarPotencia = Utils.generaNumeroAleatorio(4, 8);
			}
			else if ( experienciaLiderazgoPatron >= 160 && experienciaLiderazgoPatron < 180 ) {
				//modificador entre 2 y 5
				sumarPotencia = Utils.generaNumeroAleatorio(2, 5);
			}
			else if ( experienciaLiderazgoPatron >= 140 && experienciaLiderazgoPatron < 160 ) {
				//modificador entre 1 y 3
				sumarPotencia = Utils.generaNumeroAleatorio(1, 3);
			}
			else if ( experienciaLiderazgoPatron < 140 ) {
				//modificador entre 0 y 1
				sumarPotencia = Utils.generaNumeroAleatorio(0, 1);
			}
			energiaTotal -= energiaRestar;
			modificadorPotencia = sumarPotencia;
		}
		else if (estrategia.equals(Constants.ESTRATEGIAS_VUELTA.get(5))) //ola a favor
		{
			if ( GameSession.getInstance().condicionesMeteo.isMalaMar() ) {
				//random energia 4 y 10
				int energiaSumar = Utils.generaNumeroAleatorio(4, 10);
				energiaTotal += energiaSumar;
				//random modificador potencia 1 y 4
				int potencia = Utils.generaNumeroAleatorio(1, 4);
				modificadorPotencia = potencia;
			}
			else if ( GameSession.getInstance().condicionesMeteo.isBuenaMar() ) {
				//random energia 0 y 2
				int energiaSumar = Utils.generaNumeroAleatorio(0, 2);
				energiaTotal += energiaSumar;
				//random modificador potencia 0 y 2
				int potencia = Utils.generaNumeroAleatorio(0, 2);
				modificadorPotencia = potencia;
			}
		}
		
		if (isBuenaCalle()) {
			energiaTotal -= 30;
		} else if (isSemiBuenaCalle()) {
			energiaTotal -= 35;
		} else {
			energiaTotal -= 40;
		}
		
		int modificadorPenalizacion = 0;
		if ( energiaTotal < 0 ) {
			modificadorPenalizacion = energiaTotal;
		}
		
		if (GameSession.getInstance().condicionesMeteo.isBuenaMar()) {
			score = potenciaTotal + energiaTotal + experienciaTotal
					+ habilidadBuenaMarTotal + modificadorPotencia + modificadorPenalizacion;
		} else if (GameSession.getInstance().condicionesMeteo.isMalaMar()) {
			score = potenciaTotal + energiaTotal + experienciaTotal
					+ habilidadMalaMarTotal + modificadorPotencia + modificadorPenalizacion;
		}
		
	}
	
	public void setTiempoIda(int tiempoIda) {
		this.tiempoIda = tiempoIda;
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

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String toString() {
				return 	"\nTrainera " + nombre + "\ncon potencia total --> " + potenciaTotal
				+ "\ncon energia total --> " + energiaTotal + "\ncon experiencia total --> " + experienciaTotal + "\ncon habilidad buena mar --> " + habilidadBuenaMarTotal
				+ "\ncon habilidad mala mar --> " + habilidadMalaMarTotal + "\ncon score --> " + score + "\ncon calle -->" + calle.toString()
				+ "\ncon tiempo de ida --> " + getTiempoIda() + "\ncon estrategia ida --> " + getEstrategiaActual() + "\ncon estrategia vuelta --> " + getEstrategiaVuelta();
	}

	class OrdenarPorTiempoIda implements Comparator<Trainera> {

		@Override
		public int compare(Trainera o1, Trainera o2) {
			int resultado = ((Integer)(o1.getTiempoIda())).compareTo((Integer)(o2.getTiempoIda()));
			int resultadoNom = 0;
			if ( resultado == 0 )
				resultadoNom = o1.getNombre().compareTo(o2.getNombre());
			return resultado + resultadoNom;
		}
	}
	
	class OrdenarPorTiempoVuelta implements Comparator<Trainera> {
		
		@Override
		public int compare(Trainera o1, Trainera o2) {
			int resultado = ((Integer)(o1.getTiempoVuelta())).compareTo((Integer)(o2.getTiempoVuelta()));
			int resultadoNom = 0;
			if ( resultado == 0 )
				resultadoNom = o1.getNombre().compareTo(o2.getNombre());
			return resultado + resultadoNom;
		}
	}
	
	class OrdenarPorTiempoTotal implements Comparator<Trainera> {
		
		@Override
		public int compare(Trainera o1, Trainera o2) {
			int resultado = ((Integer)(o1.getTiempoVuelta() + o1.getTiempoIda())).compareTo((Integer)(o2.getTiempoVuelta() + o2.getTiempoIda()));
			int resultadoNom = 0;
			if ( resultado == 0 )
				resultadoNom = o1.getNombre().compareTo(o2.getNombre());
			return resultado + resultadoNom;
		}
	}

	public float getPosition_x() {
		return position_x;
	}

	public void setPosition_x(float position_x) {
		this.position_x = position_x;
	}

	public float getPosition_y() {
		return position_y;
	}

	public void setPosition_y(float position_y) {
		this.position_y = position_y;
	}

	public float getVelocity_x() {
		return velocity_x;
	}

	public void setVelocity_x(float velocity_x) {
		this.velocity_x = velocity_x;
	}

	public float getVelocity_y() {
		return velocity_y;
	}

	public void setVelocity_y(float velocity_y) {
		this.velocity_y = velocity_y;
	}

	public TextureRegion getIcon() {
		return icon;
	}

	public void setIcon(TextureRegion icon) {
		this.icon = icon;
	}
	
	
}
