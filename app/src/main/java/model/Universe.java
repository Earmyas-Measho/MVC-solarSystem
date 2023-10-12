package model;

import java.util.ArrayList;
import java.util.List;

/**
 * The Universe class represents a collection of solar systems.
 */
public class Universe {
  private List<SolarSystem> solarSystems;

  /**
   * Constructs a new Universe object.
   */
  public Universe() {
    this.solarSystems = new ArrayList<>();
  }

  /**
   * Returns the list of solar systems in the universe.
   *
   * @return the list of solar systems
   */
  public List<SolarSystem> getSolarSystems() {
    return new ArrayList<>(solarSystems);
  }

  /**
   * Retrieves a solar system from the universe based on its name.
   *
   * @param name the name of the solar system to retrieve
   *
   * @return the solar system with the specified name, or null if not found
   */
  public SolarSystem getSolarSystemByName(String name) {
    if (name == null) {
      throw new IllegalArgumentException("Cannot search for null solar system name.");
    }
    for (SolarSystem solarSystem : solarSystems) {
      if (solarSystem.getName().equals(name)) {
        return solarSystem;
      }
    }
    return null;
  }

}
