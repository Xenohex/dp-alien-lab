package state;

import environment.Cell;
import weapon.Weapon;

public class DeadState extends ActionState {

  DeadState(AIContext context) {
    super(context);
    // TODO Auto-generated constructor stub
  }

  @Override
  void executeAction() {
    respawn();
    context.setCurrentState(context.getNoWeaponState());
  }

  public void respawn() {
    Cell cell = context.e.getRandomCell();
    Weapon w;
    if(lifeForm.hasWeapon() && cell.getWeaponsCount() != 2 && cell.getLifeForm() == null) {
      cell.addLifeForm(lifeForm);
      w = lifeForm.dropWeapon();
       
    } else {
     
    }
  }
}
