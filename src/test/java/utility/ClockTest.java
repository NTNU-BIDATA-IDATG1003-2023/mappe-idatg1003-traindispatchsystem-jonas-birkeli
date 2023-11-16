package utility;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ClockTest {
  Clock clock;

  @BeforeEach
  void setUp() {
    clock = new Clock();
    clock.setTime(14, 30);
  }

  @AfterEach
  void tearDown() {
    clock = null;
  }

  @Test
  void testSetTime() {
    clock.setTime(23, 56);
    assertEquals(23, clock.getHour());
    assertEquals(56, clock.getMinute());
  }

  @Test
  void getTime() {
    assertEquals(14, clock.getTime()[0]);
    assertEquals(30, clock.getTime()[1]);
  }

  @Test
  void getHour() {
    assertEquals(14, clock.getHour());
  }

  @Test
  void getMinute() {
    assertEquals(30, clock.getMinute());
  }

  @Test
  void getTimeAsString() {
    assertEquals("14:30", clock.getTimeAsString());
  }

  @Test
  void combineDelay() {
    Clock newClock = clock.combineDelay(new Clock(4, 18));
    assertEquals("18:48", newClock.getTimeAsString());
  }
}