package code.gui;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.SwingConstants;
import javax.swing.Timer;

import code.Collider;
import code.Sun;
import code.bullet.Bullet;
import code.plant.PeaShooter;
import code.plant.Plant;
import code.plant.SunFlower;
import code.plant.Torchwood;
import code.plant.Wallnut;
import code.zombie.ConeheadZombie;
import code.zombie.FootballZombie;
import code.zombie.NormalZombie;
import code.zombie.Zombie;

/**
 * @author Vu Viet Phong
 */
public class Background extends JLayeredPane implements MouseMotionListener {
    private Image bgImg;
    private final int BG_WIDTH = 1300;
    private final int BG_HEIGHT = 769;
    private final int DELAY = 10;
    
    private Image lmImg;
    private int[] rowlm = {150, 270, 390, 510, 630}; 

    private int xMouse;
    private int yMouse;

    private boolean ingame;
    private Collider[] colliders;
    private ArrayList<ArrayList<Zombie>> laneZombies;
    private ArrayList<ArrayList<Bullet>> lanePeas;

    private Timer sunProducer;
    private Timer redrawTimer;
    private Timer advancerTimer;
    private Timer zombieProducer;

    /**SUN */
    private int sunScore;
    private JLabel sunScoreboard;

    public int getSunScore() {
        return sunScore;
    }

    public void setSunScore(int sunScore) {
        this.sunScore = sunScore;
        sunScoreboard.setText(String.valueOf(sunScore));
        sunScoreboard.setFont(new Font("Open Sans", 1, 30));
        sunScoreboard.setHorizontalTextPosition(SwingConstants.CENTER);
    }

    private ArrayList<Sun> activeSuns;

    public ArrayList<Sun> getActiveSuns() {
        return activeSuns;
    }

    public void setActiveSuns(ArrayList<Sun> activeSuns) {
        this.activeSuns = activeSuns;
    }

    public Background(JLabel sunScoreboard) {
        this.sunScoreboard = sunScoreboard;
        setSunScore(150);
        setLayout(null);
        setSize(BG_WIDTH, BG_HEIGHT);
        addMouseMotionListener(this);
        
        ingame = true;
        bgImg = new ImageIcon(this.getClass().getResource("/images/background.jpg")).getImage();
        lmImg = new ImageIcon(this.getClass().getResource("/images/items/Lawn_Mower.png")).getImage();

        laneZombies = new ArrayList<>();
        laneZombies.add(new ArrayList<>()); //line 1
        laneZombies.add(new ArrayList<>()); //line 2
        laneZombies.add(new ArrayList<>()); //line 3
        laneZombies.add(new ArrayList<>()); //line 4
        laneZombies.add(new ArrayList<>()); //line 5

        lanePeas = new ArrayList<>();
        lanePeas.add(new ArrayList<>()); //line 1
        lanePeas.add(new ArrayList<>()); //line 2
        lanePeas.add(new ArrayList<>()); //line 3
        lanePeas.add(new ArrayList<>()); //line 4
        lanePeas.add(new ArrayList<>()); //line 5

        colliders = new Collider[45];
        for (int i = 0; i < 45; i++) {
            Collider a = new Collider();
            a.setLocation(44 + (i % 9) * 100, 109 + (i / 9) * 120);
            a.setAction(new Control((i % 9), (i / 9)));
            colliders[i] = a;
            add(a, new Integer(0));
        }

        //colliders[0].setPlant(new FreezePeashooter(this,0,0));
/*
        colliders[9].setPlant(new Peashooter(this,0,1));
        laneZombies.get(1).add(new NormalZombie(this,1));*/

        activeSuns = new ArrayList<>();

        redrawTimer = new Timer(25, (ActionEvent e) -> {
            repaint();
        });
        redrawTimer.start();

        advancerTimer = new Timer(60, (ActionEvent e) -> advance());
        advancerTimer.start();

        sunProducer = new Timer(5000, (ActionEvent e) -> {
            Random rnd = new Random();
            Sun sta = new Sun(rnd.nextInt(800) + 100, 0, rnd.nextInt(300) + 200, this);
            activeSuns.add(sta);
            add(sta, new Integer(1));
        });
        sunProducer.start();

        
    }

    private void advance() {
        inGame();
        updateSun();
        updateZombies();
        updateBullets();
        checkCollision();
        repaint();
    }

    private void inGame() {
        if (!ingame) {
            advancerTimer.stop();
        }
    }

