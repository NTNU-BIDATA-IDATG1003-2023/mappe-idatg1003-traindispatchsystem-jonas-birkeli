[![Review Assignment Due Date](https://classroom.github.com/assets/deadline-readme-button-24ddc0f5d75046c5622901739e7c5dd533143b0c8e959d652212380cedb1ea36.svg)](https://classroom.github.com/a/sT7H9ZJB)
# Portfolio project IDATA1003 - 2023
This file uses Mark Down syntax. For more information see [here](https://www.markdownguide.org/basic-syntax/).

STUDENT NAME = "Jonas Birkeli"  
STUDENT ID = "107006"

## Project description

Train dispatch system, where the user can view traindepartures, add traindepartures, remove traindepartures, search for and select traindepartures, and perform certain actions on the selected traindeparture.
You can also update the time of the station, and quit the application. 
There is a help option that shows the user the different options and how to use them.
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
3. Remove selected train-departure
4. Assign track to selevted train-departure
5. Assign delay to selected train-departure's departure-time
6. Seelct a train-departure by its unique train-number
7. Search train-departure by destination
8. Update time of station
9. Quit application
10. Help

The user can choose an option by typing the corresponding number, and the program will then execute the corresponding method.
The user can quit the application by typing 9.

## How to run the tests

The tests can be run by running the core.StationTest.java, core.TrainDepartureTest.java, utility.ClockTest.java and utility.InputValidatorTest.java files.
The tests can be run by right-clicking the file and choosing "Run 'DispatchAppTest'".
The tests can also be run by clicking the green play button in the top right corner of the file.

## References
https://www.w3schools.blog/ansi-colors-java
