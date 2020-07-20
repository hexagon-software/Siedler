package de.hexagonsoftware.svc.core;

import de.hexagonsoftware.svc.Game;
import de.hexagonsoftware.svc.core.graphics.HexagonGraphicsEngine;
public class Engine {
	private EngineDefaults ed = new EngineDefaults();
	
	private HexagonGraphicsEngine hge;
	
	private IGame game;
	
	public Engine(IGame game) {
		this.game = game;
		int[] res = new int[] {
				Integer.parseInt(ed.GRAPHICS_DEFAULTS.get("WIDTH")),
				Integer.parseInt(ed.GRAPHICS_DEFAULTS.get("HEIGHT"))
		};
		this.hge = new HexagonGraphicsEngine(res, "Siedler "+((Game) game).version, this);
	}
	
	public String getInfo() {
		return "Hexagon Engine 0.1 [Beta], Hexagon Graphics Engine";
	}
	
	public void execGameLoop() {
		this.hge.getRenderer().render();
	}
	
	public IGame getGame() { return game; }
	public HexagonGraphicsEngine getHGE() { return this.hge; }
}
