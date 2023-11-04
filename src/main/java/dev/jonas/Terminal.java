package dev.jonas;

import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * The {@code UserIO} class represents the user input.
 * It is used to get input from the user, and display messages to the user.
 * <p>
 *   This class has the following methods:
 *   <ul>
 *     <li>{@link #print(String)}</li>
 *     <li>{@link #println(String)}</li>
 *     <li>{@link #getValidStringInput(String)}</li>
 *     <li>{@link #getValidIntInput(String)}</li>
 *     <li>{@link #waitForUserInput()}</li>
 *     <li>{@link #clearScreen()}</li>
 *   </ul>
 * </p>
 *
 * @author Jonas Birkeli
 * @version 1.0.0
 * @since 1.0.0
 */
public class Terminal {
  private final Scanner scanner;

  /**
   * Constructs a new {@code UserInput} with the given parameters.
   *
   * @since 1.0.0
   */
  public Terminal() {
    scanner = new Scanner(System.in);
  }

  /**
   * Prints the given message to the console. Does not include newline character at the end.
   *
   * @param message The message to print.
   * @since 1.0.0
   */
  public void print(String message) {
    System.out.print(message);
  }

  /**
   * Prints the given message to the console. Does include newline character at the end.
   *
   * @param message The message to print.
   * @since 1.0.0
   */
  public void println(String message) {
    System.out.println(message);
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
        // If no exception is thrown, the input is valid.
        validInput = true;
      } catch (NumberFormatException | NoSuchElementException | IllegalStateException e) {
        System.out.println("Input must be of non-empty String type. Please try again.");
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
    int input = 0;

    while (!validInput) {
      System.out.println(inputMessage);
      // Tries to get user input, catches exceptions if input is invalid
      try {
        input = Integer.parseInt(scanner.nextLine());
        // If no exception is thrown, the input is valid.
        validInput = true;
      } catch (NumberFormatException | NoSuchElementException | IllegalStateException e) {
        System.out.println("Input must be of Integer type. Please try again."); // Error message
      }
    }
    return input;
  }

  /**
   * Waits for user input, and halts the program until user enters something.
   *
   * @since 1.0.0
   */
  public void waitForUserInput() {
    System.out.println("Press enter to continue...");
    scanner.nextLine(); // Does not store user input, only waits for input
  }

  /**
   * Clears the screen.
   * <p>
   *   This method uses ANSI escape codes to clear the screen.
   *   This method is not supported on all operating systems.
   *   If the method is not supported, the screen is not cleared.
   *   <br>
   *   <a href="https://stackoverflow.com/a/29752985">Source</a>
   *   <br>
   *   <a href="https://en.wikipedia.org/wiki/ANSI_escape_code">ANSI escape codes</a>
   *   <br>
   *   <a href="https://en.wikipedia.org/wiki/ANSI_escape_code#CSI_sequences">CSI sequences</a>
   *   <br>
   *   <a href="https://en.wikipedia.org/wiki/ANSI_escape_code#Escape_sequences">Escape sequences</a>
   *   <br>
   * </p>
   *
   * @since 1.0.0
   */
  public void clearScreen() {
    System.out.print("\033[H\033[2J");
    System.out.flush();
  }
}
