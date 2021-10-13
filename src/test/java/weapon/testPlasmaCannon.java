package weapon;

import static org.junit.Assert.*;

import org.junit.Test;

public class testPlasmaCannon {

  @Test
  public void testFire() {
    var gun = new PlasmaCannon();
    assertEquals(50*(4/4),gun.fire(10));
    assertEquals(50*(4/4),gun.fire(35));
  }

}
