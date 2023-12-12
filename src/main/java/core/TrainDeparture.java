package core;

import java.util.Arrays;
import java.util.Objects;
import utility.Clock;


/**
 * The {@code TrainDeparture} class represents a train on a station.
 * All {@code TrainDeparture}s has a departure-time, delay, line, destination and track.
 * Both the deparute-time and delay is represented by a {@code Clock} object.
 *
 * @author Jonas Birkeli
 * @version 1.5.0
 * @since 1.0.0
 */
public class TrainDeparture implements Comparable<TrainDeparture> {
  private final Clock departureTime;
  private final Clock delay;
  private String line;
  private String destination;
  private int track;
  private int trainNumber;

  /**
   * Constructs a new {@code TrainDeparture} with default values.
   * Default values are:
   * <ul>
   *   <li>Departure time: 00:00</li>
   *   <li>Delay: 00:00</li>
   *   <li>Line: empty</li>
   *   <li>Destination: empty</li>
   *   <li>Track: -1</li>
   *   <li>Train number: -1</li>
   * </ul>
   *
   * @since 1.0.0
   */
  public TrainDeparture() {
    departureTime = new Clock(); // Default 00:00
    delay = new Clock(); // Default 00:00
    setLine("");
    setDestination("");
    setTrack(-1);
    setTrainNumber(-1);
  }

  /**
   * Constructs a new {@code TrainDeparture} with the given parameters,
   * where minute and hour has their own parameters.
   * All parameters are tested for validity.
   *
   * @param hour Scheduled hour for departure of train.
   * @param minute Scheduled minute for departure of train.
   * @param line Scheduled line of train.
   * @param destination End-destination of train.
   * @param track Number of the departure-track.
   * @since 1.3.0
   */
  public TrainDeparture(
      int hour,
      int minute,
      String line,
      String destination,
      int track,
      int trainNumber) {
    departureTime = new Clock(hour, minute);
    delay = new Clock(); // Default 00:00
    setLine(line);
    setDestination(destination);
    setTrack(track);
    setTrainNumber(trainNumber);
  }

  /**
   * Returns the departure time of the {@code TrainDeparture}
   * as a {@code Clock} object.
   *
   * @return the departure time of the {@code TrainDeparture}.
   * @since 1.3.0
   */
  public Clock getDepartureTime() {
    return departureTime;
  }

  /**
   * Sets the delay of the {@code TrainDeparture}.
   * Each {@code TrainDeparture} has two delays,
   * one representing hours, and one representing minutes.
   * If null, not two elements or either is negative, delay is set to 0.
   *
   * @param hour The hour of the delay for the {@code TrainDeparture}.
   * @param minute The minute of the delay for the {@code TrainDeparture}.
   * @since 1.0.0
   */
  public void setDelay(int hour, int minute) {
    if (hour < 0 || minute < 0) {
      // If either is negative, set delay to 0
      // Overflow is accounted for, so no need to check for that
      delay.setTime(0, 0);
    } else {
      delay.setTime(hour, minute);
    }
  }

  /**
   * Returns the delay of the {@code TrainDeparture}
   * as a referance to a {@code Clock} object.
   *
   * @return the delay of the {@code TrainDeparture}.
   * @since 1.0.0
   */
  public Clock getDelay() {
    return delay;
  }

  /**
   * Sets the line of the {@code TrainDeparture}.
   * If null, line is set to INVALID
   *
   * @param line the line of the {@code TrainDeparture}.
   * @since 1.0.0
   */
  private void setLine(String line) {
    this.line = Objects.requireNonNullElse(line, "NVALID");
  }

  /**
   * Returns the line of the {@code TrainDeparture}.
   *
   * @return the line of the {@code TrainDeparture}.
   * @since 1.0.0
   */
  public String getLine() {
    return line;
  }

  /**
   * Sets the destination of the {@code TrainDeparture}.
   * If null, destination is set to empty.
   * The method is set to private to hinder users from tampering with the destination.
   *
   * @param destination the destination of the {@code TrainDeparture}.
   * @since 1.0.0
   */
  private void setDestination(String destination) {
    this.destination = Objects.requireNonNullElse(destination, "INVALID");
  }

  /**
   * Returns the destination of the {@code TrainDeparture}.
   *
   * @return the destination of the {@code TrainDeparture}.
   * @since 1.0.0
   */
  public String getDestination() {
    return destination;
  }

  /**
   * Sets the track of the {@code TrainDeparture}.
   * If negative or zero, track is set to -1.
   *
   * @param track the track of the {@code TrainDeparture}.
   * @since 1.0.0
   */
  public void setTrack(int track) {
    if (track <= 0) {
      this.track = -1;
    } else {
      this.track = track;
    }
  }

