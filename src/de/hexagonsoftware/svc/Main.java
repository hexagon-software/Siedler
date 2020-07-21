package de.hexagonsoftware.svc;

import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		System.out.println("Siedler: Java LAN Strategie Spiel von Felix Eckert");
		System.out.println("==================================================================\n");
		
		int c = 0;
		int size = 7;
		boolean sizeNotSet = true;
		for (String s : args) {
			if (s.matches("size")) {
				size = Integer.parseInt(args[c+1]);
				sizeNotSet = false;
			}
			
			c++;
		}
		
		while (sizeNotSet) {
			System.out.print("size: ");
			Scanner s = new Scanner(System.in);
			String in = s.nextLine();
			
			if (!in.matches("")) {
				try {
					size = Integer.parseInt(in);
					if (size < 7) {
						System.out.println("Size may not be smaller than 7!");
						sizeNotSet = true;
						continue;
					}
					
					sizeNotSet = false;
				} catch (Exception e) {
					sizeNotSet = true;
				}
			}
		}
		
		Game game = new Game(size);
		game.start();
		
		ConsoleInputThread cit = new ConsoleInputThread(game);
		cit.start();
	}
}
