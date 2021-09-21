package recovery;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import exceptions.RecoveryRateException;

/**
 * Test all associated border cases with linear recovery
 * 
 * @author Jun
 *
 */
public class TestRecoveryLinear {

  /**
   * Makes sure that no recovery is made when no damage is taken
   * 
   * @throws RecoveryRateException should not happen
   */
  @Test
  public void noRecoveryWhenNotHurt() throws RecoveryRateException {
    RecoveryLinear r1 = new RecoveryLinear(3);
    int maxLifePts = 30;
    int result = r1.calculateRecovery(maxLifePts, maxLifePts);
    assertEquals(maxLifePts,result);
  }

  /**
   * Makes sure the recover does not go over the maximum life points
   * 
   * @throws RecoveryRateException should not happen
   */
  @Test
  public void recoverToMaxNotGoingOver() throws RecoveryRateException {
    RecoveryLinear r1 = new RecoveryLinear(3);
    int maxLifePts = 100;
    int result = r1.calculateRecovery(98, maxLifePts);
    assertEquals(maxLifePts,result);
  }

  /**
   * Makes sure the recover cannot be more than original health
   * 
   * @throws RecoveryRateException should no happen
   */
  @Test
  public void maxRecoverByOriginalHealthPoints() throws RecoveryRateException {
    RecoveryLinear r1 = new RecoveryLinear(3);
    int maxLifePts = 100;
    int result = r1.calculateRecovery(1, maxLifePts);
    assertEquals(2,result);
  }

  /**
   * No recovery should be made when LifeForm is dead
   * 
   * @throws RecoveryRateException should not happen
   */
  @Test
  public void noRecoveryWhenDead() throws RecoveryRateException {
    RecoveryLinear r1 = new RecoveryLinear(3);
    int maxLifePts = 100;
    int result = r1.calculateRecovery(0, maxLifePts);
    assertEquals(0,result);
  }

  /**
   * Makes sure the LifeForm can recover perfectly
   * 
   * @throws RecoveryRateException should not happen
   */
  @Test
  public void perfectRecovery() throws RecoveryRateException {
    RecoveryLinear r1 = new RecoveryLinear(3);
    int maxLifePts = 100;
    int result = r1.calculateRecovery(99, maxLifePts);
    assertEquals(maxLifePts,result);
  }
}
