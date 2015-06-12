package com.rowing.core;

import com.rowing.utils.CondicionesMeteo;

public class GameSession {

	private static GameSession gameSession;
	
	public CondicionesMeteo condicionesMeteo;
	
	private GameSession () {
		condicionesMeteo = new CondicionesMeteo();
	}
	
	public static GameSession getInstance() {
		if ( gameSession == null ) 
		{
			gameSession = new GameSession();
		}
		return gameSession;
	}
}
