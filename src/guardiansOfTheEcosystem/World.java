package guardiansOfTheEcosystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.Random;

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
    
    // All variables to store timer
    private Timer redrawTimer;
    private Timer energyProducer;
    private Timer gameplayTimer;



    public World(JLabel energyScoreBoard) {
        setSize(1000, 752);
        setLayout(null);
        addMouseMotionListener(this);
        this.energyScoreBoard = energyScoreBoard;
        
        // Set initial energy to 150
        setEnergyScore(150);
        
        try {
            bgImage = new ImageIcon(this.getClass().getResource("images/mainBG.png")).getImage();
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
        
        // Redraw every 25 milisecond
        redrawTimer = new Timer(25, (ActionEvent e) -> {
            repaint();
        });
        redrawTimer.start();
        
        // Produce energys
        activeEnergys = new ArrayList<>();
        
        gameplayTimer = new Timer(60, (ActionEvent e) -> gameplay());
        gameplayTimer.start();
        
        energyProducer = new Timer(2500, (ActionEvent e) -> {
            Random random = new Random();
            Energy energy = new Energy(this, random.nextInt(800) + 100, 0, random.nextInt(300) + 200);
            activeEnergys.add(energy);
            add(energy, 1);
        });
        
        energyProducer.start();
        
    }
    
    private void gameplay() {
        for (int i = 0; i < activeEnergys.size(); i++) {
            activeEnergys.get(i).energyFall();
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
    
    public void setActiveEnergys(ArrayList<Energy> activeEnergys) {
        this.activeEnergys = activeEnergys;
    }
    
    public void setEnergyScore(int energyScore) {
        this.energyScore = energyScore;
        energyScoreBoard.setText(String.valueOf(energyScore));
    }
    
    public int getEnergyScore() {
        return energyScore;
    }

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		mouseX = e.getX();
		mouseY = e.getY();
	}
}
