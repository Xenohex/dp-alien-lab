package recovery;

/**
 * interface for Recovery
 * 
 */
public interface RecoveryBehavior {
  /**
   * 
   * @param currentLife
   * @param maxLife
   * @return the sum of currentLife and maxLife
   */
  public int calculateRecovery(int currentLife, int maxLife);

}
