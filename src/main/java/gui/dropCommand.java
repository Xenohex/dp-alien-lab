package gui;

import environment.Environment;
import environment.Cell;
import lifeform.LifeForm;

public class dropCommand implements Command {
  
  LifeForm lifeform;
  Environment e;

  public dropCommand(LifeForm lf, Environment ee) {
    lifeform = lf;
    e = ee;
  }
  
  public void execute() {
    
    if (lifeform == null) {
      System.out.println("Error: no lifeform selected");
    } else if (lifeform.getWeapon() == null) {
      System.out.println("Error: lifeform has no weapon to drop");
    } else {
      lifeform.dropWeapon();
    }
  }
  
}
