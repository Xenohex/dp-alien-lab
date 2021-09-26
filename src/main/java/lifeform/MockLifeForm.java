package lifeform;

/**
 * This is used to mimic what LifeForm can do so the functionalities
 * are able to be tested
 *
 */
public class MockLifeForm extends LifeForm {
  /**
   * @param name
   * @param points starting health points
   */
  public MockLifeForm(String name, int points) {
    this(name, points, 0);
  }
  
  /**
   * @param name
   * @param points
   * @param attack starting attack value
   */
  public MockLifeForm(String name, int points, int attack) {
    super(name, points, attack);
  }
}
