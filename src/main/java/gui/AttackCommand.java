package gui;

import environment.Environment;
import exceptions.WeaponException;
import lifeform.LifeForm;

public class AttackCommand implements Command {
  
  LifeForm lifeform;
  String direction;
  Environment e;
  
  public AttackCommand(LifeForm lf, Environment ee) {
    lifeform = lf;
    direction = lifeform.getCurrentDirection();
    e = ee;
  }
  
  public void execute() {
    
    int row = lifeform.getRow();
    int col = lifeform.getCol();
    int distance = 0;
    
    try {
      if (direction == "North") {
      for (int i = row - 1; i >= -1; i--) {
        distance += 5;
        if (i < 0) {
          if (lifeform.hasWeapon()) {
            lifeform.getWeapon().fire(distance);
          }
          break;
        }
        if (i == 0 && e.getLifeForm(i, col) == null) {
          if (lifeform.hasWeapon()) {
            lifeform.getWeapon().fire(distance);
          }
          break;
        } 
        if (e.getLifeForm(i, col) != null) {
          lifeform.attack(e.getLifeForm(i, col), distance);
          System.out.println(lifeform.getName() + " attacked " + e.getLifeForm(i, col).getName());
          break;
        }
      }
      } else if (direction == "South") {
        for (int i = row + 1; i <= e.getNumRows(); i++) {
          distance += 5;
          if (i == e.getNumRows()) {
            if (lifeform.hasWeapon()) {
              lifeform.getWeapon().fire(distance);
            }
            break;
          }
          if (i == (e.getNumRows() - 1) && e.getLifeForm(i, col) == null) {
            if (lifeform.hasWeapon()) {
              lifeform.getWeapon().fire(distance);
            }
            break;
          } 
          if (e.getLifeForm(i, col) != null) {
            lifeform.attack(e.getLifeForm(i, col), distance);
            System.out.println(lifeform.getName() + " attacked " + e.getLifeForm(i, col).getName());
            break;
          }
        }
      } else if (direction == "East") {
        for (int i = col + 1; i <= e.getNumCols(); i++) {
          distance += 5;
          if (i == e.getNumCols()) {
            if (lifeform.hasWeapon()) {
              lifeform.getWeapon().fire(distance);
            }
            break;
          }
          if (i == (e.getNumCols() - 1) && e.getLifeForm(row, i) == null) {
            if (lifeform.hasWeapon()) {
              lifeform.getWeapon().fire(distance);
            }
            break;
          } 
          if (e.getLifeForm(row, i) != null) {
            lifeform.attack(e.getLifeForm(row, i), distance);
            System.out.println(lifeform.getName() + " attacked " + e.getLifeForm(row, i).getName());
            break;
          }
        }
      } else if (direction == "West") {
        for (int i = col - 1; i >= -1; i--) {
          distance += 5;
          if (i < 0) {
            if (lifeform.hasWeapon()) {
              lifeform.getWeapon().fire(distance);
            }
            break;
          }
          if (i == 0 && e.getLifeForm(row, i) == null) {
            if (lifeform.hasWeapon()) {
              lifeform.getWeapon().fire(distance);
            }
            break;
          } 
          if (e.getLifeForm(row, i) != null) {
            lifeform.attack(e.getLifeForm(row, i), distance);
            System.out.println(lifeform.getName() + " attacked " + e.getLifeForm(row, i).getName());
            break;
          }
        }
      }
      
      if (lifeform.hasWeapon() == false || distance <= 5) {
        System.out.println(lifeform.getName() + " attacks with melee");
      } else {
        System.out.println(lifeform.getName() + " Ammo: " + lifeform.getWeapon().getCurrentAmmo());
      }
    
    } catch (WeaponException exception) {
      System.out.println("Error: WeaponException thrown in attack command");
    }
    
  }

}
