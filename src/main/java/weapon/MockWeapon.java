package weapon;

import exceptions.WeaponException;

/**
 * Mock class to test GenericWeapon
 * @author Bader
 *
 */
public class MockWeapon extends GenericWeapon {
  
  /**
   * Constructor for a MockWeapon
   */
  public MockWeapon() {
    baseDamage = 5;
    maxRange = 40;
    rateOfFire = 3;
    maxAmmo = 20;
    currentAmmo = 20;
    shotsLeft = 3;
  }
  
  /**
   * @return string of MockWeapon
   */
  public String toString() {
    return "MockWeapon";
  } 
  
  /**
   * Fire in this case just decrements currentAmmo
   * @throws WeaponException 
   */
  @Override
  public int fire(int distance) throws WeaponException {
    if (distance < 0) {
      throw new WeaponException("Distance is less than zero!");
    }
    if (distance > maxRange) {  
      currentAmmo = Math.max(currentAmmo - 1, 0);
      shotsLeft--;
      return 0;
    } else if (currentAmmo == 0 || shotsLeft == 0) {
      currentAmmo = Math.max(currentAmmo, 0);
      return 0;
    } else { 
      int damage = baseDamage;
      currentAmmo--;
      return damage;
    }
  }
}
