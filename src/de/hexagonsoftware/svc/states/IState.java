package de.hexagonsoftware.svc.states;

import java.awt.Graphics;

public interface IState {
	void render(Graphics g);
	void update();
	void mousePressed(int x, int y);
}
