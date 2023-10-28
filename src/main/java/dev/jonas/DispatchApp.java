package dev.jonas;

import java.util.ArrayList;

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

  private void mainMenu() {
    // TODO Implement method
  }

  private void viewTrainDepartures() {
    // TODO: Implement method
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