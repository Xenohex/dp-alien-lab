package recovery;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Test all associated border cases with fractional recovery
 * @author Jun
 *
 */
public class TestRecoveryFractional {

  /**
   * Makes sure there are no recovery made when no damage is done
   */
  @Test
  public void noRecoveryWhenNotHurt() {
    RecoveryFractional r1 = new RecoveryFractional(.1);
    int maxLifePts = 30;
    int result = r1.calculateRecovery(maxLifePts, maxLifePts);
    assertEquals(maxLifePts, result);
  }

  /**
   * Makes sure the recover does not go over the maximum life points
   */
  @Test
  public void recoverToMaxNotGoingOver() {
    RecoveryFractional r1 = new RecoveryFractional(.1);
    int maxLifePts = 30;
    int result = r1.calculateRecovery(28, maxLifePts);
    assertEquals(maxLifePts, result);
  }

  /**
   * Makes sure fractional recovery is working like normal
   */
  @Test
  public void testFractionalRecovery() {
    RecoveryFractional r1 = new RecoveryFractional(.1);
    int maxLifePts = 100;
    int result = r1.calculateRecovery(10, maxLifePts);
    assertEquals(11,result);
    result = r1.calculateRecovery(11, maxLifePts);
    assertEquals(13,result);
  }
  /**
   * No recovery should be made when LifeForm is dead
   */
  @Test
  public void noRecoveryWhenDead() {
    RecoveryFractional r1 = new RecoveryFractional(.1);
    int maxLifePts = 30;
    int result = r1.calculateRecovery(0, maxLifePts);
    assertEquals(0, result);
  }

  /**
   * Makes sure the LifeForm can recover perfectly
   */
  @Test
  public void perfectRecovery() {
    RecoveryFractional r1 = new RecoveryFractional(.01);
    int maxLifePts = 100;
    int result = r1.calculateRecovery(54, maxLifePts);
    assertEquals(55, result);
  }

}
