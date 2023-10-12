package controller;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;
import model.Moon;
import model.Planet;
import model.SolarSystem;
import model.Star;
import view.Menu;

/**
 * providing methods to handle user choices, create and manipulate solar
 * systems, and display information.
 */
public class SolarSystemController {
  private SolarSystem currentSolarSystem;
  private Map<String, SolarSystem> solarSystems;
  private Scanner scanner;
  private Menu menuInstance;

  /**
   * Constructs a new `SolarSystemController` object, initializing necessary
   * components.
   */
  public SolarSystemController() {
    this.menuInstance = new Menu(this);
    this.solarSystems = new HashMap<>();
    this.scanner = new Scanner(System.in, StandardCharsets.UTF_8.name());

  }

  /**
   * Handles user input choices and performs corresponding actions.
   *
   * @param choice The user's choice input.
   *
   * @return `true` if the program should continue, `false` if it should exit.
   */
  public boolean handleUserChoice(String choice) {
    switch (choice) {
      case "1":
        menuInstance.createSolarSystem();
        break;
      case "2":
        menuInstance.selectCurrentSolarSystem();
        break;
      case "3":
        menuInstance.displayCurrentSolarSystem();
        break;
      case "4":
        menuInstance.addPlanet();
        break;
      case "5":
        menuInstance.removePlanet();
        break;
      case "6":
        menuInstance.addMoon();
        break;
      case "7":
        menuInstance.removeMoon();
        break;
      case "8":
        menuInstance.sortCurrentSolarSystemBySize();
        break;
      case "9":
        menuInstance.sortCurrentSolarSystemByOrbitRadius();
        break;
      case "10":
        menuInstance.displayAllSolarSystems();
        break;
      case "q":
        menuInstance.exitingProgram();
        scanner.close();
        return false;
      default:
        menuInstance.invalidInput();
    }
    return true;
  }

  /**
   * the run method.
   */
  public void run() {
    boolean continueRunning = true;
    while (continueRunning) {
      menuInstance.displayMenu();
      String choice = menuInstance.getUserChoice();
      continueRunning = handleUserChoice(choice);
    }

  }

  /**
   * If no current solar system is selected, it prints a message to prompt the
   * user to select one.
   *
   * @param currentSolarSystemName The name of the solar system to display.
   */
  public void displayCurrentSolarSystem(String currentSolarSystemName) {
    try {
      if (currentSolarSystemName != null) {
        SolarSystem solarSystem = getSolarSystem(currentSolarSystemName);
        menuInstance.displayCurrentSolarSystemInfo(solarSystem);
      } else {
        menuInstance.displayNoSolarSystemSelected();
      }
    } catch (Exception e) {
      menuInstance.displayGenericError();
    }
  }

  /**
   * It checks for valid radius and uniqueness of the star name.
   *
   * @param starName   The name of the central star.
   *
   * @param starRadius The radius of the central star.
   */
  public void createSolarSystem(String starName, double starRadius) {
    if (starRadius <= 20000) {
      menuInstance.invalidStarRadius();
      return;
    }
    if (solarSystems.containsKey(starName)) {
      menuInstance.displayStarNameExists();
      return;
    }
    try {
      Star star = new Star(starName, starRadius);
      SolarSystem solarSystem = new SolarSystem(star);
      solarSystems.put(starName, solarSystem);
      menuInstance.displaySolarSystemCreated(starName);
    } catch (Exception e) {
      menuInstance.errorDisplay(e.getMessage());
    }
  }

  /**
   * Retrieves a solar system by its name.
   *
   * @param name The name of the solar system to retrieve.
   *
   * @return The `SolarSystem` object if found, `null` if not.
   */
  public SolarSystem getSolarSystem(String name) {
    return this.solarSystems.get(name);
  }

  /**
   * Selects a solar system by its name as the current solar system.
   *
   * @param name The name of the solar system to select.
   *
   * @return `true` if the selection was successful, `false` if not.
   */
  public boolean selectSolarSystem(String name) {
    SolarSystem newCurrentSolarSystem = getSolarSystem(name);
    if (newCurrentSolarSystem != null) {
      this.currentSolarSystem = newCurrentSolarSystem;
      return true;
    } else {
      return false;
    }
  }

