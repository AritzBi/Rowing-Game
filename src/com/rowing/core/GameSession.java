package com.rowing.core;

import com.rowing.utils.CondicionesMeteo;
import com.rowing.utils.Utils;

public class GameSession {

	private static GameSession gameSession;

	public CondicionesMeteo condicionesMeteo;

	private GameSession() {
		condicionesMeteo = Utils.getWeatherDonosti();
	}
	
	private GameSession ( CondicionesMeteo condiciones ) {
		condicionesMeteo = condiciones;
	}

	public static GameSession getInstance() {
		if (gameSession == null) {
			gameSession = new GameSession();
		}
		return gameSession;
	}
	
	public static void setInstance( CondicionesMeteo condiciones ) {
		if (gameSession == null ) {
			gameSession = new GameSession( condiciones );
		}
		else
			gameSession.condicionesMeteo = condiciones;
	}
	
}
