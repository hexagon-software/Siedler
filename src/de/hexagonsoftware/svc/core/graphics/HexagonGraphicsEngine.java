package de.hexagonsoftware.svc.core.graphics;

import de.hexagonsoftware.svc.core.Engine;

public class HexagonGraphicsEngine {
	private int[] resolution;
	private GameWindow gameWindow;
	private MainRenderer renderer;
	private AnimationRegistry animationRegistry;
	
	public HexagonGraphicsEngine(int[] resolution, String title, Engine engine) {
		this.resolution = resolution;
		if (this.resolution == null) {
			System.out.println("[WRN] HGE: WARNING: THE CLIENT DID NOT SPECIFY THE RESOLUTION; SETTING RESOLUTION TO 1024x512!");
			this.resolution = new int[] {1024, 512};
		}
		
		this.gameWindow = new GameWindow(resolution, title);
		this.animationRegistry = new AnimationRegistry();
		this.renderer = new MainRenderer(engine, gameWindow);
	}
	
	public GameWindow getGameWindow() { return this.gameWindow; }
	public MainRenderer getRenderer() { return this.renderer; }
	public AnimationRegistry getAnimationRegistry() { return this.animationRegistry; }
}
