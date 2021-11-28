package code;

import java.awt.Image;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import code.gui.Background;


public class Sun extends JPanel implements MouseListener {
    private int locX;
    private int locY;
    private int endY;
    private int speed = 2;
    private Image image;
    private int destruct = 200;
    private Background gp;

    public Sun(int locX, int locY, int endY, Background gp) {
        image = new ImageIcon(this.getClass().getResource("/images/sun.png")).getImage();
        this.locX = locX;
        this.locY = locY;
        this.gp = gp;
        setSize(80, 80);
        setOpaque(false);
        setLocation(locX, locY);
        addMouseListener(this);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image, 0, 0, null);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void mousePressed(MouseEvent e) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void mouseExited(MouseEvent e) {
        // TODO Auto-generated method stub
        
    }
}
