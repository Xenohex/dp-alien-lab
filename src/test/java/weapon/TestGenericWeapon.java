package weapon;

import static org.junit.Assert.*;

import org.junit.Test;

import exceptions.WeaponException;
import gameplay.SimpleTimer;
import weapon.MockWeapon;

/**
 * Tests for GenericWeapon
 * @author Bader
 *
 */
public class TestGenericWeapon {

  @Test
  public void testUpdateTime() throws WeaponException {
    SimpleTimer t = new SimpleTimer();
    MockWeapon weapon = new MockWeapon();
    t.addTimeObserver(weapon);
    weapon.fire(0);
    t.timeChanged();
    assertEquals(3, weapon.getShotsLeft()); 
  }
  @Test
  public void testFire() throws WeaponException {
    MockWeapon weapon = new MockWeapon();
    weapon.fire(15);
    assertEquals(19, weapon.getCurrentAmmo()); 
  }
  @Test
  public void testNoAmmo() throws WeaponException {
    MockWeapon weapon = new MockWeapon();
    for(int i = 0; i < 20; i++) {
      weapon.fire(10);
    }
    assertEquals(0, weapon.fire(10)); 
  }
  @Test
  public void testMaxRange() throws WeaponException {
    MockWeapon weapon = new MockWeapon();
    assertEquals(0, weapon.fire(50)); 
    assertEquals(19, weapon.getCurrentAmmo()); 
  }
  @Test
  public void testReload() {
    MockWeapon weapon = new MockWeapon();
    weapon.reload();
    assertEquals(20, weapon.getCurrentAmmo()); 
  }
}
