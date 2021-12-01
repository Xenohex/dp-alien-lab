package state;

import environment.Environment;
import exceptions.EnvironmentException;
import lifeform.LifeForm;
import java.util.Random;
import weapon.Weapon;

public class DeadState extends ActionState {

  DeadState(AiContext context) {
    super(context);
  }

  @Override
  void executeAction() {
    LifeForm lifeForm = context.getLifeForm();
    Environment e = context.getEnvironment();
    respawn();
    context.setCurrentState(context.getNoWeaponState());
    e.updateCell(lifeForm.getRow(), lifeForm.getCol());
  }

  /**
   * 
   */
  public void respawn() {
    LifeForm lifeForm = context.getLifeForm();
    Environment e = context.getEnvironment();
    Random ran = new Random();
    Weapon w = lifeForm.getWeapon();
    boolean canSpawnLifeForm = false;
    
    try {
      lifeForm.dropWeapon();
      e.addWeapon(w, lifeForm.getRow(), lifeForm.getCol());
      e.removeLifeForm(lifeForm.getRow(), lifeForm.getCol());
      e.updateCell(lifeForm.getRow(), lifeForm.getCol());
      do {
        canSpawnLifeForm = e.addLifeForm(lifeForm, ran.nextInt(e.getNumRows()), 
            ran.nextInt(e.getNumCols()));
      } while (!canSpawnLifeForm);
    } catch (EnvironmentException e1) {
      e1.printStackTrace();
    }
    
    lifeForm.setCurrentLifePoints(lifeForm.getMaxLifePoints());  
  }
}
