package weapon;

import static org.junit.Assert.*;

import org.junit.Test;

public class testPistol {

  @Test
  public void testFire() {
    var gun = new Pistol();
    assertEquals(10*((50-25)+10)/50, gun.fire(25));
    assertEquals(10*((50-5)+10)/50, gun.fire(5));
  }
  
  
  @Test
  public void testTosString() {
    var gun = new Pistol();
    assertEquals("Pistol", gun.toString());
  }
  
  @Test
  public void fireTooFar() {
    var gun = new Pistol();
    assertEquals(0,gun.fire(70));
  }
  
  @Test
  public void fireNoAmmo() {
    var gun = new Pistol();
    gun.currentAmmo = 2;
    gun.fire(5);
    gun.fire(90);
    assertEquals(gun.currentAmmo,0);
    assertEquals(0,gun.fire(5));
  }
}