  /**
   * Returns the track of the {@code TrainDeparture}.
   *
   * @return the track of the {@code TrainDeparture}.
   * @since 1.0.0
   */
  public int getTrack() {
    return track;
  }

  /**
   * Sets the train number of the {@code TrainDeparture}.
   * If negative or zero, train number is set to -1.
   * Method is private to hinder users from tampering with the train number.
   *
   * @since 1.4.0
   */
  private void setTrainNumber(int trainNumber) {
    if (trainNumber <= 0) {
      this.trainNumber = -1;
    } else {
      this.trainNumber = trainNumber;
    }
  }

  /**
   * Returns the train-number of the {@code TrainDeparture}.
   *
   * @return the unique train-number of the {@code TrainDeparture}.
   * @since 1.4.0
   */
  public int getTrainNumber() {
    return trainNumber;
  }

  /**
   * Compares this {@code TrainDeparture} with the specified {@code TrainDeparture} for order.
   * Returns a negative integer, zero, or a positive integer as this {@code TrainDeparture}
   * is less than, equal to, or greater than the specified {@code TrainDeparture}.
   * The comparison is based on the departure time of the {@code TrainDeparture}.
   *
   * @param other the {@code TrainDeparture} to be compared.
   *              If null, returns 1.
   * @return a negative integer, zero, or a positive integer as this {@code TrainDeparture}
   *        is less than, equal to, or greater than the specified {@code TrainDeparture}.
   *        If null, returns 1.
   * @since 1.1.0
   */
  @Override
  public int compareTo(TrainDeparture other) {
    // If other is null, this is bigger, return 1 meaning bigger
    // Starting at -5 to make sure the state is changed
    int state = -5;

    if (other == null) {
      state = 1;
    } else {  // Checking for null to avoid NullPointerException in the following code block

      // If this and other are equal, return 0 meaning equal
      if (this.equals(other)) {
        state = 0;
      } else if (getDepartureTime().getHour() > other.getDepartureTime().getHour()) {
        state = 1;  // Comparing departure times
      } else if (getDepartureTime().getHour() < other.getDepartureTime().getHour()) {
        state = -1;  // If this is smaller, return -1 meaning smaller
      }

      // If departure hours are equal, compare minutes
      // Using builtin comparator for integers to compare minutes
      if (state == -5) {
        // If state is still -5, departure hours are equal
        state =  Integer.compare(
            getDepartureTime().getMinute(),
            other.getDepartureTime().getMinute()
        );
      }
    }
    return state;
  }

  /**
   * Checker for if the {@code TrainDeparture} is a valid departure, by checking the destination
   * and line of the {@code TrainDeparture}.
   * The {@code TrainDeparture} is valid if the destination AND line is not empty.
   *
   * @return true if the {@code TrainDeparture} is valid, false if not.
   * @since 1.5.0
   */
  public boolean isValidDeparture() {
    return !getDestination().isEmpty()
        && !getLine().isEmpty()
        && !getLine().equals("NVALID")
        && !getDestination().equals("INVALID");
  }

  /**
   * Indicates whether some other object is "equal to" this one.
   * Two {@code TrainDeparture}s are considered equal if they have the same
   * departure time, delay, line, destination and track.
   * If any of these values are not equal, the {@code TrainDeparture}s are not equal.
   * If the specified object is null, returns false.
   * If the specified object is not a {@code TrainDeparture}, returns false.
   * If the specified object is a {@code TrainDeparture} and all values are equal,
   * returns true.
   *
   * @param o the object with which to compare.
   * @return true if this object is the same as the obj argument; false otherwise.
   * @since 1.2.0
   */
  @Override
  public boolean equals(Object o) {
    // Entire method is auto-generated by IntelliJ
    if (this == o) {
      return true;
    }
    if (!(o instanceof TrainDeparture that)) {
      return false;
    }
    return getTrack() == that.getTrack() && getTrainNumber() == that.getTrainNumber()
        && Objects.equals(getDepartureTime(), that.getDepartureTime())
        && Objects.equals(getDelay(), that.getDelay()) && Objects.equals(
        getLine(), that.getLine()) && Objects.equals(getDestination(),
        that.getDestination());
  }

  /**
   * Returns a hash code value for the {@code TrainDeparture}.
   * The hash code is based upon the departure time, delay, line, destination and track.
   * If any of these values are not equal, the hash codes will not be equal.
   * If the hash codes are not equal, the {@code TrainDeparture}s are not equal.
   *
   * @return a hash code value for the {@code TrainDeparture}.
   * @since 1.2.0
   */
  @Override
  public int hashCode() {
    // Entire method is auto-generated by IntelliJ
    return Objects.hash(getDepartureTime(), getDelay(), getLine(), getDestination(), getTrack(),
        getTrainNumber());
  }
}
