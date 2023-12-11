package app;

import io.DispatchSystem;

/**
 * Starts the application.
 */
public class DispatchApp {
  public static final String APP_NAME = "app.DispatchApp";
  public static final String APP_VERSION = "1.7.0";
  public static final String APP_AUTHOR = "Jonas Birkeli";
  public static final String APP_DESCRIPTION = "A dispatch system for trains.";
  public static final String APP_RELEASE_DATE = "29-11-2023";

  public static void main(String[] args) {
    DispatchSystem dispatchSystem = new DispatchSystem();
    dispatchSystem.start();
  }
}