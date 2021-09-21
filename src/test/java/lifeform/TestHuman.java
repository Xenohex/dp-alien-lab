package lifeform;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * test all cases for human class
 *
 */
public class TestHuman {
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

}
