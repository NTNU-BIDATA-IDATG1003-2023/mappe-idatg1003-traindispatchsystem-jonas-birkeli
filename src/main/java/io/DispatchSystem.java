package io;

import static config.Colors.GREEN;
import static config.Colors.GREEN_BRIGHT;
import static config.Colors.RED;
import static config.Colors.RED_BRIGHT;
import static config.Colors.RED_UNDERLINED;
import static config.Colors.RESET;
import static config.Colors.STRIKETHROUGH;
import static config.Colors.WHITE_BOLD_BRIGHT;
import static config.Colors.WHITE_BRIGHT;
import static config.ConfigurationOptions.MAX_DESTINATION_LENGTH;
import static config.ConfigurationOptions.MAX_LINE_LENGTH;
import static config.ConfigurationOptions.MAX_TRACK_LENGTH;
import static config.ConfigurationOptions.STATE_ADD_DEPARTURE;
import static config.ConfigurationOptions.STATE_ASSIGN_DELAY;
import static config.ConfigurationOptions.STATE_ASSIGN_TRACK;
import static config.ConfigurationOptions.STATE_CHANGE_TIME;
import static config.ConfigurationOptions.STATE_EXIT;
import static config.ConfigurationOptions.STATE_HELP;
import static config.ConfigurationOptions.STATE_REMOVE_DEPARTURE;
import static config.ConfigurationOptions.STATE_SEARCH_BY_DESTINATION;
import static config.ConfigurationOptions.STATE_SELECT_TRAIN_BY_NUMBER;
import static config.ConfigurationOptions.STATE_VIEW_DEPARTURES;
import static config.ConfigurationOptions.STATION_DEPARTURE_SCREEN_TITLE;

import core.Station;
import core.TrainDeparture;
import java.util.stream.Stream;
import utility.InputHandler;
import utility.Printer;

/**
 * The {@code app.DispatchApp} class represents the main class of the program.
 * The {@code app.DispatchApp} class handles all user input and output,
 * and connects the {@code Station} to the user with a text-based interface.
 * <p>
 *   The {@code app.DispatchApp} class has the following methods:
 *   <ul>
 *     <li>{@link #start()}</li>
 *     <li>{@link #mainMenu()}</li>
 *     <li>{@link #viewTrainDepartures()}</li>
 *     <li>{@link #addTrainDeparture()}</li>
 *     <li>{@link #assignTrackToTrainDeparture()}</li>
 *     <li>{@link #assignDelayToTrainDeparture()}</li>
 *     <li>{@link #selectTrainDepartureByTrainNumber()}</li>
 *     <li>{@link #searchTrainDepartureByDestination()}</li>
 *     <li>{@link #changeTime()}</li>
 *     <li>{@link #exitApplication()}</li>
 *   </ul>
 *
 * @author Jonas Birkeli
 * @version 1.7.0
 * @since 1.0.0
 */
public class DispatchSystem {
  // Fields in this class
  private int state;
  private boolean running;
  private final Station station;
  private final InputHandler inputHandler;
  private final Printer printer;

