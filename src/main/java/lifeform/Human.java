package lifeform;

/**
 * Keeps track of the information associated with a Human. Also provides the
 * functionality related to the Human.
 *
 */
public class Human extends LifeForm {
  int armorPoints = 0;

  /**
   * Constructor for each human object
   * 
   * @param name  of Human
   * @param life  starting LifePoints
   * @param armor starting armor value
   */
  public Human(String name, int life, int armor) {
    super(name, life);
    if (armor < 0) {
      armorPoints = 0;
    } else {
      armorPoints = armor;
    }
    
    if (life < 0) {
      currentLifePoints = 0;
    } else {
      currentLifePoints = life;
    }
  }

  /**
   * 
   * @return the current armorPoints stored
   */
  public int getArmorPoints() {
    return armorPoints;
  }

  /**
   * updates the armor points
   * 
   * @param armor
   */
  public void setArmorPoints(int armor) {
    if (armor < 0) {
      armorPoints = 0;
    } else {
      armorPoints = armor;
    }
  }
}
