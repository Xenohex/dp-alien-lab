package gui;

import lifeform.LifeForm;

public class ReloadCommand implements Command {

  LifeForm lifeform;

  /**
   * 
   * @param lf  lifeform
   * Uses lifeform to determine the selected lifeform
   */
  public ReloadCommand(LifeForm lf) {
    lifeform = lf;
  }
  
  /**
   * Determines if the selected lifeform has a weapon, and if it does, it reloads
   * the weapon, therefore resetting the weapon's ammo to full.
   */
  public void execute() {
    
    if (lifeform.getWeapon() == null) {
      System.out.println("Error: lifeform has no weapon");
    } else {
      lifeform.reload();
      System.out.println("Reload executed");
    }
  }

}
