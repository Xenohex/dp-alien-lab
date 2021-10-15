package weapon;
/**
 * Generic weapon
 * @author Bader
 *
 */
abstract class GenericWeapon implements Weapon{
  
protected int baseDamage;
protected int currentAmmo;
protected int maxAmmo;
protected int maxRange;
protected int rateOfFire;
protected int shotsLeft;
  
  /**
   * Fire method for each weapon
   */
  abstract public int fire(int distance);
  
  /**
   * Return the baseDamage
   */
  @Override
  public int getBaseDamage() {
    return baseDamage;
  }

  /**
   * Return the maxRange
   */
  @Override
  public int getMaxRange() {
    return maxRange;
  }

  /**
   * Return the rateOfFire
   */
  @Override
  public int getRateOfFire() {
    return rateOfFire;
  }

  /**
   * Return the maxAmmo
   */
  @Override
  public int getMaxAmmo() {
    return maxAmmo;
  }

  @Override
  public int getCurrentAmmo() {
    return currentAmmo;
  }

  /**
   * Return the number of shotsLeft
   */
  @Override
  public int getShotsLeft() {
    return shotsLeft;
  }

  // Return the number of attachments in a weapon
  @Override
  public int getNumAttachments() {
    return 0;
  }

  /**
   * Reload the clips of the weapon
   */
  @Override
  public void reload() {
    currentAmmo = maxAmmo;
  }

}
