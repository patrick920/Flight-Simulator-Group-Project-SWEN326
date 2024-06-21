# SWEN326 (Safety-Critical Systems) Project
## Run Instructions
Instructions on how to run the code in IntelliJ, Eclipse and Visual Studio Code using Maven are provided in `Documentation/Maven Run Instructions (SWEN326 Project).pdf`.
## Overview
This project aimed to create a plane simulator to simulate a safety-critical system. It follows some (but not all) characterisitics of the DO-178C standard, which is used in the real world in avionics. We have a user interface with several controls and simulation code for the plane, but unfortunately we did not have time to combine them so the project is incomplete. Prior to working on the code, we made a group agreement, a requirements plan, high-level and low-level requirements, and a design document.
## Architecture
This project was made in Java. We decided to use JavaFX to make the user interface, as it contains many features and is better than Swing (an older library for making user interfaces in Java.) We used the Maven build system as this allows us to run the code on several different IDEs. If we didn't use Maven, then some IDEs may have trouble running the code as they expect a specific project structure. The code contains several modules:
- Application: This is where the program starts.
- Autopilot: This has not been implemented.
- FDI (Fault Detection and Isolation): This monitors sensor values.
- Simulator: This contains the code for the plane, engine and other aspects of the simulation. It also contains the timer.
- User Interface: This contains the code for the user interface such as buttons and sliders. It was implemented in JavaFX.
<p> We have also programmed JUnit tests (automated tests) to test some aspects of the code.
## Improvements
Unfortunately we did not have time to complete the project. We made a functional user interface with buttons and sliders. We also wrote code for the simulation (plane, engine, etc...). However, we did not manage to combine these together. The sliders on the interface don't do anything useful.
<p>As this is safety-critical system, we tried to implement good code features. However, this has not been implemented everywhere. In addition, there are missing Javadoc comments in some of the classes.

## Members
| Name | Username | Email |
| --- | --- | --- |
| Jiale (Jackie) Zhou | zhoujial1 | Zhoujial1@myvuw.ac.nz |
| Patrick Mills | millspatr1 | millspatr1@myvuw.ac.nz |
| Nicholas Winsley | winslenich | winslenich@myvuw.ac.nz |
| Kunal Sharma | sharmakuna | Sharmakuna@myvuw.ac.nz |
| Patrick Sawyers | sawyerpatr1 | Sawyerpatr1@myvuw.ac.nz |



