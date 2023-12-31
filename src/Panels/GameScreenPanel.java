package Panels;

import javax.swing.*;

import Cards.GuardianCard;

import java.awt.event.ActionEvent;

public class GameScreenPanel extends JPanel {

    public static World worldPanel;
    
    enum GuardianType {
        None,
        BearCat,
        Porcupine,
        Crab
    }

    public GameScreenPanel() {
        setLayout(null);

        setupGuardianCards();
        
        JLabel energy = new JLabel("energy");
        energy.setLocation(75, 73);
        energy.setSize(60, 35);
        add(energy);
        
        worldPanel = new World(energy);
        worldPanel.setLocation(0, 0);
        add(worldPanel);
    }

    private void setupGuardianCards() {
        // Guardian cards
        GuardianCard bearcatCard = createGuardianCard(GuardianType.BearCat, "images/bearcatcard.png", 130);
        GuardianCard porcupineCard = createGuardianCard(GuardianType.Porcupine, "images/porcupinecard.png", 200);
        GuardianCard crabCard = createGuardianCard(GuardianType.Crab, "images/crabcard.png", 270);

        // Add guardian cards to the panel
        add(bearcatCard);
        add(porcupineCard);
        add(crabCard);
    }

    private GuardianCard createGuardianCard(GuardianType type, String imagePath, int xLocation) {
        GuardianCard guardianCard = new GuardianCard(new ImageIcon(this.getClass().getResource(imagePath)).getImage());
        guardianCard.setLocation(xLocation, 8);
        guardianCard.setAction((ActionEvent e) -> {
            worldPanel.setActiveGuardian(type);
        });
        return guardianCard;
    }
}
