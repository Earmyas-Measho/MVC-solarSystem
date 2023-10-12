package view;

import controller.SolarSystemController;
import java.nio.charset.StandardCharsets;
import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;
import model.Moon;
import model.Planet;
import model.SolarSystem;

/**
 * the menu class of the mvc pattern.
 */
public class Menu {
  private SolarSystemController solarSystemController;
  private Scanner scanner;
  private String currentSolarSystemName;
  private String separator;

  /**
   * constractor for menu class.
   *
   * @param solarSystemController The name of the central star.
   */
  public Menu(SolarSystemController solarSystemController) {
    this.solarSystemController = solarSystemController;
    this.scanner = new Scanner(System.in, StandardCharsets.UTF_8);
    this.separator = "#####################################################################";
  }

  /**
   * menudispaly.
   */
  public void displayMenu() {
    System.out.println("######## PLEASE INSERT YOUR CHOICE UNDER #############");
    System.out.println("1. Create new solar system");
    System.out.println("2. Select current solar system");
    System.out.println("3. Display current solar system");
    System.out.println("4. Add planet to current solar system");
    System.out.println("5. Remove planet from current solar system");
    System.out.println("6. Add moon to planet in current solar system");
    System.out.println("7. Remove moon from planet in current solar system");
    System.out.println("8. Sort current solar system by size");
    System.out.println("9. Sort current solar system by orbit radius");
    System.out.println("10. Display all solar systems");
    System.out.println("q. Quit");
    System.out.print("please insert your choice here ==> ");
  }

  /**
   * Reads user input and returns the user's choice.
   *
   * @return The user's choice as a String.
   */
  public String getUserChoice() {
    String choice;
    try {
      choice = scanner.nextLine();
    } catch (NoSuchElementException | IllegalStateException e) {
      System.out.println("Error reading input. Please try again.");
      return null;
    }
    return choice;
  }

  /**
   * The provided star radius is invalid.
   */
  public void invalidStarRadius() {
    System.out.println(separator);
    System.out.println(
        "The provided star radius is invalid. The radius of a star must be larger than 20000km.");
    System.out.println(separator);
  }

  public void displayInvalidPlanetOrName() {
    System.out.println("Cannot add null planet or duplicate planet name.");
  }

  /**
   * Displays a message indicating that no current solar system is selected and
   * prompts the user
   * to select a solar system first.
   */
  public void displayNoSolarSystemSelected() {
    System.out.println("No current solar system selected. Please select a solar system first.");
  }

  /**
   * Invalid input.
   */
  public void invalidInput() {
    System.out.println("Invalid input. Please enter a number from 1-10 or 'q' to quit.");

  }

  /**
   * exsit program.
   */
  public void exitingProgram() {
    System.out.println("Exiting the program. Goodbye!");

  }

  /**
   * Prints an error message indicating that the provided star name already exists
   * and prompts the user to provide a unique name.
   */
  public void displayStarNameExists() {
    System.out.println("The provided star name already exists. Please provide a unique name and try again.");

  }

  /**
   * Prints a message indicating that a solar system with the specified central
   * star name
   * has been successfully created.
   *
   * @param starName The name of the central star.
   */
  public void displaySolarSystemCreated(String starName) {
    System.out.println("Solar system with central star name \"" + starName + "\" has been successfully created.");

  }

  /**
   * Prints an error message with a custom message.
   *
   * @param message The error message.
   */
  public void displayError(String message) {
    System.out.println("An error occurred: " + message);

  }

  /**
   * Prints an error message indicating that the solar system name cannot be null
   * or empty.
   */
  public void displayInvalidSolarSystemName() {
    System.out.println("Solar system name cannot be null or empty.");
  }

  /**
   * Prints an error message indicating that the planet name cannot be null or
   * empty.
   */
  public void displayInvalidPlanetName() {
    System.out.println("Planet name cannot be null or empty.");
  }

  /**
   * Prints an error message indicating that the specified solar system does not
   * exist.
   */
  public void displaySolarSystemNotFound() {
    System.out.println("The solar system does not exist.");
  }

  /**
   * Prints an error message indicating that the user has entered an invalid
   * number format
   * for the planet radius or orbit radius.
   */
  public void displayInvalidRadiusOrOrbit() {
    System.out.println("Invalid number format for radius or orbit radius. Please enter valid numbers.");
  }

