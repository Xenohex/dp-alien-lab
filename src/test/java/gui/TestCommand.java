package gui;

import static org.junit.Assert.*;

import org.junit.Test;

import environment.Environment;
import exceptions.EnvironmentException;
import exceptions.RecoveryRateException;
import exceptions.WeaponException;
import lifeform.Alien;
import lifeform.Human;
import weapon.ChainGun;
import weapon.Pistol;
import weapon.Weapon;

public class TestCommand {

/*----------------------------------lab6-tests------------------------------------*/
  
Environment e = Environment.getEnvironment(3, 3);
Remote r = new Remote();
  
  @Test
  public void testMove() throws RecoveryRateException, EnvironmentException {
    Alien bob = new Alien("bob", 30);
    Board b = new Board(e);
    e.addLifeForm(bob, 2, 0);
    Command move = new MoveCommand(bob, e, b);
    r.setCommand(move);
    r.buttonPressed();
    assertEquals(0, bob.getRow());
  }
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
  
  @Test
  public void testTurnSouth() throws RecoveryRateException {
    Alien bob = new Alien("bob", 30);
    assertEquals("North", bob.getCurrentDirection());
    Command south = new FaceSouthCommand(bob);
    r.setCommand(south);
    r.buttonPressed();
    assertEquals("South", bob.getCurrentDirection());
  }
  
  @Test
  public void testTurnEast() throws RecoveryRateException {
    Alien bob = new Alien("bob", 30);
    assertEquals("North", bob.getCurrentDirection());
    Command east = new FaceEastCommand(bob);
    r.setCommand(east);
    r.buttonPressed();
    assertEquals("East", bob.getCurrentDirection());
  }
  
  @Test
  public void testTurnWest() throws RecoveryRateException {
    Alien bob = new Alien("bob", 30);
    assertEquals("North", bob.getCurrentDirection());
    Command west = new FaceWestCommand(bob);
    r.setCommand(west);
    r.buttonPressed();
    assertEquals("West", bob.getCurrentDirection());
  }
  
  @Test
  public void testDropWeapon() throws RecoveryRateException, EnvironmentException {
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
    //assertEquals("South", bob.getCurrentDirection());
  }
  

}
