package com.rowing.logic;

import static com.rowing.core.Constants.CALLE_BUENA;
import static com.rowing.core.Constants.CALLE_MALA;
import static com.rowing.core.Constants.CALLE_SEMI_BUENA;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.rowing.core.GameSession;
import com.rowing.pojo.Trainera;
import com.rowing.utils.CondicionesMeteo;
import com.rowing.utils.Utils;

;

public class BasicLogic {

	/**
	 * Método que me devuelve las tres traineras con las que competirá Orio. Los
	 * valores de cada una de ellas se calculará en base a lo que se le habia
	 * puesto a Orio.
	 * 
	 * @param trainera
	 *            . Se le pasa como parámetro la trainera de Orio
	 * @return
	 */
	public static List<Trainera> obtenerTraineras(Trainera trainera) {
		List<Trainera> traineras = new ArrayList<Trainera>();

		CondicionesMeteo condicionesDeDonosti = GameSession.getInstance().condicionesMeteo;

		int energia = trainera.getEnergiaTotal();
		int experiencia = trainera.getExperienciaTotal();
		int potencia = trainera.getPotenciaTotal();
		int habilidadBuenaMar = trainera.getHabilidadBuenaMarTotal();
		int habilidadMalaMar = trainera.getHabilidadMalaMarTotal();
		trainera.nombre = "ORIO";
		
		// TRAINERA DONOSTIARRA
		int DONOSTI_SUMATORIO_PUNTOS = 4;
		Trainera traineraDonostiarra = new Trainera();
		traineraDonostiarra.setEnergiaTotal(energia);
		traineraDonostiarra.setExperienciaTotal(experiencia
				- DONOSTI_SUMATORIO_PUNTOS);
		traineraDonostiarra.setPotenciaTotal(potencia
				- DONOSTI_SUMATORIO_PUNTOS);
		if (condicionesDeDonosti.isBuenaMar())
			traineraDonostiarra.setHabilidadBuenaMarTotal(habilidadBuenaMar
					- DONOSTI_SUMATORIO_PUNTOS);
		else
			traineraDonostiarra.setHabilidadMalaMarTotal(habilidadMalaMar
					- 2);
		traineraDonostiarra.nombre = "DONOSTIARRA";
		traineras.add(traineraDonostiarra);

		// TRAINERA HONDARRIBI
		int HONDARRIBI_SUMATORIO_PUNTOS = 4;

		Trainera traineraHondarribi = new Trainera();
		traineraHondarribi.setEnergiaTotal(energia);
		traineraHondarribi.setExperienciaTotal(experiencia - 1);
		traineraHondarribi.setPotenciaTotal(potencia
				+ HONDARRIBI_SUMATORIO_PUNTOS);
		if (condicionesDeDonosti.isBuenaMar())
			traineraHondarribi.setHabilidadBuenaMarTotal(habilidadBuenaMar
					+ 2);
		else
			traineraHondarribi.setHabilidadMalaMarTotal(habilidadMalaMar
					- HONDARRIBI_SUMATORIO_PUNTOS);
		traineraHondarribi.nombre = "HONDARRIBI";
		traineras.add(traineraHondarribi);

		// TRAINERA KAIKU

		Trainera traineraKaiku = new Trainera();
		traineraKaiku.setEnergiaTotal(energia);
		traineraKaiku.setExperienciaTotal(experiencia + 2);
		traineraKaiku.setPotenciaTotal(potencia + 1);
		if (condicionesDeDonosti.isBuenaMar())
			traineraKaiku.setHabilidadBuenaMarTotal(habilidadBuenaMar);
		else
			traineraKaiku.setHabilidadMalaMarTotal(habilidadMalaMar + 3);
		traineraKaiku.nombre = "KAIKU";
		traineras.add(traineraKaiku);

		return traineras;
	}

	/**
	 * Método que devuelve un mapa con las diferentes calles y el "estado" de
	 * cada una de ellas
	 * 
	 * @return
	 */
	public static Map<Integer, String> obtenerCalles() {
		Map<Integer, String> calles = new HashMap<Integer, String>();

		int calleBuena = Utils.generaNumeroAleatorio(0, 3);
		calles.put(calleBuena, CALLE_BUENA);
		if (calleBuena == 0) {
			calles.put(1, CALLE_SEMI_BUENA);
			calles.put(2, CALLE_MALA);
			calles.put(3, CALLE_MALA);
		} else if (calleBuena == 1) {
			calles.put(0, CALLE_SEMI_BUENA);
			calles.put(2, CALLE_SEMI_BUENA);
			calles.put(3, CALLE_MALA);
		} else if (calleBuena == 2) {
			calles.put(0, CALLE_MALA);
			calles.put(1, CALLE_SEMI_BUENA);
			calles.put(3, CALLE_SEMI_BUENA);
		} else if (calleBuena == 3) {
			calles.put(0, CALLE_MALA);
			calles.put(1, CALLE_MALA);
			calles.put(2, CALLE_SEMI_BUENA);
		}
		return calles;
	}

	public static void asignarCallesATraineras(Trainera traineraOrio,
			List<Trainera> traineras, Map<Integer, String> calles) {

		List<Integer> callesYaAsignadas = new ArrayList<Integer>();
		// Asignamos calles a las traineras competidoras
		for (Trainera trainera : traineras) {
			boolean enc = false;
			while (!enc) {
				int calleRandom = Utils.generaNumeroAleatorio(0, 3);
				if (!callesYaAsignadas.contains(calleRandom)) {
					Map<Integer, String> calleSeleccionada = new HashMap<Integer, String>();
					calleSeleccionada.put(calleRandom, calles.get(calleRandom));
					trainera.setCalle(calleSeleccionada);
					callesYaAsignadas.add(calleRandom);
					enc = true;
				}
			}
		}
		// Asignamos calle a la trainera de Orio
		boolean enc = false;
		while (!enc) {
			int calleRandom = Utils.generaNumeroAleatorio(0, 3);
			if (!callesYaAsignadas.contains(calleRandom)) {
				Map<Integer, String> calleSeleccionada = new HashMap<Integer, String>();
				calleSeleccionada.put(calleRandom, calles.get(calleRandom));
				traineraOrio.setCalle(calleSeleccionada);
				callesYaAsignadas.add(calleRandom);
				enc = true;
			}
		}
	}

}
