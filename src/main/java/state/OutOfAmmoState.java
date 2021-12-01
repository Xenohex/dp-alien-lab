package state;

import environment.Environment;
import lifeform.LifeForm;

public class OutOfAmmoState extends ActionState {

  OutOfAmmoState(AiContext context) {
    super(context);
  }

  @Override
  void executeAction() {
    LifeForm lf = context.getLifeForm();
    Environment e = context.getEnvironment();
    
    if (lf.getCurrentLifePoints() <= 0) {
      context.setCurrentState(context.getDeadState());
    } else {
      reload();
    }
    e.updateCell(lf.getRow(), lf.getCol());
    // Evaluation
  }
  
  /**
   * will reload the weapon the lifeform holds
   */
  public void reload() {
    LifeForm lf = context.getLifeForm();
    Environment env = context.getEnvironment();

    lf.reload();
    context.setCurrentState(context.hasWeaponState);
  }

}
