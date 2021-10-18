package weapon;

import exceptions.WeaponException;

/**
 * @author Christian Spitler
 *
 */
public class Pistol extends GenericWeapon { 

  
  
  /**
   * Constructor for Pistol
   */
  public Pistol() {
    maxRange = 50;
    baseDamage = 10;
    rateOfFire = 2;
    maxAmmo = 10;
    currentAmmo = 10;
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
    if (distance > maxRange) { 
      currentAmmo = Math.max(currentAmmo - 1, 0);
      shotsLeft--;
      return 0;
    } else if (currentAmmo == 0 || shotsLeft == 0) {
      currentAmmo = Math.max(currentAmmo, 0);
      return 0;
    } else {
      int damage = Double.valueOf(Math.floor(
      baseDamage * ((maxRange - distance) + 10) / maxRange)).intValue();
      currentAmmo--;
      shotsLeft--;
      return damage;
    }
  }
  
  /**
   * @return string of pistol
   */
  public String toString() {
    return "Pistol"; //+attachments
  }
}
