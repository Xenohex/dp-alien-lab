package state;

public class OutOfAmmoState extends ActionState {

  OutOfAmmoState(AIContext context) {
    super(context);
  }

  @Override
  void executeAction() {
    if (lifeForm.getCurrentLifePoints() <= 0) {
      context.setCurrentState(context.getDeadState());
    } else {
      reload();
    }
    // Evaluation
  }
  
  public void reload() {
    lifeForm.reload(); // wow that was hard
    context.setCurrentState(context.hasWeaponState);
  }

}
