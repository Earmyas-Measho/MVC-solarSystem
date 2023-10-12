package model;

/**
 * The Star class represents a star in a solar system.
 */
public class Star {
  private String name;
  private double radius;
  /**
   * Constructs a new Star object with the specified name and radius.
   *
   * @param name   the name of the star
   *
   * @param radius the radius of the star
   *
   * @throws IllegalArgumentException if the radius is less than 20000
   */
  
  public Star(String name, double radius) {
    if (name == null) {
      throw new IllegalArgumentException("Name cannot be null.");
    }
    if (radius < 20000) {
      throw new IllegalArgumentException(
          "Star radius must be larger than 20000km.\n");
    }
    if (radius < 0) {
      throw new IllegalArgumentException("Radius cannot be negative.");
    }
    this.name = name;
    this.radius = radius;
  }

  /**
   * Constructs a new Star object by copying another Star object.
   *
   * @param star the star to copy
   */
  public Star(Star star) {
    this.name = star.name;
    this.radius = star.radius;
  }

  /**
   * Returns the name of the star.
   *
   * @return the star's name
   */
  public String getName() {
    return name;
  }

  /**
   * Returns the radius of the star.
   *
   * @return the star's radius
   */
  public double getRadius() {
    return radius;
  }

  @Override
  public String toString() {
    return name + ":" + radius;
  }
}
