package de.hexagonsoftware.svc.core;

import java.util.HashMap;

public class EngineDefaults {
	public HashMap<String, String> GRAPHICS_DEFAULTS = new HashMap<>();
	
	public EngineDefaults() {
		GRAPHICS_DEFAULTS.put("WIDTH", "1920");
		GRAPHICS_DEFAULTS.put("HEIGHT", "1080");	
	}
}