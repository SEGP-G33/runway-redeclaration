package seg.g33.Entitites;

public class Runway {

    private final Airport airport;
    private final Integer angle;
    private final Character direction;
    // These define a runway in the spec
    private final Double defTORA;
    private final Double defASDA;
    private final Double defTODA;
    private final Double defLDA;
    // We may decide that these 4 instance variables are redundant or better used elsewhere
    private Double clearWayLength = 0d;
    private Double stopWayLength = 0d;
    private Double RESALength = 240d;
    private Double stripEndLength = 0d;
    private Double displacedThreshold = 0d;

    public Runway(Airport airport, Integer angle, Character direction, Double defTORA, Double defASDA, Double defTODA, Double defLDA,
                  Double clearWayLength, Double stopWayLength, Double RESALength, Double stripEndLength, Double displacedThreshold){
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

        // Guard statement for default TORA
        if (defTORA > 0){
            this.defTODA = defTODA;
        } else {
            throw new IllegalArgumentException("TODA must be greater than 0");
        }

        // Guard statement for default ASDA
        if (defASDA > 0){
            this.defASDA = defASDA;
        } else {
            throw new IllegalArgumentException("ASDA must be greater than 0");
        }

        // Guard statement for default TORA
        if (defTORA > 0){
            this.defTORA = defTORA;
        } else {
            throw new IllegalArgumentException("TORA must be greater than 0");
        }

        // Guard statement for default LDA
        if (defLDA > 0){
            this.defLDA = defLDA;
        } else {
            throw new IllegalArgumentException("LDA must be greater than 0");
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

        // Guard statement for displacement threshold
        if (displacedThreshold > 0){
            this.displacedThreshold = displacedThreshold;
        } else {
            throw new IllegalArgumentException("Displacement threshold must be greater than 0");
        }
    }

    public Runway(Airport airport, Integer angle, Character direction, Double defTORA, Double defASDA, Double defTODA, Double defLDA){
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

        // Guard statement for default TORA
        if (defTORA > 0){
            this.defTODA = defTODA;
        } else {
            throw new IllegalArgumentException("TODA must be greater than 0");
        }

        // Guard statement for default ASDA
        if (defASDA > 0){
            this.defASDA = defASDA;
        } else {
            throw new IllegalArgumentException("ASDA must be greater than 0");
        }

        // Guard statement for default TORA
        if (defTORA > 0){
            this.defTORA = defTORA;
        } else {
            throw new IllegalArgumentException("TORA must be greater than 0");
        }

        // Guard statement for default LDA
        if (defLDA > 0){
            this.defLDA = defLDA;
        } else {
            throw new IllegalArgumentException("LDA must be greater than 0");
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

    public Double getDefTORA() {
        return defTORA;
    }

    public Double getDefASDA() {
        return defASDA;
    }

    public Double getDefTODA() {
        return defTODA;
    }

    public Double getDefLDA() {
        return defLDA;
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

    public Double getDisplacedThreshold(){
        return this.displacedThreshold;
    }

    public RunwayParameters getDefaultParameters(){
        return new RunwayParameters(this.defTORA, this.defASDA, this.defTODA, this.defLDA);
    }

    /**
     * -=-=-=-=-=-=-=-
     * Setter Methods
     * -=-=-=-=-=-=-=-
     */
    public void setClearWayLength(Double clearWayLength) {
        this.clearWayLength = clearWayLength;
    }

    public void setStopWayLength(Double stopWayLength) {
        this.stopWayLength = stopWayLength;
    }

    public void setRESALength(Double RESALength) {
        this.RESALength = RESALength;
    }

    public void setStripEndLength(Double stripEndLength) {
        this.stripEndLength = stripEndLength;
    }

    public void setDisplacedThreshold(Double displacedThreshold) {
        this.displacedThreshold = displacedThreshold;
    }
}
