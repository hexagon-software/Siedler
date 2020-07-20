package de.hexagonsoftware.svc.states.playing.resources;

public class InventoriedStoneResource extends InventoriedResource {
	private int amount;
	
	public InventoriedStoneResource(int amount) {
		this.amount = amount;
	}
	
	public void addAmount(int amount) {
		this.amount += amount;
	}
	
	public void reduceAmount(int amount) {
		this.amount -= amount;
	}
	
	public int getAmount() { return this.amount; }
	public IResource getResource() { return Resources.STONE; }
	public String getName() { return "Stone"; }
}
