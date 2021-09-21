package lifeform;

/**
 * Keeps track of the information associated with a simple life form. Also
 * provides the functionality related to the life form.
 *
 */
public abstract class LifeForm {
  private String myName;
  protected int currentLifePoints;
  private int attackStrength;

  /**
   * Create an instance
   * 
   * @param name   the name of the life form
   * @param points the current staring life points of the life form
   */
  public LifeForm(String name, int points) {
    myName = name;
    currentLifePoints = points;

  }

  /**
   * @return the name of the life form.
   */
  public String getName() {
    return myName;
  }

  /**
   * @return the amount of current life points the life form has.
   */
  public int getCurrentLifePoints() {
    return currentLifePoints;
  }

  /**
   * this life form takes some amount of damage that will not fall below 0
   */
  public void takeHit(int damage) {
    currentLifePoints -= damage;
    if (currentLifePoints < 0) {
      currentLifePoints = 0;
    }
  }

}
