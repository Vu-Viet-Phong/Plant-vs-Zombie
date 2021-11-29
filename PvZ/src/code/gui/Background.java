package code.gui;

import java.awt.Image;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

import code.Lawnmower;
import code.bullet.Bullet;
import code.plant.*;
import code.zombie.*;

/**
 * @author Vu Viet Phong
 */
public class Background extends JPanel implements ActionListener {
    private final int BG_WIDTH = 1300;
    private final int BG_HEIGHT = 769;

    private Image bgImg;
    private Image lmImg;

    private int sunScore;
    private JLabel sunScoreboard;

    private Timer timer;

    private final int DELAY = 100;
    private boolean ingame;
    
    private int[] rows;
    private int[] columns;

    private int xMouse;
    private int yMouse;
    private int selectedButton = -1;
    private Boolean planted = false;

    private Plant plant;
    private Plant[][] plants;
    private List<Zombie> zombies;
    private Lawnmower lm;
    private int[] rowlm = {150, 270, 390, 510, 630}; 
    private Play.PlantType active = Play.PlantType.None;
    private Control control;

    public Background(JLabel sunScoreboard) {
        setSize(BG_WIDTH, BG_HEIGHT);
        setLayout(null);
        setFocusable(true);

        ingame = true;
        this.sunScoreboard = sunScoreboard;
        setSunScore(150);

        bgImg = new ImageIcon(this.getClass().getResource("/images/background.jpg")).getImage();
        lmImg = new ImageIcon(this.getClass().getResource("/images/items/Lawn_Mower.png")).getImage();
        
        setRowsCoordinates();
        setColumnsCoordinates();
        
        timer = new Timer(DELAY, this);
        timer.start();

        // control = new Control();

        // initPlants();
        initZombies(5);
    }

    public void initPlants() {
        int x = (xMouse - 550) / 160;
        int y = (yMouse - 330) / 191;
        int plantRow = returnGridRowPosition(xMouse);
        int plantCol = returnGridColumnPosition(yMouse);

        if (planted && plantRow != 1 && plantCol != 1) {
            if (selectedButton == 0) {
                plants[y][x] = new SunFlower(xMouse, yMouse);
                sunScore -= 50;
            } else if (selectedButton == 1) {
                plants[y][x] = new PeaShooter(xMouse, yMouse);
                sunScore -= 100;
            } else if (selectedButton == 2) {
                plants[y][x] = new PeaShooter(xMouse, yMouse);
                sunScore -= 200;
            } else if (selectedButton == 3) {
                plants[y][x] = new Torchwood(xMouse, yMouse);
                sunScore -= 175;
            } else {
                plants[y][x] = new Wallnut(xMouse, yMouse);
                sunScore -= 50;
            } 
        }

        selectedButton = -1;
        planted = false;
    }

