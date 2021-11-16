package gui;

import environment.Environment;
import environment.Cell;
import lifeform.LifeForm;

public class DropCommand implements Command {
  
  LifeForm lifeform;
  Environment e;

  public DropCommand(LifeForm lf, Environment ee) {
    lifeform = lf;
    e = ee;
  }
  
  public void execute() {
    
    if (lifeform == null) {
      System.out.println("Error: no lifeform selected");
    } else if (lifeform.hasWeapon() == false) {
      System.out.println("Error: lifeform has no weapon to drop");
    } else {
      e.addWeapon(lifeform.dropWeapon(), lifeform.getRow(), lifeform.getCol());
      System.out.println("Drop executed");
    }
  }
  
}
