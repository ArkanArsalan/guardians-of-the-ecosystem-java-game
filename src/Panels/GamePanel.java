package Panels;

import javax.swing.JPanel;
import java.awt.CardLayout;

public class GamePanel extends JPanel {
	
	private CardLayout cardLayout = new CardLayout();
	
	MainMenuPanel mmp;
	GameScreenPanel gsp;
	GameOverPanel gop;
	WonPanel wp;

	public GamePanel() {
	    setLayout(cardLayout);
	    
	    mmp = new MainMenuPanel();
	    gsp = new GameScreenPanel();
	    gop = new GameOverPanel();
	    wp = new WonPanel();
	    
	    add(mmp, "MainMenu");
	    add(gsp, "GameScreen");
	    add(gop, "GameOver");
	    add(wp, "Won");
	}
	
	public void showPanel(String name) {
	    cardLayout.show(this, name);
	}
	
}
