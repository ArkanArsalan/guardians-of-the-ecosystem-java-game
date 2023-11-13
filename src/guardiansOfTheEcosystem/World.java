package guardiansOfTheEcosystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;

public class World extends JLayeredPane implements MouseMotionListener {
	
	// Variable to store image
    private Image bgImage;
    
    // List of energy
    private ArrayList<Energy> activeEnergys;
    
    // Label for energy score
    private JLabel energyScoreBoard;
    
    // Variable to track mouse position
    private int mouseX, mouseY;
    
    // Variable to store energy
    private int energyScore;

    public void setenergyScore(int energyScore) {
        this.energyScore = energyScore;
        energyScoreBoard.setText(String.valueOf(energyScore));
    }

    public World(JLabel energyScoreBoard) {
        setSize(1000, 752);
        setLayout(null);
        addMouseMotionListener(this);
        this.energyScoreBoard = energyScoreBoard;
        
        // Set initial energy to 150
        setenergyScore(150);
        
        try {
            bgImage = new ImageIcon(this.getClass().getResource("images/mainBG.png")).getImage();
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error loading background image: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }

    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(bgImage, 0, 0, null);
    }
    
    public ArrayList<Energy> getActiveEnergys() {
        return activeEnergys;
    }
    
    public int getenergyScore() {
        return energyScore;
    }

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
}
