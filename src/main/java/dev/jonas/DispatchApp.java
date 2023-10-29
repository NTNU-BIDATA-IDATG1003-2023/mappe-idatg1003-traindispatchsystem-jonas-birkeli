package dev.jonas;

import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * The {@code DispatchApp} class represents the main class of the program.
 * The {@code DispatchApp} class handles all user input and output,
 * and runs continuously until the user chooses to exit the program.
 *
 * @author Jonas Birkeli
 * @version 1.0.0
 * @since 1.0.0
 */
public class DispatchApp {
  // Variables used in the program
  private int state;
  private boolean running;
  private ArrayList<TrainDeparture> departures;
  private TrainDeparture selectedDeparture;
  private int[] currentTime;
  private Scanner scanner;

  // Constants used in the program
  private static final int STATE_MAIN_MENU = 0;
  private static final int STATE_VIEW_DEPARTURES = 1;
  private static final int STATE_ADD_DEPARTURE = 2;
  private static final int STATE_ASSIGN_TRACK = 3;
  private static final int STATE_ASSIGN_DELAY = 4;
  private static final int STATE_SEARCH_BY_NUMBER = 5;
  private static final int STATE_SEARCH_BY_DESTINATION = 6;
  private static final int STATE_CHANGE_TIME = 7;
  private static final int STATE_EXIT = 8;

  public static void main(String[] args) {
    DispatchApp dispatchApp = new DispatchApp();
    dispatchApp.start();
  }

  /**
   * Starts the {@code DispatchApp} and runs it
   * continuously until the user chooses to exit the program.
   * The program starts in the main menu.
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
    // Initialize variables
    state = STATE_MAIN_MENU;
    departures = new ArrayList<>();
    selectedDeparture = null;
    running = true;
    currentTime = new int[]{0, 0};
    scanner = new Scanner(System.in);

    TrainDeparture dep1 = new TrainDeparture(new int[]{23, 56}, "L1", "Hamburg", 2, 1);
    TrainDeparture dep2 = new TrainDeparture(new int[]{23, 57}, "L2", "Hamburg", 4, 2);
    TrainDeparture dep3 = new TrainDeparture(new int[]{23, 58}, "L3", "Hamburg", 1, 3);

    departures.add(dep1);
    departures.add(dep2);
    departures.add(dep3);

    // Starts program to run continuously until user chooses to exit
    // Uses a switch statement to determine which method to run,
    // based on the current state of the program
    while (running) {
      switch (state) {
        case STATE_MAIN_MENU -> mainMenu();
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
    int choice = 0;
    boolean validChoice = false;

    StringBuilder message;
    message = new StringBuilder();
    message
        .append("Train Dispatch System\n\n")
        .append("1. View train departures\n")
        .append("2. Add train departure\n")
        .append("3. Assign track to train departure\n")
        .append("4. Assign delay to train departure\n")
        .append("5. Search train departure by number\n")
        .append("6. Search train departure by destination\n")
        .append("7. Change time\n")
        .append("8. Exit\n");

    // Continuously ask for user input until a valid choice is made
    while (!validChoice) {
      System.out.println(message);

      try {
        choice = Integer.parseInt(scanner.nextLine());
        if (choice < 1 || choice > 8) {
          System.out.println("Choice must be between 1 and 8. Please try again.");
          continue;
        }
        // If no exception is thrown and choice is between 1 and 8,
        // the choice is valid.
        validChoice = true;
      } catch (NumberFormatException | NoSuchElementException | IllegalStateException e) {
        System.out.println("Invalid choice. Please try again.");
      }
    }
    this.state = choice;  // Change state of program to corresponding user input
  }

  /**
   * Displays all train departures.
   * If some info from one departure is missing, it will not be displayed.
   * If departure time is earlier than current time, it will not be displayed.  TODO: Implement this
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
    StringBuilder header;
    header = new StringBuilder();
    header
        .append("AVGANGER Departures")
        .append(" - ")
        .append("SPOT Track")
        .append("\n");

    System.out.println(header);

    // TODO: Implement streams to filter departures earlier than current time
    for (TrainDeparture departure : departures) {
      System.out.print(departure.getDetails());
    }
    this.state = STATE_MAIN_MENU;
    // TODO: Implement method to exit when user presses enter
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
    String[] fields = {
        "Departure hour",
        "Departure minute",
        "Line",
        "Destination",
        "Track",
        "Train number"
    };
    String[] values = new String[fields.length];
    boolean validInput = false;

    while (!validInput) {
      // Continuously ask for user input until a valid choice is made
      // Stores user input in values array
      for (int i = 0; i < fields.length; i++) {
        System.out.println(fields[i] + ": ");
        values[i] = scanner.nextLine();
      }
      try {
        // Try to parse user input to correct data types
        int[] departureTime = new int[]{Integer.parseInt(values[0]), Integer.parseInt(values[1])};
        String line = values[2];
        String destination = values[3];
        int track = Integer.parseInt(values[4]);
        int trainNumber = Integer.parseInt(values[5]);
        TrainDeparture trainDeparture = new TrainDeparture(
            departureTime, line, destination, track, trainNumber
        );
        departures.add(trainDeparture);

        // If no exception is thrown, the input is valid.
        validInput = true;
      } catch (NumberFormatException | NoSuchElementException | IllegalStateException e) {
        System.out.println("Invalid input. Please try again.");
      }
    }
    // Change state of program to main menu
    this.state = STATE_MAIN_MENU;
  }

  /**
   * Assigns a track to a {@code TrainDeparture}.
   * If no {@code TrainDeparture} is selected, the method returns early.
   *
   * @since 1.0.0
   */
  private void assignTrackToTrainDeparture() {
    if (selectedDeparture == null) {
      System.out.println("No train departure selected. Please try again.");
      this.state = STATE_MAIN_MENU;
      return;
    }

    System.out.println("Assign track to train departure");
    System.out.println(selectedDeparture.getDetails());
    System.out.println("Enter track number: ");
    int track = Integer.parseInt(scanner.nextLine());
    selectedDeparture.setTrack(track);

    this.state = STATE_MAIN_MENU;
  }

