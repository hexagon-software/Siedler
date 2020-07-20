package de.hexagonsoftware.svc;

import de.hexagonsoftware.svc.states.playing.buildings.CityBuilding;
import de.hexagonsoftware.svc.states.playing.buildings.IBuilding;

public class Tile {
	private int x, y, cvsx, cvsy, type;
	private IBuilding building;
	private int buildCounter = 0;
	
	public Tile(int x, int y, int type) {
		this.x = x;
		this.y = y;
		this.type = type;
	}
	
	public int getType() { return this.type; }
	public int getX() { return this.x; }
	public int getY() { return this.y; }

	public boolean hasBuilding(IBuilding b) {
		if (building == null)
			return false;
		
		if (b.getName().matches(building.getName()))
			return true;
		
		return false;
	}
	
	public void addBuilding(IBuilding building) {
		this.building = building;
	}

	public void setCvsX(int x) {
		this.cvsx = x;
	}
	
	public void setCvsY(int y) {
		this.cvsy = y;
	}
	
	public int getCvsX() { return this.cvsx; }
	public int getCvsY() { return this.cvsy; }
}
