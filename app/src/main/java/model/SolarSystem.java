package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * The SolarSystem class represents a solar system that consists of a central
 * star and a collection of planets.
 */
public class SolarSystem {
  private String name;
  private Star star;
  private List<Planet> planets;

  /**
   * Constructs a new SolarSystem object with the specified name and central star.
   *
   * @param name the name of the solar system
   *
   * @param star the central star of the solar system
   */
  public SolarSystem(String name, Star star) {
    this.name = name;
    this.star = star;
    this.planets = new ArrayList<>();
  }

  /**
   * Constructs a new SolarSystem object with the specified central star.
   *
   * @param star the central star of the solar system
   */
  public SolarSystem(Star star) {
    this.star = star;
    this.planets = new ArrayList<>();
  }

  /**
   * Returns the name of the solar system.
   *
   * @return the solar system's name
   */
  public String getName() {
    return name;
  }

  /**
   * Returns the central star of the solar system.
   *
   * @return the solar system's central star
   */
  public Star getStar() {
    return star;
  }

  /**
   * Sets the central star of the solar system.
   *
   * @param star the central star to set
   */
  public void setStar(Star star) {
    this.star = star;
  }

  /**
   * Returns the list of planets in the solar system.
   *
   * @return the list of planets
   */
  public List<Planet> getPlanets() {
    return planets;
  }

  /**
   * Removes a planet from the solar system.
   *
   * @param planet the planet to remove
   */
  public void removePlanet(Planet planet) {
    if (planet == null || !planets.contains(planet)) {
      throw new IllegalArgumentException("Cannot remove a null planet or a planet that is not in the system.");
    }
    planets.remove(planet);
  }

  /**
   * Adds a planet to the solar system.
   *
   * @param planet the planet to add
   *
   * @throws IllegalArgumentException if the planet's name is not unique within
   *                                  the solar system
   */
  public void addPlanet(Planet planet) {
    if (planet == null) {
      throw new IllegalArgumentException();
    }
    for (Planet existingPlanet : planets) {
      if (existingPlanet.getName().equals(planet.getName())) {
        throw new IllegalArgumentException();
      }
    }
    planets.add(planet);
  }

  /**
   * Retrieves a planet from the solar system based on its name.
   *
   * @param name the name of the planet to retrieve
   *
   * @return the planet with the specified name, or null if not found
   */
  public Planet getPlanetByName(String name) {
    if (name == null) {
      throw new IllegalArgumentException("Cannot search for null planet name.");
    }
    for (Planet planet : planets) {
      if (planet.getName().equals(name)) {
        return planet;
      }
    }
    return null;
  }

  /**
   * Sorts the planets in the star system using the provided comparator, and sorts
   * the moons of each planet using the provided moon comparator.
   *
   * @param planetComparator The comparator to use for sorting the planets.
   *
   * @param moonComparator   The comparator to use for sorting the moons of each
   *                         planet.
   */
  public void sortPlanetsAndMoons(Comparator<Planet> planetComparator, Comparator<Moon> moonComparator) {
    Collections.sort(this.planets, planetComparator);
    for (Planet planet : this.planets) {
      planet.sortMoons(moonComparator);
    }
  }

  /**
   * Returns a string representation of the star system.
   *
   * @return A string representation of the star system.
   */
  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append(star.getName()).append(":").append(star.getRadius()).append("\n");
    for (Planet planet : planets) {
      sb.append("-").append(planet.toString()).append("\n");
      for (Moon moon : planet.getMoons()) {
        sb.append("--").append(moon.toString()).append("\n");
      }
    }
    return sb.toString();
  }
}
