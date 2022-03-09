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

    public Calculator(String name, Plane plane, Obstacle obstacle, Runway runway){
        this.name = name;
        this.plane = plane;
        this.obstacle = obstacle;
        this.runway = runway;
    }

    public ArrayList<RunwayParameters> calculate(Double distFromLeftThreshold, Double distFromRightThreshold){
        RunwaySection lowerRunway = runway.getRunwaySections().get(0);
        RunwaySection upperRunway = runway.getRunwaySections().get(1);
        RunwayParameters lowerParams;
        RunwayParameters upperParams;

        if (distFromRightThreshold >= runway.getDefaultParameters().getTORA()){
            // object is closer to right hand side on 09L/27R runway
            lowerParams = calculateTowards(lowerRunway, distFromLeftThreshold);
            upperParams = calculateAway(upperRunway, distFromRightThreshold);
        } else {
            // object is closer to left hand side on 09L/27R runway
            lowerParams = calculateAway(lowerRunway, distFromLeftThreshold);
            upperParams = calculateTowards(upperRunway, distFromRightThreshold);
        }

        ArrayList<RunwayParameters> params = new ArrayList<>();
        params.add(lowerParams);
        params.add(upperParams);
        return params;
    }


    public RunwayParameters calculateTowards(RunwaySection section, Double distFromThreshold){
        Double TORA = distFromThreshold - slopeCalc() - section.getStripEndLength();
        Double LDA = distFromThreshold - section.getRESALength() - section.getStripEndLength();

        return new RunwayParameters(TORA, TORA, TORA, LDA);
    }

    public RunwayParameters calculateAway(RunwaySection section, Double distFromThreshold){
        RunwayParameters original = section.getDefaultParameters();
        Double TORA = original.getTORA() - plane.getBlastProtection() - distFromThreshold - section.getDisplacedThreshold();
        Double ASDA = TORA + section.getStopWayLength();
        Double TODA = TORA + section.getClearWayLength();
        Double LDA = original.getLDA() - distFromThreshold - section.getStripEndLength() - slopeCalc();

        return new RunwayParameters(TORA, ASDA, TODA, LDA);
    }

    public String calcAsString(Double distFromLeftThreshold, Double distFromRightThreshold){
        RunwaySection lowerRunway = runway.getRunwaySections().get(0);
        RunwaySection upperRunway = runway.getRunwaySections().get(1);
        String result = "Calculations:\n";
        String lowerParams;
        String upperParams;

        if (distFromRightThreshold >= runway.getDefaultParameters().getTORA()){
            // object is closer to right hand side on 09L/27R runway
            lowerParams = calcTowardsAsString(lowerRunway, distFromLeftThreshold);
            upperParams = calcAwayAsString(upperRunway, distFromRightThreshold);
        } else {
            // object is closer to left hand side on 09L/27R runway
            lowerParams = calcAwayAsString(lowerRunway, distFromLeftThreshold);
            upperParams = calcTowardsAsString(upperRunway, distFromRightThreshold);
        }

        result += lowerParams;
        result += upperParams;
        return result;
    }

    public String calcTowardsAsString(RunwaySection section, Double distFromThreshold){
        RunwayParameters params = calculateTowards(section, distFromThreshold);

        String result = String.format("\t Runway %s: TakeOff Toward Obstacle, Landing Toward Obstacle", section.getAngle());
        result += String.format("\t\t- TORA: %s - %s - %s = %s \n", distFromThreshold, slopeCalc(), section.getStripEndLength(), params.getTORA());
        result += String.format("\t\t- ASDA: %s = %s \n", params.getTORA(), params.getASDA());
        result += String.format("\t\t- TODA: %s = %s \n", params.getTORA(), params.getTODA());
        result += String.format("\t\t- LDA : %s - %s - %s = %s \n", distFromThreshold, section.getRESALength(), section.getStripEndLength(), params.getLDA());

        return result;
    }

    public String calcAwayAsString(RunwaySection section, Double distFromThreshold){
        RunwayParameters params = calculateAway(section, distFromThreshold);

        String result = String.format("\t Runway %s: TakeOff Away From Obstacle, Landing Over Obstacle", section.getAngle());
        result += String.format("\t\t- TORA: %s - %s - %s - %s = %s\n", section.getDefaultParameters().getTORA(), plane.getBlastProtection(), distFromThreshold, runway.getDisplacedThreshold(), params.getTORA());
        result += String.format("\t\t- ASDA: %s + %s = %s\n", params.getTORA(), section.getStopWayLength(), params.getASDA());
        result += String.format("\t\t- TODA: %s + %s = %s\n", params.getTORA(), runway.getClearWayLength(), params.getTODA());
        result += String.format("\t\t- LDA : %s - %s - %s - %s = %s\n", runway.getDefaultParameters().getLDA(), distFromThreshold, slopeCalc(), runway.getStripEndLength(), params.getLDA());

        return result;
    }
    public Double slopeCalc(){
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