  /**
   * Adds a new planet to a solar system with the specified parameters,
   * performing various checks for valid input and uniqueness.
   *
   * @param solarSystemName The name of the target solar system.
   *
   * @param planetName      The name of the planet to add.
   *
   * @param radiusStr       The radius of the planet.
   *
   * @param orbitRadiusStr  The orbit radius of the planet.
   */
  public void addPlanet(String solarSystemName, String planetName, String radiusStr, String orbitRadiusStr) {
    try {
      if (solarSystemName == null || solarSystemName.trim().isEmpty()) {
        menuInstance.displayInvalidSolarSystemName();
        return;
      }

      if (planetName == null || planetName.trim().isEmpty()) {
        menuInstance.displayInvalidPlanetName();
        return;
      }

      SolarSystem solarSystem = getSolarSystem(solarSystemName);
      if (solarSystem == null) {
        menuInstance.displaySolarSystemNotFound();
        return;
      }

      double radius = Double.parseDouble(radiusStr);
      double orbitRadius = Double.parseDouble(orbitRadiusStr);

      Star parentStar = solarSystem.getStar();
      double minRadius = 1000.00;
      double maxRadius = parentStar.getRadius() / 10;

      if (radius <= minRadius || radius >= maxRadius) {
        menuInstance.displayInvalidPlanetRadius(maxRadius);
        return;
      }

      double minOrbitRadius = parentStar.getRadius() * 10;
      double maxOrbitRadius = parentStar.getRadius() * 20;

      if (orbitRadius < minOrbitRadius || orbitRadius > maxOrbitRadius) {
        menuInstance.displayInvalidOrbitRadius(minOrbitRadius, maxOrbitRadius);
        return;
      }

      if (!isPlanetNameUnique(solarSystem, planetName)) {
        menuInstance.displayPlanetNameNotUnique();
        return;
      }

      Planet planet = new Planet(planetName, radius, orbitRadius, parentStar);
      solarSystem.addPlanet(planet);
      menuInstance.displayPlanetAddedSuccessfully();

    } catch (NumberFormatException nfe) {
      menuInstance.displayInvalidRadiusOrOrbit();
    } catch (IllegalArgumentException iae) {
      // Handle IllegalArgumentException
      menuInstance.displayInvalidPlanetOrName();
    } catch (Exception e) {
      menuInstance.displayError(e.getMessage());
    }
  }

  /**
   * Adds a new moon to a planet in a solar system with the specified parameters,
   * performing various checks
   * for valid input, parent planet existence, and moon name uniqueness.
   *
   * @param solarSystemName The name of the target solar system.
   *
   * @param planetName      The name of the parent planet.
   *
   * @param moonName        The name of the moon to add.
   *
   * @param radius          The radius of the moon.
   *
   * @param orbitRadius     The orbit radius of the moon.
   */
  public void addMoon(String solarSystemName, String planetName, String moonName, double radius, double orbitRadius) {
    try {
      SolarSystem solarSystem = getSolarSystem(solarSystemName);
      if (solarSystem == null) {
        menuInstance.displaySolarSystemNotFound();
        return;
      }

      Planet parentPlanet = solarSystem.getPlanets().stream()
          .filter(planet -> planet.getName().equals(planetName))
          .findFirst()
          .orElse(null);

      if (parentPlanet == null) {
        menuInstance.displayPlanetNotFound();
        return;
      }

      double minRadius = 10;
      double maxRadius = parentPlanet.getRadius() / 17;

      if (radius <= minRadius || radius >= maxRadius) {
        menuInstance.displayInvalidMoonRadius(minRadius, maxRadius);
        return;
      }

      if (orbitRadius < parentPlanet.getRadius() * 5) {
        menuInstance.displayInvalidMoonOrbitRadius();
        return;
      }

      if (!isMoonNameUnique(parentPlanet, moonName)) {
        menuInstance.displayDuplicateMoonName();
        return;
      }

      Moon moon = new Moon(moonName, radius, orbitRadius, parentPlanet);
      parentPlanet.addMoon(moon);
      menuInstance.displayMoonAddedSuccessfully();

    } catch (Exception e) {
      menuInstance.displayErrorAddingMoon(e.getMessage());
    }
  }

