package dev.jonas;

import static java.util.Map.Entry.comparingByValue;
import java.util.HashMap;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * The {@code DispatchApp} class represents the main class of the program.
 * The {@code DispatchApp} class handles all user input and output,
 * and runs continuously until the user chooses to exit the program.
 *
 * @author Jonas Birkeli
 * @version 1.2.0
 * @since 1.0.0
 */
public class DispatchApp {


  // Variables used in the program
  private int state;
  private boolean running;
  private TrainDeparture selectedDeparture;
  private int[] currentTime;
  private final Scanner scanner;
  private HashMap<Integer, TrainDeparture> departuresMap;

  // Constants used in the program
  private static final int STATE_VIEW_DEPARTURES = 1;
  private static final int STATE_ADD_DEPARTURE = 2;
  private static final int STATE_ASSIGN_TRACK = 3;
  private static final int STATE_ASSIGN_DELAY = 4;
  private static final int STATE_SEARCH_BY_NUMBER = 5;
  private static final int STATE_SEARCH_BY_DESTINATION = 6;
  private static final int STATE_CHANGE_TIME = 7;
  private static final int STATE_EXIT = 8;

  public static final String INVALID_INPUT_MESSAGE = "Invalid input. Please try again.";

  /**
   * Starts the {@code DispatchApp} and runs it
   * continuously until the user chooses to exit the program.
   * The program starts in the main menu.
   *
   * @param args Command line arguments (Not used)
   * @since 1.0.0
   * @see #start()
   * @see #mainMenu()
   */
  public static void main(String[] args) {
    DispatchApp dispatchApp = new DispatchApp();
    dispatchApp.start();
  }

  /**
   * Constructs a new {@code DispatchApp} with its variables initialized as default.
   * <p>
   *   The {@code DispatchApp} is initialized with the following states:
   *   <ul>
   *     <li>state = 0</li>
   *     <li>running = true</li>
   *     <li>selectedDeparture = null</li>
   *     <li>currentTime = new int[]{0, 0}</li>
   *     <li>scanner = new Scanner(System.in)</li>
   *     <li>departuresMap = new HashMap<>()</li>
   *   </ul>
   * </p>
   *
   * @since 1.2.0
   */
  public DispatchApp() {
    state = 0;
    running = true;
    selectedDeparture = null;
    currentTime = new int[]{0, 0};
    scanner = new Scanner(System.in);
    departuresMap = new HashMap<>();
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
    TrainDeparture dep1 = new TrainDeparture(new int[]{13, 56}, "L1", "Lillehammer", 2);
    TrainDeparture dep2 = new TrainDeparture(new int[]{23, 57}, "F8", "Gjøvik", 2);
    TrainDeparture dep3 = new TrainDeparture(new int[]{3, 58}, "H3", "Hamar", 1);

    departuresMap.put(60, dep1);
    departuresMap.put(22, dep2);
    departuresMap.put(47, dep3);

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
        default -> running = false;
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
    clearScreen();

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
        .append(STATE_EXIT).append(". Exit\n");

    // Continuously ask for user input until a valid choice is made
    while (!validChoice) {
      System.out.println(message);

      choice = getValidIntInput("Enter choice: ");
      // Any expeption in {@link #getValidIntInput(String)} is caught, no need to try catch block.

      if (choice < 1 || choice > 8) {
        System.out.println("Choice must be between 1 and 8. Please try again.");
        continue;
      }
      // If choice is valid, the loop is broken
      validChoice = true;
    }
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
    clearScreen();

    String currentTimeString = String.format("%02d:%02d", currentTime[0], currentTime[1]);
    // Format of which departures are displayed:
    StringBuilder header;
    header = new StringBuilder();
    header
        .append("AVGANGER Departures")
        .append("        ")
        .append("SPOR Track")
        .append(" - ")
        .append(currentTimeString);

    System.out.println(header);

    // Loops through all departures, and prints them if they are valid
    // Departures are sorted by departure time
    // Departures with earlier departure time than current time are not displayed
    departuresMap.entrySet()
        .stream()
        .filter(
            d -> d.getValue().getDepartureTime()[0] > currentTime[0] ||
                (d.getValue().getDepartureTime()[0] == currentTime[0]
                && d.getValue().getDepartureTime()[1] >= currentTime[1])
            // Filter out departures with earlier departure time than current time
        )
        // Sorts departures by departure time,
        .sorted(comparingByValue(TrainDeparture::compareTo))
        .map(d -> d.getValue().getDetails())
        .forEach(System.out::print);


    System.out.println("\n");

    // Back to main menu when user presses enter
    waitForUserInput();
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
    clearScreen();
    System.out.println("Add train departure");

    String[] fields = {
        "Departure hour",
        "Departure minute",
        "Line",
        "Destination",
        "Track",
    };
    String[] values = new String[fields.length];
    boolean validInput = false;
    int trainNumber;

    do {
      trainNumber = getValidIntInput("Train number: ");
      if (departuresMap.containsKey(trainNumber)) {
        System.out.println("Train number already exists. Please try again.");
        continue;
      }
      validInput = true;
    } while (!validInput);

    // Declare variables in correct scope
    int [] departureTime = new int[2];
    String line = "";
    String destination = "";
    int track = -1;

    do {
      // Continuously ask for user input until a valid choice is made
      // Stores user input in values array
      for (int i = 0; i < fields.length; i++) {
        values[i] = getValidStringInput(fields[i] + ": ");
      }

      // Expects correct input from user
      validInput = true;

      try {
        // Try to parse user input to correct data types
        departureTime = new int[]{Integer.parseInt(values[0]), Integer.parseInt(values[1])};
        line = values[2];
        destination = values[3];
        track = Integer.parseInt(values[4]);
      } catch (NumberFormatException e) {
        // If user input is not parsable to correct data types, an error message is displayed
        System.out.println(INVALID_INPUT_MESSAGE);

        // validInput is set to false, so th
        validInput = false;
      }

    } while (!validInput);

    // Create new TrainDeparture object with user input
    TrainDeparture trainDeparture = new TrainDeparture(
        departureTime, line, destination, track
    );

    // Add new TrainDeparture object to collection of departures
    departuresMap.put(trainNumber, trainDeparture);
  }

