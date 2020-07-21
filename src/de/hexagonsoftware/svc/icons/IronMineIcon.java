package de.hexagonsoftware.svc.icons;

import java.awt.image.BufferedImage;

import de.hexagonsoftware.svc.core.graphics.ImageLoader;

public class IronMineIcon implements IIcon {
	private BufferedImage img;
	
	public IronMineIcon() {
		this.img = ImageLoader.loadImage(getClass().getResource("/assets/textures/icons/iron_mine.png"));
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
