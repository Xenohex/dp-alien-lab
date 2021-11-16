package gui;

import environment.Environment;
import lifeform.LifeForm;
import weapon.*;

public class AcquireCommand implements Command {
    
  LifeForm lifeform;
  Environment e;

  public AcquireCommand(LifeForm lf, Environment ee) {
    lifeform = lf;
    e = ee;
  }
  public void execute() {
    
    if (e.getCell(lifeform.getRow(), lifeform.getCol()).getWeapon1() == null) {
      System.out.println("Error: no weapon in cell");
    } else if (e.getCell(lifeform.getRow(), lifeform.getCol()).getWeapon2() != null) {
      Weapon w = lifeform.dropWeapon();
      lifeform.pickUpWeapon(e.getCell(lifeform.getRow(), lifeform.getCol()).getWeapon1());
      e.removeWeapon(lifeform.getWeapon(), lifeform.getRow(), lifeform.getCol());
      e.addWeapon(w, lifeform.getRow(), lifeform.getCol());
      System.out.println("Weapon switched");
    } else if (e.getCell(lifeform.getRow(), lifeform.getCol()).getWeapon1() != null ) {
      Weapon w = lifeform.dropWeapon();
      lifeform.pickUpWeapon(e.getCell(lifeform.getRow(), lifeform.getCol()).getWeapon1());
      e.removeWeapon(lifeform.getWeapon(), lifeform.getRow(), lifeform.getCol());
      e.addWeapon(w, lifeform.getRow(), lifeform.getCol());
      System.out.println("Weapon switched");
    } else {
      lifeform.pickUpWeapon(e.getCell(lifeform.getRow(), lifeform.getCol()).getWeapon1());
      e.removeWeapon(lifeform.getWeapon(), lifeform.getRow(), lifeform.getCol());
      System.out.println("acquire weapon executed");
    }
  }

}
