package gui;

import lifeform.*;

public class reloadCommand implements Command {

  LifeForm lifeform;

  public reloadCommand(LifeForm lf) {
    lifeform = lf;
  }
  public void execute() {
    
    if (lifeform == null) {
      System.out.println("Error: no lifeform selected");
    } else if (lifeform.getWeapon() == null) {
      System.out.println("Error: lifeform has no weapon");
    } else {
      lifeform.reload();
      System.out.println("Reload executed");
    }
  }

}
