package de.hexagonsoftware.svc.polys;

import java.awt.Polygon;

@Deprecated
public class Hexagon extends Polygon {
	private static final long serialVersionUID = 1L;

	public Hexagon(int x, int y, int width, int height) {
		addPoint(25+x, 0+y); 						// Punkt Oben Links
		addPoint(75+(x+width), 0); 					// Punkt Oben Rechts
		addPoint(100+(x+width), 50+(y+height)); 	// Punkt Mitte Rechts
		addPoint(75+(x+width), 100+(y+height)); 	// Punkt unten Rechts
		addPoint(25+x, 100+(y+height)); 			// Punkt unten Links
		addPoint(0+x, 50+(y+height)); 				// Punkt mitte Link
	}
	
	public Hexagon(int x, int y) {
		addPoint(25+x, 0+y); 	// Punkt Oben Links
		addPoint(75+x, 0); 		// Punkt Oben Rechts
		addPoint(100+x, 50+y); 	// Punkt Mitte Rechts
		addPoint(75+x, 100+y); 	// Punkt unten Rechts
		addPoint(25+x, 100+y); 	// Punkt unten Links
		addPoint(0+x, 50+y); 	// Punkt mitte Link
	}
}
