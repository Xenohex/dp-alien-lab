package lifeform;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import exceptions.RecoveryRateException;

/**
 * test all cases for human class
 *
 */
public class TestHuman {
  
  //-----------------------Lab6 tests-----------------------------------------------
  /**
   * Test the maxSpeed of an Alien
   * @throws RecoveryRateException
   */
  @Test
  public void testMaxSpeed(){
    Human entity = new Human("Bob", 100, 0);
    assertEquals(3, entity.getMaxSpeed());
  }
  
  
  
  //---------------------- preLab6 tests---------------------------------
  /**
   * test to make sure that each human is successfully initialized with the right
   * name, LifePoints, and armor value
   */
  @Test
  public void testInitialization() {
    Human bob = new Human("bob", -2, -2);
    assertEquals("bob", bob.getName());
    assertEquals(0, bob.getCurrentLifePoints());
    assertEquals(0, bob.getArmorPoints());
  }

  /**
   * test to make sure that armor value cannot be less than 0
   */
  @Test
  public void testArmorPointsLessThanZero() {
    Human bob = new Human("bob", 20, -25);
    assertEquals(0, bob.getArmorPoints());
  }

  /**
   * default attack of 5
   */
  @Test
  public void defaultAttackStrength() {
    Human bob = new Human("bob", 20, 5);
    assertEquals(bob.getAttackStrength(),5);
  }
  
  /**
   * takes damage normally when armor = 0
   */
  @Test
  public void zeroArmor() {
    Human bob = new Human("bob", 20, 0);
    bob.takeHit(10);
    assertEquals(bob.getCurrentLifePoints(), 10);
  }
  
  /**
   * absorb all damage when armor > damage
   */
  @Test
  public void moreArmorThanDamage() {
    Human bob = new Human("bob", 20, 10);
    bob.takeHit(5);
    assertEquals(bob.getCurrentLifePoints(), 20);
  }
  
  /**
   * reduces damage taken when armor < damage
   */
  @Test
  public void lessArmorThanDamage() {
    Human bob = new Human("bob", 20, 10);
    bob.takeHit(15);
    assertEquals(bob.getCurrentLifePoints(), 15);
  }
  
  /**
   * no damage taken when armor = damage
   */
  @Test
  public void sameArmorThanDamage() {
    Human bob = new Human("bob", 20, 10);
    bob.takeHit(10);
    assertEquals(bob.getCurrentLifePoints(), 20);
  }
}
