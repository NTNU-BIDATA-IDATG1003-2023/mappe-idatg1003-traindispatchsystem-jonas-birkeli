package departurecore;

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
  void addTrainDeparture() {
    station.addTrainDeparture(
        new TrainDeparture(18, 15, "L5", "Gjøvik", 14, 55)
    );
    assertEquals("Gjøvik", station.getTrainDepartureByTrainNumber(55).getDestination());
    assertEquals(55, station.getTrainDepartureByTrainNumber(55).getTrainNumber());
    assertNotEquals(14, station.getTrainDepartureByTrainNumber(55).getDepartureTime().getHour());
    assertEquals(15, station.getTrainDepartureByTrainNumber(55).getDepartureTime().getMinute());
  }

  @Test
  void getTrainDepartureByTrainNumber() {
    assertEquals(50, station.getTrainDepartureByTrainNumber(50).getTrainNumber());
  }

  @Test
  void getStreamOfTimeFilteredTrainDepartures() {
    assertEquals(1, station.getStreamOfTimeFilteredTrainDepartures().count()); // 1 train departure
    station.addTrainDeparture(
        new TrainDeparture(18, 15, "L5", "Gjøvik", 14, 55)
    );
    assertEquals(2, station.getStreamOfTimeFilteredTrainDepartures().count()); // 2 train departures

    station.setStationTime(4, 50);
    assertEquals(2, station.getStreamOfTimeFilteredTrainDepartures().count()); // 2 train departures
    station.setStationTime(5, 0);
    assertNotEquals(1, station.getStreamOfTimeFilteredTrainDepartures().count()); // 1 train departure
  }

  @Test
  void getFirstTrainDepartureByPartialDestination() {
    station.addTrainDeparture(
        new TrainDeparture(18, 15, "L5", "Gjøvik", 14, 55)
    );
    assertEquals(50, station.getFirstTrainDepartureByPartialDestination("Os").getTrainNumber());
    assertEquals(55, station.getFirstTrainDepartureByPartialDestination("jøvi").getTrainNumber());
  }

  @Test
  void getAllTrainDeparturesByPartialDestination() {
    station.addTrainDeparture(
        new TrainDeparture(18, 15, "L5", "Gjøvik", 14, 57)
    );
    assertNotEquals(0, station.getAllTrainDeparturesByPartialDestination("Os").count());
    assertEquals(1, station.getAllTrainDeparturesByPartialDestination("jøvi").count());
  }

  @Test
  void hasTrainDepartureWithTrainNumber() {
    assertFalse(station.hasTrainDepartureWithTrainNumber(2));
    assertTrue(station.hasTrainDepartureWithTrainNumber(50));

  }

  @Test
  void getStationClock() {
    assertEquals(4, station.getStationClock().getTime()[0]);
    assertEquals(20, station.getStationClock().getTime()[1]);
  }

  @Test
  void setStationTime() {
    station.setStationTime(5, 0);
    assertEquals(5, station.getStationClock().getTime()[0]);
    assertEquals(0, station.getStationClock().getTime()[1]);
  }
}