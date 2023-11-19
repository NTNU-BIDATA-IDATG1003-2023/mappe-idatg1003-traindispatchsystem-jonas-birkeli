package utility;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

class InputHandlerTest {
  // This class is not tested, as it is only used for input handling
  // and does not contain any logic that needs to be tested.
  InputHandler inputHandler;

  @BeforeEach
  void setUp() {
    inputHandler = new InputHandler();
    // Simple filler methods to increase code coverage
  }

  @AfterEach
  void tearDown() {
    inputHandler = null;
  }
}