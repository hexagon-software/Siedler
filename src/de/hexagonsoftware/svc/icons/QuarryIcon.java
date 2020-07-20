package de.hexagonsoftware.svc.icons;

import java.awt.image.BufferedImage;

import de.hexagonsoftware.svc.core.graphics.ImageLoader;

public class QuarryIcon implements IIcon {
	private BufferedImage img;
	
	public QuarryIcon() {
		this.img = ImageLoader.loadImage(getClass().getResource("/assets/textures/icons/quarry.png"));
	}
	
	@Override
	public BufferedImage getImage() {
		return this.img;
	}

	@Override
	public String getName() {
		return "CITY";
	}
}
