package weapon;

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
  public int fire(int distance) {
    if(distance > maxRange) {  
      currentAmmo = Math.max(currentAmmo - 1, 0);
      shotsLeft--;
      return 0;
    } else if (currentAmmo == 0) {
        currentAmmo = Math.max(currentAmmo - 1, 0);
        return 0;
    } else {
    int damage = Double.valueOf(Math.floor(
        baseDamage * ((maxRange - distance) + 10) / maxRange)).intValue();
    currentAmmo--;
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
