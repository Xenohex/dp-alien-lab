package recovery;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * Test for RecoveryNone
 * 
 * @author Jun
 *
 */
public class TestRecoveryNone {

  /**
   * Makes sure LifeForms with this recovery behavior will not recover
   */
  @Test
  public void testRecoveryNone() {
    RecoveryBehavior rb = new RecoveryNone();
    assertEquals(100, rb.calculateRecovery(100, 3));
  }

}
