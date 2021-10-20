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

// PowerBooster = baseDamage * (1 + (currentAmmo / maxAmmo))

// ChainGun = baseDamage * (distance / maxRange)

public class TestPowerBooster {
  
  @Test
  public void testChainGunNoAttachments() throws WeaponException, AttachmentException {
    var gun = new ChainGun();
    SimpleTimer t = new SimpleTimer();
    t.addTimeObserver(gun);
    gun.updateTime(1);
    assertEquals(15,gun.fire(60)); // 15 * (60 / 60)
    assertEquals(3,gun.fire(15)); // 15 * (15 / 60) rounds down
  }
  
  @Test
  public void testChainGunThreeAttachments() throws WeaponException, AttachmentException {
    try {
      var gun = new ChainGun();
      var gun1 = new Scope(gun);
      var gun2 = new Scope(gun1);
      var gun3 = new Scope(gun2);
      fail();
    } catch (AttachmentException e) {
      // This try statement should catch an AttachmentException on line 34.
    }
  }
  
  @Test
  public void testChainGunPowerBooster() throws WeaponException, AttachmentException {
    var gun = new PowerBooster(new ChainGun());
    SimpleTimer t = new SimpleTimer();
    t.addTimeObserver(gun);
    gun.updateTime(99);
    assertEquals(30, gun.fire(60)); // 15 * (60 / 60) * (1 + (40 / 40))
    assertEquals(9, gun.fire(20)); // (15 * (20 / 60)) * (1 + (39 / 40))
    
  }
  
  @Test
  public void testChainGunPowerBoosterScope() throws WeaponException, AttachmentException {
    var gun = new Scope(new PowerBooster(new ChainGun()));
    SimpleTimer t = new SimpleTimer();
    t.addTimeObserver(gun);
    gun.updateTime(99);
    assertEquals(35, gun.fire(70)); // 15 * (70 / 70) * (1 + (40 / 40)) + 5
    assertEquals(15, gun.fire(20)); // (15 * (20 / 60)) * (1 + (39 / 40)) * (1 + ((70 - 20) / 70))
  }
  
  @Test
  public void testChainGunPowerBoosterStabilizer() throws WeaponException, AttachmentException {
    var gun = new Stabilizer(new PowerBooster(new ChainGun()));
    SimpleTimer t = new SimpleTimer();
    t.addTimeObserver(gun);
    gun.updateTime(99);
    assertEquals(37, gun.fire(60)); // 15 * (60 / 60) * (1 + (40 / 40)) * 1.25
    for (int x = gun.getCurrentAmmo(); x > 1; x--) {
      gun.fire(60);
      gun.updateTime(99); // depletes ammo to test stabilizer reload function
      System.out.println(x + " | " + gun.getCurrentAmmo());
    }
    assertEquals(18, gun.fire(60)); // 15 * (60 / 60) * (1 + (1 / 40)) * 1.25
    gun.updateTime(99);
    assertEquals(37, gun.fire(60)); // 15 * (60 / 60) * (1 + (40 / 40)) * 1.25
  }
  
  @Test
  public void testChainGunPowerBoosterPowerBooster() throws WeaponException, AttachmentException {
    var gun = new PowerBooster(new PowerBooster(new ChainGun()));
    SimpleTimer t = new SimpleTimer();
    t.addTimeObserver(gun);
    gun.updateTime(99);
    assertEquals(60, gun.fire(60)); // 15 * (60 / 60) * (1 + (40 / 40)) * (1 + (40 / 40))
    assertEquals(57, gun.fire(60));
  }
  
}