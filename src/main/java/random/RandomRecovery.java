package random;

import java.util.List;

import recovery.RecoveryBehavior;
import recovery.RecoveryFractional;
import recovery.RecoveryNone;
import recovery.RecoveryLinear;

public class RandomRecovery implements Random<RecoveryBehavior> {
  
  List<RecoveryBehavior> choices = List.of(new RecoveryNone(), 
      new RecoveryLinear(new RandInt(0,10).choose()), 
      new RecoveryFractional(new java.util.Random().nextDouble()));
  
  /**
   * chooses recovery behavior at random
   */
  public RecoveryBehavior choose() {
    
    return new FromList<RecoveryBehavior>(choices).choose();
  
  }
  
}


// have sentinel cooridnates where if one coordinate that was already used is called, 
// it instead reloops. sentinel coordinates are added to a list every time they are
// made to keep consistent

/*
 * class RandRecovery implements Random<Recovery> {
  List<Recovery> choices = List.of(new NoRecovery(), new Linear(), new Fractional());
  public Recovery choose() { return new FromList<Recovery>(choices).choose(); }
}

 */