  /**
   * Prints an error message indicating that the provided planet radius is
   * invalid.
   *
   * @param maxRadius The maximum valid planet radius.
   */
  public void displayInvalidPlanetRadius(double maxRadius) {
    System.out
        .println("The provided planet radius is invalid. Radius must be between 1000.0 and " + maxRadius + " km.");
  }

  /**
   * Prints an error message indicating that the provided orbit radius is invalid.
   *
   * @param minOrbitRadius The minimum valid orbit radius.
   * @param maxOrbitRadius The maximum valid orbit radius.
   */
  public void displayInvalidOrbitRadius(double minOrbitRadius, double maxOrbitRadius) {
    System.out.println("The provided orbit radius is invalid. Radius must be larger than " + minOrbitRadius
        + " km and less than " + maxOrbitRadius + " km.");
  }

  /**
   * Prints an error message indicating that the planet name is not unique and
   * prompts the user to use a unique name.
   */
  public void displayPlanetNameNotUnique() {
    System.out.println("Planet name is already in use. Please use a unique name.");
  }

  /**
   * Prints a message indicating that a planet has been added successfully.
   */
  public void displayPlanetAddedSuccessfully() {
    System.out.println("Planet added successfully.");
  }

  /**
   * Prints an error message with a custom message.
   *
   * @param message The error message.
   */
  public void errorDisplay(String message) {
    System.out.println("An error occurred: " + message);
  }

  /**
   * Prints a message indicating that the solar system has been successfully
   * loaded.
   */
  public void displaySolarSystemLoaded() {
    System.out.println("Solar system has been successfully loaded from the file.");
  }

  /**
   * Prints an error message when loading the solar system from a file.
   *
   * @param errorMessage The error message.
   */
  public void displayErrorLoadingSolarSystem(String errorMessage) {
    System.out.println("An error occurred when loading the solar system from the file: " + errorMessage);
  }

  /**
   * Prints an error message indicating that a null SolarSystem cannot be added.
   */
  public void displayNullSolarSystemError() {
    System.out.println("Error: Cannot add null SolarSystem.");
  }

  /**
   * Prints an error message indicating that a solar system with the same name
   * exists.
   */
  public void displayDuplicateSolarSystemError() {
    System.out.println("Error: Solar system with the same name already exists.");
  }

  /**
   * Prints a message indicating that a solar system has been added successfully.
   */
  public void displaySolarSystemAddedSuccess() {
    System.out.println("Solar system added successfully.");
  }

  /**
   * Prints a message indicating that all solar systems have been added
   * successfully.
   */
  public void displayAllSolarSystemsAddedSuccess() {
    System.out.println("All solar systems added successfully.");
  }

  /**
   * Prints a generic error message indicating that an error occurred and prompts
   * the user to try again.
   */
  public void displayGenericError() {
    System.out.println("An error occurred. Please try again.");
  }

  /**
   * Prints the information of the current Solar System, including its name
   * anddetails.
   *
   * @param solarSystem The current Solar System.
   */
  public void displayCurrentSolarSystemInfo(SolarSystem solarSystem) {
    System.out.println("The current Solar System is: " + solarSystem.getName() + "\n" + solarSystem.toString());
  }

  /**
   * desplaying messege.
   *
   * @param message The message to be displayed.
   */
  public void displayMessage(String message) {
    System.out.println(message);
  }

  /**
   * Prints an error message indicating that the planet you're trying to add a
   * moon to
   * does not exist in this solar system.
   */
  public void displayPlanetNotFound() {
    System.out.println("The planet you're trying to add a moon to does not exist in this solar system.");
  }

  /**
   * Prints an error message indicating that the provided moon radius is invalid.
   *
   * @param minRadius The minimum valid moon radius.
   *
   * @param maxRadius The maximum valid moon radius.
   */

  public void displayInvalidMoonRadius(double minRadius, double maxRadius) {
    System.out.printf("The provided moon radius is invalid. The radius of the moon must be larger than %.2f km "
        + "and less than %.2f km.%n", minRadius, maxRadius);
  }

