package departurecore;

import static java.util.Map.Entry.comparingByValue;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.stream.Stream;
import utility.Clock;

/**
 * Class for representing a station.
 * A station has a map of all traindepartures.
 *
 * @author Jonas Birkeli
 * @version 1.1.0
 * @since 1.0.0
 */
public class Station {
  private final HashMap<Integer, TrainDeparture> trainDepartures;
  private Clock stationTime;  // TODO implement clock for station

  /**
   * Constructor for Station.
   * Creates a new HashMap for trainDepartures.
   *
   * @since 1.0.0
   */
  public Station() {
    trainDepartures = new HashMap<>();
    stationTime = new Clock();
  }

  /**
   * Adds a trainDeparture to the station.
   *
   * @param trainDeparture The trainDeparture to add.
   * @since 1.0.0
   */
  public void addTrainDeparture(TrainDeparture trainDeparture) {
    trainDepartures.put(trainDeparture.getTrainNumber(), trainDeparture);
  }

  public void removeTrainDeparture(int trainNumber) {
    trainDepartures.remove(trainNumber);
  }

  /**
   * Returns the trainDeparture with the given trainNumber.
   *
   * @param trainNumber The trainNumber of the trainDeparture to return.
   * @return The trainDeparture with the given trainNumber.
   * @since 1.0.0
   */
  public TrainDeparture getTrainDeparture(int trainNumber) {
    return trainDepartures.get(trainNumber);
  }

  /**
   * Returns an iterator of {@code TrainDepartures} of the station.
   *
   * @return The trainDepartures of the station as an iterator.
   * @since 1.0.0
   */
  public Iterator<TrainDeparture> getTrainDepartures() {
    return trainDepartures.values().iterator();
  }

  /**
   * Returns a stream of {@code TrainDepartures} of the station.
   * Will send a stream of every entry in the hashmap, which can be used to get the values.
   *
   * @return The trainDepartures of the station as a stream.
   * @since 1.0.0
   */
  public Stream<Entry<Integer, TrainDeparture>> getTrainDeparturesAsEntrysetStream() {
    return trainDepartures.entrySet().stream();
  }

  /**
   * Returns a stream of {@code TrainDepartures} details of the station.
   * Will send a stream of every value in the hashmap.
   * Every value is sorted by time of departure.
   * Departures before the {@code Clock} time of the station will not be included in the stream.
   *
   * @return Details of traindeparture of the station as a stream, sorted and filtered.
   * @since 1.1.0
   */
  public Stream<String> getStreamOfDepartureDetails() {
    return trainDepartures.entrySet()
        .stream()
        .filter(
            d -> d.getValue().getDepartureTime().getHour() > stationTime.getHour()
                || (d.getValue().getDepartureTime().getHour() == stationTime.getHour()
                && d.getValue().getDepartureTime().getMinute() >= stationTime.getMinute())
            // Filter out departures with earlier departure time than current time
        )
        // Sorts departures by departure time,
        .sorted(comparingByValue(TrainDeparture::compareTo))
        .map(d -> d.getValue().getDetails());
  }

  /**
   * Returns a {@code TrainDeparture} with the given partial complete destination.
   * If no {@code TrainDeparture} has this destination, {@code null} is returned.
   * If there are multiple {@code TrainDepartures} with this destination, the first one is returned.
   *
   * @since 1.1.0
   */
  public TrainDeparture getTrainDepartureByPartialDestination(String partialDestination) {
    return trainDepartures.values().stream()
        .filter(d -> d.getDestination().toLowerCase().contains(partialDestination.toLowerCase()))
        .findFirst()
        .orElse(null);

  }

  /**
   * Checks weather the station has a trainDeparture with the given trainNumber.
   * Returns {@code true} if the station has a trainDeparture with the given trainNumber,
   * {@code false} otherwise.
   *
   * @param trainNumber The trainNumber to check for.
   * @return {@code true} if the station has a trainDeparture with the given trainNumber,
   * @since 1.0.0
   */
  public boolean hasTrainDeparture(int trainNumber) {
    return trainDepartures.containsKey(trainNumber);
  }

  /**
   * Sets the time of the station.
   *
   * @param hour  The hour to set.
   * @param minute The minute to set.
   * @since 1.1.0
   */
  public void setClockTime(int hour, int minute) {
    stationTime.setTime(hour, minute);
  }

  /**
   * Returns the clock object of the station.
   *
   * @return The time of the station as a clock object.
   * @since 1.1.0
   */
  public Clock getStationTime() {
    return stationTime;
  }
}
