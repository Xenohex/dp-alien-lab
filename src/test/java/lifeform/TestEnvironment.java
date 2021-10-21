package lifeform;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import environment.Environment;
import weapon.Pistol;
import weapon.Weapon;

/**
 * Test all cases for environment class
 * 
 * @author Christian Spitler - lab 5; jun - up to lab 4
 *
 */
public class TestEnvironment {
  
  //-------lab5 tests -------------------------
  
  @Test
  public void testEnvironmentInitializationSingle() {
    Environment environment = Environment.getInstance(1, 1);
    assertNull(environment.getLifeForm(0, 0));
    Environment environment2 = Environment.getInstance(3, 3);
    LifeForm bob = new MockLifeForm("bob", 40);
    assertFalse(environment.addLifeForm(bob, 2, 2));
  }
  
  @Test
  public void addWeapon() {
    Environment environment = Environment.getInstance(4, 4);
    Weapon pistol = new Pistol();
   // assertTrue(environment.addWeapon(pistol));
    //assertFalse(environment)
    
  }
  
  
  
  //----------Decorator/Lab4 tests---------------
  
  /**
   * test to make sure that the cells are initialized correctly
   */
  @Test
  public void testEnvironmentInitialization() {
    Environment environment = Environment.getInstance(1, 1);
    assertNull(environment.getLifeForm(0, 0));
  }

  /**
   * test add and checks such FifeForm exists
   */
  @Test
  public void testAddLifeForm() {
    Environment environment = Environment.getInstance(2, 3);
    LifeForm bob = new MockLifeForm("Bob", 40);
    assertTrue(environment.addLifeForm(bob, 0, 1));
    assertEquals("Bob", environment.getLifeForm(0, 1).getName());
  }

  /**
   * test to make sure the program is unable to add the LifeForm if the given
   * position is out of the 2d array (positive)
   */
  @Test
  public void testAddLifeFormBigNum() {
    Environment environment = Environment.getInstance(2, 3);
    LifeForm bob = new MockLifeForm("Bob", 40);
    assertFalse(environment.addLifeForm(bob, 3, 4));
  }

  /**
   * test to make sure the program is unable to add the LifeForm if the given
   * position is out of the 2d array (negative)
   */
  @Test
  public void testAddLifeFormNegNum() {
    Environment environment = Environment.getInstance(2, 3);
    LifeForm bob = new MockLifeForm("Bob", 40);
    assertFalse(environment.addLifeForm(bob, -1, -5));
  }

  /**
   * test to make sure the program is unable to add the LifeForm if the given
   * position already has a LifeForm
   */
  @Test
  public void testLifeFormtoCellContainingLifeForm() {
    Environment environment = Environment.getInstance(2, 3);
    LifeForm bob = new MockLifeForm("Bob", 40);
    LifeForm dude = new MockLifeForm("Dude", 60);
    assertTrue(environment.addLifeForm(bob, 1, 2));
    assertFalse(environment.addLifeForm(dude, 1, 2));
  }

  /**
   * test to make sure the program able to remove a LifeForm and making sure it is
   * null
   */
  @Test
  public void testRemoveLifeForm() {
    Environment environment = Environment.getInstance(2, 3);
    LifeForm bob = new MockLifeForm("Bob", 40);
    assertTrue(environment.addLifeForm(bob, 1, 2));
    environment.removeLifeForm(1, 2);
    assertNull(environment.getLifeForm(1, 2));
  }
}
