package gui;

import environment.Environment;
import environment.Cell;
import lifeform.LifeForm;

public class DropCommand implements Command {
  
  LifeForm lifeform;
  Environment env;

  /**
   * 
   * @param lf  lifeform
   * @param ee  environment
   * Constructor for the drop command
   * Uses lifeform to determine lifeform performing drop
   * Uses environment to determine where to put the dropped weapon
   */
  public DropCommand(LifeForm lf, Environment ee) {
    lifeform = lf;
    env = ee;
  }
  
  /**
   * If the lifeform has a weapon, it is removed from the lifeform's inventory
   * and placed in the lifeform's current cell
   */
  public void execute() {
    
    if (lifeform == null) {
      System.out.println("Error: no lifeform selected");
    } else if (lifeform.hasWeapon() == false) {
      System.out.println("Error: lifeform has no weapon to drop");
    } else if (env.getCell(lifeform.getRow(), lifeform.getCol()).getWeaponsCount() == 2) {
      System.out.println("Error: too many weapons in cell");
    } else {
      env.addWeapon(lifeform.dropWeapon(), lifeform.getRow(), lifeform.getCol());
      System.out.println("Drop executed");
    }
  }
  
}
