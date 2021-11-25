package code.plant;

public class PeaShooter extends Plant {
    private static String name = "PeaShooter";

    public PeaShooter(int x, int y) {
        super(x, y, name, speed, health, damage);
        loadImage("/images/plants/peashooter.gif");
        getImageDimensions();
    }

    public void shoot() {
    }
}