    public void initZombies(int n) {
        Random rd = new Random();

        zombies = new ArrayList<>();
        int[] listZombies = new int[n];
        
        for (int i = 0; i < n; i++) {
            int num = rd.nextInt(5);
            listZombies[i] = rows[num];
        }
        
        for (int i = 0; i < n; i++) {
            int type = rd.nextInt(3) + 1;

            switch (type) {
                case 1:
                    zombies.add(new NormalZombie(listZombies[i]));
                    break;
                case 2:
                    zombies.add(new ConeheadZombie(listZombies[i]));
                    break;
                case 3:
                    zombies.add(new FootballZombie(listZombies[i]));
                    break;
            }
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (ingame) {
            doDrawing(g);
        }

        Toolkit.getDefaultToolkit().sync();
    }

    private void doDrawing(Graphics g) {
        g.drawImage(bgImg, 0, 0, this.getWidth(), this.getHeight(), this);

        
        for (int i = 0; i < 5; i++) {
            g.drawImage(lmImg, 240, rowlm[i], 100, 80, this);
        }

        /*
        //Draw plants
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 9; j++) {
                plant = plants[i][j];
                if (plant instanceof SunFlower) {
                    g.drawImage(plant.getImage(), plant.getX(), plant.getY(), 75, 75, this);
                } else if (plant instanceof PeaShooter) {
                    g.drawImage(plant.getImage(), plant.getX(), plant.getY(), 75, 75, this);
                } else if (plant instanceof Torchwood) {
                    g.drawImage(plant.getImage(), plant.getX(), plant.getY(), 100, 100, this);
                } else {
                    g.drawImage(plant.getImage(), plant.getX(), plant.getY(), 75, 75, this);
                }
            }{150, 270, 390, 510, 630}
        }
        */

        //Draw zombies
        for (Zombie zombie : zombies) {
            if (zombie.isVisible()) {
                g.drawImage(zombie.getImage(), zombie.getX(), zombie.getY(), this);
            }
        }
    }

    public int getSunScore() {
        return sunScore;
    }

    public void setSunScore(int sunScore) {
        this.sunScore = sunScore;
        sunScoreboard.setText(String.valueOf(sunScore));
    }
    
    public Play.PlantType getActivePlantingBrush() {
        return active;
    }

    public void setActivePlantingBrush(Play.PlantType active) {
        this.active = active;
    }

    // Possible Row Cordinates
    private void setRowsCoordinates() {
        rows = new int[5];
        rows[0] = 90;
        rows[1] = 210;
        rows[2] = 330;
        rows[3] = 450;
        rows[4] = 570;
    }

    // Possible Column Cordinates
    private void setColumnsCoordinates() {
        columns = new int[9];
        columns[0] = 550;
        columns[1] = 710;
        columns[2] = 870;
        columns[3] = 1030;
        columns[4] = 1190;
        columns[5] = 1350;
        columns[6] = 1510;
        columns[7] = 1670;
        columns[8] = 1830;
    }

    // Ensures that the user can't randomly place plants in the world but only in the grid.
    public int returnGridRowPosition(int y) {
        int row;
        int[] rowGrid = {240, 434, 626, 822, 1011, 1205};

        for (row = 0; row < 5; row++) {
            if (y > rowGrid[row] && y < rowGrid[row + 1]) {
                return rows[row];
            }
        }

        return -1;
    }

    // Ensures that the user can't randomly place plants in the world but only in the grid.
    public int returnGridColumnPosition(int x) {
        int column;
        int[] columnGrid = {469, 628, 787, 945, 1110, 1269, 1429, 1589, 1749, 1913};

        for (column = 0; column < 9; column++) {
            if(x > columnGrid[column] && x < columnGrid[column + 1]) {
                return columns[column];
            }
        }

        return -1;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        inGame();
        // updateBullets();
        updateZombies();
        // checkCollisions();
        repaint();
    }

    private void inGame() {
        if (!ingame) {
            timer.stop();
        }
    }

    private void updateBullets() {
        List<Bullet> bs = plant.getBullets();

        for (int i = 0; i < bs.size(); i++) {
            Bullet b = bs.get(i);

            if (b.isVisible()) {
                b.move();
            } else {
                bs.remove(i);
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
            } // else {
              //  zombies.remove(i);
            // }
        }
    }

    public void checkCollisions() {
        List<Bullet> bs = plant.getBullets();
        for (Bullet b : bs) {
            Rectangle r1 = b.getBounds();
            for (Zombie zombie : zombies) {
                Rectangle r2 = zombie.getBounds();
                if (r1.intersects(r2)) {
                    b.setVisible(false);
                    if (zombie.getHealth() == 0) {
                        zombie.setVisible(false);
                    } else {
                        int hp = zombie.getHealth() - b.getDamage();
                        Zombie.setHealth(hp);
                    }
                }
            }
        }

        Rectangle r3 = plant.getBounds();
        for (Zombie zombie : zombies) {
            Rectangle r2 = zombie.getBounds();
            if (r3.intersects(r2)) {
                if (plant.getHealth() == 0) {
                    plant.setVisible(false);
                } else {
                    int hp = plant.getHealth() - zombie.getDamage();
                    plant.setHealth(hp);
                }
            }
        }

        for (int i = 0; i < 5; i++) {
            Rectangle r4 = lm.getBounds();
            for (Zombie zombie : zombies) {
                Rectangle r2 = zombie.getBounds();
                if (r4.intersects(r2)) {
                    Lawnmower lms = new Lawnmower(330, rowlm[i]);

                    lm.move();
                    if (lm.getX() == (BG_WIDTH - 1)) {
                        lm.setVisible(false);
                    }
                    zombie.setVisible(false);
                }
            }
        }
    }

    private class Control implements MouseListener, MouseMotionListener {
        @Override
        public void mouseDragged(MouseEvent e) {
            // TODO Auto-generated method stub
        }

        @Override
        public void mouseMoved(MouseEvent e) {
            // TODO Auto-generated method stub
            
        }

        @Override
        public void mouseClicked(MouseEvent e) {
            xMouse = e.getX();
            yMouse = e.getY();

            // Card position
            if (xMouse < 235 && xMouse >= 0) {
                if (yMouse <= 250 && yMouse >= 100 && sunScore >= 50) {
                    selectedButton = 0;
                }
                if (yMouse <= 400 && yMouse >= 250 && sunScore >= 100) {
                    selectedButton = 1;
                }
                if (yMouse <= 550 && yMouse >= 400 && sunScore >= 200) {
                    selectedButton = 2;
                }
                if (yMouse <= 700 && yMouse >= 550 && sunScore >= 175) {
                    selectedButton = 3;
                }
                if (yMouse < 850 && yMouse >= 700 && sunScore >= 50) {
                    selectedButton = 4;
                }
            }

            // Plantable location
            if (xMouse <= 1914 && xMouse >= 496 && yMouse <= 1205 && yMouse > 240 && selectedButton != -1) {
                planted = true;
            }
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
}
