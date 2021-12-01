package state;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import environment.Environment;
import exceptions.AttachmentException;
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

  @Test
  public void testReload() throws WeaponException {
    SimpleTimer t = new SimpleTimer(1000);
    /*
     * try { Simulator s = new Simulator(e, t, 1, 0); } catch (EnvironmentException
     * | AttachmentException e1) { e1.printStackTrace(); }
     */
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
    /*OutOfAmmoState state = new OutOfAmmoState(con);
    con.setCurrentState(state);*/
    con.updateTime(1000);
    assertEquals(10, w.getCurrentAmmo());
    System.out.println("\n" + w.getCurrentAmmo());
  }

}
