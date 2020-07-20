package de.hexagonsoftware.svc.core;

import java.awt.Graphics;

import de.hexagonsoftware.svc.states.IState;

public interface IGame extends Runnable {
	public static IState activeState = null;
	void start();
	void render(Graphics g);
}
