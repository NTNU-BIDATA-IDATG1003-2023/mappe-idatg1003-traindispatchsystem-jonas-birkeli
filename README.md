[![Review Assignment Due Date](https://classroom.github.com/assets/deadline-readme-button-24ddc0f5d75046c5622901739e7c5dd533143b0c8e959d652212380cedb1ea36.svg)](https://classroom.github.com/a/sT7H9ZJB)
# Portfolio project IDATA1003 - 2023
This file uses Mark Down syntax. For more information see [here](https://www.markdownguide.org/basic-syntax/).

STUDENT NAME = "Jonas Birkeli"  
STUDENT ID = "107006"

## Project description

Train dispatch system, where the user can view traindepartures, add traindepartures, search for and select traindepartures, and perform certain actions on the selected traindeparture.

## Project structure

Maven project with the following structure:
```
src
├── main
│   └── java
│       ├── app
│       │   └── DispatchApp.java
│       ├── config
│       │   ├── Colors.java
│       │   └── ConfigurationOptions.java
│       ├── core
│       │   ├── Station.java
│       │   └── TrainDeparture.java
│       ├── io
│       │   └── DispatchSystem.java
│       └── utility
│           ├── Clock.java
│           ├── InputHandler.java
│           ├── InputValidator.java
│           └── Printer.java 
└── test
    └── java
        ├── core
        │   ├── StationTest.java
        │   └── TrainDepartureTest.java
        └── utility
            ├── ClockTest.java
            └── InputValidatorTest.java

```

## Link to repository

https://github.com/NTNU-BIDATA-IDATG1003-2023/mappe-idatg1003-traindispatchsystem-jonas-birkeli

## How to run the project

The main class is DispatchApp.java, and the main method is the start() method.
The dispatchApp is a representation of a single station, and the time is limited to 24 hours.
The program will show a list of options, and the user can choose an option by typing the corresponding number.
The options are as following:
1. View train-departures
2. Add train-departure
3. Assign track to train-departure
4. Assign delay to train-departure's departure-time
5. Search train-departure by unique train-number
6. Search train-departure by destination
7. Update time of station
8. Quit application
9. Help

The user can choose an option by typing the corresponding number, and the program will then execute the corresponding method.
The user can quit the application by typing 8.

## How to run the tests

The tests can be run by running the DispatchAppTest.java, core.StationTest.java, core.TrainDepartureTest.java, utility.ClockTest.java, utility.InputHandlerTest.java, utility.InputValidatorTest.java, and utility.PrinterTest.java files.
The tests can be run by right-clicking the file and choosing "Run 'DispatchAppTest'".
The tests can also be run by clicking the green play button in the top right corner of the file.

## References
https://www.w3schools.blog/ansi-colors-java
