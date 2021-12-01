package state;

import environment.Cell;
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
<<<<<<< HEAD
    Cell cell = context.env.getRandomCell();
    Weapon w;
    if (lifeForm.hasWeapon() && cell.getWeaponsCount() != 2 && cell.getLifeForm() == null) {
=======
    Cell cell = context.e.getRandomCell();
    Random ran = new Random();
    Weapon w = lifeForm.getWeapon();
    boolean canSpawnWeapon = false;
    boolean canSpawnLifeForm = false;
    /*if (lifeForm.hasWeapon() && cell.getWeaponsCount() != 2 && cell.getLifeForm() == null) {
>>>>>>> 7b1c9005c0ef9cb812bb1f08b33a47fe75b94b69
      cell.addLifeForm(lifeForm);
      w = lifeForm.dropWeapon();
      e.addWeapon(w, lifeForm.getRow(), lifeForm.getCol());
      e.updateCell(lifeForm.getRow(), lifeForm.getCol());
    } else {
      System.out.println("No space available in this cell to drop the weapon!");
    }*/
    try {
      lifeForm.dropWeapon();
      e.addWeapon(w, lifeForm.getRow(), lifeForm.getCol());
      e.removeLifeForm(lifeForm.getRow(), lifeForm.getCol());
      e.updateCell(lifeForm.getRow(), lifeForm.getCol());
      do {
        canSpawnLifeForm = e.addLifeForm(lifeForm, ran.nextInt(e.getNumRows()), ran.nextInt(e.getNumCols()));
      } while (!canSpawnLifeForm);
    } catch (EnvironmentException e1) {
      e1.printStackTrace();
    }
    
    lifeForm.setCurrentLifePoints(lifeForm.getMaxLifePoints());  
  }
}
