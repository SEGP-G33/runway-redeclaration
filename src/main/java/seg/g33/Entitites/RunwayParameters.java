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
    private Double TORA;   // Take-Off Runway Available
    private Double  TODA;  // Take-Off Distance Available
    private Double ASDA;   // Accelerate-Stop Distance Available
    private Double LDA;    // Landing Distance Available

    /**
     * Constructor
     * @param TORA Take Off Runway Available
     * @param ASDA Accelerate Stop Distance Available
     * @param TODA Take Off Distance Available
     * @param LDA Landing Distance Available
     */
    public RunwayParameters(Double TORA, Double ASDA, Double TODA, Double LDA){
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

    /**
     * -=-=-=-=-=-=-=-
     * Setter Methods
     * -=-=-=-=-=-=-=-
     */

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
}
