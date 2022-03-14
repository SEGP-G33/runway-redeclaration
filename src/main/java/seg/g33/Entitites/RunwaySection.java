package seg.g33.Entitites;

public class RunwaySection {

    private Runway runway;
    private Integer angle;
    private Character direction;
    private RunwayParameters defaultParameters;

    // Getters and setters aren't provided for these yet.
    private Double clearWayLength = 0d;
    private Double stopWayLength = 0d;
    private Double RESALength = 240d;
    private Double stripEndLength = 60d;
    private Double displacedThreshold = 0d;

    /**
     * Constructor
     * @param runway The runway that this section is a part of
     * @param angle The angle of the runway. Should be 1-36
     * @param parameters Runway parameters
     */
    public RunwaySection(Runway runway, Integer angle, Character direction, RunwayParameters parameters) {
        this.runway = runway;
        this.angle = angle;
        this.direction = direction;
        this.defaultParameters = parameters;
    }

    /**
     * Second constructor, useful for when a runway has extra conditions to take into account such as the clearWay,
     * stopway or RESA
     * @param runway
     * @param angle
     * @param parameters
     * @param displacedThreshold
     * @param clearWayLength
     * @param stopWayLength
     * @param RESALength
     * @param stripEndLength
     */
    public RunwaySection(Runway runway, Integer angle, Character direction, RunwayParameters parameters, Double displacedThreshold,
                         Double clearWayLength, Double stopWayLength, Double RESALength, Double stripEndLength) {
        this.runway = runway;
        this.angle = angle;
        this.direction = direction;
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

    public Character getDirection(){
        return this.direction;
    }

    public Double getDisplacedThreshold() {
        return displacedThreshold;
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
     * -=-=-=-=-=-=-=-
     * Setter Methods
     * -=-=-=-=-=-=-=-
     */
    public void setRunway(Runway runway) {
        this.runway = runway;
    }

    public void setAngle(Integer angle) {
        this.angle = angle;
    }

    public void setDirection(Character direction){
        this.direction = direction;
    }

    public void setDisplaced(Double displaced) {
        this.displacedThreshold = displaced;
    }

    public void setDefaultParameters(RunwayParameters params){
        this.defaultParameters = params;
    }

    /**
     * toString method used for debugging.
     * @return A string describing the runway section
     */
    @Override
    public String toString() {
        return "RunwaySection{" +
                "\n\t- runway=" + runway +
                "\n\t- angle=" + angle +
                "\n\t- direction=" + direction +
                "\n\t-  defaultParameters=" + defaultParameters +
                "\n\t-  clearWayLength=" + clearWayLength +
                "\n\t-  stopWayLength=" + stopWayLength +
                "\n\t-  RESALength=" + RESALength +
                "\n\t-  stripEndLength=" + stripEndLength +
                "\n\t-  displacedThreshold=" + displacedThreshold +
                "\n}";
    }
}
