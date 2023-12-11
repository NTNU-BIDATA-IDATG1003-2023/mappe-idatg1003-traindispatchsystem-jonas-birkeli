package lang;

import static config.Colors.GREEN_BRIGHT;
import static config.Colors.RED;
import static config.Colors.RED_UNDERLINED;
import static config.Colors.RESET;
import static config.Colors.WHITE;
import static config.Colors.WHITE_BOLD;
import static config.Colors.WHITE_BOLD_BRIGHT;
import static config.Colors.WHITE_BRIGHT;
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

/**
 * A class for storing feedback messages.
 * This class makes it easy to adjust values and/or add new features to the application.
 * Also serves as a language file.
 *
 * @version 1.0.0
 * @since 1.0.0
 */
public class UserTextFeedback {
  public static final String ERROR_TRY_AGAIN = "Please try again.";
  public static final String INPUT_INVALID = "Invalid input." + ERROR_TRY_AGAIN;
  public static final String INVALID_INPUT_LENGTH =
      "Invalid input. Please enter a string with a maximum length of %d.";
  public static final String INPUT_INVALID_NUMBER_RANGE =
      "Please enter a number between %d and %d.";
  public static final String TBA = "TBA";

  public static final String ERROR_SEARCH_FOR_TRAIN_FIRST =
      "Please search for a train departure using its train-number.";
  public static final String PROMPT_ENTER_HOUR = "Enter hour: ";
  public static final String PROMPT_ENTER_MINUTE = "Enter minute: ";
  public static final String ERROR_NO_TRAIN_SELECTED = "No train departure selected."
      + ERROR_SEARCH_FOR_TRAIN_FIRST;
  public static final String PROMPT_ENTER_TRAIN_NUMBER = "Enter train number: ";
  public static final String PROMPT_ENTER_TRACK = "Enter track: ";
  public static final String bruh = "bruh";

  // *********************
  // REMOVE TRAIN DEPARTURE
  // **********************
  public static final String PROMPT_TRAIN_FOR_REMOVAL = WHITE_BRIGHT
      + "Remove train departure: " + RESET;
  public static final String PROMPT_REMOVE_EXISTING_TRAIN =
      "Do you want to remove this train departure? (Y/n)";
  public static final String STATUS_REMOVING_TRAIN = GREEN_BRIGHT
      + "Train departure successfully removed." + RESET;
  public static final String STATUS_NOT_REMOVING_TRAIN = "Not removing train departure.";

  // *********************
  // MAIN MENU
  // **********************
  public static final String PROMPT_SELECTED_TRAIN_HEADER = WHITE_BOLD_BRIGHT
      + "Selected train departure:\n" + RESET;

  private static final StringBuilder mainMenuMessage = new StringBuilder()
      .append(WHITE_BOLD_BRIGHT + "Train Dispatch System\n\n" + RESET)
      .append(STATE_VIEW_DEPARTURES)
      .append(". View train departures\n")
      .append(STATE_ADD_DEPARTURE)
      .append(". Add train departure\n")
      .append(STATE_REMOVE_DEPARTURE)
      .append(". Remove" + WHITE_BOLD + " selected" + RESET + " train departure\n")
      .append(STATE_ASSIGN_TRACK)
      .append(". Assign track to" + WHITE_BOLD + " selected" + RESET + " train departure\n")
      .append(STATE_ASSIGN_DELAY)
      .append(". Assign delay to" + WHITE_BOLD + " selected" + RESET + " train departure\n")
      .append(STATE_SELECT_TRAIN_BY_NUMBER)
      .append("." + WHITE + " Select" + RESET + " train departure by number\n")
      .append(STATE_SEARCH_BY_DESTINATION)
      .append(". Search train departure by destination\n")
      .append(STATE_CHANGE_TIME)
      .append(". Change time\n")
      .append(STATE_EXIT)
      .append(". Exit\n")
      .append(STATE_HELP)
      .append(". Help\n");
  public static final String MAIN_MENU_OPTIONS = String.valueOf(mainMenuMessage);
  // Making it accessible outside the class


  // *********************
  // ADD TRAIN DEPARTURE
  // **********************
  public static final String PROMPT_ADD_TRAIN = WHITE_BRIGHT + "Add train departure." + RESET;
  public static final String PROMPT_ENTER_DEPARTURE_TIME = "Enter departure hour: ";
  public static final String PROMPT_ENTER_DEPARTURE_MINUTE = "Enter departure minute: ";
  public static final String PROMPT_ENTER_LINE = "Enter line: ";
  public static final String PROMPT_ENTER_TRACK_HELP = PROMPT_ENTER_TRACK + "\n-1 for unset";

