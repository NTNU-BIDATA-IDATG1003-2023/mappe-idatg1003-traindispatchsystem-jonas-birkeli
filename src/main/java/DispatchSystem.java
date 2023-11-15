import static config.ConfigurationOptions.INVALID_INPUT_MESSAGE;
import static config.ConfigurationOptions.STATE_ADD_DEPARTURE;
import static config.ConfigurationOptions.STATE_ASSIGN_DELAY;
import static config.ConfigurationOptions.STATE_ASSIGN_TRACK;
import static config.ConfigurationOptions.STATE_CHANGE_TIME;
import static config.ConfigurationOptions.STATE_EXIT;
import static config.ConfigurationOptions.STATE_HELP;
import static config.ConfigurationOptions.STATE_SEARCH_BY_DESTINATION;
import static config.ConfigurationOptions.STATE_SEARCH_BY_NUMBER;
import static config.ConfigurationOptions.STATE_VIEW_DEPARTURES;
import static config.ConfigurationOptions.STATION_DEPARTURE_SCREEN_TITLE;

import departurecore.Station;
import departurecore.TrainDeparture;
import java.util.stream.Stream;
import utility.InputHandler;
import utility.Printer;

/**
 * The {@code DispatchApp} class represents the main class of the program.
 * The {@code DispatchApp} class handles all user input and output,
 * and runs continuously until the user chooses to exit the program.
 * <p>
 *   The {@code DispatchApp} class has the following methods:
 *   <ul>
 *     <li>{@link #start()}</li>
 *     <li>{@link #mainMenu()}</li>
 *     <li>{@link #viewTrainDepartures()}</li>
 *     <li>{@link #addTrainDeparture()}</li>
 *     <li>{@link #assignTrackToTrainDeparture()}</li>
 *     <li>{@link #assignDelayToTrainDeparture()}</li>
 *     <li>{@link #searchTrainDepartureByNumber()}</li>
 *     <li>{@link #searchTrainDepartureByDestination()}</li>
 *     <li>{@link #changeTime()}</li>
 *     <li>{@link #exitApplication()}</li>
 *   </ul>
 * </p>
 *
 * @author Jonas Birkeli
 * @version 1.4.0
 * @since 1.0.0
 */
public class DispatchSystem {
  // Fields in this class
  private int state;
  private boolean running;
  private TrainDeparture selectedDeparture;
  private final Station station;
  private final InputHandler inputHandler;
  private final Printer printer;

  /**
   * Constructs a new {@code DispatchApp}.The program does not start until the {@link #start()}
   * method is called.
   *
   * @since 1.2.0
   */
  public DispatchSystem() {
    state = 0;
    running = true;
    selectedDeparture = null;
    station = new Station();
    inputHandler = new InputHandler();
    printer = new Printer();
  }

