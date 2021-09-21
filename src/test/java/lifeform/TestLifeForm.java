package lifeform;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * Tests the functionality provided by the LifeForm class
 *
 */
public class TestLifeForm {

  /**
   * When a LifeForm is created, it should know its name and how many life points
   * it has.
   */
  @Test
  public void testInitialization() {
    LifeForm entity;
    entity = new MockLifeForm("Bob", 40);
    assertEquals("Bob", entity.getName());
    assertEquals(40, entity.getCurrentLifePoints());
  }

  /**
   * Test that the LifeForm can take hits and it's health will not go below 0
   */
  @Test
  public void testTakeHits() {
    LifeForm entity;
    entity = new MockLifeForm("Bob", 40);
    entity.takeHit(20);
    assertEquals(20, entity.getCurrentLifePoints());
    entity.takeHit(30);
    assertEquals(0, entity.getCurrentLifePoints());
  }
}
