package de.hexagonsoftware.svc.states;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.HashMap;
import java.util.concurrent.ThreadLocalRandom;

import de.hexagonsoftware.svc.Game;
import de.hexagonsoftware.svc.Tile;
import de.hexagonsoftware.svc.core.graphics.StringRenderer;
import de.hexagonsoftware.svc.icons.IIcon;
import de.hexagonsoftware.svc.icons.Icons;
import de.hexagonsoftware.svc.polys.DynHexagon;
import de.hexagonsoftware.svc.states.playing.buildings.Buildings;
import de.hexagonsoftware.svc.states.playing.buildings.CityBuilding;
import de.hexagonsoftware.svc.states.playing.buildings.QuarryBuilding;
import de.hexagonsoftware.svc.states.playing.resources.InventoriedResource;
import de.hexagonsoftware.svc.states.playing.resources.PlayerResources;
import de.hexagonsoftware.svc.states.playing.resources.Resources;

/**
 * PlayingState
 * 
 * This State is the main state of the game.
 * It processes the the input, renders and updates.
 * 
 * @author Felix Eckert
 * */
public class PlayingState implements IState {
	private int stateAliveTicks = 0;
	
	private Game game;
	private int size;
	
	private Tile[] map; // Array of Tiles which make up the map
	private int[] colors = new int[] {0xf1c40f, 0x2ecc71, 0x95a5a6}; // List of Colors with their position corresponding to their Tile ID
	
	public Resources res; // Game Resources (Stone, Wood etc.)
	private PlayerResources pRes; // The players Inventory
	
	private Buildings buildings;
	
	public int mousePressX, mousePressY = 0; // Stores the location of the last mouse press
	
	public PlayingState(Game game) {
		this.game = game;
		this.size = game.getSize();
		this.map = new Tile[size*size];
		System.out.println("[SVC-client][INFO] Generiere Map...");
		
		// Map Generation
		// It's really not good at the moment. Im gonna change it later on.
		for (int x = 0; x < this.size-2; x++) {
			for (int y = 0; y < this.size-2; y++) {
				int type = ThreadLocalRandom.current().nextInt(3);
				map[x+y] = new Tile(x, y, type);
			}
		}
		
		System.out.println("[SVC-client][INFO] Füge Resourcen Hinzu...");
		res = new Resources(); // Initialise the Resource "Registry"
		
		System.out.println("[SVC-client][INFO] Initialisiere Spieler...");
		// Initialise the Player's Inventory with basic Items
		this.pRes = new PlayerResources(this);
		this.pRes.addResource("STONE", 2);
		this.pRes.addResource("WOOD", 2);
		
		this.buildings = new Buildings();
	}
	
	@Override
	public void render(Graphics g) {
		g.setColor(Color.BLACK);
		
		// Rendering of the Map
		Point origin = new Point(game.getEngine().getHGE().getGameWindow().getWidth() / 2, game.getEngine().getHGE().getGameWindow().getHeight() / 2);
		drawHexMap(g, origin, size, 50, 0);
		
		// Game Info Rendering
		g.setColor(Color.BLACK);
		g.setFont(new Font("Courier New", Font.BOLD, 25));
		g.drawString("\"Siedler\" (Java) ", 1, 1+g.getFontMetrics().getHeight());
		g.drawString("Version "+game.version+" von Felix Eckert", 2, 1+g.getFontMetrics().getHeight()*2);
		
		// Render the Resources the Player currently has
		int lines = drawInventory(g)/2;
		int costY = 1+((g.getFontMetrics().getHeight()*(lines*4)));
		
		// Render information about Buildings
		String[] infoLines = new String[] {
				"",
				"Building Costs:",
				"Settlement: 1 Stone, 1 Wood",
				"Quarry: 1 Stone, 1 Wood"
		};
		
		int i = 0;
		for (String il : infoLines) {
			costY = 1+((g.getFontMetrics().getHeight()*(lines*(4+i))));
			StringRenderer.drawString(g, il, costY,
					game.getEngine().getHGE().getGameWindow().getWidth(),
					StringRenderer.RIGHT);
			i++;
		}
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
        
        int scale = 15;
        // 0x000000
        int ovalColor = 0x2980b9;
        
        g.setColor(new Color(ovalColor));
        g.fillOval((game.getEngine().getHGE().getGameWindow().getWidth() / 2)-((radius*scale)/2), 
        (game.getEngine().getHGE().getGameWindow().getHeight() / 2)-((radius*scale)/2), radius*scale, radius*scale);
        
        for (int row = 0; row < size; row++) {
            int cols = size - java.lang.Math.abs(row - half);

            for (int col = 0; col < cols; col++) {
            	feldCount++;
                int xLbl = row < half ? col - row : col - half;
                int yLbl = row - half;
                int x = (int) (origin.x + xOff * (col * 2 + 1 - cols));
                int y = (int) (origin.y + yOff * (row - half) * 3);
                
                // Check if its Land or Water
                if (row == 0 || row+1 == size || col == 0 || col+1 == cols) {
                	drawHex(g, xLbl, yLbl, x, y, radius, feldCount, 0x3498db, -1, map[row+col]); 
                } else {
                	drawHex(g, xLbl, yLbl, x, y, radius, feldCount, colors[map[row+col].getType()], map[row+col].getType(), map[row+col]);
                	if (map[row+col].hasBuilding(new CityBuilding(x, y))) {
                		drawIcon(g, Icons.CITY, map[row+col].getCvsX(), map[row+col].getCvsY(), 48);
                	} else if (map[row+col].hasBuilding(new QuarryBuilding(x, y))) {
                		drawIcon(g, Icons.QUARRY, map[row+col].getCvsX(), map[row+col].getCvsY(), 48);
                	}
                }
            }
        }
	}
	
