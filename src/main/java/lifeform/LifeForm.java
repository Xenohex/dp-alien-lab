package lifeform;

import weapon.Weapon;

/**
 * Keeps track of the information associated with a simple life form. Also
 * provides the functionality related to the life form.
 *
 */
public abstract class LifeForm extends Object {
  private String myName;
  protected int currentLifePoints;
  protected int attackStrength;
  protected Weapon weapon;

  
  /**
   * Create an instance
   * 
   * @param name   the name of the life form
   * @param points the current staring life points of the life form
   */
  public LifeForm(String name, int points) {
    this(name,points,1);
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
  public void attack(LifeForm opponent, int distance) {
    if (currentLifePoints == 0) {
    } else if (weapon == null && distance <= 5) {
      opponent.takeHit(attackStrength);
    } else if (weapon != null && weapon.getCurrentAmmo() == 0 && distance <= 5) {
      opponent.takeHit(attackStrength);
    } else if (weapon != null && distance > 5 && distance <= weapon.getMaxRange()) {
      opponent.takeHit(weapon.fire(distance));
    }
  }
  
  
  /**
   * @return true if lifeform has a weapon
   */
  public boolean hasWeapon() {
    if (weapon != null) {
      return true;
    }
    else
      return false;
  }
  
  /**
   * @return the weapon the lifeform drops
   */
  public Weapon dropWeapon() {
    Weapon weaponHolder = weapon;
    weapon = null;
    return weaponHolder;
  }
  
  
  /**
   * @param weapon
   * @return true if lifeform picks up weapon
   */
  public boolean pickUpWeapon(Weapon w) {
    if (weapon == null) {
      weapon = w;
      return true;
    }
    return false;
  }
}