  public static final String PROMPT_TRAIN_ADDED_SUCCESSFULLY_1 = GREEN_BRIGHT
      + "Train departure for ";
  public static final String PROMPT_TRAIN_ADDED_SUCCESSFULLY_2 =  GREEN_BRIGHT
      + " with train number " + RESET;
  public static final String PROMPT_TRAIN_ADDED_SUCCESSFULLY_3 = GREEN_BRIGHT
      + " successfully added." + RESET;

  // *********************
  // GET TRAIN NUMBER
  // **********************
  public static final String PROMPT_NUMBER_OVERRIDE_CHOICE = RED
      + "Train number already exists. Do you want to override it? (Y/n)"
      + "\n(-1 to cancel): " + RESET;
  // IMPORTANT; -1 is used to cancel the procedure, hard-coded in InputHandler
  public static final String PROMPT_CANCEL_ADD_TRAIN = "Exiting procedure to add train departure.";

  // *********************
  // ASSIGN TRACK
  // **********************
  public static final String PROMPT_ASSIGN_TRACK = WHITE_BRIGHT
      + "Assign track to train departure." + RESET;
  public static final String PROMPT_TRACK_ASSIGNED_SUCCESSFULLY = GREEN_BRIGHT
      + "Track successfully assigned to train departure." + RESET;


  // *********************
  // ASSIGN DELAY
  // **********************
  public static final String PROMPT_ASSIGN_DELAY = WHITE_BRIGHT
      + "Assign delay to train departure." + RESET;

  // *********************
  // SELECT TRAIN DEPARTURE
  // **********************
  public static final String PROMPT_SELECT_TRAIN = WHITE_BRIGHT
      + "Select train departure by number" + RESET;
  public static final String PROMPT_TRAIN_SELECTED_SUCCESSFULLY = "Train successfully selected.";
  public static final String ERROR_NO_TRAIN_WITH_NUMBER = "No train departure with number %d found."
      + ERROR_SEARCH_FOR_TRAIN_FIRST;


  // *********************
  // SEARCH TRAIN DEPARTURE
  // **********************
  public static final String PROMPT_SEARCH_TRAIN =
      "Search train departure by destination. Not case sensitive.";
  public static final String PROMPT_ENTER_DESTINATION = "Enter destination: ";
  public static final String PROMPT_TRAIN_FOUND = GREEN_BRIGHT + "Train departure found:" + RESET;
  public static final String ERROR_NO_TRAIN_FOUND =
      "No train departure found with destination %s .";

  // *********************
  // CHANGE TIME
  // **********************
  public static final String PROMPT_CHANGE_TIME = WHITE_BRIGHT
      + "Changing time of station. " + RESET;
  public static final String ERROR_TIME_NOT_LATER = "Time must be later than current time.";
  public static final String TIME_CHANGED_SUCCESFULLY = "Time changed successfully to %s";

  // *********************
  // HELP SCREEN
  // **********************
  private static final StringBuilder helpMessage = new StringBuilder()
      // Building the message in a StringBuilder to avoid long lines and improve readability
      .append(WHITE_BOLD_BRIGHT + "In this program, you can:\n" + RESET)
      .append("View train departures from a station\n")
      .append("Add a departure of your liking to the station\n")
      .append("Search for train departures by destination (Full or partial)\n")
      .append("Select a departure by its train number\n")
      .append("Assign a track to a selectd departure\n")
      .append("Assign a delay to a selected departure\n")
      .append("Change the time of the station\n")
      .append("\n\n")
      .append(WHITE_BOLD_BRIGHT)
      .append("How to modify a train departure:\n")
      .append(RESET)
      .append("To modify a train departure, you ")
      .append(RED_UNDERLINED)
      .append("must")
      .append(RESET)
      .append(" first select it.\n")
      .append("You can search for a train departure by its unique train-number.\n")
      .append("When you have found the train departure you want to modify, you can modify it.\n")
      .append("The selected train departure will be displayed at the bottom when viewing ")
      .append("train departures.\n")
      .append("You can modify the track and delay of a train departure.\n\n");
  public static final String HELP_MESSAGE = String.valueOf(helpMessage);
  // Making it accessible outside the class

  // What the user is prompted to enter when prompted with multiple choices
  public static final String INPUT_CHOICE = "Enter choice: ";

  // *********************
  // EXIT APPLICATION
  // **********************
  public static final String PROMPT_EXIT = WHITE_BRIGHT + "Exiting application." + RESET;
}
