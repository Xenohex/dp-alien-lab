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

// Scope when within default range = Damage * (1 + ((ampedRange - distance) / ampedRange)
// Scope when within scope range = Damage at max default range + 5

// Pistol = baseDamage * ((maxRange - distance + 10) / 10)

public class TestScope {
  
  @Test
  public void testPistolNoAttachments() throws WeaponException, AttachmentException {
    var gun = new Pistol();
    SimpleTimer t = new SimpleTimer();
    t.addTimeObserver(gun);
    gun.updateTime(99);
    assertEquals(10 * ((50 - 25) + 10) / 50, gun.fire(25));
    assertEquals(10 * ((50 - 5) + 10) / 50, gun.fire(5));
  }
  
  @Test
  public void testPistolThreeAttachments() throws WeaponException, AttachmentException {
    try {
      var gun = new Pistol();
      var gun1 = new Scope(gun);
      var gun2 = new Scope(gun1);
      var gun3 = new Scope(gun2);
      fail();
    } catch (AttachmentException e) {
      // This try statement should catch an AttachmentException on line 35.
    }
  }
  
  @Test
  public void testPistolScopeLosesAmmo() throws WeaponException, AttachmentException {
    var gun = new Scope(new Pistol());
    SimpleTimer t = new SimpleTimer();
    t.addTimeObserver(gun);
    gun.updateTime(99);
    assertEquals(gun.getMaxAmmo(), gun.getCurrentAmmo());
    gun.fire(10);
    assertEquals(gun.getMaxAmmo() - 1, gun.getCurrentAmmo());
    gun.fire(999);
    assertEquals(gun.getMaxAmmo() - 2, gun.getCurrentAmmo());
  }
  
  @Test
  public void testPistolScope() throws WeaponException, AttachmentException {
    var gun = new Pistol();
    Scope gun2 = new Scope(gun);
    SimpleTimer t = new SimpleTimer();
    t.addTimeObserver(gun);
    t.addTimeObserver(gun2);
    gun.updateTime(99);
    gun2.updateTime(99);
    assertEquals(11, gun2.fire(25)); // 10 * ((50 - 25) + 10) / 50) * (1 + (60 - 25) / 60) * (1 + (70 - 25) / 70) rounded
    assertEquals(((10 * ((50 - 50) + 10) / 50) + 5), gun2.fire(55));
    assertEquals(0, gun2.fire(99));
  }
  
  @Test
  public void testPistolScopeScope() throws WeaponException, AttachmentException {
    var gun = new Scope(new Scope(new Pistol()));
    SimpleTimer t = new SimpleTimer();
    t.addTimeObserver(gun);
    gun.updateTime(99);
    assertEquals(18, gun.fire(25)); // 10 * ((50 - 25) + 10) / 50) * (1 + (60 - 25) / 60) * (1 + (70 - 25) / 70) rounded
    assertEquals(((10 * ((50 - 50) + 10) / 50) + 5 + 5), gun.fire(65));
    assertEquals(0, gun.fire(99));
  }
  
  @Test
  public void testPistolScopeStabilizer() throws WeaponException, AttachmentException {
    var gun = new Stabilizer(new Scope(new Pistol()));
    SimpleTimer t = new SimpleTimer();
    t.addTimeObserver(gun);
    gun.updateTime(99);
    assertEquals(13, gun.fire(25)); // (10 * ((50 - 25) + 10) / 50) * (1 + (60 - 25) / 60) * 1.25 = 13.85 rounded down
    for (int x = gun.getCurrentAmmo(); x > 1; x--) {
      System.out.println(gun.getCurrentAmmo());
      gun.fire(25);
      gun.updateTime(99-x);
    }
    assertEquals(1, gun.getCurrentAmmo());
    gun.fire(25);
    assertEquals(10, gun.getCurrentAmmo());
    assertEquals(0, gun.fire(99));
    assertEquals(9, gun.getCurrentAmmo());
  }
  
  @Test
  public void testPistolScopePowerBooster() throws WeaponException, AttachmentException {
    var gun = new PowerBooster(new Scope(new Pistol()));
    SimpleTimer t = new SimpleTimer();
    t.addTimeObserver(gun);
    gun.updateTime(99);
    assertEquals(22, gun.fire(25)); // pistol damage with just scope * stabilizer boost (1 + currentAmmo / maxAmmo) rounded
    assertEquals(20, gun.fire(25));
  }
  
  @Test
  public void testChainGunPowerBoosterScope() throws WeaponException, AttachmentException {
    var gun = new PowerBooster(new Scope(new ChainGun()));
    SimpleTimer t = new SimpleTimer();
    t.addTimeObserver(gun);
    gun.updateTime(99);
    assertEquals(16,gun.fire(20));
    assertEquals(39,gun.fire(70));
  }
  
  @Test
  public void testPlasmaCannonStabilizerScope() throws WeaponException, AttachmentException {
    var gun = new PowerBooster(new Scope(new ChainGun()));
    SimpleTimer t = new SimpleTimer();
    t.addTimeObserver(gun);
    gun.updateTime(99);
    assertEquals(18,gun.fire(25));
    assertEquals(29,gun.fire(50));
  }
}
