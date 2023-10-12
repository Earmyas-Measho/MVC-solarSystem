package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * The Planet class represents a celestial body that orbits a star within a
 * solar system.
 */
public class Planet {
  private String name;
  private double radius;
  private double orbitRadius;
  private Star parentStar;
  private List<Moon> moons;

  /**
   * Constructs a new Planet object with the specified name, radius, orbit radius,
   * and parent star.
   *
   * @param name        the name of the planet
   *
   * @param radius      the radius of the planet
   *
   * @param orbitRadius the orbit radius of the planet
   *
   * @param parentStar  the parent star of the planet
   * @throws IllegalArgumentException if the radius is less than 1000 or greater
   *                                  than 10 times the parent star's radius,
   *                                  or if the orbit radius is less than 10 times
   *                                  the parent star's radius
   */
  public Planet(String name, double radius, double orbitRadius, Star parentStar) {
    if (name == null) {
      throw new IllegalArgumentException("Name cannot be null.");
    }
    if (radius < 1000 || radius > parentStar.getRadius() * 10) {
      throw new IllegalArgumentException(
          "Planet radius must be larger than 1000km and less than 10 times the star's radius.\n");
    }
    if (orbitRadius < parentStar.getRadius() * 10) {
      throw new IllegalArgumentException(
          "Planet's orbit radius must be larger than 10 times the star's radius.\n");
    }
    this.name = name;
    this.radius = radius;
    this.orbitRadius = orbitRadius;
    this.parentStar = new Star(parentStar);
    this.moons = new ArrayList<>();
  }

  /**
   * Creates a new Planet object that is a copy of the specified Planet object.
   *
   * @param planet the Planet object to copy
   */
  public Planet(Planet planet) {
    this.name = planet.name;
    this.radius = planet.radius;
    this.orbitRadius = planet.orbitRadius;
    this.parentStar = new Star(planet.parentStar);
    this.moons = new ArrayList<>(planet.moons);
  }

  /**
   * Returns the name of the planet.
   *
   * @return the planet's name
   */
  public String getName() {
    return name;
  }

  /**
   * Returns the radius of the planet.
   *
   * @return the planet's radius
   */
  public double getRadius() {
    return radius;
  }

  /**
   * Returns the orbit radius of the planet.
   *
   * @return the planet's orbit radius
   */
  public double getOrbitRadius() {
    return orbitRadius;
  }

  /**
   * Returns the parent star of the planet.
   *
   * @return the planet's parent star
   */
  public Star getParentStar() {
    return parentStar;
  }

  /**
   * Returns the list of moons orbiting the planet.
   *
   * @return the list of moons
   */
  public List<Moon> getMoons() {
    return new ArrayList<>(moons);
  }

  /**
   * Adds a moon to the planet's list of moons.
   *
   * @param moon the moon to add
   *
   * @throws IllegalArgumentException if the moon's name is not unique within the
   *                                  planet
   */
  public void addMoon(Moon moon) {
    if (moon == null) {
      throw new IllegalArgumentException("Cannot add null Moon.");
    }
    for (Moon existingMoon : moons) {
      if (existingMoon.getName().equals(moon.getName())) {
        throw new IllegalArgumentException(
            "Moon name must be unique within a planet.\n");
      }
    }
    moons.add(moon);
  }

  /**
   * Removes a moon from the planet's list of moons.
   *
   * @param moon the moon to remove
   */
  public void removeMoon(Moon moon) {
    if (moon == null) {
      throw new IllegalArgumentException("Cannot remove null Moon.");
    }
    moons.remove(moon);
  }

  /**
   * Removes a moon from the planet's list of moons based on its name.
   *
   * @param moonName the name of the moon to remove
   */
  public void removeMoon(String moonName) {
    if (moonName == null) {
      throw new IllegalArgumentException("Cannot remove a moon with null name.");
    }

    Moon moonToRemove = null;
    for (Moon moon : moons) {
      if (moon.getName().equals(moonName)) {
        moonToRemove = moon;
        break;
      }
    }

    if (moonToRemove == null) {
      throw new IllegalArgumentException("Moon with given name not found.");
    }

    moons.remove(moonToRemove);
  }

  /**
   * Displays the list of moons orbiting the planet.
   */
  public void displayMoons() {
    System.out.println("Moons:");
    for (Moon moon : moons) {
      System.out.println(moon);
    }
  }

  /**
   * Sorts the moons in the star system using the provided comparator.
   *
   * @param comparator The comparator to use for sorting the moons.
   */
  public void sortMoons(Comparator<Moon> comparator) {
    Collections.sort(this.moons, comparator);
  }

  /**
   * tostring methos.
   */
  @Override
  public String toString() {
    return name + ":" + radius + ":" + orbitRadius;
  }
}
