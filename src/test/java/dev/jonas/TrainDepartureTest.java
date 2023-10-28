package dev.jonas;

import static org.junit.jupiter.api.Assertions.*;

class TrainDepartureTest {
  TrainDeparture trainDeparture;

  @org.junit.jupiter.api.BeforeEach
  void setUp() {
    trainDeparture = new TrainDeparture();
  }

  @org.junit.jupiter.api.AfterEach
  void tearDown() {
  }

  @org.junit.jupiter.api.Test
  void setDepartureTime() {
    trainDeparture.setDepartureTime(new int[]{23, 56});
    assertEquals(23, trainDeparture.getDepartureTime()[0]);
    assertEquals(56, trainDeparture.getDepartureTime()[1]);
  }

  @org.junit.jupiter.api.Test
  void getDepartureTime() {
    assertEquals(0, trainDeparture.getDepartureTime()[0]);
    assertEquals(0, trainDeparture.getDepartureTime()[1]);
  }

  @org.junit.jupiter.api.Test
  void setDelay() {
    trainDeparture.setDelay(new int[]{23, 56});
    assertEquals(23, trainDeparture.getDelay()[0]);
    assertEquals(56, trainDeparture.getDelay()[1]);
  }

  @org.junit.jupiter.api.Test
  void getDelay() {
    assertEquals(0, trainDeparture.getDelay()[0]);
    assertEquals(0, trainDeparture.getDelay()[1]);
  }

  @org.junit.jupiter.api.Test
  void setLine() {
    trainDeparture.setLine("L1");
    assertEquals("L1", trainDeparture.getLine());
  }

  @org.junit.jupiter.api.Test
  void getLine() {
    assertEquals("", trainDeparture.getLine());
  }

  @org.junit.jupiter.api.Test
  void setDestination() {
    trainDeparture.setDestination("Hamburg");
    assertEquals("Hamburg", trainDeparture.getDestination());
  }

  @org.junit.jupiter.api.Test
  void getDestination() {
    assertEquals("", trainDeparture.getDestination());
  }

  @org.junit.jupiter.api.Test
  void setTrack() {
    trainDeparture.setTrack(3);
    assertEquals(3, trainDeparture.getTrack());
  }

  @org.junit.jupiter.api.Test
  void getTrack() {
    assertEquals(-1, trainDeparture.getTrack());
  }

  @org.junit.jupiter.api.Test
  void setTrainNumber() {
    trainDeparture.setTrainNumber(1234);
    assertEquals(1234, trainDeparture.getTrainNumber());
  }

  @org.junit.jupiter.api.Test
  void getTrainNumber() {
    assertEquals(-1, trainDeparture.getTrainNumber());
  }

  @org.junit.jupiter.api.Test
  void getDetails() {
    trainDeparture.setDepartureTime(new int[]{23, 56});
    trainDeparture.setDelay(new int[]{23, 56});
    trainDeparture.setLine("L1");
    trainDeparture.setDestination("Hamburg");
    trainDeparture.setTrack(3);
    assertEquals("23:56 L1 Hamburg 3", trainDeparture.getDetails());
  }
}