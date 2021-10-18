package weapon;

import static org.junit.Assert.*;

import org.junit.Test;

import exceptions.AttachmentException;
import exceptions.WeaponException;

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
    assertEquals(30, gun.fire(60)); // 15 * (60 / 60) * (1 + (40 / 40))
    assertEquals(9, gun.fire(20)); // (15 * (20 / 60)) * (1 + (39 / 40))
    
  }
  
  @Test
  public void testProblem() throws WeaponException, AttachmentException {
    var gun2 = new PowerBooster(new ChainGun());
    var gun3 = new ChainGun();
    
    // assertEquals(gun2.fire(60), gun3.fire(60));
    
    gun2.fire(60); gun3.fire(60);
    assertEquals(gun2.fire(60), gun3.fire(60));
    assertEquals(gun2.fire(15), gun3.fire(15));
  }
  
  @Test
  public void testChainGunPowerBoosterScope() throws WeaponException, AttachmentException {
    var gun = new Scope(new PowerBooster(new ChainGun()));
  }
  
  @Test
  public void testChainGunPowerBoosterStabilizer() throws WeaponException, AttachmentException {
    var gun = new Stabilizer(new PowerBooster(new ChainGun()));
  }
  
  @Test
  public void testChainGunPowerBoosterPowerBooster() throws WeaponException, AttachmentException {
    var gun = new PowerBooster(new PowerBooster(new ChainGun()));
  }
  
}