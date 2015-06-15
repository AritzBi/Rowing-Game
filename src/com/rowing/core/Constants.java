package com.rowing.core;

import java.util.Arrays;
import java.util.List;

public class Constants {
	public static final String RESOURCE_DIR = "resources/";
	public static final String MUSIC_DIR = "resources/music/";
	public static final String MAP_DIR = "resources/map/";
	
	public static String CALLE_BUENA = "Buena";
	public static String CALLE_SEMI_BUENA = "SemiBuena";
	public static String CALLE_MALA = "Mala";
	
	public static String CALLE_BUENA_EN = "Good";
	public static String CALLE_SEMI_BUENA_EN = "Half-Good";
	public static String CALLE_MALA_EN = "Bad";
	
	public static final List<String> ESTRATEGIAS_SALIDA = Arrays.asList
	(new String[]
			{"Come up with Maximum Power and Keep Up", 
			 "Come up with Maximum Power and Maintain A Stable Rhythm",
			 "Come up with conservative way and reserve forces"}
	);
	
	public static final List<String> ESTRATEGIAS_VUELTA = Arrays.asList
	(new String[]
			{"Come up with Maximum Power and Keep Up", 
			 "Come up with Maximum Power and Maintain A Stable Rhythm",
			 "Come up with conservative way and reserve forces",
			 "Change to the best path",
			 "Allow captain to choose the strategy",
			 "Take advantage of the waves´ direction",
			 
			}
	);
	
	public static final int NUM_ROWERS = 13;
	public static int SIZE_X=64;
	public static int SIZE_Y=64;
	public static int STATS_BAR_OFFSET=50;
	
	public static int MAX_POTENCY=100;
	public static int MAX_EXPERIENCE=100;
	public static int MAX_ENERGY=100;
	public static int GOOD_SEA=108;
	public static int BAD_SEA=129;
	
	public static float SET_POS_X_0 = 75f;
	public static float SET_POS_X_1 = 75f;
	public static float SET_POS_X_2 = 75f;
	public static float SET_POS_X_3 = 75f;
	
	public static float SET_POS_Y_0 = 275f;
	public static float SET_POS_Y_1 = 308f;
	public static float SET_POS_Y_2 = 345f;
	public static float SET_POS_Y_3 = 385f;
	
	
	public static float CIABOGA_X = 500f;
	public static int TIEMPO_PARA_ELEGIR_ESTRATEGIA_VUELTA = 15000;
	
	public static int WIDTH_TABLE_STATS_ROADS = 700;
	public static int HEIGHT_TABLE_STATS_ROADS = 200;
	
	public static int CONSTANTE_EDITAR_TABLA = 7;
}
