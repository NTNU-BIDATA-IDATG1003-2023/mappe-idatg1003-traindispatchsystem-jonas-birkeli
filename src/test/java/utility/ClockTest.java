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
  void getTime() {
    // Test that the time is set correctly with positive and negative tests
    clock.setTime(40, 10);
    assertNotEquals(40, clock.getTime()[0]);
    assertEquals(16, clock.getTime()[0]);
    assertEquals(10, clock.getTime()[1]);
  }

  @Test
  void getHour() {
    // Test that the hour is set correctly with positive and negative tests
    clock.setTime(15, 8);
    assertEquals(15, clock.getHour());
    assertNotEquals(10, clock.getHour());
  }

  @Test
  void getMinute() {
    // Test that the minute is set correctly with positive and negative tests
    clock.setTime(15, 8);
    assertEquals(8, clock.getMinute());
    assertNotEquals(10, clock.getMinute());
  }

  @Test
  void getTimeAsString() {
    // Test that the time is set correctly with positive and negative tests
    clock.setTime(15, 8);
    assertEquals("15:08", clock.getTimeAsString());
    assertNotEquals("10:08", clock.getTimeAsString());
  }

  @Test
  void combineDelay() {
    // Test that the delay is set correctly with positive and negative tests
    Clock testClock = new Clock(4, 20);
    // 4 + 4 = 8
    // 20 + 20 = 40
    clock = clock.combineDelay(testClock);
    assertEquals(8, clock.getHour());
    assertNotEquals(4, clock.getHour());
    assertEquals(40, clock.getMinute());
    assertNotEquals(9, clock.getMinute());
  }
}