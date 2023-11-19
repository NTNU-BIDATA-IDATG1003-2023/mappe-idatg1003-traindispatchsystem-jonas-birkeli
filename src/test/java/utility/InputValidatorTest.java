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
    assertTrue(inputValidator.isAcceptableInt("1", 1, 1));
    assertTrue(inputValidator.isAcceptableInt("1", 0, 2));
    assertTrue(inputValidator.isAcceptableInt("1", 1, 1));
    assertFalse(inputValidator.isAcceptableInt("0", 1, 1));
    assertFalse(inputValidator.isAcceptableInt("-1", 1, 1));
    assertFalse(inputValidator.isAcceptableInt("", 1, 1));
  }

  @Test
  void inputIsValid() {
    // Testing for full string, emtpy, blank and null
    assertTrue(inputValidator.inputIsValid("1"));
    assertFalse(inputValidator.inputIsValid(""));
    assertFalse(inputValidator.inputIsValid(" "));
    assertFalse(inputValidator.inputIsValid(null));
  }
}