	public void drawHex(Graphics g, int posX, int posY, int x, int y, int r, int feld, int color, int type, Tile tile) {
		Graphics2D g2d = (Graphics2D) g;

        DynHexagon hex = new DynHexagon(x, y, r);
        
        Rectangle rec = new Rectangle(mousePressX, mousePressY, 1, 1);
        
        if (rec.intersects(hex.getBounds())) {
        	if (type == 1) {
        		if (pRes.hasResource("WOOD") && pRes.hasResource("STONE")) {
        			CityBuilding build = new CityBuilding(x, y); 
        			build.cost(pRes);
        			tile.addBuilding(build);
        		}
        	} else if (type == 2) {
        		if (pRes.hasResource("WOOD") && pRes.hasResource("STONE")) {
        			QuarryBuilding build = new QuarryBuilding(x, y);
        			build.cost(pRes);
        			tile.addBuilding(build);
        		}
        	}
        	
            mousePressX = 0;
            mousePressY = 0;
        }
        
        hex.draw(g2d, x, y, 0, color, true);
        hex.draw(g2d, x, y, 2, color, false);
        g.setColor(Color.DARK_GRAY);
        int feldX = x- (int)g.getFontMetrics().getStringBounds(String.valueOf(feld), g).getWidth()/2;
        g.setFont(new Font("Courier New", Font.ITALIC, 18));
        g.drawString(String.valueOf(feld), x, y);
        
        if (tile == null)
        	return;
        
        tile.setCvsX(x);
        tile.setCvsY(y);
       // System.out.println("a");
	}

	public void drawIcon(Graphics g, IIcon icon, int x, int y, int scale) {
		Color color = g.getColor();
		
		g.drawImage(icon.getImage(), x-scale, y-scale, scale, scale, null);
		
		g.setColor(color);
	}
	
	/**
	 * Zeichnet das Inventar des Spielers
	 * */
	private int drawInventory(Graphics g) {
		HashMap<String, InventoriedResource> inventory = pRes.getInventory();
		String[] invList = new String[inventory.size()];
		
		int c = 0;
		for (Object o : inventory.keySet().toArray()) {
			InventoriedResource res = inventory.get(o);
			invList[c] = res.getName()+": "+res.getAmount();
			
			c++;
		}
		
		int lines = 0;
		
		for (int i = 0; i < invList.length+1; i++) {
			if (i == 0) {
				int x = (game.getEngine().getHGE().getGameWindow().getWidth()) - (int) g.getFontMetrics().
						getStringBounds("Resources:", g).getWidth();
				g.drawString("Resources:", x, 1+(g.getFontMetrics().getHeight()*(i+1)));
				continue;
			}
			
			int x = (game.getEngine().getHGE().getGameWindow().getWidth()) - (int) g.getFontMetrics().
					getStringBounds(invList[i-1], g).getWidth();
			g.drawString(invList[i-1], x, 1+(g.getFontMetrics().getHeight()*(i+1)));
		
			lines++;
		}
		return lines;
	}
	
	@Override
	public void update() {
		if (stateAliveTicks % 10000 == 0) {
			for (Tile t : map) {
				if (t == null)
					continue; 
			
				if (t.hasBuilding(new CityBuilding(t.getX(), t.getY()))) {
						pRes.addResource("WOOD", 1);
				} else if (t.hasBuilding(new QuarryBuilding(t.getX(), t.getY()))) {
						pRes.addResource("STONE", 1);
				}
			}
		}
		
		stateAliveTicks++;
	}
	
	@Override
	public void mousePressed(int x, int y) {
		this.mousePressX = x;
		this.mousePressY = y;
	}
}
