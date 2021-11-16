package gui;

import environment.Environment;
import lifeform.LifeForm;
import weapon.Weapon;

public class AcquireCommand implements Command {
    
  LifeForm lifeform;
  Environment env;

  /**
   * 
   * @param lf  lifeform
   * @param ee  environment
   * Constructor to get the selected lifeform and the singleton environment
   */
  public AcquireCommand(LifeForm lf, Environment ee) {
    lifeform = lf;
    env = ee;
  }
  
  /**
   * Executes command to pick up the weapon in the cell. If the lifeform already
   * has a weapon it switches out its own weapon for the one on the ground.
   * 
   * It makes the lifeform pick up the weapon, removing it from the environment.
   * If it has a weapon already, it removes the old weapon from the environment and 
   * places it in the lifeform's inventory while placing the lifeform's old weapon
   * into the environment.
   */
  public void execute() {
    
    if (env.getCell(lifeform.getRow(), lifeform.getCol()).getWeapon2() != null) {
      Weapon w = lifeform.dropWeapon();
      lifeform.pickUpWeapon(env.getCell(lifeform.getRow(), lifeform.getCol()).getWeapon1());
      env.removeWeapon(lifeform.getWeapon(), lifeform.getRow(), lifeform.getCol());
      env.addWeapon(w, lifeform.getRow(), lifeform.getCol());
      System.out.println("Weapon switched");
    } else if (env.getCell(lifeform.getRow(), lifeform.getCol()).getWeapon1() != null) {
      Weapon w = lifeform.dropWeapon();
      lifeform.pickUpWeapon(env.getCell(lifeform.getRow(), lifeform.getCol()).getWeapon1());
      env.removeWeapon(lifeform.getWeapon(), lifeform.getRow(), lifeform.getCol());
      env.addWeapon(w, lifeform.getRow(), lifeform.getCol());
      System.out.println("Weapon switched");
    } else {
      System.out.println("Error: no weapon in cell"); 
    }
  }

}
