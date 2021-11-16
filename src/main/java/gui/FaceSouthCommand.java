package gui;

import lifeform.LifeForm;

public class FaceSouthCommand implements Command {
  
  LifeForm lifeform;

  public FaceSouthCommand(LifeForm lf) {
    lifeform = lf;
  }
  
  public void execute() {
    lifeform.changeDirection("South");
    System.out.println(lifeform.getName() + " facing South");
  }

}
