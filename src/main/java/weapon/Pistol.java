package weapon;

/**
 * @author Christian Spitler
 *
 */
public class Pistol {

  int MaxRange = 50;
  int BaseDamage = 10;
  int RateOfFire = 2;
  int MaxAmmo = 10;
  
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
    int damage = Math.round
        (BaseDamage*((MaxRange - distance) + 10)/MaxRange);
    return damage;
  }
  
}
