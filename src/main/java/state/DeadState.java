package state;

import environment.Cell;
import environment.Environment;
import exceptions.EnvironmentException;
import lifeform.LifeForm;
import java.util.Random;
import weapon.Weapon;

public class DeadState extends ActionState {

  DeadState(AIContext context) {
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

  public void respawn() {
    LifeForm lifeForm = context.getLifeForm();
    Environment e = context.getEnvironment();
    Cell cell = context.e.getRandomCell();
    Random ran = new Random();
    Weapon w;
    boolean canSpawn = false;
    /*if (lifeForm.hasWeapon() && cell.getWeaponsCount() != 2 && cell.getLifeForm() == null) {
      cell.addLifeForm(lifeForm);
      w = lifeForm.dropWeapon();
      e.addWeapon(w, lifeForm.getRow(), lifeForm.getCol());
      e.updateCell(lifeForm.getRow(), lifeForm.getCol());
    } else {
      System.out.println("No space available in this cell to drop the weapon!");
    }*/
    try {
      if (e.getCell(lifeForm.getRow(), lifeForm.getCol()).getWeapon2() != null) {
        do {
          e.updateCell(lifeForm.getRow(), lifeForm.getCol());
          e.removeLifeForm(lifeForm.getRow(), lifeForm.getCol());
          canSpawn = e.addLifeForm(lifeForm,ran.nextInt(e.getNumRows()), ran.nextInt(e.getNumCols()));
        } while (!canSpawn);
      } else {
        e.addWeapon(lifeForm.dropWeapon(), lifeForm.getRow(), lifeForm.getCol());
        e.removeLifeForm(lifeForm.getRow(), lifeForm.getCol());
        e.updateCell(lifeForm.getRow(), lifeForm.getCol());
        do {
          canSpawn = e.addLifeForm(lifeForm,ran.nextInt(e.getNumRows()), ran.nextInt(e.getNumCols()));
        } while (!canSpawn);
      }
    } catch (EnvironmentException e1) {
      e1.printStackTrace();
    }
    
    lifeForm.setCurrentLifePoints(lifeForm.getMaxLifePoints());  
  }
}
