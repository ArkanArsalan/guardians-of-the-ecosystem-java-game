package guardiansOfTheEcosystem;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class MainMenuPanel extends JPanel {
    
    public MainMenuPanel() {
        // Set the background color to a light shade
        setBackground(new Color(230, 230, 255)); // You can adjust the RGB values

        // Use GridBagLayout for flexibility in component placement
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(10, 10, 10, 10); // Add some padding

        // Create a title label
        JLabel titleLabel = new JLabel("Guardians of the Ecosystem");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));

        // Create a start button and bind an action listener to switch panels
        JButton startButton = new JButton("Start Game");
        startButton.addActionListener(this::startGame);

        // Add components to the panel with centered layout
        add(titleLabel, gbc);
        gbc.gridy++;
        add(startButton, gbc);
    }

    private void startGame(ActionEvent e) {
        GamePanel parent = (GamePanel) getParent();
        parent.showPanel("GameScreen");
        GameScreenPanel.worldPanel.startGame();
    }
    
}
