package weapon;

/**
 * @author Christian Spitler
 *
 */
public class PlasmaCannon {

  
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
    //return baseDamage*(actualAmmo/maxAmmo);
    return 50;
  }
  
  public String toString( ) {
    return "Plasma Cannon";//+attachments
  }
}