    private void updateSun() {
        for (int i = 0; i < activeSuns.size(); i++) {
            activeSuns.get(i).advance();
        }
    }

    private void updatePlants() {
        
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
            } else {
                zombies.remove(i);
            }
        }
    }

    public void checkCollision() {
        List<Bullet> bs = plant.getBullets();

        for (Bullet b : bs) {
            Rectangle r1 = b.getBounds();

            for (Zombie zombie : zombies) {
                Rectangle r2 = zombie.getBounds();

                if (r1.intersects(r2)) {
                    zombie.setHealth(zombie.getHealth() - b.getDamage());
                    b.setVisible(false);
                    
                    if (zombie.getHealth() == 0) {
                        zombie.setVisible(false);
                    }
                }
            }
        }

        Rectangle r3 = plant.getBounds();

        for (Zombie zombie : zombies) {
            Rectangle r2 = zombie.getBounds();
            
            if (r2.intersects(r3)) {
                plant.setHealth(plant.getHealth() - zombie.getDamage());

                if (plant.getHealth() == 0) {
                    plant.setVisible(false);
                }
            }
        }
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
            drawObjects(g);
        }
    }

    private void drawObjects(Graphics g) {
        // Draw background
        g.drawImage(bgImg, 0, 0, this.getWidth(), this.getHeight(), this);

        // Draw lawnmover
        for (int i = 0; i < 5; i++) {
            g.drawImage(lmImg, 240, rowlm[i], 100, 80, this);
        }
        
        // Draw plants
        for (int i = 0; i < 45; i++) {
            Collider c = colliders[i];
            if (c.assignedPlant != null) {
                Plant plant = c.assignedPlant;
                if (plant instanceof SunFlower) {
                    g.drawImage(plant.getImage(), plant.getX(), plant.getY(), 75, 75, this);
                } else if (plant instanceof PeaShooter) {
                    g.drawImage(plant.getImage(), plant.getX(), plant.getY(), 75, 75, this);
                } else if (plant instanceof Torchwood) {
                    g.drawImage(plant.getImage(), plant.getX(), plant.getY(), 100, 100, this);
                } else {
                    g.drawImage(plant.getImage(), plant.getX(), plant.getY(), 75, 75, this);
                }
            }
        }
    }

    private int[] rows;
    private int[] columns;
    
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
        columns[0] = 350;
        columns[1] = 450;
        columns[2] = 550;
        columns[3] = 650;
        columns[4] = 750;
        columns[5] = 850;
        columns[6] = 950;
        columns[7] = 1050;
        columns[8] = 1150;
    }

    // Ensures that the user can't randomly place plants in the world but only in the grid.
    public int returnGridRowPosition(int y) {
        int row;
        int[] rowGrid = {30, 150, 270, 390, 510, 630};

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
        int[] columnGrid = {300, 400, 500, 600, 700, 800, 900, 1000, 1100, 1200};

        for (column = 0; column < 9; column++) {
            if(x > columnGrid[column] && x < columnGrid[column + 1]) {
                return columns[column];
            }
        }

        return -1;
    }

    /** Control Mouse */
    private Play.PlantType active = Play.PlantType.None;

    public Play.PlantType getActivePlantingBrush() {
        return active;
    }

    public void setActivePlantingBrush(Play.PlantType active) {
        this.active = active;
    }

    private class Control implements ActionListener {
        int x;
        int y;

        public Control(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if (active == Play.PlantType.Sunflower && getSunScore() >= 50) {
                colliders[x + y * 9].setPlant(new SunFlower(x, y));
                setSunScore(getSunScore() - 50);
            }

            if (active == Play.PlantType.Peashooter && getSunScore() >= 100) {
                colliders[x + y * 9].setPlant(new PeaShooter(x, y));
                setSunScore(getSunScore() - 100);
            }

            if (active == Play.PlantType.Peashooter2 && getSunScore() >= 100) {
                colliders[x + y * 9].setPlant(new PeaShooter(x, y));
                setSunScore(getSunScore() - 100);
            }

            if (active == Play.PlantType.Torchwood && getSunScore() >= 175) {
                colliders[x + y * 9].setPlant(new Torchwood(x, y));
                setSunScore(getSunScore() - 175);
            }

            if (active == Play.PlantType.Wallnut && getSunScore() >= 50) {
                colliders[x + y * 9].setPlant(new Wallnut(x, y));
                setSunScore(getSunScore() - 50);
            }

            active = Play.PlantType.None;
        }
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        xMouse = e.getX();
        yMouse = e.getY();
    }
}