  /**
   * Assigns a track to a {@code TrainDeparture}.
   * If no {@code TrainDeparture} is selected, the method returns early.
   *
   * @since 1.0.0
   */
  private void assignTrackToTrainDeparture() {
    clearScreen();

    if (selectedDeparture == null) {
      // If no departure is selected, the method returns early
      System.out.println("No train departure selected. Please try again.");
      return;
    }

    System.out.println("Assign track to train departure");
    System.out.println(selectedDeparture.getDetails());

    int track = getValidIntInput("Enter track number: ");

    selectedDeparture.setTrack(track);
    // Updates track of selected departure
  }

  /**
   * Assigns a delay to a {@code TrainDeparture}.
   * If no {@code TrainDeparture} is selected, the method returns early.
   *
   * @since 1.0.0
   */
  private void assignDelayToTrainDeparture() {
    clearScreen();

    if (selectedDeparture == null) {
      // If no departure is selected, the method returns early
      System.out.println("No train departure selected. Please try again.");

      return;
    }

    System.out.println("Assign delay to train departure");
    System.out.println(selectedDeparture.getDetails()); // Prints details of selected departure

    int delayHour = getValidIntInput("Enter delay hour: ");
    int delayMinute = getValidIntInput("Enter delay minute: ");

    selectedDeparture.setDelay(new int[]{delayHour, delayMinute});
    // Updates delay of departure
  }

  /**
   * Searches for a {@code TrainDeparture} by train number.
   * If no {@code TrainDeparture} is found, an error message is displayed,
   * and selected departure is not changed
   *
   * @since 1.0.0
   */
  private void searchTrainDepartureByNumber() {
    clearScreen();
    System.out.println("Search train departure by number");
    int trainNumber = getValidIntInput("Enter train number: ");

    if (departuresMap.containsKey(trainNumber)) {
      selectedDeparture = departuresMap.get(trainNumber);
      System.out.println("Train departure found:");
      System.out.println(selectedDeparture.getDetails());
    } else {
      System.out.println(
          "No train departure found with train number " + trainNumber + ". Please try again."
      );
    }

    waitForUserInput();
  }