  /**
   * Removes a planet from a solar system by its name, if it exists in the solar
   * system.
   * Performs checks for solar system existence and planet existence.
   *
   * @param solarSystemName The name of the target solar system.
   *
   * @param planetName      The name of the planet to remove.
   */
  public void removePlanet(String solarSystemName, String planetName) {
    try {
      SolarSystem solarSystem = getSolarSystem(solarSystemName);
      if (solarSystem == null) {
        menuInstance.displaySolarSystemNotFoundd();
        return;
      }

      Planet planetToRemove = solarSystem.getPlanets().stream()
          .filter(planet -> planet.getName().equals(planetName))
          .findFirst()
          .orElse(null);

      if (planetToRemove == null) {
        menuInstance.displayPlanetNotFound();
        return;
      }

      solarSystem.removePlanet(planetToRemove);
      menuInstance.displayPlanetRemovedSuccessfully();
    } catch (Exception e) {
      menuInstance.displayErrorRemovingPlanet(e.getMessage());
    }
  }

  /**
   * Removes a moon from a planet in a solar system with the specified parameters,
   * performing checks
   * for solar system existence, parent planet existence, and moon existence.
   *
   * @param solarSystemName The name of the target solar system.
   *
   * @param planetName      The name of the parent planet.
   *
   * @param moonName        The name of the moon to remove.
   */
  public void removeMoon(String solarSystemName, String planetName, String moonName) {
    try {
      SolarSystem solarSystem = getSolarSystem(solarSystemName);
      if (solarSystem == null) {
        menuInstance.displaySolarSystemNotFoundi();
        return;
      }

      Planet planet = solarSystem.getPlanetByName(planetName);
      if (planet == null) {
        menuInstance.displayPlanetNotFound();
        return;
      }

      Moon moonToRemove = planet.getMoons().stream()
          .filter(moon -> moon.getName().equals(moonName))
          .findFirst()
          .orElse(null);

      if (moonToRemove == null) {
        menuInstance.displayMoonNotFound();
        return;
      }

      planet.removeMoon(moonName);
      menuInstance.displayMoonRemovedSuccessfully();
    } catch (Exception e) {
      menuInstance.displayErrorRemovingMoon(e.getMessage());
    }
  }

  /**
   * Removes the central star and all planets from the current solar system,
   * effectively clearing the solar system.
   * Displays a message to inform the user.
   */
  public void removeStar() {
    if (currentSolarSystem == null) {
      menuInstance.displayNoSolarSystemSelected();
      return;
    }
    currentSolarSystem.getPlanets().clear();
    currentSolarSystem.setStar(null);
    menuInstance.displayStarRemovedSuccessfully();
  }

  /**
   * Retrieves a list of planets in the current solar system, sorted by their size
   * (radius).
   *
   * @return A sorted list of planets.
   */
  public List<Planet> getPlanetsOrderedBySize() {
    if (currentSolarSystem == null) {
      menuInstance.displayNoSolarSystemSelected();
      return Collections.emptyList();
    }
    List<Planet> sortedPlanets = currentSolarSystem.getPlanets().stream()
        .sorted(Comparator.comparing(Planet::getRadius))
        .collect(Collectors.toList());
    menuInstance.displayPlanetsOrderedBySize(sortedPlanets);
    return sortedPlanets;
  }

  /**
   * Retrieves a list of planets in the current solar system, sorted by their
   * orbit radius.
   *
   * @return A sorted list of planets.
   */
  public List<Planet> getPlanetsOrderedByOrbitRadius() {
    if (currentSolarSystem == null) {
      menuInstance.displayNoSolarSystemSelected();
      return Collections.emptyList();
    }
    List<Planet> sortedPlanets = currentSolarSystem.getPlanets().stream()
        .sorted(Comparator.comparing(Planet::getOrbitRadius))
        .collect(Collectors.toList());
    menuInstance.displayPlanetsOrderedByOrbitRadius(sortedPlanets);
    return sortedPlanets;
  }

  /**
   * Checks if a planet name is unique within a given solar system.
   *
   * @param solarSystem The solar system to check within.
   *
   * @param name        The name of the planet to check for uniqueness.
   *
   * @return `true` if the name is unique, `false` otherwise.
   */
  private boolean isPlanetNameUnique(SolarSystem solarSystem, String name) {
    if (solarSystem == null) {
      menuInstance.displayInvalidSolarSystem();
      return false;
    }
    return solarSystem.getPlanets().stream().noneMatch(planet -> planet.getName().equals(name));
  }

  /**
   * Checks if a moon name is unique within a given parent planet.
   *
   * @param parentPlanet The parent planet to check within.
   *
   * @param name         The name of the moon to check for uniqueness.
   *
   * @return `true` if the name is unique, `false` otherwise.
   */
  private boolean isMoonNameUnique(Planet parentPlanet, String name) {
    if (parentPlanet == null) {
      menuInstance.displayInvalidParentPlanet();
      return false;
    }
    return parentPlanet.getMoons().stream().noneMatch(moon -> moon.getName().equals(name));
  }

