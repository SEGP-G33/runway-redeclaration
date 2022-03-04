package seg.g33.Entitites;


/**
 * Runway Parameters is a data structure which contains the various parameters defining a re-declared runway
 * This includes the TORA, TODA, ASDA and LDA
 * These will be Set by the Calculator object
 * And will be retrieved by the view to be displayed to the user
 */
public class RunwayParameters {

    /**
     * Properties
     */
    private Runway runway;
    private Character direction;
    private Double TORA;   // Take-Off Runway Available
    private Double  TODA;  // Take-Off Distance Available
    private Double ASDA;   // Accelerate-Stop Distance Available
    private Double LDA;    // Landing Distance Available

    /**
     * These two are not mentioned in the UML, but are in the spec
     * May become necessary, may become redundant
     */
    private Double ALS;    // Approach Landing Surface
    private Double TOCS;   // Take-Off Climb Surface
    private Double RESA;   // Runway End Safety Area

    /**
     * Default constructor
     */
    public RunwayParameters() {
        this.ALS = 0d;
        this.TOCS = 0d;
        this.RESA = 0d;
    }

    /**
     * Parametrized constructor
     */
    public RunwayParameters(Double TORA, Double ASDA, Double TODA, Double LDA){
        this();

        this.TORA = TORA;
        this.ASDA = ASDA;
        this.TODA = TODA;
        this.LDA = LDA;
    }


    /**
     * -=-=-=-=-=-=-=-
     * Getter Methods
     * -=-=-=-=-=-=-=-
     */

    public Runway getRunway() {
        return this.runway;
    }

    public Character getDirection() {
        return this.direction;
    }

    public Double getTORA() {
        return this.TORA;
    }

    public Double getTODA() {
        return this.TODA;
    }

    public Double getASDA() {
        return this.ASDA;
    }

    public Double getLDA(){
        return this.LDA;
    }

    public Double getALS(){
        return this.ALS;
    }

    public Double getTOCS(){
        return this.TOCS;
    }

    public Double getRESA() {
        return this.RESA;
    }

    /**
     * -=-=-=-=-=-=-=-
     * Setter Methods
     * -=-=-=-=-=-=-=-
     */

    public void setRunway(Runway name) {
        this.runway = name;
    }

    public void setDirection(Character direction) throws IllegalArgumentException {
        if (direction.equals('L') || direction.equals('R')) {
            this.direction = direction;
        } else {
            throw new IllegalArgumentException("Direction must be \"L/R\" ");
        }
    }

    public void setTORA(Double TORA) {
        this.TORA = TORA;
    }

    public void setTODA(Double TODA) {
        this.TODA = TODA;
    }

    public void setASDA(Double ASDA) {
        this.ASDA = ASDA;
    }

    public void setLDA(Double LDA) {
        this.LDA = LDA;
    }

    public void setALS(Double ALS) {
        this.ALS = ALS;
    }

    public void setTOCS(Double TOCS) {
        this.TOCS = TOCS;
    }

    public void setRESA(Double RESA){
        this.RESA = RESA;
    }
}
