package io;

import config.Colors;
import config.ConfigurationOptions;
import core.Station;
import core.TrainDeparture;
import java.util.stream.Stream;
import lang.UserTextFeedback;
import utility.InputHandler;
import utility.Printer;

/**
 * The {@code DispatchApp} class represents the main class of the program.
 * The {@code DispatchApp} class handles the main loop of the program,
 * and connects the {@code Station} to the user with a text-based interface.
 * <br>
 * The program can be started with the {@link #start()} method.
 *
 * @author Jonas Birkeli
 * @version 1.7.0
 * @since 1.0.0
 */
public class DispatchSystem {
  public static final String APP_NAME = "DispatchApp";
  public static final String APP_VERSION = "1.7.0";
  public static final String APP_AUTHOR = "Jonas Birkeli";
  public static final String APP_DESCRIPTION = "A dispatch system for trains.";
  public static final String APP_RELEASE_DATE = "29-11-2023";

  // Fields in this class
  private int state;
  private boolean running;
  private final Station station;
  private final InputHandler inputHandler;
  private final Printer printer;

  /**
   * Constructs a new {@code DispatchApp}. This only initializes the fields of the class.
   * The program does not start until the {@link #start()} method is called.
   * The constructor will add some filler train departures to the station,
   * to make the station look more realistic.
   *
   * @since 1.2.0
   */
  public DispatchSystem() {
    state = 0;
    running = true;
    station = new Station();
    inputHandler = new InputHandler();
    printer = new Printer();

    // Adding some filler train departures to make the station look more realistic,
    // instead of forcing the user to add departures just to use and test the program.
    station.addFillerTrainDepartures();
  }

  /**
   * Starts the {@code DispatchApp} and runs it
   * continuously until the user chooses to exit the program.
   * The program starts in the main menu, and will run continuously until the user chooses to exit.
   * The program will clear the screen before starting.
   * <br>
   * The user will be prompted with a main menu,
   * where the user can choose between the following options:
   * <ul>
   *   <li>View train departures</li>
   *   <li>Add train departure</li>
   *   <li>Remove selected train departure</li>
   *   <li>Assign track to selected train departure</li>
   *   <li>Assign delay to selected train departure</li>
   *   <li>Select train departure by number</li>
   *   <li>Search train departure by destination</li>
   *   <li>Change time</li>
   *   <li>Exit</li>
   *   <li>Help</li>
   * </ul>
   *
   * @since 1.0.0
   */
  public void start() {
    printer.println("Starting " + APP_NAME + " v." + APP_VERSION);
    // Clears the screen before starting the program.
    // This method does not work in all terminals or operating systems.
    printer.clearScreen();

    // Starts program to run continuously until user chooses to exit
    // Uses a switch statement to determine which method to run,
    // based on the current state of the program
    while (running) {

      mainMenu();
      switch (state) {
        case ConfigurationOptions.STATE_VIEW_DEPARTURES ->
            viewTrainDepartures();
        case ConfigurationOptions.STATE_ADD_DEPARTURE ->
            addTrainDeparture();
        case ConfigurationOptions.STATE_REMOVE_DEPARTURE ->
            removeTrainDeparture();
        case ConfigurationOptions.STATE_ASSIGN_TRACK ->
            assignTrackToTrainDeparture();
        case ConfigurationOptions.STATE_ASSIGN_DELAY ->
            assignDelayToTrainDeparture();
        case ConfigurationOptions.STATE_SELECT_TRAIN_BY_NUMBER ->
            selectTrainDepartureByTrainNumber();
        case ConfigurationOptions.STATE_SEARCH_BY_DESTINATION ->
            searchTrainDepartureByDestination();
        case ConfigurationOptions.STATE_CHANGE_TIME ->
            changeTime();
        case ConfigurationOptions.STATE_EXIT ->
            exitApplication();
        case ConfigurationOptions.STATE_HELP ->
            help();
        default ->
            running = false;
      }
      if (state != ConfigurationOptions.STATE_EXIT) {
        // If we are not exiting the program, we wait for user input before clearing the screen.
        // This way, the user has time to read the output before it is cleared.
        inputHandler.waitForUserInput();
        printer.clearScreen();
      }
    }
  }

