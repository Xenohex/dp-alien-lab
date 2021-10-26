package lifeform;

import weapon.Weapon;
import environment.Environment;
import exceptions.WeaponException;
import exceptions.EnvironmentException;

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
  
  private int currentRow = -1;
  private int currentCol = -1;

  
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
   * @param damage 
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
   * @param distance 
   * @throws WeaponException 
   */
  public void attack(LifeForm opponent, int distance) throws WeaponException {
    if (distance <= 5 && currentLifePoints != 0) {
      opponent.takeHit(attackStrength);
    } else if (hasWeapon() == true && distance > 5 && weapon.getShotsLeft() != 0 
        && currentLifePoints != 0) {
      try {
        opponent.takeHit(weapon.fire(distance));
      } catch (WeaponException e) {
        throw e;
      }
    } /* else if (hasWeapon() == true && distance > weapon.getMaxRange()) {
      try{
        opponent.takeHit(weapon.fire(distance));
      } catch(WeaponException e) {
        throw e;
      } 
    } */
  }
  
  
  /**
   * @return true if lifeform has a weapon
   */
  public boolean hasWeapon() {
    if (weapon != null) {
      return true;
    } else {
      return false;
    }
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
   * @param w
   * @return true if lifeform picks up weapon
   */
  public boolean pickUpWeapon(Weapon w) {
    if (weapon == null) {
      weapon = w;
      return true;
    }
    return false;
  }
  
  /**
   * allows life form to reload
   */
  public void reload() {
    weapon.reload();
  }

  /**
   * 
   * @return lifeform's current row / currentRow
   */
  public int getCurrentRow() {
    return currentRow;
  }

  /**
   * 
   * @return lifeform's current column / currentCol
   */
  public int getCurrentCol() {
    return currentCol;
  }

  /**
   * sets the lifeform's current coordinates to the entered arguments, unless it either of them are less than zero
   * @param row
   * @param col
   * @throws EnvironmentException
   */
  public void setLocation(int row, int col) throws EnvironmentException {
    if (row < 0 || col < 0)
      throw new EnvironmentException("Coordinates are invalid!");
    this.currentRow = row;
    this.currentCol = col;
  }
}
