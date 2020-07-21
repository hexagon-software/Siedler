package de.hexagonsoftware.svc.input;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import de.hexagonsoftware.svc.Game;

public class Keyboard extends KeyAdapter {
	private Game game;
	
	public Keyboard(Game game) {
		this.game = game;
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		game.handleKeyDown(e);
	}
	
	@Override
	public void keyReleased(KeyEvent e) {
		game.handleKeyUp(e);
	}
}
