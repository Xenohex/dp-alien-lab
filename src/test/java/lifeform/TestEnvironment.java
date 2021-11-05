package lifeform;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;

import environment.Environment;
import exceptions.EnvironmentException;
import weapon.ChainGun;
import weapon.Pistol;
import weapon.Weapon;

/**
 * Test all cases for environment class
 * 
 * @author Christian Spitler lab 5; jun up to lab 4
 *
 */
public class TestEnvironment {
  private int rows;
  private int cols;
  private Environment e;
  //-------lab5 tests -------------------------
  
  /**
   * 
   */
  @Before 
  public void resetEnvironment() {
    Environment e = Environment.getEnvironment(6, 6);
    rows = 6;
    cols = 6;
    e.clearBoard();
  }
  @Test
  public void test()
  {
    assertEquals(rows, e.getNumRows());
    assertEquals(cols, e.getNumCols());
    
    Environment f = Environment.getEnvironment(2, 3);
    assertEquals(rows, f.getNumRows());
    assertEquals(cols, f.getNumCols());
  }
  @Test
  public void testEnvironmentInitializationSingle() throws EnvironmentException {
    Environment environment = Environment.getEnvironment(6, 6);
    assertNull(environment.getLifeForm(0, 0));
    Environment environment2 = Environment.getEnvironment(8, 8);
    LifeForm bob = new MockLifeForm("bob", 40);
    assertFalse(environment.addLifeForm(bob, 5, 6));
  }
  
  /**
   * 
   */
  @Test
  public void addWeapon() {
    Environment environment = Environment.getEnvironment(6, 6);
    Weapon pistol = new Pistol();
    Weapon chain = new ChainGun();
    assertTrue(environment.addWeapon(pistol, 2, 4));
    assertTrue(environment.addWeapon(chain, 2, 4));
    assertFalse(environment.addWeapon(chain, 2, 4));
    
  }
  
  @Test
  public void removeWeapon() {
    Environment environment = Environment.getEnvironment(6, 6);
    Weapon pistol = new Pistol();
    Weapon chain = new ChainGun();
    environment.addWeapon(pistol, 1, 5);
    environment.addWeapon(chain, 2, 3);
    assertEquals(chain,environment.removeWeapon(chain, 2, 3));
    assertNull(environment.removeWeapon(chain, 2,3));
  }
  
  @Test
  public void testWeaponArray() {
    Environment environment = Environment.getEnvironment(6, 6);
    Weapon pistol = new Pistol();
    Weapon chain = new ChainGun();
    environment.addWeapon(pistol, 1, 5);
    environment.addWeapon(chain, 1, 5);
    Weapon w[] = { pistol, chain };
    assertEquals(w, environment.getWeapons(1, 5));
  }
  
  /**
   * Tests the first version of getDistance with the same rows
   * @throws EnvironmentException
   */
  @Test
  public void testGetDistanceOnlyCoordinatesSameRow() throws EnvironmentException {
    Environment environment = Environment.getEnvironment(6, 6);
    assertEquals(15, environment.getDistance(1, 1, 1, 4), 0.1);
  }
  
  /**
   * Tests the first version of getDistance with the same columns
   * @throws EnvironmentException
   */
  @Test
  public void testGetDistanceOnlyCoordinatesSameCol() throws EnvironmentException {
    Environment environment = Environment.getEnvironment(6, 6);
    assertEquals(25, environment.getDistance(0, 2, 5, 2), 0.1);
  }
  /**
   * Tests the second version of getDistance with different rows and columns
   * @throws EnvironmentException
   */
  @Test
  public void testGetDistanceLifeFormsDiffRowDiffCol() throws EnvironmentException {
    Environment environment = Environment.getEnvironment(6, 6);
    LifeForm bob = new MockLifeForm("bob", 40);
    LifeForm joe = new MockLifeForm("joe", 40);
    bob.setLocation(0, 0);
    joe.setLocation(3, 2);
    assertEquals(18, environment.getDistance(bob, joe), 0.1);
  }
  
  
  //----------Decorator/pre-lab5 tests---------------
  
  /**
   * test to make sure that the cells are initialized correctly
   */
  @Test
  public void testEnvironmentInitialization() {
    Environment environment = Environment.getEnvironment(6, 6);
    assertNull(environment.getLifeForm(0, 0));
  }

  /**
   * test add and checks such FifeForm exists
   * @throws EnvironmentException 
   */
  @Test
  public void testAddLifeForm() throws EnvironmentException {
    Environment environment = Environment.getEnvironment(6, 6);
    LifeForm bob = new MockLifeForm("Bob", 40);
    assertTrue(environment.addLifeForm(bob, 0, 1));
    assertEquals("Bob", environment.getLifeForm(0, 1).getName());
  }

  /**
   * test to make sure the program is unable to add the LifeForm if the given
   * position is out of the 2d array (positive)
   * @throws EnvironmentException 
   */
  @Test
  public void testAddLifeFormBigNum() throws EnvironmentException {
    Environment environment = Environment.getEnvironment(6, 6);
    LifeForm bob = new MockLifeForm("Bob", 40);
    assertFalse(environment.addLifeForm(bob, 6, 7));
  }

  /**
   * test to make sure the program is unable to add the LifeForm if the given
   * position is out of the 2d array (negative)
   * @throws EnvironmentException 
   */
  @Test
  public void testAddLifeFormNegNum() throws EnvironmentException {
    Environment environment = Environment.getEnvironment(6, 7);
    LifeForm bob = new MockLifeForm("Bob", 40);
    assertFalse(environment.addLifeForm(bob, -1, -5));
  }

  /**
   * test to make sure the program is unable to add the LifeForm if the given
   * position already has a LifeForm
   * @throws EnvironmentException 
   */
  @Test
  public void testLifeFormtoCellContainingLifeForm() throws EnvironmentException {
    Environment environment = Environment.getEnvironment(6, 6);
    LifeForm bob = new MockLifeForm("Bob", 40);
    LifeForm dude = new MockLifeForm("Dude", 60);
    assertTrue(environment.addLifeForm(bob, 1, 2));
    assertFalse(environment.addLifeForm(dude, 1, 2));
  }

  /**
   * test to make sure the program able to remove a LifeForm and making sure it is
   * null
   * @throws EnvironmentException 
   */
  @Test
  public void testRemoveLifeForm() throws EnvironmentException {
    Environment environment = Environment.getEnvironment(6, 6);
    LifeForm bob = new MockLifeForm("Bob", 40);
    assertTrue(environment.addLifeForm(bob, 1, 2));
    environment.removeLifeForm(1, 2);
    assertNull(environment.getLifeForm(1, 2));
  }
}
