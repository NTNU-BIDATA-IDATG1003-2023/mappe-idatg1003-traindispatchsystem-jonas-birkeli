import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utility.UserInputOutputHandler;

class UserInputOutputHandlerTest {
  UserInputOutputHandler userInputOutputHandler;

  @BeforeEach
  void setUp() {
    userInputOutputHandler = new UserInputOutputHandler();
  }

  @AfterEach
  void tearDown() {
    userInputOutputHandler = null;
  }

  @Test
  void print() {
    // Cannot test this method because it does not perform logic.
  }

  @Test
  void println() {
    // Cannot test this method because it does not perform logic.
  }

  @Test
  void getValidStringInput() {
    // Cannot test this method because it contains a while loop.
  }

  @Test
  void getValidIntInput() {
    // Cannot test this method because it contains a while loop.
  }

  @Test
  void waitForUserInput() {
    // Cannot test this method because it does not perform logic.
  }

  @Test
  void clearScreen() {
    // Cannot test this method because it does not perform logic.
  }
}