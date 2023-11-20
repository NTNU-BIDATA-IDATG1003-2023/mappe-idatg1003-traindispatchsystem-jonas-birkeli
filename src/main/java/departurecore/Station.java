package departurecore;

import static java.util.Map.Entry.comparingByValue;

import java.util.HashMap;
import java.util.Map.Entry;
import java.util.stream.Stream;
import utility.Clock;

/**
 * Class for representing a station.
 * The station has a collection of every train departure, and has the responsibility of adding,
 * searching and sorting the train departures for later printing or other use-cases.
 * <p>
 *   This class has the following methods:
 *   <ul>
 *     <li>{@link #addTrainDeparture(TrainDeparture)}</li>
 *     <li>{@link #getTrainDepartureByTrainNumber(int)}</li>
 *     <li>{@link #getStreamOfTimeFilteredTrainDepartures()}</li>
 *     <li>{@link #getAllTrainDeparturesByPartialDestination(String)}</li>
 *     <li>{@link #hasTrainDepartureWithTrainNumber(int)}</li>
 *     <li>{@link #getStationClock()}</li>
 *     <li>{@link #setStationTime(int, int)}</li>
 *     <li>{@link #getSortedStreamOfTrainDepartures()}</li>
 *   </ul>
 * </p>
 *
 * @author Jonas Birkeli
 * @version 1.4.0
 * @since 1.0.0
 */
public class Station {
  private HashMap<Integer, TrainDeparture> trainDepartures;
  private TrainDeparture selectedTrainDeparture;
  private final Clock stationTime;

  /**
   * Constructor for Station.
   * Initalized an empty collection of trainDepartures, and sets the time of the station to 00:00.
   *
   * @since 1.0.0
   */
  public Station() {
    trainDepartures = new HashMap<>();
    selectedTrainDeparture = null;
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
  public TrainDeparture getTrainDepartureByTrainNumber(int trainNumber) {
    return trainDepartures.get(trainNumber);
  }

  /**
   * Returns a stream of {@code TrainDepartures} details of the station. Will send a stream of every
   * value in the hashmap. Every value is sorted by time of departure. Departures before the
   * {@code Clock} time of the station will not be included in the stream.
   *
   * @return Details of traindeparture of the station as a stream, sorted and filtered.
   * @since 1.1.0
   */
  public Stream<TrainDeparture> getStreamOfTimeFilteredTrainDepartures() {
    return getSortedStreamOfTrainDepartures()
        .filter(
            d -> d.getDepartureTime().getHour() > stationTime.getHour()
                || (d.getDepartureTime().getHour() == stationTime.getHour()
                && d.getDepartureTime().getMinute() >= stationTime.getMinute())
        );
  }



  /**
   * Filters out the destinations that does not contain the given partial complete destination, and
   * returns a stream of the trains that passes the filter.
   * If no {@code TrainDeparture} has this destination, an empty stream is returned.
   * If there are multiple {@code TrainDepartures} with this destination, all of them are returned.
   * The stream is sorted by departure time.
   *
   * @param partialDestination The partial complete destination to filter for.
   * @return A stream of {@code TrainDepartures} that has the given partial complete destination.
   * @since 1.2.0
   */
  public Stream<TrainDeparture> getAllTrainDeparturesByPartialDestination(
      String partialDestination
  ) {
    return getSortedStreamOfTrainDepartures()
        .filter(d -> d.getDestination().toLowerCase().contains(partialDestination.toLowerCase()));
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

  /**
   * Sets a new time of the station if the time is later than the current station time.
   * If the time is later than the time, the method returns true, else false.
   * When a valid time is set, the collection of trains is filtered to only include trains that
   * depart after the new time.
   *
   * @param hour The hour to set the station time to.
   * @param minute The minute to set the station time to.
   * @return true if the time is later than the current station time, else false.
   * @since 1.3.0
   */
  public boolean setStationTime(int hour, int minute) {
    boolean validTime = hour > stationTime.getHour() || (hour == stationTime.getHour()
        && minute > stationTime.getMinute());

    if (validTime) {
      // Sets the new time if the time is valid
      stationTime.setTime(hour, minute);

      // Filters out the trains that depart before the new time
      trainDepartures = trainDepartures.entrySet().stream()
          .filter(d -> d.getValue().getDepartureTime().getHour() > stationTime.getHour()
              || (d.getValue().getDepartureTime().getHour() == stationTime.getHour()
              && d.getValue().getDepartureTime().getMinute() >= stationTime.getMinute())
          )
          // Collecting the new hashmap
          // This line was generated by Copilot and tested by me
          .collect(HashMap::new, (m, e) -> m.put(e.getKey(), e.getValue()), HashMap::putAll);
    }
    return validTime;
  }

  /**
   * Sorts the {@code TrainDepartures} by departure time,
   * and returns a stream of the {@code TrainDepartures}.
   * The stream is sorted by departure time.
   *
   * @return A stream of {@code TrainDepartures} sorted by departure time.
   * @since 1.2.0
   */
  private Stream<TrainDeparture> getSortedStreamOfTrainDepartures() {
    return trainDepartures.entrySet().stream()
        .sorted(comparingByValue(TrainDeparture::compareTo))
        .map(Entry::getValue);
  }

  /**
   * Selects a {@code TrainDeparture} by the given trainNumber.
   * If the station has a {@code TrainDeparture} with the given trainNumber, the
   * {@code TrainDeparture} is selected.
   *
   * @return 0 if the {@code TrainDeparture} was found and successfully selected, -1 if the
   *        {@code TrainDeparture} was not found.
   * @since 1.4.0
   */
  public int selectTrainDeparture(int trainNumber) {
    int returnCode = 0;
    if (hasTrainDepartureWithTrainNumber(trainNumber)) {
      selectedTrainDeparture = getTrainDepartureByTrainNumber(trainNumber);
      // Successfully selected trainDeparture
    } else {
      // TrainDeparture not found
      returnCode = -1;
    }
    return returnCode;
  }

  /**
   * Returns the selected {@code TrainDeparture}.
   *
   * @since 1.4.0
   */
  public TrainDeparture getSelectedTrainDeparture() {
    return selectedTrainDeparture;
  }
}
