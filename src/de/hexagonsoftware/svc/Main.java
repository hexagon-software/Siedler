package de.hexagonsoftware.svc;

public class Main {
	public static void main(String[] args) {
		System.out.println("Siedler: Java LAN Strategie Spiel von Felix Eckert");
		System.out.println("==================================================================\n");
		
		int c = 0;
		int size = 7;
		for (String s : args) {
			if (s.matches("size")) {
				size = Integer.parseInt(args[c+1]);
			}
			
			System.out.println(s);
			
			c++;
		}
		
		Game game = new Game(size);
		game.start();
	}
}
