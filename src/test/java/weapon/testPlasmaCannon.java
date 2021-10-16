package weapon;

import static org.junit.Assert.*;

import org.junit.Test;

public class testPlasmaCannon {

  @Test
  public void testFire() {
    var gun = new PlasmaCannon();
    var gun2 = new PlasmaCannon();
    assertEquals(50*(4/4),gun.fire(10));
    assertEquals(50*(4/4),gun2.fire(35));
  }

  
  @Test
  public void testTosString() {
    var gun = new PlasmaCannon();
    assertEquals("Plasma Cannon", gun.toString());
  }
  
}