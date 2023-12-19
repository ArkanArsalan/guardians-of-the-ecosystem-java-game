package Panels;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JPanel;

public class MainMenuPanel extends JPanel {

    private Image backgroundImage;

    public MainMenuPanel() {
        // Load the background image
        try {
            URL resource = getClass().getResource("images/mainmenu.jpg");
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

        // Create a start button and bind an action listener to switch panels
        JButton startButton = new JButton("");
        startButton.addActionListener(this::startGame);

        // Set the button to be transparent
        startButton.setOpaque(false);
        startButton.setContentAreaFilled(false);
        startButton.setBorderPainted(false);

        // Set the X and Y positions for the button
        startButton.setBounds(420, 520, 100, 50); // Adjust X, Y, width, and height as needed
        startButton.setPreferredSize(new Dimension(200, 100));

        // Add the button to the panel
        add(startButton);
    }



    private void startGame(ActionEvent e) {
        GamePanel parent = (GamePanel) getParent();
        parent.showPanel("GameScreen");
        GameScreenPanel.worldPanel.startGame();
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
