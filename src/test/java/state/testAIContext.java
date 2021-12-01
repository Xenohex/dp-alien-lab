package state;

import static org.junit.Assert.*;

import org.junit.Test;

import environment.Environment;
import exceptions.RecoveryRateException;
import lifeform.Alien;
import lifeform.LifeForm;

public class testAIContext {

  /**
   * tests if AIContext can get states
   * @throws RecoveryRateException
   */
  @Test
  public void testGetStates() throws RecoveryRateException {
    Environment e = Environment.getEnvironment(3, 5);
    LifeForm al = new Alien("Al",35);
    AiContext context = new AiContext(al, e);
    assertEquals(NoWeaponState.class, context.getCurrentState().getClass());
    assertEquals(HasWeaponState.class, context.getHasWeaponState().getClass());
    assertEquals(DeadState.class, context.getDeadState().getClass());
    assertEquals(OutOfAmmoState.class, context.getOutOfAmmoState().getClass());
  }
  
  /**
   * tests if AIContext can change the state
   * @throws RecoveryRateException
   */
  @Test
  public void testChangeState() throws RecoveryRateException {
    Environment e = Environment.getEnvironment(3, 5);
    LifeForm al = new Alien("Al",35);
    AiContext context = new AiContext(al, e);
    context.setCurrentState(context.getDeadState());
    assertEquals(DeadState.class, context.getCurrentState().getClass());
    context.setCurrentState(context.getHasWeaponState());
    assertEquals(HasWeaponState.class, context.getCurrentState().getClass());
  }

}
