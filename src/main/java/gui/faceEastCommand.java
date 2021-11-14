package gui;

import lifeform.LifeForm;

public class faceEastCommand implements Command {

  LifeForm lifeform;

  public faceEastCommand(LifeForm lf) {
    lifeform = lf;
  }
  
  public void execute() {
    
    if (lifeform == null) {
      System.out.println("Error: no lifeform selected");
    } else {
    lifeform.changeDirection("East");
    }
  }
  
}
