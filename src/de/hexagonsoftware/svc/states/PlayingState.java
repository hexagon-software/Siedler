package de.hexagonsoftware.svc.states;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.util.concurrent.ThreadLocalRandom;

import de.hexagonsoftware.svc.Game;
import de.hexagonsoftware.svc.Tile;
import de.hexagonsoftware.svc.polys.DynHexagon;

/**
 * PlayingState (Spiel State)
 * 
 * Diese IState Impl. Generiert, rendert und updated
 * die Map beim Client.
 * 
 * @author Felix Eckert
 * */
public class PlayingState implements IState {
	private Game game;
	private int size = 7;
	
	private Tile[] map = new Tile[size*size]; // Die Map bestehend aus Tile instanzen
	private int[] colors = new int[] {0xf1c40f, 0x2ecc71, 0x95a5a6}; // Farben für die verschiedenen Felder
	
	public PlayingState(Game game) {
		this.game = game;
		System.out.println("[SVC-client][INFO] Generiere Map...");
		
		// Map Generieren
		for (int x = 0; x < size-2; x++) {
			for (int y = 0; y < size-2; y++) {
				int type = ThreadLocalRandom.current().nextInt(3);
				map[x+y] = new Tile(x, y, type);
			}
		}
	}
	
	@Override
	public void render(Graphics g) {
		g.setColor(Color.BLACK);
		
		// Die Map Rendern
		Point origin = new Point(game.getEngine().getHGE().getGameWindow().getWidth() / 2, game.getEngine().getHGE().getGameWindow().getHeight() / 2);
		drawHexMap(g, origin, size, 50, 0);
		
		g.setColor(Color.BLACK);
		g.setFont(new Font("Courier New", Font.BOLD, 25));
		g.drawString("\"Die Siedler von Catan\" in Java", 1, 1+g.getFontMetrics().getHeight());
		g.drawString("Version 0.1.0 von Felix Eckert", 2, 1+g.getFontMetrics().getHeight()*2);
	}
	
	/**
	 * Diese Methode übernimmt das Malen der Grund Map.
	 * 
	 * @author Felix Eckert
	 * */
	public void drawHexMap(Graphics g, Point origin, int size, int radius, int padding) {
        double ang30 = Math.toRadians(30);
        double xOff = Math.cos(ang30) * (radius + padding);
        double yOff = Math.sin(ang30) * (radius + padding);
        int half = size / 2;
		
        int feldCount = 0;
        
        for (int row = 0; row < size; row++) {
            int cols = size - java.lang.Math.abs(row - half);

            for (int col = 0; col < cols; col++) {
            	feldCount++;
                int xLbl = row < half ? col - row : col - half;
                int yLbl = row - half;
                int x = (int) (origin.x + xOff * (col * 2 + 1 - cols));
                int y = (int) (origin.y + yOff * (row - half) * 3);

                // Überprüfen ob es ein Land oder eine Wasser Landschaft sein sollte
                if (row == 0 || row+1 == size || col == 0 || col+1 == cols) {
                	drawHex(g, xLbl, yLbl, x, y, radius, feldCount, 0x3498db); 
                } else {
                	drawHex(g, xLbl, yLbl, x, y, radius, feldCount, colors[map[row+col].getType()]);
                }
            }
        }
	}
	
	public void drawHex(Graphics g, int posX, int posY, int x, int y, int r, int feld, int color) {
		Graphics2D g2d = (Graphics2D) g;

        DynHexagon hex = new DynHexagon(x, y, r);
        
        hex.draw(g2d, x, y, 0, color, true);
        hex.draw(g2d, x, y, 2, 0x000000, false);
        g.setColor(Color.DARK_GRAY);
        int feldX = x- (int)g.getFontMetrics().getStringBounds(String.valueOf(feld), g).getWidth()/2;
        g.setFont(new Font("Courier New", Font.ITALIC, 18));
        g.drawString(String.valueOf(feld), feldX, y);
	}

	@Override
	public void update() {}
}
