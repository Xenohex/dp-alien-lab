package gui;

import environment.Environment;
import lifeform.LifeForm;
import weapon.*;

public class acquireCommand implements Command {
    
  LifeForm lifeform;
  Environment e;

  public acquireCommand(LifeForm lf, Environment ee) {
    lifeform = lf;
    e = ee;
  }
  public void execute() {
    
    if (lifeform == null) {
      System.out.println("Error: no lifeform selected");
    } else if (e.getCell(lifeform.getRow(), lifeform.getCol()).getWeapon1() == null) {
      System.out.println("Error: no weapon in cell");
    } else {
      lifeform.pickUpWeapon(e.getCell(lifeform.getRow(), lifeform.getCol()).getWeapon1());
      e.removeWeapon(lifeform.getWeapon(), lifeform.getRow(), lifeform.getCol());
      System.out.println("acquire weapon executed");
    }
  }

}
