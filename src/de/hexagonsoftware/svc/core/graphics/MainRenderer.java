package de.hexagonsoftware.svc.core.graphics;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;

import de.hexagonsoftware.svc.core.Engine;

public class MainRenderer {
	private BufferStrategy bufferStrat;
	private Graphics g;
	private Engine engine;
	private GameWindow win;
	
	public MainRenderer(Engine engine, GameWindow win) {
		this.engine = engine;
		this.win = win;
	}
	
	public void render() {
		bufferStrat = win.getBufferStrategy();
		
		if (bufferStrat == null) {
			win.createBufferStrategy(3);
			return;
		}
		
		g = bufferStrat.getDrawGraphics();
		
		g.clearRect(0, 0, win.getWidth(), win.getHeight());
		
		engine.getGame().render(g);
		
		g.dispose();
		bufferStrat.show();
	}
}
