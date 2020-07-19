package de.hexagonsoftware.svc;

public class Main {
	public static void main(String[] args) {
		System.out.println("Die Siedler von Catan: Lokale Java Implementation von Felix Eckert");
		System.out.println("==================================================================\n");
		
		Game game = new Game();
		game.start();
	}
}
