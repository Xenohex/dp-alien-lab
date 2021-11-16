package gui;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import environment.Environment;
import exceptions.EnvironmentException;
import exceptions.RecoveryRateException;
import exceptions.WeaponException;
import lifeform.Alien;
import weapon.ChainGun;
import weapon.Pistol;
import weapon.Weapon;

/**
 * Tests for Commands
 * @author Bader
 *
 */
public class TestCommand {

/*----------------------------------lab6-tests------------------------------------*/
  
Environment e;
Remote r = new Remote();
  
/**
 * Initialize and clear the environment before each test
 */
  @Before 
  public void resetEnvironment() {
    e = Environment.getEnvironment(6, 6);
    e.clearBoard();
  }
  
  /**
   * Test Move
   * @throws RecoveryRateException
   * @throws EnvironmentException
   */
  @Test
  public void testMove() throws RecoveryRateException, EnvironmentException {
    Alien bob = new Alien("bob", 30);
    e.addLifeForm(bob, 2, 0);
    Command move = new MoveCommand(bob, e);
    r.setCommand(move);
    r.buttonPressed();
    assertEquals(0, bob.getRow());
  }
  
  /**
   * test Reload 
   * @throws RecoveryRateException
   * @throws WeaponException
   */
  @Test
  public void testReload() throws RecoveryRateException, WeaponException {
    Alien bob = new Alien("bob", 30);
    Weapon w = new Pistol();
    w.fire(5);
    assertEquals(9, w.getCurrentAmmo());
    bob.pickUpWeapon(w);
    Command reload = new ReloadCommand(bob);
    r.setCommand(reload);
    r.buttonPressed();
    assertEquals(10, w.getCurrentAmmo());
  }
  
  /**
   * test Direction commands
   * @throws RecoveryRateException
   */
  @Test
  public void testTurnNorth() throws RecoveryRateException {
    Alien bob = new Alien("bob", 30);
    bob.changeDirection("South");
    assertEquals("South", bob.getCurrentDirection());
    Command north = new FaceNorthCommand(bob);
    r.setCommand(north);
    r.buttonPressed();
    assertEquals("North", bob.getCurrentDirection());
  }
  
  /**
   * test Direction commands
   * @throws RecoveryRateException
   */
  @Test
  public void testTurnSouth() throws RecoveryRateException {
    Alien bob = new Alien("bob", 30);
    assertEquals("North", bob.getCurrentDirection());
    Command south = new FaceSouthCommand(bob);
    r.setCommand(south);
    r.buttonPressed();
    assertEquals("South", bob.getCurrentDirection());
  }
  
  /**
   * test Direction commands
   * @throws RecoveryRateException
   */
  @Test
  public void testTurnEast() throws RecoveryRateException {
    Alien bob = new Alien("bob", 30);
    assertEquals("North", bob.getCurrentDirection());
    Command east = new FaceEastCommand(bob);
    r.setCommand(east);
    r.buttonPressed();
    assertEquals("East", bob.getCurrentDirection());
  }
  
  /**
   * test Direction commands
   * @throws RecoveryRateException
   */
  @Test
  public void testTurnWest() throws RecoveryRateException {
    Alien bob = new Alien("bob", 30);
    assertEquals("North", bob.getCurrentDirection());
    Command west = new FaceWestCommand(bob);
    r.setCommand(west);
    r.buttonPressed();
    assertEquals("West", bob.getCurrentDirection());
  }
  
  /**
   * test drop weapon command
   * @throws RecoveryRateException
   * @throws EnvironmentException
   */
  @Test
  public void testDropWeaponNoSpace() throws RecoveryRateException, EnvironmentException {
    Alien bob = new Alien("bob", 30);
    Weapon w = new Pistol();
    bob.pickUpWeapon(w);
    e.addLifeForm(bob, 1, 1);
    Weapon w1 = new Pistol();
    Weapon w2 = new ChainGun();
    e.addWeapon(w1, 1, 1);
    e.addWeapon(w2, 1, 1);
    Command drop = new DropCommand(bob, e);
    r.setCommand(drop);
    r.buttonPressed();
    assertTrue(bob.hasWeapon());
  }
  
  /**
   * test drop weapon command
   * @throws RecoveryRateException
   * @throws EnvironmentException
   */
  @Test
  public void testDropWeaponSpace() throws RecoveryRateException, EnvironmentException {
    Alien bob = new Alien("bob", 30);
    Weapon w = new Pistol();
    bob.pickUpWeapon(w);
    e.addLifeForm(bob, 1, 1);
    Weapon w2 = new ChainGun();
    e.addWeapon(w2, 1, 1);
    Command drop = new DropCommand(bob, e);
    r.setCommand(drop);
    r.buttonPressed();
    assertFalse(bob.hasWeapon());
    assertEquals(2, e.getCell(1, 1).getWeaponsCount());
  }

  /**
   * test Acquire weapon command
   * @throws RecoveryRateException
   * @throws EnvironmentException
   */
  @Test
  public void testAcquireNoWeapon() throws RecoveryRateException, EnvironmentException {
    Alien bob = new Alien("bob", 30);
    e.addLifeForm(bob, 1, 1);
    Command pick = new AcquireCommand(bob, e);
    r.setCommand(pick);
    r.buttonPressed();
    assertFalse(bob.hasWeapon());
    assertEquals(0, e.getCell(1, 1).getWeaponsCount());
  }
  
  /**
   * test Acquire weapon command
   * @throws RecoveryRateException
   * @throws EnvironmentException
   */
  @Test
  public void testAcquireWeapon() throws RecoveryRateException, EnvironmentException {
    Alien bob = new Alien("bob", 30);
    Weapon w = new Pistol();
    bob.pickUpWeapon(w);
    e.addLifeForm(bob, 1, 1);
    Weapon w2 = new ChainGun();
    e.addWeapon(w2, 1, 1);
    Command pick = new AcquireCommand(bob, e);
    r.setCommand(pick);
    r.buttonPressed();
    assertTrue(bob.hasWeapon());
    assertEquals("Pistol", e.getCell(1, 1).getWeapon1().toString());
  }
  
}
