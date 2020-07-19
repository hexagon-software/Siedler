package de.hexagonsoftware.svc.core;

import java.awt.Graphics;

public interface IGame extends Runnable {
	void start();
	void render(Graphics g);
}
