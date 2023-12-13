package guardiansOfTheEcosystem;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Grid extends JPanel implements MouseListener {

    private ActionListener al;

    public Grid() {
        setBorder((Border) new LineBorder(Color.RED));
        setOpaque(false);
        addMouseListener(this);
        setSize(100, 120);
    }

    public Guardian assignedGuardian;

    public void setGuardian(Guardian p) {
        assignedGuardian = p;
    }

    public void removeGuardian() {
        assignedGuardian.stop();
        assignedGuardian = null;
    }

    public boolean isInsideGrid(int tx) {
        return (tx > getLocation().x) && (tx < getLocation().x + 100);
    }

    public void setAction(ActionListener al) {
        this.al = al;
    }


    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (al != null) {
            al.actionPerformed(new ActionEvent(this, ActionEvent.RESERVED_ID_MAX + 1, ""));
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
    public void resetGuardian() {
        assignedGuardian = null;
    }

}
