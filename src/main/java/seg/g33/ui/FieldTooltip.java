package seg.g33.ui;

import javafx.scene.control.Tooltip;
import javafx.util.Duration;

/**
 * Custom Tooltip class.
 * Same as the default JavaFX Tooltip class, just sets a quicker showDelay to 0.2.
 * Avoids having to call this everytime.
 */
public class FieldTooltip extends Tooltip  {

    /**
     * Constructor for FieldTooltip with showDelay set to 0.2 seconds.
     * @param tooltipLabel the text to be shown in the tooltip.
     */
    public FieldTooltip(String tooltipLabel) {
        super(tooltipLabel);
        this.setShowDelay(Duration.seconds(0.2));
    }

    /**
     * Constructor for FieldTooltip with setShowDelay set to a custom value.
     * @param tooltipLabel the text to be shown in the tooltip.
     * @param showDelay time, in seconds, for the showDelay property.
     */
    public FieldTooltip(String tooltipLabel, Double showDelay) {
        super(tooltipLabel);
        this.setShowDelay(Duration.seconds(showDelay));
    }

}
