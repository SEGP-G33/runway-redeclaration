package seg.g33.Entitites;

public class RunwaySection {

    private Runway runway;
    private Double angle;
    private Double displaced;
    private RunwayParameters parameters;

    // TODO: This might not be needed? IDK?
    // Getters and setters aren't provided for these yet.
    private Double RESA;
    private Double stripend;
    private Double stopway;
    private Double clearway;

    /**
     * Constructor
     * @param runway The runway that this section is a part of
     * @param angle The angle of the runway. Should be 1-36
     * @param displaced The displaced threshold
     * @param parameters Runway parameters
     */
    public RunwaySection(Runway runway, Double angle, Double displaced, RunwayParameters parameters) {
        this.runway = runway;
        this.angle = angle;
        this.displaced = displaced;
        this.parameters = parameters;
    }

    public Runway getRunway() {
        return runway;
    }

    public void setRunway(Runway runway) {
        this.runway = runway;
    }

    public Double getAngle() {
        return angle;
    }

    public void setAngle(Double angle) {
        this.angle = angle;
    }

    public Double getDisplaced() {
        return displaced;
    }

    public void setDisplaced(Double displaced) {
        this.displaced = displaced;
    }

    public RunwayParameters getParameters() {
        return parameters;
    }

    public void setParameters(RunwayParameters parameters) {
        this.parameters = parameters;
    }
}
