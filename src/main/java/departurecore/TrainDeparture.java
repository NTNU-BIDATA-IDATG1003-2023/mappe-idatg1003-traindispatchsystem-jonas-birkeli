package departurecore;

import java.util.Arrays;
import java.util.Objects;
import utility.Clock;


/**
 * The {@code departurecore.TrainDeparture} class represents a train on a station.
 * All {@code departurecore.TrainDeparture}s has a departuretime,
 * delay, line, destination and track.
 * <p>
 *   This class has the following methods:
 *   <ul>
 *     <li>{@link #setDepartureTime(int[])}</li>
 *     <li>{@link #getDepartureTime()}</li>
 *     <li>{@link #setDelay(int[])}</li>
 *     <li>{@link #getDelay()}</li>
 *     <li>{@link #setLine(String)}</li>
 *     <li>{@link #getLine()}</li>
 *     <li>{@link #setDestination(String)}</li>
 *     <li>{@link #getDestination()}</li>
 *     <li>{@link #setTrack(int)}</li>
 *     <li>{@link #getTrack()}</li>
 *     <li>{@link #getDetails()}</li>
 *     <li>{@link #compareTo(TrainDeparture)}</li>
 *   </ul>
 * </p>
 *
 * @author Jonas Birkeli
 * @version 1.4.0
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
   * Constructs a new {@code departurecore.TrainDeparture} with default values.
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
   * Constructs a new {@code departurecore.TrainDeparture} with the given parameters,
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
   * Adjusts the departure-time of the {@code TrainDeparture} to the
   * Each {@code departurecore.TrainDeparture} has two departure times,
   * one representing hours, and one representing minutes.
   * If null, not two elements or either is negative, departure time is set to 0.
   *
   * @param departureTime the departure time of the {@code departurecore.TrainDeparture}.
   * @since 1.0.0
   */
  public void setDepartureTime(int[] departureTime) {
    if (departureTime == null
        || departureTime.length != 2
        || departureTime[0] < 0
        || departureTime[1] < 0
    ) {
      // If any of the conditions above is false, departuretime is set to default.
      this.departureTime.setTime(0, 0);
    } else {
      // Method overload for setting departure time using an array.
      this.departureTime.setTime(departureTime);
    }
  }


  /**
   * Returns the departure time of the {@code departurecore.TrainDeparture}
   * as an integer list in the format {HH, MM}.
   *
   * @return the departure time of the {@code departurecore.TrainDeparture}.
   * @since 1.3.0
   */
  public Clock getDepartureTime() {
    return departureTime;
  }

  /**
   * Sets the delay of the {@code departurecore.TrainDeparture}.
   * Each {@code departurecore.TrainDeparture} has two delays,
   * one representing hours, and one representing minutes.
   * If null, not two elements or either is negative, delay is set to 0.
   *
   * @param delay the delay of the {@code departurecore.TrainDeparture}.
   * @since 1.0.0
   */
  public void setDelay(int[] delay) {
    if (delay == null
        || delay.length != 2
        || delay[0] < 0
        || delay[1] < 0
    ) {
      this.delay.setTime(0, 0);
    } else {
      this.delay.setTime(delay);
    }
  }

  /**
   * Returns the delay of the {@code departurecore.TrainDeparture}
   * as an integer list in the format {HH, MM}.
   *
   * @return the delay of the {@code departurecore.TrainDeparture}.
   * @since 1.0.0
   */
  public int[] getDelay() {
    return delay.getTime();
  }

  /**
   * Sets the line of the {@code departurecore.TrainDeparture}.
   * If null, line is set to empty.
   *
   * @param line the line of the {@code departurecore.TrainDeparture}.
   * @since 1.0.0
   */
  public void setLine(String line) {
    if (line == null) {
      this.line = "";
    } else {
      this.line = line;
    }
  }

  /**
   * Returns the line of the {@code departurecore.TrainDeparture}.
   *
   * @return the line of the {@code departurecore.TrainDeparture}.
   * @since 1.0.0
   */
  public String getLine() {
    return line;
  }

  /**
   * Sets the destination of the {@code departurecore.TrainDeparture}.
   * If null, destination is set to empty.
   *
   * @param destination the destination of the {@code departurecore.TrainDeparture}.
   * @since 1.0.0
   */
  public void setDestination(String destination) {
    if (destination == null) {
      this.destination = "";
      return;
    }
    this.destination = destination;
  }

  /**
   * Returns the destination of the {@code departurecore.TrainDeparture}.
   *
   * @return the destination of the {@code departurecore.TrainDeparture}.
   * @since 1.0.0
   */
  public String getDestination() {
    return destination;
  }

  /**
   * Sets the track of the {@code departurecore.TrainDeparture}.
   * If negative or zero, track is set to -1.
   *
   * @param track the track of the {@code departurecore.TrainDeparture}.
   * @since 1.0.0
   */
  public void setTrack(int track) {
    if (track <= 0) {
      this.track = -1;
      return;
    }
    this.track = track;
  }

  /**
   * Returns the track of the {@code departurecore.TrainDeparture}.
   *
   * @return the track of the {@code departurecore.TrainDeparture}.
   * @since 1.0.0
   */
  public int getTrack() {
    return track;
  }

  /**
   * Sets the train number of the {@code TrainDeparture}.
   * If negative or zero, train number is set to -1.
   *
   * @since 1.4.0
   */
  public void setTrainNumber(int trainNumber) {
    if (trainNumber <= 0) {
      this.trainNumber = -1;
      return;
    }
    this.trainNumber = trainNumber;
  }

  /**
   * Returns the train number of the {@code TrainDeparture}.
   *
   * @return the train number of the {@code TrainDeparture}.
   * @since 1.4.0
   */
  public int getTrainNumber() {
    return trainNumber;
  }

  /**
   * Returns a string representation of the {@code departurecore.TrainDeparture}.
   * Similar to what you see at an actual train station.
   * Format is always the same length with whitespaces appended to the destination field.
   * Departure time includes any set delay.
   * The Delay field will be a separate field at the end of the String.
   * If some values are not set, they return string will be empty.
   * Includes newline at the end if all values are set.
   * The way the string has been formatted is with the help of StringBuilder.
   * Input is reversed, whitespaces are added to the reversed input in the beginning,
   * and then the reversed input is reversed again.
   * This way, the whitespaces are added to the end of the input.
   * This is done to make sure the string is always the same length.
   * This is done for all fields except the track field.
   *
   * @see #getLine()
   * @see #getTrack()
   * @see #getDepartureTime()
   * @see #getDestination()
   * @return a string representation of the {@code departurecore.TrainDeparture}.
   * @since 1.0.0
   */
  public String getDetails() {
    boolean departureIsValid = true;
    // If some values are not set, return empty string
    if (line.isEmpty()
            || destination.isEmpty()
            || track == -1
    ) {
      departureIsValid = false;
    }

    // Formatting the departure time with leading zeros if needed
    String formattedDepartureTime = departureTime
        .combineTime(delay.getTime())
        .getTimeAsString();

    // Reversing the input
    StringBuilder inputReversed = new StringBuilder();
    inputReversed.append(formattedDepartureTime);
    inputReversed.append(" ");
    inputReversed.append(getLine());
    inputReversed.append(" ");
    inputReversed.append(getDestination());
    inputReversed.reverse();


    // Catting the leading whitespaces to reversed destination
    StringBuilder formattedDestination = new StringBuilder();

    // This line has been taken from Baeldung. It is used to add whitespaces to the reversed
    // Source: https://www.baeldung.com/java-pad-string
    // input. The input is reversed again after this line.
    formattedDestination.append(String.format("%1$26s", inputReversed));
    formattedDestination.reverse();


    // Using StringBuilder for efficiency and readability
    StringBuilder objectInformation;
    objectInformation = new StringBuilder();

    // Appending details of the train departure to StringBuilder
    objectInformation
        .append(formattedDestination)
        .append(" ")
        .append(getTrack())
        .append("            ");

    if (delay.getHour() == 0 && delay.getMinute() == 0) {
      objectInformation.append("On time");
    } else {
      objectInformation.append(delay.getTimeAsString());
    }

    objectInformation.append("\n");  //

    // If some values are not set, return empty string insead of objectInformation
    if (!departureIsValid) {
      return "";
    } else {
      return String.valueOf(objectInformation);
    }
  }

  /**
   * Compares this {@code TrainDeparture} with the specified {@code TrainDeparture} for order.
   * Returns a negative integer, zero, or a positive integer as this {@code TrainDeparture}
   * is less than, equal to, or greater than the specified {@code TrainDeparture}.
   * The comparison is primarily based on the departure time of the {@code TrainDeparture}.
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
      }

      // Combining departure time and delay for each departure, stored as a new clock object.
      int[] thisDepartureTime = departureTime.combineTime(delay.getTime()).getTime();
      int[] otherDepartureTime = other.departureTime.combineTime(other.delay.getTime()).getTime();

      // Comparing departure times
      if (thisDepartureTime[0] > otherDepartureTime[0]) {
        state = 1;
      }
      if (thisDepartureTime[0] < otherDepartureTime[0]) {
        state = -1;  // If this is smaller, return -1 meaning smaller
      }
      // If departure hours are equal, compare minutes
      // Using builtin comparator for integers to compare minutes

      if (state == -5) {
        // If state is still -5, departure hours are equal
        state =  Integer.compare(thisDepartureTime[1], otherDepartureTime[1]);
      }
    }
    return state;
  }

  /**
   * Indicates whether some other object is "equal to" this one.
   * Two {@code departurecore.TrainDeparture}s are considered equal if they have the same
   * departure time, delay, line, destination and track.
   * If any of these values are not equal, the {@code departurecore.TrainDeparture}s are not equal.
   * If the specified object is null, returns false.
   * If the specified object is not a {@code departurecore.TrainDeparture}, returns false.
   * If the specified object is a {@code departurecore.TrainDeparture} and all values are equal,
   * returns true.
   *
   * @param other the object with which to compare.
   * @return true if this object is the same as the obj argument; false otherwise.
   * @since 1.2.0
   */
  @Override
  public boolean equals(Object other) {
    if (this == other) {
      return true;
    }
    if (!(other instanceof TrainDeparture that)) {
      return false;
    }
    return getTrack() == that.getTrack() && Arrays.equals(getDepartureTime().getTime(),
        that.getDepartureTime().getTime()) && Arrays.equals(getDelay(), that.getDelay())
        && Objects.equals(getLine(), that.getLine()) && Objects.equals(
        getDestination(), that.getDestination());
  }

  /**
   * Returns a hash code value for the {@code departurecore.TrainDeparture}.
   * The hash code is based upon the departure time, delay, line, destination and track.
   * If any of these values are not equal, the hash codes will not be equal.
   * If the hash codes are not equal, the {@code departurecore.TrainDeparture}s are not equal.
   *
   * @return a hash code value for the {@code departurecore.TrainDeparture}.
   * @since 1.2.0
   */
  @Override
  public int hashCode() {
    int result = Objects.hash(getLine(), getDestination(), getTrack());
    result = 31 * result + Arrays.hashCode(getDepartureTime().getTime());
    result = 31 * result + Arrays.hashCode(getDelay());
    return result;
  }
}
