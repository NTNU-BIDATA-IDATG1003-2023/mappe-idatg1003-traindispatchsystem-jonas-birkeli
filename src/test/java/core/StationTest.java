package core;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class StationTest {
  Station station;

  @BeforeEach
  void setUp() {
    station = new Station();
    station.setStationTime(4, 20);
    station.addTrainDeparture(
        new TrainDeparture(5, 4, "L3", "Oslo", 4, 50)
    );
  }

  @AfterEach
  void tearDown() {
    station = null;
  }

  @Test
  void addTrainDepartureCombinedTest() {
    station.addTrainDeparture(
        new TrainDeparture(18, 15, "L5", "Gjøvik", 14, 55)
    );
    assertEquals("Gjøvik", station.getTrainDepartureByTrainNumber(55).getDestination(), "Destination should be Gjøvik");
    assertEquals(55, station.getTrainDepartureByTrainNumber(55).getTrainNumber(), "Train number should be 55");
    assertNotEquals(14, station.getTrainDepartureByTrainNumber(55).getDepartureTime().getHour(), "Hour should not be 14, should be 18");
    assertEquals(15, station.getTrainDepartureByTrainNumber(55).getDepartureTime().getMinute(), "Minute should be 15, should be 15");
  }

  @Test
  void getTrainDepartureByTrainNumberPositiveTest() {
    assertEquals(50, station.getTrainDepartureByTrainNumber(50).getTrainNumber(), "Train number should be 50");
  }

  @Test
  void getStreamOfTimeFilteredTrainDeparturesPositiveTest() {
    assertEquals(1, station.getStreamOfTimeFilteredTrainDepartures().count(), "Stream of trains should contain 1 departure"); // 1 train departure
    station.addTrainDeparture(
        new TrainDeparture(18, 15, "L5", "Gjøvik", 14, 55)
    );
    assertEquals(2, station.getStreamOfTimeFilteredTrainDepartures().count(), "Stream of trains should contain 2 departures"); // 2 train departures

    station.setStationTime(4, 50);
    assertEquals(2, station.getStreamOfTimeFilteredTrainDepartures().count(), "Stream of trains should contain 2 departures after 4:50"); // 2 train departures

  }

  @Test
  void getStreamOfTimeFilteredTrainDeparturesNegativeTest() {
    // Tests for whether the time filtration works correctly by checking how many departures there
    // are before and after the time is set to 5:00
    station.addTrainDeparture(
        new TrainDeparture(
            18, 15, "L5", "Gjøvik", 14, 55)
    );
    station.setStationTime(4, 50);
    assertNotEquals(1, station.getStreamOfTimeFilteredTrainDepartures().count(),
        "Stream of trains should contain 2 departures after 4:50"); // 2 train departures
    station.setStationTime(5, 0);
    assertNotEquals(1, station.getStreamOfTimeFilteredTrainDepartures().count(),
        "Stream of trains should contain 1 departure after 5:00"); // 1 train departure
  }

  @Test
  void getAllTrainDeparturesByPartialDestination() {
    station.addTrainDeparture(
        new TrainDeparture(
            18, 15, "L5", "Gjøvik", 14, 57
        )
    );
    assertNotEquals(0, station.getAllTrainDeparturesByPartialDestination("Os").count(),
        "Stream of trains should contain 1 departure to Oslo");
    assertEquals(1, station.getAllTrainDeparturesByPartialDestination("jøvi").count(),
        "Stream of trains should contain 1 departure to Gjøvik");
  }

  @Test
  void hasTrainDepartureWithTrainNumberPositiveTest() {
    assertTrue(station.hasTrainDepartureWithTrainNumber(50),
        "Train number 50 should exist");

  }

  @Test
  void hasTrainDepartureWithTrainNumberNegativeTest() {
    assertFalse(station.hasTrainDepartureWithTrainNumber(2),
        "Train number 2 should not exist");
  }

  @Test
  void getStationClockPositiveTest() {
    assertEquals(4, station.getStationClock().getHour(), "Hour should be 4");
    assertEquals(20, station.getStationClock().getMinute(), "Minute should be 20");
  }

  @Test
  void getStationClockNegativeTest() {
    assertNotEquals(5, station.getStationClock().getHour(), "Hour should not be 5, should be 4");
    assertNotEquals(21, station.getStationClock().getMinute(), "Minute should not be 21, should be 20");
  }

  @Test
  void setStationTime() {
    station.setStationTime(5, 0);
    assertEquals(5, station.getStationClock().getHour(), "Hour should be 5");
    assertEquals(0, station.getStationClock().getMinute(), "Minute should be 0");
  }

  @Test
  void setStationTimeNegativeTest() {
    station.setStationTime(5, 0);
    assertNotEquals(4, station.getStationClock().getHour(), "Hour should not be 4, should be 5");
    assertNotEquals(1, station.getStationClock().getMinute(), "Minute should not be 1, should be 0");
  }
}