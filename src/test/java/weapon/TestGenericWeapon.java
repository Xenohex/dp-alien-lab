package weapon;

import static org.junit.Assert.*;

import org.junit.Test;

import gameplay.SimpleTimer;

/**
 * Tests for GenericWeapon
 * @author Bader
 *
 */
public class TestGenericWeapon {

  @Test
  public void testUpdateTime() {
    SimpleTimer t = new SimpleTimer();
    MockWeapon weapon = new MockWeapon(30, 50, 3, 20);
    t.addTimeObserver(weapon);
    weapon.fire(0);
    t.timeChanged();
    assertEquals(3, weapon.getShotsLeft()); 
  }
  @Test
  public void testFire() {
    MockWeapon weapon = new MockWeapon(30, 50, 3, 20);
    weapon.fire(15);
    assertEquals(29, weapon.getCurrentAmmo()); 
  }
  @Test
  public void testNoAmmo() {
    MockWeapon weapon = new MockWeapon(0, 50, 3, 20);
    assertEquals(0, weapon.fire(15)); 
  }
  @Test
  public void testMaxRange() {
    MockWeapon weapon = new MockWeapon(10, 50, 3, 20);
    assertEquals(0, weapon.fire(30)); 
    assertEquals(9, weapon.getCurrentAmmo()); 
  }
  @Test
  public void testReload() {
    MockWeapon weapon = new MockWeapon(0, 50, 3, 20);
    weapon.reload();
    assertEquals(50, weapon.getCurrentAmmo()); 
  }

}