  /**
   * Constructs a new {@code app.DispatchApp}.The program does not start until the {@link #start()}
   * method is called.
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
   * Starts the {@code app.DispatchApp} and runs it
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
   *
   * @since 1.0.0
   */
  public void start() {
    printer.println("Starting " + app.DispatchApp.APP_NAME + " " + app.DispatchApp.APP_VERSION);
    // Clears the screen before starting the program.
    // This method does not work in all terminals or operating systems.
    printer.clearScreen();

    // Starts program to run continuously until user chooses to exit
    // Uses a switch statement to determine which method to run,
    // based on the current state of the program
    while (running) {

      mainMenu();
      switch (state) {
        case STATE_VIEW_DEPARTURES -> viewTrainDepartures();
        case STATE_ADD_DEPARTURE -> addTrainDeparture();
        case STATE_REMOVE_DEPARTURE -> removeTrainDeparture();
        case STATE_ASSIGN_TRACK -> assignTrackToTrainDeparture();
        case STATE_ASSIGN_DELAY -> assignDelayToTrainDeparture();
        case STATE_SELECT_TRAIN_BY_NUMBER -> selectTrainDepartureByTrainNumber();
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
    StringBuilder selectedTrainDepartureDetails;
    selectedTrainDepartureDetails = new StringBuilder();

    selectedTrainDepartureDetails
        .append(WHITE_BOLD_BRIGHT + "Selected train departure:\n" + RESET);

    if (station.getSelectedTrainDeparture() != null) {
      selectedTrainDepartureDetails
          .append(GREEN)
          .append(buildTrainDepartureDetails(station.getSelectedTrainDeparture()))
          .append(RESET);
    } else {
      selectedTrainDepartureDetails
          .append(RED + "No train departure selected.\n" + RESET);
    }

    // Second message to be printed.
    // Includes what options the user has.
    StringBuilder message;
    message = new StringBuilder();

    // Using constants to display menu options instead of hard-coded numbers in case of change
    message
        .append(WHITE_BOLD_BRIGHT + "Train Dispatch System\n\n" + RESET)
        .append(STATE_VIEW_DEPARTURES)
        .append(". View train departures\n")
        .append(STATE_ADD_DEPARTURE)
        .append(". Add train departure\n")
        .append(STATE_REMOVE_DEPARTURE)
        .append(". Remove train departure\n")
        .append(STATE_ASSIGN_TRACK)
        .append(". Assign track to selected train departure\n")
        .append(STATE_ASSIGN_DELAY)
        .append(". Assign delay to selected train departure\n")
        .append(STATE_SELECT_TRAIN_BY_NUMBER)
        .append(". Select train departure by number\n")
        .append(STATE_SEARCH_BY_DESTINATION)
        .append(". Search train departure by destination\n")
        .append(STATE_CHANGE_TIME)
        .append(". Change time\n")
        .append(STATE_EXIT)
        .append(". Exit\n")
        .append(STATE_HELP)
        .append(". Help\n");

    // Continuously ask for user input until a valid choice is made
    printer.println(String.valueOf(selectedTrainDepartureDetails));
    printer.println(String.valueOf(message));

    // User input, must be between 1 and 9, incorrect input is not accepted
    state = inputHandler.getValidIntInput("Enter choice: ", 1, 10);
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
    // Appends the clock to the end of the title as it uses print and not println
    printer.println("\n" + station.getStationClock().getTimeAsString());
    // Format of which departures are displayed:
    printer.println(STATION_DEPARTURE_SCREEN_TITLE);

    // Loops through all departures, and prints them if they are valid
    // Departures are sorted by departure time
    // Departures with earlier departure time than current time are not displayed
    printer.print(WHITE_BRIGHT);  // ANSI escape code for bold white text
    station.getStreamOfTimeFilteredTrainDepartures()
        .map(this::buildTrainDepartureDetails) // Gets details of each departure
        .forEach(printer::print);

    printer.println(RESET + "\n");
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
    printer.println("Add train departure");

    // Getting a unique train number from user
    // Train number is unique, so we need to check if it already exists with a robust method
    int trainNumber = checkAndGetValidTrainNumber();

    // In case the user does not know what train-number is valid,
    // the user can enter -1 to exit the method.
    TrainDeparture trainDeparture = null;

    if (trainNumber != -1) {
      // Getting input from user for every field of the train departure
      printer.print(WHITE_BRIGHT);
      int departureHour = inputHandler.getValidIntInput("Departure hour: ", 0, 23);
      int departureMinute = inputHandler.getValidIntInput("Departure minute: ", 0, 59);
      String line = inputHandler.getValidStringInput("Line: ", MAX_LINE_LENGTH);
      String destination = inputHandler.getValidStringInput("Destination: ", MAX_DESTINATION_LENGTH);
      int track = inputHandler.getValidIntInput("Track: \n(-1 for unset)", -1, 68);


      trainDeparture = new TrainDeparture(
          departureHour, departureMinute, line, destination, track, trainNumber
      );

      // Inserting the train departure into the station
      station.addTrainDeparture(trainDeparture);

      printer.println(GREEN_BRIGHT
          + "Train departure for "
          + WHITE_BOLD_BRIGHT
          + destination
          + RESET
          + GREEN_BRIGHT
          + " with train number "
          + WHITE_BOLD_BRIGHT
          + trainNumber
          + GREEN_BRIGHT
          + " added."
          + RESET);
    }
    printer.print(RESET);
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
      printer.println(WHITE_BRIGHT + "Remove train departure:" + RESET);
      printer.println(buildTrainDepartureDetails(station.getSelectedTrainDeparture()));

      String answer = inputHandler.getValidStringInput(
          "Do you want to remove this train departure? (Y/n)",
          -1
      );

      if (answer.equalsIgnoreCase("y")) {
        station.removeTrainDeparture();
        printer.println(GREEN_BRIGHT + "Train departure successfully removed." + RESET);
      } else {
        printer.println("Not removing train departure.");
      }
    } else {
      printer.printError("No train departure selected."
          + "Please search for a train departure using its train-number.");
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
      trainNumber = inputHandler.getValidIntInput("Train number: ", 1, Integer.MAX_VALUE);

      if (station.hasTrainDepartureWithTrainNumber(trainNumber)) {
        // If train number already exists, the user is asked if they want to override it
        printer.println(RED + "Train number already exists. Do you want to override it? (Y/n)");
        printer.println("(-1 to exit)" + RESET);  // Red text to make it more visible
        String answer = inputHandler.getValidStringInput("Enter choice: ", -1);

        // If user wants to override, the loop exits
        if (answer.equalsIgnoreCase("y")) {
          validInput = true;
        } else if (answer.equals("-1")) {
          trainNumber = -1;
          printer.println("Exiting procedure to add train departure.");

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
    if (station.getSelectedTrainDeparture() != null) {
      printer.println(WHITE_BRIGHT + "Assign track to train departure:" + RESET);
      printer.println(buildTrainDepartureDetails(station.getSelectedTrainDeparture()));

      int track = inputHandler.getValidIntInput("Enter track number: ", 1, 68);

      station.getSelectedTrainDeparture().setTrack(track);
      // Updates track of selected departure

      printer.println(GREEN_BRIGHT + "Track successfully assigned to train departure." + RESET);

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
    if (station.getSelectedTrainDeparture() != null) {
      printer.println(WHITE_BRIGHT + "Assign delay to train departure" + RESET);
      printer.println(buildTrainDepartureDetails(station.getSelectedTrainDeparture()));
      // Prints details of selected departure

      int delayHour = inputHandler.getValidIntInput("Enter delay hour: ", 0, 23);
      int delayMinute = inputHandler.getValidIntInput("Enter delay minute: ", 0, 59);

      station.getSelectedTrainDeparture().setDelay(delayHour, delayMinute);
      // Updates delay of departure
    } else {
      // If no departure is selected, an error message is displayed
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
  private void selectTrainDepartureByTrainNumber() {
    printer.println(WHITE_BRIGHT + "Select train departure by number" + RESET);
    int trainNumber = inputHandler.getValidIntInput("Enter train number: ", 1, Integer.MAX_VALUE);

    if (station.selectTrainDeparture(trainNumber) == 0) {
      // Method above returns 0 if train departure is found and successfully selected
      printer.println(GREEN_BRIGHT + "Train departure found:" + RESET);
      printer.println(buildTrainDepartureDetails(station.getSelectedTrainDeparture()));
    } else {
      printer.printError("No train departure found with train number " + trainNumber + ".");
      printer.println("To view all train-numbers, select 'View train departures'");
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
    String destination = inputHandler.getValidStringInput(
        "Enter destination: ",
        MAX_DESTINATION_LENGTH
    );

    // Making a temporary stream to check if any train departures are found.
    // Is made into a stream of details of each train departure.
    Stream<String> trainDepartureDetails =
        station.getAllTrainDeparturesByPartialDestination(destination)
        .map(this::buildTrainDepartureDetails);

    if (trainDepartureDetails.findAny().isEmpty()) {
      //
      printer.printError("No train departure found with destination " + destination + ".");
    } else {
      printer.println(GREEN_BRIGHT + "Train departure found:" + RESET);
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
    printer.println(WHITE_BRIGHT + "Changing time of station. " + RESET);
    int hour = inputHandler.getValidIntInput("Enter hour: ", 0, 23);
    int minute = inputHandler.getValidIntInput("Enter minute: ", 0, 59);
    boolean timeIsValid = station.setStationTime(hour, minute);

    if (timeIsValid) {
      printer.println(
          GREEN_BRIGHT + "Time changed to " + RESET + station.getStationClock().getTimeAsString()
      );
    } else {
      printer.printError("Time must be later than current time.");
    }
  }

  /**
   * Exits the application by breaking the main loop, and printing a status message to the user.
   *
   * @since 1.0.0
   */
  private void exitApplication() {
    printer.println(RED_BRIGHT + "Exiting application..." + RESET);
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

    StringBuilder message;
    message = new StringBuilder();
    // Building the message in a StringBuilder to avoid long lines and improve readability

    message.append(WHITE_BOLD_BRIGHT + "In this program, you can:\n" + RESET)
        .append("View train departures from a station\n")
        .append("Add a departure of your liking to the station\n")
        .append("Search for train departures by destination (Full or partial)\n")
        .append("Select a departure by its train number\n")
        .append("Assign a track to a selectd departure\n")
        .append("Assign a delay to a selected departure\n")
        .append("Change the time of the station\n")
        .append("\n\n");

    message.append(WHITE_BOLD_BRIGHT + "How to modify a train departure:\n" + RESET);
    message.append("To modify a train departure, you "
            + RED_UNDERLINED
            + "must"
            + RESET
            + " first search for it.\n")
        .append("You can search for a train departure by its unique train-number.\n")
        .append("When you have found the train departure you want to modify, you can modify it.\n")
        .append("The selected train departure will be displayed at the bottom when viewing ")
        .append("train departures.\n")
        .append("You can modify the track and delay of a train departure.\n\n");

    printer.print(String.valueOf(message));
  }

  /**
   * Creates a String representation of every valuable field for an end-user to read.
   * If {@code TrainDeparture.line} or {@code TrainDeparture.destination} is not set,
   * the method returns an empty string.
   * If {@code TrainDeparture.track} is not set,
   * the method returns "TBA" instead of the track number.
   * If {@code TrainDeparture.delay} is not set,
   * the method returns the departure time without delay.
   * If {@code TrainDeparture.delay} is set, the method returns the departure time with delay.
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
      objectInformation.append(RED_BRIGHT + STRIKETHROUGH)
            .append(trainDeparture.getDepartureTime().getTimeAsString())
            .append(RESET + WHITE_BRIGHT)
            .append(" ")
            .append(trainDeparture
                .getDepartureTime()
                .combine(trainDeparture.getDelay())
                .getTimeAsString()
          // Combining the departure-time with delay to get the actual departure time
          );
    }

    objectInformation.append(" ")
       .append(trainDeparture.getLine())
        .append(" ".repeat(
            MAX_LINE_LENGTH - trainDeparture.getLine().length())
        )
        .append(trainDeparture.getDestination())
        .append(" ".repeat(
            MAX_DESTINATION_LENGTH - trainDeparture.getDestination().length())
        );

    if (trainDeparture.getTrack() == -1) {
      // If the track is not set, we display "TBA" instead of the track number
      objectInformation.append("TBA");
    } else {
      objectInformation.append(trainDeparture.getTrack())
          .append(" ".repeat(
              MAX_TRACK_LENGTH - String.valueOf(trainDeparture.getTrack()).length())
          );
    }

    objectInformation.append("          ")  // Empty string for spacing the trainnumber away form the details
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