  /**
   * Prints an error message indicating that the provided orbit radius for the
   * moon is invalid.
   * The orbit radius of the moon must be at least 5 times the radius of it parent
   * planet.
   */
  public void displayInvalidMoonOrbitRadius() {
    System.out.println(
        "The provided orbit radius for the moon is invalid."
            +
            "The orbit radius of the moon must be at least 5 times the radius of its parent planet.");
  }

  /**
   * Prints an error message indicating that the name provided for the moon
   * already exists
   * for this planet and prompts the user to provide a unique name.
   */
  public void displayDuplicateMoonName() {
    System.out.println(
        "The name provided for the moon already exists for this planet. Please provide a unique name and try again.");
  }

  /**
   * Prints a message indicating that a moon has been added to a planet
   * successfully.
   */
  public void displayMoonAddedSuccessfully() {
    System.out.println("Moon added successfully.");
  }

  /**
   * Prints an error message indicating that an error occurred when adding the
   * moon and provides the error message.
   *
   * @param errorMessage The error message.
   */
  public void displayErrorAddingMoon(String errorMessage) {
    System.out.println("An error occurred when adding the moon: " + errorMessage);
  }

  /**
   * Prints an error message indicating that the solar system you're trying to
   * remove does not exist.
   */
  public void displaySolarSystemNotFoundd() {
    System.out.println("The solar system you're trying to remove a planet from does not exist.");
  }

  /**
   * Prints a message indicating that a planet has been removed from the solar
   * system successfully.
   */
  public void displayPlanetRemovedSuccessfully() {
    System.out.println("Planet removed successfully.");
  }

  /**
   * Prints an error message indicating that an error occurred when removing the
   * planet and the error message.
   *
   * @param errorMessage The error message.
   */
  public void displayErrorRemovingPlanet(String errorMessage) {
    System.out.println("An error occurred when removing the planet: " + errorMessage);
  }

  /**
   * Prints an error message indicating that the solar system you're trying to
   * remove a moon from does not exist.
   */
  public void displaySolarSystemNotFoundi() {
    System.out.println("The solar system you're trying to remove a moon from does not exist.");
  }

  /**
   * Prints an error message indicating that the moon you're trying to remove does
   * not exist
   */
  public void displayMoonNotFound() {
    System.out.println("Moon not found for the given planet. Please check the moon name and try again.");
  }

  /**
   * Prints a message indicating that a moon has been removed from a planet
   * successfully.
   */
  public void displayMoonRemovedSuccessfully() {
    System.out.println("Moon removed successfully.");
  }

  /**
   * Prints an error message that an error occurred when removing the moon and
   * provides the error message.
   *
   * @param errorMessage The error message.
   */
  public void displayErrorRemovingMoon(String errorMessage) {
    System.out.println("An error occurred when removing the moon: " + errorMessage);
  }

  /**
   * Prints a message indicating that the star and its planets have been
   * successfully removed
   */
  public void displayStarRemovedSuccessfully() {
    System.out.println("Star and its planets have been successfully removed from the current solar system.");
  }

  /**
   * Prints a list of planets ordered by size, displaying their names and radii.
   *
   * @param planets The list of planets to be displayed.
   */
  public void displayPlanetsOrderedBySize(List<Planet> planets) {
    System.out.println("Planets ordered by size:");
    for (Planet planet : planets) {
      System.out.println(planet.getName() + " - " + planet.getRadius());
    }
  }

  /**
   * Prints a list of planets ordered by orbit radius, displaying their names and
   * orbit radii.
   *
   * @param planets The list of planets to be displayed.
   */
  public void displayPlanetsOrderedByOrbitRadius(List<Planet> planets) {
    System.out.println("Planets ordered by orbit radius:");
    for (Planet planet : planets) {
      System.out.println(planet.getName() + " - " + planet.getOrbitRadius());
    }
  }

  /**
   * Prints an error message that the provided solar system is null.
   */
  public void displayInvalidSolarSystem() {
    System.out.println("Solar system is null. Please provide a valid solar system.");
  }

  /**
   * Prints an error message that the parent planet is null ask the user again.
   */
  public void displayInvalidParentPlanet() {
    System.out.println("Parent planet is null. Please provide a valid planet.");
  }

  /**
   * Prints a message indicating that the solar system has been sorted.
   */
  public void displaySolarSystemSorted() {
    System.out.println("Solar system has been sorted.");
  }

