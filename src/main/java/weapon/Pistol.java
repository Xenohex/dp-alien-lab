package weapon;

/**
 * @author Christian Spitler
 *
 */
public class Pistol {//extends GenericWeapon {

  int maxRange = 50;
  int baseDamage = 10;
  int rateOfFire = 2;
  int maxAmmo = 10;
  
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
    int damage = Math.round(
        baseDamage * ((maxRange - distance) + 10) / maxRange);
    return damage;
  }
  
  public String toString() {
    return "Pistol";//+attachments
  }
}
