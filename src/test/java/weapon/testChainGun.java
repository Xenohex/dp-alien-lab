package weapon;

import static org.junit.Assert.*;

import org.junit.Test;

public class testChainGun {

  @Test
  public void testFire() {
    var gun = new ChainGun();
    assertEquals(15*(60/60),gun.fire(60));
    assertEquals(15*(15/60),gun.fire(15));
  }

}
