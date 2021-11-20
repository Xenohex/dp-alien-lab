package lifeform;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Test;

import exceptions.RecoveryRateException;
import gameplay.SimpleTimer;
import recovery.RecoveryLinear;

/**
 * Test the functionalities of Alien
 * @author Jun
 *
 */
public class TestAlien {
  
  //---------------------Lab 7 Tests-----------------------------------//
  /**
   * Test maxLifePoints of Alien
   * @throws RecoveryRateException 
   */
  @Test
  public void testmaxLifePoints() throws RecoveryRateException {
    Alien entity = new Alien("Bob",100);
    entity.takeHit(20);
    entity.takeHit(5);
    assertEquals(100, entity.getMaxLifePoints());
  }

  //-----------------------Lab6 tests-----------------------------------------------
  /**
   * Test the maxSpeed of an Alien
   * @throws RecoveryRateException
   */
  @Test
  public void testMaxSpeed() throws RecoveryRateException {
    Alien entity = new Alien("bob", 100);
    assertEquals(2, entity.getMaxSpeed());
  }
  
  
  
  //---------------------- preLab6 tests---------------------------------
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
    RecoveryLinear r1 = new RecoveryLinear(3);
    Alien a = new Alien("a", 100, r1);
    a.takeHit(70);
    a.recover();
//    a.currentLifePoints = a.rb.calculateRecovery(a.currentLifePoints, a.maxLifePoints);
    assertEquals(33, a.getCurrentLifePoints());
  }

  /**
   * default attack of 10
   * @throws RecoveryRateException should not happen
   */
  @Test
  public void defaultAttackStrength() throws RecoveryRateException {
    Alien bob = new Alien("bob", 20);
    assertEquals(bob.getAttackStrength(),10);
  }
  
  /**
   * makes sure that alien works with timer implemented
   * and recovers every round when damaged
   * @throws RecoveryRateException
   */
  @Test
  public void testRecoverRateAtZero() throws RecoveryRateException {
    RecoveryLinear rl = new RecoveryLinear(2);
    Alien bob = new Alien("bob", 100, rl, 0);
    assertEquals(bob.getRecoveryRate(), 0);
    SimpleTimer st = new SimpleTimer();
    st.addTimeObserver(bob);
    bob.takeHit(25);
    assertEquals(bob.getCurrentLifePoints(),75);
    st.timeChanged();
    assertEquals(bob.getCurrentLifePoints(),77);
    st.timeChanged();
    assertEquals(bob.getCurrentLifePoints(),79);
  }
  
  /**
   * makes sure that alien works with timer implemented
   * and recovers every 2 round when damaged
   * @throws RecoveryRateException
   */
  @Test
  public void testRecoverRateAtTwo() throws RecoveryRateException {
    RecoveryLinear rl = new RecoveryLinear(2);
    Alien bob = new Alien("bob", 100, rl, 2);
    assertEquals(bob.getRecoveryRate(), 2);
    SimpleTimer st = new SimpleTimer();
    st.addTimeObserver(bob);
    bob.takeHit(25);
    assertEquals(bob.getCurrentLifePoints(),75);
    st.timeChanged();
    assertEquals(bob.getCurrentLifePoints(),75);
    st.timeChanged();
    assertEquals(bob.getCurrentLifePoints(),77);
  }
  
  /**
   * makes sure that alien works with timer implemented
   * and recovers every 3 round when damaged
   * @throws RecoveryRateException
   */
  @Test
  public void testRecoverRateAtThree() throws RecoveryRateException {
    RecoveryLinear rl = new RecoveryLinear(2);
    Alien bob = new Alien("bob", 100, rl, 3);
    assertEquals(bob.getRecoveryRate(), 3);
    SimpleTimer st = new SimpleTimer();
    st.addTimeObserver(bob);
    bob.takeHit(25);
    assertEquals(bob.getCurrentLifePoints(),75);
    st.timeChanged();
    assertEquals(bob.getCurrentLifePoints(),75);
    st.timeChanged();
    assertEquals(bob.getCurrentLifePoints(),75);
    st.timeChanged();
    assertEquals(bob.getCurrentLifePoints(),77);
  }
  
  /**
   * makes sure that each observer is notified with the new
   * round
   * @throws RecoveryRateException
   */
  @Test
  public void testTrackPassedTime() throws RecoveryRateException {
    RecoveryLinear rl = new RecoveryLinear(2);
    Alien bob = new Alien("bob", 100, rl, 3);
    SimpleTimer st = new SimpleTimer();
    st.addTimeObserver(bob);
    bob.takeHit(25);
    assertEquals(bob.currentRound, 0);
    st.timeChanged();
    assertEquals(bob.currentRound, 1);
  }
  
  /**
   * makes sure the removed observer does not get updated 
   * @throws RecoveryRateException
   */
  @Test
  public void testNoRecoveryWhenRemoved() throws RecoveryRateException {
    RecoveryLinear rl = new RecoveryLinear(2);
    Alien bob = new Alien("bob", 100, rl, 0);
    SimpleTimer st = new SimpleTimer();
    st.addTimeObserver(bob);
    bob.takeHit(25);
    assertEquals(bob.getCurrentLifePoints(),75);
    st.timeChanged();
    assertEquals(bob.getCurrentLifePoints(),77);
    st.removeTimeObserver(bob);
    st.timeChanged();
    assertEquals(bob.getCurrentLifePoints(),77);
  }
  
  /**
   * a RecoveryRateException should be thrown when aliens are
   * initialized with an invalid recovery rate
   */
  @Test
  public void testInvalidRecoveryRate() {
    try {
      RecoveryLinear rl = new RecoveryLinear(2);
      Alien bob = new Alien("bob", 100, rl, -10);
      fail();
    } catch (RecoveryRateException e) {
      //this is expected
    }
  }
}
