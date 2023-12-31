package utility;

/**
 * The {@code InputValidator} class is used to validate input.
 * This class is used to validate input.
 * It is used to check if the input meat the requirements by the given parameters.
 *
 * @author Jonas Birkeli
 * @version 1.0.0
 * @since 1.0.0
 */
public class InputValidator {

  /**
   * Checks if the input is parsable to an integer, and if the integer is within the given range.
   *
   * @param input The input to check.
   *              This input is expected to be parsable to an integer.
   * @param min The minimum value the input can be.
   * @param max The maximum value the input can be.
   * @return {@code true} if the input is parsable to an integer, {@code false} otherwise.
   * @since 1.0.0
   */
  public boolean validateIntInput(String input, int min, int max) {
    // Expects input to be valid
    boolean isValid = true;

    try {
      int testInt = Integer.parseInt(input);

      // Input must be positive
      if (testInt < min) {
        isValid = false;
      }
      if (testInt > max) {
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
  public boolean validateStringInput(String input, int maxLength) {
    // Expects the input to be invalid
    boolean isValid = true;

    if (maxLength == -1) {
      maxLength = Integer.MAX_VALUE;
    }

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
      if (input.length() > maxLength) {
        isValid = false;
      }
    }
    // If the input has been invalid for any of the above, the input is invalid.
    return isValid;
  }

}
