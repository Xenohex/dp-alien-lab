package lifeform;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import environment.Environment;
import exceptions.EnvironmentException;
import exceptions.RecoveryRateException;
import weapon.ChainGun;
import weapon.Pistol;
import weapon.Weapon;
import gui.Command;
import gui.MoveCommand;
import gui.Remote;
/**
 * Test all cases for environment class
 * 
 * @author Bader lab 6, Christian Spitler lab 5; jun up to lab 4
 *
 */
public class TestEnvironment {
  //-----------------------------Lab 7 Tests-------------------//
  /*
   * 
   */
  @Test
  public void testRandom() {
    Environment e = Environment.getEnvironment(6, 6);
    System.out.println(e.getRandomCell());
  }
  /*---------------------------lab6-tests--------------------------------------*/
  
  /**
   * testing Moving w or w/o obstacles, also, on different edges of the environment
   * @throws EnvironmentException
   * @throws RecoveryRateException
   */
  @Test
  public void testMoveNorth() throws EnvironmentException, RecoveryRateException {
    Environment e = Environment.getEnvironment(6, 6);
    Remote r = new Remote();
    Alien Bob = new Alien("Bob", 30);
    Human Alice = new Human("Alice", 40, 0);
    e.addLifeForm(Alice, 5, 0);
    e.addLifeForm(Bob, 4, 0);
    Command move = new MoveCommand(Alice, e);
    r.setCommand(move);
    r.buttonPressed();
    assertEquals(2, Alice.getRow());
    Command move2 = new MoveCommand(Bob, e);
    r.setCommand(move2);
    r.buttonPressed();
    assertEquals(3, Bob.getRow());
  }
  
  @Test
  public void testMoveSouth() throws EnvironmentException, RecoveryRateException {
    Environment e = Environment.getEnvironment(6, 6);
    Remote r = new Remote();
    Alien Bob = new Alien("Bob", 30);
    Human Alice = new Human("Alice", 40, 0);
    Bob.changeDirection("South");
    Alice.changeDirection("South");
    e.addLifeForm(Alice, 1, 0);
    e.addLifeForm(Bob, 2, 0);
    Command move = new MoveCommand(Alice, e);
    r.setCommand(move);
    r.buttonPressed();
    assertEquals(4, Alice.getRow());
    Command move2 = new MoveCommand(Bob, e);
    r.setCommand(move2);
    r.buttonPressed();
    assertEquals(3, Bob.getRow());
  }
  
  @Test
  public void testMoveEast() throws EnvironmentException, RecoveryRateException {
    Environment e = Environment.getEnvironment(6, 6);
    Remote r = new Remote();
    Alien Bob = new Alien("Bob", 30);
    Human Alice = new Human("Alice", 40, 0);
    Bob.changeDirection("East");
    Alice.changeDirection("East");
    e.addLifeForm(Alice, 0, 0);
    e.addLifeForm(Bob, 0, 1);
    Command move = new MoveCommand(Alice, e);
    r.setCommand(move);
    r.buttonPressed();
    assertEquals(3, Alice.getCol());
    Command move2 = new MoveCommand(Bob, e);
    r.setCommand(move2);
    r.buttonPressed();
    assertEquals(2, Bob.getCol());
  }
  
  @Test
  public void testMoveWest() throws EnvironmentException, RecoveryRateException {
    Environment e = Environment.getEnvironment(6, 6);
    Remote r = new Remote();
    Alien Bob = new Alien("Bob", 30);
    Human Alice = new Human("Alice", 40, 0);
    Bob.changeDirection("West");
    Alice.changeDirection("West");
    e.addLifeForm(Alice, 0, 3);
    e.addLifeForm(Bob, 0, 2);
    Command move = new MoveCommand(Alice, e);
    r.setCommand(move);
    r.buttonPressed();
    assertEquals(0, Alice.getCol());
    Command move2 = new MoveCommand(Bob, e);
    r.setCommand(move2);
    r.buttonPressed();
    assertEquals(1, Bob.getCol());
  }
  
  @Test
  public void testMoveAtEdge() throws EnvironmentException, RecoveryRateException {
    Environment e = Environment.getEnvironment(6, 6);
    Remote r = new Remote();
    Alien Bob = new Alien("Bob", 30);
    Human Alice = new Human("Alice", 40, 0);
    Human Josh = new Human("Josh", 40, 0);
    Alien John = new Alien("John", 30);
    Josh.changeDirection("South");
    Bob.changeDirection("East");
    Alice.changeDirection("West");
    e.addLifeForm(John, 1, 0);
    e.addLifeForm(Josh, 5, 0);
    e.addLifeForm(Alice, 0, 1);
    e.addLifeForm(Bob, 0, 5);
    Command move1 = new MoveCommand(Alice, e);
    r.setCommand(move1);
    r.buttonPressed();
    assertEquals(0, Alice.getCol());
    Command move2 = new MoveCommand(Bob, e);
    r.setCommand(move2);
    r.buttonPressed();
    assertEquals(5, Bob.getCol());
    Command move3 = new MoveCommand(Josh, e);
    r.setCommand(move3);
    r.buttonPressed();
    assertEquals(5, Josh.getRow());
    Command move4 = new MoveCommand(John, e);
    r.setCommand(move4);
    r.buttonPressed();
    assertEquals(1, John.getRow());
  }
  
  
  //-------lab5 tests -------------------------
  
  /**
   * 
   */
  @Before 
  public void resetEnvironment() {
    Environment e = Environment.getEnvironment(6, 6);
    e.clearBoard();
  }
  
  @Test
  public void testEnvironmentInitializationSingle() throws EnvironmentException {
    Environment environment = Environment.getEnvironment(6, 6);
    assertNull(environment.getLifeForm(0, 0));
    Environment environment2 = Environment.getEnvironment(8, 8);
    LifeForm bob = new MockLifeForm("bob", 40);
    assertFalse(environment.addLifeForm(bob, 5, 6));
  }
  
  @Test
  public void testAddWeapon() {
    Environment environment = Environment.getEnvironment(6, 6);
    Weapon pistol = new Pistol();
    Weapon chain = new ChainGun();
    assertTrue(environment.addWeapon(pistol, 2, 4));
    assertTrue(environment.addWeapon(chain, 2, 4));
    assertFalse(environment.addWeapon(chain, 2, 4));
    
  }
  
  @Test
  public void testRemoveWeapon() {
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
