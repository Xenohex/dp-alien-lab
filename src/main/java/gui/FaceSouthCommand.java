package gui;

import lifeform.LifeForm;

public class faceSouthCommand implements Command {
  
  LifeForm lifeform;

  public faceSouthCommand(LifeForm lf) {
    lifeform = lf;
  }
  
  public void execute() {
    lifeform.changeDirection("South");
    System.out.println(lifeform.getName() + " facing South");
  }

}
