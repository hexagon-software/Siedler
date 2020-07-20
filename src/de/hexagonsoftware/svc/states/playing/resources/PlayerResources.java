package de.hexagonsoftware.svc.states.playing.resources;

import java.util.HashMap;

import de.hexagonsoftware.svc.states.PlayingState;

public class PlayerResources {
	private HashMap<String, InventoriedResource> inventory = new HashMap<>();
	private PlayingState state;
	
	public PlayerResources(PlayingState state) {
		this.state = state;
	}
	
	public void addResource(String name, int amount) {
		InventoriedResource res = state.res.getInvetoriedResource(name);
		if (inventory.containsKey(name)) {
			inventory.get(name).addAmount(amount);
		} else {
			res.addAmount(amount);
			inventory.put(name, res);
		}
		
		System.out.println("[SVC-client][INFO] (game) Added "+name+" Resource to inventory!");
	}
	
	public HashMap<String, InventoriedResource> getInventory() { return this.inventory; }

	public boolean hasResource(String name) {
		if (inventory.containsKey(name)) {
			if (inventory.get(name).getAmount() > 0) {
				return true;
			} else {
				return false;
			}
		}
		return false;
	}
	
	public void reduceResource(String name, int amount) {
		if (inventory.containsKey(name)) {
			inventory.get(name).reduceAmount(amount);
		}
	}
}
