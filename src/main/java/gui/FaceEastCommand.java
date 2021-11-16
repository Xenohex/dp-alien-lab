package gui;

import lifeform.LifeForm;

public class FaceEastCommand implements Command {

  LifeForm lifeform;

  public FaceEastCommand(LifeForm lf) {
    lifeform = lf;
  }
  
  public void execute() {
    lifeform.changeDirection("East");
    System.out.println(lifeform.getName() + " facing East");
  }
  
}
