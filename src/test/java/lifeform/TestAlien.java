package lifeform;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import exceptions.RecoveryRateException;
import recovery.RecoveryLinear;

/**
 * Test the functionalities of Alien
 * @author Jun
 *
 */
public class TestAlien {

  /**
   * This is to ensure the name and life points is set correctly when initializing
   * 
   * @throws RecoveryRateException should not be thrown
   */
  @Test
  public void testInitialization() throws RecoveryRateException {
    Alien a = new Alien("a", 100);
    assertEquals("a", a.getName());
    assertEquals(100, a.getCurrentLifePoints());
  }

  /**
   * This is to test the linear Recovery ability that an Alien can have
   * 
   * @throws RecoveryRateException should not be thrown
   */
  @Test
  public void linearRecovery() throws RecoveryRateException {
    RecoveryLinear r1 = new RecoveryLinear(0);
    Alien a = new Alien("a", 100, r1);
    a.takeHit(70);
    a.recover();
//    a.currentLifePoints = a.rb.calculateRecovery(a.currentLifePoints, a.maxLifePoints);
    assertEquals(60, a.getCurrentLifePoints());
  }

}
