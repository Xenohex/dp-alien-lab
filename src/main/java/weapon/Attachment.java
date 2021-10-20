package weapon;

import exceptions.WeaponException;

/**
 * Attachment
 * @author Jun
 *
 */
abstract class Attachment implements Weapon {
  protected Weapon base;
  
  public Attachment() {}
  /**
   * Fire method for each attachment
   */
  public abstract int fire(int distance) throws WeaponException;

  /**
   * Return the baseDamage
   */
  public int getBaseDamage() {
    return base.getBaseDamage();
  }
  
  /**
   * Return the maxRange
   */
  public int getMaxRange() {
    return base.getMaxRange();
  }
  
  /**
   * Return the rateOfFire
   */
  public int getRateOfFire() {
    return base.getRateOfFire();
  }
  
  /**
   * Return the maxAmmo
   */
  public int getMaxAmmo() {
    return base.getMaxAmmo();
  }
  
  /**
   * Return currentAmmo
   */
  public int getCurrentAmmo() {
    return base.getCurrentAmmo();
  }
  
  /**
   * Return the number of shotsLeft left for the round
   */
  public int getShotsLeft() {
    return base.getShotsLeft();
  }
  
  /**
   * Return the number of attachments in a weapon + 1
   */
  public int getNumAttachments() {
    return base.getNumAttachments() + 1;
  }
  
  /**
   * Reload the clips of the weapon
   */
  public void reload() {
    base.reload();
  }
  
  /**
   * Updates shots left each round
   * @param time
   */
  public void updateTime(int time) {
    if (time >= 0) {
      base.updateTime(time);
    } else {
      System.out.println("bad");
    }
  }
}
