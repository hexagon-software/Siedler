package de.hexagonsoftware.svc.states.playing.buildings;

import de.hexagonsoftware.svc.icons.IIcon;
import de.hexagonsoftware.svc.states.playing.resources.PlayerResources;

public interface IBuilding {
	String getName();
	IIcon getIcon();
	int[] getCoordinates();
	void cost(PlayerResources pRes);
}
