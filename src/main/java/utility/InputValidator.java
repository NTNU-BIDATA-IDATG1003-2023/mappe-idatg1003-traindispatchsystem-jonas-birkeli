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
  public boolean inputIsParsable(String input) {
    boolean isValid = true;
    try {
      Integer.parseInt(input);
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
    // Expects the input to be valid
    boolean isValid = true;
    // Going through multiple checks to see if the input is valid.
    if (input == null) {
      isValid = false;
    }
    if (input.isEmpty()) {
      isValid = false;
    }
    if (input.isBlank()) {
      isValid = false;
    }

    // If the input has been invalid for any of the above, the input is invalid.
    return isValid;
  }

}