  /**
   * Starts the {@code DispatchApp} and runs it
   * continuously until the user chooses to exit the program.
   * The program starts in the main menu, and will run continuously until the user chooses to exit.
   * <p>
   *   The main menu has the following options:
   *   <ul>
   *     <li>View train departures</li>
   *     <li>Add train departure</li>
   *     <li>Assign track to train departure</li>
   *     <li>Assign delay to train departure</li>
   *     <li>Search train departure by number</li>
   *     <li>Search train departure by destination</li>
   *     <li>Change time</li>
   *     <li>Exit</li>
   *   </ul>
   * </p>
   *
   * @since 1.0.0
   */
  public void start() {
    // Hard-coded departures for not having to add them manually every time the program is started
    TrainDeparture dep1 = new TrainDeparture(23, 18, "L1", "Lillehammer", 2, 60);
    TrainDeparture dep2 = new TrainDeparture(23, 57, "F8", "Gjøvik", 2, 22);
    TrainDeparture dep3 = new TrainDeparture(3, 59, "H3", "Hamar", 1, 47);

    // Train numbers are used as keys in the HashMap
    station.addTrainDeparture(dep1);
    station.addTrainDeparture(dep2);
    station.addTrainDeparture(dep3);

    // Starts program to run continuously until user chooses to exit
    // Uses a switch statement to determine which method to run,
    // based on the current state of the program
    while (running) {

      mainMenu();
      switch (state) {
        case STATE_VIEW_DEPARTURES -> viewTrainDepartures();
        case STATE_ADD_DEPARTURE -> addTrainDeparture();
        case STATE_ASSIGN_TRACK -> assignTrackToTrainDeparture();
        case STATE_ASSIGN_DELAY -> assignDelayToTrainDeparture();
        case STATE_SEARCH_BY_NUMBER -> searchTrainDepartureByNumber();
        case STATE_SEARCH_BY_DESTINATION -> searchTrainDepartureByDestination();
        case STATE_CHANGE_TIME -> changeTime();
        case STATE_EXIT -> exitApplication();
        case STATE_HELP -> help();
        default -> running = false;
      }
      if (state != STATE_EXIT) {
        // If we are not exiting the program, we wait for user input before clearing the screen.
        // This way, the user has time to read the output before it is cleared.
        inputHandler.waitForUserInput();
        printer.clearScreen();
      }
    }
  }

  /**
   * Displays the main menu of the program.
   * The main menu has the following options:
   * <ul>
   *   <li>View train departures</li>
   *   <li>Add train departure</li>
   *   <li>Assign track to train departure</li>
   *   <li>Assign delay to train departure</li>
   *   <li>Search train departure by number</li>
   *   <li>Search train departure by destination</li>
   *   <li>Change time</li>
   *   <li>Exit</li>
   * </ul>
   * <p>Continuously asks for user input until a valid choice is made.
   *    If the user input is not a valid choice, an error message is displayed.
   *    If the user input is a valid choice, the state of the program is changed
   *    to the corresponding choice.</p>
   *
   * @since 1.0.0
   */
  private void mainMenu() {
    int choice = 0;
    boolean validChoice = false;

    StringBuilder message;
    message = new StringBuilder();

    // Using constants to display menu options instead of hard-coded numbers in case of change
    message
        .append("Train Dispatch System\n\n")
        .append(STATE_VIEW_DEPARTURES).append(". View train departures\n")
        .append(STATE_ADD_DEPARTURE).append(". Add train departure\n")
        .append(STATE_ASSIGN_TRACK).append(". Assign track to train departure\n")
        .append(STATE_ASSIGN_DELAY).append(". Assign delay to train departure\n")
        .append(STATE_SEARCH_BY_NUMBER).append(". Search train departure by number\n")
        .append(STATE_SEARCH_BY_DESTINATION).append(". Search train departure by destination\n")
        .append(STATE_CHANGE_TIME).append(". Change time\n")
        .append(STATE_EXIT).append(". Exit\n")
        .append(STATE_HELP).append(". Help\n");

    // Continuously ask for user input until a valid choice is made
    printer.println(String.valueOf(message));

    choice = inputHandler.getValidIntInput("Enter choice: ", 1, 9);
    state = choice;  // Change state of program to corresponding user input
  }

  /**
   * Displays all train departures.
   * If some info from one departure is missing, it will not be displayed.
   * If departure time is earlier than current time, it will not be displayed.
   *
   * <p>
   *   The departures are displayed in the following format:
   *   <ul>
   *     <li>Departure time including delay</li>
   *     <li>Line</li>
   *     <li>Destination</li>
   *     <li>Track</li>
   *   </ul>
   * </p>
   *
   * @since 1.0.0
   */
  private void viewTrainDepartures() {
    // Format of which departures are displayed:
    printer.print(STATION_DEPARTURE_SCREEN_TITLE);
    printer.println(station.getStationClock().getTimeAsString());

    // Loops through all departures, and prints them if they are valid
    // Departures are sorted by departure time
    // Departures with earlier departure time than current time are not displayed
    station.getStreamOfTimeFilteredTrainDepartures()
        .map(TrainDeparture::getDetails) // Gets details of each departure
        .forEach(printer::print);

    printer.println("\n");

    // Prints selected departure if it is not null
    if (selectedDeparture != null) {
      printer.println("Selected train departure:");
      printer.println(selectedDeparture.getDetails());
    }
  }

