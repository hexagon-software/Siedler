package de.hexagonsoftware.svc.states.playing.buildings;

import de.hexagonsoftware.svc.icons.IIcon;

public interface IBuilding {
	String getName();
	IIcon getIcon();
	int[] getCoordinates();
}
