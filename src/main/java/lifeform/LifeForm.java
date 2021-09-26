package lifeform;

/**
 * Keeps track of the information associated with a simple life form. Also
 * provides the functionality related to the life form.
 *
 */
public abstract class LifeForm {
  private String myName;
  protected int currentLifePoints;
  protected int attackStrength;

  /**
   * Create an instance
   * 
   * @param name   the name of the life form
   * @param points the current staring life points of the life form
   */
  public LifeForm(String name, int points) {
    this(name,points,0);
  }
  
  /**
   * creates an instance
   * @param name
   * @param points - the life points
   * @param attack - the attack strength
   */
  public LifeForm(String name, int points, int attack) {
    myName = name;
    currentLifePoints = points;
    attackStrength = attack;
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
    currentLifePoints = Math.max(0, currentLifePoints - damage);
  }
  
  /**
   * 
   * @return the attack strength of the current LifeForm
   */
  public int getAttackStrength() {
    return attackStrength;
  }
  
  /**
   * attack another LifeForm with attack strength
   * @param opponent the other LifeForm that is being attacked
   */
  public void attack(LifeForm opponent) {
    if (currentLifePoints != 0) {
      opponent.takeHit(attackStrength);
    }
  }
}