  /**
   * Displays the main menu of the program.
   * Continuously asks for user input until a valid choice is made.
   * If the user input is not a valid choice, an error message is displayed.
   * If the user input is a valid choice, the state of the program is changed
   * to the corresponding choice.</p>
   *
   * @since 1.0.0
   */
  private void mainMenu() {
    StringBuilder selectedTrainDepartureDetails;
    selectedTrainDepartureDetails = new StringBuilder();

    selectedTrainDepartureDetails
        .append(UserTextFeedback.PROMPT_SELECTED_TRAIN_HEADER);

    if (station.getSelectedTrainDeparture() != null) {
      selectedTrainDepartureDetails
          .append(Colors.GREEN)
          .append(buildTrainDepartureDetails(station.getSelectedTrainDeparture()))
          .append(Colors.RESET);
    } else {
      selectedTrainDepartureDetails
          .append(UserTextFeedback.ERROR_NO_TRAIN_SELECTED);
    }
    
    // Continuously ask for user input until a valid choice is made
    printer.println(String.valueOf(selectedTrainDepartureDetails));
    printer.println(UserTextFeedback.MAIN_MENU_OPTIONS);

    // User input, must be between 1 and 9, incorrect input is not accepted
    state = inputHandler.getValidIntInput(UserTextFeedback.INPUT_CHOICE, 1, 10);
  }

  /**
   * Displays all {@code TrainDeparture}s with a header for formatting alongside the station time.
   * If some info from one {@code TrainDeparture} is missing, it will not be displayed.
   * If departure time is earlier than current time, it will not be displayed.
   *
   * <p>
   *   The departures are displayed in the following format:
   *   <ul>
   *     <li>Departure time, including delay</li>
   *     <li>Line</li>
   *     <li>Destination</li>
   *     <li>Track</li>
   *     <li>Train number</li>
   *   </ul>
   * </p>
   *
   * @since 1.0.0
   */
  private void viewTrainDepartures() {
    // Appends the clock to the end of the title as it uses print and not println
    printer.println("\n" + station.getStationClock().getTimeAsString());
    // Format of which departures are displayed:
    printer.println(ConfigurationOptions.STATION_DEPARTURE_SCREEN_TITLE);

    // Loops through all departures, and prints them if they are valid
    // Departures are sorted by departure time
    // Departures with earlier departure time than current time are not displayed
    printer.print(Colors.WHITE_BRIGHT);  // ANSI escape code for bold white text
    station.getStreamOfTimeFilteredTrainDepartures()
        .map(this::buildTrainDepartureDetails) // Gets details of each departure
        .forEach(printer::print);

    printer.println(Colors.RESET + "\n");
  }

  /**
   * Adds a {@code TrainDeparture} to the station.
   * The user is asked to enter a unique train number.
   * If the train number already exists, the user is asked if they want to override it.
   * If the user does not want to override it,
   * the loop continues until a valid train number is entered.
   *
   *
   * @since 1.5.0
   * @see TrainDeparture
   */
  private void addTrainDeparture() {
    printer.println(UserTextFeedback.PROMPT_ADD_TRAIN);

    // Getting a unique train number from user
    // Train number is unique, so we need to check if it already exists with a robust method
    int trainNumber = checkAndGetValidTrainNumber();

    // In case the user does not know what train-number is valid,
    // the user can enter -1 to exit the method.
    TrainDeparture trainDeparture;

    if (trainNumber != -1) {
      // Getting input from user for every field of the train departure
      printer.print(Colors.WHITE_BRIGHT);
      int departureHour = inputHandler.getValidIntInput(
          UserTextFeedback.PROMPT_ENTER_DEPARTURE_TIME, 0, 23
      );
      int departureMinute = inputHandler.getValidIntInput(
          UserTextFeedback.PROMPT_ENTER_DEPARTURE_MINUTE, 0, 59
      );
      String line = inputHandler.getValidStringInput(
          UserTextFeedback.PROMPT_ENTER_LINE, ConfigurationOptions.MAX_LINE_LENGTH
      );
      String destination = inputHandler.getValidStringInput(
          UserTextFeedback.PROMPT_ENTER_DESTINATION, ConfigurationOptions.MAX_DESTINATION_LENGTH
      );
      int track = inputHandler.getValidIntInput(
          UserTextFeedback.PROMPT_ENTER_TRACK_HELP, -1, 68
      );

      trainDeparture = new TrainDeparture(
          departureHour, departureMinute, line, destination, track, trainNumber
      );

      // Inserting the train departure into the station
      station.addTrainDeparture(trainDeparture);

      printer.println(UserTextFeedback.PROMPT_TRAIN_ADDED_SUCCESSFULLY_1
          + Colors.WHITE_BOLD_BRIGHT
          + destination
          + Colors.RESET
          + UserTextFeedback.PROMPT_TRAIN_ADDED_SUCCESSFULLY_2
          + Colors.WHITE_BOLD_BRIGHT
          + trainNumber
          + UserTextFeedback.PROMPT_TRAIN_ADDED_SUCCESSFULLY_3
          + Colors.RESET);
    }
  }

