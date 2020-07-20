package de.hexagonsoftware.svc.input;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import de.hexagonsoftware.svc.Game;

public class Mouse implements MouseListener {
	private Game game;
	
	public Mouse(Game game) {
		this.game = game;
	}
	
	@Override
	public void mouseClicked(MouseEvent arg0) {
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		game.handleMouse(arg0);
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
			
	}

}
