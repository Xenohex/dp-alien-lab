package weapon;

import static org.junit.Assert.*;

import org.junit.Test;

import exceptions.AttachmentException;
import exceptions.WeaponException;
import gameplay.SimpleTimer;

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
    SimpleTimer t = new SimpleTimer();
    t.addTimeObserver(gun);
    t.addTimeObserver(gun2);
    gun.updateTime(99);
    gun2.updateTime(99);
    assertEquals(50 * (4 / 4),gun.fire(10));
    gun.updateTime(99);
    assertEquals(50 * (4 / 4),gun2.fire(35));
    gun2.updateTime(99);
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
    SimpleTimer t = new SimpleTimer();
    t.addTimeObserver(gun);
    gun.updateTime(99);
    assertEquals(62,gun.fire(10)); // 50 * (4/4) * 1.25
    gun.updateTime(99);
    assertEquals(46,gun.fire(10)); // 50 * (3/4) * 1.25
    gun.updateTime(99);
    assertEquals(31,gun.fire(10)); // 50 * (2/4) * 1.25
    gun.updateTime(99);
    
    assertEquals(1, gun.getCurrentAmmo());
    assertEquals(15,gun.fire(10)); // 50 * (1/4) * 1.25
    gun.updateTime(99);
    assertEquals(4, gun.getCurrentAmmo());
    assertEquals(62,gun.fire(10)); // 50 * (4/4) * 1.25
  }
  
  @Test
  public void testPistolStabilizerScope() throws WeaponException, AttachmentException {
    var gun = new Scope(new Stabilizer(new Pistol()));
    SimpleTimer t = new SimpleTimer();
    t.addTimeObserver(gun);
    gun.updateTime(99);
    assertEquals(22, gun.fire(10)); 
    assertEquals(7, gun.fire(60));
  }
  
  @Test
  public void testPlasmaCannonStabilizerStabilizer() throws WeaponException, AttachmentException {
    var gun = new Stabilizer(new Stabilizer(new PlasmaCannon()));
    SimpleTimer t = new SimpleTimer();
    t.addTimeObserver(gun);
    gun.updateTime(99);
    assertEquals(77, gun.fire(10)); // (50 * (4 / 4) * 1.25) * 1.25 rounded down after each step
    gun.updateTime(99);
    assertEquals(57, gun.fire(10)); // (50 * (3 / 4) * 1.25) * 1.25
  }
  
  @Test 
  public void testChainGunPowerBoosterStabilizer() throws WeaponException, AttachmentException {
    var gun = new Stabilizer(new PowerBooster(new ChainGun()));
    SimpleTimer t = new SimpleTimer();
    t.addTimeObserver(gun);
    gun.updateTime(99);
    assertEquals(5, gun.fire(10)); 
    assertEquals(28, gun.fire(50)); 
  }
  
}

