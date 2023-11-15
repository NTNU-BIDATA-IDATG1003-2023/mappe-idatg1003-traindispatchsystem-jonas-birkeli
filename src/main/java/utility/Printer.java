package utility;

/**
 * Class for handling user output.
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
   * This method has been created using CoPilot. <br>
   *
   * @since 1.0.0
   */
  public void clearScreen() {
    System.out.print("\033[H\033[2J");
    System.out.flush();
  }

  /**
   * Prints an error message to the standard error stream, including the newline character.
   * This method is equivalent to {@code System.err.println(message)}.
   *
   * @param message
   */
  public void printError(String message) {
    System.err.println(message);
  }
}