  /**
   * Assigns a delay to a {@code TrainDeparture}.
   * If no {@code TrainDeparture} is selected, the method returns early.
   *
   * @since 1.0.0
   */
  private void assignDelayToTrainDeparture() {
    if (this.selectedDeparture == null) {
      System.out.println("No train departure selected. Please try again.");
      this.state = STATE_MAIN_MENU;
      return;
    }

    System.out.println("Assign delay to train departure");
    System.out.println(this.selectedDeparture.getDetails());
    System.out.println("Enter delay in the format {HH, mm}: ");
    String[] delay = scanner.nextLine().split(", ");
    this.selectedDeparture.setDelay(
        new int[]{Integer.parseInt(delay[0]), Integer.parseInt(delay[1])}
    );

    this.state = STATE_MAIN_MENU;
  }

  /**
   * Searches for a {@code TrainDeparture} by train number.
   * If no {@code TrainDeparture} is found, the method returns early.
   *
   * @since 1.0.0
   */
  private void searchTrainDepartureByNumber() {
    System.out.println("Search train departure by number");
    System.out.println("Enter train number: ");
    int trainNumber = Integer.parseInt(scanner.nextLine());
    for (TrainDeparture departure : this.departures) {  // TODO: Implement streams
      if (departure.getTrainNumber() == trainNumber) {
        this.selectedDeparture = departure;
        System.out.println("Train departure found:");
        System.out.println(selectedDeparture.getDetails());
        this.state = STATE_MAIN_MENU;
        return;
      }
    }
    System.out.println(
        "No train departure found with train number " + trainNumber + ". Please try again."
    );
    this.state = STATE_MAIN_MENU;
  }

  /**
   * Searches for a {@code TrainDeparture} by destination.
   * If no {@code TrainDeparture} is found, an error message is displayed,
   * and selected departure is not changed
   *
   * @since 1.0.0
   */
  private void searchTrainDepartureByDestination() {
    System.out.println("Search train departure by destination");
    System.out.println("Enter destination: ");
    String destination = scanner.nextLine();
    for (TrainDeparture departure : this.departures) {  // TODO: Implement streams
      if (departure.getDestination().equals(destination)) {
        this.selectedDeparture = departure;  // Update selected departure
        System.out.println("Train departure found:");
        System.out.println(selectedDeparture.getDetails());
        this.state = STATE_MAIN_MENU;
        return;
      }
    }
    System.out.println(
        "No train departure found with destination " + destination + ". Please try again."
    );
    this.state = STATE_MAIN_MENU;
  }

  /**
   * Changes the time of the program.
   *
   * @since 1.0.0
   */
  private void changeTime() {
    System.out.println("Change time");
    System.out.println("Enter new time in the format {HH, mm}: ");
    String[] time = scanner.nextLine().split(", ");
    this.currentTime = new int[]{Integer.parseInt(time[0]), Integer.parseInt(time[1])};
    this.state = STATE_MAIN_MENU;
  }

  /**
   * Exits the application.
   *
   * @since 1.0.0
   */
  private void exitApplication() {
    System.out.println("Exiting application...");
    this.state = STATE_MAIN_MENU;
    this.running = false;
  }
}