  /**
   * Removes a {@code TrainDeparture} from the station.
   * If no {@code TrainDeparture} is selected, user is prompted an error message.
   * If a {@code TrainDeparture} is selected, the user is asked if they want to remove it.
   *
   * @since 1.6.0
   */
  private void removeTrainDeparture() {
    if (station.getSelectedTrainDeparture() != null) {
      printer.println(UserTextFeedback.PROMPT_TRAIN_FOR_REMOVAL);
      printer.println(buildTrainDepartureDetails(station.getSelectedTrainDeparture()));

      String answer = inputHandler.getValidStringInput(
          UserTextFeedback.PROMPT_REMOVE_EXISTING_TRAIN,
          -1
      );

      if (answer.equalsIgnoreCase("y")) {
        station.removeTrainDeparture();
        printer.println(UserTextFeedback.STATUS_REMOVING_TRAIN);
      } else {
        printer.println(UserTextFeedback.STATUS_NOT_REMOVING_TRAIN);
      }
    } else {
      printer.printError(UserTextFeedback.ERROR_NO_TRAIN_SELECTED);
    }
  }

  /**
   * Checks if the train number is valid.
   * If the train number already exists, the user is asked if they want to override it.
   * If the user does not want to override it,
   * the loop continues until a valid train number is entered.
   * In case the user does not know what train-number is valid,
   * the user can enter -1 to exit the method. In this case,
   * -1 is returned and needs to be accounted for.
   *
   * @return A valid train number for adding a new {@code TrainDeparture}.
   * @since 1.4.0
   */
  private int checkAndGetValidTrainNumber() {
    boolean validInput = false;
    int trainNumber;
    do {
      trainNumber = inputHandler.getValidIntInput(
          UserTextFeedback.PROMPT_ENTER_TRAIN_NUMBER, 1, Integer.MAX_VALUE
      );

      if (station.hasTrainDepartureWithTrainNumber(trainNumber)) {
        // If train number already exists, the user is asked if they want to override it
        printer.println(UserTextFeedback.PROMPT_NUMBER_OVERRIDE_CHOICE);
        String answer = inputHandler.getValidStringInput(UserTextFeedback.INPUT_CHOICE, -1);

        // If user wants to override, the loop exits
        if (answer.equalsIgnoreCase("y")) {
          validInput = true;
        } else if (answer.equals("-1")) {
          trainNumber = -1;  // If user wants to exit, -1 is returned
          printer.println(UserTextFeedback.PROMPT_CANCEL_ADD_TRAIN);

          validInput = true;
        } else {
          // If the user does not want to override, an error message is displayed
          // and the loop continues.
          printer.printError(UserTextFeedback.ERROR_TRY_AGAIN);
        }
      } else {
        // If train number does not exist,
        // the train number is valid either way and we exit the loop
        validInput = true;
      }

    } while (!validInput);
    return trainNumber;
  }

  /**
   * Assigns a track to a {@code TrainDeparture}.
   * If no {@code TrainDeparture} is selected, user is prompted an error message.
   *
   * @since 1.0.0
   */
  private void assignTrackToTrainDeparture() {
    if (station.getSelectedTrainDeparture() != null) {
      printer.println(UserTextFeedback.PROMPT_ASSIGN_TRACK);
      printer.println(buildTrainDepartureDetails(station.getSelectedTrainDeparture()));

      int track = inputHandler.getValidIntInput(UserTextFeedback.PROMPT_ENTER_TRACK, 1, 68);

      station.getSelectedTrainDeparture().setTrack(track);
      // Updates track of selected departure

      printer.println(UserTextFeedback.PROMPT_TRACK_ASSIGNED_SUCCESSFULLY);

    } else {
      // If no departure is selected, the method returns early
      printer.printError(UserTextFeedback.ERROR_NO_TRAIN_SELECTED);
    }
  }

