package utility;

/**
 * The {@code InputValidator} class is used to validate input.
 * <p>
 *   This class is used to validate input.
 *   It is used to check if the input is parsable to an integer.
 *   It is also used to check if the input is valid.
 * </p>
 *
 * @author Jonas Birkeli
 * @version 1.0.0
 * @since 1.0.0
 */
public class InputValidator {

  /**
   * Checks if the input is parsable to an integer.
   *
   * @param input The input to check.
   * @return {@code true} if the input is parsable to an integer, {@code false} otherwise.
   * @since 1.0.0
   */
  public boolean isAcceptableInt(String input) {
    // Expects input to be valid
    boolean isValid = true;

    try {
      int testInt = Integer.parseInt(input);

      // Input must be positive
      if (testInt < 0) {
        isValid = false;
      }
    } catch (NumberFormatException e) {
      isValid = false;
    }

    return isValid;
  }

  /**
   * Checks if the input is valid.
   * <p>
   *   This method checks if the input is {@code null}, empty or blank.
   *   If the input is {@code null}, empty or blank, the input is invalid.
   *   If the input is not {@code null}, empty or blank, the input is valid.
   * </p>
   *
   * @param input The input to check.
   * @return {@code true} if the input is valid, {@code false} otherwise.
   * @since 1.0.0
   */
  public boolean inputIsValid(String input) {
    // Expects the input to be invalid
    boolean isValid = true;

    if (input == null) {
      isValid = false;
    } else {
      // If the input is not null, empty or blank, the input is valid.

      // Going through multiple checks to see if the input is valid.
      if (input.isEmpty()) {
        isValid = false;
      }
      if (input.isBlank()) {
        isValid = false;
      }
    }
    // If the input has been invalid for any of the above, the input is invalid.
    return isValid;
  }

}
