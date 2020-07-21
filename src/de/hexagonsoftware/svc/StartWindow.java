package de.hexagonsoftware.svc;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;

public class StartWindow {
	private JFrame frame;
	private JTextArea in;
	private int size = -1;
	private boolean finished = false;
	
	public StartWindow() {
		frame = new JFrame("Siedler");
		frame.setSize(800, 800);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JLabel label1 = new JLabel();
		label1.setText("Map Size");
		
		in = new JTextArea();
		JButton b = new JButton();
		b.setText("Start");
		b.setSize(20, 5);
		b.addActionListener(new StartHandler());
		
		frame.add(label1);
		frame.add(in);
		frame.add(b);
		frame.pack();
		frame.setVisible(true);
	}
	
	public JFrame getFrame() { return frame; }
	
	public boolean getFinished() { return finished; }
	public int getSize() { return size; }
	
	private class StartHandler implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			size = Integer.parseInt(in.getText());
			finished = true;
			frame.setVisible(false);
		}	
	}
}
