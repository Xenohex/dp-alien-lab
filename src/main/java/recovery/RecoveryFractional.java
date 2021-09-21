package recovery;

/**
 * @author Jun
 *
 */
public class RecoveryFractional implements RecoveryBehavior {

  double percentRecovery;


  /**
   * @param percent of current health to recover
   */
  public RecoveryFractional(double percent) {
    percentRecovery = percent;
  }

  /**
   * recovering fractional amount based on the given health, will not
   * go over the max possible health
   * LifeForm with 11 health and recover of 10% will heal 2 = 13
   * 
   * @param currentLife the LifeForm's current life points
   * @param maxLife the maximum amount of life points the LifeForm can have
   * @return the amount of life points after recover
   */
  public int calculateRecovery(int currentLife, int maxLife) {
    //System.out.println(currentLife + " max = " + maxLife);
    double num = Math.ceil(percentRecovery * currentLife);
    if (num > currentLife) {
      currentLife += currentLife;
    } else {
      currentLife += num;
    }
    if (currentLife > maxLife) {
      currentLife = maxLife;
    } else if (currentLife < 0) {
      return 0;
    }

    return currentLife;
  }

}
