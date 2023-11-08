package utility;

import static config.ConfigurationOptions.INVALID_INPUT_MESSAGE;

import java.util.NoSuchElementException;
import java.util.Scanner;

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
   * @return The user input as a string.
   * @since 1.0.0
   */
  public String getValidStringInput(String inputMessage) {
    // Variables used in the method
    boolean validInput = false;
    String input = "";

    while (!validInput) {
      System.out.println(inputMessage);
      // Tries to get user input, catches exceptions if input is invalid
      try {
        input = scanner.nextLine();

        if (!inputValidator.inputIsValid(input)) {
          printer.println(INVALID_INPUT_MESSAGE);
          continue;
        }
        // If no exception is thrown, the input is valid.
        validInput = true;
      } catch (NoSuchElementException | IllegalStateException e) {
        printer.println(INVALID_INPUT_MESSAGE);
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
   * @return The user input as an integer.
   * @since 1.0.0
   */
  public int getValidIntInput(String inputMessage) {
    // Variables used in the method
    boolean validInput = false;
    String input = "";
    int inputAsInt = 0;

    while (!validInput) {
      System.out.println(inputMessage);
      // Tries to get user input, catches exceptions if input is invalid
      try {
        input = scanner.nextLine();

        if (!inputValidator.inputIsParsable(input)) {
          printer.println(INVALID_INPUT_MESSAGE);
          continue;
        }

        inputAsInt = Integer.parseInt(input);

        // If no exception is thrown, the input is valid.
        validInput = true;
      } catch (NoSuchElementException | IllegalStateException e) {
        printer.println(INVALID_INPUT_MESSAGE);
        // Error message
      }
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
