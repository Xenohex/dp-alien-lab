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
  
  
}
