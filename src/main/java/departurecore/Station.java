package departurecore;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.stream.Stream;

/**
 * Class for representing a station.
 * A station has a map of all traindepartures.
 *
 * @author Jonas Birkeli
 * @version 1.0.0
 * @since 1.0.0
 */
public class Station {
  private HashMap<Integer, TrainDeparture> trainDepartures;

  /**
   * Constructor for Station.
   * Creates a new HashMap for trainDepartures.
   *
   * @since 1.0.0
   */
  public Station() {
    trainDepartures = new HashMap<>();
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
}
