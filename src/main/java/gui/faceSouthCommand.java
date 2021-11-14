package gui;

import lifeform.LifeForm;

public class faceSouthCommand implements Command {
  
  LifeForm lifeform;

  public faceSouthCommand(LifeForm lf) {
    lifeform = lf;
  }
  
  public void execute() {
    
    if (lifeform == null) {
      System.out.println("Error: no lifeform selected");
    } else {
    lifeform.changeDirection("South");
    }
  }

}
