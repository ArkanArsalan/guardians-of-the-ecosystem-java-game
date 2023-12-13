package guardiansOfTheEcosystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class GameScreenPanel extends JPanel {

    static World worldPanel;
    
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
        energy.setLocation(50, 80);
        energy.setSize(60, 20);
        add(energy);
        
        worldPanel = new World(energy);
        worldPanel.setLocation(0, 0);
        add(worldPanel);
    }

    private void setupGuardianCards() {
        // Guardian cards
        GuardianCard bearcatCard = createGuardianCard(GuardianType.BearCat, "images/card_peashooter.png", 110);
        GuardianCard porcupineCard = createGuardianCard(GuardianType.Porcupine, "images/card_peashooter.png", 175);
        GuardianCard crabCard = createGuardianCard(GuardianType.Crab, "images/card_peashooter.png", 240);

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
