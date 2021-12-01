package state;

import static org.junit.Assert.*;

import org.junit.Test;

import environment.Environment;
import exceptions.EnvironmentException;
import exceptions.RecoveryRateException;
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
}
