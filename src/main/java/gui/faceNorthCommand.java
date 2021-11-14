package gui;

import lifeform.LifeForm;

public class faceNorthCommand implements Command {
  
  LifeForm lifeform;

  public faceNorthCommand(LifeForm lf) {
    lifeform = lf;
  }
  
  public void execute() {
    
    if (lifeform == null) {
      System.out.println("Error: no lifeform selected");
    } else {
      lifeform.changeDirection("North");
      System.out.println(lifeform.getName() + " facing North");
    }
  }
  
}
