package de.hexagonsoftware.svc.icons;

import java.awt.image.BufferedImage;

import de.hexagonsoftware.svc.core.graphics.ImageLoader;

public class CityIcon implements IIcon {
	private BufferedImage img;
	
	public CityIcon() {
		this.img = ImageLoader.loadImage(getClass().getResource("/assets/textures/icons/city_modern.png"));
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
