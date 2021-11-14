package gui;

import environment.*;
import exceptions.*;
import lifeform.*;
import weapon.*;

public class reloadCommand implements Command {

  LifeForm lifeform;

  public reloadCommand(LifeForm lf) {
    lifeform = lf;
  }
  public void execute() {
    lifeform.reload();
  }

}
