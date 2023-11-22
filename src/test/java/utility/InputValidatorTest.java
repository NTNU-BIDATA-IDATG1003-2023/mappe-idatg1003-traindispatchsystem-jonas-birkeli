package utility;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class InputValidatorTest {
  InputValidator inputValidator;

  @BeforeEach
  void setUp() {
    inputValidator = new InputValidator();
  }

  @AfterEach
  void tearDown() {
    inputValidator = null;
  }

  @Test
  void isAcceptableInt() {
    // Testing for positive and negative integers
    // by using the same min and max values
    assertTrue(inputValidator.validateIntInput("1", 1, 1));
    assertTrue(inputValidator.validateIntInput("1", 0, 2));
    assertTrue(inputValidator.validateIntInput("1", 1, 1));
    assertFalse(inputValidator.validateIntInput("0", 1, 1));
    assertFalse(inputValidator.validateIntInput("-1", 1, 1));
    assertFalse(inputValidator.validateIntInput("", 1, 1));
  }

  @Test
  void inputIsValid() {
    // Testing for full string, emtpy, blank and null
    assertTrue(inputValidator.validateStringInput("1"));
    assertFalse(inputValidator.validateStringInput(""));
    assertFalse(inputValidator.validateStringInput(" "));
    assertFalse(inputValidator.validateStringInput(null));
  }
}