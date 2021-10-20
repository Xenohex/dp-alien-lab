package weapon;

import static org.junit.Assert.*;

import org.junit.Test;

import exceptions.WeaponException;
import gameplay.SimpleTimer;
import weapon.PlasmaCannon;

public class testPlasmaCannon {

  @Test
  public void testFire() throws WeaponException {
    var gun = new PlasmaCannon();
    var gun2 = new PlasmaCannon();
    SimpleTimer t = new SimpleTimer();
    t.addTimeObserver(gun);
    gun.updateTime(1);
    assertEquals(50*(4/4),gun.fire(10));
    gun2.updateTime(2);
    assertEquals(50*(4/4),gun2.fire(35));
  }

  @Test
  public void testFireTooFar() throws WeaponException {
    var gun = new PlasmaCannon();
    SimpleTimer t = new SimpleTimer();
    t.addTimeObserver(gun);
    gun.updateTime(1);
    assertEquals(0, gun.fire(41));
  }
  @Test
  public void testTosString() {
    var gun = new PlasmaCannon();
    assertEquals("PlasmaCannon", gun.toString());
  }
  
}
