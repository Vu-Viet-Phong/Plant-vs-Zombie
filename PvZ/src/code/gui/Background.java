package code.gui;

import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

import code.bullet.Bullet;
import code.plant.Plant;
import code.zombie.Zombie;

public class Background extends JPanel implements ActionListener {
    private Image backgroundgImg;

    private int sunScore;
    private JLabel sunScoreboard;

    private boolean ingame;
    private Timer timer;
    private Plant plants;
    private List<Zombie> zombies;

    private final int DELAY = 10;
    private int[] rows;
    private int[] columns;
    private Play.PlantType activePlantingBrush = Play.PlantType.None;

    public Background(JLabel sunScoreboard) {
        setLayout(null);
        this.sunScoreboard = sunScoreboard;
        backgroundgImg = new ImageIcon(this.getClass().getResource("/images/background.png")).getImage();

        ingame = true;

        // initZombies();

        // timer = new Timer(DELAY, this);
        // timer.start();
    }

    public void initZombies() {
        zombies = new ArrayList<>();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (ingame) {
            g.drawImage(backgroundgImg, 0, 0, this.getWidth(), this.getHeight(), this);
            drawObjects(g);
        } 

        Toolkit.getDefaultToolkit().sync();
    }

    private void drawObjects(Graphics g) {
        if (plants.isVisible()) {
            g.drawImage(plants.getImage(), plants.getX(), plants.getY(), this);
        }

        List<Bullet> bs = plants.getBullets();
        for (Bullet bullet : bs) {
            if (bullet.isVisible()) {
                g.drawImage(bullet.getImage(), bullet.getX(), bullet.getY(), this);
            }
        }

        for (Zombie zombie : zombies) {
            if (zombie.isVisible()) {
                g.drawImage(zombie.getImage(), zombie.getX(), zombie.getY(), this);
            }
        }
    }

    private void drawGameOver(Graphics g) {
        
    }

    public int getSunScore() {
        return sunScore;
    }

    public void setSunScore(int sunScore) {
        this.sunScore = sunScore;
        sunScoreboard.setText(String.valueOf(sunScore));
    }

    public Play.PlantType getActivePlantingBrush() {
        return activePlantingBrush;
    }

    public void setActivePlantingBrush(Play.PlantType activePlantingBrush) {
        this.activePlantingBrush = activePlantingBrush;
    }

    /**
     * Possible Row Cordinates
     */
    private void setRowsCoordinates() {
        rows = new int[5];
        rows[0] = 267;
        rows[1] = 446;
        rows[2] = 642;
        rows[3] = 816;
        rows[4] = 1010;
    }
    
    /**
     * Possible Column Cordinates
     */
    private void setColumnsCoordinates() {
        columns = new int[9];
        columns[0] = 783;
        columns[1] = 924;
        columns[2] = 1066;
        columns[3] = 1208;
        columns[4] = 1350;
        columns[5] = 1487;
        columns[6] = 1633;
        columns[7] = 1775;
        columns[8] = 1916;
    }

    /**
     * Ensures that the user can't randomly place plants in the world but only in the grid.
     */
    public int returnGridRowPosition(int y) {
        int row;
        int[] rowGrid = {240, 434, 626, 822, 1011, 1205};
        
        for (row = 0; row < 5; row += 1) {
            if (y > rowGrid[row] && y < rowGrid[row + 1]) {
                return rows[row];
            }
        }

        return -1;
    }
    
    /**
     * Ensures that the user can't randomly place plants in the world but only in the grid.
     */
    public int returnGridColumnPosition(int x) {
        int column;
        int[] columnGrid = {469, 628, 787, 945, 1110, 1269, 1429, 1589, 1749, 1913};

        for (column = 0; column < 9; column += 1) {
            if(x > columnGrid[column] && x < columnGrid[column + 1]) {
                return columns[column];
            }
        }

        return -1;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        inGame();
        updatePlants();
        updateBullets();
        updateZombies();
        checkCollisions();
        repaint();
    }

    private void inGame() {
        if (!ingame) {
            timer.stop();
        }
    }

    private void updatePlants() {

        if (plants.isVisible()) {
            
        }
    }

    private void updateBullets() {
        List<Bullet> bullets = plants.getBullets();

        for (int i = 0; i  < bullets.size(); i++) {
            Bullet b = bullets.get(i);

            if (b.isVisible()) {
                b.move();
            } else {
                bullets.remove(i);
            }
        }
    }

    private void updateZombies() {
        if (zombies.isEmpty()) {
            ingame = false;
            return;
        }

        for (int i = 0; i < zombies.size(); i++) {
            Zombie z = zombies.get(i);

            if (z.isVisible()) {
                z.move();
            } else {
                zombies.remove(i);
            }
        }
    }

    public void checkCollisions() {
        Rectangle r3 = plants.getBounds();

        for (Zombie zombie : zombies) {
            Rectangle r2 = zombie.getBounds();
            if (r3.intersects(r2)) {
                
            }
        }

        List<Bullet> bs = plants.getBullets();

        for (Bullet b : bs) {
            Rectangle r1 = b.getBounds();
            for (Zombie zombie : zombies) {
                Rectangle r2 = zombie.getBounds();
                if (r1.intersects(r2)) {

                }
            }
        }
    }

    private void prepareLawnmowers() {
        
    }
}
