package lifeform;

import exceptions.RecoveryRateException;
import recovery.RecoveryBehavior;
import recovery.RecoveryNone;

/**
 * Keeps track of the information associated with a Alien. Also provides the
 * functionality related to the Alien.
 * 
 * @author Jun
 *
 */
public class Alien extends LifeForm {

  int maxLifePoints = 0;
  int recoveryRate = 0;
  RecoveryBehavior rb;

  /**
   * @param name
   * @param life
   * @throws RecoveryRateException should never happen
   */
  public Alien(String name, int life) throws RecoveryRateException {
    this(name, life, new RecoveryNone());
  }

  /**
   * @param name
   * @param life
   * @param rb the method in which this alien will recover life points
   * @throws RecoveryRateException should never happen
   */
  public Alien(String name, int life, RecoveryBehavior rb) 
      throws RecoveryRateException {
    this(name, life, rb, 0);
  }

  /**
   * @param name
   * @param life
   * @param rb
   * @param recoveryRate The rate in which aliens can recover, 1 = every round, 0 = no recover
   * @throws RecoveryRateException thrown if recoverRate is less than 0
   */
  public Alien(String name, int life, RecoveryBehavior rb, 
      int recoveryRate) throws RecoveryRateException {
    super(name, life);
    maxLifePoints = life;
    this.rb = rb;
    try {
      if (recoveryRate < 0) {
        throw new RecoveryRateException("Recovery Rate is Negative!");
      } else {
        this.recoveryRate = recoveryRate;
      }
    } catch (Exception e) {
      throw e;
    }
  }

  /**
   * @return maximum life points the alien can have
   */
  public int getMaxLifePoints() {
    return maxLifePoints;
  }

  /**
   * @return the recovery rate
   */
  public int getRecoveryRate() {
    return recoveryRate;
  }

  /**
   * method to perform the recover
   */
  public void recover() {
    currentLifePoints = rb.calculateRecovery(currentLifePoints, maxLifePoints);
  }
}
