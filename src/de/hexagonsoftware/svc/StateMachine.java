package de.hexagonsoftware.svc;

import java.util.HashMap;

import de.hexagonsoftware.svc.states.IState;
import de.hexagonsoftware.svc.states.PlayingState;

public class StateMachine {
	HashMap<String, IState> states = new HashMap<>();
	IState activeState;
	
	public StateMachine(Game game) {
		states.put("PlayingState", new PlayingState(game));
	}
	
	public void activateState(String name) {
		this.activeState = states.get(name);
	}
	
	public IState getActiveState() { return this.activeState; }
}
