package de.hexagonsoftware.svc.states.playing.buildings;

import de.hexagonsoftware.svc.icons.IIcon;
import de.hexagonsoftware.svc.icons.Icons;

public class CityBuilding implements IBuilding {
	private int[] coordinates;
	
	public CityBuilding(int x, int y) {
		this.coordinates = new int[] {x, y};
	}
	
	@Override
	public String getName() {
		return "City";
	}

	@Override
	public IIcon getIcon() {
		return Icons.CITY;
	}

	@Override
	public int[] getCoordinates() { return this.coordinates; }
}
