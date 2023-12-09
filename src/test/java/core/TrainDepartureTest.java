package core;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TrainDepartureTest {

  TrainDeparture trainDeparture;

  @BeforeEach
  void setUp() {
    // Create a new trainDeparture before each test
    trainDeparture = new TrainDeparture(5, 4, "L3", "Oslo", 4, 50);
  }

  @AfterEach
  void tearDown() {
    // Remove reference to trainDeparture
    trainDeparture = null;
  }

  @Test
  void getDepartureTimePositiveTest() {
    // Test that the departure time is correct, where the time is set in the constructor
    assertEquals(5, trainDeparture.getDepartureTime().getHour(), "Hour should be 4");
    assertEquals(4, trainDeparture.getDepartureTime().getMinute(), "Minute should be 50");
  }

  @Test
  void getDepartureTimeNegativeTest() {
    // Test that the departure time is correct, where the time is set in the constructor
    assertNotEquals(4, trainDeparture.getDepartureTime().getHour(), "Hour should not be 4, should be 5");
    assertNotEquals(51, trainDeparture.getDepartureTime().getMinute(), "Minute should not be 51, should be 4");
  }

  @Test
  void setDelayPositiveTest() {
    // Test that the delay is set correctly with positive and negative tests
    trainDeparture.setDelay(10, 9);
    assertEquals(10, trainDeparture.getDelay().getHour(), "Hour should be 10");
    assertEquals(9, trainDeparture.getDelay().getMinute(), "Minute should be 9");
  }

  @Test
  void setDelayNegativeTest() {
    // Test that the delay is set correctly with negative tests
    trainDeparture.setDelay(10, 9);
    assertNotEquals(1, trainDeparture.getDelay().getHour(), "Hour should not be 1, should be 10");
    assertNotEquals(1, trainDeparture.getDelay().getMinute(), "Minute should not be 1, should be 9");
  }

  @Test
  void getDelayPositiveTest() {
    // Test that the delay is set correctly with positive test
    trainDeparture.setDelay(10, 9);
    assertEquals(10, trainDeparture.getDelay().getHour(), "Hour should be 10");
    assertEquals(9, trainDeparture.getDelay().getMinute(), "Minute should be 9");
  }

  @Test
  void getDelayNegativeTest() {
    trainDeparture.setDelay(10, 9);
    assertNotEquals(1, trainDeparture.getDelay().getHour(), "Hour should not be 1, should be 10");
  }

  @Test
  void getLinePositiveTest() {
    // Test that the line is set correctly
    assertEquals("L3", trainDeparture.getLine(), "Line should be L3");
  }

  @Test
  void getLineNegativeTest() {
    assertNotEquals("L4", trainDeparture.getLine(), "Line should not be L4, should be L3");
  }

  @Test
  void getDestinationPositiveTest() {
    // Test that the destination is set correctly
    assertEquals("Oslo", trainDeparture.getDestination(), "Destination should be Oslo");
  }

  @Test
  void getDestinationNegativeTest() {
    // Test that the destination is set correctly with negative test
    assertNotEquals("Gjøvik", trainDeparture.getDestination(), "Destination should not be Gjøvik, should be Oslo");
  }

  @Test
  void setTrackPositveTest() {
    // Test that the track is set correctly with positive tests
    // The method checks for validity, and if the track is invalid, it will return -1
    trainDeparture.setTrack(10);
    assertEquals(10, trainDeparture.getTrack(), "Track should be 10");
    trainDeparture.setTrack(-3);
    assertEquals(-1, trainDeparture.getTrack(), "Track should be -1");
    trainDeparture.setTrack(0);
    assertEquals(-1, trainDeparture.getTrack(), "Track should be -1");
  }

  @Test
  void setTrackNegativeTest() {
    // Test that the track is set correctly with negative tests
    trainDeparture.setTrack(10);
    assertNotEquals(11, trainDeparture.getTrack(), "Track should not be 11, should be 10");
    trainDeparture.setTrack(-3);
    assertNotEquals(0, trainDeparture.getTrack(), "Track should not be 0, should be -1");
    trainDeparture.setTrack(0);
    assertNotEquals(0, trainDeparture.getTrack(), "Track should not be 0, should be -1");
  }

  @Test
  void getTrackPositveTest() {
    // Test that the track is set correctly with positive and negative tests
    // The method checks for validity, and if the track is invalid, it will return -1
    trainDeparture.setTrack(10);
    assertEquals(10, trainDeparture.getTrack(), "Track should be 10");
    trainDeparture.setTrack(-1);
    assertEquals(-1, trainDeparture.getTrack(), "Track should be -1");
    trainDeparture.setTrack(0);
    assertEquals(-1, trainDeparture.getTrack(), "Track should be -1");
  }

  @Test
  void getTrackNegativeTest() {
    // Test that the track is set correctly with positive and negative tests
    // The method checks for validity, and if the track is invalid, it will return -1
    trainDeparture.setTrack(10);
    assertNotEquals(11, trainDeparture.getTrack(), "Track should not be 11, should be 10");
    trainDeparture.setTrack(-1);
    assertNotEquals(0, trainDeparture.getTrack(), "Track should not be 0, should be -1");
    trainDeparture.setTrack(0);
    assertNotEquals(0, trainDeparture.getTrack(), "Track should not be 0, should be -1");
  }

  @Test
  void getTrainNumberPositiveTest() {
    // Test that the trainNumber is set correctly
    assertEquals(50, trainDeparture.getTrainNumber(), "Train number should be 50");
  }

  @Test
  void compareToPositiveTest() {
    // Test that the compareTo method works as intended
    // The compareTo method compares the departure time of the trainDeparture
    // -1, 0 and 1 is returned depending on if the departure time is before, equal or after the
    TrainDeparture trainDeparture1 = new TrainDeparture(5, 4, "L3", "Oslo", 4, 50);
    assertEquals(0, trainDeparture.compareTo(trainDeparture1), "Should be equal or 0");

    TrainDeparture trainDeparture2 = new TrainDeparture(5, 3, "L3", "Oslo", 4, 51);
    assertEquals(1, trainDeparture.compareTo(trainDeparture2), "Should be greater or 1");

    TrainDeparture trainDeparture3 = new TrainDeparture(5, 5, "L3", "Oslo", 4, 49);
    assertEquals(-1, trainDeparture.compareTo(trainDeparture3), "Should be less or -1");
  }

  @Test
  void compareToNegativeTest() {
    // Test that the compareTo method works as intended
    // The compareTo method compares the departure time of the trainDeparture
    // -1, 0 and 1 is returned depending on if the departure time is before, equal or after the
    TrainDeparture trainDeparture1 = new TrainDeparture(5, 4, "L3", "Oslo", 4, 50);
    assertNotEquals(1, trainDeparture.compareTo(trainDeparture1), "Should be equal or 0");

    TrainDeparture trainDeparture2 = new TrainDeparture(5, 3, "L3", "Oslo", 4, 51);
    assertNotEquals(-1, trainDeparture.compareTo(trainDeparture2), "Should be greater or 1");

    TrainDeparture trainDeparture3 = new TrainDeparture(5, 5, "L3", "Oslo", 4, 49);
    assertNotEquals(0, trainDeparture.compareTo(trainDeparture3), "Should be less or -1");
  }
}