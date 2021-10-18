package weapon;

import static org.junit.Assert.*;

import org.junit.Test;

import exceptions.AttachmentException;
import exceptions.WeaponException;

/**
 * @author Austin Pliska
 *
 */

// Stabilizer = Increases damage by 25% (rounded down) and automatically reloads when empty

// PlasmaCannon = baseDamage * (currentAmmo / maxAmmo)

public class TestStabilizer {
  
  @Test
  public void testPlasmaCannonNoAttachments() throws WeaponException, AttachmentException {
    var gun = new PlasmaCannon();
    var gun2 = new PlasmaCannon();
    assertEquals(50 * (4 / 4),gun.fire(10));
    assertEquals(50 * (4 / 4),gun2.fire(35));
    assertEquals(37, gun.fire(10));
  }
  
  @Test
  public void testPlasmaCannonThreeAttachments() throws WeaponException, AttachmentException {
    try {
      var gun = new PlasmaCannon();
      var gun1 = new Scope(gun);
      var gun2 = new Scope(gun1);
      var gun3 = new Scope(gun2);
      fail();
    } catch (AttachmentException e) {
      // This try statement should catch an AttachmentException on line 36.
    }
  }
  
  @Test
  public void testPlasmaCannonStabilizer() throws WeaponException, AttachmentException {
    var gun = new Stabilizer(new PlasmaCannon());
    assertEquals(62,gun.fire(10)); // 50 * (4/4) * 1.25
    assertEquals(46,gun.fire(10)); // 50 * (3/4) * 1.25
    assertEquals(31,gun.fire(10)); // 50 * (2/4) * 1.25
    
    assertEquals(1, gun.getCurrentAmmo());
    assertEquals(15,gun.fire(10)); // 50 * (1/4) * 1.25
    assertEquals(4, gun.getCurrentAmmo());
    assertEquals(62,gun.fire(10)); // 50 * (4/4) * 1.25
  }
  
  @Test
  public void testPlasmaCannonStabilizerScope() throws WeaponException, AttachmentException {
    var gun = new Scope(new Stabilizer(new PlasmaCannon()));
    var gun2 = new Scope(new Stabilizer(new PlasmaCannon()));
    assertEquals(111, gun.fire(10)); // (50 * (4 / 4) * 1.25) * (1 + (50 - 10) / 50) rounded down after each step
    assertEquals(82, gun.fire(10)); // 50 * (3 / 4) * 1.25 * (1 + (50 - 10) / 50) 
    assertEquals(67, gun2.fire(45)); // (50 * (4 / 4) * 1.25) + 5
    assertEquals(51, gun2.fire(45)); // (50 * (3 / 4) * 1.25) + 5
  }
  
  @Test
  public void testPlasmaCannonStabilizerStabilizer() throws WeaponException, AttachmentException {
    var gun = new Stabilizer(new Stabilizer(new PlasmaCannon()));
    assertEquals(77, gun.fire(10)); // (50 * (4 / 4) * 1.25) * 1.25 rounded down after each step
    assertEquals(57, gun.fire(10)); // (50 * (3 / 4) * 1.25) * 1.25
  }
  
  @Test //TODO DOES NOT PASS LINE 54, SHOULD BE 81, RETURNS 46
  public void testPlasmaCannonStabilizerPowerBooster() throws WeaponException, AttachmentException {
    var gun = new PowerBooster(new Stabilizer(new PlasmaCannon()));
    assertEquals(124, gun.fire(10)); // (50 * (4 / 4) * 1.25) * (1 + (4 / 4)) rounded down after each step
    assertEquals(81, gun.fire(10)); // (50 * (3 / 4) * 1.25) * (1 + (3 / 4)) 
  }
  
}

