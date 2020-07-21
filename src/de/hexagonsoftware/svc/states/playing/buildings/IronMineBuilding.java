package de.hexagonsoftware.svc.states.playing.buildings;

import de.hexagonsoftware.svc.icons.IIcon;
import de.hexagonsoftware.svc.icons.Icons;
import de.hexagonsoftware.svc.states.playing.resources.PlayerResources;

public class IronMineBuilding implements IBuilding {
	private int[] coordinates;
	
	public IronMineBuilding(int x, int y) {
		this.coordinates = new int[] {x, y};
	}
	
	@Override
	public String getName() {
		return "Iron Mine";
	}

	@Override
	public IIcon getIcon() {
		return Icons.IRON_MINE;
	}

	@Override
	public int[] getCoordinates() { return this.coordinates; }

	@Override
	public void cost(PlayerResources pRes) {
		pRes.reduceResource("WOOD", 2);
		pRes.reduceResource("STONE", 2);
		pRes.reduceResource("CASH", 1);
	}
}
