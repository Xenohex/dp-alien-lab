package state;

import environment.Environment;
import lifeform.LifeForm;

public class OutOfAmmoState extends ActionState {

  OutOfAmmoState(AIContext context) {
    super(context);
  }

  @Override
  void executeAction() {
    LifeForm lf = context.getLifeForm();
    Environment env = context.getEnvironment();
    
    if (lf.getCurrentLifePoints() <= 0) {
      context.setCurrentState(context.getDeadState());
    } else {
      reload();
    }
    e.updateCell(lf.getRow(), lf.getCol());
    // Evaluation
  }
  
  public void reload() {
    LifeForm lf = context.getLifeForm();
    Environment env = context.getEnvironment();

    lf.reload();
    context.setCurrentState(context.hasWeaponState);
  }

}
