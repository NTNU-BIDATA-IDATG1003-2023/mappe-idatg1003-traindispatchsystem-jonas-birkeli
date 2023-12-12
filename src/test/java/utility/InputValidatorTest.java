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
  void validateIntInputPositiveTest() {
    // Testing for positive and negative integers
    // by using the same min and max values
    assertTrue(inputValidator.validateIntInput("1", 1, 1), "1 is valid");
    assertTrue(inputValidator.validateIntInput("1", 0, 2), "1 is valid");
    assertTrue(inputValidator.validateIntInput("-1", -1, 0), "1 is valid");
    assertTrue(inputValidator.validateIntInput("1", 1, 1), "1 is valid");
    // Feedback messages produced by CoPilot
  }

  @Test
  void validateIntInputNegativeTest() {
    // Testing for negative integers and NaN
    assertFalse(inputValidator.validateIntInput("0", 1, 1), "0 is not valid as it is out of bounds");
    assertFalse(inputValidator.validateIntInput("-1", 1, 1), "-1 is not valid as it is out of bounds");
    assertFalse(inputValidator.validateIntInput("", 1, 1), "Empty string is not valid");
    assertFalse(inputValidator.validateIntInput(" ", 1, 1), "Blank string is not valid");
    assertFalse(inputValidator.validateIntInput(null, 1, 1), "Null string is not valid");
    // Feedback messages produced by CoPilot
  }

  @Test
  void validateStringInputPositiveTest() {
    assertTrue(inputValidator.validateStringInput("1", -1), "1 should be valid");
    assertTrue(inputValidator.validateStringInput("0", -1), "0 should be valid");
    assertTrue(inputValidator.validateStringInput("-1", -1), "-1 should be valid");
    assertTrue(inputValidator.validateStringInput("This is a very long string, but should still "
        + "be valid as it is not empty or blank", -1), "Long string should be valid");

    assertTrue(inputValidator.validateStringInput("13", 2), "Length of input is 2, should be valid");
    assertTrue(inputValidator.validateStringInput("51", -1), "Length of input is not set, should be valid");
    // Feedback messages produced by CoPilot
  }

  @Test
  void inputIsValidNegativeTest() {
    assertFalse(inputValidator.validateStringInput("", 1), "Empty string should not be valid");
    assertFalse(inputValidator.validateStringInput(" ", 1), "Blank string should not be valid");
    assertFalse(inputValidator.validateStringInput(null, 1), "Null string should not be valid");

    assertFalse(inputValidator.validateStringInput("13", 1), "Length of input larger than 1, should not be valid");
    assertFalse(inputValidator.validateStringInput("10000", 4), "Length of input larger than 4, should not be valid");
    // Feedback messages produced by CoPilot

  }
}