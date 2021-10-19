package weapon.weapon;

import static org.junit.Assert.*;

import org.junit.Test;

import exceptions.WeaponException;
import gameplay.SimpleTimer;
import weapon.Pistol;

public class testPistol {

  @Test
  public void testFire() throws WeaponException {
    var gun = new Pistol();
    SimpleTimer t = new SimpleTimer();
    t.addTimeObserver(gun);
    gun.updateTime(1);
    assertEquals(10*((50-25)+10)/50, gun.fire(25));
    assertEquals(10*((50-5)+10)/50, gun.fire(5));
  }
  
  
  @Test
  public void testTosString() {
    var gun = new Pistol();
    assertEquals("Pistol", gun.toString());
  }
  
  @Test
  public void fireTooFar() throws WeaponException {
    var gun = new Pistol();
    SimpleTimer t = new SimpleTimer();
    t.addTimeObserver(gun);
    gun.updateTime(1);
    assertEquals(0,gun.fire(70));
  }
  
  @Test
  public void fireNoAmmo() throws WeaponException {
    var gun = new Pistol();
    for (int i = 0; i < 10; i++) {
      SimpleTimer t = new SimpleTimer();
      t.addTimeObserver(gun);
      gun.updateTime(1);
      gun.fire(60);
    }
    gun.fire(5);
    gun.fire(90);
    assertEquals(gun.getCurrentAmmo(),0);
    assertEquals(0,gun.fire(5));
  }
}
