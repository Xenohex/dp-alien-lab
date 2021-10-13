package weapon;

/**
 * @author Christian Spitler
 *
 */
public class ChainGun {

  int baseDamage = 15;
  int maxRange = 60;
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
    int damage = Math.round(baseDamage*(distance/maxRange));
    return damage;
  }

  public String toString() {
    return "Chain Gun"; //+ attachments;
  }
  
}
