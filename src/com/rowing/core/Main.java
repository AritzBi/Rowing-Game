package com.rowing.core;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.rowing.logic.BasicLogic;
import com.rowing.pojo.Equipo;
import com.rowing.pojo.Patron;
import com.rowing.pojo.Remero;
import com.rowing.pojo.Trainera;
import com.rowing.utils.Utils;

public class Main {

	public static void main(String[] args) {
		//Al entrar en confeccionar equipo, se requerirá consultar el tiempo!
		Utils.getWeatherDonosti();
		
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
		
		List<Trainera> trainerasCompetidoras = BasicLogic.obtenerTraineras(trainera);
		Map<Integer,String> calles = BasicLogic.obtenerCalles();
		BasicLogic.asignarCallesATraineras(trainerasCompetidoras, calles);
		
		//Seteamos por "defecto" una estrategia para la trainera de Orio
		String estrategia_Orio = Constants.ESTRATEGIAS_SALIDA.get(0);
		trainera.calcularScoreTrainera_Ida(estrategia_Orio);
		
		for ( Trainera traineraAux: trainerasCompetidoras ) {
			int estrategiaRandom = (int)(Math.random() * 2 + 0);
			traineraAux.calcularScoreTrainera_Ida(Constants.ESTRATEGIAS_SALIDA.get(estrategiaRandom));
		}

	}
}
