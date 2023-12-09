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
  void isAcceptableIntPositiveTest() {
    // Testing for positive and negative integers
    // by using the same min and max values
    assertTrue(inputValidator.validateIntInput("1", 1, 1), "1 is valid");
    assertTrue(inputValidator.validateIntInput("1", 0, 2), "1 is valid");
    assertTrue(inputValidator.validateIntInput("-1", -1, 0), "1 is valid");
    assertTrue(inputValidator.validateIntInput("1", 1, 1), "1 is valid");

  }

  @Test
  void isAcceptableIntNegativeTest() {
    // Testing for negative integers
    assertFalse(inputValidator.validateIntInput("0", 1, 1), "0 is not valid as it is out of bounds");
    assertFalse(inputValidator.validateIntInput("-1", 1, 1), "-1 is not valid as it is out of bounds");
    assertFalse(inputValidator.validateIntInput("", 1, 1), "Empty string is not valid");
    assertFalse(inputValidator.validateIntInput(" ", 1, 1), "Blank string is not valid");
    assertFalse(inputValidator.validateIntInput(null, 1, 1), "Null string is not valid");
  }

  @Test
  void inputIsValidPositiveTest() {
    // Testing for full string, emtpy, blank and null
    assertTrue(inputValidator.validateStringInput("1"), "1 should be valid");
    assertTrue(inputValidator.validateStringInput("0"), "0 should be valid");
    assertTrue(inputValidator.validateStringInput("-1"), "-1 should be valid");
    assertTrue(inputValidator.validateStringInput("This is a very long string, but should still "
        + "be valid as it is not empty or blank"), "Long string should be valid");
  }

  @Test
  void inputIsValidNegativeTest() {
    // Testing for full string, emtpy, blank and null
    assertFalse(inputValidator.validateStringInput(""), "Empty string should not be valid");
    assertFalse(inputValidator.validateStringInput(" "), "Blank string should not be valid");
    assertFalse(inputValidator.validateStringInput(null), "Null string should not be valid");
  }
}