package code.gui;

import java.awt.Image;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

import code.zombie.Zombie;

public class Background extends JPanel implements ActionListener {
    private final int BG_WIDTH = 1800;
    private final int BG_HEIGHT = 1000;

    private Image bgImg;
    private Image lmImg;
    private Image zImg;

    private int sunScore;
    private JLabel sunScoreboard;

    private Timer timer;

    private final int DELAY = 10;
    private boolean ingame;
    private List<Zombie> zombies;
    private Play.PlantType active = Play.PlantType.None;

    public Background(JLabel sunScoreboard) {
        setSize(BG_WIDTH, BG_HEIGHT);
        setLayout(null);
        setFocusable(true);
        ingame = true;
        this.sunScoreboard = sunScoreboard;
        setSunScore(150);

        bgImg = new ImageIcon(this.getClass().getResource("/images/background.png")).getImage();
        lmImg = new ImageIcon(this.getClass().getResource("/images/items/Lawn_Mower.png")).getImage();
        zImg = new ImageIcon(this.getClass().getResource("/images/zombies/conehead_zombie.gif")).getImage();
        setRowsCoordinates();
        setColumnsCoordinates();

        initZombies(40);

        timer = new Timer(DELAY, this);
        timer.start();
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
            int num = rd.nextInt(3) + 1;
            zombies.add(Zombie.getZombie(num, listZombies[i]));
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
        g.drawImage(zImg, 1700, 267, this);
        int[] cols = {210, 366, 522, 678, 834}; 
        for (int i = 0; i < 5; i++) {
            g.drawImage(lmImg, 330, cols[i], 100, 80, this);
        }
        
        /*
        for (Zombie zombie : zombies) {
            if (zombie.isVisible()) {
                g.drawImage(zombie.getImage(), 1800, zombie.getY(), this);
            }
        }*/
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

    private int[] rows;
    private int[] columns;

    // Possible Row Cordinates
    private void setRowsCoordinates() {
        rows = new int[5];
        rows[0] = 267;
        rows[1] = 446;
        rows[2] = 642;
        rows[3] = 816;
        rows[4] = 1010;
    }

    // Possible Column Cordinates
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
        updateZombies();
    }

    private void inGame() {
        if (!ingame) {
            timer.stop();
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
            }
        }
    }
}
