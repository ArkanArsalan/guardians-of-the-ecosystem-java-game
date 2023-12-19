package Main;

import javax.swing.*;

import Panels.GamePanel;

public class GameWindow extends JFrame {
	
	public static GamePanel gp;
	
	public GameWindow() {
		 setTitle("Guardian of the Ecosystem");
		 setSize(1012, 785);
		 setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		 setLocationRelativeTo(null); 
		 gp = new GamePanel();
		 add(gp);
	}

	public static void main(String[] args) {
		java.awt.EventQueue.invokeLater(() -> {
			new GameWindow().setVisible(true);
		});
	}
}
