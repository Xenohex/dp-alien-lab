package recovery;

/**
 * @author Jun
 *
 */
public class RecoveryLinear implements RecoveryBehavior {

  int recoveryStep = 0;

  /**
   * @param step is the number of health to recover each round
   */
  public RecoveryLinear(int step) {
    recoveryStep = step;
  }
  
  /**
   * same life points recovered every round based on recoveryStep
   * 
   * @param currentLife LifeForm's current life points
   * @param maxLife the maximum amount of life points the LifeForm can have
   * @return the amount of life points after recover
   */
  public int calculateRecovery(int currentLife, int maxLife) {
    
    if (currentLife == 0) {
      return 0;
    }
    if (currentLife < recoveryStep) {
      currentLife += currentLife;
    } else {
      currentLife += recoveryStep;
    }
    if (currentLife > maxLife) {
      currentLife = maxLife;
    }

    return currentLife;
  }

}
