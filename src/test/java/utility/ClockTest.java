package utility;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ClockTest {
  Clock clock;

  @BeforeEach
  void setUp() {
    // Create a new clock before each test
    clock = new Clock(4, 20);
  }

  @AfterEach
  void tearDown() {
    // Remove reference to clock
    clock = null;
  }

  @Test
  void setTime() {
    // Test that the time is set correctly with positive and negative tests
    clock.setTime(10, 9);
    assertEquals(10, clock.getHour());
    assertNotEquals(10, clock.getMinute());
    assertEquals(9, clock.getMinute());
  }

  @Test
  void testSetTime() {
    // Test that the time is set correctly with positive and negative tests
    clock.setTime(4, 20);
    assertEquals(10, clock.getHour());
    assertNotEquals(10, clock.getMinute());
    assertEquals(9, clock.getMinute());
  }

  @Test
  void getTimePositiveTest() {
    // Test that the time is set correctly with positive and negative tests
    clock.setTime(40, 10);
    assertNotEquals(40, clock.getTime()[0], "Hour is not set correctly with overflow");
    assertEquals(16, clock.getTime()[0], "Hour is set correctly with overflow");
    assertEquals(10, clock.getTime()[1], "Minute is set correctly with overflow");
  }

  @Test
  void getHour() {
    // Test that the hour is set correctly with positive and negative tests
    clock.setTime(15, 8);
    assertEquals(15, clock.getHour(), "Hour is set correctly");
    assertNotEquals(10, clock.getHour(), "Hour is not set correctly");
  }

  @Test
  void getMinutePositiveTest() {
    // Test that the minute is set correctly with positive and negative tests
    clock.setTime(15, 8);
    assertEquals(8, clock.getMinute(), "Minute is set correctly");

  }

  @Test
  void getMinuteNegativeTest() {
    // Test that the minute is set correctly with positive and negative tests
    clock.setTime(15, 8);
    assertNotEquals(10, clock.getMinute(), "Minute is not set correctly");
  }

  @Test
  void getTimeAsStringPositiveTest() {
    // Test that the time is set correctly with positive and negative tests
    clock.setTime(15, 8);
    assertEquals("15:08", clock.getTimeAsString(), "Time is set correctly");

  }

  @Test
  void getTimeAsStringNegativeTest() {
    // Test that the time is set correctly with positive and negative tests
    clock.setTime(15, 8);
    assertNotEquals("10:08", clock.getTimeAsString(), "Time is not set correctly");
  }

  @Test
  void combineDelayPositiveTest() {
    // Test that the delay is set correctly with positive and negative tests
    Clock testClock = new Clock(4, 20);
    // 4 + 4 = 8
    // 20 + 20 = 40
    clock = clock.combine(testClock);
    assertEquals(8, clock.getHour());
    assertNotEquals(4, clock.getHour());
    assertEquals(40, clock.getMinute());
    assertNotEquals(20, clock.getMinute());
  }
}