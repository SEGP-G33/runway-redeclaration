package seg.g33.Helpers;

import javafx.scene.control.TextField;
import seg.g33.Entitites.Runway;
import seg.g33.Entitites.RunwayParameters;
import seg.g33.Entitites.RunwaySection;

import java.util.regex.Pattern;

public final class Validator {

    /**
     * Constructor is private so no new instances can be created
     */
    private Validator() { }

    /**
     * Checks whether a given file is an XML file.
     * @param filename the name of the file to be checked.
     * @return true if it's an XML file, false otherwise
     */
    public static Boolean isXMLFile(String filename) {
        return filename.endsWith(".xml");
    }


    /**
     * Checks if all text fields in an array have numeric values.
     * @param fields the array of text fields to check for numeric values
     * @return true if all fields have numbers as values, false otherwise
     */
    public static boolean checkObstacleTextFieldsValid(TextField[] fields) {
        boolean areAllValid = true;
        for (TextField field : fields) {
            if (!isTextFieldNumber(field)) {
                areAllValid = false;
            }
        }

        return areAllValid;
    }

    /**
     * Checks if a given input string from a text field has a numeric value.
     * Source code adapted from: https://www.baeldung.com/java-check-string-number
     * @param inputTextField the text fields containing the string to be checked
     * @return true if input is a number. False otherwise
     */
    public static boolean isTextFieldNumber(TextField inputTextField) {
        String input = inputTextField.getText();

        Pattern pattern = Pattern.compile("-?\\d+(\\.\\d+)?");
        if (input == null) {
            return false;
        }
        return pattern.matcher(input).matches();
    }

    /**
     * Checks if all text fields in an array non blank text
     * @param fields the array of text fields to check for non blank text
     * @return true if all fields don't have blank text, false otherwise
     */
    public static boolean areAllFieldsValid(TextField[] fields) {
        boolean areAllValid = true;
        for (TextField field : fields) {
            if (textFieldHasBlankText(field)) {
                areAllValid = false;
            }
        }

        return areAllValid;
    }

    /**
     * Checks a given text fields for blank text
     * @param inputTextField the TextField to be checked for blank text
     * @return true if the text field has blank text, false otherwise
     */
    public static boolean textFieldHasBlankText(TextField inputTextField) {
        String input = inputTextField.getText();
        return input.isBlank();
    }


    /**
     * Checks that an obstacle has valid distances from the left and right side of the runway, if their distances don't add up to the length of the runway, it can cause problems for the calculator
     * @param runway the runway to check against
     * @param distFromLeft the distance of the obstacle from the left threshold
     * @param distFromRight the distance of the obstacle from the right threshold
     * @return true if it is valid
     */
    public static boolean distancesAreValid(Runway runway, Double distFromLeft, Double distFromRight){
        RunwaySection section1 = runway.getRunwaySections().get(0);
        RunwaySection section2 = runway.getRunwaySections().get(1);
        double length = Double.max(section1.getDefaultParameters().getTORA(), section2.getDefaultParameters().getTORA());
        return !((distFromLeft + distFromRight < length));
    }

    /**
     * Checks that a set of runway parameters is correct
     * @param section the runway section being validated
     * @return true if it is valid
     */
    public static boolean runwayParametersAreValid(RunwaySection section){
        RunwayParameters params = section.getDefaultParameters();
        return params.getTORA() + section.getClearWayLength() == params.getTODA() &&
                params.getTORA() + section.getStopWayLength() == params.getASDA() &&
                params.getTORA() - section.getDisplacedThreshold() == params.getLDA();
    }

    /**
     * Same as above but with raw inputs rather than fully formed runway
     * @param TODA TODA
     * @param TORA TORA
     * @param ASDA ASDA
     * @param LDA LDA
     * @param clearway Clearway
     * @param stopway Stopway
     * @param displace Displaced Threshold
     * @return true if parameters are valid
     */
    public static boolean runwayParametersAreValid(Double TODA, Double TORA, Double ASDA, Double LDA, Double clearway, Double stopway, Double displace){
        return TORA + clearway == TODA && TORA + stopway == ASDA && TORA - displace == LDA;
    }
}
