package de.hexagonsoftware.svc.icons;

import java.awt.image.BufferedImage;

import de.hexagonsoftware.svc.core.graphics.ImageLoader;

public class SawmillIcon implements IIcon {
	private BufferedImage img;
	
	public SawmillIcon() {
		this.img = ImageLoader.loadImage(getClass().getResource("/assets/textures/icons/sawmill.png"));
	}
	
	@Override
	public BufferedImage getImage() {
		return this.img;
	}

	@Override
	public String getName() {
		return "SAWMILL";
	}
}
