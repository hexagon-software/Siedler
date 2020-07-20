package de.hexagonsoftware.svc.input;

import de.hexagonsoftware.svc.states.IState;

public class MouseInputHandler {
	private IState state;
	
	public MouseInputHandler(IState state) {
		this.state = state;
	}
	
	public void handleInput(int x, int y) {
		state.mousePressed(x, y);
	}
}