  /**
   * Assigns a delay to a {@code TrainDeparture}.
   * If no {@code TrainDeparture} is selected, user is prompted an error message.
   *
   * @since 1.0.0
   */
  private void assignDelayToTrainDeparture() {
    if (station.getSelectedTrainDeparture() != null) {
      printer.println(UserTextFeedback.PROMPT_ASSIGN_DELAY);
      printer.println(buildTrainDepartureDetails(station.getSelectedTrainDeparture()));
      // Prints details of selected departure

      int delayHour = inputHandler.getValidIntInput(UserTextFeedback.PROMPT_ENTER_HOUR, 0, 23);
      int delayMinute = inputHandler.getValidIntInput(UserTextFeedback.PROMPT_ENTER_MINUTE, 0, 59);

      station.getSelectedTrainDeparture().setDelay(delayHour, delayMinute);
      // Updates delay of departure
    } else {
      // If no departure is selected, an error message is displayed
      printer.printError(UserTextFeedback.ERROR_NO_TRAIN_SELECTED);
    }
  }

  /**
   * Searches for a {@code TrainDeparture} by train number.
   * If no {@code TrainDeparture} is found, an error message is displayed,
   * and selected departure is not changed
   *
   * @since 1.0.0
   */
  private void selectTrainDepartureByTrainNumber() {
    printer.println(UserTextFeedback.PROMPT_SELECT_TRAIN);
    int trainNumber = inputHandler.getValidIntInput(
        UserTextFeedback.PROMPT_ENTER_TRAIN_NUMBER, 1, Integer.MAX_VALUE
    );

    if (station.selectTrainDeparture(trainNumber) == 0) {
      // Method above returns 0 if train departure is found and successfully selected
      printer.println(UserTextFeedback.PROMPT_TRAIN_SELECTED_SUCCESSFULLY);
      printer.println(buildTrainDepartureDetails(station.getSelectedTrainDeparture()));
    } else {
      printer.printError(String.format(UserTextFeedback.ERROR_NO_TRAIN_WITH_NUMBER, trainNumber));
      printer.println(UserTextFeedback.ERROR_SEARCH_FOR_TRAIN_FIRST);
    }
  }

  /**
   * Searches for a {@code TrainDeparture} by destination.
   * If no {@code TrainDeparture} is found, an error message is displayed,
   *
   * @since 1.0.0
   */
  private void searchTrainDepartureByDestination() {
    printer.println(UserTextFeedback.PROMPT_SEARCH_TRAIN);
    String destination = inputHandler.getValidStringInput(
        UserTextFeedback.PROMPT_ENTER_DESTINATION,
        ConfigurationOptions.MAX_DESTINATION_LENGTH
    );

    // Making a temporary stream to check if any train departures are found.
    // Is made into a stream of details of each train departure.
    Stream<String> trainDepartureDetails =
        station.getAllTrainDeparturesByPartialDestination(destination)
        .map(this::buildTrainDepartureDetails);

    if (trainDepartureDetails.findAny().isEmpty()) {
      //
      printer.printError(String.format(UserTextFeedback.ERROR_NO_TRAIN_FOUND, destination));
    } else {
      printer.println(UserTextFeedback.PROMPT_TRAIN_FOUND);
      station.getAllTrainDeparturesByPartialDestination(destination)
          .map(this::buildTrainDepartureDetails)
          .forEach(printer::print);
    }
  }

