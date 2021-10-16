package weapon;

import static org.junit.Assert.*;

import org.junit.Test;

public class testChainGun {

  @Test
  public void testFire() {
    var gun = new ChainGun();
    assertEquals(15*(60/60),gun.fire(60));
    assertEquals(15/4,gun.fire(15));
  }
  
  @Test
  public void testTosString() {
    var gun = new ChainGun();
    assertEquals("Chain Gun", gun.toString());
  }
  
  @Test
  public void fireTooFar() {
    var gun = new ChainGun();
    assertEquals(0,gun.fire(70));
  }
  
  @Test
  public void fireNoAmmo() {
    var gun = new ChainGun();
    gun.currentAmmo = 2;
    gun.fire(5);
    gun.fire(90);
    assertEquals(gun.currentAmmo,0);
    assertEquals(0,gun.fire(5));
  }

}
