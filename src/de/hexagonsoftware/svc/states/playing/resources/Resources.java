package de.hexagonsoftware.svc.states.playing.resources;

import java.util.HashMap;

public class Resources {
	private HashMap<String, InventoriedResource> invRes = new HashMap<>();
	
	public Resources() {
		invRes.put("STONE", new InventoriedStoneResource(0));
		invRes.put("IRON", new InventoriedIronResource(0));
		invRes.put("WOOD", new InventoriedWoodResource(0));
		invRes.put("CASH", new InventoriedCashResource(0));
	}
	
	public static IResource STONE = new StoneResource();
	public static IResource IRON  = new IronResource();
	public static IResource WOOD  = new WoodResource();

	public InventoriedResource getInvetoriedResource(String name) {
		return invRes.get(name);
	}
}
