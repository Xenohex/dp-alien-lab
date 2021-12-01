package state;

import static org.junit.Assert.*;

import org.junit.Test;

import environment.Environment;
import exceptions.EnvironmentException;
import exceptions.RecoveryRateException;
import exceptions.WeaponException;
import lifeform.Alien;
import lifeform.Human;
import lifeform.LifeForm;
import weapon.Pistol;
import weapon.Weapon;

public class TestHasWeaponState {

  @Test
  public void testNoTarget() throws RecoveryRateException {
    Environment e = Environment.getEnvironment(3, 5);
    LifeForm al = new Alien("Al",35);
    Weapon w = new Pistol();
    al.pickUpWeapon(w);
    AiContext context = new AiContext(al, e);
    HasWeaponState hs = new HasWeaponState(context);
    hs.executeAction();
    assertEquals(10, w.getCurrentAmmo());
  }

  @Test 
  public void testSameType() throws EnvironmentException {
    Environment e = Environment.getEnvironment(3, 5);
    LifeForm Bob = new Human("Bob",35, 0);
    LifeForm Alice = new Human("Alice",50, 0);
    e.addLifeForm(Alice, 2, 3);
    Weapon w = new Pistol();
    Bob.pickUpWeapon(w);
    AiContext context = new AiContext(Bob, e);
    HasWeaponState hs = new HasWeaponState(context);
    hs.executeAction();
    assertEquals(50, Alice.getCurrentLifePoints());
  }
  
  @Test 
  public void testDifType() throws EnvironmentException, RecoveryRateException, WeaponException {
    Environment e = Environment.getEnvironment(3, 5);
    LifeForm Bob = new Human("Bob",35, 0);
    LifeForm Alice = new Alien("Alice",50);
    e.addLifeForm(Alice, 2, 3);
    Weapon w = new Pistol();
    Bob.pickUpWeapon(w);
    AiContext context = new AiContext(Bob, e);
    HasWeaponState hs = new HasWeaponState(context);
    hs.executeAction();
    Bob.attack(Alice, 5);
    assertEquals(45, Alice.getCurrentLifePoints());
  }
  
  @Test 
  public void testDifTypeAnd1Shot() throws EnvironmentException, RecoveryRateException, WeaponException {
    Environment e = Environment.getEnvironment(3, 5);
    LifeForm Bob = new Human("Bob",35, 0);
    LifeForm Alice = new Alien("Alice",50);
    e.addLifeForm(Alice, 2, 3);
    Weapon w = new Pistol();
    w.fire(5);
    Bob.pickUpWeapon(w);
    AiContext context = new AiContext(Bob, e);
    HasWeaponState hs = new HasWeaponState(context);
    hs.executeAction();
    Bob.attack(Alice, 5);
    assertEquals(45, Alice.getCurrentLifePoints());
  }
  
  @Test 
  public void testOutRange() throws EnvironmentException, RecoveryRateException, WeaponException {
    Environment e = Environment.getEnvironment(3, 5);
    LifeForm Bob = new Human("Bob",35, 0);
    LifeForm Alice = new Alien("Alice",50);
    e.addLifeForm(Alice, 2, 3);
    Weapon w = new Pistol();
    Bob.pickUpWeapon(w);
    AiContext context = new AiContext(Bob, e);
    HasWeaponState hs = new HasWeaponState(context);
    hs.executeAction();
    Bob.attack(Alice, 100);
    assertEquals(50, Alice.getCurrentLifePoints());
  }
  
  @Test 
  public void testDead() throws EnvironmentException, WeaponException {
    Environment e = Environment.getEnvironment(3, 5);
    LifeForm Bob = new Human("Bob", 0, 0);
    AiContext context = new AiContext(Bob, e);
    context.setCurrentState(context.getDeadState());
    //HasWeaponState hs = new HasWeaponState(context);
    //hs.executeAction();
    assertTrue(context.getCurrentState() instanceof DeadState);
  }
}
