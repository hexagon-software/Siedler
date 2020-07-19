package de.hexagonsoftware.svc;

public class Tile {
	private int x, y, type;
	
	public Tile(int x, int y, int type) {
		this.x = x;
		this.y = y;
		this.type = type;
	}
	
	public int getType() { return this.type; }
}
