package code.gui;

import java.awt.Image;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.Random;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

import code.Collider;
import code.Sun;
import code.bullet.Bullet;
import code.plant.*;
import code.zombie.Zombie;

public class Background extends JPanel implements MouseMotionListener {
    private final int BG_WIDTH = 1800;
    private final int BG_HEIGHT = 1000;

    private Image bgImg;
    private Image lmImg;

    private int sunScore;
    private JLabel sunScoreboard;

    private Collider[] colliders;

    private ArrayList<ArrayList<Zombie>> laneZombies;
    private ArrayList<ArrayList<Bullet>> lanePlants;
    private ArrayList<Sun> activeSuns;

    private Timer redrawTimer;
    private Timer advancerTimer;
    private Timer sunProducer;
    private Timer zombieProducer;
    
    private Play.PlantType active = Play.PlantType.None;
    private int mouseX, mouseY;

    public Background(JLabel sunScoreboard) {
        setSize(BG_WIDTH, BG_HEIGHT);
        setLayout(null);
        this.sunScoreboard = sunScoreboard;
        setSunScore(150);

        bgImg = new ImageIcon(this.getClass().getResource("/images/background.png")).getImage();
        lmImg = new ImageIcon(this.getClass().getResource("/images/items/Lawn_Mower.png")).getImage();
        
        colliders = new Collider[45];
        for (int i = 0; i < 45; i++) {
            Collider a = new Collider();
            a.setLocation(44 + (i % 9) * 100, 109 + (i / 9) * 120);
            a.setAction(new PlantActionListener((i % 9), (i / 9)));
            colliders[i] = a;
            add(a, new Integer(0));
        }

    }

    private void advance() {
        for (int i = 0; i < 5; i++) {
            for (Zombie z : laneZombies.get(i)) {
                z.advance();
            }

            for (int j = 0; j < lanePeas.get(i).size(); j++) {
                Bullet p = lanePeas.get(i).get(j);
                p.advance();
            }

        }

        for (int i = 0; i < activeSuns.size(); i++) {
            activeSuns.get(i).advance();
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        doDrawing(g);
    }

    private void doDrawing(Graphics g) {
        g.drawImage(bgImg, 0, 0, this.getWidth(), this.getHeight(), this);

        int[] cols = {210, 366, 522, 678, 834}; 
        for (int i = 0; i < 5; i++) {
            g.drawImage(lmImg, 330, cols[i], 100, 80, this);
        }
    }

    public int getSunScore() {
        return sunScore;
    }

    public void setSunScore(int sunScore) {
        this.sunScore = sunScore;
        sunScoreboard.setText(String.valueOf(sunScore));
    }
    
    private class PlantActionListener implements ActionListener {

        int x, y;

        public PlantActionListener(int x, int y) {
            this.x = x;
            this.y = y;
        }
        @Override
        public void actionPerformed(ActionEvent e) {
            if (active == Play.PlantType.Sunflower) {
                if (getSunScore() >= 50) {
                    colliders[x + y * 9].setPlant(new SunFlower(x, y));
                    setSunScore(getSunScore() - 50);
                }
            }
            if (active == Play.PlantType.Peashooter) {
                if (getSunScore() >= 100) {
                    colliders[x + y * 9].setPlant(new PeaShooter(x, y));
                    setSunScore(getSunScore() - 100);
                }
            }

            if (active == Play.PlantType.Wallnut) {
                if (getSunScore() >= 175) {
                    colliders[x + y * 9].setPlant(new Wallnut(x, y));
                    setSunScore(getSunScore() - 175);
                }
            }
            active = Play.PlantType.None;
        }
    }

    public Play.PlantType getActivePlantingBrush() {
        return active;
    }

    public void setActivePlantingBrush(Play.PlantType active) {
        this.active = active;
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        // TODO Auto-generated method stub
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        mouseX = e.getX();
        mouseY = e.getY();
    }
}