  /**
   * Prints a message indicating that no solar systems are registered.
   */
  public void displayNoSolarSystems() {
    System.out.println("No solar systems registered.");
  }

  /**
   * Prints a list of all solar systems retrieved from the controller and displays
   * them.
   */
  public void displayAllSolarSystems(List<SolarSystem> solarSystems) {
    if (solarSystems == null || solarSystems.isEmpty()) {
      System.out.println("No Solar Systems to display.");
      return;
    }
    System.out.println("\n########### Displaying All Solar Systems ############");
    for (SolarSystem solarSystem : solarSystems) {
      displaySolarSystem(solarSystem);
    }
    System.out.println("########### End of Solar Systems List  ################\n");
  }

  /**
   * Prompts the user to create a new solar system by providing the name and
   * radius of the central star.
   */
  public void displayAllSolarSystems() {
    List<SolarSystem> allSolarSystems = this.solarSystemController.getAllSolarSystems();
    displayAllSolarSystems(allSolarSystems);
  }

  /**
   * Prompts the user to select a solar system by entering the name of the central
   * star.
   */
  public void createSolarSystem() {
    while (true) {
      System.out.print("Enter the name of the central star ==> ");
      String starName = scanner.nextLine();
      if (starName == null || starName.trim().isEmpty()) {
        throw new IllegalArgumentException("Star name cannot be empty.");
      }
      System.out.print("Enter the radius of the star ==> ");
      String starRadiusStr = scanner.nextLine();
      if (starRadiusStr == null || starRadiusStr.trim().isEmpty()) {
        throw new IllegalArgumentException("Radius cannot be empty.");
      }
      double starRadius = Double.parseDouble(starRadiusStr);
      if (starRadius < 0) {
        throw new IllegalArgumentException("Radius cannot be negative.");
      }
      solarSystemController.createSolarSystem(starName, starRadius);
      break;
    }
  }

  /**
   * Displays the details of the currently selected solar system, if one is
   * selected.
   */
  public void selectCurrentSolarSystem() {
    boolean isSolarSystemSelected = false;
    while (!isSolarSystemSelected) {
      System.out.print("Enter the name of the central star of the solar system you want to select ==> ");
      String name = scanner.nextLine();
      isSolarSystemSelected = solarSystemController.selectSolarSystem(name);
      if (isSolarSystemSelected) {
        System.out.println("Solar system with central star name \" " + name + " \" is selected now.");
        this.currentSolarSystemName = name;
      } else {
        System.out.println("Solar system with central star name \"" + name + "\" not found. Try again.");

      }
    }
  }

  /**
   * Displays the details of the currently selected solar system, if one is
   * selected.
   */
  public void displayCurrentSolarSystem() {
    if (currentSolarSystemName != null) {
      SolarSystem solarSystem = solarSystemController.getSolarSystem(currentSolarSystemName);
      displaySolarSystem(solarSystem);
    } else {
      displayNoSolarSystemSelected();

    }
  }

  /**
   * Displays the details of the solar system with the specified name.
   *
   * @param currentSolarSystemName The name of the solar system to display.
   */
  public void displayCurrentSolarSystem(String currentSolarSystemName) {
    if (currentSolarSystemName != null) {
      SolarSystem solarSystem = solarSystemController.getSolarSystem(currentSolarSystemName);
      displaySolarSystem(solarSystem);
    } else {
      displayNoSolarSystemSelected();
    }
  }

  /**
   * Prompts the user to add a planet to the currently selected solar system.
   * Collects input for planet details and uses the solarSystemController to add
   * the planet.
   */
  public void addPlanet() {
    if (currentSolarSystemName != null) {
      System.out.print("Enter the name of the planet ==> ");
      final String planetName = scanner.nextLine();
      System.out.print("Enter the radius of the planet ==> ");
      String planetRadiusStr = scanner.nextLine();
      System.out.print("Enter the orbit radius of the planet ==> ");
      String orbitRadiusStr = scanner.nextLine();
      solarSystemController.addPlanet(currentSolarSystemName, planetName, planetRadiusStr, orbitRadiusStr);
    } else {
      displayNoSolarSystemSelected();
    }
  }