  /**
   * Adds a {@code TrainDeparture} to the list of departures.
   * If some info is not parsable to correct data types,
   * the user is asked to try again.
   * {@code TrainDeparture} is added to the list of departures when all info is valid.
   *
   * @since 1.0.0
   * @see TrainDeparture
   */
  private void addTrainDeparture() {
    // TODO Implement error message for each field if input, especially for integers is invalid
    printer.println("Add train departure");

    String[] fields = {
        "Departure hour",
        "Departure minute",
        "Line",
        "Destination",
        "Track",
    };
    String[] values = new String[fields.length];

    int trainNumber;
    trainNumber = checkAndGetValidTrainNumber();

    // Declare variables in correct scope
    int departureHour = 0;
    int departureMinute = 0;
    String line = "";
    String destination = "";
    int track = -1;

    boolean validInput;  // Used to break the loop if input is valid
    do {
      // Continuously ask for user input until a valid choice is made
      // Stores user input in values array
      for (int i = 0; i < fields.length; i++) {
        values[i] = inputHandler.getValidStringInput(fields[i] + ": ");
      }
      // Expects correct input from user
      validInput = true;

      try {
        // Try to parse user input to correct data types
        departureHour = Integer.parseInt(values[0]);
        departureMinute = Integer.parseInt(values[1]);
        line = values[2];
        destination = values[3];
        track = Integer.parseInt(values[4]);
      } catch (NumberFormatException e) {
        // If user input is not parsable to correct data types, an error message is displayed
        printer.println(INVALID_INPUT_MESSAGE);

        // validInput is set to false, so th
        validInput = false;
      }

    } while (!validInput);

    // Create new TrainDeparture object with user input
    TrainDeparture trainDeparture = new TrainDeparture(
        departureHour, departureMinute, line, destination, track, trainNumber
    );

    // Add new TrainDeparture object to collection of departures
    station.addTrainDeparture(trainDeparture);
  }

