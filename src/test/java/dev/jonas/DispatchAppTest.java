package dev.jonas;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class DispatchAppTest {
  DispatchApp dispatchApp;

  @BeforeEach
  void setUp() {
    dispatchApp = new DispatchApp();
  }

  @AfterEach
  void tearDown() {
    dispatchApp = null;
  }

  @Test
  void testMain() {
    // Cannot test this method because it contains a while loop.
  }

  @Test
  void testStart() {
    // Cannot test this method because it contains a while loop.
  }
}