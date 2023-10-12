package model;

/**
 * The Moon class represents a moon orbiting a planet.
 */
public class Moon {
  private String name;
  private double radius;
  private double orbitRadius;
  private Planet parentPlanet;

  /**
   * Constructs a new Moon object with the specified name, radius, orbit radius,
   * and parent planet.
   *
   * @param name         the name of the moon
   *
   * @param radius       the radius of the moon
   *
   * @param orbitRadius  the orbit radius of the moon
   *
   * @param parentPlanet the parent planet of the moon
   *
   * @throws IllegalArgumentException if the radius is less than 10 or greater
   *                                  than 17 times the parent planet's radius,
   *                                  or if the orbit radius is less than 5 times
   *                                  the parent planet's radius
   */
  public Moon(String name, double radius, double orbitRadius, Planet parentPlanet) {
    if (name == null) {
      throw new IllegalArgumentException("Name cannot be null.");
    }
    if (radius < 10 || radius > parentPlanet.getRadius() * 17) {
      throw new IllegalArgumentException(
          "Moon radius must be larger than 10km and 17x less than the planet radius.\n");
    }
    if (orbitRadius < parentPlanet.getRadius() * 5) {
      throw new IllegalArgumentException("Moon's orbit radius must be 5x the planet radius.\n");
    }
    this.name = name;
    this.radius = radius;
    this.orbitRadius = orbitRadius;
    this.parentPlanet = new Planet(parentPlanet);
  }

  /**
   * Returns the name of the moon.
   *
   * @return the moon's name
   */
  public String getName() {
    return name;
  }

  /**
   * Returns the radius of the moon.
   *
   * @return the moon's radius
   */
  public double getRadius() {
    return radius;
  }

  /**
   * Returns the orbit radius of the moon.
   *
   * @return the moon's orbit radius
   */
  public double getOrbitRadius() {
    return orbitRadius;
  }

  /**
   * Returns the parent planet of the moon.
   *
   * @return the parent planet
   */
  public Planet getParentPlanet() {
    return new Planet(parentPlanet);
  }

  @Override
  public String toString() {
    return name + ":" + radius + ":" + orbitRadius;
  }
}
