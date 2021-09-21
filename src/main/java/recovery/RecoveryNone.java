package recovery;

/**
 * @author Jun
 *
 */
public class RecoveryNone implements RecoveryBehavior {
  
  /**
   * LifeForms with this recovery behavior will not be able to recover
   * 
   * @param currentLife LifeForm's current life points
   * @param maxLife the maximum amount of life points the LifeForm can have
   */
  public int calculateRecovery(int currentLife, int maxLife) {
    return currentLife;
  }

}
