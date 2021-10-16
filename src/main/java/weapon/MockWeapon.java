package weapon;

/**
 * Mock class to test GenericWeapon
 * @author Bader
 *
 */
public class MockWeapon extends GenericWeapon{

  /**
   * Constructor for a MockWeapon
   */
  public MockWeapon(int ammo, int max, int rate, int range) {
    maxAmmo = max;
    currentAmmo = ammo;
    rateOfFire = rate;
    maxRange = range;
  }
  
  /**
   * Fire in this case just decrements currentAmmo
   */
  @Override
  public int fire(int distance) {
    int damage = 0;
    if (currentAmmo > 0 && distance < maxRange) {
      currentAmmo--;
      damage++;
    } else if (currentAmmo > 0 && distance > maxRange) {
      currentAmmo--;
    }
    return damage;
  }
}
