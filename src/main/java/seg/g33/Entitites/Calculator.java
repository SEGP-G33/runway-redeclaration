package seg.g33.Entitites;

import java.util.ArrayList;

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
     * Constructor
     * @param name the name for the calculator
     * @param plane the plane being calculated for
     * @param obstacle the obstacle on the runway
     * @param runway the runway the obstacle is on
     */
    public Calculator(String name, Plane plane, Obstacle obstacle, Runway runway){
        this.name = name;
        this.plane = plane;
        this.obstacle = obstacle;
        this.runway = runway;
    }

    /**
     * Calculate will return a pair of RunwayParameters after calculating the results for both logical runways
     * @return the pair of runwayParameters calculated
     */
    public ArrayList<RunwayParameters> calculate() throws IllegalArgumentException{
        Double distFromLeftThreshold = this.obstacle.getLeftDistance();
        Double distFromRightThreshold = this.obstacle.getRightDistance();
        RunwaySection lowerRunway = runway.getRunwaySections().get(0);
        RunwaySection upperRunway = runway.getRunwaySections().get(1);
        RunwayParameters lowerParams;
        RunwayParameters upperParams;
        if (distFromRightThreshold >= distFromLeftThreshold) {
            // object is closer to right hand side on 09L/27R runway
            lowerParams = calculateAway(lowerRunway, distFromLeftThreshold);
            upperParams = calculateTowards(upperRunway, distFromRightThreshold);
        } else {
            // object is closer to left hand side on 09L/27R runway
            lowerParams = calculateTowards(lowerRunway, distFromLeftThreshold);
            upperParams = calculateAway(upperRunway, distFromRightThreshold);
        }
        ArrayList<RunwayParameters> params = new ArrayList<>();
        params.add(lowerParams);
        params.add(upperParams);
        return params;
    }

    /**
     * Calculated a new RunwayParameter in the case that a plane is taking off towards, or landing towards the obstacle
     * @param section the logical runway section the obstacle is on
     * @param distFromThreshold the distance the obstacle is from the threshold
     * @return redeclared runwayParameters
     */
    private RunwayParameters calculateTowards(RunwaySection section, Double distFromThreshold) throws IllegalArgumentException{
        Double TORA = distFromThreshold + section.getDisplacedThreshold() - slopeCalc() - section.getStripEndLength();
        Double LDA = distFromThreshold - section.getRESALength() - section.getStripEndLength();

        return new RunwayParameters(TORA, TORA, TORA, LDA);
    }

    /**
     * Calculates a new RunwayParameter in the case that a plane is taking off away, or landing over the obstacle
     * @param section the logical runway section the obstacle is on
     * @param distFromThreshold the distance the obstacle is from the threshold
     * @return redeclared runwayParameters
     */
    private RunwayParameters calculateAway(RunwaySection section, Double distFromThreshold) throws IllegalArgumentException{
        RunwayParameters original = section.getDefaultParameters();
        Double TORA = original.getTORA() - plane.getBlastProtection() - distFromThreshold - section.getDisplacedThreshold();
        Double ASDA = TORA + section.getStopWayLength();
        Double TODA = TORA + section.getClearWayLength();
        Double LDA = original.getLDA() - distFromThreshold - section.getStripEndLength() - slopeCalc();

        return new RunwayParameters(TORA, ASDA, TODA, LDA);
    }

    /**
     * CalcAsString will breakdown the calculations so that a redeclarataion calculator can compare results with the program
     * @return a string with both logical runway parameter calculations broken down
     */
    public String calcAsString(){
        Double distFromLeftThreshold = this.obstacle.getLeftDistance();
        Double distFromRightThreshold = this.obstacle.getRightDistance();

        RunwaySection lowerRunway = runway.getRunwaySections().get(0);
        RunwaySection upperRunway = runway.getRunwaySections().get(1);
        String result = "Calculations:\n";
        String lowerParams;
        String upperParams;

        if (distFromRightThreshold >= distFromLeftThreshold){
            // object is closer to right hand side on 09L/27R runway
            lowerParams = calcAwayAsString(lowerRunway, distFromLeftThreshold);
            upperParams = calcTowardsAsString(upperRunway, distFromRightThreshold);
        } else {
            // object is closer to left hand side on 09L/27R runway
            lowerParams = calcTowardsAsString(lowerRunway, distFromLeftThreshold);
            upperParams = calcAwayAsString(upperRunway, distFromRightThreshold);
        }

        result += lowerParams;
        result += upperParams;
        return result;
    }

    /**
     * returns a string calculation breakdown in the case of a plane landing/ taking off towards the obstacle
     * @param section the logical runway section
     * @param distFromThreshold the distance from the threshold
     * @return A String breakdown of the calculations
     */
    private String calcTowardsAsString(RunwaySection section, Double distFromThreshold){
        RunwayParameters params = calculateTowards(section, distFromThreshold);

        String result = String.format("\t Runway %s: TakeOff Toward Obstacle, Landing Toward Obstacle\n", section.getAngle());
        result += String.format("\t\t- TORA: %s + %s - %s - %s = %s \n", distFromThreshold, section.getDisplacedThreshold(), slopeCalc(), section.getStripEndLength(), params.getTORA());
        result += String.format("\t\t- ASDA: %s = %s \n", params.getTORA(), params.getASDA());
        result += String.format("\t\t- TODA: %s = %s \n", params.getTORA(), params.getTODA());
        result += String.format("\t\t- LDA : %s - %s - %s = %s \n", distFromThreshold, section.getRESALength(), section.getStripEndLength(), params.getLDA());

        return result;
    }

    /**
     * returns a string calculation breakdown in the case of a plane landing/ taking off away from the obstacle
     * @param section the logical runway section
     * @param distFromThreshold the distance from the threshold
     * @return A String breakdown of the calculations
     */
    private String calcAwayAsString(RunwaySection section, Double distFromThreshold){
        RunwayParameters params = calculateAway(section, distFromThreshold);

        String result = String.format("\t Runway %s: TakeOff Away From Obstacle, Landing Over Obstacle\n", section.getAngle());
        result += String.format("\t\t- TORA: %s - %s - %s - %s = %s\n", section.getDefaultParameters().getTORA(), plane.getBlastProtection(), distFromThreshold, section.getDisplacedThreshold(), params.getTORA());
        result += String.format("\t\t- ASDA: %s + %s = %s\n", params.getTORA(), section.getStopWayLength(), params.getASDA());
        result += String.format("\t\t- TODA: %s + %s = %s\n", params.getTORA(), section.getClearWayLength(), params.getTODA());
        result += String.format("\t\t- LDA : %s - %s - %s - %s = %s\n", section.getDefaultParameters().getLDA(), distFromThreshold, slopeCalc(), section.getStripEndLength(), params.getLDA());

        return result;
    }

    /**
     * Returns the distance needed for the plane to land safely over the obstacle
     * @return the distance
     */
    private Double slopeCalc(){
        return obstacle.getHeight() * plane.getSlope();
    }

    /**
     *  Getters and Setters
     */

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Plane getPlane() {
        return plane;
    }

    public void setPlane(Plane plane) {
        this.plane = plane;
    }

    public Obstacle getObstacle() {
        return obstacle;
    }

    public void setObstacle(Obstacle obstacle) {
        this.obstacle = obstacle;
    }

    public Runway getRunway() {
        return runway;
    }

    public void setRunway(Runway runway) {
        this.runway = runway;
    }
}