  /**
   * Checks if the train number is valid.
   * If the train number already exists, the user is asked if they want to override it.
   * If the user does not want to override it,
   * the loop continues until a valid train number is entered.
   *
   * @return A valid train number for new {@code TrainDeparture}s.
   * @since 1.4.0
   */
  private int checkAndGetValidTrainNumber() {
    boolean validInput = false;
    int trainNumber;
    do {
      trainNumber = inputHandler.getValidIntInput("Train number: ", 1, Integer.MAX_VALUE);

      if (station.hasTrainDepartureWithTrainNumber(trainNumber)) {
        // If train number already exists, the user is asked if they want to override it
        printer.println("Train number already exists. Do you want to override it? (Y/n)");
        String answer = inputHandler.getValidStringInput("Enter choice: ");

        // If user wants to override, the loop exits
        if (answer.equalsIgnoreCase("y")) {
          validInput = true;
        } else {
          // If the user does not want to override, an error message is displayed
          // and the loop continues.
          printer.printError("Please try again.");
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
    if (selectedDeparture != null) {
      printer.println("Assign track to train departure");
      printer.println(selectedDeparture.getDetails());

      int track = inputHandler.getValidIntInput("Enter track number: ", 1, 68);

      selectedDeparture.setTrack(track);
      // Updates track of selected departure

    } else {
      // If no departure is selected, the method returns early
      printer.printError(
          "No train departure selected. Please search for a train departure using its train-number."
      );
    }
  }

  /**
   * Assigns a delay to a {@code TrainDeparture}.
   * If no {@code TrainDeparture} is selected, user is prompted an error message.
   *
   * @since 1.0.0
   */
  private void assignDelayToTrainDeparture() {
    if (selectedDeparture != null) {
      printer.println("Assign delay to train departure");
      printer.println(selectedDeparture.getDetails()); // Prints details of selected departure

      int delayHour = inputHandler.getValidIntInput("Enter delay hour: ", 0, 23);
      int delayMinute = inputHandler.getValidIntInput("Enter delay minute: ", 0, 59);

      selectedDeparture.setDelay(new int[]{delayHour, delayMinute});
      // Updates delay of departure
    } else {
      // If no departure is selected, the method returns early
      printer.printError(
          "No train departure selected. Please search for a train departure using its train-number."
      );
    }
  }

  /**
   * Searches for a {@code TrainDeparture} by train number.
   * If no {@code TrainDeparture} is found, an error message is displayed,
   * and selected departure is not changed
   *
   * @since 1.0.0
   */
  private void searchTrainDepartureByNumber() {
    printer.println("Search train departure by number");
    int trainNumber = inputHandler.getValidIntInput("Enter train number: ", 1, Integer.MAX_VALUE);

    if (station.hasTrainDepartureWithTrainNumber(trainNumber)) {
      selectedDeparture = station.getTrainDepartureByTrainNumber(trainNumber);
      printer.println("Train departure found:");
      printer.println(selectedDeparture.getDetails());
    } else {
      printer.printError("No train departure found with train number " + trainNumber + ".");
    }
  }

  /**
   * Searches for a {@code TrainDeparture} by destination.
   * If no {@code TrainDeparture} is found, an error message is displayed,
   *
   * @since 1.0.0
   */
  private void searchTrainDepartureByDestination() {
    printer.println("Search train departure by destination. Not case sensitive.");
    String destination = inputHandler.getValidStringInput("Enter destination: ");

    // Making a temporary stream to check if any train departures are found.
    // Is made into a stream of details of each train departure.
    Stream<String> trainDepartureDetails =
        station.getAllTrainDeparturesByPartialDestination(destination)
        .map(TrainDeparture::getDetailsWithTrainNumber);

    if (trainDepartureDetails.findAny().isEmpty()) {
      //
      printer.printError("No train departure found with destination " + destination + ".");
    } else {
      printer.println("Train departure found:");
      station.getAllTrainDeparturesByPartialDestination(destination)
          .map(TrainDeparture::getDetailsWithTrainNumber)
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
    printer.println("Changing time of station. ");
    int hour = inputHandler.getValidIntInput("Enter hour: ", 0, 23);
    int minute = inputHandler.getValidIntInput("Enter minute: ", 0, 59);
    station.getStationClock().setTime(hour, minute);
  }

  /**
   * Exits the application by breaking the main loop, and printing a status message to the user.
   *
   * @since 1.0.0
   */
  private void exitApplication() {
    printer.println("Exiting application...");
    running = false;
  }

  /**
   * Displays a help message to the user on how the program works.
   * Explains what to do to modify a train departure, and lists what you can do in this program.
   *
   * @since 1.4.0
   */
  private void help() {
    printer.clearScreen();
    printer.println("How this program works.\n");

    StringBuilder message;
    message = new StringBuilder();
    // Building the message in a StringBuilder to avoid long lines and improve readability

    message.append("In this program, you can:\n")
        .append("View train departures\n")
        .append("Add train departure\n")
        .append("Assign track to train departure\n")
        .append("Assign delay to train departure\n")
        .append("Search train departure by number\n")
        .append("Search train departure by destination\n")
        .append("Change time\n")
        .append("Exit\n")
        .append("Help\n\n");

    message.append("To modify a train departure, you must first search for it.\n")
        .append("You can search for a train departure by its unique train-number.\n")
        .append("When you have found the train departure you want to modify, you can modify it.\n")
        .append("The selected train departure will be displayed at the bottom when viewing ")
        .append("train departures.\n")
        .append("You can modify the track and delay of a train departure.\n\n");

    printer.print(String.valueOf(message));
  }
}