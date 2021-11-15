package gui;

import lifeform.LifeForm;

public class faceEastCommand implements Command {

  LifeForm lifeform;

  public faceEastCommand(LifeForm lf) {
    lifeform = lf;
  }
  
  public void execute() {
    lifeform.changeDirection("East");
    System.out.println(lifeform.getName() + " facing East");
  }
  
}
