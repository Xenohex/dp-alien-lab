package gui;

import lifeform.LifeForm;

public class faceWestCommand implements Command {
  
  LifeForm lifeform;

  public faceWestCommand(LifeForm lf) {
    lifeform = lf;
  }
  
  public void execute() {
    
    if (lifeform == null) {
      System.out.println("Error: no lifeform selected");
    } else {
      lifeform.changeDirection("West");
    }
  }
  
}
