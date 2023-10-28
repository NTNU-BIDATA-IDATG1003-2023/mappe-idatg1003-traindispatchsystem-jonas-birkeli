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

  private void addTrainDeparture() {
    // TODO: Implement method
  }

  private void assignTrackToTrainDeparture() {
    // TODO: Implement method
  }

  private void assignDelayToTrainDeparture() {
    // TODO: Implement method
  }

  private void searchTrainDepartureByNumber() {
    // TODO: Implement method
  }

  private void searchTrainDepartureByDestination() {
    // TODO: Implement method
  }

  private void changeTime() {
    // TODO: Implement method
  }

  private void exitApplication() {
    // TODO: Implement method
  }
}