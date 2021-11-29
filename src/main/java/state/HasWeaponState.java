package state;

public class HasWeaponState extends ActionState {

  HasWeaponState(AIContext context) {
    super(context);
    // TODO Auto-generated constructor stub
  }

  @Override
  void executeAction() {
    if(lifeForm.getWeapon().getCurrentAmmo() <= 0) {
      context.setCurrentState(context.getOutOfAmmoState());
    } else if (lifeForm.getCurrentLifePoints() <= 0) {
      context.setCurrentState(context.getDeadState());
    }
    //Evaluation
  }
  
  public void search() {
    String direction = lifeForm.getCurrentDirection();
    if(direction == "South") {
      lifeForm.changeDirection("North");
    } else if (direction == "North") {
      lifeForm.changeDirection("South");
    } else if (direction == "East") {
      lifeForm.changeDirection("West");
    } else {
      lifeForm.changeDirection("East");
    }
  }

}
