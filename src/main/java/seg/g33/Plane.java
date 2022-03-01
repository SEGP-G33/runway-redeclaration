package seg.g33;

public class Plane {
    private String name;
    private double blastProtection, slope;
    private static final double DEFAULT_BLAST_ALLOWANCE = 300, DEFAULT_SLOPE = 50;

    public Plane(String name, Double blastProtection, Double slope){
        this.name = name;
        if(blastProtection == null){
            this.blastProtection = DEFAULT_BLAST_ALLOWANCE;
        }
        else{
            this.blastProtection = blastProtection;
        }
        if(slope == null){
            this.slope = DEFAULT_SLOPE;
        }
        else{
            this.slope = slope;
        }
    }

    public double getSlope() {
        return slope;
    }

    public double getBlastProtection() {
        return blastProtection;
    }

    public Plane(String name){
        this(name, null, null);
    }

    public String getName() {
        return name;
    }


}
