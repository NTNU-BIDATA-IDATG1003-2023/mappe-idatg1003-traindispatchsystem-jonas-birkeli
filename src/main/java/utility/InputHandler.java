package utility;

import java.util.NoSuchElementException;
import java.util.Scanner;
import lang.UserTextFeedback;

/**
 * Class for handling user input.
 *
 * @version 1.0.0
 * @since 1.0.0
 */
public class InputHandler {
  Scanner scanner;
  InputValidator inputValidator;
  Printer printer;

  /**
   * Constructor for InputHandler.
   * Creates a new Scanner object.
   *
   * @since 1.0.0
   */
  public InputHandler() {
    scanner = new Scanner(System.in);
    inputValidator = new InputValidator();
    printer = new Printer();
  }

  /**
   * Returns a valid input from the user.
   * Continuously asks for user input until a valid input is given.
   * If the input is not valid, an error message is displayed.
   * If the input is valid, the input is returned.
   *
   * @param inputMessage Message to display before waiting for user input.
   * @param maxLength The maximum length the input can be. -1 for unset.
   * @return The user input as a string.
   * @since 1.0.0
   */
  public String getValidStringInput(String inputMessage, int maxLength) {
    // Variables used in the method
    boolean validInput = false;
    String input = "";

    while (!validInput) {
      printer.println(inputMessage);
      // Tries to get user input, catches exceptions if input is invalid
      try {
        input = scanner.nextLine();

        if (!inputValidator.validateStringInput(input, maxLength)) {
          printer.printError(UserTextFeedback.INPUT_INVALID);
          printer.println(String.format(UserTextFeedback.INVALID_INPUT_LENGTH, maxLength));
        } else {
          // Input has been validated and is valid
          validInput = true;
        }
      } catch (NoSuchElementException | IllegalStateException e) {
        printer.printError(UserTextFeedback.INPUT_INVALID);
        // Error message
      }
    }
    return input;
  }

  /**
   * Returns a valid input from the user.
   * Continuously asks for user input until a valid input is given.
   * If the input is not valid, an error message is displayed.
   * If the input is valid, the input is returned.
   *
   * @param inputMessage Message to display before waiting for user input.
   * @param min The minimum value the input can be.
   * @param max The maximum value the input can be.
   * @return The user input as an integer.
   * @since 1.0.0
   */
  public int getValidIntInput(String inputMessage, int min, int max) {
    // Variables used in the method
    boolean validInput = false;
    String input;
    int inputAsInt = 0;

    while (!validInput) {
      // Tries to get user input, catches exceptions if input is invalid
      input = getValidStringInput(inputMessage, -1);

      if (!inputValidator.validateIntInput(input, min, max)) {
        printer.printError(String.format(UserTextFeedback.INPUT_INVALID_NUMBER_RANGE, min, max));
        continue;
      }

      inputAsInt = Integer.parseInt(input);

      // If no exception is thrown, the input is valid.
      validInput = true;
    }
    return inputAsInt;
  }

  /**
   * Waits for user input, and halts the program until user enters something.
   *
   * @since 1.0.0
   */
  public void waitForUserInput() {
    printer.println("Press enter to continue...");
    scanner.nextLine(); // Does not store user input, only waits for input
  }
}
