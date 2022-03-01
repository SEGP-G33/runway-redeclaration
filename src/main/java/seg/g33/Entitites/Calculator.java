package seg.g33.Entitites;

/**
 * Calculator class
 *
 * Contains all logic for calculating new parameters given any runway with an obstacle on it
 */
public class Calculator {

    private String name;
    private Plane plane;
    private Obstacle obstacle;
    private Runway runway;

    /**
     * Calculates the new runway parameters in the case that a plane is taking off towards and obstacle,
     * or landing towards an obstacle
     *
     * @return the complete set of runway parameters
     */
    public RunwayParameters calculateCase1(){
        RunwayParameters params = new RunwayParameters();
        Integer distFromThresh = this.getDistanceFromThreshold();

        Integer TORA = distFromThresh + runway.getDisplacedThreshold();
        Integer ASDA = TORA;
        Integer TODA = TORA;
        Integer LDA = distFromThresh - runway.getRESALength() - runway.getStripEndLength();

        params.setTORA(TORA);
        params.setASDA(ASDA);
        params.setTODA(TODA);
        params.setLDA(LDA);
        return params;
    }

    /**
     * Calculates the new runway parameters in the case that a plane is taking off away from an obstacle,
     * or landing over the obstacle
     *
     * @return the complete set of runway parameters
     */
    public RunwayParameters calculateCase2(){
        RunwayParameters params = new RunwayParameters();
        Integer distFromThresh = this.getDistanceFromThreshold();
        Integer slopeCalc = this.getSlopeCalculation();

        Integer TORA = this.runway.getDefaultParameters().getTORA() - this.plane.getBlastProtection() - distFromThresh - this.runway.getDisplacedThreshold();
        Integer ASDA = TORA + this.runway.getStopWayLength();
        Integer TODA = TORA + this.runway.getClearWayLength();
        Integer LDA = this.runway.getDefaultParameters().getLDA() - distFromThresh - slopeCalc - this.runway.getStripEndLength();

        params.setTORA(TORA);
        params.setASDA(ASDA);
        params.setTODA(TODA);
        params.setLDA(LDA);
        return params;
    }

    /**
     * -=-=-=-=-=-=-=-
     * Getter Methods
     * -=-=-=-=-=-=-=-
     */
    public Plane getPlane() {
        return plane;
    }

    public Obstacle getObstacle() {
        return obstacle;
    }

    public Runway getRunway() {
        return runway;
    }

    public Integer getDistanceFromThreshold(){
        // TODO
        return 0;
    }

    public Integer getSlopeCalculation(){
        // TODO
        return 0;
    }

    /**
     * -=-=-=-=-=-=-=-
     * Setter Methods
     * -=-=-=-=-=-=-=-
     */
    public void setPlane(Plane plane) {
        this.plane = plane;
    }

    public void setObstacle(Obstacle obstacle) {
        this.obstacle = obstacle;
    }

    public void setRunway(Runway runway) {
        this.runway = runway;
    }
}
