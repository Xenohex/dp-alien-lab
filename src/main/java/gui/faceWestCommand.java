package gui;

import lifeform.LifeForm;

public class faceWestCommand implements Command {
  
  LifeForm lifeform;

  public faceWestCommand(LifeForm lf) {
    lifeform = lf;
  }
  
  public void execute() {
      lifeform.changeDirection("West");
      System.out.println(lifeform.getName() + " facing West");
  }
  
}
