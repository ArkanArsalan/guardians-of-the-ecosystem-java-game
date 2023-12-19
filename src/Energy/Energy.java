package Energy;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import Panels.World;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.*;

public class Energy extends JPanel implements MouseListener {

    private Image sunImage;

    private int xCoor;
    private int yCoor;
    private int endY;

	private World gp;
	
	private int isDestroy = 200;
	
    public Energy(World parent, int startX, int startY, int endY) {
        this.gp = parent;
        this.endY = endY;
        setSize(80, 80);
        setOpaque(false);
        xCoor = startX;
        yCoor = startY;
        setLocation(xCoor, yCoor);
        sunImage = new ImageIcon(this.getClass().getResource("images/sun.png")).getImage();
        addMouseListener(this);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(sunImage, 0, 0, null);
    }
    
    public void energyFall() {
    	// Move energy object down by 4 pixel
        if (yCoor < endY) {
            yCoor += 4;
        } else {
        	isDestroy--;
        	if (isDestroy < 0) {
        		gp.remove(this);
        		removeEnergyFromList();
        	}
        }
        setLocation(xCoor, yCoor);
    }
    
    public void removeEnergyFromList() {
		gp.getActiveEnergys().remove(this);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        gp.setEnergyScore(gp.getEnergyScore() + 25);
        gp.remove(this);
        removeEnergyFromList();
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
	
}
