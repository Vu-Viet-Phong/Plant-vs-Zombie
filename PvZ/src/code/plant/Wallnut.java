package code.plant;

public class Wallnut extends Plant {
    private static String name = "Wallnut";
    private static int health = 500;

    public Wallnut(int x, int y) {
        super(x, y, name, speed, health, damage);
        loadImage("/images/plants/wall_");
    }
}
