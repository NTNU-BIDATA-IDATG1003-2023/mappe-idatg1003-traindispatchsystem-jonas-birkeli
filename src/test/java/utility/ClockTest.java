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
  void setTimePositiveTest() {
    // Test that the time is set correctly with positive tests
    clock.setTime(10, 9);
    assertEquals(10, clock.getHour(), "Hour is set correctly, is 10");
    assertEquals(9, clock.getMinute(), "Minute is set correctly, is 9");
  }
  @Test
  void setTimeNegativeTest() {
    // Test that the time is set correctly with negative tests
    clock.setTime(10, 9);
    assertNotEquals(4, clock.getHour(), "Hour is not set correctly, should be 10");
    assertNotEquals(20, clock.getMinute(), "Minute is not set correctly, should be 9");
  }

  @Test
  void getHourPositiveTest() {
    // Test that the hour is set correctly with positive tests
    clock.setTime(15, 8);
    assertEquals(15, clock.getHour(), "Hour is set correctly, is 15");
  }

  @Test
  void getHourNegativeTest() {
    // Test that the hour is set correctly with negative tests
    clock.setTime(15, 8);
    assertNotEquals(12, clock.getHour(), "Hour is not set correctly, should be 15");
  }

  @Test
  void getMinutePositiveTest() {
    // Test that the minute is set correctly with positive and negative tests
    clock.setTime(15, 8);
    assertEquals(8, clock.getMinute(), "Minute is set correctly, is 8");

  }

  @Test
  void getMinuteNegativeTest() {
    // Test that the minute is set correctly with positive and negative tests
    clock.setTime(15, 8);
    assertNotEquals(10, clock.getMinute(), "Minute is not set correctly, should be 8");
  }

  @Test
  void getTimeAsStringPositiveTest() {
    // Test that the time is set correctly with positive and negative tests
    clock.setTime(15, 8);
    assertEquals("15:08", clock.getTimeAsString(), "Time is set correctly, is 15:08");

  }

  @Test
  void getTimeAsStringNegativeTest() {
    // Test that the time is set correctly with positive and negative tests
    clock.setTime(12, 3);
    assertNotEquals("10:08", clock.getTimeAsString(), "Time is not set correctly, should be 12:03");
  }

  @Test
  void combineDelayPositiveTest() {
    // Test that the delay is set correctly with positive and negative tests
    Clock testClock = new Clock(4, 20);
    // 4 + 4 = 8
    // 20 + 20 = 40
    clock = clock.combine(testClock);
    assertEquals(8, clock.getHour(), "Hour is combined correctly, is 8");
    assertEquals(40, clock.getMinute(), "Minute is combined correctly, is 40");
  }

  @Test
  void combineDelayNegativeTest() {
    // Test that the delay is set correctly with positive and negative tests
    Clock testClock = new Clock(4, 20);
    // 4 + 4 = 8
    // 20 + 20 = 40
    clock = clock.combine(testClock);
    assertNotEquals(4, clock.getHour(), "Hour is not combined correctly, should be 8");
    assertNotEquals(20, clock.getMinute(), "Minute is not combined correctly, should be 40");
  }
}