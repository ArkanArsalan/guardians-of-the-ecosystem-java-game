package guardiansOfTheEcosystem;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class WonPanel extends JPanel {
	
    public WonPanel() {
        // Set the background color to red
        setBackground(Color.CYAN);

        // Use GridBagLayout for flexibility in component placement
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new java.awt.Insets(10, 10, 10, 10); // Add some padding

        // Create a game over label
        JLabel gameOverLabel = new JLabel("You Won!");
        gameOverLabel.setFont(new Font("Arial", Font.BOLD, 36));
        gameOverLabel.setForeground(Color.WHITE); // Set text color to white

        // Create a label for the message
        JLabel messageLabel = new JLabel("You saved the ecosystem");
        messageLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        messageLabel.setForeground(Color.WHITE); // Set text color to white

        // Create a restart button and bind an action listener to switch panels
        JButton restartButton = new JButton("Back to Main Menu");
        restartButton.addActionListener(this::restartGame);

        // Add components to the panel with centered layout
        add(gameOverLabel, gbc);
        gbc.gridy++;
        add(messageLabel, gbc);
        gbc.gridy++;
        add(restartButton, gbc);
    }
    
    private void restartGame(ActionEvent e) {
        GamePanel parent = (GamePanel) getParent();
        parent.showPanel("MainMenu");
    }
    
}
