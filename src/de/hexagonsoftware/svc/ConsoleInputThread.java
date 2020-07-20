package de.hexagonsoftware.svc;

import java.util.Scanner;

public class ConsoleInputThread implements Runnable {
	private Thread t;
	private Game game;
	
	public ConsoleInputThread(Game game) {
		this.game = game;
	}
	
	public void start() {
		this.t = new Thread(this, "SVC-CIT");
		t.start();
	}
	
	@Override
	public void run() {
		while (true) {
			Scanner scanner = new Scanner(System.in);
			String in = scanner.nextLine();
			
			if (in.matches("exit")) {
				System.out.println("[SVC-ConsoleInputThread][INFO] Stoppe Spiel!");
				game.stop();
				System.exit(0);
			}
		}
	}
}
