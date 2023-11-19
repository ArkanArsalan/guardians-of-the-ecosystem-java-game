package guardiansOfTheEcosystem;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class GameWindow extends JFrame {

    //PlantType activePlantingBrush = PlantType.None;

    public GameWindow() {
        setSize(1012, 785);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLayout(null);

        JLabel energy = new JLabel("energy");
        energy.setLocation(50, 80);
        energy.setSize(60, 20);
        
        // First layer : show background of the world
        World gp = new World(energy);
        gp.setLocation(0, 0);
        getLayeredPane().add(gp, new Integer(0));
        
        // Second Layer : show guardian card
        GuardianCard bearcatCard = new GuardianCard(new ImageIcon(this.getClass().getResource("images/card_peashooter.png")).getImage());
        bearcatCard.setLocation(110, 8);
        getLayeredPane().add(bearcatCard, new Integer(1));

        // Third layer : show the energy score board
        getLayeredPane().add(energy, new Integer(2));
        setResizable(false);
        setVisible(true);
    }

    static GameWindow gw;

    public static void main(String[] args) {
        gw = new GameWindow();
    }

}
