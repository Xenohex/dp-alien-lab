package weapon;

import exceptions.WeaponException;

/**
 * Generic weapon
 * @author Bader
 *
 */
abstract class GenericWeapon implements Weapon {
  
  protected int baseDamage;
  protected int currentAmmo;
  protected int maxAmmo;
  protected int maxRange;
  protected int rateOfFire;
  protected int shotsLeft;
  
  /**
   * Fire method for each weapon
   */
  abstract public int fire(int distance) throws WeaponException;
  abstract public String toString();
  /**
   * Return the baseDamage
   */
  public int getBaseDamage() {
    return baseDamage;
  }

  /**
   * Return the maxRange
   */
  public int getMaxRange() {
    return maxRange;
  }

  /**
   * Return the rateOfFire
   */
  public int getRateOfFire() {
    return rateOfFire;
  }

  /**
   * Return the maxAmmo
   */
  public int getMaxAmmo() {
    return maxAmmo;
  }

  /**
   * Return currentAmmo
   */
  public int getCurrentAmmo() {
    return currentAmmo;
  }

  /**
   * Return the number of shotsLeft
   */
  public int getShotsLeft() {
    return shotsLeft;
  }

  /**
   * Return the number of attachments in a weapon
   */
  public int getNumAttachments() {
    return 0;
  }

  /**
   * Reload the clips of the weapon
   */
  public void reload() {
    currentAmmo = maxAmmo;
  }

  /**
   * Update how many shots left in each round
   * @param time
   */
  @Override
  public void updateTime(int time) {
    if (time >= 0) {
      shotsLeft = rateOfFire;
    }
  }
  
}
