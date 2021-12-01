package state;

import static org.junit.Assert.*;

import org.junit.Test;

import environment.Environment;
import exceptions.EnvironmentException;
import lifeform.Human;
import lifeform.LifeForm;
import weapon.Pistol;
import weapon.Weapon;

public class TestDeadState {

  @Test
  public void testWithWeapon() throws EnvironmentException {
    Environment e = Environment.getEnvironment(3, 5);
    LifeForm Bob = new Human("Bob", 30, 0);
    Weapon w = new Pistol();
    Bob.pickUpWeapon(w);
    e.addLifeForm(Bob, 1, 1);
    AiContext context = new AiContext(Bob, e);
    ActionState d = new DeadState(context);
    d.executeAction();
    assertFalse(Bob.hasWeapon());
  }
  
  @Test
  public void testWithNoWeapon() throws EnvironmentException {
    Environment e = Environment.getEnvironment(3, 5);
    LifeForm Bob = new Human("Bob", 30, 0);
    e.addLifeForm(Bob, 1, 1);
    AiContext context = new AiContext(Bob, e);
    ActionState d = new DeadState(context);
    d.executeAction();
    assertFalse(Bob.hasWeapon());
    assertEquals(30, Bob.getCurrentLifePoints());
  }
}
