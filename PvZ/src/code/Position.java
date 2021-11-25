package code;

public class Position {
    private double locX = 0;
    private double locY = 0;

    public Position(double locX, double locY) {
        this.locX = locX;
        this.locY = locY;
    }

    public double getLocX() {
        return locX;
    }

    public void setLocX(double locX) {
        this.locX = locX;
    }

    public double getLocY() {
        return locY;
    }

    public void setLoccY(double locY) {
        this.locY = locY;
    }

    @Override
    public String toString() {
        return String.format("(%6.2f, %6.2f)", locX, locY);
    }
}