  /**
   * Prompts the user to remove a planet from the currently selected solar system.
   * Collects input for the planet's name and uses the solarSystemController to
   * remove the planet.
   */
  public void removePlanet() {
    if (currentSolarSystemName != null) {
      String planetName;
      while (true) {
        System.out.print("Enter the name of the planet ==>");
        planetName = scanner.nextLine();
        solarSystemController.removePlanet(currentSolarSystemName, planetName);
        System.out.println(
            "Planet with name \"" + planetName + "\" has been removed from " + currentSolarSystemName + ".");
        break;
      }
    } else {
      displayNoSolarSystemSelected();

    }
  }

  /**
   * Prompts the user to add a moon to a planet in the currently selected solar
   * system.
   * Collects input for moon details and uses the solarSystemController to add the
   * moon.
   */
  public void addMoon() {
    if (currentSolarSystemName != null) {
      String planetName;
      String moonName;
      double moonRadius;
      double orbitRadius;
      while (true) {
        System.out.print("Enter the name of the planet the moon orbits ==> ");
        planetName = scanner.nextLine();
        System.out.print("Enter the name of the moon ==> ");
        moonName = scanner.nextLine();
        System.out.print("Enter the radius of the moon ==> ");
        moonRadius = scanner.nextDouble();
        System.out.print("Enter the orbit radius of the moon ==>");
        orbitRadius = scanner.nextDouble();
        scanner.nextLine();
        solarSystemController.addMoon(currentSolarSystemName, planetName, moonName,
            moonRadius, orbitRadius);
        System.out.println("Moon " + moonName + " has been added to planet " + planetName + ".");
        break;
      }
    } else {
      displayNoSolarSystemSelected();
    }
  }

  /**
   * Prompts the user to remove a moon from a planet in the currently selected
   * solar system.
   * Collects input for moon and planet names and uses the solarSystemController
   * to remove the moon.
   */
  public void removeMoon() {
    if (currentSolarSystemName != null) {
      String planetName;
      String moonName;
      while (true) {
        System.out.print("Enter the name of the planet the moon orbits ==> ");
        planetName = scanner.nextLine();
        System.out.print("Enter the name of the moon ==> ");
        moonName = scanner.nextLine();
        solarSystemController.removeMoon(currentSolarSystemName, planetName, moonName);
        System.out.println("Moon " + moonName + " has been removed from planet " + planetName + ".");
        break;
      }
    } else {
      displayNoSolarSystemSelected();
    }
  }

  /**
   * Sorts the planets in the currently selected solar system by size (radius) and
   * displays the result.
   */
  public void sortCurrentSolarSystemBySize() {
    if (currentSolarSystemName != null) {
      solarSystemController.sortSolarSystem(currentSolarSystemName, Comparator.comparing(Planet::getRadius),
          Comparator.comparing(Moon::getRadius));
      System.out.println("Planets sorted by size:");
      displayCurrentSolarSystem();
    } else {
      displayNoSolarSystemSelected();
    }
  }

  /**
   * Sorts the planets in the currently selected solar system by orbit radius and
   * displays the result.
   */
  public void sortCurrentSolarSystemByOrbitRadius() {
    if (currentSolarSystemName != null) {
      solarSystemController.sortSolarSystem(currentSolarSystemName, Comparator.comparing(Planet::getOrbitRadius),
          Comparator.comparing(Moon::getOrbitRadius));
      System.out.println("Planets sorted by orbit radius:");
      displayCurrentSolarSystem();
    } else {
      displayNoSolarSystemSelected();
    }
  }

  /**
   * Displays the details of a given solar system, including its star, planets,
   * and moons.
   *
   * @param solarSystem The SolarSystem to be displayed.
   */
  public void displaySolarSystem(SolarSystem solarSystem) {
    if (solarSystem == null) {
      System.out.println("No Solar System selected.");
      return;
    }
    System.out.println(solarSystem.getStar().getName() + ":" + solarSystem.getStar().getRadius());
    for (Planet planet : solarSystem.getPlanets()) {
      System.out.println("-" + planet.getName() + ":" + planet.getRadius() + ":" + planet.getOrbitRadius());
      for (Moon moon : planet.getMoons()) {
        System.out.println("--" + moon.getName() + ":" + moon.getRadius() + ":" + moon.getOrbitRadius());
      }
    }
  }

}
