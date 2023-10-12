package controller;

import java.io.IOException;

/**
 * The Main class is the entry point of the program.
 */
public class App {

  /**
   * The main method creates instances of SolarSystemController, SolarSystemView,
   * and Menu, and starts the program.
   *
   * @param args the command line arguments
   */
  public static void main(String[] args) throws IOException {
    SolarSystemController solarSystemController = new SolarSystemController();
    solarSystemController.loadSolarSystemFromFile("src/main/java/file.txt");
    solarSystemController.run();
  }

}
