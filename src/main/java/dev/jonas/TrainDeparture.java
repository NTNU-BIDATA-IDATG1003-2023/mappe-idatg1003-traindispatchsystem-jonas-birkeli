package dev.jonas;

/**
 * The {@code TrainDeparture} class represents a train on a station.
 * All {@code TrainDeparture}s has a departuretime, delay, line, destination and track.
 *
 * @author Jonas Birkeli
 * @version 1.0.0
 * @since 1.0.0
 */
public class TrainDeparture {

  private int[] departureTime;
  private int[] delay;
  private String line;
  private String destination;
  private int track;
  private int trainNumber;

  /**
   * Constructs a new {@code TrainDeparture} with default values.
   *
   * @since 1.0.0
   */
  public TrainDeparture() {
    setDepartureTime(new int[]{0, 0});
    setLine("");
    setDestination("");
    setTrack(-1);
    setDelay(new int[]{0, 0});
    setTrainNumber(-1);
  }

  /**
   * Constructs a new {@code TrainDeparture} with the given parameters.
   *
   * @param departureTime  Scheduled time for departure of train.
   * @param line Scheduled line of train
   * @param destination End-destination of train.
   * @param track Number of the departure-track.
   * @param trainNumber Unique number for the {@code TrainDeparture}.
   *
   * @see #setDepartureTime(int[])
   * @see #setLine(String)
   * @see #setDestination(String)
   * @see #setTrack(int)
   * @see #setTrainNumber(int)
   * @since 1.0.0
   */
  public TrainDeparture(
      int[] departureTime,
      String line,
      String destination,
      int track,
      int trainNumber) {
    setDepartureTime(departureTime);
    setLine(line);
    setDestination(destination);
    setTrack(track);
    setDelay(new int[]{0, 0});
    setTrainNumber(trainNumber);
  }

  /**
   * Sets the departure time of the {@code TrainDeparture}.
   * Each {@code TrainDeparture} has two departure times,
   * one representing hours, and one representing minutes.
   * If null, departure time is set to 0.
   *
   * @param departureTime the departure time of the {@code TrainDeparture}.
   * @since 1.0.0
   */
  public void setDepartureTime(int[] departureTime) {
    if (departureTime == null) {
      this.departureTime = new int[]{0, 0};
      return;
    }
    this.departureTime = departureTime;
  }

  /**
   * Returns the departure time of the {@code TrainDeparture}.
   *
   * @return the departure time of the {@code TrainDeparture}.
   * @since 1.0.0
   */
  public int[] getDepartureTime() {
    return departureTime;
  }

  /**
   * Sets the delay of the {@code TrainDeparture}.
   * Each {@code TrainDeparture} has two delays,
   * one representing hours, and one representing minutes.
   * If null, delay is set to 0.
   *
   * @param delay the delay of the {@code TrainDeparture}.
   * @since 1.0.0
   */
  public void setDelay(int[] delay) {
    if (delay == null) {
      this.delay = new int[]{0, 0};
      return;
    }
    this.delay = delay;
  }

  /**
   * Returns the delay of the {@code TrainDeparture}.
   *
   * @return the delay of the {@code TrainDeparture}.
   * @since 1.0.0
   */
  public int[] getDelay() {
    return delay;
  }

  /**
   * Sets the line of the {@code TrainDeparture}.
   * If null, line is set to empty.
   *
   * @param line the line of the {@code TrainDeparture}.
   * @since 1.0.0
   */
  public void setLine(String line) {
    if (line == null) {
      this.line = "";
      return;
    }
    this.line = line;
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
   *
   * @param destination the destination of the {@code TrainDeparture}.
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
   * If negative, track is set to -1.
   *
   * @param track the track of the {@code TrainDeparture}.
   * @since 1.0.0
   */
  public void setTrack(int track) {
    if (track < 0) {
      this.track = -1;
      return;
    }
    this.track = track;
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
   * Sets a train number for the {@code TrainDeparture}.
   * If negative, train number is set to -1.
   * Number must be unique for each {@code TrainDeparture}.
   *
   * @since 1.0.0
   */
  public void setTrainNumber(int trainNumber) {
    if (trainNumber < 0) {
      this.trainNumber = -1;
      return;
    }
    this.trainNumber = trainNumber;
  }

  /**
   * Returns the train number of the {@code TrainDeparture}.
   *
   * @return the train number of the {@code TrainDeparture}.
   * @since 1.0.0
   */
  public int getTrainNumber() {
    return trainNumber;
  }

  /**
   * Returns a string representation of the {@code TrainDeparture}.
   * Similar to what you see at an actual train station.
   * Format is always the same length with whitespaces appended to the destination field.
   * Departure time includes any set delay.
   * If some values are not set, they return string will be empty.
   * Includes newline at the end if all values are set.
   *
   * @see #getLine()
   * @see #getTrack()
   * @see #getDepartureTime()
   * @see #getDestination()
   * @return a string representation of the {@code TrainDeparture}.
   * @since 1.0.0
   */
  public String getDetails() {
    // If some values are not set, return empty string
    if (
        getLine().isEmpty()
            || getTrack() == -1
            || getDepartureTime()[0] == 0
            || getDepartureTime()[1] == 0
            || getDestination().isEmpty()
    ) {
      return "";
    }

    // Reversing the input
    StringBuilder inputReversed = new StringBuilder();
    inputReversed.append(getDestination());
    inputReversed.reverse();

    String whitespacesReversed = String.format("%1$17s", inputReversed);
    // Catting the leading whitespaces to reversed destination
    StringBuilder destination = new StringBuilder(whitespacesReversed);
    destination.reverse();

    // Using StringBuilder for efficiency and readability
    StringBuilder objectInfomation;
    objectInfomation = new StringBuilder();

    // Appending details of the train departure to StringBuilder
    objectInfomation
        .append(getDepartureTime()[0] + getDelay()[0])
        .append(":")
        .append(getDepartureTime()[1] + getDelay()[1])
        .append(" ")
        .append(getLine())
        .append(" ")
        .append(destination)
        .append(" ")
        .append(getTrack())
        .append("\n");

    return objectInfomation.toString();
  }
}
