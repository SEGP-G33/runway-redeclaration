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


    /**
     * Getters
     */
    public String getName() {
        return name;
    }

    public Double getBlastProtection() {
        return blastProtection;
    }

    public Double getSlope() {
        return slope;
    }

    /**
     * Setters
     */
    public void setName(String name) {
        this.name = name;
    }

    public void setBlastProtection(Double blastProtection) {
        this.blastProtection = blastProtection;
    }

    public void setSlope(Double slope) {
        this.slope = slope;
    }

    /**
     * toString method used for simple debugging
     */
    @Override
    public String toString() {
        return "Plane{" +
                "\n\t- name='" + name + '\'' +
                "\n\t- blastProtection=" + blastProtection +
                "\n\t- slope=" + slope +
                "\n}";
    }
}
