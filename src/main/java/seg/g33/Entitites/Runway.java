package seg.g33.Entitites;

public class Runway {

    private Airport airport;
    private Integer angle;
    private Character direction;
    private Double length;
    // We may decide that these 4 instance variables are redundant or better used elsewhere
    private Double clearWayLength;
    private Double stopWayLength;
    private Double RESALength;
    private Double stripEndLength;
    private Double displacedThreshold = 0d;

    /**
     * Constructor method for a runway
     * @param airport the airport (used to access safetyStandard)
     * @param angle the angle the runway heads toward
     * @param direction the direction, (L or R)
     * @param length the length of the runway
     * @param clearWayLength the length of the clearWay
     * @param stopWayLength the length of the stopWay
     * @param RESALength the length of the RESA
     * @param stripEndLength the length of the strip end
     */
    public Runway(Airport airport, Integer angle, Character direction, Double length,
                  Double clearWayLength, Double stopWayLength, Double RESALength, Double stripEndLength) throws IllegalArgumentException{

        // Airport assignment
        this.airport = airport;

        // Guard statement for angle
        if (angle >= 0 && angle <= 36) {
            this.angle = angle;
        } else {
            throw new IllegalArgumentException("Angle must be between 0 and 36");
        }

        // Guard statement for direction
        if (direction.equals('L') || direction.equals('R')) {
            this.direction = direction;
        } else {
            throw new IllegalArgumentException("Direction must be 'L' or 'R'");
        }

        // Guard statement for runway length
        if (length > 0){
            this.length = length;
        } else {
            throw new IllegalArgumentException("Length of the runway must be greater than 0");
        }

        // Guard statement for clearWayLength
        if (clearWayLength > 0) {
            this.clearWayLength = clearWayLength;
        } else {
            throw new IllegalArgumentException("Length of the clearway must be greater than 0");
        }

        // Guard statement for stopWayLength
        if (stopWayLength > 0){
            this.stopWayLength = stopWayLength;
        } else {
            throw new IllegalArgumentException("Length of stopWay must be greater than 0");
        }

        // Guard statement for RESA
        if (RESALength > 0) {
            this.RESALength = RESALength;
        } else {
            throw new IllegalArgumentException("RESA length must be greater than 0");
        }

        // Guard statement for strip end length
        if (stripEndLength > 0) {
            this.stripEndLength = stripEndLength;
        } else {
            throw new IllegalArgumentException("Strip end length must be greater than 0");
        }
    }


    /**
     * -=-=-=-=-=-=-=-
     * Getter Methods
     * -=-=-=-=-=-=-=-
     */

    public Airport getAirport() {
        return airport;
    }

    public Integer getAngle() {
        return angle;
    }

    public Character getDirection() {
        return direction;
    }

    public Double getLength() {
        return length;
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

    public RunwayParameters getDefaultParameters(){
        // TODO Create logic for returning default runway parameters
        return new RunwayParameters();
    }

    public Double getDisplacedThreshold(){
        return this.displacedThreshold;
    }

    /**
     * -=-=-=-=-=-=-=-
     * Setter Methods
     * -=-=-=-=-=-=-=-
     */

    public void setDisplacedThreshold(Double displacedThreshold){
        this.displacedThreshold = displacedThreshold;
    }
}
