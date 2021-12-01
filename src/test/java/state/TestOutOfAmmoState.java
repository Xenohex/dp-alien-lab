package state;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import environment.Environment;
import exceptions.EnvironmentException;
import exceptions.WeaponException;
import gameplay.SimpleTimer;
import lifeform.Human;
import lifeform.LifeForm;
import weapon.Pistol;

public class TestOutOfAmmoState {

  Environment e;

  @Before
  public void resetEnvironment() {
    e = Environment.getEnvironment(6, 6);
    e.clearBoard();
  }

  /**
   * Tests the initialization of the OutOfAmmoState
   */
  @Test
  public void testInitialization() {
    LifeForm bob = new Human("bob", 10, 10);
    AiContext con = new AiContext(bob, e);
    con.setCurrentState(con.getOutOfAmmoState());
    assertEquals(OutOfAmmoState.class, con.getCurrentState().getClass());
  }

  /**
   * Tests to see if when in the OutOfAmmoState, that the lifeform 
   * reloads their weapon
   * @throws WeaponException
   * @throws EnvironmentException
   */
  @Test
  public void testReload() throws WeaponException, EnvironmentException {
    SimpleTimer t = new SimpleTimer(1000);
    LifeForm bob = new Human("bob", 20, 10);
    Pistol w = new Pistol();
    t.addTimeObserver(w);
    bob.pickUpWeapon(w);
    AiContext con = new AiContext(bob, e);
    t.addTimeObserver(con);

    for (int i = w.getMaxAmmo(); i > 0; i--) {
      w.fire(10);
      w.updateTime(1000);
      System.out.print(w.getCurrentAmmo() + " ");
    }
    
    OutOfAmmoState state = new OutOfAmmoState(con);
    con.setCurrentState(state);
    assertEquals(OutOfAmmoState.class, con.getCurrentState().getClass());
    t.timeChanged();
    assertEquals(10, w.getCurrentAmmo());
    assertEquals(HasWeaponState.class, con.getCurrentState().getClass());
    System.out.println("\n" + w.getCurrentAmmo());
  }
  
  /**
   * Tests that the lifeform changes states correctly when it does not
   * have a weapon, when it picks up a weapon, when it runs out of ammo,
   * and when it reloads.
   * @throws WeaponException
   * @throws EnvironmentException
   */
  @Test
  public void testMoveStates() throws WeaponException, EnvironmentException {
    SimpleTimer t = new SimpleTimer(1000);
    LifeForm bob = new Human("bob", 20, 10);
    Pistol w = new Pistol();
    t.addTimeObserver(w);
    AiContext con = new AiContext(bob, e);
    t.addTimeObserver(con);
    assertEquals(NoWeaponState.class, con.getCurrentState().getClass());
    e.addLifeForm(bob, 0, 0);
    e.addWeapon(w, 0, 0);
    t.timeChanged();
    assertEquals(HasWeaponState.class, con.getCurrentState().getClass());
    for (int i = w.getMaxAmmo(); i > 0; i--) {
      w.fire(10);
      t.timeChanged();
    }
    assertEquals(0, w.getCurrentAmmo());
    assertEquals(OutOfAmmoState.class, con.getCurrentState().getClass());
    t.timeChanged();
    assertEquals(w.getCurrentAmmo(), w.getMaxAmmo());
    assertEquals(HasWeaponState.class, con.getCurrentState().getClass());
  }
  
  /**
   * Tests to make sure that when the lifeform shoots and runs out of ammo,
   * if it's health points have reached zero, then instead of switching to
   * the OutOfAmmoState and completing the reload, it dies and switches
   * to the DeadState.
   * @throws EnvironmentException
   * @throws WeaponException
   */
  @Test
  public void testDead() throws EnvironmentException, WeaponException {
    SimpleTimer t = new SimpleTimer(1000);
    LifeForm bob = new Human("bob", 20, 10);
    Pistol w = new Pistol();
    t.addTimeObserver(w);
    AiContext con = new AiContext(bob, e);
    t.addTimeObserver(con);
    e.addLifeForm(bob, 0, 0);
    e.addWeapon(w, 0, 0);
    assertEquals(NoWeaponState.class, con.getCurrentState().getClass());
    for (int i = w.getMaxAmmo(); i > 0; i--) {
      w.fire(10);
      w.updateTime(1000);
    }
    t.timeChanged();
    assertEquals(HasWeaponState.class, con.getCurrentState().getClass());
    t.timeChanged();
    bob.setCurrentLifePoints(0);
    t.timeChanged();
    assertEquals(DeadState.class, con.getCurrentState().getClass());
    assertEquals(0, w.getCurrentAmmo());
  }

}
