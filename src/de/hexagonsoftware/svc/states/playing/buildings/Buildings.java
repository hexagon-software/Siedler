package de.hexagonsoftware.svc.states.playing.buildings;

import java.util.HashMap;

public class Buildings {
	private HashMap<String, IBuilding> buildings = new HashMap<>();
	
	public void addBuidling(String name, IBuilding building) {
		this.buildings.put(name, building);
	}

	public IBuilding getBuilding(String name) {
		return this.buildings.get(name);
	} 
}
