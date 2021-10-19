package weapon;

import exceptions.WeaponException;

/**
 * @author Christian Spitler
 *
 */
public class PlasmaCannon extends GenericWeapon { 

  
  /**
   * Constructor for PlasmaCannon
   */
  public PlasmaCannon() {
    baseDamage = 50;
    maxRange = 40;
    rateOfFire = 1;
    maxAmmo = 4;
    currentAmmo = 4;
    shotsLeft = rateOfFire;
  }
  
  /**
   * @param distance
   * @return the damage the weapon deals to the target, 
   * the target's armor may mitigate this damage. The 
   * damage will be zero if a) the target is out of 
   * range (the ammo is still used in this case) OR b)
   *  the weapon was out of ammo (ammo cannot be 
   *  negative)
   */
  public int fire(int distance) throws WeaponException {
    if (distance < 0) {
      throw new WeaponException("Given Distance is negative!!!");
    }
    if (currentAmmo == 0 || shotsLeft == 0) {
      return 0;
    } else if (distance > maxRange) { 
      currentAmmo = Math.max(currentAmmo - 1, 0);
      shotsLeft--;
      return 0;
    } else { 
      int damage = Double.valueOf(Math.floor(
          baseDamage * currentAmmo / maxAmmo)).intValue();
      shotsLeft--;
      currentAmmo--;
      return damage;
    }
  }
  
  /**
   * @return string of Plasma Cannon
   */
  public String toString() {
    return "PlasmaCannon";//+attachments
  }
}

