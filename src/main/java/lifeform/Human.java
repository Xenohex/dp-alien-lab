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
    armorPoints = Math.max(0, armor);
    currentLifePoints = Math.max(0, life);
    attackStrength = 5;
    maxSpeed = 3;
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
    armorPoints = Math.max(0,  armor);
  }
  
  /**
   * Humans have armor to reduce the actual damage that is deal to them
   */
  @Override
  public void takeHit(int damage) {
    int realDamage = Math.max(0, damage - armorPoints);
    currentLifePoints = Math.max(0, currentLifePoints - realDamage);
  }
}