  /**
   * Changes the time of the station.
   * The user is asked to enter a new hour and minute.
   * The entered time is checked for overflow and set to a 24-hour format.
   * The time of the station is then changed to the new time.
   *
   * @since 1.0.0
   */
  private void changeTime() {
    printer.println(UserTextFeedback.PROMPT_CHANGE_TIME);
    int hour = inputHandler.getValidIntInput(UserTextFeedback.PROMPT_ENTER_HOUR, 0, 23);
    int minute = inputHandler.getValidIntInput(UserTextFeedback.PROMPT_ENTER_MINUTE, 0, 59);
    boolean timeIsValid = station.setStationTime(hour, minute);

    if (timeIsValid) {
      printer.println(String.format(
          UserTextFeedback.TIME_CHANGED_SUCCESFULLY, station.getStationClock().getTimeAsString())
      );
    } else {
      printer.printError(UserTextFeedback.ERROR_TIME_NOT_LATER);
    }
  }

  /**
   * Exits the application by breaking the main loop, and printing a status message to the user.
   *
   * @since 1.0.0
   */
  private void exitApplication() {
    printer.println(UserTextFeedback.PROMPT_EXIT);
    running = false;
  }

  /**
   * Displays a help message to the user on how the program works.
   * Explains what to do to modify a {@code TrainDeparture},
   * and lists what you can do in this program.
   *
   * @since 1.4.0
   */
  private void help() {
    printer.clearScreen();
    printer.print(UserTextFeedback.HELP_MESSAGE);
  }

  /**
   * Creates a String representation of every valuable field for an end-user to read.
   * If {@code TrainDeparture.line} or {@code TrainDeparture.destination} is not set,
   * the method will return an empty string.
   * If {@code TrainDeparture.track} is not set,
   * the method sets "TBA" as field instead of the track number.
   * If {@code TrainDeparture.delay} is not set,
   * the method ignores the delay.
   * If {@code TrainDeparture.delay}, it strikes through the departure time,
   * and displays the actual departure time.
   * <br>
   * If all values are present, the method returns a string with all values.
   *
   * @param trainDeparture The {@code TrainDeparture} to build a string representation of.
   * @return A string representation of the {@code TrainDeparture}.
   * @since 1.7.0
   */
  private String buildTrainDepartureDetails(TrainDeparture trainDeparture) {
    // Using StringBuilder to avoid long lines and improve readability
    StringBuilder objectInformation = new StringBuilder();

    if (trainDeparture.getDelay().getHour() == 0 && trainDeparture.getDelay().getMinute() == 0) {
      // There is no delay, so we only need to display the departure time
      objectInformation.append(trainDeparture.getDepartureTime().getTimeAsString())
          .append("      ");  // Spaces representing the allocated space for the departure time,
      // including delay
    } else {
      objectInformation.append(Colors.RED_BRIGHT + Colors.STRIKETHROUGH)
            .append(trainDeparture.getDepartureTime().getTimeAsString())
            .append(Colors.RESET + Colors.WHITE_BRIGHT)
            .append(" ")
            .append(trainDeparture
                .getDepartureTime()
                .combine(trainDeparture.getDelay()).getTimeAsString()
          // Combining the departure-time with delay to get the actual departure time
          );
    }

    objectInformation.append(" ")
       .append(trainDeparture.getLine())
        .append(" ".repeat(
            ConfigurationOptions.MAX_LINE_LENGTH - trainDeparture.getLine().length())
        )
        .append(" ")
        .append(trainDeparture.getDestination())
        .append(" ".repeat(
            ConfigurationOptions.MAX_DESTINATION_LENGTH - trainDeparture.getDestination().length())
        )
        .append(" ");

    if (trainDeparture.getTrack() == -1) {
      // If the track is not set, we display "TBA" instead of the track number
      objectInformation.append(UserTextFeedback.TBA);
    } else {
      objectInformation.append(trainDeparture.getTrack())
          .append(" ".repeat(
              ConfigurationOptions.MAX_TRACK_LENGTH - String.valueOf(
                  trainDeparture.getTrack()).length()
              )
          );
    }

    objectInformation.append("          ")
        // Empty string for spacing the trainnumber away form the details
        .append(trainDeparture.getTrainNumber()).append("\n");

    // Initializing a temporary string for returning, in case some values are not set
    String returnString = "";

    // If some values are not set, the required fields are not set and therefore the departure
    // is not valid.
    if (trainDeparture.isValidDeparture()) {
      returnString = String.valueOf(objectInformation);
    }

    // Is empty if some values are not set
    // else, the objectInformation is returned
    return returnString;

  }
}