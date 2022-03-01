package seg.g33.Entitites;


/**
 * Runway Parameters is a data structure which contains the various parameters defining a re-declared runway
 * This includes the TORA, TODA, ASDA and LDA
 * These will be Set by the Calculator object
 * And will be retrieved by the view to be displayed to the user
 */
public class RunwayParameters {

    private Runway runway;
    private Character direction;
    private Integer TORA;   // Take-Off Runway Available
    private Integer  TODA;  // Take-Off Distance Available
    private Integer ASDA;   // Accelerate-Stop Distance Available
    private Integer LDA;    // Landing Distance Available
    /**
     * These two are not mentioned in the UML, but are in the spec
     * May become necessary, may become redundant
     */
    private Integer ALS;    // Approach Landing Surface
    private Integer TOCS;   // Take-Off Climb Surface
    private Integer RESA;   // Runway End Safety Area

    /**
     * -=-=-=-=-=-=-=-
     * Getter Methods
     * -=-=-=-=-=-=-=-
     */

    public Runway getName() {
        return this.runway;
    }

    public Character getDirection() {
        return this.direction;
    }

    public Integer getTORA() {
        return this.TORA;
    }

    public Integer getTODA() {
        return this.TODA;
    }

    public Integer getASDA() {
        return this.ASDA;
    }

    public Integer getLDA(){
        return this.LDA;
    }

    public Integer getALS(){
        return this.ALS;
    }

    public Integer getTOCS(){
        return this.TOCS;
    }

    public Integer getRESA() {
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

    public void setTORA(Integer TORA) {
        this.TORA = TORA;
    }

    public void setTODA(Integer TODA) {
        this.TODA = TODA;
    }

    public void setASDA(Integer ASDA) {
        this.ASDA = ASDA;
    }

    public void setLDA(Integer LDA) {
        this.LDA = LDA;
    }

    public void setALS(Integer ALS) {
        this.ALS = ALS;
    }

    public void setTOCS(Integer TOCS) {
        this.TOCS = TOCS;
    }

    public void setRESA(Integer RESA){
        this.RESA = RESA;
    }
}
