package gui;

import environment.Environment;
import exceptions.WeaponException;
import lifeform.LifeForm;

public class attackCommand implements Command {
  
  LifeForm lifeform;
  String direction;
  Environment e;
  
  public attackCommand(LifeForm lf, Environment ee) {
    lifeform = lf;
    direction = lifeform.getCurrentDirection();
    e = ee;
  }
  
  public void execute() {
    
    int row = lifeform.getRow();
    int col = lifeform.getCol();
    int distance = 0;
    
    if (lifeform == null) {
      System.out.println("Error: no lifeform selected");
    } else if (lifeform.getWeapon().getCurrentAmmo() == 0) {
      System.out.println("Error: no ammo");
    } else {
      try {
        if (direction == "North") {
          for (int i = row - 1; i >= -1; i--) {
            distance += 5;
            if (i < 0) {
              lifeform.getWeapon().fire(distance);
              break;
            }
            if (i == 0 && e.getLifeForm(i, col) == null) {
              lifeform.getWeapon().fire(distance);
              break;
            } 
            if (e.getLifeForm(i, col) != null) {
              lifeform.attack(e.getLifeForm(i, col), distance);
              System.out.println(lifeform.getName() + " did " + lifeform.getWeapon().fire(distance) + " damage to " + e.getLifeForm(i, col).getName());
              break;
            }
          }
        } else if (direction == "South") {
          for (int i = row + 1; i <= e.getNumRows(); i++) {
            distance += 5;
            if (i == e.getNumRows()) {
              lifeform.getWeapon().fire(distance);
              break;
            }
            if (i == (e.getNumRows() - 1) && e.getLifeForm(i, col) == null) {
              lifeform.getWeapon().fire(distance);
              break;
            } 
            if (e.getLifeForm(i, col) != null) {
              lifeform.attack(e.getLifeForm(i, col), distance);
              System.out.println(lifeform.getName() + " did " + lifeform.getWeapon().fire(distance) + " damage to " + e.getLifeForm(i, col).getName());
              break;
            }
          }
        } else if (direction == "East") {
          for (int i = col + 1; i <= e.getNumCols(); i++) {
            distance += 5;
            if (i == e.getNumCols()) {
              lifeform.getWeapon().fire(distance);
              break;
            }
            if (i == (e.getNumCols() - 1) && e.getLifeForm(row, i) == null) {
              lifeform.getWeapon().fire(distance);
              break;
            } 
            if (e.getLifeForm(row, i) != null) {
              lifeform.attack(e.getLifeForm(row, i), distance);
              System.out.println(lifeform.getName() + " did " + lifeform.getWeapon().fire(distance) + " damage to " + e.getLifeForm(row, i).getName());
              break;
            }
          }
        } else if (direction == "West") {
          for (int i = col - 1; i >= -1; i--) {
            distance += 5;
            if (i < 0) {
              lifeform.getWeapon().fire(distance);
              break;
            }
            if (i == 0 && e.getLifeForm(row, i) == null) {
              lifeform.getWeapon().fire(distance);
              break;
            } 
            if (e.getLifeForm(row, i) != null) {
              lifeform.attack(e.getLifeForm(row, i), distance);
              System.out.println(lifeform.getName() + " did " + lifeform.getWeapon().fire(distance) + " damage to " + e.getLifeForm(row, i).getName());
              break;
            }
          }
        }
        
        System.out.println(lifeform.getName() + " Ammo: " + lifeform.getWeapon().getCurrentAmmo());
        
      
      } catch (WeaponException exception) {
        System.out.println("Error: WeaponException thrown in attack command");
      }
    }
    
  }

}
