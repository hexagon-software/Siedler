package de.hexagonsoftware.svc;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.event.MouseEvent;
import java.util.concurrent.ThreadLocalRandom;

import de.hexagonsoftware.svc.core.Engine;
import de.hexagonsoftware.svc.core.IGame;
import de.hexagonsoftware.svc.input.Mouse;
import de.hexagonsoftware.svc.polys.DynHexagon;
import de.hexagonsoftware.svc.polys.Hexagon;
import de.hexagonsoftware.svc.states.IState;

/**
 * Die Siedler von Catan Java Implementation.
 * 
 * Dies ist eine Java Version von "Die Siedler von Catan" von KOSMOS.
 * Sie nutzt die Hexagon Graphics Engine in Kombination mit einer eigenen 
 * Simplen Game Engine, welche auf der Hexagon Game Engine basiert.
 * 
 * @author Felix Eckert
 * */
public class Game implements IGame {
	private Engine engine;
	private Thread t;
	
	private StateMachine stateMachine;
	
	public String version = "0.2.0 ALPHA";
	public int size;
	
	public Game(int size) {
		this.engine = new Engine(this);
		this.engine.getHGE().getGameWindow().addMouseListener(new Mouse(this));
		this.size = size;
		System.out.println("[SVC-client][INFO] (engine-info) "+engine.getInfo());	
		System.out.println("[SVC-client][INFO] Starte Spiel...");
		
		// State Machine
		this.stateMachine = new StateMachine(this);
		this.stateMachine.activateState("PlayingState");
	}

	public void start() {
		this.t = new Thread(this, "SVC-CLIENT");
		t.start();
	}
	
	@Override
	public void run() {
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("[SVC-client][INFO] Spiel Intialisiert!");
		int tick = 0;
		while (true) {
			engine.execGameLoop();
			update();
			
			tick++;
		}
	}
	
	@Override
	public void render(Graphics g) {
		this.stateMachine.getActiveState().render(g);
	}
	
	public void update() {
		this.stateMachine.getActiveState().update();
	}
	
	public void handleMouse(MouseEvent e) {
		stateMachine.activeState.mousePressed(e.getX(), e.getY());
	}
	
	public Engine getEngine() { return this.engine; }
	public int getSize() { return this.size; }

	public void stop() {
		t.interrupt();
	}
}
