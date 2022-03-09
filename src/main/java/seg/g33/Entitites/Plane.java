package seg.g33.Entitites;

public class Plane {

    /**
     * Plane Properties
     */
    private String name;
    private Double blastProtection;
    private Double slope;

    /**
     * Constructor
     * @param name Plane Name
     * @param blastProtection Plane Blast Protection
     * @param slope Plane Slope
     */
    public Plane(String name, Double blastProtection, Double slope){
        this.name = name;
        this.blastProtection = blastProtection;
        this.slope = slope;
    }

    /**
     * Constructor just with Name
     * @param name Plane name
     */
    public Plane(String name) {
        this.name = name;
        this.blastProtection = 300.0;
        this.slope = 50.0;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getBlastProtection() {
        return blastProtection;
    }

    public void setBlastProtection(Double blastProtection) {
        this.blastProtection = blastProtection;
    }

    public Double getSlope() {
        return slope;
    }

    public void setSlope(Double slope) {
        this.slope = slope;
    }

    @Override
    public String toString() {
        return "Plane{" +
                "name='" + name + '\'' +
                ", blastProtection=" + blastProtection +
                ", slope=" + slope +
                '}';
    }
}
