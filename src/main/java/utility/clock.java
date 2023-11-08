package utility;

/**
 * The {@code clock} class represents a clock.
 * It is used to keep track of time.
 * It only represents the time as a 24-hour clock.
 * Overflow is considered and checked when updating time.
 *
 * @author Jonas Birkeli
 * @version 1.0.0
 * @since 1.0.0
 */
public class clock {
  // Variables used in this class
  private int hour;
  private int minute;

  /**
   * Constructs a new {@code clock} with the given parameters.
   *
   * @param hour The hour of the clock.
   * @param minute The minute of the clock.
   *
   * @since 1.0.0
   */
  public clock(int hour, int minute) {
    setTime(hour, minute);
  }

  /**
   * Sets the {@code hour} and {@code minute} of the clock.
   * Overflow will result in modulo of
   *
   * @param hour The hour of the clock.
   *             If the hour is greater than 23, the hour will be incremented by the number of
   *             times 24 fits within the hour.
   * @param minute The minute of the clock.
   *               If the minute is greater than 59, the hour will be incremented by the number of
   *               times 60 fits within the minute.
   * @since 1.0.0
   */
  private void setTime(int hour, int minute) {

    hour += minute / 60;  // Adds how many times minute fits within 60 minutes to hour.
    // Floor division

    minute %= 60;  // Overflow check
    hour %= 24;  // Overflow check

    // Sets new values
    setHour(hour);
    setMinute(minute);
  }

  /**
   * Sets the {@code hour} of the clock.
   * Checking for overflow is done in {@link #setTime(int, int)}.
   *
   * @param hour The hour of the clock.
   *             Must be between 0 and 23.
   *             If not, the hour will be set to 0.
   * @since 1.0.0
   */
  private void setHour(int hour) {
    if (0 > hour || hour > 23) {
      this.hour = 0;
      return;
    }
    this.hour = hour;
  }

  /**
   * Sets the {@code minute} of the clock.
   * Checking for overflow is done in {@link #setTime(int, int)}.
   * @param minute The minute of the clock.
   *               Must be between 0 and 59.
   *               If not, the minute will be set to 0.
   * @since 1.0.0
   */
  private void setMinute(int minute) {
    if (0 > minute || minute > 59) {
      this.minute = 0;
      return;
    }
    this.minute = minute;
  }

  /**
   * Returns the time as an array with the first element being the hour and the second element being
   * the minute.
   *
   * @return The time as an array with the first element being the hour and the second element being
   *    the minute.
   * @since 1.0.0
   */
  public int[] getTimeAsArray() {
    return new int[]{hour, minute};
  }

  /**
   * Returns the time as a string with correct formatting.
   * Example of format:
   * <p>
   *   <ul>
   *     <li>00:00</li>
   *     <li>09:09</li>
   *     <li>23:59</li>
   *   </ul>
   * </p>
   *
   * @return The time as a string with correct formatting.
   * @since 1.0.0
   */
  public String getTimeAsString() {
    StringBuilder time = new StringBuilder();

    // Adds a 0 in front of the hour if the hour is less than 10
    if (hour < 10) {
      time.append("0");
    }
    time.append(hour).append(":");

    // Adds a 0 in front of the minute if the minute is less than 10
    if (minute < 10) {
      time.append("0");
    }
    time.append(minute);
    // Returns the time as a string
    return String.valueOf(time);
  }
}
