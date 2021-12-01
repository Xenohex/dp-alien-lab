package state;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import environment.Environment;
import exceptions.EnvironmentException;
import gameplay.SimpleTimer;
import lifeform.Human;
import lifeform.LifeForm;
import weapon.Pistol;

public class TestNoWeaponState {
  
  Environment e;

  @Before
  public void resetEnvironment() {
    e = Environment.getEnvironment(6, 6);
    e.clearBoard();
  }

  /**
   * Tests to make sure that the lifeform spawns in NoWeaponState and checks
   * to see if the lifeform picks up the weapon and switches to HasWeaponState
   * @throws EnvironmentException
   */
  @Test
  public void testWeaponInCell() throws EnvironmentException {
    SimpleTimer t = new SimpleTimer(1000);
    LifeForm bob = new Human("bob", 20, 10);
    Pistol w = new Pistol();
    t.addTimeObserver(w);
    AiContext con = new AiContext(bob, e);
    t.addTimeObserver(con);
    e.addLifeForm(bob, 0, 0);
    e.addWeapon(w, 0, 0);
    assertNull(bob.getWeapon());
    assertEquals(NoWeaponState.class, con.getCurrentState().getClass());
    t.timeChanged();
    assertEquals(w, bob.getWeapon());
    assertEquals(HasWeaponState.class, con.getCurrentState().getClass());
  }
  
  /**
   * Tests if there is no weapon in the cell the lifeform is in, if it
   * remains in the NoWeaponState.
   * @throws EnvironmentException
   */
  @Test
  public void testNoWeaponInCell() throws EnvironmentException {
    SimpleTimer t = new SimpleTimer(1000);
    LifeForm bob = new Human("bob", 20, 10);
    AiContext con = new AiContext(bob, e);
    t.addTimeObserver(con);
    e.addLifeForm(bob, 0, 0);
    assertNull(bob.getWeapon());
    assertEquals(NoWeaponState.class, con.getCurrentState().getClass());
    t.timeChanged();
    assertNull(bob.getWeapon());
    assertEquals(NoWeaponState.class, con.getCurrentState().getClass());
  }
  
  /**
   * Tests to see that if a lifeform is dead, that even though it was initially
   * in the NoWeaponState that it cannot pick up a weapon and instead moves
   * to the dead state.
   * @throws EnvironmentException
   */
  @Test
  public void testDead() throws EnvironmentException {
    SimpleTimer t = new SimpleTimer(1000);
    LifeForm bob = new Human("bob", 20, 10);
    Pistol w = new Pistol();
    t.addTimeObserver(w);
    AiContext con = new AiContext(bob, e);
    t.addTimeObserver(con);
    e.addLifeForm(bob, 0, 0);
    e.addWeapon(w, 0, 0);
    assertNull(bob.getWeapon());
    assertEquals(NoWeaponState.class, con.getCurrentState().getClass());
    bob.setCurrentLifePoints(0);
    t.timeChanged();
    assertNull(bob.getWeapon());
    assertEquals(0, bob.getCurrentLifePoints());
    assertEquals(DeadState.class, con.getCurrentState().getClass());
  }

}
