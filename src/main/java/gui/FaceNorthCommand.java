package gui;

import lifeform.LifeForm;

public class faceNorthCommand implements Command {
  
  LifeForm lifeform;

  public faceNorthCommand(LifeForm lf) {
    lifeform = lf;
  }
  
  public void execute() {
    lifeform.changeDirection("North");
    System.out.println(lifeform.getName() + " facing North");
  }
  
}
