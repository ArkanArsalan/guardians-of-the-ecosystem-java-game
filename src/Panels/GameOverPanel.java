package Panels;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class GameOverPanel extends JPanel {
	
	private Image backgroundImage;
	 
    public GameOverPanel() {
    	// Load the background image
        try {
            URL resource = getClass().getResource("images/gameover.jpg");
            backgroundImage = ImageIO.read(resource);
        } catch (IOException e) {
            e.printStackTrace(); // Handle the exception appropriately
        }
        
     // Set the layout and background color
        setLayout(new GridBagLayout());
        setBackground(new Color(230, 230, 255)); // You can adjust the RGB values

        // Initialize components and add them to the panel
        initComponents();
    }
    
    private void initComponents() {
    	
    	setLayout(null); // Set layout to null for absolute positioning
        // Use GridBagLayout for flexibility in component placement

        // Create a restart button and bind an action listener to switch panels
        JButton restartButton = new JButton("");
        restartButton.addActionListener(this::restartGame);

        
     // Set the button to be transparent
        restartButton.setOpaque(false);
        restartButton.setContentAreaFilled(false);
        restartButton.setBorderPainted(false);

        // Set the X and Y positions for the button
        restartButton.setBounds(420, 520, 100, 50); // Adjust X, Y, width, and height as needed
        restartButton.setPreferredSize(new Dimension(200, 100));

        // Add components to the panel with centered layout

        add(restartButton);
    }
    
    private void restartGame(ActionEvent e) {
        GamePanel parent = (GamePanel) getParent();
        parent.showPanel("MainMenu");
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Draw the background image
        if (backgroundImage != null) {
            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        }
    }
    
}
