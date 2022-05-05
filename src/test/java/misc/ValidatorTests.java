package misc;

import javafx.scene.control.TextField;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import seg.g33.DataHolders.Environment;
import seg.g33.Helpers.Validator;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ValidatorTests {

    @DisplayName("Testing Validator: Empty text TextField")
    @Test
    public void testValidatorEmptyTextField() {
       var textField = new TextField();

        var result = Validator.textFieldHasBlankText(textField);
        assertEquals(result, true);
    }


}
