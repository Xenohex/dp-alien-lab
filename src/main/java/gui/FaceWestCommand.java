package gui;

import lifeform.LifeForm;

public class FaceWestCommand implements Command {
  
  LifeForm lifeform;

  public FaceWestCommand(LifeForm lf) {
    lifeform = lf;
  }
  
  public void execute() {
    lifeform.changeDirection("West");
    System.out.println(lifeform.getName() + " facing West");
  }
  
}
