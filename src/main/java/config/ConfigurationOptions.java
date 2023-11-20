package config;

/**
 * A class for storing configuration options.
 * This class makes it easy to adjust values and/or add new features to the application.
 *
 * @author Jonas Birkeli
 * @version 1.0.0
 * @since 1.0.0
 */
public class ConfigurationOptions {
  public static final int STATE_VIEW_DEPARTURES = 1;
  public static final int STATE_ADD_DEPARTURE = 2;
  public static final int STATE_ASSIGN_TRACK = 3;
  public static final int STATE_ASSIGN_DELAY = 4;
  public static final int STATE_SELECT_TRAIN_BY_NUMBER = 5;
  public static final int STATE_SEARCH_BY_DESTINATION = 6;
  public static final int STATE_CHANGE_TIME = 7;
  public static final int STATE_EXIT = 8;
  public static final int STATE_HELP = 9;

  public static final String INVALID_INPUT_MESSAGE = "Invalid input. Please try again.";

  public static final String STATION_DEPARTURE_SCREEN_TITLE =
      "AVGANGER Departures        SPOR Track   ";  // Spaces at end to account for station

  private ConfigurationOptions() {
    // Empty constructor to hide the implicit public one.
  }
}
