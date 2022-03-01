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
     * @param distFromThresh The distance of the obstacle from the threshold of the runway
     * @return the complete set of runway parameters
     */
    public RunwayParameters calculateCase1(Integer distFromThresh){
        RunwayParameters params = new RunwayParameters();

        Double TORA = distFromThresh + runway.getDisplacedThreshold();
        Double ASDA = TORA;
        Double TODA = TORA;
        Double LDA = distFromThresh - runway.getRESALength() - runway.getStripEndLength();

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
     * @param distFromThresh The distance of the obstacle from the threshold of the runway
     * @return
     */
    public RunwayParameters calculateCase2(Integer distFromThresh){
        RunwayParameters params = new RunwayParameters();
        Double slopeCalc = this.getSlopeCalculation();

        Double TORA = this.runway.getDefaultParameters().getTORA() - this.plane.getBlastProtection() - distFromThresh - this.runway.getDisplacedThreshold();
        Double ASDA = TORA + this.runway.getStopWayLength();
        Double TODA = TORA + this.runway.getClearWayLength();
        Double LDA = this.runway.getDefaultParameters().getLDA() - distFromThresh - slopeCalc - this.runway.getStripEndLength();

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

    private Double getSlopeCalculation(){
        return (double) (this.obstacle.getHeight() * 50);
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
