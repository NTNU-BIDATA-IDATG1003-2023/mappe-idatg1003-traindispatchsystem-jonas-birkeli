package departurecore;

import static java.util.Map.Entry.comparingByValue;

import java.util.HashMap;
import java.util.stream.Stream;
import utility.Clock;

/**
 * Class for representing a station.
 * The station has a collection of every train departure, and has the responsibility of adding,
 * searching and sorting the train departures for later printing or other use-cases.
 *
 * @author Jonas Birkeli
 * @version 1.1.0
 * @since 1.0.0
 */
public class Station {
  private final HashMap<Integer, TrainDeparture> trainDepartures;
  private final Clock stationTime;

  /**
   * Constructor for Station.
   * Initalized an empty collection of trainDepartures, and sets the time of the station to 00:00.
   *
   * @since 1.0.0
   */
  public Station() {
    trainDepartures = new HashMap<>();
    stationTime = new Clock();
  }

  /**
   * Adds a {@code TrainDeparture} to the station.
   * The {@code TrainDeparture} is stored in the hashmap with the trainNumber as the identifier.
   *
   * @param trainDeparture The trainDeparture to add to the station.
   * @since 1.0.0
   */
  public void addTrainDeparture(TrainDeparture trainDeparture) {
    if (trainDeparture != null) {
      // TrainDeparture is not nullable
      trainDepartures.put(trainDeparture.getTrainNumber(), trainDeparture);
    }
  }

  /**
   * Returns the {@code TrainDeparture} with the given trainNumber.
   *
   * @param trainNumber The trainNumber of the {@code TrainDeparture} to return.
   * @return The {@code TrainDeparture} with the given train-number.
   * @since 1.0.0
   */
  public TrainDeparture getTrainDeparture(int trainNumber) {
    return trainDepartures.get(trainNumber);
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
        // Sorts departures by departure time
        // Checks for hour difference, then minute difference if the hours are equal
        .sorted(comparingByValue(TrainDeparture::compareTo))
        .map(d -> d.getValue().getDetails());
  }

  /**
   * Filters out the destinations that does not contain the given partial complete destination, and
   * returns the first train that passes the filter.
   * If no {@code TrainDeparture} has this destination, {@code null} is returned.
   * If there are multiple {@code TrainDepartures} with this destination, the first one is returned.
   *
   * @param partialDestination The partial complete destination to filter for.
   * @since 1.1.0
   */
  public TrainDeparture getTrainDepartureByPartialDestination(String partialDestination) {
    return trainDepartures.values().stream()
        // Filters out the destinations that does
        .filter(d -> d.getDestination().toLowerCase().contains(partialDestination.toLowerCase()))
        .findFirst()
        .orElse(null);
  }

  /**
   * Checks whether the station has a {@code TrainDeparture} with the given trainNumber.
   * Returns {@code true} if the station has a {@code TrainDeparture} with the given trainNumber,
   * {@code false} otherwise.
   *
   * @param trainNumber The train-number to check for.
   * @return {@code true} if the station has a {@code TrainDeparture} with the given trainNumber,
   * @since 1.0.0
   */
  public boolean hasTrainDepartureWithTrainNumber(int trainNumber) {
    return trainDepartures.containsKey(trainNumber);
  }

  /**
   * Gives access to the station time object for the station.
   * Can be used for setting a new time or getting the current time of the station.
   *
   * @return The {@code Clock} object representing the time of the station.
   * @since 1.1.0
   */
  public Clock getStationClock() {
    return stationTime;
  }
}
