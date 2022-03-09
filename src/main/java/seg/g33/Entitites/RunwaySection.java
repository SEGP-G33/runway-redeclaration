package seg.g33.Entitites;

public class RunwaySection {

    private Runway runway;
    private Integer angle;
    private RunwayParameters defaultParameters;

    // TODO: This might not be needed? IDK?
    // Getters and setters aren't provided for these yet.
    private Double clearWayLength = 0d;
    private Double stopWayLength = 0d;
    private Double RESALength = 240d;
    private Double stripEndLength = 0d;
    private Double displacedThreshold = 0d;

    /**
     * Constructor
     * @param runway The runway that this section is a part of
     * @param angle The angle of the runway. Should be 1-36
     * @param displacedThreshold The displaced threshold
     * @param parameters Runway parameters
     */
    public RunwaySection(Runway runway, Integer angle, Double displacedThreshold, RunwayParameters parameters) {
        this.runway = runway;
        this.angle = angle;
        this.displacedThreshold = displacedThreshold;
        this.defaultParameters = parameters;
    }

    /**
     * Second constructor, useful for when a runway has extra conditions to take into account such as the clearWay,
     * stopway or RESA
     * @param runway
     * @param angle
     * @param displacedThreshold
     * @param parameters
     * @param clearWayLength
     * @param stopWayLength
     * @param RESALength
     * @param stripEndLength
     */
    public RunwaySection(Runway runway, Integer angle, Double displacedThreshold, RunwayParameters parameters,
                         Double clearWayLength, Double stopWayLength, Double RESALength, Double stripEndLength) {
        this.runway = runway;
        this.angle = angle;
        this.displacedThreshold = displacedThreshold;
        this.defaultParameters = parameters;
        this.clearWayLength = clearWayLength;
        this.stopWayLength = stopWayLength;
        this.RESALength = RESALength;
        this.stripEndLength = stripEndLength;
    }


    /**
     * -=-=-=-=-=-=-=-
     * Getter Methods
     * -=-=-=-=-=-=-=-
     */

    public Runway getRunway() {
        return runway;
    }

    public Integer getAngle() {
        return angle;
    }

    public Double getDisplacedThreshold() {
        return displacedThreshold;
    }


    /**
     * -=-=-=-=-=-=-=-
     * Getter Methods
     * -=-=-=-=-=-=-=-
     */
    public void setRunway(Runway runway) {
        this.runway = runway;
    }

    public void setAngle(Integer angle) {
        this.angle = angle;
    }

    public void setDisplaced(Double displaced) {
        this.displacedThreshold = displaced;
    }

    public void setDefaultParameters(RunwayParameters defaultParameters) {
        this.defaultParameters = defaultParameters;
    }

    public RunwayParameters getDefaultParameters() {
        return defaultParameters;
    }

    public Double getClearWayLength() {
        return clearWayLength;
    }

    public Double getStopWayLength() {
        return stopWayLength;
    }

    public Double getRESALength() {
        return RESALength;
    }

    public Double getStripEndLength() {
        return stripEndLength;
    }

    /**
     * toString method used for debugging.
     * @return
     */
    @Override
    public String toString() {
        return "RunwaySection{" +
                "runway=" + runway +
                ", angle=" + angle +
                ", displaced=" + displaced +
                ", parameters=" + parameters +
                ", RESA=" + RESA +
                ", stripend=" + stripend +
                ", stopway=" + stopway +
                ", clearway=" + clearway +
                '}';
    }
}