  /**
   * Searches for a {@code TrainDeparture} by destination.
   * If no {@code TrainDeparture} is found, an error message is displayed,
   * and selected departure is not changed
   *
   * @since 1.0.0
   */
  private void searchTrainDepartureByDestination() {
    clearScreen();
    System.out.println("Search train departure by destination");
    String destination = getValidStringInput("Enter destination: ");

    departuresMap.entrySet()
        .stream()
        .filter(d -> d.getValue().getDestination().equals(destination))
        .findFirst()
        .ifPresentOrElse(
            d -> {
              selectedDeparture = d.getValue();
              System.out.println("Train departure found:");
              System.out.println(selectedDeparture.getDetails());
            },
            () -> System.out.println(
                "No train departure found with destination " + destination + ". Please try again."
            ));

    waitForUserInput();
  }

  /**
   * Changes the time of the program.
   *
   * @since 1.0.0
   */
  private void changeTime() {
    clearScreen();
    System.out.println("Change time of program.");
    int hour = getValidIntInput("Enter hour: ");
    int minute = getValidIntInput("Enter minute: ");
    currentTime = new int[]{hour, minute};
  }

  /**
   * Exits the application.
   *
   * @since 1.0.0
   */
  private void exitApplication() {
    System.out.println("Exiting application...");
    running = false;
  }

  /**
   * Waits for user input, and halts the program until user enters something.
   *
   * @since 1.1.0
   */
  private void waitForUserInput() {
    System.out.println("Press enter to continue...");
    scanner.nextLine(); // Does not store user input, only waits for input
  }

  /**
   * Returns a valid input from the user.
   * Continuously asks for user input until a valid input is given.
   * If the input is not valid, an error message is displayed.
   * If the input is valid, the input is returned.
   *
   * @param inputMessage Message to display before waiting for user input.
   * @return The user input as a string.
   * @since 1.1.0
   */
  private String getValidStringInput(String inputMessage) {
    // Variables used in the method
    boolean validInput = false;
    String input = "";

    while (!validInput) {
      System.out.println(inputMessage);
      // Tries to get user input, catches exceptions if input is invalid
      try {
        input = scanner.nextLine();
        // If no exception is thrown, the input is valid.
        validInput = true;
      } catch (NumberFormatException | NoSuchElementException | IllegalStateException e) {
        System.out.println("Input must be of non-empty String type. Please try again."); // Error message
      }
    }
    return input;
  }

  /**
   * Returns a valid input from the user.
   * Continuously asks for user input until a valid input is given.
   * If the input is not valid, an error message is displayed.
   * If the input is valid, the input is returned.
   *
   * @param inputMessage Message to display before waiting for user input.
   * @return The user input as an integer.
   * @since 1.1.0
   */
  private int getValidIntInput(String inputMessage) {
    // Variables used in the method
    boolean validInput = false;
    int input = 0;

    while (!validInput) {
      System.out.println(inputMessage);
      // Tries to get user input, catches exceptions if input is invalid
      try {
        input = Integer.parseInt(scanner.nextLine());
        // If no exception is thrown, the input is valid.
        validInput = true;
      } catch (NumberFormatException | NoSuchElementException | IllegalStateException e) {
        System.out.println("Input must be of Integer type. Please try again."); // Error message
      }
    }
    return input;
  }

  /**
   * Clears the screen.
   * <p>
   *   This method uses ANSI escape codes to clear the screen.
   *   This method is not supported on all operating systems.
   *   If the method is not supported, the screen is not cleared.
   *   <br>
   *   <a href="https://stackoverflow.com/a/29752985">Source</a>
   *   <br>
   *   <a href="https://en.wikipedia.org/wiki/ANSI_escape_code">ANSI escape codes</a>
   *   <br>
   *   <a href="https://en.wikipedia.org/wiki/ANSI_escape_code#CSI_sequences">CSI sequences</a>
   *   <br>
   *   <a href="https://en.wikipedia.org/wiki/ANSI_escape_code#Escape_sequences">Escape sequences</a>
   *   <br>
   *
   * </p>
   * @since 1.2.0
   */
  private void clearScreen() {
    System.out.print("\033[H\033[2J");
    System.out.flush();
  }
}