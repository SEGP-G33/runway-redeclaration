package seg.g33.Entitites;

public class Obstacle {
    private String name;
    private double height;

    public Obstacle(Double height, String name){
        this.height = height;
        this.name = name;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double h) {
        this.height = h;
    }

    public String getName() {
        return name;
    }

    public void setName(String n) {
        this.name = n;
    }

    public Obstacle(double height) {
        this.height = height;
    }

    public Obstacle(String name){this.name = name;}
}
