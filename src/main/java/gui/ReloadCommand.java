package gui;

import lifeform.*;

public class ReloadCommand implements Command {

  LifeForm lifeform;

  public ReloadCommand(LifeForm lf) {
    lifeform = lf;
  }
  public void execute() {
    
    if (lifeform.getWeapon() == null) {
      System.out.println("Error: lifeform has no weapon");
    } else {
      lifeform.reload();
      System.out.println("Reload executed");
    }
  }

}
