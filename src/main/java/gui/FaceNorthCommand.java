package gui;

import lifeform.LifeForm;

public class FaceNorthCommand implements Command {
  
  LifeForm lifeform;

  public FaceNorthCommand(LifeForm lf) {
    lifeform = lf;
  }
  
  public void execute() {
    lifeform.changeDirection("North");
    System.out.println(lifeform.getName() + " facing North");
  }
  
}
