package utility;

import static config.Colors.RED_BRIGHT;
import static config.Colors.RESET;

/**
 * Class for handling user output.
 * This class makes it easy to adjust values and/or add new features to the application,
 * like adding colors to the output, or changing the output stream.
 *
 * @author Jonas Birkeli
 * @version 1.0.0
 * @since 1.0.0
 */
public class Printer {

  /**
   * Prints a message to the standard output, including the newline character.
   * <p>
   *   This method is equivalent to {@code System.out.println(message)}.
   *   <br>
   *   <a href="https://docs.oracle.com/javase/8/docs/api/java/lang/System.html#out">System.out</a>
   * </p>
   *
   * @param message The message to print.
   * @since 1.0.0
   */
  public void println(String message) {
    System.out.println(message);
  }

  /**
   * Prints a message to the standard output, excluding the newline character.
   * This method is equivalent to {@code System.out.print(message)}.
   *
   * @param message The message to print.
   * @since 1.0.0
   */
  public void print(String message) {
    System.out.print(message);
  }

  /**
   * Clears the screen.
   * This method uses ANSI escape codes to clear the screen.
   * This method is not supported on all operating systems.
   * If the method is not supported, the screen is not cleared.
   * This method has been created using CoPilot.
   *
   * @since 1.0.0
   */
  public void clearScreen() {
    // Produced by CoPilot
    System.out.print("\033[H\033[2J");
    System.out.flush();
  }

  /**
   * Prints an error message to the standard error stream colored in red,
   * including the newline character.
   * This method is equivalent to {@code System.err.println(message)}.
   *
   * @param message The error message to be printed to user.
   */
  public void printError(String message) {
    System.err.println(RED_BRIGHT + message + RESET);
  }
}
