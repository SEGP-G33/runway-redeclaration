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
    public RunwayParameters calcCase1(Integer distFromThresh){
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
     * @return the new set of runway parameters
     */
    public RunwayParameters calcCase2(Integer distFromThresh){
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
     * Calculates runway parameters the same as calcCase1, but this time returns them as a String describing the working out
     * This is important as it allows the redeclaration calculators to compare their working out with the software results
     *
     * @param distFromThresh The distance of the obstacle from the threshold of the runway
     * @return A string describing the calculations
     */
    public String calcAsStringCase1(Integer distFromThresh){
        RunwayParameters params = this.calcCase1(distFromThresh);
        String result = "Complete Calculations: Taking off towards or landing towards obstacle";

        // Adds each calculated value on a new line of the result
        result += String.format("\t- TORA: %s + %s = %s\n", distFromThresh, runway.getDisplacedThreshold(), params.getTORA());
        result += String.format("\t- ASDA: %s\n", params.getASDA());
        result += String.format("\t- TODA: %s\n", params.getTODA());
        result += String.format("\t- LDA : %s - %s - %s = %s\n", distFromThresh, runway.getRESALength(), runway.getStripEndLength(), params.getLDA());

        return result;
    }

    /**
     * Calculates runway parameters the same as calcCase2, but this time returns them as a String describing the working out
     * This is important as it allows the redeclaration calculators to compare their working out with the software results
     *
     * @param distFromThresh The distance of the obstacle from the threshold of the runway
     * @return A string describing the calculations
     */
    public String calcAsStringCase2(Integer distFromThresh){
        RunwayParameters params = this.calcCase1(distFromThresh);
        String result = "Complete Calculations: Taking off away or landing over obstacle";
        Double slopeCalc = this.getSlopeCalculation();

        // Adds each calculated value on a new line of the result
        result += String.format("\t- TORA: %s - %s - %s - %s = %s\n", runway.getDefaultParameters().getTORA(), plane.getBlastProtection(), distFromThresh, runway.getDisplacedThreshold(), params.getTORA());
        result += String.format("\t- ASDA: %s + %s = %s\n", params.getTORA(), runway.getStopWayLength(), params.getASDA());
        result += String.format("\t- TODA: %s + %s = %s\n", params.getTORA(), runway.getClearWayLength(), params.getTODA());
        result += String.format("\t- LDA : %s - %s - %s - %s = %s\n", runway.getDefaultParameters().getLDA(), distFromThresh, slopeCalc, runway.getStripEndLength(), params.getLDA());
        return result;
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
        return (this.obstacle.getHeight() * this.plane.getSlope());
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