  /**
   * Sorts the planets and moons within a specified solar system based on the
   * provided comparators for planets and moons.
   *
   * @param solarSystemName  The name of the target solar system.
   *
   * @param planetComparator Comparator for sorting planets.
   *
   * @param moonComparator   Comparator for sorting moons.
   */
  public void sortSolarSystem(String solarSystemName, Comparator<Planet> planetComparator,
      Comparator<Moon> moonComparator) {
    SolarSystem solarSystem = getSolarSystem(solarSystemName);
    if (solarSystem == null) {
      menuInstance.displayInvalidSolarSystem();
      return;
    }
    solarSystem.sortPlanetsAndMoons(planetComparator, moonComparator);
    menuInstance.displaySolarSystemSorted();
  }

  /**
   * Retrieves a list of all solar systems stored in the controller.
   *
   * @return A list of all solar systems.
   */
  public List<SolarSystem> getAllSolarSystems() {
    if (solarSystems.isEmpty()) {
      menuInstance.displayNoSolarSystems();
      return Collections.emptyList();
    }
    List<SolarSystem> allSolarSystems = new ArrayList<>(solarSystems.values());
    menuInstance.displayAllSolarSystems(allSolarSystems);
    return allSolarSystems;
  }

  /**
   * Loads solar system data from a file and populates the controller with the
   * data.
   * Parses the data to recreate solar systems, planets, and moons.
   *
   * @param filePath The path to the file containing solar system data.
   */
  public void loadSolarSystemFromFile(String filePath) {
    try (BufferedReader reader = new BufferedReader(
        new InputStreamReader(new FileInputStream(filePath), StandardCharsets.UTF_8))) {
      String line;
      SolarSystem currentSolarSystem = null;
      Planet currentPlanet = null;

      while ((line = reader.readLine()) != null) {
        int depth = countLeadingHyphens(line);
        String[] parts = line.split(":");

        if (depth == 0) {
          String starName = parts[0];
          double starRadius = Double.parseDouble(parts[1]);
          currentSolarSystem = new SolarSystem(starName, new Star(starName, starRadius));
          addSolarSystem(currentSolarSystem, false);
        } else if (depth == 1 && currentSolarSystem != null) {
          String planetName = parts[0].substring(1);
          double radius = Double.parseDouble(parts[1]);
          double orbitRadius = Double.parseDouble(parts[2]);
          currentPlanet = new Planet(planetName, radius, orbitRadius, currentSolarSystem.getStar());
          currentSolarSystem.addPlanet(currentPlanet);
        } else if (depth == 2 && currentPlanet != null) {
          String moonName = parts[0].substring(2);
          double radius = Double.parseDouble(parts[1]);
          double orbitRadius = Double.parseDouble(parts[2]);
          Moon moon = new Moon(moonName, radius, orbitRadius, currentPlanet);
          currentPlanet.addMoon(moon);
        }
      }
      menuInstance.displaySolarSystemLoaded();
    } catch (Exception e) {
      menuInstance.displayErrorLoadingSolarSystem(e.getMessage());
    }
  }

  /**
   * Counts the number of leading hyphens ('-') in a string.
   *
   * @param str The input string.
   * @return The count of leading hyphens.
   */
  private int countLeadingHyphens(String str) {
    int count = 0;
    for (char c : str.toCharArray()) {
      if (c == '-') {
        count++;
      } else {
        break;
      }
    }
    return count;
  }

  /**
   * Adds a solar system to the controller's list of solar systems, performing
   * checks for null solar systems and name uniqueness.
   *
   * @param solarSystem The solar system to add.
   */
  public void addSolarSystem(SolarSystem solarSystem, boolean showMessage) {
    try {
      if (solarSystem == null) {
        menuInstance.displayNullSolarSystemError();
        return;
      }
      if (solarSystems.containsKey(solarSystem.getName())) {
        menuInstance.displayDuplicateSolarSystemError();
        return;
      }
      solarSystems.put(solarSystem.getName(), solarSystem);
      if (showMessage) {
        menuInstance.displaySolarSystemAddedSuccess();
      }
    } catch (Exception e) {
      menuInstance.displayGenericError();
    }
  }

}
