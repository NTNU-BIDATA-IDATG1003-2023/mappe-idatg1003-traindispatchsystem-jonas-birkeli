package departurecore;

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
  void getDepartureTime() {
    // Test that the departure time is correct, where the time is set in the constructor
    assertEquals(4, trainDeparture.getDepartureTime().getHour());
    assertEquals(50, trainDeparture.getDepartureTime().getMinute());
  }

  @Test
  void setDelay() {
    // Test that the delay is set correctly with positive and negative tests
    trainDeparture.setDelay(10, 9);
    assertEquals(10, trainDeparture.getDelay().getHour());
    assertNotEquals(10, trainDeparture.getDelay().getMinute());
    assertEquals(9, trainDeparture.getDelay().getMinute());
  }

  @Test
  void getDelay() {
    // Test that the delay is set correctly with positive and negative tests
    trainDeparture.setDelay(10, 9);
    assertEquals(10, trainDeparture.getDelay().getHour());
    assertNotEquals(10, trainDeparture.getDelay().getMinute());
    assertEquals(9, trainDeparture.getDelay().getMinute());
  }

  @Test
  void getLine() {
    // Test that the line is set correctly
    assertEquals("L3", trainDeparture.getLine());
    assertNotEquals("L4", trainDeparture.getLine());
  }

  @Test
  void getDestination() {
    // Test that the destination is set correctly
    assertEquals("Oslo", trainDeparture.getDestination());
  }

  @Test
  void setTrack() {
    // Test that the track is set correctly with positive and negative tests
    // The method checks for validity, and if the track is invalid, it will return -1
    trainDeparture.setTrack(10);
    assertEquals(10, trainDeparture.getTrack());
    trainDeparture.setTrack(-1);
    assertEquals(-1, trainDeparture.getTrack());
    trainDeparture.setTrack(0);
    assertEquals(-1, trainDeparture.getTrack());
  }

  @Test
  void getTrack() {
    // Test that the track is set correctly with positive and negative tests
    // The method checks for validity, and if the track is invalid, it will return -1
    trainDeparture.setTrack(10);
    assertEquals(10, trainDeparture.getTrack());
    trainDeparture.setTrack(-1);
    assertEquals(-1, trainDeparture.getTrack());
    trainDeparture.setTrack(0);
    assertNotEquals(0, trainDeparture.getTrack());
    assertEquals(-1, trainDeparture.getTrack());
  }

  @Test
  void getTrainNumber() {
    // Test that the trainNumber is set correctly
    assertEquals(5, trainDeparture.getTrainNumber());
  }

  @Test
  void compareTo() {
    // Test that the compareTo method works as intended
    // The compareTo method compares the departure time of the trainDeparture
    // -1, 0 and 1 is returned depending on if the departure time is before, equal or after the
    TrainDeparture trainDeparture1 = new TrainDeparture(5, 4, "L3", "Oslo", 4, 50);
    assertEquals(0, trainDeparture.compareTo(trainDeparture1));

    TrainDeparture trainDeparture2 = new TrainDeparture(5, 3, "L3", "Oslo", 4, 51);
    assertEquals(1, trainDeparture.compareTo(trainDeparture2));

    TrainDeparture trainDeparture3 = new TrainDeparture(5, 5, "L3", "Oslo", 4, 49);
    assertEquals(-1, trainDeparture.compareTo(trainDeparture3));
  }

  @Test
  void testEquals() {
    // Test that the equals method works as intended
    TrainDeparture trainDeparture1 = new TrainDeparture(5, 4, "L3", "Oslo", 4, 50);
    assertEquals(trainDeparture, trainDeparture1);

    TrainDeparture trainDeparture2 = new TrainDeparture(5, 3, "L3", "Oslo", 4, 51);
    assertNotEquals(trainDeparture, trainDeparture2);

    TrainDeparture trainDeparture3 = new TrainDeparture(5, 5, "L3", "Oslo", 4, 49);
    assertNotEquals(trainDeparture, trainDeparture3);
  }

  @Test
  void testHashCode() {
    // Test that the hashCode method works as intended
    TrainDeparture trainDeparture1 = new TrainDeparture(5, 4, "L3", "Oslo", 4, 50);
    assertEquals(trainDeparture.hashCode(), trainDeparture1.hashCode());

    TrainDeparture trainDeparture2 = new TrainDeparture(5, 3, "L3", "Oslo", 4, 51);
    assertNotEquals(trainDeparture.hashCode(), trainDeparture2.hashCode());

    TrainDeparture trainDeparture3 = new TrainDeparture(5, 5, "L3", "Oslo", 4, 49);
    assertNotEquals(trainDeparture.hashCode(), trainDeparture3.hashCode());
  }
}