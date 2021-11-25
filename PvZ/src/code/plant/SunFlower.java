package code.plant;

public class SunFlower extends Plant {
    private static String name = "SunFlower";

    public SunFlower(int x, int y) {
        super(x, y, name, speed, health, damage);
        loadImage("/images/plants/sun_flower.gif");
        getImageDimensions();
    }

    
}
